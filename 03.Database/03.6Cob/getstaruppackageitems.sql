CREATE OR REPLACE FUNCTION mdl_cob.getstaruppackageitems(startuppackageid TEXT, languegecode TEXT) 
RETURNS TABLE(
	n_id mdl_cob.tb_startup_package_items.n_id%TYPE,
	s_startup_package_items_id mdl_cob.tb_startup_package_items.s_startup_package_items_id%TYPE,
	s_advise_service_id mdl_cob.tb_startup_package_items.s_advise_service_id%TYPE,
	s_startup_package_id mdl_cob.tb_startup_package_items.s_startup_package_id%TYPE,
	s_service_unit_id mdl_cob.tb_startup_package_items.s_service_unit_id%TYPE,
	n_duration mdl_cob.tb_startup_package_items.n_duration%TYPE,
	s_duration_type mdl_cob.tb_startup_package_items.s_duration_type%TYPE,
	d_from_date mdl_cob.tb_startup_package_items.d_from_date%TYPE,
	d_to_date mdl_cob.tb_startup_package_items.d_to_date%TYPE,
	s_advise_service_name mdl_cob.tb_advise_services.s_advise_service_name_vn%TYPE,
	s_advise_service_shortdesc mdl_cob.tb_advise_services.s_advise_service_shortdesc_vn%TYPE,
	s_unit_name mdl_cob.tb_service_unit.s_unit_name_vn%TYPE,
	s_advise_service_icon mdl_cob.tb_advise_services.s_advise_service_icon%TYPE,
	s_duration_type_name TEXT,
	n_rating NUMERIC(4,3)
)   
AS 
$$
	SELECT  packitem.*
			  ,CASE WHEN UPPER(languegecode) = 'VN' THEN adser.s_advise_service_name_vn
			  			WHEN UPPER(languegecode) = 'EN' THEN adser.s_advise_service_name_en
			  	 	   ELSE adser.s_advise_service_name_cn
			  	 END s_advise_service_name
			  ,CASE WHEN UPPER(languegecode) = 'VN' THEN adser.s_advise_service_shortdesc_vn
			  			WHEN UPPER(languegecode) = 'EN' THEN adser.s_advise_service_shortdesc_en
			  	 	   ELSE adser.s_advise_service_shortdesc_cn
			  	 END s_advise_service_shortdesc
			  ,CASE WHEN UPPER(languegecode) = 'VN' THEN serunit.s_unit_name_vn
			  			WHEN UPPER(languegecode) = 'EN' THEN serunit.s_unit_name_en
			  	 	   ELSE serunit.s_unit_name_cn
			  	 END s_unit_name,
			adser.s_advise_service_icon,
			CASE WHEN UPPER(languegecode) = 'VN' THEN dtype.s_duration_type_name_vn
					 WHEN UPPER(languegecode) = 'CN' THEN dtype.s_duration_type_name_cn
					 WHEN UPPER(languegecode) = 'EN' THEN dtype.s_duration_type_name_en
					 END s_duration_type_name,
					adser.n_advise_service_rating
	FROM mdl_cob.tb_startup_package_items packitem
	LEFT JOIN mdl_cob.tb_advise_services adser
	ON adser.s_advise_service_id = packitem.s_advise_service_id
	LEFT JOIN mdl_cob.tb_service_unit serunit
	ON serunit.s_service_unit_id = packitem.s_service_unit_id
	INNER JOIN mdl_cob.tb_duration_type dtype ON packitem.s_duration_type = dtype.s_duration_type_id
	WHERE packitem.s_startup_package_id = startuppackageid
	ORDER BY packitem.s_startup_package_items_id
$$
LANGUAGE sql;