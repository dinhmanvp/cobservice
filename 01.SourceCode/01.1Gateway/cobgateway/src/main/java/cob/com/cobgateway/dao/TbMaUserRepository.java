package cob.com.cobgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobgateway.entities.TbMaUserAuthentication;

public interface TbMaUserRepository extends JpaRepository<TbMaUserAuthentication, Integer> {
	@Query(value = "SELECT a FROM TbMaUserAuthentication a WHERE LOWER(a.sUsername) = LOWER(:username) and a.sChannelId = :channelId")
	TbMaUserAuthentication getTbMaUserAuthenticationByUserIdAndChannelId(@Param("username") String username,
			@Param("channelId") String channelId);
}
