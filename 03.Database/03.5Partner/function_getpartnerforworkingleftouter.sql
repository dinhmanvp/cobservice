DROP FUNCTION mdl_partner.getpartnerforworking
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.getpartnerforworkingleftouter(userid TEXT, languagecode TEXT, fromDate TEXT, toDate TEXT)
RETURNS TABLE(
	n_id mdl_partner.tb_listpartners.n_id%TYPE,
	s_partner_id mdl_partner.tb_listpartners.s_partner_id%TYPE,
	s_partner_name mdl_partner.tb_listpartners.s_partner_name_vn%TYPE,
	s_business_phone mdl_partner.tb_listpartners.s_business_phone%TYPE,
	s_business_email mdl_partner.tb_listpartners.s_business_email%TYPE,
	s_address_no mdl_partner.tb_listpartners.s_address_no%TYPE,
	s_street mdl_partner.tb_listpartners.s_street%TYPE,
	s_company_logo mdl_partner.tb_listpartners.s_company_logo%TYPE,
	s_taxcode mdl_partner.tb_listpartners.s_taxcode%TYPE,
	s_owner_name mdl_partner.tb_listpartners.s_owner_name%TYPE,
	d_registration_date mdl_partner.tb_listpartners.d_registration_date%TYPE,
	s_opening_time mdl_partner.tb_listpartners.s_opening_time%TYPE,
	s_close_time mdl_partner.tb_listpartners.s_close_time%TYPE,
	s_group_business_id mdl_partner.tb_listpartners.s_group_business_id%TYPE,
	s_group_business_name mdl_core.tb_group_business.s_group_business_name_en%TYPE,
	s_business_type_id mdl_partner.tb_listpartners.s_business_type_id%TYPE,
	s_created_by mdl_partner.tb_listpartners.s_created_by%TYPE,
	s_partner_desc mdl_partner.tb_listpartners.s_partner_desc_vn%TYPE,
	n_partner_rating mdl_partner.tb_listpartners.n_partner_rating%TYPE,
	s_full_address mdl_partner.tb_listpartners.s_full_address%TYPE,
	n_is_approved mdl_partner.tb_listpartners.n_is_approved%TYPE,
	n_count_order BIGINT,
	s_my_business_id TEXT
)
AS $$
BEGIN		
	return query
	SELECT ls.n_id
			,ls.s_partner_id
			,CASE WHEN languagecode = '' OR languagecode = 'VN' THEN ls.s_partner_name_vn
					WHEN languagecode = 'EN' THEN ls.s_partner_name_en
					ELSE ls.s_partner_name_cn
			END s_partner_name
			,ls.s_business_phone
			,ls.s_business_email
			,ls.s_address_no
			,ls.s_street
			--,ls.s_company_logo
			,('partner/getImage/' || ls.s_partner_id) s_company_logo
			,ls.s_taxcode
			,ls.s_owner_name
			,ls.d_registration_date
			,ls.s_opening_time
			,ls.s_close_time
			,ls.s_group_business_id
			,COALESCE(CASE WHEN languagecode = '' OR lower(languagecode) = 'vn' THEN gb.s_group_business_name_vn
								WHEN lower(languagecode) = 'cn' THEN gb.s_group_business_name_cn
								ELSE gb.s_group_business_name_en END,'') s_group_business_name
			,ls.s_business_type_id
			,ls.s_created_by
			,CASE WHEN languagecode = '' OR languagecode = 'VN' THEN ls.s_partner_desc_vn
					WHEN languagecode = 'EN' THEN ls.s_partner_desc_en
					ELSE ls.s_partner_desc_cn
			END s_partner_desc
			,ls.n_partner_rating
			,ls.s_full_address
			,ls.n_is_approved
			,COUNT(od.s_order_number)
			,'' s_my_business_id
	FROM mdl_partner.tb_listpartners ls
	INNER JOIN mdl_partner.tb_partner_account ac 
	ON ac.s_partner_id = ls.s_partner_id
	LEFT JOIN ( SELECT *
		FROM 	mdl_account.tb_my_business mybiz
		WHERE mybiz.s_user_id = userid
	)mybiz
	ON ls.s_partner_id = mybiz.s_partner_id
	LEFT JOIN (SELECT * FROM mdl_business.tb_order 
					WHERE n_order_status_id = 1 
					AND CASE WHEN  fromDate = '' AND toDate = '' THEN d_appointment_date = d_appointment_date
								WHEN  fromDate = '' AND toDate != '' THEN d_appointment_date <= CAST (toDate AS DATE)
								WHEN  fromDate != '' AND toDate = '' THEN d_appointment_date >= CAST (fromDate AS DATE)
								ELSE d_appointment_date >= cast(fromDate AS DATE) AND d_appointment_date <= CAST (toDate AS DATE)
						END) od
					ON ls.s_partner_id = od.s_seller_partner_id
	LEFT JOIN mdl_core.tb_group_business gb ON ls.s_group_business_id = gb.s_group_business_id
	WHERE ls.n_is_approved = 1 
	AND ac.n_is_deleted != 1
	AND ac.n_is_approved = 1
	AND ac.s_user_id = userid
	AND mybiz.s_user_id IS NULL
	GROUP BY ls.s_partner_id, s_group_business_name;
	
END;
$$  
LANGUAGE plpgsql;