CREATE OR REPLACE FUNCTION mdl_finance.readyDataForVerifyBalance(pocketId TEXT)
RETURNS TABLE
(
n_id mdl_finance.tb_mypocket.n_id%TYPE,
s_pocket_id  mdl_finance.tb_mypocket.s_pocket_id%TYPE,
n_balance mdl_finance.tb_mypocket.n_balance%TYPE,
n_available_balance mdl_finance.tb_mypocket.n_available_balance%TYPE,
s_currency_id mdl_core.tb_currencies.s_currency_id%TYPE,
s_user_id mdl_finance.tb_mypocket.s_user_id%TYPE,
n_blocked_balance  mdl_finance.tb_mypocket.n_blocked_balance%TYPE,
s_key mdl_account.tb_user.s_user_id%TYPE,
limit_balance mdl_core.tb_currencies.limit_balance%TYPE,
s_username mdl_account.tb_user.s_username%TYPE
) AS 
$$
	SELECT 
	poc.n_id,
	poc.s_pocket_id,
	poc.n_balance, 
	poc.n_available_balance, 
	poc.s_currency_id, 
	poc.s_user_id,
	poc.n_blocked_balance, 
	u.s_key, 
	cur.limit_balance,
	u.s_username
	FROM mdl_finance.tb_mypocket poc
	INNER JOIN mdl_core.tb_currencies cur
	ON poc.s_currency_id = cur.s_currency_id
	INNER JOIN mdl_account.tb_user u 
	ON poc.s_user_id = u.s_user_id
	WHERE poc.s_pocket_id = pocketId AND cur.s_currency_id = 'COB'
$$
LANGUAGE sql;