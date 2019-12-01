DELIMITER //
CREATE OR REPLACE FUNCTION mdl_business.registerMedicalAppointment(jsonInput TEXT)
RETURNS INTEGER AS
$$
DECLARE 
		 p_partnerid TEXT := jsonInput::json->>'ssellerPartnerId';
		 p_businessserviceid TEXT := jsonInput::json->>'sbusinessServiceId';
		 p_appointmentDate TEXT := jsonInput::json->>'dappointmentDate';
		 p_dayofWeek INTEGER := jsonInput::json->>'DayofWeek';
		 p_cardId TEXT := jsonInput::json->>'sCardIdNo';
		 p_orderId TEXT := jsonInput::json->>'Orderid';
		 p_buyer_partnerId TEXT := jsonInput::json->>'sbuyerPartnerId';
		 p_group_bizcate TEXT := jsonInput::json->>'sgroupBusinessCateId';
		 p_buzservice TEXT := jsonInput::json->>'sbusinessServiceId';
		 p_buyer_user TEXT := jsonInput::json->>'sbuyerUserId';
		 p_patient_name TEXT := jsonInput::json->>'spatientName';
		 p_patient_age TEXT := jsonInput::json->>'spatientAge';
		 p_gender TEXT := jsonInput::json->>'spatientGender';
		 p_seller_user TEXT := jsonInput::json->>'ssellerUserId';
		 p_seller_partner TEXT := jsonInput::json->>'ssellerPartnerId';
		 p_bhyt INTEGER := jsonInput::json->>'nbhyt';
		 p_appointmentNumber INTEGER;
		 appoint_time TIME;
		 interval_time INTEGER;
		 fromtime TIME;
		 totime TIME;
		 countperday INTEGER;
		 duration INTEGER;
		 startfrom TIME;
		 cardidisok INTEGER;
		 previoustime TIME;
BEGIN
		-- WITH workingtime AS
