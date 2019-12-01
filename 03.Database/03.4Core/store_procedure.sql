-- 1
CREATE OR REPLACE FUNCTION mdl_core.getdashboardbytype(is_available INTEGER) RETURNS SETOF mdl_core.tb_dashboard  AS 
$$
	SELECT  * 
	FROM mdl_core.tb_dashboard
	WHERE 
	  CASE 
	  		WHEN  is_available = 0 THEN mdl_core.tb_dashboard.n_is_available = is_available
		  	WHEN is_available = 1 THEN mdl_core.tb_dashboard.n_is_available = is_available
			WHEN is_available = 2 THEN mdl_core.tb_dashboard.n_is_available = 1 OR mdl_core.tb_dashboard.n_is_available = 0	 
				
			END
			ORDER BY n_order asc
$$
LANGUAGE sql;



-- 2
CREATE OR REPLACE FUNCTION mdl_core.getgroupbusinessbydashboardid(dashboard_id CHARACTER VARYING(15)) RETURNS SETOF mdl_core.tb_group_business AS 
$$
		SELECT  *
		FROM mdl_core.tb_group_business 
		WHERE mdl_core.tb_group_business.s_dashboard_id = dashboard_id
		AND mdl_core.tb_group_business.n_group_business_availble = 1
		ORDER BY n_order asc
$$
LANGUAGE sql;


--3
CREATE OR REPLACE FUNCTION mdl_core.getbusinessservicebygroupbusiness(groupbusiness_id CHARACTER VARYING(15)) RETURNS SETOF mdl_core.tb_business_services AS 
$$
		SELECT  *
		FROM mdl_core.tb_business_services WHERE mdl_core.tb_business_services.s_group_business_id = groupbusiness_id
		ORDER BY n_order asc
$$
LANGUAGE sql;

--4 select * from mdl_core.getroupbusinessishome()
CREATE OR REPLACE FUNCTION mdl_core.getroupbusinessishome() RETURNS SETOF mdl_core.tb_group_business AS 
$$
		SELECT  gb.*
		FROM mdl_core.tb_group_business gb
		WHERE gb.n_home_display = 1
		ORDER BY gb.n_order asc
$$
LANGUAGE sql;



-- Example 1

-- CREATE OR REPLACE FUNCTION mdl_core.tt(IN n_id INTEGER, out n_is_available int) RETURNS INTEGER AS
-- $$
-- SELECT mdl_core.tb_dashboard.n_is_available from mdl_core.tb_dashboard WHERE mdl_core.tb_dashboard.n_id = n_is_available
-- $$
-- LANGUAGE sql

-- Example 2


-- CREATE FUNCTION sales_func(employee_id int, vehicle_id int)
-- RETURNS SETOF sales
-- AS $$
-- DECLARE
-- car_model text;
-- car_price int;
-- sales_bonus int;
-- bonus int;
-- BEGIN
-- EXECUTE 'SELECT model, sales_bonus, price FROM cars WHERE car_id = $1'
-- INTO car_model, sales_bonus, car_price
-- USING vehicle_id;
-- 
-- bonus := sales_bonus::real/100 * car_price;
-- 
-- INSERT INTO sales (staff_id, car_id, staff_bonus, sales_price) VALUES
-- (employee_id, vehicle_id, bonus, car_price);
-- RETURN QUERY SELECT * FROM sales ORDER BY created_at;
-- 
-- END;
-- $$
-- LANGUAGE plpgsql;

--Example 3 -- chua chay duoc
-- CREATE OR REPLACE FUNCTION mdl_core.test(n INT)
-- RETURNS refcursor AS
-- $$
-- DECLARE
-- results refcursor;           -- Declare cursor variables
-- BEGIN
-- OPEN results FOR SELECT *
-- FROM tb_dashboard WHERE tb_dashboard.n_is_available = n;
-- RETURN results
-- END;
-- $$
--   LANGUAGE plpgsql;
