CREATE OR REPLACE FUNCTION mdl_partner.getpartnerbizcates(partnerid TEXT) 
RETURNS TABLE(
	n_id mdl_core.tb_groupbusiness_cate.n_id%TYPE,
	s_group_business_cate_id mdl_core.tb_groupbusiness_cate.s_group_business_cate_id%TYPE,
	s_group_business_id mdl_core.tb_groupbusiness_cate.s_group_business_id%TYPE,
	s_group_business_cate_name_vn mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE,
	s_group_business_name_cate_en mdl_core.tb_groupbusiness_cate.s_group_business_name_cate_en%TYPE,
	s_group_business_name_cate_cn mdl_core.tb_groupbusiness_cate.s_group_business_name_cate_cn%TYPE,
	s_group_business_description_vn mdl_core.tb_groupbusiness_cate.s_group_business_description_vn%TYPE,
	s_group_business_description_en mdl_core.tb_groupbusiness_cate.s_group_business_description_en%TYPE,
	s_group_business_description_cn mdl_core.tb_groupbusiness_cate.s_group_business_description_cn%TYPE,
	i_group_business_cate_icon mdl_core.tb_groupbusiness_cate.i_group_business_cate_icon%TYPE,
	s_partner_id mdl_partner.tb_partner_bizcate.s_partner_id%TYPE,
	s_partner_bizcate_id mdl_partner.tb_partner_bizcate.s_partner_bizcate_id%TYPE,
	n_is_activated mdl_partner.tb_partner_bizcate.n_is_activated%TYPE
)   
AS 
$$
	SELECT  
			 gbc.n_id
			,gbc.s_group_business_cate_id
			,gbc.s_group_business_id
			,gbc.s_group_business_cate_name_vn
			,gbc.s_group_business_name_cate_en
			,gbc.s_group_business_name_cate_cn
			,gbc.s_group_business_description_vn
			,gbc.s_group_business_description_en
			,gbc.s_group_business_description_cn
			--,gbc.i_group_business_cate_icon
			,('core/getImage/groupbusinesscate/' || gbc.s_group_business_cate_id) i_group_business_cate_icon
			,pbc.s_partner_id, pbc.s_partner_bizcate_id, pbc.n_is_activated
	FROM mdl_partner.tb_partner_bizcate pbc
	INNER JOIN mdl_core.tb_groupbusiness_cate gbc
	ON gbc.s_group_business_cate_id = pbc.s_group_business_cate_id
	WHERE pbc.s_partner_id = partnerid
			AND pbc.n_is_activated = 1
	ORDER BY pbc.s_group_business_cate_id
$$
LANGUAGE sql;