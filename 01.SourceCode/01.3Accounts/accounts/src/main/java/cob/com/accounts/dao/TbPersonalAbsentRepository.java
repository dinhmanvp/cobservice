package cob.com.accounts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.accounts.entities.TbPersonalAbsent;


public interface TbPersonalAbsentRepository extends JpaRepository<TbPersonalAbsent, Integer> {

	@Query("SELECT t FROM TbPersonalAbsent t WHERE t.sPersonalAbsentId =:sPersonalAbsentId" )
	public TbPersonalAbsent GetPerbyid(@Param("sPersonalAbsentId") String sPersonalAbsentId);
	
	@Query("SELECT t FROM TbPersonalAbsent t WHERE t.sMyBusinessId =:sMyBusinessId" )
	public List<TbPersonalAbsent> GetPerbuid(@Param("sMyBusinessId") String sMyBusinessId);
}
