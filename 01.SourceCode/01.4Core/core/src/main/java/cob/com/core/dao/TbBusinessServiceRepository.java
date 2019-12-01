package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbBusinessService;

public interface TbBusinessServiceRepository extends JpaRepository<TbBusinessService,Integer>{
	@Query("SELECT t FROM TbBusinessService t WHERE t.sBusinessServiceId = :id")
	public TbBusinessService findByBusinessServiceId(@Param("id") String id);
}
