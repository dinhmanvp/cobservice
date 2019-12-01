DELIMITER //
CREATE OR REPLACE FUNCTION mdl_finance.getPoketInfoForOther
(
	userId TEXT,
	phone TEXT,
	email TEXT,
	currencyId TEXT
)
RETURNS TABLE(
	n_id mdl_finance.tb_mypocket.n_id%TYPE,
	s_pocket_id mdl_finance.tb_mypocket.s_pocket_id%TYPE,
	n_balance mdl_finance.tb_mypocket.n_balance%TYPE,
	n_available_balance mdl_finance.tb_mypocket.n_available_balance%TYPE,
	s_currency_id mdl_finance.tb_mypocket.s_currency_id%TYPE,
	s_user_id mdl_finance.tb_mypocket.s_user_id%TYPE,
	n_blocked_balance mdl_finance.tb_mypocket.n_blocked_balance%TYPE,
	s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
	s_currency_image mdl_core.tb_currencies.s_currency_image%TYPE,
	s_key mdl_account.tb_user.s_key%TYPE
)
AS
$$
--DECLARE
--	p_totalPages bigint := 0;
--BEGIN
	SELECT pk.*, curr.s_currency_symbol, curr.s_currency_image, usr.s_key
	FROM mdl_finance.tb_mypocket pk
	INNER JOIN mdl_core.tb_currencies curr
	ON pk.s_currency_id = curr.s_currency_id
	INNER JOIN mdl_account.tb_user usr
	ON pk.s_user_id = usr.s_user_id
	WHERE pk.s_user_id = CASE WHEN userId = '' THEN pk.s_user_id ELSE userId END
			AND pk.s_currency_id = CASE WHEN currencyId = '' THEN pk.s_currency_id ELSE currencyId END
			AND usr.s_phone = CASE WHEN phone = '' THEN usr.s_phone ELSE phone END
			AND usr.s_email = CASE WHEN email = '' THEN usr.s_email ELSE email END
--END
$$
LANGUAGE sql;