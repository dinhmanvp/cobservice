DELIMITER //
CREATE OR REPLACE FUNCTION mdl_account.regispersonalabsent(jsonInput TEXT)
RETURNS INT
AS 
$$
DECLARE
p_id TEXT := jsonInput::json->>'sabsentid';
p_absent_reason TEXT := jsonInput::json->>'sabsentReson';
p_fromdate TEXT := jsonInput::json->>'dfromdate';
p_todate TEXT := jsonInput::json->>'dtodate';
p_userid TEXT := jsonInput::json->>'suserid';
p_partnerid TEXT := jsonInput::json->>'spartnerid';
p_result INTEGER := 0;
BEGIN 
	INSERT INTO mdl_account.tb_personal_absent(s_personal_absent_id,s_my_business_id, d_absent_date,s_absent_reson,d_from_date,d_to_date)
	SELECT  params.p_id, mybiz.s_my_business_id, NOW()::DATE, params.p_absent_reason, to_date(params.p_fromdate, 'dd/MM/yyyy'), to_date(params.p_todate, 'dd/MM/yyyy')
	FROM
		(SELECT p_id, p_absent_reason, p_fromdate, p_todate) params
		INNER JOIN 
		(SELECT s_my_business_id FROM mdl_account.tb_my_business WHERE s_user_id = p_userid AND s_partner_id = p_partnerid) mybiz
		ON TRUE;
	p_result := 1;
	RETURN p_result;
END;
$$
LANGUAGE plpgsql;



SELECT mdl_account.regispersonalabsent('{"dabsentDate":"11/12/2019","sabsentReson":"Tao PersonalAbsent Lan 3 De Test","dfromdate":"28/11/2019","dtodate":"28/11/2019","suserid":"c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198","spartnerid":"1570763371113","sabsentid":"aab237c7-ebaf-4f0e-8ed6-2ab319eea73f"}')

SELECT * FROM mdl_partner.tb_partner_account WHERE s_partner_id = '1570763371113'