DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.getbizserviceforworking(partnerid TEXT, languagecode TEXT, fromDate TEXT, toDate TEXT)
RETURNS TABLE(
s_business_service_id mdl_core.tb_business_services.s_business_service_id%TYPE,
s_business_service_name  mdl_core.tb_business_services.s_business_service_name_en%TYPE,
i_business_service_icon  mdl_core.tb_business_services.i_business_service_icon%TYPE,
s_business_service_desc_shrink mdl_core.tb_business_services.s_business_service_desc_shrink_en%TYPE,
s_business_service_desc mdl_core.tb_business_services.s_business_service_desc_en%TYPE,
n_business_service_rating mdl_core.tb_business_services.n_business_service_rating%TYPE,
n_count_order BIGINT
)AS
$$
BEGIN		
	return query
	SELECT 
	p_bs.s_business_service_id, 
	CASE WHEN languagecode = '' OR languagecode = 'VN' THEN bs.s_business_service_name_vn
					WHEN languagecode = 'EN' THEN bs.s_business_service_name_en
					ELSE bs.s_business_service_name_cn
			END s_business_service_name, 
	--bs.i_business_service_icon, 
	('core/getImage/businessservices/' || bs.s_business_service_id) i_business_service_icon,
	CASE WHEN languagecode = '' OR languagecode = 'VN' THEN bs.s_business_service_desc_shrink_vn
					WHEN languagecode = 'EN' THEN bs.s_business_service_desc_shrink_en
					ELSE bs.s_business_service_desc_shrink_cn
			END s_business_service_desc_shrink, 
	CASE WHEN languagecode = '' OR languagecode = 'VN' THEN bs.s_business_service_desc_vn
					WHEN languagecode = 'EN' THEN bs.s_business_service_desc_en
					ELSE bs.s_business_service_desc_cn
			END s_business_service_desc,
	 bs.n_business_service_rating,
	--COUNT(o.s_order_number) n_order_count
	COALESCE(o.n_order_count,0) n_order_count
	FROM mdl_partner.tb_partner_business_services p_bs
	INNER JOIN mdl_core.tb_business_services bs ON p_bs.s_business_service_id = bs.s_business_service_id
	LEFT JOIN (
				SELECT od.s_seller_partner_id, od.s_business_service_id, COUNT(od.s_business_service_id) n_order_count from mdl_business.tb_order od 
				WHERE od.s_seller_partner_id = partnerid 	
				AND od.n_order_status_id = 1 
				AND CASE WHEN  fromDate = '' AND toDate = '' THEN od.d_appointment_date = od.d_appointment_date
								WHEN  fromDate = '' AND toDate != '' THEN od.d_appointment_date <= CAST (toDate AS DATE)
								WHEN  fromDate != '' AND toDate = '' THEN od.d_appointment_date >= CAST (fromDate AS DATE)
								ELSE od.d_appointment_date >= cast(fromDate AS DATE) AND od.d_appointment_date <= CAST (toDate AS DATE)
						END
				GROUP BY od.s_seller_partner_id, od.s_business_service_id
				) o 
	ON p_bs.s_business_service_id = o.s_business_service_id
	WHERE p_bs.s_partner_id = partnerid	AND p_bs.n_is_activated = 1;
	-- GROUP BY o.s_order_number,
-- p_bs.s_business_service_id,
-- s_business_service_name,
-- i_business_service_icon,
-- s_business_service_desc_shrink,
-- s_business_service_desc,
-- 				n_business_service_rating
END;
$$
LANGUAGE plpgsql;

