package cob.com.business.ws.validate;

import com.google.gson.JsonObject;

import cob.com.business.utils.ConfigUtility;
import cob.com.business.utils.DateUtility;
import cob.com.business.utils.StringUtility;
import cob.com.business.ws.models.ResponseMessage;
import cob.com.business.ws.param.Parameter;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	public ResponseMessage registerOrderByEcom(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (!StringUtility.isEmpty(input.get(Parameter.D_APPOINTMETN_DATE))
				&& !DateUtility.Dateformat(input.get(Parameter.D_APPOINTMETN_DATE).getAsString(), "yyyy-MM-dd")) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.regis-order-date.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.regis-order-date.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

}