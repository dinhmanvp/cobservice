package cob.com.accounts.ws.validate;



import com.google.gson.JsonObject;

import cob.com.accounts.utils.ConfigUtility;
import cob.com.accounts.utils.StringUtility;
import cob.com.accounts.ws.models.ResponseMessage;
import cob.com.accounts.ws.param.Parameter;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}
	public ResponseMessage loadMybusinessWorkingtime(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadMybusinessWorkingtime.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.loadMybusinessWorkingtime.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}
	
	public ResponseMessage RegisAccount(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.FIRSTNAME)) 
			|| StringUtility.isEmpty(input.get(Parameter.LASTNAME)) 
			|| StringUtility.isEmpty(input.get(Parameter.PHONE)) 
			|| StringUtility.isEmpty(input.get(Parameter.EMAIL)) 
			|| StringUtility.isEmpty(input.get(Parameter.USERNAME))
			|| StringUtility.isEmpty(input.get(Parameter.GENDER))
			|| StringUtility.isEmpty(input.get(Parameter.S_CARDID_NO))
				) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.account.vaildateregisaccount.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.account.vaildateregisaccount.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	
}