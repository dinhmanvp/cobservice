package cob.com.cobgateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobgateway.entities.TbChannel;

public interface TbChannelRepository extends JpaRepository<TbChannel, String>{
	@Query(value = "SELECT b.timeOut FROM TbChannel b WHERE b.channelId = :channelId")
	Integer getTimeOutByChannelId(@Param("channelId")String channelId);
}
