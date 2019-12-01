CREATE OR REPLACE FUNCTION mdl_business.medicalemaildata(orderId TEXT, languageCode VARCHAR(5))
RETURNS TABLE(
username mdl_account.tb_user.s_username%TYPE, 
email mdl_account.tb_user.s_email%TYPE, 
d_appointment_date mdl_business.tb_order.d_appointment_date%TYPE,
s_appointment_time mdl_business.tb_order.s_appointment_time%TYPE,
p_name mdl_business.tb_order.s_patient_name%TYPE, 
p_age mdl_business.tb_order.s_patient_age%TYPE,
gender mdl_business.tb_order.s_patient_gender%TYPE,
ordernumber mdl_business.tb_order.n_appointment_number%TYPE,
hospital TEXT, 
department TEXT
) AS
$$
	SELECT 
	u.s_username
	, u.s_email
	, o.d_appointment_date
	, o.s_appointment_time 
	, o.s_patient_name
	, o.s_patient_age
	,o.s_patient_gender
	,o.n_appointment_number
	,(CASE WHEN (languageCode = '' OR LOWER(languageCode) = 'vn') THEN p.s_partner_name_vn 
				WHEN LOWER(languageCode) = 'en' THEN p.s_partner_name_en 
				WHEN LOWER(languageCode) = 'cn' THEN p.s_partner_name_cn 
	END) hospital
	,COALESCE((CASE 
				WHEN (languageCode = '' OR LOWER(languageCode) = 'vn') THEN bc.s_group_business_cate_name_vn 
				WHEN LOWER(languageCode) = 'en' THEN bc.s_group_business_name_cate_en  
				WHEN LOWER(languageCode) = 'cn' THEN bc.s_group_business_name_cate_cn 
	END ),'') department 
	FROM mdl_business.tb_order o
	INNER JOIN mdl_account.tb_user u ON o.s_buyer_user_id = u.s_user_id
-- 	INNER JOIN mdl_account.tb_customer c ON u.s_customer_id = c.s_customer_id
	INNER JOIN mdl_partner.tb_listpartners p ON p.s_partner_id = o.s_seller_partner_id
	LEFT JOIN mdl_core.tb_groupbusiness_cate bc ON bc.s_group_business_cate_id = o.s_group_business_cate_id
	WHERE o.s_order_number = orderId
 $$
LANGUAGE sql;  
