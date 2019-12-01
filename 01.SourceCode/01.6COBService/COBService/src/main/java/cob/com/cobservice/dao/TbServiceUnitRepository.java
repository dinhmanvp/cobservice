package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbServiceUnit;

public interface TbServiceUnitRepository extends JpaRepository<TbServiceUnit,Integer>{
	@Query("SELECT t FROM TbServiceUnit t WHERE t.sServiceUnitId = :id")
	public TbServiceUnit findById(@Param("id") String id);
}
