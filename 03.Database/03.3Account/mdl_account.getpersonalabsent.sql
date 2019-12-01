DROP FUNCTION mdl_account.getpersonalabsent
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_account.getpersonalabsent(jsonInput TEXT)
RETURNS 
TABLE
(
n_id mdl_account.tb_personal_absent.n_id%TYPE,
d_absent_date mdl_account.tb_personal_absent.d_absent_date%TYPE,
s_absent_reson mdl_account.tb_personal_absent.s_absent_reson%TYPE,
s_my_business_id TEXT,
s_personal_absent_id mdl_account.tb_personal_absent.s_personal_absent_id%TYPE,
d_from_date mdl_account.tb_personal_absent.d_from_date%TYPE,
d_to_date mdl_account.tb_personal_absent.d_to_date%TYPE
)
AS
$$
DECLARE
p_user_id TEXT := jsonInput::json->>'suserid';
p_partner_id TEXT := jsonInput::json->>'spartnerid';
p_pageNumber INTEGER := jsonInput::json->>'pagenumber';
p_pageSize INTEGER := jsonInput::json->>'pagesize';
	BEGIN
	RETURN query
	SELECT list.n_id, list.d_absent_date, list.s_absent_reson, list.s_my_business_id, list.s_personal_absent_id, list.d_from_date, list.d_to_date
	FROM 
		(SELECT ab.n_id, ab.d_absent_date, ab.s_absent_reson, ab.s_my_business_id, ab.s_personal_absent_id, ab.d_from_date, ab.d_to_date, row_number() over (ORDER BY ab.d_absent_date DESC) row_index
		FROM mdl_account.tb_personal_absent ab
		INNER JOIN mdl_account.tb_my_business mybiz ON ab.s_my_business_id = mybiz.s_my_business_id
		WHERE mybiz.s_user_id = p_user_id AND mybiz.s_partner_id = p_partner_id
		) list
	WHERE list.row_index >= (p_pageNumber-1)*p_pageSize + 1 AND list.row_index < p_pageNumber*p_pageSize +1
	ORDER BY  list.d_from_date DESC, list.d_to_date DESC ;
	END;
$$
LANGUAGE plpgsql; 

SELECT * FROM  mdl_account.getpersonalabsent('{"suserid":"7b8e5acb6806c0fa76fc3d78ede096e2adbc07e1778d23b55f3a364f615e66ad","spartnerid":"7b8e5acb6806c0fa76fc3d78ede096e2adbc07e1778d23b55f3a364f615e66ad1573721151030","pagenumber":"1","pagesize":"10"}')

SELECT ab.n_id, ab.d_absent_date, ab.s_absent_reson, ab.s_my_business_id, ab.s_personal_absent_id, ab.d_from_date, ab.d_to_date, row_number() over (ORDER BY ab.d_absent_date DESC) row_index
		FROM mdl_account.tb_personal_absent ab
		INNER JOIN mdl_account.tb_my_business mybiz ON ab.s_my_business_id = mybiz.s_my_business_id
		WHERE mybiz.s_user_id = '7b8e5acb6806c0fa76fc3d78ede096e2adbc07e1778d23b55f3a364f615e66ad' AND mybiz.s_partner_id = '1570763371113'