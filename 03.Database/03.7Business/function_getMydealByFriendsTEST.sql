
DROP FUNCTION mdl_business.getMydealByFriendsTEST;
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_business.getMydealByFriendsTEST
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
	s_business_service_id mdl_business.tb_mydeal.s_business_service_id%TYPE,
	s_group_business_cate_id  mdl_business.tb_mydeal.s_group_business_cate_id%TYPE,
	s_partner_name mdl_partner.tb_listpartners.s_partner_name_vn%TYPE,	
	s_company_logo mdl_partner.tb_listpartners.s_company_logo%TYPE,	count_content BIGINT,	
	i_group_business_icon mdl_core.tb_group_business.i_group_business_icon%TYPE,
	s_group_business_name mdl_core.tb_group_business.s_group_business_name_vn%TYPE,
	--s_business_service_name mdl_core.tb_business_services.s_business_service_name_en%TYPE,
	--i_business_service_icon mdl_core.tb_business_services.i_business_service_icon%TYPE,
	s_group_business_cate_name mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE,
	s_currency_id mdl_business.tb_mydeal.s_currency_id%TYPE,
	number_like BIGINT,	
	s_currency_symbol mdl_core.tb_currencies.s_currency_symbol%TYPE,
	n_price_cob NUMERIC(12,2),
	s_comments mdl_business.tb_mydeal_comments.s_comments%TYPE,
	number_of_friends_like BIGINT,	
	number_of_comment BIGINT,-----1
	number_of_share BIGINT,
	is_like BIGINT,
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
			,dt.s_business_service_id
			,dt.s_group_business_cate_id
			,dt.s_partner_name
			,dt.s_company_logo
			,dt.countContent
			,dt.i_group_business_icon
			,dt.s_group_business_name
			--,dt.s_business_service_name
			--,dt.i_business_service_icon
			,dt.s_group_business_cate_name
			,dt.s_currency_id
			,dt.numberLike				
			,dt.s_currency_symbol
			,dt.n_price_cob
			,'' s_comments
			,dt.number_of_friends_like
			,dt.number_of_comment -----1
			,dt.number_of_share-----2
			,dt.is_liked
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
					,dt.s_business_service_id
					,dt.s_group_business_cate_id
					,dt.s_partner_name
					,dt.s_company_logo
					,dt.countContent
					,dt.i_group_business_icon
					,dt.s_group_business_name
					--,dt.s_business_service_name
					--,dt.i_business_service_icon
					,COALESCE (dt.s_group_business_cate_name, '') s_group_business_cate_name
					,dt.s_currency_id
					,COUNT(dt.userIdLike) numberLike				
					,dt.s_currency_symbol
					,dt.n_price_cob
					,'' s_comments
					,dt.number_of_friends_like
					,dt.number_of_comment -----1
					,dt.number_of_share-----2
					,COALESCE(dt.is_liked,0) is_liked
					,dt.contenElement
			FROM (SELECT 
						t.n_id
						,t.s_mydeal_id
						,t.s_partner_id
						,t.s_user_id
						,t.n_is_buyer
						,t.s_deal_header
						,t.s_deal_content
						,CASE WHEN COALESCE(t.s_deal_image1,'') = '' THEN ''
								ELSE 'business/getImage/mydeal/' || t.s_mydeal_id || '/1'
						 END s_deal_image1
						,CASE WHEN COALESCE(t.s_deal_image2,'') = '' THEN ''
								ELSE 'business/getImage/mydeal/' || t.s_mydeal_id || '/2'
						 END s_deal_image2
						,CASE WHEN COALESCE(t.s_deal_image3,'') = '' THEN ''
								ELSE 'business/getImage/mydeal/' || t.s_mydeal_id || '/3'
						 END s_deal_image3
						,t.n_price
						,t.d_deal_create_date
						,t.d_deal_vlid_from
						,t.d_deal_valid_to
						,t.n_is_buyer_posting
						,t.s_group_business_service_id
						,t.s_currency_id
						,t.s_business_service_id
						,t.s_group_business_cate_id
						,t.n_quantity
						,t.s_product_id						
						,CASE WHEN languageCode = 'VN' THEN pn.s_partner_name_vn
							 	WHEN languageCode = 'EN' THEN pn.s_partner_name_en
							 	ELSE pn.s_partner_name_cn 
						END s_partner_name
						--,pn.s_company_logo
						,('partner/getImage/' || pn.s_partner_id) s_company_logo
						,CASE WHEN contentSearch = '' THEN 0 
					  		ELSE (SELECT COUNT(*) 
							  		FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contentSearch,'g'))
					  	END countContent
						,CASE WHEN contentSearch = '' THEN 0 
					  		ELSE (SELECT COUNT(*) 
						FROM regexp_matches(REPLACE(UPPER(mdl_business.unaccent(CONCAT(t.s_deal_header, t.s_deal_content, pn.s_partner_name_vn, pn.s_partner_name_en, pn.s_partner_name_cn))), ' ', ''),contenElement,'g') )
					  	END contenElement
						--,grb.i_group_business_icon
						,('core/getImage/groupbusinesscate/' || grb.s_group_business_id) i_group_business_icon
						,CASE WHEN languageCode = 'VN' THEN grb.s_group_business_name_vn
							 	WHEN languageCode = 'EN' THEN grb.s_group_business_name_en
							 	ELSE grb.s_group_business_name_cn
						END s_group_business_name
						-- ,CASE WHEN languageCode = 'VN' THEN bs.s_business_service_name_vn
