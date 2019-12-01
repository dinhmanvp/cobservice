package cob.com.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.communication.entities.TbTokenUser;

public interface TbTokenUserRepository extends JpaRepository<TbTokenUser, Integer>{
	@Query("SELECT t from TbTokenUser t WHERE LOWER(t.sUsername) = LOWER(:username)")
	public TbTokenUser findByUsername(@Param("username") String username);

}
