/*
select * from mdl_partner.getpartnerholiday('111','201910')
*/

CREATE OR REPLACE FUNCTION mdl_partner.getpartnerholiday
(
	partnerid VARCHAR(50),
	monthOfYear VARCHAR(50)
)
RETURNS SETOF mdl_partner.tb_partner_holiday AS 
$$
	SELECT t FROM mdl_partner.tb_partner_holiday t 
	WHERE t.s_partner_id = partnerid
			AND CAST(concat(date_part('year', t.d_holiday_date),date_part('month', t.d_holiday_date)) AS VARCHAR(50)) = monthOfYear
$$
LANGUAGE sql;

