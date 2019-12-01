package cob.com.finance.ws.validate;

import com.google.gson.JsonObject;

import cob.com.finance.param.Parameter;
import cob.com.finance.utils.ConfigUtility;
import cob.com.finance.utils.StringUtility;
import cob.com.finance.ws.models.ResponseMessage;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	public ResponseMessage validateTransactionInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.S_POCKET_ID))
				|| StringUtility.isEmpty(input.get(Parameter.S_FROM_USER_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.input-null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.input-null.msg"));
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage getPoketInfoForOther(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.USER_ID))
				&& StringUtility.isEmpty(input.get(Parameter.S_CURRENCY_ID))
				&& StringUtility.isEmpty(input.get(Parameter.PHONE))
				&& StringUtility.isEmpty(input.get(Parameter.EMAIL))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.input-null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.input-null.msg"));
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}
	
	public ResponseMessage validateTransferCoinInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if(StringUtility.isEmpty(input.get(Parameter.TRANSACTION_TYPE_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.TO_USER_ID)) ||
				//StringUtility.isEmpty(input.get(Parameter.TO_POCKET_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.AMOUNT)) ||
				StringUtility.isEmpty(input.get(Parameter.CHANNEL_ID)) ||
				//StringUtility.isEmpty(input.get(Parameter.CURRENCY_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.FROM_USER_ID)) 
				//||StringUtility.isEmpty(input.get(Parameter.FROM_POCKET_ID)) 
//				|| StringUtility.isEmpty(input.get(Parameter.TRANS_MESSAGE))
				) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.input-null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.input-null.msg"));
		}
		
		return responseMessage;
	}
}