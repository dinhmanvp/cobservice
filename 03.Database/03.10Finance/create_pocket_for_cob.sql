--ví cob

INSERT INTO mdl_finance.tb_mypocket(s_pocket_id, n_balance, n_available_balance, s_currency_id, s_user_id, n_blocked_balance)
VALUES('COBPOKET','496e342aac0b59a26b65fd134fdb7c11', '496e342aac0b59a26b65fd134fdb7c11', 'COB', 'cob10000', 'ff2db2a8a160af02503c06f324279d30')

;

INSERT INTO mdl_account.tb_user(s_user_id, s_username, is_validate, s_key)
VALUES('cob10000', 'cty COB', 1, '89cea2097630c552c4d9434f32595aee')
;
----tạo account backoffice
INSERT INTO mdl_account.tb_user(s_user_id, s_username, is_validate, s_key, s_customer_id,b_password)
VALUES('cob00001', 'cob00001', 1, 'babe9b372cb154025929a6f72c10dae2','cob00001','4f45050d373ee00b558be31f33e579db')
;
INSERT INTO mdl_account.tb_customer(s_customer_id, s_firstname, s_lastname)
VALUES('cob00001', 'cty COB back office', '')

INSERT INTO mdl_finance.tb_mypocket(s_pocket_id, n_balance, n_available_balance, s_currency_id, s_user_id, n_blocked_balance)
VALUES('COBOFFICE','3230aea5d3b2d8147b22aeb70576e90e', '3230aea5d3b2d8147b22aeb70576e90e', 'COB', 'cob00001', '3230aea5d3b2d8147b22aeb70576e90e')
;