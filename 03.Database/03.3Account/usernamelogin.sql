CREATE OR REPLACE FUNCTION mdl_account.usernamelogin
(
	Username VARCHAR(50),
	Password TEXT
)
RETURNS TABLE
(
	n_id mdl_account.tb_user.n_id%TYPE,
	s_user_id mdl_account.tb_user.s_user_id%TYPE,
	s_username mdl_account.tb_user.s_username%TYPE,
	b_password mdl_account.tb_user.b_password%TYPE,
	s_email mdl_account.tb_user.s_email%TYPE,
	s_phone mdl_account.tb_user.s_phone%TYPE,
	d_lastlogin mdl_account.tb_user.d_lastlogin%TYPE,
	d_lastlogout mdl_account.tb_user.d_lastlogout%TYPE,
	s_customer_id mdl_account.tb_user.s_customer_id%TYPE,
	is_validate mdl_account.tb_user.is_validate%TYPE,
	is_change_password mdl_account.tb_user.is_change_password%TYPE,
	s_key mdl_account.tb_user.s_key%TYPE,
	b_avatar mdl_account.tb_customer.b_avatar%TYPE,
	s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
	s_currency_image mdl_core.tb_currencies.s_currency_image%TYPE,
	n_available_balance mdl_finance.tb_mypocket.n_available_balance%TYPE,
	s_fullname TEXT,
	s_card_id_no mdl_account.tb_customer.s_card_id_no%TYPE
) AS 
$$
	SELECT us.*, cus.b_avatar, cur.s_currency_symbol, cur.s_currency_image, poc.n_available_balance
	,CONCAT(cus.s_lastname,' ', cus.s_firstname) AS s_fullname
	,cus.s_card_id_no
	FROM mdl_account.tb_user AS us
	INNER JOIN mdl_account.tb_customer cus
	ON cus.s_customer_id = us.s_customer_id
	INNER JOIN mdl_finance.tb_mypocket AS poc
	ON poc.s_user_id = us.s_user_id
	INNER JOIN mdl_core.tb_currencies AS cur
	ON cur.s_currency_id = poc.s_currency_id	
	WHERE upper(us.s_username) = upper(Username) AND cur.s_currency_symbol = 'COB'
	AND us.b_password = PASSWORD AND us.is_validate = 1
$$
LANGUAGE sql;