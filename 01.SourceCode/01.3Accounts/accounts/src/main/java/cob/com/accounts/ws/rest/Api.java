package cob.com.accounts.ws.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.accounts.entities.TbCustomer;
import cob.com.accounts.entities.TbCustomerLoyaltyProgram;
import cob.com.accounts.entities.TbMyBusiness;
import cob.com.accounts.entities.TbMybusinessWorkingtime;
import cob.com.accounts.entities.TbMypocket;
import cob.com.accounts.entities.TbPersonalAbsent;
import cob.com.accounts.entities.TbUser;
import cob.com.accounts.services.Accounts;
import cob.com.accounts.utils.ConfigUtility;
import cob.com.accounts.utils.StringUtility;
import cob.com.accounts.ws.models.AccountInfo;
import cob.com.accounts.ws.models.ResponseMessage;
import cob.com.accounts.ws.param.Parameter;
import cob.com.accounts.ws.validate.ValidateInput;

//import cob.com.core.ws.param.Parameter;
/*
 * @author ldman 2019/07/06 REST API
 */
@RestController
public class Api {

	@Autowired
	private ConfigUtility configUtil;

	@Autowired
	private Accounts accounts;

	private void sendEmail(String templateName, String userName, Map<Object, Object> params) {
		Map<Object, Object> object = new HashMap<Object, Object>();
		object.put(Parameter.USERNAME, userName);
		object.put(Parameter.TEMPLATE_NAME, templateName);
		object.put(Parameter.PARAMS, params);
		accounts.sendEmail(object);

	}

	@GetMapping("/getImage/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable String id) {

		String imageData = accounts.getImage(id);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
				.body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
	}
	
