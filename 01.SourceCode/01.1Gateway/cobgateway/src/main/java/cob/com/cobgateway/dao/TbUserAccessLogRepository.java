package cob.com.cobgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cob.com.cobgateway.entities.TbUserAccessLog;

public interface TbUserAccessLogRepository extends JpaRepository<TbUserAccessLog, Integer>{

}
