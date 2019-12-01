DELIMITER //
CREATE OR REPLACE FUNCTION mdl_business.likedeal(dealId TEXT, userIdLike TEXT)
RETURNs INTEGER AS
$$
DECLARE i BIGINT := (SELECT COUNT (l.n_id) 
							FROM mdl_business.tb_mydeal_like l 
							WHERE l.s_mydeal_like_id = dealId 
							AND l.s_user_id = userIdLike);
BEGIN
IF (i > 0)
THEN
	DELETE  FROM mdl_business.tb_mydeal_like l WHERE l.s_mydeal_like_id = dealId AND l.s_user_id = userIdLike;
	RETURN 0;
ELSE 
	INSERT INTO mdl_business.tb_mydeal_like(s_mydeal_like_id, s_user_id, d_like_date) VALUES (dealId, userIdLike, NOW());
	RETURN 1;
END IF;
END
$$
LANGUAGE plpgsql;

SELECT * FROM mdl_business.likedeal('1571218548358','7b8e5acb6806c0fa76fc3d78ede096e2adbc07e1778d23b55f3a364f615e66ad')