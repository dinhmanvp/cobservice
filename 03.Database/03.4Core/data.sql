CREATE TABLE mdl_core.tb_contract_policy_type_detail
(
	n_id SERIAL NOT NULL,
	s_contract_policy_type_detail_id CHARACTER(15) NOT NULL UNIQUE,
	s_contract_policy_type_id CHARACTER VARYING(15) NOT NULL,
	n_count_from INT,
	n_count_to  INT,getgroupbusinesscategetgroupbusinesscate
	m_price NUMERIC,
	PRIMARY KEY(n_id)
);

CREATE TABLE mdl_core.tb_contract_policy_type
(
	n_id SERIAL NOT NULL,
	s_contract_policy_type_id CHARACTER NOT NULL UNIQUE ,
	s_policy_type_name_vn VARCHAR(3000),
	s_policy_type_name_en VARCHAR(3000),
	s_policy_type_name_cn VARCHAR(3000),
	n_is_montlyfee_applied INT,
	m_from_amount NUMERIC,
	m_to_amount NUMERIC,
	n_is_charge_in_transaction INT ,
	PRIMARY KEY(n_id)
);

CREATE TABLE mdl_partner.tb_partner_contract
(
	n_id SERIAL NOT NULL,
	s_contract_number CHARACTER VARYING(15) NOT NULL UNIQUE,
	partner_id CHARACTER VARYING(15) NOT NULL,
	s_saleman_id CHARACTER VARYING(15) NOT NULL,
	s_contract_policy_type_id CHARACTER VARYING(15) NOT NULL,
	d_from_date DATE,
	d_to_date  DATE,
	n_contract_duration INT,
	s_contract_unit VARCHAR(150),
	n_total INT,
	s_partner_signature VARCHAR(3000),
	s_percent_charge_per_txn VARCHAR(3000),
	s_bussiness_service_id CHARACTER VARYING(15) NOT NULL,
	PRIMARY KEY(n_id)
);

CREATE TABLE mdl_partner.tb_partner_workingtime
(
	n_id SERIAL NOT NULL,
	s_partner_working_id CHARACTER VARYING(15) NOT NULL UNIQUE,
	partner_id CHARACTER VARYING(15) NOT NULL,
	n_duration_for_session INT ,
	n_count_per_day INT ,
	s_Mo_From   VARCHAR(150),
	s_Mo_To     VARCHAR(150),
	s_Mo_isOFF  VARCHAR(150),
	s_Tu_From   VARCHAR(150),
	s_Tu_To     VARCHAR(150),
	s_Tu_isOFF  VARCHAR(150),
	s_We_From   VARCHAR(150),
	s_We_To     VARCHAR(150),
	s_We_isOFF  VARCHAR(150),
	s_Th_From   VARCHAR(150),
	s_Th_To     VARCHAR(150),
	s_Th_isOFF  VARCHAR(150),
	s_Fr_From   VARCHAR(150),
	s_Fr_To     VARCHAR(150),
	s_Fr_isOFF  VARCHAR(150),
	s_Sa_From   VARCHAR(150),
	s_Sa_To     VARCHAR(150),
	s_Sa_isOFF  VARCHAR(150),
	s_Su_From   VARCHAR(150),
	s_Su_To     VARCHAR(150),
	s_Su_isOFF  VARCHAR(150),
	PRIMARY KEY(n_id)
);


CREATE OR REPLACE FUNCTION mdl_core.getcontractpolicytypedetail(contractPolicyTypeId CHARACTER VARYING(15)) 
RETURNS SETOF mdl_core.tb_contract_policy_type_detail
AS 
$$
		SELECT  *
		FROM mdl_core.tb_contract_policy_type_detail WHERE mdl_core.tb_contract_policy_type_detail.s_contract_policy_type_id = contractPolicyTypeId
		ORDER BY m_price asc
$$
LANGUAGE sql;



CREATE OR REPLACE FUNCTION mdl_core.getGroupBusinessCate(groupBusinessId CHARACTER VARYING(15)) 
RETURNS SETOF mdl_core.tb_groupbusiness_cate
AS 
$$
		SELECT  *
		FROM mdl_core.tb_groupbusiness_cate WHERE mdl_core.tb_groupbusiness_cate.s_group_business_id= groupBusinessId
		
$$
LANGUAGE sql;








