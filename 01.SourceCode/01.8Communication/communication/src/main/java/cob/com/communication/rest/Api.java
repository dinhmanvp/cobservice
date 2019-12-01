package cob.com.communication.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.communication.entities.TbCustomer;
import cob.com.communication.entities.TbNotificationTemplate;
import cob.com.communication.entities.TbSendEmailLog;
import cob.com.communication.entities.TbTokenUser;
import cob.com.communication.entities.TbUser;
import cob.com.communication.entities.TbVerifyOtp;
import cob.com.communication.entities.TbVerifyOtpPK;
import cob.com.communication.models.ResponseMessage;
import cob.com.communication.param.Parameter;
import cob.com.communication.services.Communication;
import cob.com.communication.utils.ConfigUtility;
import cob.com.communication.utils.StringUtility;

@RestController
public class Api {

	@Value("${notification.api.url.fcm}")
	private String apiUrlFcm;

	@Value("${notificatipn.sender-id}")
	private String senderId;

	@Value("${notification.auth.key.fcm}")
	private String authKeyFcm;
	@Autowired
	private Communication communication;

	@Autowired
	private ConfigUtility configUtil;

	@GetMapping("/test")
	public String test() {
		return "Ok";
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<ResponseMessage> sendEmail(@RequestBody Object object) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(object).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (!StringUtility.isEmpty(jObject.get(Parameter.USERNAME))
				&& !StringUtility.isEmpty(jObject.get(Parameter.TEMPLATE_NAME))) {

			String username = jObject.get(Parameter.USERNAME).getAsString();
			String template = jObject.get(Parameter.TEMPLATE_NAME).getAsString();

			TbUser u = communication.getUserByUsername(username);

			if (u != null) {
				TbCustomer customer = communication.getCustomerByCustomerId(u.getSCustomerId());
				JsonObject paramsObject = jObject.get(Parameter.PARAMS).getAsJsonObject();
				Map<String, String> paramsValue = new HashMap<String, String>();
				String otp = "";
				String transactionId = "";
				if (template.equals(Parameter.OTP_NOTIFY_TEMPLATE)) {
					if (StringUtils.isEmpty(jObject.get(Parameter.OTP_PARAMETER))) {
						otp = StringUtility.OTP(6);
					} else {
						otp = jObject.get(Parameter.OTP_PARAMETER).getAsString();
					}
					paramsValue.put(Parameter.OTP_PARAMETER, otp);
				}
				String[] paramsByTemplate = communication.getParamListByTemplate(template);
				String paramValueLog = "";
				if (paramsByTemplate != null) {
					paramsValue.put(Parameter.USERNAME, customer.getSLastname());
					for (int i = 0; i < paramsByTemplate.length; i++) {
						String key = paramsByTemplate[i].toString();
						String value = paramsObject.get(key).getAsString();
						if (key.equals(Parameter.TRANSACTION_ID)) {
							transactionId = value;
						}
						paramValueLog += key + ":" + value + "|";
						if (value.equals(null)) {
							break;
							// return
						}
						paramsValue.put(key, value);
					}
					try {
						Boolean isOk = communication.sendEmail(u.getSEmail(), template, paramsValue);
						if (isOk) {
							if (template.equals(Parameter.OTP_NOTIFY_TEMPLATE)) {
								TbVerifyOtp tbVerifyOtp = new TbVerifyOtp();
								TbVerifyOtpPK tbVerifyOtpPK = new TbVerifyOtpPK();
								tbVerifyOtpPK.setSUsername(username);
								tbVerifyOtpPK.setSTransactionId(transactionId);
								tbVerifyOtp.setId(tbVerifyOtpPK);
								tbVerifyOtp.setDCreatedDate(new Date(System.currentTimeMillis()));
								tbVerifyOtp.setNIsVerified(0);
								tbVerifyOtp.setSOtp(otp);
								TbVerifyOtp saved = communication.save(tbVerifyOtp);
							}
							// save log
							TbSendEmailLog log = new TbSendEmailLog();
							log.setDDateSend(new Date(System.currentTimeMillis()));
							log.setsTemplateName(template);
							log.setSUsername(username);
							log.setSParamsValue(paramValueLog);
							communication.saveLog(log);
							respone.getBody()
									.setResponseCode(configUtil.getProperty("communication.send-email.success.cod"));
							respone.getBody()
									.setResponseMessage(configUtil.getProperty("communication.send-email.success.msg"));
						} else {
							respone.getBody()
									.setResponseCode(configUtil.getProperty("communication.send-email.error.cod"));
							respone.getBody()
									.setResponseMessage(configUtil.getProperty("communication.send-email.error.msg"));
						}
					} catch (MessagingException e) {
						respone.getBody().setResponseCode(configUtil.getProperty("communication.send-email.error.cod"));
						respone.getBody()
								.setResponseMessage(configUtil.getProperty("communication.send-email.error.msg"));
						e.printStackTrace();
					}
				}
			}
		}
		return respone;
	}

