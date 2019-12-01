DELIMITER //
CREATE OR REPLACE FUNCTION mdl_business.getMydealByFriends
(
	userId TEXT,
	languageCode TEXT,
	contentSearch TEXT,
	pageNumber INTEGER,
	pageSize INTEGER,
	partnerId TEXT,
	groubBusinessId TEXT,
	contenElement TEXT
)
RETURNS TABLE(
	n_id mdl_business.tb_mydeal.n_id%TYPE,
	s_mydeal_id mdl_business.tb_mydeal.s_mydeal_id%TYPE,
	s_partner_id mdl_business.tb_mydeal.s_partner_id%TYPE,
	s_user_id mdl_business.tb_mydeal.s_user_id%TYPE,
	n_is_buyer mdl_business.tb_mydeal.n_is_buyer%TYPE,
	s_deal_header mdl_business.tb_mydeal.s_deal_header%TYPE,
	s_deal_content mdl_business.tb_mydeal.s_deal_content%TYPE,
	s_deal_image1 mdl_business.tb_mydeal.s_deal_image1%TYPE,
	s_deal_image2 mdl_business.tb_mydeal.s_deal_image2%TYPE,
	s_deal_image3 mdl_business.tb_mydeal.s_deal_image3%TYPE,
	n_price mdl_business.tb_mydeal.n_price%TYPE,
	d_deal_create_date mdl_business.tb_mydeal.d_deal_create_date%TYPE,
	d_deal_vlid_from mdl_business.tb_mydeal.d_deal_vlid_from%TYPE,
	d_deal_valid_to mdl_business.tb_mydeal.d_deal_valid_to%TYPE,
	n_is_buyer_posting mdl_business.tb_mydeal.n_is_buyer_posting%TYPE,
	s_group_business_service_id mdl_business.tb_mydeal.s_group_business_service_id%TYPE,
	s_partner_name mdl_partner.tb_listpartners.s_partner_name_vn%TYPE,
	s_company_logo mdl_partner.tb_listpartners.s_company_logo%TYPE,
	count_content BIGINT,	
	i_group_business_icon mdl_core.tb_group_business.i_group_business_icon%TYPE,
	s_group_business_name mdl_core.tb_group_business.s_group_business_name_vn%TYPE,
	s_currency_id mdl_business.tb_mydeal.s_currency_id%TYPE,
	number_like BIGINT,	
	s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
	n_price_cob NUMERIC(12,2),
	s_comments mdl_business.tb_mydeal_comments.s_comments%TYPE,
	number_of_friends_like BIGINT,	
	row_number BIGINT,
	total_rows BIGINT
)
AS
$$
DECLARE
	p_totalRow BIGINT := 0;
BEGIN

	p_totalRow := (SELECT COUNT (*) total_row
							FROM(
									SELECT dt.n_id
											,dt.s_mydeal_id			
									FROM (SELECT t.*												
										FROM mdl_business.tb_mydeal t
										INNER JOIN mdl_partner.tb_listpartners pn
										ON t.s_partner_id = pn.s_partner_id
										INNER JOIN mdl_core.tb_group_business grb
										ON t.s_group_business_service_id = grb.s_group_business_id
										INNER JOIN mdl_core.tb_currencies cur ON cur.s_currency_id = t.s_currency_id
										WHERE t.s_partner_id = (CASE WHEN partnerId = '' THEN t.s_partner_id ELSE partnerId END)
										AND t.s_group_business_service_id = (CASE WHEN groubBusinessId = '' THEN t.s_group_business_service_id ELSE groubBusinessId END)
										AND(
											(SELECT COUNT(*) 
											FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contentSearch,'g') ) >= (CASE WHEN contentSearch = '' THEN 0 ELSE 1 END)
											OR
											(SELECT COUNT(*) 
											FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contenElement,'g') ) >= (CASE WHEN contentSearch = '' THEN 0 ELSE 1 END)
										)										
									)dt
									GROUP BY dt.n_id, dt.s_mydeal_id	
							) dt
					);
