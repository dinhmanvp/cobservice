DELIMITER //
CREATE OR REPLACE FUNCTION mdl_cob.exchangetocobcurrency(fromcurrencyId VARCHAR(50),  saleprice NUMERIC)
RETURNS NUMERIC(13,3)  AS 
$$
	DECLARE
		rateToVnd NUMERIC := (SELECT t.n_exchange_rate 
										FROM mdl_cob.tb_currency_exchange t 
										WHERE t.s_from_currency_id = fromcurrencyId
										AND t.n_is_available = 1
										);
		rateVndToCob NUMERIC := (SELECT t.n_exchange_rate 
										FROM mdl_cob.tb_currency_exchange t 
										WHERE t.s_from_currency_id = 'COB'
										AND t.n_is_available = 1
										);
										
	BEGIN 
		IF fromcurrencyId = 'VND' 
			THEN RETURN saleprice/rateVndToCob;
		ELSEIF fromcurrencyId = 'COB'
			THEN RETURN saleprice;
		ELSE 
			RETURN saleprice*rateToVnd/rateVndToCob;
		END IF;
	END
$$
LANGUAGE plpgsql;

select mdl_cob.exchangetocobcurrency('USD', 30000)