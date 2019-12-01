package cob.com.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.communication.entities.TbTemplate;

public interface TbTemplateRepository extends JpaRepository<TbTemplate, Integer>{
	@Query("SELECT t from TbTemplate t WHERE t.sName = :template")
	TbTemplate getTemplateByName(@Param("template") String template);

}
