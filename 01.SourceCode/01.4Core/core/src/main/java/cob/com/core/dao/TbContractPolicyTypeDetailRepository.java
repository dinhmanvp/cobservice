package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbContractPolicyTypeDetail;

public interface TbContractPolicyTypeDetailRepository extends JpaRepository<TbContractPolicyTypeDetail, Integer> {
	@Query("SELECT t FROM TbContractPolicyTypeDetail t WHERE t.sContractPolicyTypeDetailId = :id")
	public TbContractPolicyTypeDetail findByContractPolicyTypeDetailId(@Param("id") String id);
}
