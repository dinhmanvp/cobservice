package cob.com.cobservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbStartupPackageItem;

public interface TbStartupPackageItemRepository extends JpaRepository<TbStartupPackageItem,Integer>{
	@Query("SELECT t FROM TbStartupPackageItem t WHERE t.sStartupPackageItemsId = :id")
	public TbStartupPackageItem findById(@Param("id") String id);
	
	@Query("SELECT t FROM TbStartupPackageItem t WHERE t.sStartupPackageId = :id")
	public List<TbStartupPackageItem> findBysStartupPackageId(@Param("id") String id);
}
