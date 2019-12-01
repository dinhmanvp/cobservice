CREATE OR REPLACE FUNCTION mdl_partner.getbusinessserviceinfobypartner(partnerid TEXT) RETURNS TABLE 
(
n_id mdl_partner.tb_partner_business_services.n_id%TYPE,
n_is_activated mdl_partner.tb_partner_business_services.n_is_activated%TYPE,
s_business_service_id mdl_partner.tb_partner_business_services.s_business_service_id%TYPE,
s_partner_business_service_id mdl_partner.tb_partner_business_services.s_partner_business_service_id%TYPE,
s_partner_id mdl_partner.tb_partner_business_services.s_partner_id%TYPE,
s_business_service_name_en mdl_core.tb_business_services.s_business_service_name_en%TYPE,
s_business_service_name_vn mdl_core.tb_business_services.s_business_service_name_en%TYPE,
s_business_service_name_cn mdl_core.tb_business_services.s_business_service_name_en%TYPE
)
AS
$$
	SELECT pbs.n_id
	,pbs.n_is_activated
	,pbs.s_business_service_id
	,pbs.s_partner_business_service_id
	,pbs.s_partner_id
	,core.s_business_service_name_en
	, core.s_business_service_name_vn
	, core.s_business_service_name_cn
	FROM mdl_partner.tb_partner_business_services pbs 
	INNER JOIN mdl_core.tb_business_services core
	ON pbs.s_business_service_id = core.s_business_service_id
	WHERE pbs.n_is_activated = 1 AND pbs.s_partner_id = partnerid
$$
LANGUAGE sql;
