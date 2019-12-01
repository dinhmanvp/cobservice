DELIMITER //
CREATE OR REPLACE FUNCTION mdl_core.loadCompanyType
(
	languageCode TEXT
)
RETURNS TABLE(
	s_company_type_id mdl_core.tb_company_type.s_company_type_id%TYPE,
	s_company_type_name mdl_core.tb_company_type.s_company_type_name_vn%TYPE
)
AS
$$
	SELECT com.s_company_type_id
			,CASE WHEN languageCode = '' OR UPPER(languageCode) = 'VN' THEN com.s_company_type_name_vn
					WHEN UPPER(languageCode) = 'EN' THEN com.s_company_type_name_en
					ELSE com.s_company_type_name_cn
			 END s_company_type_name
	FROM mdl_core.tb_company_type com
	ORDER BY com.s_company_type_id
$$
LANGUAGE sql;