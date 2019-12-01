ALTER TABLE mdl_account.tb_customer
(
	n_id SERIAL NOT null PRIMARY KEY,
	s_customer_id  VARCHAR(50) NOT NULL UNIQUE,
	s_firstname VARCHAR(2000),
	s_lastname VARCHAR(2000),
	d_birthday DATE,
	n_gender INTEGER,
	b_avatar TEXT,
	s_contact_address_no TEXT,
	s_contact_street TEXT,
	s_contact_zipcode TEXT,
	s_contact_country_id VARCHAR(50),
	s_contact_city_id VARCHAR(50),
	s_billing_address_no TEXT,
	s_billing_street TEXT,
	s_billing_zipcode TEXT,
	s_billing_country_id VARCHAR(50),
	s_billing_city_id VARCHAR(50),
	s_shipping_address_no TEXT,
	s_shipping_street TEXT,
	s_shipping_zipcode TEXT,
	s_shipping_country_id VARCHAR(50),
	s_shipping_city_id VARCHAR(50),
	s_referalId VARCHAR(50)
);



CREATE TABLE mdl_account.tb_my_business
(
	n_id SERIAL NOT NULL PRIMARY KEY, 
	s_my_business_id VARCHAR(50) NOT NULL UNIQUE,
	s_customer_id VARCHAR(50),
	s_partner_id VARCHAR(50),
	s_partner_bizcate_id VARCHAR(50),
	s_partner_business_service_id VARCHAR(50)
);

CREATE TABLE mdl_account.tb_user
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(50) NOT NULL UNIQUE,
	s_username VARCHAR(50),
	b_password TEXT,
	s_email VARCHAR(50),
	s_phone VARCHAR(50),
	d_lastlogin DATE,
	d_lastlogout DATE,
	s_customer_id VARCHAR(50),
	is_validate INTEGER
);

CREATE TABLE mdl_account.tb_user_authentication
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_privatetoken TEXT NOT NULL UNIQUE,
	d_created_date TIMESTAMP,
	b_is_exprited INTEGER DEFAULT 0 NOT null,
	bi_authen_index BIGINT,
	s_user_id VARCHAR(50)
);

CREATE TABLE mdl_account.tb_City
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

CREATE TABLE mdl_account.tb_country
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_country_id VARCHAR(50) NOT NULL UNIQUE,
	s_country_name_en VARCHAR(2000),
	s_country_name_vn VARCHAR(2000),
	s_country_name_cn VARCHAR(2000),
	b_country_flag BIT VARYING(50)
);

CREATE TABLE mdl_account.tb_business_type
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

CREATE TABLE mdl_account.tb_myfriends
(
	s_owner_user_id VARCHAR(150) NOT NULL,
	s_friend_user_id VARCHAR(150) NOT NULL,
	s_description TEXT,
	
	PRIMARY KEY (s_owner_user_id, s_friend_user_id)
);


/*Tạo View Account*/
CREATE VIEW mdl_account.cus_user AS
  SELECT cus.n_id AS nid, cus.s_customer_id, cus.s_firstname, cus.s_lastname, cus.d_birthday, cus.n_gender, cus.b_avatar, cus.s_address_number, cus.s_city_id, cus.s_country_id,
  us.s_user_id, us.s_username, us.b_password, us.s_email, us.s_phone, us.d_lastlogin, us.d_lastlogout
    
  FROM mdl_account.tb_customer cus, mdl_account.tb_user us
  WHERE cus.s_customer_id = us.s_user_id


SELECT * FROM mdl_account.cus_user


/*Tạo View NumberBusiness*/
CREATE VIEW mdl_account.number_my_business as
SELECT bu.s_business_service_id AS sbusnessserviceid, count(bu.s_business_service_id) AS number FROM 
mdl_account.tb_my_business AS bu GROUP BY bu.s_business_service_id

SELECT * FROM mdl_account.number_my_business










