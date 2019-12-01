DROP function mdl_finance.saveTransaction
call mdl_finance.saveTransaction(
'c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198'
,'84e43c2e3527ce263da6e8d7c0a9577ca2d14d98535ae41a4f4bbbf215279754'
,'159059c5d4095ce112fe91d022d7cc1c'
,'159059c5d4095ce112fe91d022d7cc1c'
,'86b1ed11f6e16492dd166eb5655d288cecf27945e5e2b839ce07ee623303dbcc'
,'67b2520dfab09760edf435ab10586cd1ec5ac8452ff4df1298662358b7b999c8'
,'78d5980fbbd9d0c5b0c37e0bcb429bd0'
,'07b5f27246ff6e8da175bd4f6cd15201'
, 'transactionId789hfssd'
,1
,'COB'
,'TRANFER_TO_USER'
,''
,'001')
DELIMITER //
CREATE OR REPLACE PROCEDURE mdl_finance.saveTransaction(
fromUserId TEXT,
fromPocketId TEXT   ,
avaiFrom TEXT  ,
balFrom TEXT ,
toUserId TEXT  ,
toPocketId TEXT  ,
avaiTo TEXT  ,
balTo TEXT  ,
sTransactionId TEXT  ,
amount NUMERIC  ,
currencyId VARCHAR(10)  ,
transactionType VARCHAR(50)  ,
massage TEXT  ,
channelId VARCHAR(10)
) --RETURNS VOID 
AS 
$$
BEGIN 

UPDATE mdl_finance.tb_mypocket
SET n_available_balance = avaiFrom, n_balance = balFrom
WHERE s_pocket_id = fromPocketId;
--COMMIT;
--
UPDATE mdl_finance.tb_mypocket
SET n_available_balance = avaiTo, n_balance = balTo
WHERE s_pocket_id = toPocketId;
--COMMIT;
--
INSERT INTO mdl_finance.tb_transactions(
s_transaction_id
, s_from_user_id
, s_to_user_id
, n_amount
, s_currency_id
, s_pocket_id
, s_transaction_type_id
, d_transaction_date
, n_transaction_status_id
, s_trans_message
, s_channel_id
--, s_e_contract_no
--, n_cashback_amt
--, d_cashback_validate_date
--, s_cashback_validate_by
--, d_cashback_approved_date
--, s_cashback_approved_by
--, s_ref_transaction_id
--, n_otp_auth
--, n_otp_confirmed
--, n_otp_confirmed_times
--, d_trans_confirm_date
)
VALUES (sTransactionId
, fromUserId
, toUserId
, amount
, currencyId
, fromPocketId
, transactionType
, NOW()
, 1
, massage
, channelId );
END

$$
LANGUAGE plpgsql;

SELECT * FROM mdl_partner.tb_partner_account a WHERE a.s_partner_id = '1573815250354'

UPDATE mdl_finance.tb_mypocket
SET n_available_balance = '159059c5d4095ce112fe91d022d7cc1c', n_balance = '159059c5d4095ce112fe91d022d7cc1c'
WHERE s_pocket_id = '84e43c2e3527ce263da6e8d7c0a9577ca2d14d98535ae41a4f4bbbf215279754';

UPDATE mdl_finance.tb_mypocket
SET n_available_balance = '78d5980fbbd9d0c5b0c37e0bcb429bd0', n_balance = '07b5f27246ff6e8da175bd4f6cd15201'
WHERE s_pocket_id = '67b2520dfab09760edf435ab10586cd1ec5ac8452ff4df1298662358b7b999c8';




