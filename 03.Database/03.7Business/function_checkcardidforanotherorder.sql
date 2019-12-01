CREATE OR REPLACE FUNCTION mdl_business.checkcardidforanotherorder
(
	Partnerid VARCHAR(50),
	cardId VARCHAR(50),
	appointDate DATE 
)
RETURNS BIGINT AS 
$$
	SELECT COUNT(*)
	FROM mdl_business.tb_order t
	WHERE s_seller_partner_id = Partnerid AND t.s_card_id_no = cardId
	AND t.d_appointment_date = appointDate AND t.n_order_status_id = 1
$$
LANGUAGE sql;

SELECT  mdl_business.checkcardidforanotherorder('2150','dsjfs','2019-11-19')