CREATE OR REPLACE FUNCTION mdl_core.getcityinfo(countryid TEXT, cityid TEXT) 
RETURNS SETOF mdl_core.tb_city   
AS 
$$
	SELECT  ct.* 
	FROM mdl_core.tb_city ct
	WHERE ct.s_country_id = (	CASE 
													WHEN countryid = '' THEN ct.s_country_id
													ELSE countryid 
													END)
			AND ct.s_city_id = (	CASE 
										WHEN cityid = '' THEN ct.s_city_id
										ELSE cityid 
										END)
	ORDER BY ct.s_city_id
$$
LANGUAGE sql;