package cob.com.cobservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbStartupPackage;

public interface TbStartupPackageRepository extends JpaRepository<TbStartupPackage, Integer>{
	@Query("SELECT t FROM TbStartupPackage t WHERE t.sStartupPackageId = :id")
	public TbStartupPackage findById(@Param("id") String id);
	
	@Query("SELECT t FROM TbStartupPackage t WHERE t.sGroupBusinessId = :id and t.nIsAvailable = :isAvailable")
	public List<TbStartupPackage> findByGroupBusinessId(@Param("id") String groupBusinessId, @Param("isAvailable") String isAvailable);
}
