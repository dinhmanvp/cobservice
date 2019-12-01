CREATE OR REPLACE FUNCTION mdl_cob.getadviseservicespricing(adviseServiceId text,serviceUnitId TEXT, languegeCode TEXT, contentSearch TEXT)
RETURNS TABLE
(
n_id mdl_cob.tb_advise_service_pricing.n_id%TYPE,
s_advise_service_pricing_id mdl_cob.tb_advise_service_pricing.s_advise_service_pricing_id%TYPE,
n_price mdl_cob.tb_advise_service_pricing.n_price%TYPE,
n_discount_amount mdl_cob.tb_advise_service_pricing.n_discount_amount%TYPE,
n_saleprice mdl_cob.tb_advise_service_pricing.n_saleprice%TYPE,
d_from_date mdl_cob.tb_advise_service_pricing.d_from_date%TYPE,
d_to_date mdl_cob.tb_advise_service_pricing.d_to_date%TYPE,
n_is_available mdl_cob.tb_advise_service_pricing.n_is_available%TYPE,
s_advise_service_id mdl_cob.tb_advise_service_pricing.s_advise_service_id%TYPE,
s_service_unit_id mdl_cob.tb_advise_service_pricing.s_service_unit_id%TYPE,
n_qty mdl_cob.tb_advise_service_pricing.n_qty%TYPE,
s_currency_id mdl_cob.tb_advise_service_pricing.s_currency_id%TYPE,
s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
n_sale_price_cob NUMERIC(13,2),
s_advise_service_name TEXT,
s_advise_service_shortdesc TEXT,
s_advise_service_icon TEXT,
n_advise_service_rating NUMERIC(4,3), 
s_unit_name TEXT,
s_duration_type TEXT,
n_count_order BIGINT
)
AS
$$ 
	SELECT pr.*,s_currency_symbol,  mdl_cob.exchangetocobcurrency(pr.s_currency_id, CAST (n_saleprice AS NUMERIC(13,2))) AS n_sale_price_cob,
		(CASE WHEN languegeCode = '' THEN ads.s_advise_service_name_vn
				WHEN Lower(languegeCode) = 'en' THEN ads.s_advise_service_name_en
				WHEN Lower(languegeCode) = 'vn' THEN ads.s_advise_service_name_vn
				WHEN Lower(languegeCode) = 'cn' THEN ads.s_advise_service_name_cn END) s_advise_service_name,
		(CASE WHEN languegeCode = '' THEN ads.s_advise_service_shortdesc_vn
				WHEN Lower(languegeCode) = 'en' THEN ads.s_advise_service_shortdesc_en
				WHEN Lower(languegeCode) = 'vn' THEN ads.s_advise_service_shortdesc_vn
				WHEN Lower(languegeCode) = 'cn' THEN ads.s_advise_service_shortdesc_cn END) s_advise_service_shortdesc,
		ads.s_advise_service_icon,
		ads.n_advise_service_rating,
		(CASE WHEN languegeCode = '' THEN su.s_unit_name_vn
				WHEN Lower(languegeCode) = 'en' THEN su.s_unit_name_en
				WHEN Lower(languegeCode) = 'vn' THEN su.s_unit_name_vn
				WHEN Lower(languegeCode) = 'cn' THEN su.s_unit_name_cn END) s_unit_name,
		(CASE WHEN languegeCode = '' THEN dt.s_duration_type_name_vn
				WHEN Lower(languegeCode) = 'en' THEN dt.s_duration_type_name_en
				WHEN Lower(languegeCode) = 'vn' THEN dt.s_duration_type_name_vn
				WHEN Lower(languegeCode) = 'cn' THEN dt.s_duration_type_name_cn END) s_duration_type,
		(CASE contentSearch WHEN  '' THEN 0
								 ELSE (SELECT COUNT(*) FROM regexp_matches(LOWER(concat(ads.s_advise_service_name_en,
																					ads.s_advise_service_name_vn,
																					ads.s_advise_service_name_cn,
																					ads.s_advise_service_shortdesc_en,
																					ads.s_advise_service_shortdesc_vn,
																					ads.s_advise_service_shortdesc_cn,'g')
																		), lower(contentSearch)))END) n_count_row
	FROM mdl_cob.tb_advise_service_pricing pr 
	INNER JOIN mdl_core.tb_currencies cur ON pr.s_currency_id = cur.s_currency_id
	INNER JOIN mdl_cob.tb_advise_services ads ON pr.s_advise_service_id = ads.s_advise_service_id
	INNER JOIN mdl_cob.tb_service_unit su ON su.s_service_unit_id = pr.s_service_unit_id
	INNER JOIN mdl_cob.tb_duration_type dt ON dt.s_duration_type_id = su.s_duration_type
	WHERE pr.s_advise_service_id = (CASE adviseServiceId WHEN '' THEN pr.s_advise_service_id ELSE adviseServiceId END)
	AND pr.s_service_unit_id = (CASE serviceUnitId WHEN '' THEN pr.s_service_unit_id ELSE serviceUnitId END)
	AND pr.n_is_available = 1	
	AND pr.d_from_date <= NOW() AND NOW() <= pr.d_to_date
	AND CASE WHEN contentSearch != '' THEN  (SELECT COUNT(*) FROM regexp_matches(LOWER(concat(ads.s_advise_service_name_en,
		 ads.s_advise_service_name_vn,
		 ads.s_advise_service_name_cn,
		 ads.s_advise_service_shortdesc_en,
		 ads.s_advise_service_shortdesc_vn,
		 ads.s_advise_service_shortdesc_cn,'g')
		 ), LOWER (contentSearch))) > 0
		 	ELSE 0 = 0
 		END												
	ORDER BY n_count_row DESC, pr.d_from_date DESC, pr.d_to_date DESC
	
$$
LANGUAGE sql;
