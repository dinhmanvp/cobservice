DROP FUNCTION mdl_partner.getbusinesscate(TEXT,TEXT,TEXT,TEXT,TEXT)
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.getbusinesscate(partnerId TEXT, buzserviceId TEXT, languagecode TEXT, fromDate TEXT, toDate TEXT)
RETURNS TABLE 
(
s_group_business_cate_id mdl_core.tb_groupbusiness_cate.s_group_business_cate_id%TYPE,
s_group_business_cate_name mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE,
s_group_business_description character varying,
i_group_business_cate_icon mdl_core.tb_groupbusiness_cate.i_group_business_cate_icon%TYPE,
n_count_order BIGINT
) AS
$$
BEGIN		
	return query
	SELECT 	gbc.s_group_business_cate_id,
				CASE WHEN upper(languagecode) = '' OR languagecode = 'VN' THEN gbc.s_group_business_cate_name_vn
					WHEN upper(languagecode) = 'EN' THEN gbc.s_group_business_name_cate_en
					ELSE gbc.s_group_business_name_cate_cn
				END s_group_business_cate_name,
				CASE WHEN upper(languagecode) = '' OR languagecode = 'VN' THEN gbc.s_group_business_description_vn
					WHEN upper(languagecode) = 'EN' THEN gbc.s_group_business_description_en
					ELSE gbc.s_group_business_name_cate_cn
				END s_group_business_description,
			   ('core/getImage/groupbusinesscate/' || gbc.s_group_business_cate_id) i_group_business_cate_icon,
				--COUNT(o.s_order_number)
					COALESCE(o.n_order_count,0) n_order_count
	FROM mdl_partner.tb_partner_bizcate pb 
	inner join mdl_core.tb_groupbusiness_cate gbc ON pb.s_group_business_cate_id = gbc.s_group_business_cate_id
	LEFT JOIN (
		SELECT od.s_seller_partner_id, od.s_business_service_id, od.s_group_business_cate_id, COUNT(od.s_group_business_cate_id) n_order_count
		 from mdl_business.tb_order od WHERE od.s_seller_partner_id = partnerId AND od.s_business_service_id = buzserviceId 
															AND CASE WHEN  fromDate = '' AND toDate = '' THEN od.d_appointment_date = od.d_appointment_date
																		WHEN  fromDate = '' AND toDate != '' THEN od.d_appointment_date <= CAST (toDate AS DATE)
																		WHEN  fromDate != '' AND toDate = '' THEN od.d_appointment_date >= CAST (fromDate AS DATE)
																		ELSE od.d_appointment_date >= cast(fromDate AS DATE) AND od.d_appointment_date <= CAST (toDate AS DATE)
																END 
															AND od.n_order_status_id = 1 
															GROUP BY od.s_seller_partner_id, od.s_business_service_id, od.s_group_business_cate_id
															)o ON pb.s_group_business_cate_id = o.s_group_business_cate_id
	WHERE pb.s_partner_id = partnerId 
	GROUP BY o.n_order_count, gbc.s_group_business_cate_id, s_group_business_cate_name, s_group_business_description, gbc.i_group_business_cate_icon;
END;
$$
LANGUAGE plpgsql;
