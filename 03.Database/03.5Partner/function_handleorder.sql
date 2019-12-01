DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.handleorder(partnerId TEXT, businessServiceId TEXT, cateId TEXT, apdate DATE, sellerUserId TEXT, languageCode text)
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
) as
$$
begin
		RETURN query
		SELECT * FROM mdl_partner.getorderbyselleruserId(partnerId, businessServiceId, cateId, apdate, sellerUserId, languageCode);
		UPDATE mdl_business.tb_order SET n_order_status_id = 2
		WHERE mdl_business.tb_order.s_order_number IN (SELECT firstorder.s_order_number 
																		FROM (SELECT *, row_number() over () AS row_number
																				FROM (SELECT * 
																						FROM mdl_partner.getorderbyselleruserId(partnerId, businessServiceId, cateId, apdate, sellerUserId, languageCode)) _orders )firstorder 
																		WHERE firstorder.row_number = 1 );
END 
$$
LANGUAGE plpgsql;

DROP FUNCTION mdl_partner.handleorder

SELECT * FROM mdl_partner.handleorder('1570763371113','301001','BC3010001','2019-11-12','c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198','vn');
SELECT * FROM mdl_business.tb_order o WHERE o.s_seller_partner_id = '1570763371113' AND o.s_business_service_id = '301001' AND s_group_business_cate_id = 'BC3010001'

SELECT * FROM mdl_business.tb_order o WHERE o.s_order_number = '9e32b70c926738ffe229ea59c3c73ad226fafb1cb28056aaab86529710e96cae'