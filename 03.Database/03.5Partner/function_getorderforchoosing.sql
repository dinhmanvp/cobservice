CREATE OR REPLACE FUNCTION mdl_partner.getorderforchoosing(partnerId TEXT, businessServiceId TEXT, cateId TEXT, apdate DATE, languageCode text)
RETURNS TABLE(
	s_order_number mdl_business.tb_order.s_order_number%TYPE,
	n_appointment_number mdl_business.tb_order.n_appointment_number%TYPE,
	s_patient_name mdl_business.tb_order.s_patient_name%TYPE,
	s_patient_gender mdl_business.tb_order.s_patient_gender%TYPE,
	s_patient_age mdl_business.tb_order.s_patient_age%TYPE,
	s_appointment_time mdl_business.tb_order.s_appointment_time%TYPE,
	n_bhyt mdl_business.tb_order.n_bhyt%TYPE,
	s_address TEXT,
	s_full_name TEXT
) 
AS 
$$
	SELECT s_order_number
			, n_appointment_number
			, s_patient_name
			, s_patient_gender
			, s_patient_age
			, s_appointment_time
			, n_bhyt
			, CASE WHEN languageCode = '' OR LOWER(languageCode) = 'vn' THEN ci.s_city_name_vn
			  		 WHEN LOWER(languageCode) = 'cn' THEN ci.s_city_name_cn
					 ELSE ci.s_city_name_vn 
				END s_address
			,'' s_full_name
	FROM mdl_business.tb_order o
	INNER JOIN mdl_account.tb_user u ON o.s_buyer_user_id = u.s_user_id
	INNER JOIN mdl_account.tb_customer c ON u.s_customer_id = c.s_customer_id
	INNER JOIN mdl_core.tb_city ci ON c.s_contact_city_id = ci.s_city_id
	WHERE o.s_seller_partner_id = partnerId
	AND o.n_order_status_id = 1
	AND o.s_business_service_id = businessServiceId
	AND o.s_group_business_cate_id = (CASE cateId WHEN '' THEN o.s_group_business_cate_id ELSE cateId END )
	AND o.d_appointment_date = apdate AND o.s_seller_user_id ISNULL 
	ORDER BY n_appointment_number
$$
LANGUAGE sql









SELECT * FROM mdl_business.tb_order o WHERE o.s_seller_partner_id = '1570763371113'  
AND o.n_order_status_id = 1 AND o.d_appointment_date = '2019-11-12'
AND o.s_seller_user_id = 'c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198'

UPDATE mdl_business.tb_order  SET s_seller_user_id = NULL  
WHERE s_seller_partner_id = '1570763371113' 
AND s_buyer_user_id = '7b8e5acb6806c0fa76fc3d78ede096e2adbc07e1778d23b55f3a364f615e66ad' AND n_order_status_id = 1
AND n_appointment_number >9 
