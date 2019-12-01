package cob.com.accounts.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.accounts.entities.TbCustomerLoyaltyProgram;

public interface TbCustomerLoyaltyProgramRepository extends JpaRepository<TbCustomerLoyaltyProgram,Integer>{
	@Query("select t from TbCustomerLoyaltyProgram t where t.sProgramId = :id and t.sProgramType = :typpe and t.dFromDate <= :date and t.dToDate >= :date ")
	TbCustomerLoyaltyProgram getByIdandType(@Param("id")String id,@Param("type") String type,@Param("date") Date date);
}
