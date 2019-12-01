CREATE OR REPLACE FUNCTION mdl_account.checkusernamephonecustomer(username character varying,phone character VARYING, email character VARYING, cardId  character VARYING)
    RETURNS integer
    LANGUAGE 'sql'
    VOLATILE
    PARALLEL UNSAFE
    COST 100
AS $BODY$	
	
	select 
	(CASE 
				WHEN  upper(username) = (SELECT upper(us.s_username) FROM mdl_account.tb_user AS us WHERE upper(username) = upper(us.s_username)) THEN 1
				WHEN phone = (SELECT us.s_phone from mdl_account.tb_user AS us WHERE phone = us.s_phone) THEN 2
				WHEN email = (SELECT us.s_email from mdl_account.tb_user AS us WHERE email = us.s_email) THEN 3
				WHEN cardId = (SELECT cus.s_card_id_no from mdl_account.tb_customer AS cus WHERE cardId = cus.s_card_id_no) THEN 4
				ELSE 0
			END)
 $BODY$;