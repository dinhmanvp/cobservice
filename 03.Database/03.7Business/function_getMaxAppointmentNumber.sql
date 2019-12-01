
/*
Lay Max cua cot appoitmentnumber
select *from mdl_business.getmaxappointmentnumber('111','111','2019-10-20')
*/
CREATE OR REPLACE FUNCTION mdl_business.getmaxappointmentnumber
(
	Partnerid VARCHAR(50),
	BuserviceId VARCHAR(50),
	appointDate DATE 
)
RETURNS Integer AS 
$$
	SELECT MAX(t.n_appointment_number) AS MaxNumber FROM mdl_business.tb_order t
	WHERE s_seller_partner_id = Partnerid AND t.s_business_service_id = BuserviceId
	AND t.d_appointment_date = appointDate
$$
LANGUAGE sql;