CREATE SCHEMA mdl_gateway

CREATE TABLE mdl_gateway.tb_ma_user_authentication
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(150) NOT NULL, -- thay doi do rong --- LƯU USERNAME THAY VI USER ID
	s_public_token TEXT NOT NULL,
	s_private_token TEXT NOT NULL,
	d_created_date TIMESTAMP NOT NULL,
	bi_is_expired INTEGER DEFAULT 0,
	s_channel_id VARCHAR(50) --bo sung
);

CREATE TABLE mdl_gateway.tb_user_access_log
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(150) NOT NULL, --thay doi do rong --- LƯU USERNAME THAY VI USER ID
	s_channel VARCHAR(50) NOT NULL,
	s_function_id VARCHAR(150),-- thay doi do rong
	d_access_date TIMESTAMP,
	--d_access_time TIME,
	s_result VARCHAR(50),
	s_ip_access VARCHAR(50)
);

CREATE TABLE mdl_gateway.tb_rank_ip_countries
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	ip_index BIGINT NOT NULL,
	s_country_ip_blocks VARCHAR(1000),
	b_is_allowed INTEGER DEFAULT 0,
	s_country_id VARCHAR(50) 
);

CREATE TABLE mdl_gateway.tb_user_locked
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(150) NOT NULL, -- thay doi do rong
	s_channel_id VARCHAR(50) NOT NULL,
	b_is_locked INTEGER DEFAULT 0,
	d_locked_date DATE,
	d_locked_time TIME,
	s_reason TEXT,
	d_unlocked_date DATE,
	d_unlocked_time TIME,
	s_cob_staff_id VARCHAR(200)
);

CREATE TABLE mdl_gateway.channel --bo sung
(
	s_channel_id VARCHAR(50) PRIMARY KEY NOT NULL,
	n_time_out INTEGER
);

CREATE TABLE mdl_gateway.tb_api_permission
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_api VARCHAR(250) NOT NULL UNIQUE,
	n_permission_require INTEGER  DEFAULT 0
);