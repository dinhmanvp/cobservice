/*
Store Check Authentication
select * from mdl_account.checkauthentication('fsfsfsfa','manld')
select * from mdl_account.checkauthentication
*/


CREATE OR REPLACE FUNCTION mdl_account.checkauthentication
(
	Privatetoken TEXT,
	UserId VARCHAR(50)
)
RETURNS mdl_account.tb_user_authentication AS  
$$
SELECT au.* FROM mdl_account.tb_user_authentication AS au 
WHERE au.s_privatetoken = Privatetoken AND au.s_user_id = UserId
	
$$
LANGUAGE sql;









