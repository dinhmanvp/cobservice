CREATE OR REPLACE FUNCTION mdl_partner.getgroupbizcateinfobypartner(partnerid TEXT)
RETURNS TABLE 
(
n_id mdl_partner.tb_partner_bizcate.n_id%TYPE,
s_group_business_cate_id mdl_partner.tb_partner_bizcate.s_group_business_cate_id%TYPE,
s_partner_bizcate_id mdl_partner.tb_partner_bizcate.s_partner_bizcate_id%TYPE,
s_partner_id mdl_partner.tb_partner_bizcate.s_partner_id%TYPE,
n_is_activated mdl_partner.tb_partner_bizcate.n_is_activated%TYPE,
s_group_business_cate_name_vn mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE,
s_group_business_name_cate_en mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE,
s_group_business_name_cate_cn mdl_core.tb_groupbusiness_cate.s_group_business_cate_name_vn%TYPE
)
AS
$$
	SELECT pb.n_id
	, pb.s_group_business_cate_id
	, pb.s_partner_bizcate_id
	, pb.s_partner_id
	, pb.n_is_activated
	, core.s_group_business_cate_name_vn
	, core.s_group_business_name_cate_en
	, core.s_group_business_name_cate_cn
	FROM mdl_partner.tb_partner_bizcate pb INNER JOIN mdl_core.tb_groupbusiness_cate core ON pb.s_group_business_cate_id = core.s_group_business_cate_id
	WHERE pb.n_is_activated = 1 AND pb.s_partner_id = partnerid
$$
LANGUAGE sql;