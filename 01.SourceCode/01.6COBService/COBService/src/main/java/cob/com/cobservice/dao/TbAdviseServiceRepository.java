package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbAdviseService;


public interface TbAdviseServiceRepository extends JpaRepository<TbAdviseService,Integer>{
	@Query("SELECT t FROM TbAdviseService t WHERE t.sAdviseServiceId = :id")
	public TbAdviseService findByAdviseServiceId(@Param("id") String id);	
}
