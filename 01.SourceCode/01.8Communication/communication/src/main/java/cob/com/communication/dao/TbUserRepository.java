package cob.com.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cob.com.communication.entities.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
	// thupa compare lower case
	@Query("SELECT t FROM TbUser t WHERE LOWER(t.sUsername) = LOWER(:username)")
	public TbUser getUserByUserName(@Param("username") String username);
}
