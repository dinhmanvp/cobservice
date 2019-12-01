CREATE OR REPLACE FUNCTION mdl_marketing.GETBYHOME(is_home INTEGER) RETURNS SETOF mdl_marketing.tb_advertise AS 
$$
		SELECT 
				n_id
			  ,s_user_id
			  ,s_parter_id
			  --,s_image
			  ,('marketing/getImage/advertise/' || n_id)
			  ,n_link_type
			  ,s_url
			  ,n_display_as
			  ,n_order
			  ,d_start_date
			  ,d_end_date
		FROM mdl_marketing.tb_advertise
		WHERE 
		CASE	  		 
			WHEN 	is_home = 0 OR is_home = 1 THEN n_display_as = is_home AND d_start_date <= NOW() AND NOW() <= d_end_date
			WHEN is_home != 0 AND is_home != 1 THEN d_start_date <= NOW() AND NOW() <= d_end_date
			END
		ORDER BY n_order asc
		
$$
LANGUAGE sql;
