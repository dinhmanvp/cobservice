/*Kiểm tra username và phone đã tồn tại chưa, trước khi tạo mới customer và user 
kết quả trả về 1 là username tồn tại, 2 là phone tồn tại,
0 là là kết tồn tại username và phone.

select * from mdl_account.checkusernamephonecustomer ('Minhteo', '09999999990')
*/
CREATE OR REPLACE FUNCTION mdl_account.checkusernamephonecustomer
(
	username VARCHAR(50),
	phone VARCHAR(50)
)
RETURNS INTEGER AS getuserbyusername
$$
	select 
	(CASE 
				WHEN  upper(username) = (SELECT upper(us.s_username) FROM mdl_account.tb_user AS us WHERE upper(username) = upper(us.s_username)) THEN 1
				WHEN phone = (SELECT us.s_phone from mdl_account.tb_user AS us WHERE phone = us.s_phone) THEN 2
				ELSE 0
			END)
 $$
LANGUAGE sql;