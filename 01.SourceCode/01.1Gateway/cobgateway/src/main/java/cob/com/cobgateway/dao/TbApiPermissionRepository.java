package cob.com.cobgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobgateway.entities.TbApiPermission;

public interface TbApiPermissionRepository extends JpaRepository<TbApiPermission, Integer>{
	@Query("SELECT t FROM TbApiPermission t WHERE t.sApi = :url")
	TbApiPermission findByName(@Param("url")String url);

}
