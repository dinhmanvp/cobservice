/*

select * from mdl_business.getProduct('123','3','')
select * from mdl_business.getProduct('','','')
*/

CREATE OR REPLACE FUNCTION mdl_business.getProduct
(
	partnerId VARCHAR(50),
	userId VARCHAR(150),
	productId VARCHAR(50)
)
RETURNS SETOF mdl_business.tb_myproduct AS 
$$
	SELECT t FROM mdl_business.tb_myproduct t WHERE 
	(partnerId = '' AND userId = '' AND productId = '') OR -- tất cả để là null
	(partnerId = '' AND userId = '' AND productId <> '' AND t.s_product_id =  productId) OR  -- search theo ProductId
	(partnerId = '' AND userId <> '' AND productId = '' AND t.s_user_id = userId) OR -- search theo userid 
	(partnerId = '' AND userId <> '' AND productId <> '' AND t.s_user_id = userId AND t.s_product_id =  productId) OR -- search theo userid va productid 
	(partnerId <> '' AND userId = '' AND productId = '' AND t.s_partner_id = partnerId) OR -- search theo partner id 
	(partnerId <> '' AND userId = '' AND productId <> '' and t.s_partner_id = partnerId AND t.s_product_id =  productId) OR -- search theo partnerid va productid 
	(partnerId <> '' AND userId <> '' AND productId = '' AND t.s_partner_id = partnerId AND t.s_user_id = userId) OR -- search theo partnerid va userid
	(partnerId <> '' AND userId <> '' AND productId <> '' AND t.s_partner_id = partnerId AND t.s_product_id =  productId AND t.s_user_id = userId)
$$
LANGUAGE sql;

