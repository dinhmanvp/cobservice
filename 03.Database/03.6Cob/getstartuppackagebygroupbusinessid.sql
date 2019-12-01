CREATE OR REPLACE FUNCTION mdl_cob.getstartuppackagebygroupbusinessid(id VARCHAR(150), isavailable INTEGER, language_code VARCHAR(10)) 
RETURNS TABLE(
n_id mdl_cob.tb_startup_package.n_id%TYPE,
s_startup_package_id mdl_cob.tb_startup_package.s_startup_package_id%TYPE,
s_group_business_id mdl_cob.tb_startup_package.s_group_business_id%TYPE,
s_package_name TEXT,
-- s_package_name_en mdl_cob.tb_startup_package.s_package_name_en%TYPE,
-- s_package_name_vn mdl_cob.tb_startup_package.s_package_name_vn%TYPE,
-- s_package_name_cn mdl_cob.tb_startup_package.s_package_name_cn%TYPE,
s_package_icon mdl_cob.tb_startup_package.s_package_icon%TYPE,
-- s_package_desc_en mdl_cob.tb_startup_package.s_package_desc_en%TYPE,
-- s_package_desc_vn mdl_cob.tb_startup_package.s_package_desc_vn%TYPE,
-- s_package_desc_cn mdl_cob.tb_startup_package.s_package_desc_cn%TYPE,
s_package_desc TEXT,
n_price mdl_cob.tb_startup_package.n_price%TYPE,
n_discount_price mdl_cob.tb_startup_package.n_discount_price%TYPE,
n_sale_pricce mdl_cob.tb_startup_package.n_sale_pricce%TYPE,
n_is_available mdl_cob.tb_startup_package.n_is_available%TYPE,
s_currency_id mdl_cob.tb_startup_package.s_currency_id%TYPE,
amount_click_star mdl_cob.tb_startup_package.amount_click_star%TYPE,
s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
n_sale_price_cob NUMERIC
)
AS 
$$
	SELECT  	pk.n_id,
			 	pk.s_startup_package_id, 
			 	pk.s_group_business_id, 
			 	(CASE 
			 		WHEN LOWER(language_code) = 'en' THEN pk.s_package_name_en
				 	WHEN LOWER(language_code) = 'vn' THEN pk.s_package_name_vn
				 	WHEN LOWER(language_code) = 'cn' THEN pk.s_package_name_cn
				END) s_package_name, 
			 	pk.s_package_icon, 
			 	(CASE 
					WHEN LOWER(language_code) ='en' THEN pk.s_package_desc_en
					WHEN LOWER(language_code) = 'vn' THEN pk.s_package_desc_vn
					WHEN LOWER(language_code) = 'cn' THEN pk.s_package_desc_cn
				 END) s_package_desc ,
				pk.n_price, 
				pk.n_discount_price, 
				pk.n_sale_pricce, 
				pk.n_is_available, 
				pk.s_currency_id, 
				pk.amount_click_star,
				s_currency_symbol, 
				mdl_cob.exchangetocobcurrency(pk.s_currency_id, CAST (n_sale_pricce AS NUMERIC)) AS n_sale_price_cob
	FROM mdl_cob.tb_startup_package pk INNER JOIN mdl_core.tb_currencies cur ON pk.s_currency_id = cur.s_currency_id
	WHERE pk.s_group_business_id = id
			AND pk.n_is_available = (	CASE 
												WHEN isavailable = 0 OR isavailable = 1 THEN isavailable
												ELSE pk.n_is_available 
											END)
	ORDER BY pk.s_startup_package_id asc
$$
LANGUAGE sql;