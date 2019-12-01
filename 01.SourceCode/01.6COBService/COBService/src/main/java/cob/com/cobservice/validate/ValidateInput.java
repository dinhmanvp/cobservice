package cob.com.cobservice.validate;

import java.math.BigDecimal;

import com.google.gson.JsonObject;

import cob.com.cobservice.utils.ConfigUtility;
import cob.com.cobservice.utils.StringUtility;
import cob.com.cobservice.ws.models.ResponseMessage;
import cob.com.cobservice.ws.param.Parameter;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	public ResponseMessage validateAdviseServicesInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_ID))
				|| StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_IS_AVAILABLE))
				|| StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_RATING))
				|| (input.get(Parameter.ADVISE_SERVICE_ICON) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_NAME_CN) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_NAME_EN) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_NAME_VN) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_SHORTDESC_CN) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_SHORTDESC_EN) == null)
				|| (input.get(Parameter.ADVISE_SERVICE_SHORTDESC_VN) == null)) {			
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.msg"));
		}else {
			BigDecimal maxRatingValue = new BigDecimal(5.00);
			BigDecimal inputValue = (input.get(Parameter.ADVISE_SERVICE_RATING)).getAsBigDecimal();
			if(inputValue.compareTo(maxRatingValue) == 1) {
				responseMessage.setResponseCode(configUtil.getProperty("cob.rating-input.error.code"));
				responseMessage.setResponseMessage(configUtil.getProperty("cob.rating-input.error.msg"));
			}
		}
		return responseMessage;
	}
	public ResponseMessage validateAdviseServiceDetailsInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_DETAIL_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_ID)) ||
				(input.get(Parameter.CONTENT) == null)
				) {			
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.msg"));
		}
		return responseMessage;
	}
	public ResponseMessage validateServiceUnitInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.SERVICE_UNIT_ID)) ||
				(input.get(Parameter.DURATION_TYPE) == null) ||
				(input.get(Parameter.UNIT_NAME_CN) == null) ||
				(input.get(Parameter.UNIT_NAME_EN) == null) ||
				(input.get(Parameter.UNIT_NAME_VN) == null)
				) {			
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.msg"));
		}
		return responseMessage;
	}
	
	public ResponseMessage validateStartupPackageInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (
				(input.get(Parameter.DISCOUNT_PRICE) == null) ||
				(input.get(Parameter.IS_AVAILABLE) == null) ||
				(input.get(Parameter.PRICE) == null) ||
				StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID)) ||
				(input.get(Parameter.PACKAGE_DESC_CN) == null) ||
				(input.get(Parameter.PACKAGE_DESC_EN) == null) ||
				(input.get(Parameter.PACKAGE_DESC_VN) == null) ||
				(input.get(Parameter.PACKAGE_ICON) == null) ||
				(input.get(Parameter.PACKAGE_NAME_CN) == null) ||
				(input.get(Parameter.PACKAGE_NAME_EN) == null) ||
				(input.get(Parameter.PACKAGE_NAME_VN) == null) ||
				StringUtility.isEmpty(input.get(Parameter.STARTUP_PACKAGE_ID))
				) {			
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.msg"));
		}
		return responseMessage;
	}
	public ResponseMessage validateStartupPackageItemInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (
				
				StringUtility.isEmpty(input.get(Parameter.FROM_DATE)) ||
				StringUtility.isEmpty(input.get(Parameter.TO_DATE)) ||
				StringUtility.isEmpty(input.get(Parameter.DURATION)) ||
				StringUtility.isEmpty(input.get(Parameter.ADVISE_SERVICE_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.DURATION_TYPE)) ||
				StringUtility.isEmpty(input.get(Parameter.SERVICE_UNIT_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.STARTUP_PACKAGE_ID)) ||
				StringUtility.isEmpty(input.get(Parameter.STARTUP_PACKAGE_ITEM_ID))
				) {			
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.code"));
			responseMessage.setResponseCode(configUtil.getProperty("cob.input.null.msg"));
		}
		return responseMessage;
	}
}
