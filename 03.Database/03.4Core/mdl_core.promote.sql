DROP FUNCTION mdl_core.promote 
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_core.promote(jsonInput TEXT)
RETURNS TABLE(
n_id mdl_core.tb_promotion_history.n_id%TYPE,
s_voucher_code mdl_core.tb_promotion_history.s_voucher_code%TYPE,
s_voucher_serial mdl_core.tb_promotion_history.s_voucher_serial%TYPE
 )  
AS
$$
DECLARE 
p_userid TEXT := jsonInput::json->>'suserid';
p_promotion_id TEXT := jsonInput::json->>'promotionid';
p_promotion_type TEXT := jsonInput::json->>'promotiontype';
p_rewardCount INTEGER = 0;
p_isCondition INTEGER;
-- p_randomNumber INTEGER;
p_serial TEXT;
p_voucher_codes TEXT[];
p_voucher_code TEXT;
p_counter INTEGER := 0;
p_isRewarded INTEGER := 0;
--test TEXT;
BEGIN
	-- p_rewardCount, p_isCondition
	SELECT n_slot, n_is_condition INTO p_rewardCount, p_isCondition
	FROM mdl_core.tb_customer_loyalty_program p
	WHERE p.s_program_id = p_promotion_id 
		AND p.s_program_type = p_promotion_type
		AND p.d_from_date <= NOW() AND p.d_to_date >= NOW();

	IF p_rewardCount = 0 
	THEN 
		RETURN  QUERY (SELECT -1 ,'' ,'' );
			RETURN;
	END IF;

	
	SELECT COUNT(*) INTO p_isRewarded
	FROM mdl_core.tb_promotion_history h
	WHERE h.s_loyalty_program_id = p_promotion_id AND h.s_user_id = p_userid;
	
	IF p_isRewarded >= p_rewardCount 
	THEN 
		RETURN QUERY (SELECT -2 ,'' ,'' );
			RETURN;
	END IF;

	
	LOOP		
		EXIT WHEN p_counter =  p_rewardCount;
		p_counter := p_counter + 1;
		select array_to_string(ARRAY(SELECT CHR((48 + round(RANDOM() * 9)) :: INTEGER) INTO p_voucher_code FROM generate_series(1,12)), '');
		--SELECT p_voucher_code INTO test;
		p_voucher_codes := p_voucher_codes || p_voucher_code;
		INSERT INTO mdl_core.tb_promotion_history(s_loyalty_program_id, s_user_id, s_voucher_code, d_createddate, n_status, s_voucher_id, s_voucher_serial)		 
		SELECT  t.p_promotion_id, t.p_userid, t.p_voucher_code, t.d_createddate, t.n_status, t.s_voucher_id, t.s_voucher_serial 
		FROM 
			((SELECT p_promotion_id,  p_userid, p_voucher_code, NOW() d_createddate, CASE WHEN p_isCondition = 0 THEN 1 ELSE 0 END n_status)  t1 --de.s_voucher_id, de.s_voucher_serial		
			inner JOIN 
				(SELECT  de.s_voucher_id, de.s_voucher_serial 
				FROM mdl_core.tb_voucher_details de
				INNER JOIN (SELECT * 
								FROM mdl_core.tb_voucher v
								WHERE v.d_from_date <= NOW() AND v.d_to_date >= NOW() AND v.n_status = 1) v
								ON de.s_voucher_id = v.s_voucher_id
				WHERE de.n_status = 0
				ORDER BY RANDOM()
				LIMIT 1) t2
			ON TRUE)t;
--SELECT '5' INTO test;
		UPDATE mdl_core.tb_voucher_details  SET n_status = 1 WHERE mdl_core.tb_voucher_details.s_voucher_serial IN 
		(
			SELECT h.s_voucher_serial
			FROM mdl_core.tb_promotion_history h
			WHERE h.s_user_id = p_userid AND h.s_loyalty_program_id = p_promotion_id  
		);
	END LOOP;

	 RETURN query   
	 	SELECT h.n_id n_id, h.s_voucher_code s_voucher_code, CASE WHEN p_isCondition = 0 THEN  h.s_voucher_serial ELSE '' END s_voucher_serial
		FROM mdl_core.tb_promotion_history h
		WHERE h.s_user_id = p_userid
		AND h.s_voucher_code IN	(SELECT UNNEST(p_voucher_codes[0:p_counter]));
	DROP TABLE IF EXISTS p_isrewarded;
	EXCEPTION WHEN OTHERS THEN
	RAISE NOTICE 'sdfj';
END;
$$
LANGUAGE plpgsql;


SELECT * FROM  mdl_core.promote('{"suserid":"c82a38b6f05de0fe8d8d7bf829dae0afab2639e4283b0a4629009e2319807198", "promotionid":"REGIS", "promotiontype":"VOUCHER"}')


