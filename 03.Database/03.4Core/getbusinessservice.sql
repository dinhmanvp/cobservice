CREATE OR REPLACE FUNCTION mdl_core.getbusinessservice(groupbusiness_id TEXT) 
RETURNS TABLE
(
	n_id mdl_core.tb_business_services.n_id%TYPE,
	s_business_service_id mdl_core.tb_business_services.s_business_service_id%TYPE,
	s_group_business_id mdl_core.tb_business_services.s_group_business_id%TYPE,
	s_business_service_name_en mdl_core.tb_business_services.s_business_service_name_en%TYPE,
	s_business_service_name_vn mdl_core.tb_business_services.s_business_service_name_vn%TYPE,
	s_business_service_name_cn mdl_core.tb_business_services.s_business_service_name_cn%TYPE,
	i_business_service_icon mdl_core.tb_business_services.i_business_service_icon%TYPE,
	s_business_service_desc_shrink_en mdl_core.tb_business_services.s_business_service_desc_shrink_en%TYPE,
   s_business_service_desc_shrink_vn mdl_core.tb_business_services.s_business_service_desc_shrink_vn%TYPE,
   s_business_service_desc_shrink_cn mdl_core.tb_business_services.s_business_service_desc_shrink_cn%TYPE ,
   s_business_service_desc_en mdl_core.tb_business_services.s_business_service_desc_en%TYPE ,
   s_business_service_desc_vn mdl_core.tb_business_services.s_business_service_desc_vn%TYPE ,
   s_business_service_desc_cn mdl_core.tb_business_services.s_business_service_desc_cn%TYPE ,
	n_business_service_rating mdl_core.tb_business_services.n_business_service_rating%TYPE ,
	n_business_service_available mdl_core.tb_business_services.n_business_service_available%TYPE ,
	n_order mdl_core.tb_business_services.n_order%TYPE ,	
	number_partners BIGINT
)
AS 
$$
		SELECT 
				 bs.n_id
				,bs.s_business_service_id
				,bs.s_group_business_id
				,bs.s_business_service_name_en
				,bs.s_business_service_name_vn
				,bs.s_business_service_name_cn
				,('core/getImage/businessservices/' || bs.s_business_service_id) i_business_service_icon
				,bs.s_business_service_desc_shrink_en
				,bs.s_business_service_desc_shrink_vn
				,bs.s_business_service_desc_shrink_cn
				,bs.s_business_service_desc_en
				,bs.s_business_service_desc_vn
				,bs.s_business_service_desc_cn
				,bs.n_business_service_rating
				,bs.n_business_service_available
				,bs.n_order
				,COALESCE(ls.numberPartners ,0) AS number_partners
		FROM mdl_core.tb_business_services bs
		LEFT JOIN (	
						SELECT ls.s_group_business_id, ps.s_business_service_id 
								, COUNT(ls.s_partner_id) AS numberPartners 
						FROM mdl_partner.tb_listpartners ls
						INNER JOIN mdl_partner.tb_partner_business_services ps 
						ON ls.s_partner_id = ps.s_partner_id
						WHERE ls.s_group_business_id = groupbusiness_id
						GROUP BY ls.s_group_business_id, ps.s_business_service_id
		) ls
		ON bs.s_business_service_id   = ls.s_business_service_id
		WHERE bs.s_group_business_id = groupbusiness_id
		ORDER BY bs.n_order asc
$$
LANGUAGE sql;