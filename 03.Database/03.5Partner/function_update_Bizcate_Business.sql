/*
SELECT * FROM mdl_partner.updatepartnerbizcateisactive('1570507000498')
*/
CREATE OR REPLACE FUNCTION mdl_partner.updatepartnerbizcateisactive
(
	PartnerID VARCHAR(50)
)
RETURNS VOID AS 
'
	UPDATE mdl_partner.tb_partner_bizcate SET n_is_activated = 1 WHERE s_partner_id = PartnerID;
'
LANGUAGE sql;


/*                        updatepartnerbusiessserviceisactive
SELECT * FROM mdl_partner.updatepartnerbusiessserviceisactive('1570507000498')
*/
CREATE OR REPLACE FUNCTION mdl_partner.updatepartnerbusiessserviceisactive
(
	PartnerID VARCHAR(50)
)
RETURNS VOID AS 
'
	UPDATE mdl_partner.tb_partner_business_services SET n_is_activated = 1 WHERE s_partner_id = PartnerID;
'
LANGUAGE sql;