package cob.com.core.ws.rest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.core.entities.TbBusinessService;
import cob.com.core.entities.TbBusinessType;
import cob.com.core.entities.TbCity;
import cob.com.core.entities.TbContractPolicyType;
import cob.com.core.entities.TbContractPolicyTypeDetail;
import cob.com.core.entities.TbCountry;
import cob.com.core.entities.TbCurrency;
import cob.com.core.entities.TbDashboard;
import cob.com.core.entities.TbGroupBusiness;
import cob.com.core.entities.TbGroupbusinessCate;
import cob.com.core.entities.TbGroupbusinessCateName;
import cob.com.core.entities.TbPromotionHistory;
import cob.com.core.services.Core;
import cob.com.core.utils.ConfigUtility;
import cob.com.core.utils.StringUtility;
import cob.com.core.ws.models.BusinessServiceInfo;
import cob.com.core.ws.models.CompanyTypeInfo;
import cob.com.core.ws.models.PromotionModel;
import cob.com.core.ws.models.ResponseMessage;
import cob.com.core.ws.param.Parameter;
import cob.com.core.ws.validate.ValidateInput;

/**
 * @author ldman 2019/07/06 REST API
 */
@RestController
public class Api {

	@Autowired
	private ConfigUtility configUtil;

	@Autowired
	private Core core;
	
	
	private static final Logger log = LoggerFactory.getLogger(Api.class);


	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> testApi() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData("contact info vuvanbao@gmail.com");
		return respone;
	}

	@PostMapping(value = "/loadDashboard")
	public ResponseEntity<ResponseMessage> loadDashboard(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.loadDashboardValidate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			respone.getBody().setResponseCode(responseMessage.getResponseCode());
			respone.getBody().setResponseData(responseMessage.getResponseData());
			respone.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return respone;
		}
		// execute
		Integer typeId = jObject.get(Parameter.LOAD_TYPE).getAsInt();
		List<TbDashboard> dashboards = core.getDashboardList(typeId);
		// return
		HashMap<String, List<TbDashboard>> result = new HashMap<>();
		result.put("listDashboard", dashboards);
		respone.getBody().setResponseData(result);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return respone;
	}

	@PostMapping(value = "/loadGroupBusiness")
	public ResponseEntity<ResponseMessage> loadGroupBusiness(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadGroupBusinessValidate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		// execute
		String dbId = jObject.get(Parameter.DASHBOARD_ID).getAsString();
		List<TbGroupBusiness> groupBusinesses = core.getGroupBusinessByDashboardId(dbId);
		// return
		HashMap<String, List<TbGroupBusiness>> result = new HashMap<>();
		result.put("GroupBusinesses", groupBusinesses);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping(value = "/loadBusinessService")
	public ResponseEntity<ResponseMessage> loadBusinessService(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadBusinessServiceValidate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		// execute
		String gbId = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
		List<BusinessServiceInfo> businessService = core.getBusinessServicesByGroupBusinessId(gbId);
		// return
		HashMap<String, Object> result = new HashMap<>();
		result.put("BusinessServiceses", businessService);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping(value = "/loadBusinessType")
	public ResponseEntity<ResponseMessage> loadBusinessType(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.GROUP_BUSINESS_ID))) {
			re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		} else {
			String groupBusinessId = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
			List<TbBusinessType> businessType = core.getBusinessType(groupBusinessId);

			HashMap<String, List<TbBusinessType>> result = new HashMap<>();
			result.put("BusinessTypes", businessType);
			re.getBody().setResponseData(result);
			re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		}

		return re;
	}

	@PostMapping(value = "/loadCity")
	public ResponseEntity<ResponseMessage> loadCity(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);

		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// excuse
		List<TbCity> city = core.getCity(jObject);

		HashMap<String, List<TbCity>> result = new HashMap<>();
		result.put("City", city);

		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@GetMapping(value = "/loadCountry")
	public ResponseEntity<ResponseMessage> loadCountry() {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		List<TbCountry> country = core.getCountry();
		HashMap<String, Object> result = new HashMap<>();
		result.put("Country", country);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping(value = "/loadGroupBusinessCate")
	public ResponseEntity<ResponseMessage> loadGroupBusinessCate(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadGroupbusinessCate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		String tdId = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
		List<TbGroupbusinessCate> cate = core.getGroupbusinessCate(tdId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("BusinessCates", cate);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@GetMapping(value = "/loadGroupBusinessIsHome")
	public ResponseEntity<ResponseMessage> loadGroupBusinessIsHome() {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		List<TbGroupBusiness> ishome = core.getGroupBusinessisHome();
		HashMap<String, Object> result = new HashMap<>();
		result.put("GroupBusiness", ishome);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@GetMapping(value = "/loadContractPolicyTypeDetail")
	public ResponseEntity<ResponseMessage> loadContractPolicyTypeDetail(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadContractPolicyTypeDetail(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		String tdId = jObject.get(Parameter.CONTRACT_POLICY_TYPE_ID).getAsString();
		List<TbContractPolicyTypeDetail> detail = core.getContractPolicyTypeDetail(tdId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("ContractPolicyTypeDetail", detail);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@GetMapping(value = "/loadContractPolicyType")
	public ResponseEntity<ResponseMessage> loadContractPolicyType() {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		List<TbContractPolicyType> policytype = core.getContractPolicyType();
		HashMap<String, Object> result = new HashMap<>();
		result.put("ContractPolicyType", policytype);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping(value = "/groupBusinessIsHomeCreate")
	public ResponseEntity<ResponseMessage> groupBusinessIsHomeCreate(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadGroupBusinessIsHomeValidate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		// execute
		String dbId = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
		// List<TbGroupBusiness> groupBusinesses =
		// core.groupBusinessisHomeCretate(dbId);
		Object isSuccess = core.groupBusinessisHomeCretate(dbId);
		// return
		HashMap<String, Object> result = new HashMap<>();
		result.put("Result", isSuccess);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PostMapping(value = "/groupBusinessIsHomeRemove")
	public ResponseEntity<ResponseMessage> groupBusinessIsHomeRemove(@RequestBody Object input) {
		ResponseMessage rm = new ResponseMessage();
		ResponseEntity<ResponseMessage> re = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		rm = validate.loadGroupBusinessIsHomeValidate(jObject);
		if (!configUtil.getProperty("cob.success.code").equals(rm.getResponseCode())) {
			re.getBody().setResponseCode(rm.getResponseCode());
			re.getBody().setResponseData(rm.getResponseData());
			re.getBody().setResponseMessage(rm.getResponseMessage());
			return re;
		}
		// execute
		String dbId = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
		Object isSuccess = core.groupBusinessisHomeRemove(dbId);
		// return
		HashMap<String, Object> result = new HashMap<>();
		result.put("Result", isSuccess);
		re.getBody().setResponseData(result);
		re.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		re.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return re;
	}

	@PutMapping("/dashboardUpdate")
	public ResponseEntity<ResponseMessage> dashboardUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.dashboardUpdateOrInsertValidate(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbDashboard entity = new TbDashboard();
			entity.setSDashboardId(jObject.get(Parameter.DASHBOARD_ID).getAsString());
			entity.setIItemImage(jObject.get(Parameter.DASHBOARD_IMAGE).getAsString());
			entity.setNIsAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setSItemNameCn(jObject.get(Parameter.DASHBOARD_NAME_CN).getAsString());
			entity.setSItemNameVn(jObject.get(Parameter.DASHBOARD_NAME_VN).getAsString());
			entity.setSItemNameEn(jObject.get(Parameter.DASHBOARD_NAME_EN).getAsString());
			entity.setnOrder(jObject.get(Parameter.ORDER).getAsInt());
			Integer result = core.updateDashboard(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerDashboard")
	public ResponseEntity<ResponseMessage> registerDashboard(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.dashboardUpdateOrInsertValidate(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbDashboard entity = new TbDashboard();
			entity.setSDashboardId(jObject.get(Parameter.DASHBOARD_ID).getAsString());
			entity.setIItemImage(jObject.get(Parameter.DASHBOARD_IMAGE).getAsString());
			entity.setNIsAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setSItemNameCn(jObject.get(Parameter.DASHBOARD_NAME_CN).getAsString());
			entity.setSItemNameVn(jObject.get(Parameter.DASHBOARD_NAME_VN).getAsString());
			entity.setSItemNameEn(jObject.get(Parameter.DASHBOARD_NAME_EN).getAsString());
			entity.setnOrder(jObject.get(Parameter.ORDER).getAsInt());
			Integer result = core.addDashboard(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerGroupBusiness")
	public ResponseEntity<ResponseMessage> registerGroupBusiness(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.groupBusinessValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbGroupBusiness entity = new TbGroupBusiness();
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setIGroupBusinessIcon(jObject.get(Parameter.GROUP_BUSINESS_ICON).getAsString());
			entity.setNGroupBusinessAvailble(jObject.get(Parameter.GROUP_BUSINESS_AVAILABLE).getAsInt());
			entity.setNGroupBusinessRating(jObject.get(Parameter.GROUP_BUSINESS_RATING).getAsBigDecimal());
			entity.setNHomeDisplay(jObject.get(Parameter.HOME_DISPLAY).getAsInt());
			entity.setNOrder(jObject.get(Parameter.ORDER).getAsInt());
			entity.setSDashboardId(jObject.get(Parameter.DASHBOARD_ID).getAsString());
			entity.setSGroupBusinessDescCn(jObject.get(Parameter.GROUP_BUSINESS_DESC_CN).getAsString());
			entity.setSGroupBusinessDescEn(jObject.get(Parameter.GROUP_BUSINESS_DESC_EN).getAsString());
			entity.setSGroupBusinessDescVn(jObject.get(Parameter.GROUP_BUSINESS_DESC_VN).getAsString());
			entity.setSGroupBusinessDescShrinkCn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_CN).getAsString());
			entity.setSGroupBusinessDescShrinkVn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_VN).getAsString());
			entity.setSGroupBusinessDescShrinkEn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_EN).getAsString());
			entity.setSGroupBusinessNameCn(jObject.get(Parameter.GROUP_BUSINESS_NAME_CN).getAsString());
			entity.setSGroupBusinessNameVn(jObject.get(Parameter.GROUP_BUSINESS_NAME_VN).getAsString());
			entity.setSGroupBusinessNameEn(jObject.get(Parameter.GROUP_BUSINESS_NAME_EN).getAsString());
			Integer result = core.addgroupBusiness(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/groupBusinessUpdate")
	public ResponseEntity<ResponseMessage> groupBusinessUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.groupBusinessValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbGroupBusiness entity = new TbGroupBusiness();
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setIGroupBusinessIcon(jObject.get(Parameter.GROUP_BUSINESS_ICON).getAsString());
			entity.setNGroupBusinessAvailble(jObject.get(Parameter.GROUP_BUSINESS_AVAILABLE).getAsInt());
			entity.setNGroupBusinessRating(jObject.get(Parameter.GROUP_BUSINESS_RATING).getAsBigDecimal());
			entity.setNHomeDisplay(jObject.get(Parameter.HOME_DISPLAY).getAsInt());
			entity.setNOrder(jObject.get(Parameter.ORDER).getAsInt());
			entity.setSDashboardId(jObject.get(Parameter.DASHBOARD_ID).getAsString());
			entity.setSGroupBusinessDescCn(jObject.get(Parameter.GROUP_BUSINESS_DESC_CN).getAsString());
			entity.setSGroupBusinessDescEn(jObject.get(Parameter.GROUP_BUSINESS_DESC_EN).getAsString());
			entity.setSGroupBusinessDescVn(jObject.get(Parameter.GROUP_BUSINESS_DESC_VN).getAsString());
			entity.setSGroupBusinessDescShrinkCn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_CN).getAsString());
			entity.setSGroupBusinessDescShrinkVn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_VN).getAsString());
			entity.setSGroupBusinessDescShrinkEn(jObject.get(Parameter.GROUP_BUSINESS_DESC_SHRINK_EN).getAsString());
			entity.setSGroupBusinessNameCn(jObject.get(Parameter.GROUP_BUSINESS_NAME_CN).getAsString());
			entity.setSGroupBusinessNameVn(jObject.get(Parameter.GROUP_BUSINESS_NAME_VN).getAsString());
			entity.setSGroupBusinessNameEn(jObject.get(Parameter.GROUP_BUSINESS_NAME_EN).getAsString());
			Integer result = core.groupBusinessUpdate(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/businessServiceUpdate")
	public ResponseEntity<ResponseMessage> businessServiceUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.businessServiceValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbBusinessService entity = new TbBusinessService();
			entity.setSBusinessServiceId(jObject.get(Parameter.BUSINESS_SERVICE_ID).getAsString());
			entity.setIBusinessServiceIcon(jObject.get(Parameter.BUSINESS_SERVICE_ICON).getAsString());
			entity.setNBusinessServiceAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setNBusinessServiceRating(jObject.get(Parameter.BUSINESS_SERVICE_RATING).getAsBigDecimal());
			entity.setNOrder(jObject.get(Parameter.ORDER).getAsInt());
			entity.setSBusinessServiceDescCn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_CN).getAsString());
			entity.setSBusinessServiceDescEn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_EN).getAsString());
			entity.setSBusinessServiceDescVn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_VN).getAsString());
			entity.setSBusinessServiceDescShrinkCn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_CN).getAsString());
			entity.setSBusinessServiceDescShrinkEn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_EN).getAsString());
			entity.setSBusinessServiceDescShrinkVn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_VN).getAsString());
			entity.setSBusinessServiceNameCn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_CN).getAsString());
			entity.setSBusinessServiceNameVn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_VN).getAsString());
			entity.setSBusinessServiceNameEn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_EN).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			Integer result = core.updateBusinessService(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerBusinessService")
	public ResponseEntity<ResponseMessage> registerBusinessService(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.businessServiceValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbBusinessService entity = new TbBusinessService();
			entity.setSBusinessServiceId(jObject.get(Parameter.BUSINESS_SERVICE_ID).getAsString());
			entity.setIBusinessServiceIcon(jObject.get(Parameter.BUSINESS_SERVICE_ICON).getAsString());
			entity.setNBusinessServiceAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setNBusinessServiceRating(jObject.get(Parameter.BUSINESS_SERVICE_RATING).getAsBigDecimal());
			entity.setNOrder(jObject.get(Parameter.ORDER).getAsInt());
			entity.setSBusinessServiceDescCn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_CN).getAsString());
			entity.setSBusinessServiceDescEn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_EN).getAsString());
			entity.setSBusinessServiceDescVn(jObject.get(Parameter.BUSINESS_SERVICE_DESC_VN).getAsString());
			entity.setSBusinessServiceDescShrinkCn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_CN).getAsString());
			entity.setSBusinessServiceDescShrinkEn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_EN).getAsString());
			entity.setSBusinessServiceDescShrinkVn(
					jObject.get(Parameter.BUSINESS_SERVICE_DESC_SHRINK_VN).getAsString());
			entity.setSBusinessServiceNameCn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_CN).getAsString());
			entity.setSBusinessServiceNameVn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_VN).getAsString());
			entity.setSBusinessServiceNameEn(jObject.get(Parameter.BUSINESS_SERVICE_NAME_EN).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			Integer result = core.addBusinessService(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerGroupBusinessCate")
	public ResponseEntity<ResponseMessage> registerGroupBusinessCate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.groupBusinessCateValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbGroupbusinessCate entity = new TbGroupbusinessCate();
			entity.setSGroupBusinessCateId(jObject.get(Parameter.GROUP_BUSINESS_CATE_ID).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setSGroupBusinessCateNameVn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_VN).getAsString());
			entity.setSGroupBusinessNameCateCn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_CN).getAsString());
			entity.setSGroupBusinessNameCateEn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_EN).getAsString());
			entity.setSGroupBusinessDescriptionCn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_CN).getAsString());
			entity.setSGroupBusinessDescriptionEn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_EN).getAsString());
			entity.setSGroupBusinessDescriptionVn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_VN).getAsString());
			entity.setIGroupBusinessCateIcon(jObject.get(Parameter.GROUP_BUSINESS_CATE_ICON).getAsString());
			Integer result = core.addgroupBusinessCate(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/groupBusinessCateUpdate")
	public ResponseEntity<ResponseMessage> updateGroupBusinessCate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.groupBusinessCateValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbGroupbusinessCate entity = new TbGroupbusinessCate();
			entity.setSGroupBusinessCateId(jObject.get(Parameter.GROUP_BUSINESS_CATE_ID).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setSGroupBusinessCateNameVn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_VN).getAsString());
			entity.setSGroupBusinessNameCateCn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_CN).getAsString());
			entity.setSGroupBusinessNameCateEn(jObject.get(Parameter.GROUP_BUSINESS_CATE_NAME_EN).getAsString());
			entity.setSGroupBusinessDescriptionCn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_CN).getAsString());
			entity.setSGroupBusinessDescriptionEn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_EN).getAsString());
			entity.setSGroupBusinessDescriptionVn(jObject.get(Parameter.GROUP_BUSINESS_DESCRIPTION_VN).getAsString());
			entity.setIGroupBusinessCateIcon(jObject.get(Parameter.GROUP_BUSINESS_CATE_ICON).getAsString());
			Integer result = core.updategroupBusinessCate(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerBusinessType")
	public ResponseEntity<ResponseMessage> registerBusinessType(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.businessTypeValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbBusinessType entity = new TbBusinessType();
			entity.setSBusinessTypeDescCn(jObject.get(Parameter.BUSINESS_TYPE_DESC_CN).getAsString());
			entity.setSBusinessTypeDescEn(jObject.get(Parameter.BUSINESS_TYPE_DESC_EN).getAsString());
			entity.setSBusinessTypeDescVn(jObject.get(Parameter.BUSINESS_TYPE_DESC_VN).getAsString());
			entity.setsBusinessGroupId(jObject.get(Parameter.BUSINESS_GROUP_ID).getAsString());
			entity.setSBusinessTypeId(jObject.get(Parameter.BUSINESS_TYPE_ID).getAsString());
			entity.setSBusinessTypeNameCn(jObject.get(Parameter.BUSINESS_TYPE_NAME_CN).getAsString());
			entity.setSBusinessTypeNameEn(jObject.get(Parameter.BUSINESS_TYPE_NAME_EN).getAsString());
			entity.setSBusinessTypeNameVn(jObject.get(Parameter.BUSINESS_TYPE_NAME_VN).getAsString());

			Integer result = core.addBusinessType(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/businessTypeUpdate")
	public ResponseEntity<ResponseMessage> businessTypeUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.businessTypeValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbBusinessType entity = new TbBusinessType();
			entity.setSBusinessTypeDescCn(jObject.get(Parameter.BUSINESS_TYPE_DESC_CN).getAsString());
			entity.setSBusinessTypeDescEn(jObject.get(Parameter.BUSINESS_TYPE_DESC_EN).getAsString());
			entity.setSBusinessTypeDescVn(jObject.get(Parameter.BUSINESS_TYPE_DESC_VN).getAsString());
			entity.setsBusinessGroupId(jObject.get(Parameter.BUSINESS_GROUP_ID).getAsString());
			entity.setSBusinessTypeId(jObject.get(Parameter.BUSINESS_TYPE_ID).getAsString());
			entity.setSBusinessTypeNameCn(jObject.get(Parameter.BUSINESS_TYPE_NAME_CN).getAsString());
			entity.setSBusinessTypeNameEn(jObject.get(Parameter.BUSINESS_TYPE_NAME_EN).getAsString());
			entity.setSBusinessTypeNameVn(jObject.get(Parameter.BUSINESS_TYPE_NAME_VN).getAsString());

			Integer result = core.updateBusinessType(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/contractPolicyTypeUpdate")
	public ResponseEntity<ResponseMessage> contractPolicyTypeUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.contractPolicyTypeValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbContractPolicyType entity = new TbContractPolicyType();
			entity.setMFromAmount(jObject.get(Parameter.FROM_AMOUNT).getAsInt());
			entity.setMToAmount(jObject.get(Parameter.TO_AMOUNT).getAsInt());
			entity.setNIsChargeInTransaction(jObject.get(Parameter.CHARGE_IN_TRANSACTION).getAsInt());
			entity.setNIsMontlyfeeApplied(jObject.get(Parameter.IS_MONTHLY_FEE_APPLIED).getAsInt());
			entity.setSContractPolicyTypeId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_ID).getAsString());
			entity.setSPolicyTypeNameCn(jObject.get(Parameter.POLICY_TYPE_NAME_CN).getAsString());
			entity.setSPolicyTypeNameEn(jObject.get(Parameter.POLICY_TYPE_NAME_EN).getAsString());
			entity.setSPolicyTypeNameVn(jObject.get(Parameter.POLICY_TYPE_NAME_VN).getAsString());

			Integer result = core.updateContractPolicyType(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerContractPolicyType")
	public ResponseEntity<ResponseMessage> registerContractPolicyType(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.contractPolicyTypeValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbContractPolicyType entity = new TbContractPolicyType();
			entity.setMFromAmount(jObject.get(Parameter.FROM_AMOUNT).getAsInt());
			entity.setMToAmount(jObject.get(Parameter.TO_AMOUNT).getAsInt());
			entity.setNIsChargeInTransaction(jObject.get(Parameter.CHARGE_IN_TRANSACTION).getAsInt());
			entity.setNIsMontlyfeeApplied(jObject.get(Parameter.IS_MONTHLY_FEE_APPLIED).getAsInt());
			entity.setSContractPolicyTypeId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_ID).getAsString());
			entity.setSPolicyTypeNameCn(jObject.get(Parameter.POLICY_TYPE_NAME_CN).getAsString());
			entity.setSPolicyTypeNameEn(jObject.get(Parameter.POLICY_TYPE_NAME_EN).getAsString());
			entity.setSPolicyTypeNameVn(jObject.get(Parameter.POLICY_TYPE_NAME_VN).getAsString());

			Integer result = core.addContractPolicyType(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;

			}
		}
		return response;
	}

	@PostMapping("/registerContractPolicyTypeDetail")
	public ResponseEntity<ResponseMessage> registerContractPolicyTypeDetail(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.contractPolicyTypeDetailValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbContractPolicyTypeDetail entity = new TbContractPolicyTypeDetail();
			entity.setMPrice(jObject.get(Parameter.PRICE).getAsBigDecimal());
			entity.setNCountFrom(jObject.get(Parameter.COUNT_FROM).getAsInt());
			entity.setNCountTo(jObject.get(Parameter.COUNT_TO).getAsInt());
			entity.setSContractPolicyTypeDetailId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_DETAIL_ID).getAsString());
			entity.setSContractPolicyTypeId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_ID).getAsString());
			Integer result = core.addContractPolicyTypeDetail(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/contractPolicyTypeDetailUpdate")
	public ResponseEntity<ResponseMessage> contractPolicyTypeDetailUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.contractPolicyTypeDetailValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbContractPolicyTypeDetail entity = new TbContractPolicyTypeDetail();
			entity.setMPrice(jObject.get(Parameter.PRICE).getAsBigDecimal());
			entity.setNCountFrom(jObject.get(Parameter.COUNT_FROM).getAsInt());
			entity.setNCountTo(jObject.get(Parameter.COUNT_TO).getAsInt());
			entity.setSContractPolicyTypeDetailId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_DETAIL_ID).getAsString());
			entity.setSContractPolicyTypeId(jObject.get(Parameter.CONTRACT_POLICY_TYPE_ID).getAsString());
			Integer result = core.updateContractPolicyTypeDetail(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerCountry")
	public ResponseEntity<ResponseMessage> registerCountry(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.countryValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbCountry entity = new TbCountry();
			entity.setSCountryFlagImage(jObject.get(Parameter.COUNTRY_FLAG_IMAGE).getAsString());
			entity.setSCountryId(jObject.get(Parameter.COUNTRY_ID).getAsString());
			entity.setSCountryNameCn(jObject.get(Parameter.COUNTRY_NAME_CN).getAsString());
			entity.setSCountryNameVn(jObject.get(Parameter.COUNTRY_NAME_VN).getAsString());
			entity.setSCountryNameEn(jObject.get(Parameter.COUNTRY_NAME_EN).getAsString());
			Integer result = core.addCountry(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/countryUpdate")
	public ResponseEntity<ResponseMessage> countryUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.countryValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbCountry entity = new TbCountry();
			entity.setSCountryFlagImage(jObject.get(Parameter.COUNTRY_FLAG_IMAGE).getAsString());
			entity.setSCountryId(jObject.get(Parameter.COUNTRY_ID).getAsString());
			entity.setSCountryNameCn(jObject.get(Parameter.COUNTRY_NAME_CN).getAsString());
			entity.setSCountryNameVn(jObject.get(Parameter.COUNTRY_NAME_VN).getAsString());
			entity.setSCountryNameEn(jObject.get(Parameter.COUNTRY_NAME_EN).getAsString());
			Integer result = core.updateCountry(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/cityUpdate")
	public ResponseEntity<ResponseMessage> cityUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.cityValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbCity entity = new TbCity();
			entity.setICityMapLatitude(jObject.get(Parameter.CITY_MAP_LATITUDE).getAsBigDecimal());
			entity.setICityMapLongitude(jObject.get(Parameter.CITY_MAP_LONGTITUDE).getAsBigDecimal());
			entity.setSCityId(jObject.get(Parameter.CITY_ID).getAsString());
			entity.setSCityNameCn(jObject.get(Parameter.CITY_NAME_CN).getAsString());
			entity.setSCityNameVn(jObject.get(Parameter.CITY_NAME_VN).getAsString());
			entity.setSCityNameEn(jObject.get(Parameter.CITY_NAME_EN).getAsString());
			entity.setSCountryId(jObject.get(Parameter.COUNTRY_ID).getAsString());
			Integer result = core.updateCity(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.id-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.id-not-exist.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.reference-not-exist.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.reference-not-exist.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerCity")
	public ResponseEntity<ResponseMessage> registerCity(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.cityValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbCity entity = new TbCity();
			entity.setICityMapLatitude(jObject.get(Parameter.CITY_MAP_LATITUDE).getAsBigDecimal());
			entity.setICityMapLongitude(jObject.get(Parameter.CITY_MAP_LONGTITUDE).getAsBigDecimal());
			entity.setSCityId(jObject.get(Parameter.CITY_ID).getAsString());
			entity.setSCityNameCn(jObject.get(Parameter.CITY_NAME_CN).getAsString());
			entity.setSCityNameVn(jObject.get(Parameter.CITY_NAME_VN).getAsString());
			entity.setSCityNameEn(jObject.get(Parameter.CITY_NAME_EN).getAsString());
			entity.setSCountryId(jObject.get(Parameter.COUNTRY_ID).getAsString());
			Integer result = core.addCity(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.id-existed.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.id-existed.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.core.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.core.add.error.msg"));
				break;
			}
		}
		return response;
	}

	@GetMapping("/groupBusinessCateName")
	public ResponseEntity<ResponseMessage> groupBusinessCateName() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		List<TbGroupbusinessCateName> names = core.getAllGroupbusinessCateName();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("data", names);

		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		response.getBody().setResponseData(map);
		return response;
	}

	@RequestMapping(path = "/loadCompanyType", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> loadCompanyType(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		//excuse
		List<CompanyTypeInfo> companyLst = core.loadCompanyType(jObject);
		Map<Object, Object> output = new HashMap<Object, Object>();
		output.put("CompanyTypes", companyLst);

		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		response.getBody().setResponseData(output);
		return response;
	}
	
	@GetMapping("/currencies")
	public ResponseEntity<ResponseMessage> getAllCurrencies() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		List<TbCurrency> cur = core.getAllCurrencies();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("currencies", cur);

		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		response.getBody().setResponseData(map);
		return response;
	}
	
	@GetMapping("/getImage/{tablename}/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable String tablename,@PathVariable String id) {
		
		String imageData = core.getImage(tablename, id);
		
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
                .body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
    }

	@PostMapping("/promotion")
	public ResponseEntity<ResponseMessage> promotion(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		if (StringUtility.isEmpty(jObject.get("suserid")) || StringUtility.isEmpty(jObject.get("promotionid"))
				|| StringUtility.isEmpty(jObject.get("promotiontype"))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		else {
			String promotionType = jObject.get("promotiontype").getAsString();
			if(promotionType.equals("VOUCHER")) {
				List<PromotionModel> result = core.voucherPromote(jObject);
				if(result.size() == 1) {
					if(result.get(0).getNid() == -1) {
						responseMessage.setResponseCode(configUtil.getProperty("cob.core.voucher-expire.code"));
						responseMessage.setResponseMessage(configUtil.getProperty("cob.core.voucher-expire.msg"));
						return response;
					}
					else if(result.get(0).getNid() == -2){
						responseMessage.setResponseCode(configUtil.getProperty("cob.core.voucher-count.code"));
						responseMessage.setResponseMessage(configUtil.getProperty("cob.core.voucher-count.msg"));
						return response;
					}
				}
				else if(result.size() == 0) {
					responseMessage.setResponseCode(configUtil.getProperty("cob.core.voucher.soldout.code"));
					responseMessage.setResponseMessage(configUtil.getProperty("cob.core.voucher.soldout.msg"));
					return response;
				}
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("voucher", result);
				response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
				response.getBody().setResponseData(map);
			}
			else if(promotionType.equals("COIN")) {
				
			}
		}
		return response;
	}
	
	@PostMapping("/getPromotion")
	public ResponseEntity<ResponseMessage> getPromotion(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		if (StringUtility.isEmpty(jObject.get("suserid")) || StringUtility.isEmpty(jObject.get("promotionid"))) {
			responseMessage.setResponseCode(configUtil.getProperty("cob.core.input.null.code"));
			responseMessage.setResponseMessage(configUtil.getProperty("cob.core.input.null.msg"));
		}
		else {
			String userId = jObject.get("suserid").getAsString();
			String promotionId = jObject.get("promotionid").getAsString();
			TbPromotionHistory p = core.getPromotionByUserIdPromotionId(userId, promotionId);
			if(p != null) {
				responseMessage.setResponseCode(configUtil.getProperty("cob.core.voucher.already.code"));
				responseMessage.setResponseMessage(configUtil.getProperty("cob.core.voucher.already.msg"));
			}else {
				responseMessage.setResponseCode(configUtil.getProperty("cob.core.voucher.yet.code"));
				responseMessage.setResponseMessage(configUtil.getProperty("cob.core.voucher.yet.msg"));
			}
		}
		return response;
	}
	
	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void run() {
		log.info("Current time is :: " + Calendar.getInstance().getTime());
	}
}
