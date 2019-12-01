package cob.com.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cob.com.finance.entities.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
	@Query("SELECT t FROM TbUser t WHERE t.sUserId = :sUserId")
	public TbUser getUserByUserId(@Param("sUserId") String sUserId);
}
