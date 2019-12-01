

/*
select * from mdl_business.deletebymydealid('1571218405032')
*/

CREATE OR REPLACE FUNCTION mdl_business.deletebymydealid
(
	mydealId VARCHAR(50)
)
RETURNS Integer AS 
'
	delete FROM mdl_business.tb_mydeal_country t WHERE t.s_mydeal_id = mydealId;
	select 1;
'
LANGUAGE sql;