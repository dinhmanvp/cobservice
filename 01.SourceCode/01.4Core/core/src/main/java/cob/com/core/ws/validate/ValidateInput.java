package cob.com.core.ws.validate;



import com.google.gson.JsonObject;

import cob.com.core.utils.ConfigUtility;
import cob.com.core.utils.StringUtility;
import cob.com.core.ws.models.ResponseMessage;
import cob.com.core.ws.param.Parameter;

/**
 * @author ldman 2019/07/06 check validate input
 */
public class ValidateInput {

	private ConfigUtility configUtil;

	public ValidateInput(ConfigUtility configUtil) {
		this.configUtil = configUtil;
	}

	public ResponseMessage loadDashboardValidate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.LOAD_TYPE)) || input.get(Parameter.LOAD_TYPE).getAsInt() < 0
				|| input.get(Parameter.LOAD_TYPE).getAsInt() > 2) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loaddashboard.loadtype.error.code"));
			responseMessage
					.setResponseMessage(configUtil.getProperty("cob.core.loaddashboard.loadtype.error.code.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage dashboardUpdateOrInsertValidate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.DASHBOARD_ID)) || (input.get(Parameter.DASHBOARD_IMAGE) == null)
				|| StringUtility.isEmpty(input.get(Parameter.IS_AVAILABLE))
				|| (input.get(Parameter.DASHBOARD_NAME_CN) == null) || (input.get(Parameter.DASHBOARD_NAME_EN) == null)
				|| (input.get(Parameter.DASHBOARD_NAME_VN) == null) || (input.get(Parameter.ORDER) == null)) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage groupBusinessCateValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_CATE_ID))
				|| (input.get(Parameter.GROUP_BUSINESS_CATE_ICON) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_DESCRIPTION_CN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_DESCRIPTION_EN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_DESCRIPTION_VN) == null)
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_CATE_NAME_VN))
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_CATE_NAME_CN))
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_CATE_NAME_EN))
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage businessTypeValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (

		StringUtility.isEmpty(input.get(Parameter.BUSINESS_TYPE_ID))
				|| (input.get(Parameter.BUSINESS_TYPE_DESC_CN) == null)
				|| (input.get(Parameter.BUSINESS_TYPE_DESC_EN) == null)
				|| (input.get(Parameter.BUSINESS_TYPE_DESC_VN) == null)
				|| (input.get(Parameter.BUSINESS_TYPE_NAME_CN) == null)
				|| (input.get(Parameter.BUSINESS_TYPE_NAME_EN) == null)
				|| (input.get(Parameter.BUSINESS_TYPE_NAME_VN) == null)
				|| StringUtility.isEmpty(input.get(Parameter.BUSINESS_GROUP_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage contractPolicyTypeValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.FROM_AMOUNT))
				|| StringUtility.isEmpty(input.get(Parameter.TO_AMOUNT))
				|| StringUtility.isEmpty(input.get(Parameter.CHARGE_IN_TRANSACTION))
				|| StringUtility.isEmpty(input.get(Parameter.IS_MONTHLY_FEE_APPLIED))
				|| StringUtility.isEmpty(input.get(Parameter.CONTRACT_POLICY_TYPE_ID))
				|| (input.get(Parameter.POLICY_TYPE_NAME_CN) == null)
				|| (input.get(Parameter.POLICY_TYPE_NAME_EN) == null)
				|| (input.get(Parameter.POLICY_TYPE_NAME_VN) == null)) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage contractPolicyTypeDetailValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if ((input.get(Parameter.PRICE) == null) || (input.get(Parameter.COUNT_FROM) == null)
				|| (input.get(Parameter.COUNT_TO) == null)
				|| StringUtility.isEmpty(input.get(Parameter.CONTRACT_POLICY_TYPE_DETAIL_ID))
				|| StringUtility.isEmpty(input.get(Parameter.CONTRACT_POLICY_TYPE_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage countryValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if ((input.get(Parameter.COUNTRY_FLAG_IMAGE) == null) || StringUtility.isEmpty(input.get(Parameter.COUNTRY_ID))
				|| (input.get(Parameter.COUNTRY_NAME_CN) == null) || (input.get(Parameter.COUNTRY_NAME_EN) == null)
				|| (input.get(Parameter.COUNTRY_NAME_VN) == null)) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage cityValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if ((input.get(Parameter.CITY_MAP_LATITUDE) == null) || (input.get(Parameter.CITY_MAP_LONGTITUDE) == null)
				|| StringUtility.isEmpty(input.get(Parameter.CITY_ID)) || (input.get(Parameter.CITY_NAME_CN) == null)
				|| (input.get(Parameter.CITY_NAME_VN) == null) || StringUtility.isEmpty(input.get(Parameter.COUNTRY_ID))
				|| (input.get(Parameter.CITY_NAME_EN) == null)) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage groupBusinessValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if ((input.get(Parameter.GROUP_BUSINESS_ICON) == null)
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_AVAILABLE))
				|| (input.get(Parameter.GROUP_BUSINESS_RATING) == null) || (input.get(Parameter.HOME_DISPLAY) == null)
				|| (input.get(Parameter.ORDER) == null) || StringUtility.isEmpty(input.get(Parameter.DASHBOARD_ID))
				|| (input.get(Parameter.GROUP_BUSINESS_DESC_EN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_DESC_VN) == null)
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))
				|| (input.get(Parameter.GROUP_BUSINESS_NAME_CN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_NAME_EN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_NAME_VN) == null)
				|| (input.get(Parameter.GROUP_BUSINESS_DESC_CN) == null)) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.update-dashboard-input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.update-dashboard-input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage businessServiceValidateInput(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if ((input.get(Parameter.BUSINESS_SERVICE_ICON) == null)
				|| StringUtility.isEmpty(input.get(Parameter.BUSINESS_SERVICE_AVAILABLE))
				|| (input.get(Parameter.BUSINESS_SERVICE_RATING) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_CN) == null) || (input.get(Parameter.ORDER) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_EN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_CN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_EN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_VN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_DESC_VN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_NAME_CN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_NAME_EN) == null)
				|| (input.get(Parameter.BUSINESS_SERVICE_NAME_VN) == null)
				|| StringUtility.isEmpty(input.get(Parameter.BUSINESS_SERVICE_ID))
				|| StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.update-dashboard-input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.update-dashboard-input.null.msg"));
		}
		return responseMessage;
	}

	public ResponseMessage loadGroupBusinessValidate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.DASHBOARD_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadgroupbusiness.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.loadgroupbusiness.error.code.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage loadBusinessServiceValidate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadbusinessservice.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.loadbusinessservice.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage loadGroupBusinessIsHomeValidate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadbusinessservice.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.loadbusinessservice.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage loadContractPolicyTypeDetail(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.CONTRACT_POLICY_TYPE_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadcontractpolicytypedetail.error.code"));
			responseMessage
					.setResponseMessage(configUtil.getProperty("cob.core.loadcontractpolicytypedetail.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

	public ResponseMessage loadGroupbusinessCate(JsonObject input) {
		ResponseMessage responseMessage = new ResponseMessage();
		if (StringUtility.isEmpty(input.get(Parameter.GROUP_BUSINESS_ID))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.loadgroupbusinesscate.error.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.loadgroupbusinesscate.error.msg"));
			return responseMessage;
		}
		responseMessage.setResponseCode(configUtil.getProperty("cob.success.code"));
		responseMessage.setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return responseMessage;
	}

}