	@PostMapping("/pushNotification")
	public ResponseEntity<ResponseMessage> pushNotification(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		String username = "";
		String transactionId = "";
		String otp = "";

		// validate
		if (!StringUtility.isEmpty(jObject.get(Parameter.NOTIFY_TEMPLATE_NAME))
				&& !StringUtility.isEmpty(jObject.get(Parameter.USERNAME))) {
			username = jObject.get(Parameter.USERNAME).getAsString();
			String notifyTemplate = jObject.get(Parameter.NOTIFY_TEMPLATE_NAME).getAsString();
			if (username != null && notifyTemplate != null) {
				String clientAppId = "";
				TbTokenUser tokenUser = communication.getByUsername(username);
				if (tokenUser != null) {
					clientAppId = tokenUser.getSToken();
					CloseableHttpClient client = HttpClients.createDefault();
					HttpPost httpPost = new HttpPost(apiUrlFcm);

					httpPost.setHeader("Content-type", "application/json");
					httpPost.setHeader("Authorization", "key=" + authKeyFcm);
					httpPost.setHeader("Sender", "id=" + senderId);
					TbNotificationTemplate template = communication.getByName(notifyTemplate);
					String notifyContent = "";
					if (template != null) {
						notifyContent = template.getSContent();
						Map<Object, Object> data = new HashMap<Object, Object>();
						data.put(Parameter.CLIENT_APP_ID, clientAppId);
						String[] params = template.getSParams().split("-");
						JsonObject paramsInput = jObject.get(Parameter.PARAMS).getAsJsonObject();
						if (notifyTemplate.equals(Parameter.OTP_NOTIFY_TEMPLATE)) {
							if (StringUtils.isEmpty(jObject.get(Parameter.OTP_PARAMETER))) {
								otp = StringUtility.OTP(6);
							} else {
								otp = jObject.get(Parameter.OTP_PARAMETER).getAsString();
							}
							data.put(Parameter.OTP_PARAMETER, otp);
						}
						data.put(Parameter.CLIENT_APP_ID, clientAppId);
						if (params.length > 0 && !params[0].equals("")) {
							// Map<Object,Object> data = new HashMap<Object, Object>();

							for (int i = 0; i < params.length; i++) {
								String key = params[i];

								if (!StringUtility.isEmpty(paramsInput.get(key))) {
									String value = paramsInput.get(key).getAsString();
									data.put(key, value);
									if (key.equals(Parameter.TRANSACTION_ID)) {
										transactionId = value;
									}
								} else {
									re.getBody()
											.setResponseMessage(configUtil.getProperty("communication.input.null.msg"));
									re.getBody()
											.setResponseCode(configUtil.getProperty("communication.input.null.cod"));
									return re;
								}
							}
						}
						StrSubstitutor sub = new StrSubstitutor(data);
						String resolvedString = sub.replace(notifyContent);
						try {
							StringEntity entity = new StringEntity(resolvedString);
							entity.setContentType("application/json");
							httpPost.setEntity(entity);

							re.getBody().setResponseMessage(
									configUtil.getProperty("communication.send-notify.success.msg"));
							re.getBody()
									.setResponseCode(configUtil.getProperty("communication.send-notify.success.cod"));
							// save otp
							if (!username.equals("") && !transactionId.equals("") && !otp.equals("")) {
								TbVerifyOtp tbVerifyOtp = new TbVerifyOtp();
								TbVerifyOtpPK tbVerifyOtpPK = new TbVerifyOtpPK();
								tbVerifyOtpPK.setSUsername(tokenUser.getSUsername());
								tbVerifyOtpPK.setSTransactionId(transactionId);
								tbVerifyOtp.setId(tbVerifyOtpPK);
								tbVerifyOtp.setDCreatedDate(new Date(System.currentTimeMillis()));
								tbVerifyOtp.setNIsVerified(0);
								tbVerifyOtp.setSOtp(otp);
								CloseableHttpResponse response = client.execute(httpPost);
								client.close();
								try {
									TbVerifyOtp saved = communication.save(tbVerifyOtp);
								} catch (Exception e) {
									re.getBody().setResponseCode(
											configUtil.getProperty("communication.save-otp-transaction.error.cod"));
									re.getBody().setResponseMessage(
											configUtil.getProperty("communication.save-otp-transaction.error.msg"));
								}

							}
						} catch (UnsupportedEncodingException e) {
							re.getBody()
									.setResponseMessage(configUtil.getProperty("communication.send-notify.error.msg"));
							re.getBody().setResponseCode(configUtil.getProperty("communication.send-notify.error.cod"));
							e.printStackTrace();
						} catch (ClientProtocolException e) {
							re.getBody()
									.setResponseMessage(configUtil.getProperty("communication.send-notify.error.msg"));
							re.getBody().setResponseCode(configUtil.getProperty("communication.send-notify.error.cod"));
							e.printStackTrace();
						} catch (IOException e) {
							re.getBody()
									.setResponseMessage(configUtil.getProperty("communication.send-notify.error.msg"));
							re.getBody().setResponseCode(configUtil.getProperty("communication.send-notify.error.cod"));
							e.printStackTrace();
						}
					} else {
						re.getBody()
								.setResponseMessage(configUtil.getProperty("communication.temlate-notexist.error.msg"));
						re.getBody()
								.setResponseCode(configUtil.getProperty("communication.temlate-notexist.error.cod"));
					}

//				    String notifyContent = "{\"to\":\""
//			    			 + clientAppId
//			    			 + "\",\"priority\": \"high\""
//			    			 + ",\"notification\":"
//			    			 	+ "{"
//			    			 	+ "\"title\":\"Manld test\""
//			    			 	+ ",\"body\": \"Hello word\""
//			    			 	+ "} "
//			    			 + "}";
//				    
				}
			}
		}
		return re;

	}

