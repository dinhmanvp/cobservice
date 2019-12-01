/* 
User Login trả về bảng user
select * from mdl_account.accountlogin('MINHTEO','12345')
*/

CREATE OR REPLACE FUNCTION mdl_account.accountlogin
(
	Username VARCHAR(50),
	Password TEXT
)
RETURNS mdl_account.tb_user AS 
$$
	SELECT * FROM mdl_account.tb_user AS us WHERE upper(us.s_username) = upper(Username) AND us.b_password = PASSWORD AND us.is_validate = 1
$$
LANGUAGE sql;




 










