package cob.com.accounts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cob.com.accounts.entities.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
	// thupa compare lower case
	@Query("SELECT t FROM TbUser t WHERE LOWER(t.sUsername) = LOWER(:username)")
	public TbUser getUserByUserName(@Param("username") String username);
	
	@Query("SELECT t.sCustomerId FROM TbUser t WHERE t.sPhone =:sPhone")
	public String GetUserIdbyphone(@Param("sPhone") String sPhone);
	// @Query("SELECT t FROM TbUser t WHERE t.s_user_id = :sUserId")
	// public TbUser GetUsersssss(@Param("sUserId")String sUserId);
	
}