--return query
RETURN query 
SELECT dt.*
FROM (
	SELECT dt.n_id
			,dt.s_mydeal_id
			,dt.s_partner_id
			,dt.s_user_id
			,dt.n_is_buyer
			,dt.s_deal_header
			,dt.s_deal_content
			,dt.s_deal_image1
			,dt.s_deal_image2
			,dt.s_deal_image3
			,dt.n_price
			,dt.d_deal_create_date
			,dt.d_deal_vlid_from
			,dt.d_deal_valid_to
			,dt.n_is_buyer_posting
			,dt.s_group_business_service_id
			,dt.s_partner_name
			,dt.s_company_logo
			,dt.countContent
			,dt.i_group_business_icon
			,dt.s_group_business_name
			,dt.s_currency_id
			,dt.numberLike				
			,dt.s_currency_symbol
			,dt.n_price_cob
			,'' s_comments
			,dt.number_of_friends_like
			,ROW_NUMBER () OVER (ORDER BY dt.countContent DESC, dt.d_deal_vlid_from DESC, dt.d_deal_valid_to DESC) rownumber
			,p_totalRow
	FROM(
			SELECT dt.n_id
					,dt.s_mydeal_id
					,dt.s_partner_id
					,dt.s_user_id
					,dt.n_is_buyer
					,dt.s_deal_header
					,dt.s_deal_content
					,dt.s_deal_image1
					,dt.s_deal_image2
					,dt.s_deal_image3
					,dt.n_price
					,dt.d_deal_create_date
					,dt.d_deal_vlid_from
					,dt.d_deal_valid_to
					,dt.n_is_buyer_posting
					,dt.s_group_business_service_id
					,dt.s_partner_name
					,dt.s_company_logo
					,dt.countContent
					,dt.i_group_business_icon
					,dt.s_group_business_name
					,dt.s_currency_id
					,COUNT(dt.userIdLike) numberLike				
					,dt.s_currency_symbol
					,dt.n_price_cob
					,'' s_comments
					,dt.number_of_friends_like
					,dt.contenElement
			FROM (SELECT t.*
						,CASE WHEN languageCode = 'VN' THEN pn.s_partner_name_vn
							 	WHEN languageCode = 'EN' THEN pn.s_partner_name_en
							 	ELSE pn.s_partner_name_cn 
						END s_partner_name
						,pn.s_company_logo
						,CASE WHEN contentSearch = '' THEN 0 
					  		ELSE (SELECT COUNT(*) 
							  		FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contentSearch,'g'))
					  	END countContent
						,CASE WHEN contentSearch = '' THEN 0 
					  		ELSE (SELECT COUNT(*) 
						FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contenElement,'g') )
					  	END contenElement
						,grb.i_group_business_icon
						,CASE WHEN languageCode = 'VN' THEN grb.s_group_business_name_vn
							 	WHEN languageCode = 'EN' THEN grb.s_group_business_name_en
							 	ELSE grb.s_group_business_name_cn
						END s_group_business_name
						, mylike.s_user_id userIdLike
						,cur.s_currency_symbol
						,mdl_cob.exchangetocobcurrency(t.s_currency_id, CAST (t.n_price AS NUMERIC(12,2))) AS n_price_cob
						,fl.number_of_friends_like						
				FROM mdl_business.tb_mydeal t
				INNER JOIN mdl_partner.tb_listpartners pn
				ON t.s_partner_id = pn.s_partner_id
				LEFT JOIN mdl_business.tb_mydeal_country coun
				ON t.s_mydeal_id = coun.s_mydeal_id
				INNER JOIN mdl_core.tb_group_business grb
				ON t.s_group_business_service_id = grb.s_group_business_id
				LEFT JOIN mdl_business.tb_mydeal_like mylike
				ON t.s_mydeal_id = mylike.s_mydeal_like_id
				INNER JOIN mdl_core.tb_currencies cur ON cur.s_currency_id = t.s_currency_id
				LEFT JOIN (
						select dl.s_mydeal_like_id, COUNT(f.s_friend_user_id) AS number_of_friends_like
						 	 from	mdl_business.tb_mydeal_like dl
						inner join  mdl_account.tb_myfriends f
										ON dl.s_user_id = f.s_friend_user_id
						WHERE f.s_owner_user_id = userId
						GROUP BY dl.s_mydeal_like_id
						ORDER BY number_of_friends_like DESC
					) fl
					ON mylike.s_mydeal_like_id = fl.s_mydeal_like_id
				WHERE 
				t.s_partner_id = (CASE WHEN partnerId = '' THEN t.s_partner_id ELSE partnerId END)
				AND t.s_group_business_service_id = (CASE WHEN groubBusinessId = '' THEN t.s_group_business_service_id ELSE groubBusinessId END)
				AND(
						(SELECT COUNT(*) 
						FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contentSearch,'g') ) >= (CASE WHEN contentSearch = '' THEN 0 ELSE 1 END)
						OR
						(SELECT COUNT(*) 
						FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contenElement,'g') ) >= (CASE WHEN contentSearch = '' THEN 0 ELSE 1 END)
				) 
			)dt
			GROUP BY dt.n_id
					,dt.s_mydeal_id
					,dt.s_partner_id
					,dt.s_user_id
					,dt.n_is_buyer
					,dt.s_deal_header
					,dt.s_deal_content
					,dt.s_deal_image1
					,dt.s_deal_image2
					,dt.s_deal_image3
					,dt.n_price
					,dt.d_deal_create_date
					,dt.d_deal_vlid_from
					,dt.d_deal_valid_to
					,dt.n_is_buyer_posting
					,dt.s_group_business_service_id
					,dt.s_mydeal_id
					,dt.s_partner_name
					,dt.s_company_logo
					,dt.countContent
					,dt.i_group_business_icon
					,dt.s_group_business_name
					,dt.s_currency_id
					,dt.s_currency_symbol
					,dt.n_price_cob
					,dt.number_of_friends_like
					,dt.contenElement
		ORDER BY  dt.countContent DESC, dt.contenElement DESC, dt.number_of_friends_like DESC, dt.d_deal_vlid_from DESC, dt.d_deal_valid_to DESC		
	) dt
) dt
WHERE (pageNumber-1)*pageSize + 1  <= dt.rownumber 
		AND dt.rownumber  <= (pageNumber)*pageSize;
--END
--ORDER BY dt.number_of_friends_like DESC;
END;
$$
LANGUAGE plpgsql;