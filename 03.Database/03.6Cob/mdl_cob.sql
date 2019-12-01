

CREATE TABLE mdl_cob.tb_advise_services
(
	n_id SERIAL NOT NULL UNIQUE,
	s_advise_service_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_advise_service_name_vn TEXT,
	s_advise_service_name_en TEXT,
	s_advise_service_name_cn TEXT,
	s_advise_service_shortdesc_vn TEXT,
	s_advise_service_shortdesc_en TEXT, 
	s_advise_service_shortdesc_cn TEXT,
	s_advise_service_icon TEXT,
	n_advise_service_rating INTEGER,
	n_advise_service_is_available INTEGER 
);

CREATE TABLE mdl_cob.tb_advise_service_detail
(
	n_id SERIAL NOT NULL UNIQUE,
	advise_service_detail_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_content TEXT,
	s_advise_service_id VARCHAR(50)
);

CREATE TABLE mdl_cob.tb_advise_service_pricing
(
	n_id SERIAL NOT NULL UNIQUE,
	s_advise_service_pricing_id VARCHAR(50) NOT NULL PRIMARY KEY,
	n_price INTEGER,
	n_discount_amount INTEGER,
	n_saleprice INTEGER,
	d_from_date DATE,
	d_to_date DATE,
	n_is_available INTEGER,
	s_advise_service_id VARCHAR(50),
	s_service_unit_id VARCHAR(50),
	n_qty INTEGER 
);


CREATE TABLE mdl_cob.tb_service_unit
(
	n_id SERIAL NOT NULL UNIQUE,
	s_service_unit_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_unit_name_en TEXT,
	s_unit_name_vn TEXT,
	s_unit_name_cn TEXT,
	s_duration_type TEXT

);

CREATE TABLE mdl_cob.tb_partner_registration_agent
(
	n_id SERIAL NOT NULL UNIQUE,
	s_agent_id VARCHAR(50) NOT NULL PRIMARY KEY,
	s_advise_service_id VARCHAR(50),
	s_partner_id VARCHAR(50),
	s_service_unit_id VARCHAR(50),
	n_price_bid INTEGER,
	n_cob_price INTEGER,
	n_approval_price INTEGER, 
	d_approval_date DATE,
	s_approval_by TEXT,
	n_is_stoped INTEGER,
	n_partner_confirmed INTEGER,
	s_partner_comment TEXT,
	s_cob_comment TEXT,
	d_valid_from DATE,
	d_valid_to DATE
);

CREATE TABLE mdl_cob.tb_startup_package
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_startup_package_id VARCHAR(150) UNIQUE NOT NULL,
	s_group_business_id VARCHAR(150) NOT NULL,
	s_package_name_en VARCHAR(250),
	s_package_name_vn VARCHAR(250),
	s_package_name_cn VARCHAR(250),
	s_package_icon TEXT,
	s_package_desc_en TEXT,
	s_package_desc_vn TEXT,
	s_package_desc_cn TEXT,
	n_price INTEGER,
	n_discount_price INTEGER,
	n_sale_pricce INTEGER,
	n_is_available INTEGER 
);

CREATE TABLE mdl_cob.tb_startup_package_items
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_startup_package_items_id VARCHAR(150) UNIQUE NOT NULL,
	s_advise_service_id VARCHAR(150) NOT NULL,
	s_startup_package_id VARCHAR(150) NOT NULL,
	s_service_unit_id varchar(150) NOT NULL,
	n_duration INTEGER,
	s_duration_type VARCHAR(150),
	d_from_date TIMESTAMP,
	d_to_date TIMESTAMP
);

CREATE TABLE mdl_cob.tb_startup_package
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_startup_package_id VARCHAR(150) UNIQUE NOT NULL,
	s_group_business_id VARCHAR(150) NOT NULL,
	s_package_name_en VARCHAR(250),
	s_package_name_vn VARCHAR(250),
	s_package_name_cn VARCHAR(250),
	s_package_icon TEXT,
	s_package_desc_en TEXT,
	s_package_desc_vn TEXT,
	s_package_desc_cn TEXT,
	n_price INTEGER,
	n_discount_price INTEGER,
	n_sale_pricce INTEGER,
	n_is_available INTEGER 
);

CREATE TABLE mdl_cob.tb_startup_package_items
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_startup_package_items_id VARCHAR(150) UNIQUE NOT NULL,
	s_advise_service_id VARCHAR(150) NOT NULL,
	s_startup_package_id VARCHAR(150) NOT NULL,
	s_service_unit_id varchar(150) NOT NULL,
	n_duration INTEGER,
	s_duration_type VARCHAR(150),
	d_from_date TIMESTAMP,
	d_to_date TIMESTAMP
);

CREATE TABLE mdl_cob.tb_currency_exchange
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_from_currency_id VARCHAR(50) NOT NULL,
	s_to_currency_id VARCHAR(50) DEFAULT 'VND',
	n_exchange_rate INTEGER NOT NULL,
	d_updated_date TIMESTAMP DEFAULT NOW(),
	n_is_available INTEGER DEFAULT 1
);

CREATE TABLE mdl_cob.tb_duration_type(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_duration_type_id VARCHAR(150) UNIQUE NOT NULL,
	s_duration_type_name_vn TEXT,
	s_duration_type_name_cn TEXT,
	s_duration_type_name_en TEXT
);



INSERT INTO mdl_cob.tb_currency_exchange(s_from_currency_id, n_exchange_rate)
VALUES('COB',1000),('USD',23000),('SGD',15000);
