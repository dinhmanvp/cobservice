package cob.com.partner.validate;

import com.google.gson.JsonObject;

import cob.com.partner.utils.ConfigUtility;
import cob.com.partner.utils.DateUtility;
import cob.com.partner.utils.StringUtility;
import cob.com.partner.ws.models.ResponseMessage;
import cob.com.partner.ws.param.Parameter;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	public ResponseMessage getPartnerBizcates(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.S_PARTNER_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.partner.id.invalid.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.partner.id.invalid.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}
	
	public ResponseMessage getPartnersForWorking(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.S_USER_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.userId.id.invalid.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.userId.id.invalid.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage getPartnerAccounts(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (!StringUtility.isEmpty(input.get(Parameter.D_APPOINTMETN_DATE))
				&& !DateUtility.Dateformat(input.get(Parameter.D_APPOINTMETN_DATE).getAsString(), "dd/MM/yyyy")) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.partner.register.date.invalid.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.partner.register.date.invalid.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}
}