package cob.com.marketing.ws.validate;

import com.google.gson.JsonObject;

import cob.com.marketing.utils.ConfigUtility;
import cob.com.marketing.utils.StringUtility;
import cob.com.marketing.ws.models.ResponseMessage;
import cob.com.marketing.ws.param.Parameter;

public class ValidateInput {
	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}
	
	public ResponseMessage getAdvertiseValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if(StringUtility.isEmpty(input.get(Parameter.IS_DISPLAY))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		}
		return responseMessage;
	}
}
