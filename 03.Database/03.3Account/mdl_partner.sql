
CREATE TABLE mdl_partner.tb_listpartners
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_name_en TEXT,
	s_partner_name_vn TEXT ,
	s_partner_name_cn TEXT,
	s_business_phone VARCHAR(50),
	s_business_email VARCHAR(50),
	s_address_no TEXT,
	s_street TEXT,
	s_company_logo TEXT,
	s_taxcode VARCHAR(50),
	s_owner_name VARCHAR(50),
	d_registration_date TIMESTAMP,
	s_opening_time VARCHAR(50),
	s_close_time VARCHAR(50),
	s_country_id VARCHAR(50),
	s_city_id VARCHAR(50),
	s_group_business_id VARCHAR(50),
	s_business_type_id VARCHAR(50),
	s_created_by VARCHAR(250),
	s_partner_desc_vn TEXT,
	s_partner_desc_en TEXT,
	s_partner_desc_cn TEXT,
	n_partner_rating INTEGER,
	n_is_approved INTEGER,
	s_comment TEXT,
	s_partner_rule_id VARCHAR(150),
	s_user_id VARCHAR(150)
);



CREATE TABLE mdl_partner.tb_partner_bizcate
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_bizcate_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_id VARCHAR(50),
	s_group_business_cate_id VARCHAR(50),
	n_is_activated INTEGER
);

CREATE TABLE mdl_partner.tb_partner_business_services
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_business_service_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_id VARCHAR(50),
	s_business_service_id VARCHAR(50),
	n_is_activated INTEGER
);


CREATE TABLE mdl_partner.tb_partner_account
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_account_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(50),
	n_is_approved INTEGER,
	s_comment TEXT,
	n_is_deleted INTEGER,
	s_staff_number TEXT,
	d_joined_date DATE,
	s_partner_rule_id VARCHAR(50)
);

CREATE TABLE mdl_partner.tb_partner_rules
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_rule_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_rule_name_en TEXT,
	s_rule_name_vn TEXT,
	s_rule_name_cn TEXT,
	n_is_delete INTEGER,
	n_is_addnew INTEGER
);

CREATE TABLE mdl_partner.tb_partner_workingtime
( 
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_working_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_id VARCHAR(50),
	n_duration_for_session INTEGER,
	n_count_per_day INTEGER,
	s_mo_from TEXT,
	s_mo_to TEXT,
	s_mo_is_off TEXT,
	s_tu_from TEXT,
	s_tu_to TEXT,
	s_tu_is_off TEXT,
	s_we_from TEXT,
	s_we_to TEXT,
	s_we_is_off TEXT,
	s_th_from TEXT,
	s_th_to TEXT,
	s_th_is_off TEXT,
	s_fr_from TEXT,
	s_fr_to TEXT,
	s_fr_is_off TEXT,
	s_sa_from TEXT,
	s_sa_to TEXT,
	s_sa_is_off TEXT,
	s_su_from TEXT,
	s_su_to TEXT,
	s_su_is_off TEXT
);

CREATE TABLE mdl_partner.tb_partner_contract
(
	n_id SERIAL NOT NULL UNIQUE,
	s_contract_number VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_id VARCHAR(50),
	s_saleman_id VARCHAR(50),
	s_contract_policy_type_id VARCHAR(50),
	d_from_date DATE,
	d_to_date DATE,
	n_contract_duration INTEGER,
	s_contract_unit TEXT,
	n_total INTEGER,
	s_partner_signature TEXT,
	n_percent_charge_per_txn INTEGER,
	s_business_service_id VARCHAR(50)
);

CREATE TABLE mdl_partner.tb_mybusiness_workingtime
(
	n_id SERIAL NOT NULL UNIQUE,
	s_my_business_setting_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_my_business_id VARCHAR(50),
	s_partner_working_id VARCHAR(50)
);

CREATE TABLE mdl_partner.tb_partner_holiday
(
	n_id SERIAL NOT NULL UNIQUE,
	s_partner_holiday_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_partner_id VARCHAR(50),
	d_holiday_date DATE,
	s_holiday_desc TEXT
);








