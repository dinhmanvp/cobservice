/*
auth: Van Sinh
date: 25/9/2019
*/






/*
Lấy mã CustomerId để tạo Account mới
select * from mdl_account.getendaccountid()
*/
CREATE OR REPLACE FUNCTION mdl_account.getendaccountid()
RETURNS Integer AS
$$
	SELECT MAX(n_id) as endcusid FROM mdl_account.tb_customer 
$$
LANGUAGE sql;

/* Kiểm tra store getcustomerid
SELECT * FROM mdl_account.getendcusid()
*/




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
	countryID VARCHAR(50),tb_user
	userID VARCHAR(50),
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
 	s_city_id = cityID,
 	s_country_id = countryID
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


/*
Acccount thay đổi password
select * from mdl_account.accountchangepassword('hanvansinh','12345', '123456')
1: username không tồn tại
2: mật khẩu cũ không đúng.
3: mật khẩu mới trùng với mật khẩu củ
0: tiến hành đổi mật khẩu
*/

CREATE OR REPLACE FUNCTION mdl_account.accountchangepassword
(
	Username VARCHAR(50),
	PasswordOld TEXT,
	Passwordnew TEXT
)
RETURNS Integer AS 
$$
	SELECT 
	(
		Case
		    WHEN 1 > (SELECT COUNT(us.s_username) FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username)) THEN 1 
			 WHEN PasswordOld <> (SELECT us.b_password FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username)) THEN 2
			 WHEN Passwordnew = (SELECT us.b_password FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username)) THEN 3
			 ELSE 0 
		END 
	) 
$$
LANGUAGE sql;

/*
 khi store mdl_account.accountchangepassword trả về 0 thì tiến hành update password
 select * from mdl_account.accountupdatepassword('hanvansinh','12346')
*/

CREATE OR REPLACE FUNCTION mdl_account.accountupdatepassword
(
	Username VARCHAR(50),
	Passwordnew TEXT
)
RETURNS VOID AS 
$$
	UPDATE mdl_account.tb_user us
	SET b_password = Passwordnew
	WHERE upper(s_username) = upper(Username)
$$
LANGUAGE sql;

/*
Lấy User thông qua username
SELECT * FROM mdl_account.getuserbyusername('minhteo')
*/
CREATE OR REPLACE FUNCTION mdl_account.getuserbyusername
(
	Username VARCHAR(50)
)
RETURNS mdl_account.tb_user AS 
$$
	SELECT us FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username)
$$
LANGUAGE sql;

/*
Lấy Customer thông qua username
SELECT * FROM mdl_account.getcustomerbyusername('hanvansinh')
*/
CREATE OR REPLACE FUNCTION mdl_account.getcustomerbyusername
(
	Username VARCHAR(50)
)
RETURNS mdl_account.tb_customer AS 
$$
	SELECT * FROM mdl_account.tb_customer AS cus WHERE cus.s_customer_id =  
	(SELECT us.s_user_id FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username))
$$
LANGUAGE sql;


/*
Lấy My Business dựa vào s_my_business_id 
select * from mdl_account.getmybusiness('0000000004123')
*/
CREATE OR REPLACE FUNCTION mdl_account.getmybusiness
(
	myBusnessId VARCHAR(50)
)
RETURNS mdl_account.tb_my_business AS 
$$
	SELECT * FROM mdl_account.tb_my_business AS mybu WHERE mybu.s_my_business_id = myBusnessId
$$
LANGUAGE sql;


/*
Xử lý cho api getNumberMyBusiness
select * from mdl_account.getnumbermybusiness()
*/
CREATE OR REPLACE FUNCTION mdl_account.getnumbermybusiness()
RETURNS SETOF mdl_account.number_my_business AS 
$$
	SELECT * FROM mdl_account.number_my_business
$$
LANGUAGE sql;