	private void sendNotify(String templateName, String username, Map<Object, Object> params) {
		Map<Object, Object> object = new HashMap<Object, Object>();
		object.put(Parameter.USERNAME, username);
		object.put(Parameter.TEMPLATE_NAME, templateName);
		object.put(Parameter.PARAMS, params);
		accounts.sendNotify(object);
	}

	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> testApi() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		String transactionTypeId = Parameter.TRANSFER_TO_USER_TYPE;
		String toUserId = "1b67027faa2575c466a129ed53e7d122fe19f1db1e91379c5f06b7efc42a4e73";
		BigDecimal amountTransfer = new BigDecimal("5").setScale(2);
		String channelId = Parameter.MOBILE_CHANNEL;
		String fromUserId = Parameter.COB_USER_ID;
		String transMessage = "tặng đó";
		Map<String,Object> data = new HashMap<String,Object>();
		data.put(Parameter.TRANSACTION_TYPE_ID, transactionTypeId);
		data.put(Parameter.TO_USER_ID, toUserId);
		data.put(Parameter.AMOUNT, amountTransfer);
		data.put(Parameter.CHANNEL_ID, channelId);
		data.put(Parameter.FROM_USER_ID, fromUserId);
		data.put(Parameter.TRANS_MESSAGE, transMessage);
		Object resutl = accounts.transferCobCoin(data);
		//System.out.println(resutl);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData(resutl);
		return respone;
	}

	@PostMapping("/verifiedOTP")
	public ResponseEntity<ResponseMessage> verifiedOTP(@RequestBody Object object) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(object).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (!StringUtility.isEmpty(jObject.get(Parameter.USERNAME))
				&& !StringUtility.isEmpty(jObject.get(Parameter.TRANSACTION_ID))
				&& !StringUtility.isEmpty(jObject.get(Parameter.OTP_PARAMETER))
				&& !StringUtility.isEmpty(jObject.get(Parameter.IS_GENERATE_PASSWORD))) {
			ResponseMessage result = accounts.verifiedOTP(object);
			String resultCode = result.getResponseCode();
			if (resultCode.equals("810")) {
				// update account isValidate = 1 and generate password
				String username = jObject.get(Parameter.USERNAME).getAsString();
				TbUser user = accounts.getByUserName(username);
				if (user != null) {
					String password = StringUtility.geek_Password(8);
					String userKey = user.getsKey();
					Map<Object, Object> updateAccStatData = new HashMap<Object, Object>();
					updateAccStatData.put("username", username);
					updateAccStatData.put("isValidate", 1);
					String isGeneratePassword = jObject.get(Parameter.IS_GENERATE_PASSWORD).getAsString();
					if (isGeneratePassword.equals("1")) {						
						String passwordEncrypt;
						try {
							passwordEncrypt = StringUtility.encryptData(userKey, password);
							updateAccStatData.put(Parameter.PASSWORD, passwordEncrypt);
						} catch (Exception e) {
							e.printStackTrace();
						}						
					}
					jElement = gson.toJsonTree(updateAccStatData).getAsJsonObject();
					jObject = jElement.getAsJsonObject();
					Boolean isOk = accounts.UpdateCustomerUser(jObject);

					if (isOk) {						
						// send email
						Runnable runnable = new Runnable() {							
							@Override
							public void run() {
								if (isGeneratePassword.equals("0")) {
									sendEmail(Parameter.SIGNUP_SUCCESS_TEMPLATE_NAME, username,
											new HashMap<Object, Object>());
								} else {
									Map<Object, Object> map = new HashMap<Object, Object>();
									map.put(Parameter.PASSWORD, password);
									sendEmail(Parameter.SEND_PASSWORD, username, map);
								}								
							}
						};
						Thread thread = new Thread(runnable);
						thread.start();
						// check pocket
						boolean isPocketExisted = accounts.getPocketByUserIdAndCurrencyI(user.getSUserId(),
								Parameter.CURRENCY_ID_DEFAULT);
						if (isPocketExisted) {
							try {
								Date current = new Date(System.currentTimeMillis());
								String content = current.toString() + username;
								String pocketId = StringUtility.HexSHA(content);
								TbMypocket pocket = new TbMypocket();
								pocket.setSUserId(user.getSUserId());
								pocket.setSCurrencyId(Parameter.CURRENCY_ID_DEFAULT);
								String valueEncrypt = StringUtility.encryptData(userKey, "0");	
								pocket.setnBlockedBalance(valueEncrypt);
								pocket.setNAvailableBalance(valueEncrypt);
								pocket.setNBalance(valueEncrypt);
								pocket.setSPocketId(pocketId);
								accounts.addPocket(pocket);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//check promotion
							Runnable runnable_1 = new Runnable() {
								
								@Override
								public void run() {
									TbCustomerLoyaltyProgram promotion = accounts.checkPromotionForNewUser("REGIS", "COIN", new Date(System.currentTimeMillis()));
									if(promotion != null && promotion.getNQuantity() >0){
										String transactionTypeId = Parameter.TRANSFER_TO_USER_TYPE;
										String toUserId = user.getSUserId();
										BigDecimal amountTransfer = new BigDecimal(promotion.getNQuantity()).setScale(2);
										String channelId = Parameter.MOBILE_CHANNEL;
										String fromUserId = Parameter.COB_USER_ID;
										String transMessage = promotion.getSDescription();
										Map<String,Object> data = new HashMap<String,Object>();
										data.put(Parameter.TRANSACTION_TYPE_ID, transactionTypeId);
										data.put(Parameter.TO_USER_ID, toUserId);
										data.put(Parameter.AMOUNT, amountTransfer);
										data.put(Parameter.CHANNEL_ID, channelId);
										data.put(Parameter.FROM_USER_ID, fromUserId);
										data.put(Parameter.TRANS_MESSAGE, transMessage);
										Object resutl = accounts.transferCobCoin(data);
										System.out.println(resutl);
									}
								}
								
							};
							Thread thread_1 = new Thread(runnable_1);
							thread_1.start();
						}						
					}
				}
				respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.verify-otp.success.code"));
				respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.verify-otp.success.msg"));
			} else {
				respone.getBody().setResponseCode(result.getResponseCode());
				respone.getBody().setResponseMessage(result.getResponseMessage());
			}
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.verify-otp.null.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.verify-otp.null.msg"));
		}
		return respone;
	}

	@PostMapping("/registerAccount")
	public ResponseEntity<ResponseMessage> RegisterAccount(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.RegisAccount(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			respone.getBody().setResponseCode(responseMessage.getResponseCode());
			respone.getBody().setResponseData(responseMessage.getResponseData());
			respone.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return respone;
		}
		String Username = jObject.get(Parameter.USERNAME).getAsString();
		String Phone = jObject.get(Parameter.PHONE).getAsString();
		String Email = jObject.get(Parameter.EMAIL).getAsString();
		String CardId = jObject.get(Parameter.S_CARDID_NO).getAsString();

		Integer Check = accounts.CheckUsernamPhone(Username, Phone, Email, CardId);
		if (Check == 1) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkusername.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkusername.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (Check == 2) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkphone.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkphone.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (Check == 3) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkemail.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkemail.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (Check == 4) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkcardid.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkcardid.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			Integer res = accounts.RegisCustomerUser(jObject);
			if (res == 1) {
				// send otp
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						String method = Parameter.DEFAULT_NOTIFY_METHOD.toString();
						if (!StringUtility.isEmpty(jObject.get(Parameter.NOTIFY_METHOD))) {
							method = jObject.get(Parameter.NOTIFY_METHOD).getAsString();
						}
						if (!StringUtility.isEmpty(jObject.get(Parameter.TRANSACTION_ID))) {
							String transactionId = jObject.get(Parameter.TRANSACTION_ID).getAsString();
							if (method.equals("0")) {
								Map<Object, Object> data = new HashMap<Object, Object>();
								data.put(Parameter.TRANSACTION_ID, transactionId);
								sendEmail(Parameter.OTP_TEMPLATE, Username, data);
							} else if (method.equals("1")) {
								Map<Object, Object> data = new HashMap<Object, Object>();
								data.put(Parameter.TRANSACTION_ID, transactionId);
								sendNotify(Parameter.OTP_TEMPLATE, Username, data);
							}

						}
						
					}
				};
				Thread thread = new Thread(runnable);
				thread.start();

				respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.createaccount.success.code"));
				respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.createaccout.success.msg"));
				respone.getBody().setResponseData("");
				return respone;
			} else {
				if (res == 0) {
					respone.getBody()
							.setResponseCode(configUtil.getProperty("cob.accounts.createaccountrefe.error.code"));
					respone.getBody()
							.setResponseMessage(configUtil.getProperty("cob.accounts.createaccountrefe.error.msg"));
					respone.getBody().setResponseData("");
					return respone;
				} else {
					respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.createaccount.error.code"));
					respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.createaccout.error.msg"));
					respone.getBody().setResponseData("");
					return respone;
				}
			}

		}
	}

	@PostMapping("/registerMybusinessWorkingTime")
	public ResponseEntity<ResponseMessage> registerMybusinessWorkingTime(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean res = accounts.RegisWorkingTime(jObject);
		if (res == true) {
			respone.getBody()
					.setResponseCode(configUtil.getProperty("cob.accounts.createMybusinessWorkingTime.success.code"));
			respone.getBody()
					.setResponseMessage(configUtil.getProperty("cob.accounts.createMybusinessWorkingTime.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody()
					.setResponseCode(configUtil.getProperty("cob.accounts.createMybusinessWorkingTime.error.code"));
			respone.getBody()
					.setResponseMessage(configUtil.getProperty("cob.accounts.createMybusinessWorkingTime.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

	}

	@RequestMapping(path = "/loginAccount/{Username}/{Password}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> LoginAcount(@PathVariable("Username") String Username,
			@PathVariable("Password") String Password) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		AccountInfo resultlogin = accounts.AcountLogin(Username, Password);
		// get pokect cob
		if (resultlogin == null || StringUtils.isEmpty(resultlogin.getSCustomerId())) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.login.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.login.error.msg"));
			// respone.getBody().setResponseData("");
			return respone;
		}

		respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.login.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.login.success.msg"));
		respone.getBody().setResponseData(resultlogin);
		return respone;

	}

	@RequestMapping(path = "/changePassWord", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> changePassWord(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		String Username = jObject.get(Parameter.USERNAME).getAsString();
		String PassOld = jObject.get(Parameter.PASSWORD).getAsString();
		String PassNew = jObject.get(Parameter.PASSWORD_NEW).getAsString();
		
		Integer result = accounts.ChangePassAccount(Username, PassOld, PassNew);
		if (result == 0) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.changepass.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.changepass.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == 1) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.changepassname.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.changepassname.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == 2) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.changepassOld.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.changepassOld.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == 3) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.changepassNew.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.changepassNew.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == 4) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.changepass.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.changepass.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		return respone;
	}

	@PostMapping("/updateAccount")
	public ResponseEntity<ResponseMessage> UpdateAccount(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean res = accounts.UpdateCustomerUser(jObject);
		if (res == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.updateaccount.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.updateaccount.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.updateaccount.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.updateaccount.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@PostMapping("/resetPassWordAccount")
	public ResponseEntity<ResponseMessage> RestPassWordAccount(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.USERNAME))
				|| StringUtility.isEmpty(jObject.get(Parameter.NOTIFY_METHOD))
				|| StringUtility.isEmpty(jObject.get(Parameter.TRANSACTION_ID))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		} else {
			String username = jObject.get(Parameter.USERNAME).getAsString();
			String method = jObject.get(Parameter.NOTIFY_METHOD).getAsString();
			String transactionId = jObject.get(Parameter.TRANSACTION_ID).getAsString();
			TbUser u = accounts.getByUserName(username);
			if (u != null) {
				TbCustomer customer = accounts.getCustomerByCustomerId(u.getSCustomerId());
				if (method.equals("0")) {					
					Map<Object, Object> data = new HashMap<Object, Object>();
					data.put(Parameter.TRANSACTION_ID, transactionId);
					sendEmail(Parameter.OTP_TEMPLATE, customer.getSLastname(), data);
					response.getBody().setResponseCode(configUtil.getProperty("cob.account.reset-password.code"));
					response.getBody().setResponseMessage(configUtil.getProperty("cob.account.reset-password.msg"));
				} else if (method.equals("0")) {
					Map<Object, Object> data = new HashMap<Object, Object>();
					data.put(Parameter.TRANSACTION_ID, transactionId);
					sendNotify(Parameter.OTP_TEMPLATE, username, data);
					response.getBody().setResponseCode(configUtil.getProperty("cob.account.reset-password.code"));
					response.getBody().setResponseMessage(configUtil.getProperty("cob.account.reset-password.msg"));
				}
			}
		}
		return response;
	}

	@PostMapping("/myBusinessCreate")
	public ResponseEntity<ResponseMessage> MyBusinessCreate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean a = accounts.myBusinessCreate(jObject);
		if (a == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.mybusinesscreate.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.mybusinesscreate.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (a == false) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.mybusinesscreate.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.mybusinesscreate.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

		return respone;
	}

	@RequestMapping(path = "/getMyBusinessInfo/{Mybuid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getMyBusinessInfo(@PathVariable("Mybuid") String Mybuid) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		TbMyBusiness mybu = accounts.getMyBusinessInfo(Mybuid);
		if (mybu != null) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.getbusiness.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.getbusiness.success.msg"));
			respone.getBody().setResponseData(mybu);
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.getbusiness.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.getbusiness.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@PostMapping("/myBusinessUpdate")
	public ResponseEntity<ResponseMessage> MyBusinessUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean a = accounts.myBusinessUpdate(jObject);
		if (a == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.updatebusiness.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.updatebusiness.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (a == false) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.updatebusiness.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.updatebusiness.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

		return respone;
	}

	@RequestMapping(path = "/checkTokenExprited/{Token}/{UserId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> GetNumberMyBusiness(@PathVariable("Token") String Token,
			@PathVariable("UserId") String UserId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Integer result = accounts.CheckAuthen(Token, UserId);
		if (result == 0) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkauthentrue.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkauthentrue.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == 1) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkauthenfalse.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkauthenfalse.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

		respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.checkauthen.error.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.checkauthen.error.msg"));
		respone.getBody().setResponseData("");
		return respone;
	}

	/*
	 * Chưa Dùng Tới và bảng đã thay đổi
	 * 
	 * @RequestMapping(path = "/getNumberMyBusiness", method = RequestMethod.GET)
	 * public ResponseEntity<ResponseMessage> GetNumberMyBusiness() {
	 * ResponseMessage responseMessage = new ResponseMessage();
	 * ResponseEntity<ResponseMessage> respone = new
	 * ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
	 * 
	 * List<ViewNumberMyBusiness> lstmybusiness = new
	 * ArrayList<ViewNumberMyBusiness>(); lstmybusiness =
	 * accounts.GetNumberMyBusiness(); if(lstmybusiness != null) {
	 * respone.getBody().setResponseCode(configUtil.getProperty(
	 * "cob.accounts.numbermybusiness.success.code"));
	 * respone.getBody().setResponseMessage(configUtil.getProperty(
	 * "cob.accounts.numbermybusiness.success.msg"));
	 * respone.getBody().setResponseData(lstmybusiness); return respone; }
	 * respone.getBody().setResponseCode(configUtil.getProperty(
	 * "cob.accounts.numbermybusiness.error.code"));
	 * respone.getBody().setResponseMessage(configUtil.getProperty(
	 * "cob.accounts.numbermybusiness.error.msg"));
	 * respone.getBody().setResponseData(""); return respone; }
	 */
	@GetMapping(value = "/getMybusinessWorkingtime")
	public ResponseEntity<ResponseMessage> MybusinessWorkingtime(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadMybusinessWorkingtime(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		String tdId = jObject.get(Parameter.BUSINESS_ID).getAsString();
		List<TbMybusinessWorkingtime> detail = accounts.MybusinessWorkingtime(tdId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("workingtime", detail);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping("/registerPersonalAbsent")
	public ResponseEntity<ResponseMessage> registerPersonalAbsent(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = accounts.registerPersonalAbsent(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.regisPerAb.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.regisPerAb.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == false) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.regisPerAb.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.regisPerAb.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

		return respone;
	}

	@PostMapping("/personalAbsentUpdate")
	public ResponseEntity<ResponseMessage> personalAbsentUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = accounts.personalAbsentUpdate(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.PerAbupdate.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.PerAbupdate.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		if (result == false) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.PerAbupdate.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.PerAbupdate.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}

		return respone;
	}

	@PostMapping("/getPersonalAbsent")
	public ResponseEntity<ResponseMessage> getPersonalAbsent(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		List<TbPersonalAbsent> result = accounts.getPersonalAbsent(jObject);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.accounts.getPerAb.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.accounts.getPerAb.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@PostMapping("/getAccountInfo")
	public ResponseEntity<ResponseMessage> getAccountInfo(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.USERNAME))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		} else {
			String username = jObject.get(Parameter.USERNAME).getAsString();

			TbUser user = accounts.getByUserName(username);

			if (user != null) {
				TbCustomer customer = accounts.getCustomerByCustomerId(user.getSCustomerId());
				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				JsonElement jeUser = gson.toJsonTree(user).getAsJsonObject();
				JsonObject joUser = jeUser.getAsJsonObject();

				JsonElement jeCustomer = gson.toJsonTree(customer).getAsJsonObject();				
				JsonObject joCustomer = jeCustomer.getAsJsonObject();
				String formatImageValue = joCustomer.get("bAvatar") == null ? ""
						: (joCustomer.get("bAvatar").getAsString().equals("") ? ""
								: (Parameter.IMAGE_PREFIX + "/" + joCustomer.get("sCustomerId").getAsString()));
				joCustomer.addProperty("bAvatar", formatImageValue);

				for (Map.Entry<String, JsonElement> e : joUser.entrySet()) {
					joCustomer.add(e.getKey(), e.getValue());
				}

				String orderJsonString = new Gson().toJson(joCustomer).replace("\"{\\", "{").replace("\\\"}\"", "\"}")
						.replace("\\\"", "\"");
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
				JsonNode node = null;
				try {
					node = mapper.readTree(orderJsonString);
				} catch (IOException e) {
					e.printStackTrace();
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("customer", node);

				response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
				response.getBody().setResponseData(map);
			}
			return response;
		}
		
	}

	
}