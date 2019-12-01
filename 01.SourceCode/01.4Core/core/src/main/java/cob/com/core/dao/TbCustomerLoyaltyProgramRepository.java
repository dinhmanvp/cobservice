package cob.com.core.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cob.com.core.entities.TbCustomerLoyaltyProgram;

public interface TbCustomerLoyaltyProgramRepository extends JpaRepository<TbCustomerLoyaltyProgram, Integer> {
	@Query("Select t from TbCustomerLoyaltyProgram t where t.sProgramId = :id and t.sProgramType and t.dFromDate >= :currentDate and t.dToDate >= :currentDate")
	TbCustomerLoyaltyProgram getByIdandType(String id, String type, Date currentDate);

}
