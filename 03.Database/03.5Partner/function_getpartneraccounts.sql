DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.getpartneraccounts(partnerid TEXT, languageCode VARCHAR(10), dappointmentDate TEXT, p_dayofWeek INTEGER)
RETURNS TABLE(
	n_id mdl_partner.tb_partner_account.n_id%TYPE,
	s_partner_account_id mdl_partner.tb_partner_account.s_partner_account_id%TYPE,
	s_user_id mdl_partner.tb_partner_account.s_user_id%TYPE,
	n_is_approved mdl_partner.tb_partner_account.n_is_approved%TYPE,
	s_comment mdl_partner.tb_partner_account.s_comment%TYPE,
	n_is_deleted mdl_partner.tb_partner_account.n_is_deleted%TYPE,
	s_staff_number mdl_partner.tb_partner_account.s_staff_number%TYPE,
	d_joined_date mdl_partner.tb_partner_account.d_joined_date%TYPE,
	s_partner_rule_id mdl_partner.tb_partner_account.s_partner_rule_id%TYPE,
	s_partner_id mdl_partner.tb_partner_account.s_partner_id%TYPE,
	s_full_name TEXT,
	b_avatar TEXT,
	s_rule_name mdl_partner.tb_partner_rules.s_rule_name_en%TYPE
)
AS $$
BEGIN
	
	CREATE TEMP TABLE sub as
		(SELECT 		 mb.s_user_id 
						,mb.s_partner_id 
						,t.n_count_per_day
						,n_duration_for_session
						,CASE WHEN p_dayofWeek = 1 THEN t.s_su_from
		    					WHEN p_dayofWeek = 2 THEN t.s_mo_from
		    					WHEN p_dayofWeek = 3 THEN t.s_tu_from
		    					WHEN p_dayofWeek = 4 THEN t.s_we_from
		    					WHEN p_dayofWeek = 5 THEN t.s_th_from
		    					WHEN p_dayofWeek = 6 THEN t.s_fr_from
		    					ELSE t.s_sa_from
		    			END from_Time
		    			,CASE WHEN p_dayofWeek = 1 THEN t.s_su_to
		    					WHEN p_dayofWeek = 2 THEN t.s_mo_to
		    					WHEN p_dayofWeek = 3 THEN t.s_tu_to
		    					WHEN p_dayofWeek = 4 THEN t.s_we_to
		    					WHEN p_dayofWeek = 5 THEN t.s_th_to
		    					WHEN p_dayofWeek = 6 THEN t.s_fr_to
		    					ELSE t.s_sa_to
		    			END to_Time
		    			,CASE WHEN p_dayofWeek = 1 THEN t.s_su_isoff
		    					WHEN p_dayofWeek = 2 THEN t.s_mo_isoff
		    					WHEN p_dayofWeek = 3 THEN t.s_tu_isoff
		    					WHEN p_dayofWeek = 4 THEN t.s_we_isoff
		    					WHEN p_dayofWeek = 5 THEN t.s_th_isoff
		    					WHEN p_dayofWeek = 6 THEN t.s_fr_isoff
		    					ELSE t.s_sa_to
		    			END is_off
		    			,orders.countPerSeller 
		    			,orders.maxNumber 
		    			,COALESCE(orders.maxTime, CASE WHEN p_dayofWeek = 1 THEN t.s_su_from::TIME
		    					WHEN p_dayofWeek = 2 THEN t.s_mo_from::TIME
		    					WHEN p_dayofWeek = 3 THEN t.s_tu_from::TIME
		    					WHEN p_dayofWeek = 4 THEN t.s_we_from::TIME
		    					WHEN p_dayofWeek = 5 THEN t.s_th_from::TIME
		    					WHEN p_dayofWeek = 6 THEN t.s_fr_from::TIME
		    					ELSE t.s_sa_from::TIME
		    			END ) maxTime
			FROM mdl_account.tb_my_business mb
			INNER JOIN mdl_account.tb_mybusiness_workingtime t
			ON mb.s_my_business_id = t.s_my_business_id
			LEFT JOIN (SELECT COUNT(ord.s_order_number) countPerSeller, ord.s_seller_user_id s_seller_user_id, MAX(ord.n_appointment_number) maxNumber,MAX(ord.s_appointment_time::TIME) maxTime
							from mdl_business.tb_order ord
							WHERE ord.s_seller_partner_id = partnerid AND ord.d_appointment_date = TO_DATE(dappointmentDate,'dd/mm/yyyy')
							GROUP BY ord.s_seller_user_id
							) orders
			ON mb.s_user_id = orders.s_seller_user_id
			WHERE mb.s_partner_id = partnerid 
			--AND orders.countPerSeller < n_count_per_day
			);

	return query
	SELECT pac.*
			 ,CONCAT(cus.s_firstname,' ',cus.s_lastname)  sfull_name
			 --,cus.b_avatar
			 ,('accounts/getImage/' || cus.s_customer_id) b_avatar
			 ,CASE WHEN languageCode = '' OR LOWER(languageCode) = 'vn' THEN rul.s_rule_name_vn
			 		 WHEN LOWER(languageCode) = 'cn' THEN rul.s_rule_name_cn
			 		 ELSE rul.s_rule_name_en
			 	END s_rule_name
	FROM mdl_partner.tb_partner_account pac
	INNER JOIN mdl_account.tb_user usr
	ON pac.s_user_id = usr.s_user_id
	INNER JOIN mdl_account.tb_customer cus
	ON usr.s_customer_id = cus.s_customer_id
	INNER JOIN mdl_partner.tb_partner_rules rul
	ON pac.s_partner_rule_id = rul.s_partner_rule_id
	
	LEFT JOIN sub ON pac.s_user_id = sub.s_user_id	
	 
	WHERE pac.n_is_approved = 1 
	AND pac.n_is_deleted <> 1
	AND pac.s_partner_id = partnerid
	AND COALESCE(sub.is_off,0) = 0
	AND CASE WHEN sub.s_user_id IS NULL
				THEN TRUE 
			ELSE  
				CASE WHEN TO_DATE(dappointmentDate,'dd/mm/yyyy') = NOW()::DATE
						THEN NOW()::TIME + make_interval(0,0,0,0,0,5) + make_interval(0,0,0,0,0,sub.n_duration_for_session)
						ELSE sub.maxTime::TIME + make_interval(0,0,0,0,0,sub.n_duration_for_session)
				END < sub.to_Time::TIME		
		END;
	DROP TABLE sub;