-- WHEN languageCode = 'EN' THEN bs.s_business_service_name_en
-- ELSE bs.s_business_service_name_cn
-- 						END s_business_service_name						 
-- 						,bs.i_business_service_icon
						,CASE WHEN languageCode = 'VN' THEN gbc.s_group_business_cate_name_vn
							 	WHEN languageCode = 'EN' THEN gbc.s_group_business_name_cate_en
							 	ELSE gbc.s_group_business_name_cate_cn
						END s_group_business_cate_name
						,mylike.s_user_id userIdLike
						,cur.s_currency_symbol
						,mdl_cob.exchangetocobcurrency(t.s_currency_id, CAST (t.n_price AS NUMERIC(12,2))) AS n_price_cob
						,fl.number_of_friends_like
						,cmt.number_of_comment ---1
						,sh.number_of_share ----2
						,islike.is_liked
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
				--INNER JOIN mdl_core.tb_business_services bs ON t.s_business_service_id = bs.s_business_service_id
				--INNER JOIN mdl_core.tb_groupbusiness_cate gbc ON t.s_group_business_cate_id = gbc.s_group_business_cate_id
				LEFT JOIN  mdl_core.tb_groupbusiness_cate gbc ON t.s_group_business_cate_id = gbc.s_group_business_cate_id
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
				LEFT JOIN (
						SELECT md.s_mydeal_id, COUNT(cmt.s_mydeal_comment_id) AS number_of_comment
						FROM mdl_business.tb_mydeal_comments cmt
						INNER JOIN mdl_business.tb_mydeal md ON cmt.s_mydeal_id = md.s_mydeal_id
						WHERE cmt.s_user_id != md.s_user_id
						GROUP BY md.s_mydeal_id
					) cmt
					ON t.s_mydeal_id = cmt.s_mydeal_id
				LEFT JOIN (
						SELECT md.s_mydeal_id, COUNT(sh.s_user_id) AS number_of_share
						FROM mdl_business.tb_mydeal_share sh
						INNER JOIN mdl_business.tb_mydeal md ON sh.s_mydeal_id = md.s_mydeal_id
						WHERE sh.s_user_id != md.s_user_id
						GROUP BY md.s_mydeal_id
				) sh
					ON t.s_mydeal_id = sh.s_mydeal_id
				LEFT JOIN (
						SELECT ml.s_mydeal_like_id, COUNT(*) AS is_liked
						FROM mdl_business.tb_mydeal_like ml
						WHERE ml.s_user_id = userId --AND ml.s_mydeal_like_id = t.s_mydeal_id
						GROUP BY ml.s_mydeal_like_id
				) islike
					ON t.s_mydeal_id = islike.s_mydeal_like_id
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
					,dt.s_business_service_id
					,dt.s_group_business_cate_id
					,dt.s_mydeal_id
					,dt.s_partner_name
					,dt.s_company_logo
					,dt.countContent
					,dt.i_group_business_icon
					,dt.s_group_business_name
					--,dt.s_business_service_name
					--,dt.i_business_service_icon
					,dt.s_group_business_cate_name
					,dt.s_business_service_id
					,dt.s_currency_id
					,dt.s_currency_symbol
					,dt.n_price_cob
					,dt.number_of_friends_like
					,dt.number_of_comment
					,dt.number_of_share
					,dt.is_liked
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