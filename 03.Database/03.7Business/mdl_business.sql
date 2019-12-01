/*
CREATE SCHEMA mdl_business
    AUTHORIZATION dbcob;
*/

CREATE TABLE mdl_business.tb_product_cate
(
	n_id SERIAL  NOT NULL PRIMARY KEY,
	s_product_cate_id VARCHAR(50) NOT NULL UNIQUE,
	s_product_cate_icon TEXT,
	s_product_cate_name_vn TEXT,
	s_product_cate_name_en TEXT,
	s_product_cate_name_cn TEXT,
	s_partner_id VARCHAR(50)
);

CREATE TABLE mdl_business.tb_myproduct
(
	n_id SERIAL  NOT NULL PRIMARY KEY,
	s_product_id VARCHAR(50) NOT NULL UNIQUE,
	s_partner_id VARCHAR(50),
	s_user_id VARCHAR(150),
	s_product_cate_id VARCHAR(50),
	s_product_header_vn TEXT,
	s_product_header_en TEXT,
	s_product_header_cn TEXT,
	s_product_desc_en TEXT,
	s_product_desc_cn TEXT,
	s_product_desc_vn TEXT,
	n_price INTEGER,
	n_discount INTEGER,
	n_saleprice INTEGER,
	s_product_image_main TEXT,
	s_product_image_2 TEXT,
	s_product_image_3 TEXT,
	s_product_image_4 TEXT,
	s_currency TEXT,
	n_rating INTEGER
);

CREATE TABLE mdl_business.tb_mydeal
(
	n_id SERIAL  NOT NULL PRIMARY KEY,
	s_mydeal_id  VARCHAR(50) NOT NULL UNIQUE,
	s_partner_id VARCHAR(50),
	s_user_id VARCHAR(150),
	n_is_buyer INTEGER,
	s_deal_header TEXT,
	s_deal_content TEXT,
	s_deal_image1 TEXT,
	s_deal_image2 TEXT,
	s_deal_image3 TEXT,
	n_price INTEGER,
	d_deal_create_date DATE,
	d_deal_vlid_from DATE,
	d_deal_valid_to DATE,
	n_is_buyer_posting INTEGER 
);

CREATE TABLE mdl_business.tb_mydeal_country
(
	n_id SERIAL  NOT NULL PRIMARY KEY,
	s_mydeal_country_id VARCHAR(50) NOT NULL UNIQUE,
	s_mydeal_id VARCHAR(50),
	s_country_id VARCHAR(50)
);

CREATE TABLE mdl_business.tb_order
(
	n_id SERIAL NOT NULL UNIQUE,
	s_order_number VARCHAR(150) not null PRIMARY KEY ,
	d_appointment_date DATE,
	d_order_date DATE,
	s_buyer_partner_id VARCHAR(50),
	s_group_business_cate_id VARCHAR(50),
	s_business_service_id VARCHAR(50),
	s_buyer_user_id VARCHAR(150),
	n_order_status_id INTEGER,
	n_appointment_number INTEGER,
	s_appointment_time TEXT,
	s_patient_name TEXT, 
	s_patient_age TEXT,
	s_patient_gender TEXT,
	n_bhyt INTEGER,
	n_is_buyer_partner_confirmed INTEGER,
	s_buyer_partner_confirmed_by TEXT,
	n_is_done INTEGER,
	n_is_paid INTEGER,
	s_seller_user_id VARCHAR(150),
	s_seller_partner_id VARCHAR(50),
	n_is_seller_partner_confirmed INTEGER,
	s_seller_partner_confirmed_by TEXT,
	n_total_amount INTEGER,
	s_currency TEXT,
	n_buyer_raking INTEGER,
	s_oder_summary TEXT 

)

CREATE TABLE mdl_business.tb_mydeal_like
(
	n_id SERIAL NOT NULL UNIQUE,
	s_mydeal_like_id TEXT not null ,
	s_user_id VARCHAR(150) not null,
	d_like_date DATE,
	PRIMARY KEY (s_mydeal_like_id,s_user_id)
)

CREATE TABLE mdl_business.tb_mydeal_comments
(
	n_id SERIAL NOT NULL PRIMARY KEY NOT NULL,
	s_mydeal_comment_id VARCHAR(50) UNIQUE NOT NULL,
	s_comments TEXT,
	s_user_id VARCHAR(150),
	d_commented_date date DEFAULT NOW()
);
ALTER TABLE mdl_business.tb_mydeal_comments ADD s_mydeal_id TEXT;

CREATE TABLE mdl_business.tb_order_detail
(
	n_id SERIAL  NOT NULL PRIMARY KEY,
	s_order_detail_id VARCHAR(150) NOT NULL UNIQUE,
	s_order_number VARCHAR(150),
	n_is_product INTEGER,
	s_mydeal_id VARCHAR(150),
	s_product_id VARCHAR(150),
	n_subprice NUMERIC(1000,3),
	n_qty NUMERIC(1000,3),
	n_total NUMERIC(1000,3)
);

CREATE TABLE mdl_business.tb_mydeal_share
(
	n_id SERIAL NOT NULL UNIQUE,
	s_mydeal_id TEXT not null ,
	s_user_id VARCHAR(150) not null,
	d_like_date DATE,
	PRIMARY KEY (s_mydeal_id,s_user_id)
)
;

CREATE TABLE mdl_business.tb_order_conversation
(
	n_id SERIAL NOT NULL UNIQUE,
	s_order_conversation_id VARCHAR(150) NOT NULL,
	s_order_number VARCHAR(150) not null,
	s_user_id VARCHAR(150) not null,
	d_like_date DATE,
	PRIMARY KEY (s_order_conversation_id)
)
;

CREATE TABLE mdl_business.tb_esmart_contract
(
	n_id SERIAL NOT NULL UNIQUE,
	s_econtract_no VARCHAR(150) NOT NULL,
	s_order_number VARCHAR(150),
	s_buyer_partner_id VARCHAR(50),
	s_buyer_user_id VARCHAR(150),
	s_seller_partner_id VARCHAR(50),
	s_seller_user_id VARCHAR(150),
	n_amount NUMERIC(1000,3),
	n_taxrate NUMERIC(1000,3),
	n_taxamount NUMERIC(1000,3),
	d_create_date DATE,
	s_create_by VARCHAR(150),
	n_contract_status_id INTEGER,
	PRIMARY KEY (s_econtract_no)
)
;
CREATE TABLE mdl_business.tb_contract_status
(
	n_contract_status_id INTEGER NOT NULL,
	s_contract_status_name_vn VARCHAR(150),
	s_contract_status_name_en VARCHAR(150),
	s_contract_status_name_cn VARCHAR(150),
	PRIMARY KEY (n_contract_status_id)
)
;