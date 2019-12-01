package cob.com.cobgateway.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cob.com.cobgateway.dao.TbApiPermissionRepository;
import cob.com.cobgateway.dao.TbChannelRepository;
import cob.com.cobgateway.dao.TbMaUserRepository;
import cob.com.cobgateway.dao.TbRankIpCountryRepository;
import cob.com.cobgateway.dao.TbUserAccessLogRepository;
import cob.com.cobgateway.entities.TbApiPermission;
import cob.com.cobgateway.entities.TbMaUserAuthentication;
import cob.com.cobgateway.entities.TbRankIpCountry;
import cob.com.cobgateway.entities.TbUserAccessLog;
import cob.com.cobgateway.intercom.UserClient;
import cob.com.gateway.models.ResponseMessage;

@Component
public class GatewayServiceImpl implements GatewayService {
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private TbMaUserRepository tbMaUserRepository;

	@Autowired
	private TbChannelRepository tbChannelRepository;

	@Autowired
	private TbRankIpCountryRepository tbRankIpCountryRepository;

	@Autowired
	private TbUserAccessLogRepository tbUserAccessLogRepository;

	@Autowired
	private TbApiPermissionRepository tbApiPermissionRepository;

	@Override
	public ResponseMessage checkLogin(String user, String pwd) {
		return userClient.checkLogin(user, pwd);
	}

	public ResponseMessage checkTest() {
		return userClient.checkTest();
	}

	@Override
	public Boolean addOrUpdate(TbMaUserAuthentication entity) {
		// String userId = getUserIdByUsername(username);
		TbMaUserAuthentication existedEntity = tbMaUserRepository
				.getTbMaUserAuthenticationByUserIdAndChannelId(entity.getSUserId(), entity.getsChannelId());
		if (existedEntity != null) {
			existedEntity.setDCreatedDate(new Date(System.currentTimeMillis()));
			existedEntity.setSPrivateToken(entity.getSPrivateToken());
			existedEntity.setSPublicToken(entity.getSPublicToken());
			existedEntity.setBiIsExpired(entity.getBiIsExpired());
			return tbMaUserRepository.save(existedEntity) != null;
		}
		return tbMaUserRepository.save(entity) != null;
	}

	@Override
	public TbMaUserAuthentication getTbMaUserAuthenticationByUserIdAndChannelId(String userId, String channelId) {
		TbMaUserAuthentication entity = tbMaUserRepository.getTbMaUserAuthenticationByUserIdAndChannelId(userId,
				channelId);

		return entity;
	}

	@Override
	public Integer getExpireTimeByChannel(String channelId) {
		Integer timeOut = tbChannelRepository.getTimeOutByChannelId(channelId);
		return timeOut != null ? Integer.valueOf(timeOut) : 0;
	}

//	@Override
//	public String getUserIdByUsername(String username) {
//
//		return tbUserRepository.getUserIdByUsername(username);
//	}

	@Override
	public Integer checkIpIsAllow(String ip) {
		List<TbRankIpCountry> ranks = new ArrayList<TbRankIpCountry>();
		ranks = tbRankIpCountryRepository.findAll();
		for (TbRankIpCountry tbRankIpCountry : ranks) {
			String ipRank = tbRankIpCountry.getSCountryIpBlocks();
			String[] blocks = ipRank.split("-");
			String[] froms = blocks[0].split("\\.");
			String[] tos = blocks[1].split("\\.");
			String[] ips = ip.split("\\.");
			for (int i = 0; i < 4; i++) {
				if (Integer.valueOf(froms[i]) <= Integer.valueOf(ips[i])
						&& Integer.valueOf(ips[i]) <= Integer.valueOf(tos[i])) {
					if (i != 3) {
						continue;
					} else if (i == 3) {
						return tbRankIpCountry.getBIsAllowed();
					}

				} else {
					break;
				}
			}
		}
		return 0;
	}

	@Override
	public Boolean addUserAccessLog(TbUserAccessLog userAccessLog) {
		TbUserAccessLog log = tbUserAccessLogRepository.save(userAccessLog);
		return null;
	}

	@Override
	public Integer checkApiRequirePermission(String url) {
		TbApiPermission entity = tbApiPermissionRepository.findByName(url);
		if (entity != null) {
			return entity.getNPermissionRequire();
		} else {
			return -1;
		}
	}

}
