package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbContractPolicyType;

public interface TbContractPolicyTypeRepository extends JpaRepository<TbContractPolicyType, Integer>{
	@Query("SELECT t FROM TbContractPolicyType t WHERE t.sContractPolicyTypeId = :id")
	public TbContractPolicyType findByContractPolicyTypeId(@Param("id") String id);
}