-- 		(
			SELECT card.checkcardidforanotherorder
			FROM (
					SELECT * FROM mdl_business.checkcardidforanotherorder(p_partnerid,p_cardId,TO_DATE(p_appointmentDate,'DD/MM/YYYY'))
					) card INTO cardidisok;
			IF cardidisok != 0 then
			RETURN -1;
			END IF;
			CREATE temp TABLE workingtime AS
	    	(SELECT CASE WHEN seller_business.n_count_per_day IS NULL THEN  t.n_count_per_day
			 				ELSE seller_business.n_count_per_day
					  END countday
	    			,CASE WHEN seller_business.n_duration_for_session IS NULL THEN  t.n_duration_for_session 
	    					ELSE seller_business.n_duration_for_session
					 END  durationtime
	    			,CASE WHEN seller_business.from_Time IS NULL THEN 
    							CASE  WHEN p_dayofWeek = 1 THEN t.s_su_from
			    					WHEN p_dayofWeek = 2 THEN t.s_mo_from
			    					WHEN p_dayofWeek = 3 THEN t.s_tu_from
			    					WHEN p_dayofWeek = 4 THEN t.s_we_from
			    					WHEN p_dayofWeek = 5 THEN t.s_th_from
			    					WHEN p_dayofWeek = 6 THEN t.s_fr_from
		    					ELSE t.s_sa_from
	    						END
	    			 		ELSE seller_business.from_Time
					 END from_Time
	    			,CASE WHEN seller_business.to_Time IS NULL THEN 
	    						CASE WHEN p_dayofWeek = 1 THEN t.s_su_to
			    					WHEN p_dayofWeek = 2 THEN t.s_mo_to
			    					WHEN p_dayofWeek = 3 THEN t.s_tu_to
			    					WHEN p_dayofWeek = 4 THEN t.s_we_to
			    					WHEN p_dayofWeek = 5 THEN t.s_th_to
			    					WHEN p_dayofWeek = 6 THEN t.s_fr_to
			    					ELSE t.s_sa_to
			    				END 
	    					ELSE seller_business.to_Time   					
	    			 END to_Time
			FROM mdl_partner.tb_partner_workingtime t
					LEFT JOIN 
					(
							SELECT 
									mb.s_partner_id
									,t.n_count_per_day
									,n_duration_for_session
									,CASE WHEN p_dayofWeek = 1 THEN t.s_su_from
					    					WHEN p_dayofWeek = 2 THEN t.s_mo_from
					    					WHEN p_dayofWeek = 3 THEN t.s_tu_from
					    					WHEN p_dayofWeek = 4 THEN t.s_we_from
					    					WHEN p_dayofWeek = 5 THEN t.s_th_from
					    					WHEN p_dayofWeek = 6 THEN t.s_fr_from
					    					ELSE t.s_sa_from
					    			END from_Time
					    			,CASE WHEN p_dayofWeek = 1 THEN t.s_su_to
					    					WHEN p_dayofWeek = 2 THEN t.s_mo_to
					    					WHEN p_dayofWeek = 3 THEN t.s_tu_to
					    					WHEN p_dayofWeek = 4 THEN t.s_we_to
					    					WHEN p_dayofWeek = 5 THEN t.s_th_to
					    					WHEN p_dayofWeek = 6 THEN t.s_fr_to
					    					ELSE t.s_sa_to
					    			END to_Time
							FROM mdl_account.tb_my_business mb
							INNER JOIN mdl_account.tb_mybusiness_workingtime t
							ON mb.s_my_business_id = t.s_my_business_id			
							WHERE mb.s_partner_id = p_partnerid AND mb.s_user_id = p_seller_user
					)seller_business ON t.s_partner_id = seller_business.s_partner_id
			WHERE t.s_partner_id = p_partnerid
			and t.s_business_service_id = p_businessserviceid);
-- 	   )
	   --get appointment Number
	   SELECT from_Time into fromtime FROM workingtime ;
		SELECT countday INTO countperday FROM workingtime ;
		SELECT durationtime INTO duration	FROM workingtime ;
		SELECT to_Time INTO 	totime FROM workingtime;
		SELECT COALESCE(ma.getmaxappointmentnumber,0)
		FROM (
				SELECT * from  mdl_business.getmaxappointmentnumber(p_partnerid, p_businessserviceid,TO_DATE(p_appointmentDate,'DD/MM/YYYY'))
				) ma INTO p_appointmentNumber;
		IF(p_appointmentNumber+1) > countperday THEN
			RAISE NOTICE 'Task completed sucessfully. p_appointmentNumber > n_count_per_day';
			RETURN 0;
		ELSE 
				interval_time :=  p_appointmentNumber * duration;				
				appoint_time := fromtime::INTERVAL + make_interval(0,0,0,0,0,interval_time); --make_time(0, interval_time, 00.0);
				IF TO_DATE(p_appointmentDate,'DD/MM/YYYY') = NOW()::DATE
					THEN
						IF NOW()::TIME > 	appoint_time
						THEN 
								--kiem tra truoc do xem co order nao ko
							 	SELECT MAX(s_appointment_time) INTO previoustime
							 	FROM mdl_business.tb_order o
							 	WHERE o.d_appointment_date = NOW()::DATE
								 AND o.s_seller_partner_id =  p_partnerid
								 AND o.s_business_service_id = p_businessserviceid;
							 	IF previoustime IS NULL
							 		THEN appoint_time := NOW()::TIME;
							 	ELSE							 	
									IF NOW()::TIME >= previoustime::INTERVAL + make_interval(0,0,0,0,0,duration)
									 THEN
									 	appoint_time := NOW()::TIME;
									 ELSE 
									 	appoint_time := previoustime::INTERVAL + make_interval(0,0,0,0,0,duration);
									 	--appoint_time :=  NOW()::TIME;
								 	END IF;
								 END IF;						 
						END IF;
				END IF;
				IF TO_CHAR(appoint_time, 'HH24:MI') >= TO_CHAR(totime::TIME, 'HH24:MI')
					THEN 
					RETURN 2;
				END IF;
		END IF;
		--insert
		INSERT INTO mdl_business.tb_order(
			s_order_number
		  ,d_appointment_date
		  ,d_order_date
		  ,s_buyer_partner_id
		  ,s_group_business_cate_id
		  ,s_business_service_id
		  ,s_buyer_user_id
		  ,n_order_status_id
		  ,n_appointment_number
		  ,s_appointment_time
		  ,s_patient_name
		  ,s_patient_age
		  ,s_patient_gender
		  ,n_bhyt
		  ,s_seller_user_id
		  ,s_seller_partner_id
		  ,s_card_id_no
		)
		VALUES(
			p_orderId
		  ,TO_DATE(p_appointmentDate,'DD/MM/YYYY')
		  ,NOW()
		  ,p_buyer_partnerId
		  ,p_group_bizcate
		  ,p_buzservice
		  ,p_buyer_user
		  ,1
		  ,(p_appointmentNumber+1)
		  , TO_CHAR(appoint_time, 'HH24:MI')
		  ,p_patient_name
		  ,p_patient_age
		  ,p_gender
		  ,p_bhyt
		  ,p_seller_user
		  ,p_seller_partner
		  ,p_cardId
		);
		DROP TABLE IF EXISTS workingtime;
		DROP TABLE IF EXISTS previoustime;
		RETURN 1;
