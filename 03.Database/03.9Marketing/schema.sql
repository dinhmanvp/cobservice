CREATE SCHEMA mdl_marketing

CREATE TABLE mdl_marketing.tb_advertise
(
	n_id SERIAL NOT NULL PRIMARY KEY,
	s_user_id VARCHAR(50),
	s_parter_id VARCHAR(50),
	s_image TEXT,
	n_link_type INTEGER,
	s_url TEXT,
	n_display_as INTEGER,
	n_order INTEGER,
	d_start_date TIMESTAMP,
	d_end_date TIMESTAMP 
)

ALTER TABLE mdl_marketing.tb_advertise ADD COLUMN 	d_start_date TIMESTAMP
ALTER TABLE mdl_marketing.tb_advertise ADD COLUMN 	d_end_date TIMESTAMP


