package cob.com.cobgateway.config;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.cobgateway.entities.TbMaUserAuthentication;
import cob.com.cobgateway.entities.TbUserAccessLog;
import cob.com.cobgateway.service.GatewayService;
import cob.com.cobgateway.utils.DateUtility;
import cob.com.gateway.models.ResponseMessage;
import cob.com.gateway.params.Parameter;
import io.jsonwebtoken.ExpiredJwtException;

@SuppressWarnings("all")
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	// @Autowired
	// private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private GatewayService gatewayService;
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	private DateUtility dateUtility;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String req_uri = req.getRequestURL().toString();
		String username = req.getHeader(Parameter.USER_NAME);
		String password = req.getHeader(Parameter.PASSWORD);
		String authToken = req.getHeader(Parameter.AUTH_TOKEN);
		String channelId = req.getHeader(Parameter.CHANNEL_ID);
		Boolean isVerified = false;
		Boolean isValidIp = false;
		
		// Date current = new Date();
		// String userI = "";
		String resultAccess = "";
		String clientIP = req.getHeader("X-FORWARDED-FOR");
		// String clientIP = "";
		if (clientIP == null) {
			clientIP = req.getRemoteAddr();
		}
		isValidIp = gatewayService.checkIpIsAllow(clientIP) == 1 ? false : true;
		log.info("clientIP:" + clientIP );
		log.info("URI:" + req_uri );
		if(isValidIp) {
			if (req_uri.matches(Parameter.LOGIN_URL_REGEX) ) {
				Integer expireIn = gatewayService.getExpireTimeByChannel(channelId);
				
				if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(channelId) && expireIn > 0) {
					
					ResponseMessage rm = gatewayService.checkLogin(username, password);
					log.info("ResponseMessage:" + rm.toString() );
					if (rm.getResponseCode().equals(Parameter.LOGIN_SUCCESS_CODE)) {
						Gson gson = new Gson();
						JsonElement jElement = gson.toJsonTree(rm.getResponseData()).getAsJsonObject();
						JsonObject jObject = jElement.getAsJsonObject();
						username = jObject.get("susername").getAsString();
						isVerified = true;
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								username, null, null);
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						SecurityContextHolder.getContext().setAuthentication(authentication);
						Object resposneData = rm.getResponseData();						
						String publicTokden = jwtTokenUtil.doGenerateToken(username);
						TbMaUserAuthentication entity = new TbMaUserAuthentication();
						entity.setSUserId(username);
						entity.setSPublicToken(publicTokden);
						entity.setSPrivateToken("");
						entity.setDCreatedDate(new Date(System.currentTimeMillis()));
						entity.setBiIsExpired(expireIn);
						entity.setsChannelId(channelId);
						boolean isAdded = gatewayService.addOrUpdate(entity);
						if (isAdded) {
							res.addHeader(Parameter.TOKEN_RESPONSE, entity.getSPublicToken());
						}
					} else {
						resultAccess = Parameter.PARAM_HEADER_WRONG;
						log.info("resultAccess :Sai thong tin dang nhap o header");
					}
				} else { 
					resultAccess = Parameter.HEADER_INFO_IS_LACKED_MSG_LOGIN;
					log.info("resultAccess :Header thieu username,password,hoac channelId");
				}
			}else {
				Integer p = req_uri.indexOf("/api");
				String api = req_uri.substring(p);
				Integer permissionRequire = gatewayService.checkApiRequirePermission(api);
				if(api.contains("getImage"))
					permissionRequire = 0;
				if (permissionRequire == 0) {//api luu trong database va permission require = 0 => khong yeu cau quyen truy cap
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
							null, null);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}else {
					//Boolean isValidIp = false;
					//isValidIp = gatewayService.checkIpIsAllow(clientIP) == 1 ? false : true;//tồn tại trong database và bằng 1 -> bị chặn
					Boolean isValidToken = false;
					String userToken = jwtTokenUtil.getUsernameFromPublicToken(authToken);
					if (username.toLowerCase().equals(userToken.toLowerCase())) {
						isVerified = true;
						TbMaUserAuthentication entity = gatewayService
								.getTbMaUserAuthenticationByUserIdAndChannelId(username, channelId);
						if (entity != null) {
							Date expireDate = dateUtility.AddMinute(entity.getDCreatedDate(), entity.getBiIsExpired());
							Date current = new Date(System.currentTimeMillis());
							if (current.after(expireDate) || !entity.getSPublicToken().equals(authToken)) {
								isValidToken = false;
							} else {
								isValidToken = true;
								// refresh token								
								entity.setDCreatedDate(current);
								gatewayService.addOrUpdate(entity);								
							}
						} else {
							resultAccess = Parameter.USER_HAS_NOT_AUTHEN;
						}
					}
					if (isValidIp && isValidToken) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								username, null, null);
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
					else if (resultAccess.equals("") && !isValidToken) {
						resultAccess = Parameter.TOKEN_NOT_VALID;
					}
				}
			}
		}else {
			resultAccess = Parameter.IP_NOT_VALID;
		}				
		
		//res.setHeader(Parameter.RESPONSE_STATUS, resultAccess);
		TbUserAccessLog log = new TbUserAccessLog();
		log.setDAccessDate(new Date(System.currentTimeMillis()));
		log.setSChannel(channelId == null ? "" : channelId);
		log.setSIpAccess(clientIP);		
		log.setSFunctionId(req_uri);
		log.setSResult(resultAccess);
		
		if (isVerified) {
			log.setSUserId(username);
		}else {
			log.setSUserId("undefine");
		}
		gatewayService.addUserAccessLog(log);
		chain.doFilter(req, res);
	}
}