package cob.com.cobgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobgateway.entities.TbUser;

public interface TbUserRepository extends JpaRepository<TbUser, Integer>{
	@Query(value="Select t.sUserId FROM TbUser t WHERE t.sUsername = :username")
	String getUserIdByUsername(@Param("username") String username);
}
