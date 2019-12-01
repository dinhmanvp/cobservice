package cob.com.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbBusinessType;

public interface TbBusinessTypeRepository extends JpaRepository<TbBusinessType, Integer>{
	@Query("SELECT t FROM TbBusinessType t WHERE t.sBusinessTypeId = :id ORDER BY nOrder")
	public TbBusinessType findByBusinessTypeId(@Param("id") String id);
	
	@Query("SELECT t FROM TbBusinessType t WHERE t.sBusinessGroupId = :id ORDER BY nOrder")
	public List<TbBusinessType> findByGroupBusinessId(@Param("id") String id);
}
