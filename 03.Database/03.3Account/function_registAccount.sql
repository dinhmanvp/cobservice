/* Tạo Customer và User*/
CREATE OR REPLACE FUNCTION mdl_account.registerCustomer
(
	customerID VARCHAR(50),
	firstname VARCHAR(2000),
	lastname VARCHAR(2000),
	birthday DATE,
	gender INTEGER,
	cityID VARCHAR(50),
	countryID VARCHAR(50),
	userID VARCHAR(50),
	username VARCHAR(50),
	password TEXT,
	email VARCHAR(50),
	phone VARCHAR(50)
)
RETURNS VOID AS 
'
 INSERT INTO mdl_account.tb_customer(s_customer_id, s_firstname,s_lastname,d_birthday, n_gender, s_contact_city_id, s_contact_country_id)
		VALUES (customerID,firstname, lastname, birthday, gender, cityID, countryID);
	INSERT INTO mdl_account.tb_user(s_user_id, s_username, b_password, s_email, s_phone, s_customer_id)
		VALUES (userID, upper(username), password, email, phone, customerID);
'
LANGUAGE sql;