END;
$$  
LANGUAGE plpgsql;



SELECT * FROM sub
DROP TABLE sub

SELECT * FROM mdl_partner.getpartneraccounts('1570763371113','vn','27/11/2019',2)

SELECT pa.s_user_id, COUNT(o.s_order_number)
FROM mdl_partner.tb_partner_account pa LEFT JOIN mdl_business.tb_order o ON pa.s_partner_id = o.s_seller_partner_id AND pa.s_user_id = o.s_seller_user_id
WHERE pa.s_partner_id = '1570763371113' 
GROUP BY pa.s_user_id

SELECT COUNT(*) FROM mdl_business.tb_order o WHERE o.s_seller_user_id =  'c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198' AND o.s_seller_partner_id = '1570763371113'
SELECT * FROM mdl_partner.tb_partner_account pa WHERE pa.s_partner_id = '1570763371113' AND pa.s_user_id = 'c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198'
SELECT 
FROM mdl_business.tb_order WHERE 


SELECT MAX(o.s_appointment_time::TIME)
FROM mdl_business.tb_order o
WHERE o.s_seller_partner_id = '1570763371113' 
AND o.s_seller_user_id = 'ad65aadd89c51688efa258ed688af0015756214be13950f582f04e6e7791592e'
AND o.d_appointment_date = '2019-11-27'