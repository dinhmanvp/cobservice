---tạo chema
CREATE SCHEMA [Tên schema]
    AUTHORIZATION dbcob;

---tạo table
CREATE TABLE tb_dashboard(
	n_id SERIAL NOT NULL,
	s_dashboard_id CHARACTER VARYING(15) NOT NULL UNIQUE,
	s_item_name_en CHARACTER VARYING(150),
	s_item_name_vn CHARACTER VARYING(150),
	s_item_name_cn CHARACTER VARYING(150),
	n_is_available INT,
	i_item_image TEXT,
	n_order INT,
	PRIMARY KEY (n_id)
);

CREATE TABLE tb_group_business(
	n_id SERIAL NOT NULL,
	s_group_business_id CHARACTER VARYING(15) NOT NULL UNIQUE,
	s_dashboard_id CHARACTER VARYING(15) NOT NULL,
	s_group_business_name_en CHARACTER VARYING(150),
	s_group_business_name_vn CHARACTER VARYING(150),
	s_group_business_name_cn CHARACTER VARYING(150),
	i_group_business_icon TEXT,
	s_group_business_desc_shrink_en TEXT,
	s_group_business_desc_shrink_vn TEXT,
	s_group_business_desc_shrink_cn TEXT,
	s_group_business_desc_en TEXT,
	s_group_business_desc_vn TEXT,
	s_group_business_desc_cn TEXT,
	n_group_business_rating NUMERIC(6,2),
	n_group_business_availble INT,
	n_order INT,
	PRIMARY KEY (n_id)	
);

CREATE TABLE tb_business_services(
	n_id SERIAL NOT NULL,
	s_business_service_id CHARACTER VARYING(15) NOT NULL,
	s_group_business_id CHARACTER VARYING(15) NOT NULL,
	s_business_service_name_en CHARACTER VARYING(150),
	s_business_service_name_vn CHARACTER VARYING(150),
	s_business_service_name_cn CHARACTER VARYING(150),
	i_business_service_icon TEXT,
	s_business_service_desc_shrink_en TEXT,
	s_business_service_desc_shrink_vn TEXT,
	s_business_service_desc_shrink_cn TEXT,
	s_business_service_desc_en TEXT,
	s_business_service_desc_vn TEXT,
	s_business_service_desc_cn TEXT,
	n_business_service_rating NUMERIC(6,2),
	n_business_service_available INT,
	n_order INT,
	PRIMARY KEY (n_id)
);

CREATE TABLE mdl_core.tb_business_type
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_business_type_id VARCHAR(50) NOT NULL UNIQUE,
	s_business_type_name_en VARCHAR(3000),
	s_business_type_name_vn VARCHAR(3000),
	s_business_type_name_cn VARCHAR(3000),
	s_business_type_desc_en VARCHAR(5000),
	s_business_type_desc_vn VARCHAR(5000),
	s_business_type_desc_cn VARCHAR(5000)
);

CREATE TABLE mdl_core.tb_City
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_city_id VARCHAR(50) NOT NULL UNIQUE,
	s_city_name_en TEXT,
	s_city_name_vn TEXT,
	s_city_name_cn TEXT,
	i_city_map_latitude NUMERIC(23,7),
	i_city_map_longitude NUMERIC(23,7),
	s_country_id VARCHAR(5)
);


CREATE TABLE mdl_core.tb_country
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_country_id VARCHAR(50) NOT NULL UNIQUE,
	s_country_name_en VARCHAR(2000),
	s_country_name_vn VARCHAR(2000),
	s_country_name_cn VARCHAR(2000),
	b_country_flag NUMBER(23,7)
);

CREATE TABLE mdl_core.tb_cob_staff
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_cob_staff_id VARCHAR(200) NOT NULL UNIQUE,
	s_name VARCHAR(200) 
);

CREATE table mdl_core.tb_channels
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_channel_id VARCHAR(50) NOT NULL UNIQUE,
	s_channel_name_en VARCHAR(50),
	s_channel_name_vn VARCHAR(50),
	s_channel_name_cn VARCHAR(50)
);

ALTER TABLE mdl_core.tb_contract_policy_type_detail 
ALTER COLUMN s_contract_policy_type_detail_id TYPE VARCHAR(15);

ALTER TABLE mdl_core.tb_contract_policy_type 
ALTER COLUMN s_contract_policy_type_id TYPE VARCHAR(15);

ALTER TABLE mdl_core.tb_contract_policy_type 
ALTER COLUMN m_to_amount TYPE INTEGER;

ALTER TABLE mdl_core.tb_contract_policy_type 
ALTER COLUMN m_from_amount TYPE INTEGER;

ALTER TABLE mdl_core.tb_contract_policy_type_detail 
ALTER COLUMN m_price TYPE INTEGER;

ALTER TABLE mdl_business.tb_mydeal
ADD COLUMN s_group_business_service_id VARCHAR(15) NOT NULL

ALTER TABLE mdl_cob.tb_advise_services
ALTER COLUMN n_advise_service_rating SET DATA TYPE NUMERIC(4,3);


CREATE TABLE mdl_core.tb_company_type
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_company_type_id VARCHAR(50) NOT NULL UNIQUE,
	s_company_type_name_en TEXT,
	s_company_type_name_vn TEXT,
	s_company_type_name_cn TEXT
)
;

ALTER TABLE mdl_core.tb_business_type
ADD COLUMN n_order INTEGER;