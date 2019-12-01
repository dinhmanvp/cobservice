/* Update Customer và User ph*/

CREATE OR REPLACE FUNCTION mdl_account.UpdateCustomer
(
	customerID VARCHAR(50),
	firstname VARCHAR(2000),
	lastname VARCHAR(2000),
	birthday DATE,
	gender INTEGER,
	avatar TEXT,
	address TEXT,
	cityID VARCHAR(50),
	countryID VARCHAR(50),
	userID VARCHAR(150),
	username VARCHAR(50),
	password TEXT,
	email VARCHAR(50),
	phone VARCHAR(50),
	lastLogin DATE,
	lastLogout DATE 
)
RETURNS VOID AS 
'
 UPDATE mdl_account.tb_customer 
 SET 
 	s_firstname = firstname,
 	s_lastname = lastname,
 	d_birthday = birthday,
 	n_gender = gender,
 	b_avatar = avatar,
 	s_address_number = address,
 	s_contact_city_id = cityID,
 	s_contact_country_id = countryID
 WHERE s_customer_id = customerID;
 	
 UPDATE mdl_account.tb_user AS us
 SET 
 	s_username = username,
 	b_password = password,
 	s_email = email,
 	s_phone = phone,
 	d_lastlogin = lastLogin,
 	d_lastlogout = lastLogout
 WHERE s_user_id = userID;
'
LANGUAGE sql;