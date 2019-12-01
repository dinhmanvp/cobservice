DROP FUNCTION mdl_finance.gettransactioninfo
CREATE OR REPLACE FUNCTION mdl_finance.gettransactioninfo(transferCoin VARCHAR(10), fromUserId TEXT, toUserId text)
RETURNS TABLE
(
s_currency_id mdl_core.tb_currencies.s_currency_id%TYPE,
limit_balance mdl_core.tb_currencies.limit_balance%TYPE,
s_key_from mdl_account.tb_user.s_key%TYPE,
n_balance_from mdl_finance.tb_mypocket.n_balance%TYPE,
n_available_balance_from mdl_finance.tb_mypocket.n_available_balance%TYPE,
n_blocked_balance_from mdl_finance.tb_mypocket.n_blocked_balance%TYPE,
s_username_from mdl_account.tb_user.s_username%TYPE,
s_pocket_id_from mdl_finance.tb_mypocket.s_pocket_id%TYPE,
s_key_to mdl_account.tb_user.s_key%TYPE,
n_balance_to mdl_finance.tb_mypocket.n_balance%TYPE,
n_available_balance_to mdl_finance.tb_mypocket.n_available_balance%TYPE,
n_blocked_balance_to mdl_finance.tb_mypocket.n_blocked_balance%TYPE,
n_pocket_id_to mdl_finance.tb_mypocket.s_pocket_id%TYPE,
n_id_pocket_from mdl_finance.tb_mypocket.n_id%TYPE,
n_id_pocket_to mdl_finance.tb_mypocket.n_id%TYPE
) AS 
$$

	SELECT 
	userFrom.s_currency_id
	,userFrom.limit_balance
	,userFrom.s_key
	,userFrom.n_balance
	,userFrom.n_available_balance
	,userFrom.n_blocked_balance
	,userFrom.s_username
	,userFrom.s_pocket_id
	,userTo.s_key
	,userTo.n_balance
	,userTo.n_available_balance
	,userTo.n_blocked_balance
	,userTo.s_pocket_id
	,userFrom.n_id
	,userTo.n_id
	FROM 
		(SELECT 
			cur.s_currency_id
			,cur.limit_balance
			,fromUser.s_key
			,fromPocket.n_balance
			,fromPocket.n_available_balance
			,fromPocket.n_blocked_balance
			,fromUser.s_username
			,fromPocket.s_pocket_id
			,fromPocket.n_id
		FROM mdl_core.tb_currencies cur 
		inner JOIN mdl_finance.tb_mypocket fromPocket ON cur.s_currency_id = fromPocket.s_currency_id
		INNER JOIN mdl_account.tb_user fromUser ON fromUser.s_user_id = fromPocket.s_user_id
		WHERE fromUser.s_user_id = fromUserId AND cur.s_currency_id = transferCoin) userFrom
	 
	INNER JOIN 

		(SELECT 
			cur.s_currency_id
			,cur.limit_balance
			,toUser.s_key
			,toPocket.n_balance
			,toPocket.n_available_balance
			,toPocket.n_blocked_balance
			,toPocket.s_pocket_id
			,toPocket.n_id
		FROM mdl_core.tb_currencies cur 
		inner JOIN mdl_finance.tb_mypocket toPocket ON cur.s_currency_id = toPocket.s_currency_id
		INNER JOIN mdl_account.tb_user toUser ON toUser.s_user_id = toPocket.s_user_id
		WHERE toUser.s_user_id = toUserId AND cur.s_currency_id = transferCoin) userTo
	ON userFrom.s_currency_id = userTo.s_currency_id
$$
LANGUAGE sql;
SELECT * FROM mdl_finance.gettransactioninfo('COB','c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198','86b1ed11f6e16492dd166eb5655d288cecf27945e5e2b839ce07ee623303dbcc')


SELECT nextval('mdl_finance.tb_transactions_n_id_seq')
