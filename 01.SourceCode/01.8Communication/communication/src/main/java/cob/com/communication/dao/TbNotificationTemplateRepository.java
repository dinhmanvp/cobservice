package cob.com.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.communication.entities.TbNotificationTemplate;

public interface TbNotificationTemplateRepository extends JpaRepository<TbNotificationTemplate, Integer>{
	@Query("SELECT t FROM TbNotificationTemplate t WHERE t.sName = :templateName")
	public TbNotificationTemplate getByName(@Param("templateName") String templateName);
}
