/*
SELECT * FROM mdl_partner.getpartnerbyuserid('0000000009')
*/
CREATE OR REPLACE FUNCTION mdl_partner.getpartnerbyuserid
(
	UserId VARCHAR(150)
)
RETURNS SETOF mdl_partner.tb_listpartners AS 
$$
SELECT ls.* FROM mdl_partner.tb_partner_account ac
	inner JOIN mdl_partner.tb_listpartners ls ON ac.s_partner_id = ls.s_partner_id
	WHERE ac.s_user_id = UserId
$$
LANGUAGE sql;