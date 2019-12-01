/*
SELECT * FROM mdl_partner.getpartnerbyuseridgroupid('0000000009','321')
*/
DELIMITER //
CREATE OR REPLACE FUNCTION mdl_partner.getpartnerbyuseridgroupid
(
	userid character varying,
	groupbusinessid character varying,
	businessserviceid character varying,
	businesstypeid character varying,
	isapprove integer
)
RETURNS TABLE(
	n_id mdl_partner.tb_listpartners.n_id%TYPE,
	s_partner_id mdl_partner.tb_listpartners.s_partner_id%TYPE,
	s_partner_name_en mdl_partner.tb_listpartners.s_partner_name_en%TYPE,
	s_partner_name_vn mdl_partner.tb_listpartners.s_partner_name_vn%TYPE,
	s_partner_name_cn mdl_partner.tb_listpartners.s_partner_name_cn%TYPE,
	s_business_phone mdl_partner.tb_listpartners.s_business_phone%TYPE,
	s_business_email mdl_partner.tb_listpartners.s_business_email%TYPE,
	s_address_no mdl_partner.tb_listpartners.s_address_no%TYPE,
	s_street mdl_partner.tb_listpartners.s_street%TYPE,
	s_company_logo mdl_partner.tb_listpartners.s_company_logo%TYPE,
	s_taxcode mdl_partner.tb_listpartners.s_taxcode%TYPE,
	s_owner_name mdl_partner.tb_listpartners.s_owner_name%TYPE,
	d_registration_date mdl_partner.tb_listpartners.d_registration_date%TYPE,
	s_opening_time mdl_partner.tb_listpartners.s_opening_time%TYPE,
	s_close_time mdl_partner.tb_listpartners.s_close_time%TYPE,
	s_country_id mdl_partner.tb_listpartners.s_country_id%TYPE,
	s_city_id mdl_partner.tb_listpartners.s_city_id%TYPE,
	s_group_business_id mdl_partner.tb_listpartners.s_group_business_id%TYPE,
	s_business_type_id mdl_partner.tb_listpartners.s_business_type_id%TYPE,
	s_created_by mdl_partner.tb_listpartners.s_created_by%TYPE,
	s_partner_desc_vn mdl_partner.tb_listpartners.s_partner_desc_vn%TYPE,
	s_partner_desc_en mdl_partner.tb_listpartners.s_partner_desc_en%TYPE,
	s_partner_desc_cn mdl_partner.tb_listpartners.s_partner_desc_cn%TYPE,
	n_partner_rating mdl_partner.tb_listpartners.n_partner_rating%TYPE,
	s_full_address mdl_partner.tb_listpartners.s_full_address%TYPE,
	n_is_approved mdl_partner.tb_listpartners.n_is_approved%TYPE,
	s_company_type_id mdl_partner.tb_listpartners.s_company_type_id%TYPE,
	s_phone_contact mdl_partner.tb_listpartners.s_phone_contact%TYPE,
	s_group_business_name_en mdl_core.tb_group_business.s_group_business_name_en%TYPE,
	s_group_business_name_vn mdl_core.tb_group_business.s_group_business_name_vn%TYPE,
	s_group_business_name_cn mdl_core.tb_group_business.s_group_business_name_cn%TYPE
)
AS $$
BEGIN
	IF UserId = '' Then
		return query
		SELECT ls.n_id
				 ,ls.s_partner_id
				 ,ls.s_partner_name_en
				 ,ls.s_partner_name_vn
				 ,ls.s_partner_name_cn
				 ,ls.s_business_phone
				 ,ls.s_business_email
				 ,ls.s_address_no
				 ,ls.s_street
				 --,ls.s_company_logo
				 ,('partner/getImage/' || ls.s_partner_id) s_company_logo
				 ,ls.s_taxcode
				 ,ls.s_owner_name
				 ,ls.d_registration_date
				 ,ls.s_opening_time
				 ,ls.s_close_time
				 ,ls.s_country_id
				 ,ls.s_city_id
				 ,ls.s_group_business_id
				 ,ls.s_business_type_id
				 ,ls.s_created_by
				 ,ls.s_partner_desc_vn
				 ,ls.s_partner_desc_en
				 ,ls.s_partner_desc_cn
				 ,ls.n_partner_rating
				 ,ls.s_full_address
				 ,ls.n_is_approved
				 ,ls.s_company_type_id
				 ,ls.s_phone_contact
				 ,gbs.s_group_business_name_en
				 ,gbs.s_group_business_name_vn
				 ,gbs.s_group_business_name_cn
		FROM mdl_partner.tb_listpartners ls
		INNER JOIN mdl_partner.tb_partner_business_services ps 
		ON ls.s_partner_id = ps.s_partner_id
		INNER JOIN mdl_core.tb_group_business gbs
		ON ls.s_group_business_id = gbs.s_group_business_id
		WHERE ps.n_is_activated = 1
		AND ls.s_group_business_id = (CASE groupbusinessid WHEN '' THEN  ls.s_group_business_id ELSE groupbusinessid END )
		AND ps.s_business_service_id = (CASE businessserviceid WHEN '' THEN  ps.s_business_service_id ELSE businessserviceid END )
		AND ls.s_business_type_id = (CASE businesstypeid WHEN '' THEN ls.s_business_type_id ELSE  businesstypeid END)
		AND ls.n_is_approved = (CASE isapprove WHEN -1 THEN ls.n_is_approved ELSE isapprove END);
	ELSE
		return query
		SELECT ls.n_id
				 ,ls.s_partner_id
				 ,ls.s_partner_name_en
				 ,ls.s_partner_name_vn
				 ,ls.s_partner_name_cn
				 ,ls.s_business_phone
				 ,ls.s_business_email
				 ,ls.s_address_no
				 ,ls.s_street
				 --,ls.s_company_logo
			    ,('partner/getImage/' || ls.s_partner_id) s_company_logo
				 ,ls.s_taxcode
				 ,ls.s_owner_name
				 ,ls.d_registration_date
				 ,ls.s_opening_time
				 ,ls.s_close_time
				 ,ls.s_country_id
				 ,ls.s_city_id
				 ,ls.s_group_business_id
				 ,ls.s_business_type_id
				 ,ls.s_created_by
				 ,ls.s_partner_desc_vn
				 ,ls.s_partner_desc_en
				 ,ls.s_partner_desc_cn
				 ,ls.n_partner_rating
				 ,ls.s_full_address
				 ,ls.n_is_approved
				 ,ls.s_company_type_id
				 ,ls.s_phone_contact
				 ,gbs.s_group_business_name_en
				 ,gbs.s_group_business_name_vn
				 ,gbs.s_group_business_name_cn
		FROM mdl_partner.tb_listpartners ls
		INNER JOIN mdl_partner.tb_partner_account ac ON ac.s_partner_id = ls.s_partner_id
		--INNER JOIN mdl_partner.tb_partner_business_services ps
		--ON ls.s_partner_id = ps.s_partner_id
		INNER JOIN mdl_core.tb_group_business gbs
		ON ls.s_group_business_id = gbs.s_group_business_id
		WHERE	ac.n_is_approved = 1
		AND ac.s_user_id = (CASE UserId WHEN '' THEN  ac.s_user_id ELSE userid END )
		AND ls.s_group_business_id = (CASE groupbusinessid WHEN '' THEN  ls.s_group_business_id ELSE groupbusinessid END )
		--AND ps.s_business_service_id = (CASE businessserviceid WHEN '' THEN  ps.s_business_service_id ELSE businessserviceid END )
		AND ls.s_business_type_id = (CASE businesstypeid WHEN '' THEN ls.s_business_type_id ELSE  businesstypeid END)
		AND ls.n_is_approved = (CASE isapprove WHEN -1 THEN ls.n_is_approved ELSE isapprove END);
	END IF;
END;
$$  LANGUAGE plpgsql;