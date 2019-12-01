CREATE TABLE mdl_core.tb_currencies
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_currency_id VARCHAR(50) NOT NULL UNIQUE, 
	s_currency_symbol VARCHAR(15),
	s_currency_image TEXT
);

INSERT INTO mdl_core.tb_currencies(s_currency_id,s_currency_symbol) VALUES('001','COB')

CREATE SCHEMA mdl_finance

CREATE TABLE mdl_finance.tb_mypocket
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_pocket_id VARCHAR(150) UNIQUE NOT NULL,
	n_balance INTEGER,
	n_available_balance INTEGER,
	s_currency_id VARCHAR(50) NOT NULL,
	s_user_id VARCHAR(150) NOT NULL
);

ALTER TABLE mdl_finance.tb_mypocket ADD COLUMN n_blocked_balance INTEGER 


CREATE TABLE mdl_finance.tb_transaction_status
(
	n_transaction_status_id SERIAL NOT NULL PRIMARY KEY,
	s_trans_status_vn_name VARCHAR(150),
	s_trans_status_en_name VARCHAR(150),
	s_trans_status_cn_name VARCHAR(150)
);


CREATE TABLE mdl_finance.tb_transactions
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_transaction_id VARCHAR(150) NOT NULL UNIQUE,
	s_from_user_id VARCHAR(150),
	s_to_user_id VARCHAR(150),
	n_amount INTEGER,
	s_currency_id VARCHAR(50) NOT NULL,
	s_pocket_id VARCHAR(150),
	s_transaction_type_id VARCHAR(50),
	d_transaction_date TIMESTAMP,
	n_transaction_status_id INTEGER,
	s_trans_message TEXT,
	n_otp_auth INTEGER,
	n_otp_confirmed INTEGER,
	n_otp_confirmed_times INTEGER,
	d_trans_confirm_date TIMESTAMP,
	s_channel_id VARCHAR(15),
	s_e_contract_no VARCHAR(150),
	n_cashback_amt INTEGER,
	d_cashback_validate_date TIMESTAMP,
	s_cashback_validate_by VARCHAR(150),
	d_cashback_approved_date TIMESTAMP,
	s_cashback_approved_by VARCHAR(150)
);

CREATE TABLE mdl_finance.tb_transaction_type
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_transaction_type_id VARCHAR(50) UNIQUE NOT null,
	s_transaction_type_vn_name VARCHAR(150),
	s_transaction_type_en_name VARCHAR(150),
	s_transaction_type_cn_name VARCHAR(150)
);

CREATE TABLE mdl_finance.tb_transaction_fee
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_trans_fee_id TEXT UNIQUE NOT NULL,
	n_fee NUMERIC(13,3),
	d_from_date TIMESTAMP,
	d_to_date TIMESTAMP,
	s_transaction_type_id TEXT
);

CREATE TABLE mdl_finance.tb_fee_setting
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_transaction_name VARCHAR(100) NOT NULL,
	s_currency_id VARCHAR(15) NOT NULL,
	n_amount_from NUMERIC(12,2),
	n_amount_to NUMERIC(12,2),
	n_fee_charge NUMERIC(12,2),
	n_cashback NUMERIC(12,2)
);