	@PostMapping("/saveClientAppTokenId")
	public ResponseEntity<ResponseMessage> saveClientAppTokenId(@RequestBody Object object) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(object).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (!StringUtility.isEmpty(jObject.get(Parameter.USERNAME))
				&& !StringUtility.isEmpty(jObject.get(Parameter.CLIENT_APP_ID))) {
			String username = jObject.get(Parameter.USERNAME).getAsString();
			String userToken = jObject.get(Parameter.CLIENT_APP_ID).getAsString();
			if (username != null && userToken != null) {
				// save to database
				Boolean isSucceed = communication.addOrUpdateClientAppTokenId(username, userToken);
				if (isSucceed) {
					re.getBody().setResponseMessage(configUtil.getProperty("communication.save-token.success.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.save-token.success.cod"));
				} else {
					re.getBody().setResponseMessage(configUtil.getProperty("communication.save-token.error.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.save-token.error.cod"));
				}
			}
		} else {
			re.getBody().setResponseMessage(configUtil.getProperty("communication.input.null.msg"));
			re.getBody().setResponseCode(configUtil.getProperty("communication.input.null.cod"));
		}

		return re;
	}

	@PostMapping("/verifiedOTP")
	public ResponseEntity<ResponseMessage> verifiedOTP(@RequestBody Object object) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(object).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (!StringUtility.isEmpty(jObject.get(Parameter.TRANSACTION_ID))
				&& !StringUtility.isEmpty(jObject.get(Parameter.USERNAME))
				&& !StringUtility.isEmpty(jObject.get(Parameter.OTP_PARAMETER))) {
			String transactionId = jObject.get(Parameter.TRANSACTION_ID).getAsString();
			String username = jObject.get(Parameter.USERNAME).getAsString();
			String otp = jObject.get(Parameter.OTP_PARAMETER).getAsString();
			if (transactionId != null && username != null && otp != null) {
				Integer verifyOtp = communication.verifyOtp(transactionId, username, otp);
				switch (verifyOtp) {
				case 0:
					re.getBody().setResponseMessage(configUtil.getProperty("communication.verified-otp.null.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.verified-otp.null.cod"));
					break;
				case 1:
					re.getBody().setResponseMessage(configUtil.getProperty("communication.verified-otp.success.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.verified-otp.success.cod"));
					break;
				case 2:
					re.getBody().setResponseMessage(configUtil.getProperty("communication.verified-otp.error.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.verified-otp.error.cod"));
					break;
				case 3:
					re.getBody().setResponseMessage(configUtil.getProperty("communication.verified-otp.sys-error.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.verified-otp.sys-error.cod"));
					break;
				case 4:
					re.getBody().setResponseMessage(configUtil.getProperty("communication.verified-otp.expired.msg"));
					re.getBody().setResponseCode(configUtil.getProperty("communication.verified-otp.expired.cod"));
					break;
				}				
			}
		} else {
			re.getBody().setResponseMessage(configUtil.getProperty("communication.input.null.msg"));
			re.getBody().setResponseCode(configUtil.getProperty("communication.input.null.cod"));
		}

		return re;
	}
}
