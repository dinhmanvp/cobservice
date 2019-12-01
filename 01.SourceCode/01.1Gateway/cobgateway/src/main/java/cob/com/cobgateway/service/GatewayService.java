package cob.com.cobgateway.service;

import cob.com.cobgateway.entities.TbMaUserAuthentication;
import cob.com.cobgateway.entities.TbUserAccessLog;
import cob.com.gateway.models.ResponseMessage;

public interface GatewayService {
	ResponseMessage checkLogin(String user, String pwd);
	ResponseMessage checkTest();
	Boolean addOrUpdate(TbMaUserAuthentication entity);
	TbMaUserAuthentication getTbMaUserAuthenticationByUserIdAndChannelId(String userId,String channelId);
	Integer getExpireTimeByChannel(String channelId);
//	String getUserIdByUsername(String username);
	Integer checkIpIsAllow(String ip);
	Boolean addUserAccessLog(TbUserAccessLog userAccessLog);
	Integer checkApiRequirePermission(String url);
}
