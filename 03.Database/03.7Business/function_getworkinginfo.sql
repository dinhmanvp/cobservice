
/*
select * from mdl_business.getWorkingInfo('111','2019-10-01','2019-10-31','111')
*/

CREATE OR REPLACE FUNCTION mdl_business.getWorkingInfo
(
	partnerid VARCHAR(50),
	DateStart DATE,
	DateEnd DATE,
	BusinessServiceId VARCHAR(50)
)
RETURNS TABLE
(
	s_partner_id VARCHAR(50),
	d_appointment_date DATE,
	n_appointment_number INTEGER,
	n_count_per_day INTEGER,
	is_Holiday INTEGER,
	s_business_service_id VARCHAR(50),
	s_is_fullday VARCHAR(10),
	is_holiday_desc TEXT,
	s_is_off_day TEXT
)

AS 
$$
		SELECT 
		partnerid,
	  tem.d_appointment_date, 
	  t.n_appointment_number ,
	  0,
	  0,
	  BusinessServiceId,
	  'false',
	  '',
	  ''
	FROM 
	  (
		    select 
		      d_appointment_date :: date 
		    from 
		      generate_series(
		        DateStart, DateEnd, '1 day' :: interval
		      ) d_appointment_date
	  ) tem 
	  LEFT JOIN (
		    SELECT 
		      t.s_seller_partner_id, 
		      t.d_appointment_date, 
		      Max(t.n_appointment_number) n_appointment_number
		    FROM 
		      mdl_business.tb_order t
			WHERE t.s_seller_partner_id = partnerid AND t.s_business_service_id = BusinessServiceId
		    GROUP BY 
		      t.s_seller_partner_id, 
		      t.d_appointment_date
	  ) t ON tem.d_appointment_date = t.d_appointment_date
$$
LANGUAGE sql;