-- RAISE NOTICE 'Task completed sucessfully.';
-- RETURN 1;
-- EXCEPTION
-- RAISE notice E'Got exception:
-- jsonInput  : %', jsonInput;
--     RETURN 0;
END
$$
LANGUAGE plpgsql;
select mdl_business.registerMedicalAppointment(
'{"ssellerPartnerId":"1570763371113","dappointmentDate":"28/11/2019","sbuyerPartnerId":"buyer","sgroupBusinessCateId":"group","sbusinessServiceId":"301001","sbuyerUserId":"52ad08e22c34ec970b0b7b91692a5c530693bf2f78b69867438eb1a917cd72f7","spatientName":"bbb","spatientAge":"20","spatientGender":"0","nbhyt":"1","nisBuyerPartnerConfirmed":"10","sbuyerPartnerConfirmedBy":"sBy","nisDone":"10","nisPaid":"10","ssellerUserId":"sellUId","nisSellerPartnerConfirmed":"1","ssellerPartnerConfirmedBy":"confirmed","ntotalAmount":"100","scurrency":"scurrency","nbuyerRaking":"101","soderSummary":"soderSummary","sCardIdNo":"38472235678174","Orderid":"d9c3b35afc5cbe8815e9e5bc28f115e2c81818cvdySdcGtEsfRettbTrew","DayofWeek":5}')


SELECT * FROM mdl_business.workingtime
SELECT * from mdl_business.getmaxappointmentnumber('1574048864853', '301005',TO_DATE('29-11-2019','DD/MM/YYYY'));
select make_time(0, 0, 00.0)
SELECT mdl_business.checkcardidforanotherorder('1570784089949','123456789','2019-11-19')

SELECT COUNT(*)
	FROM mdl_business.tb_order t
	WHERE s_seller_partner_id = '1570784089949' AND t.s_card_id_no = '123456789'
	AND t.d_appointment_date = '2019-11-20' AND t.n_order_status_id = 1
	
	SELECT TO_DATE('20/11/2019','DD/MM/YYYY')
	
SELECT * FROM 	mdl_business.medicalemaildata('bf50a3afbf5c13ef12ee5b8a1716cc1cb0d2b2a7341d4dd7f881bb4678263c00','')

SELECT make_interval(0,0,0,0,0,75);

SELECT NULL::TIME