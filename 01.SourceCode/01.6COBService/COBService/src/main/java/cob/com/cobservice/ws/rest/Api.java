package cob.com.cobservice.ws.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

import cob.com.cobservice.entities.TbAdviseService;
import cob.com.cobservice.entities.TbAdviseServiceDetail;
import cob.com.cobservice.entities.TbCurrency;
import cob.com.cobservice.entities.TbServiceUnit;
import cob.com.cobservice.entities.TbStartupPackage;
import cob.com.cobservice.entities.TbStartupPackageItem;
import cob.com.cobservice.services.COBServices;
import cob.com.cobservice.utils.ConfigUtility;
import cob.com.cobservice.utils.StringUtility;
import cob.com.cobservice.validate.ValidateInput;
import cob.com.cobservice.ws.models.ResponseMessage;
import cob.com.cobservice.ws.models.StartupPackageItemInfo;
import cob.com.cobservice.ws.models.TbAdviseServicePricingModel;
import cob.com.cobservice.ws.models.TbStartupPackageModel;
import cob.com.cobservice.ws.param.Parameter;

@RestController
public class Api {

	@Autowired
	private ConfigUtility configUtil;

	@Autowired
	private COBServices cobservice;

	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> testApi() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// ;

		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData("contact info vuvanbaonn@gmail.com");
		return respone;
	}

	@RequestMapping(path = "/getAdviseService", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getAdviseService() {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		List<TbAdviseService> AdviseServices = cobservice.getAdviseServices();

		if (AdviseServices != null) {
			HashMap<String, List<TbAdviseService>> result = new HashMap<>();
			result.put("AdviseServices", AdviseServices);

			respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			respone.getBody().setResponseData(result);
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/getServiceUnit", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getServiceUnit() {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		List<TbServiceUnit> ServiceUnit = cobservice.getServiceUnit();

		HashMap<String, List<TbServiceUnit>> result = new HashMap<>();
		result.put("ServiceUnit", ServiceUnit);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/getAdviseServicesPricing", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getAdviseServicesPricing(@RequestBody Object input) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		String adviseServiceId = "";
		String serviceUnitId = "";
		String languageCode = "";
		String contentSearch = "";
		if (jObject.get("adviseServiceId") != null) {
			adviseServiceId = jObject.get("adviseServiceId").getAsString();
		}
		if (jObject.get("serviceUnitId") != null) {
			serviceUnitId = jObject.get("serviceUnitId").getAsString();
		}
		if (jObject.get("languageCode") != null) {
			languageCode = jObject.get("languageCode").getAsString();
		}
		if (jObject.get("contentSearch") != null) {
			contentSearch = jObject.get("contentSearch").getAsString();
		}

		int pageNumber = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_NUMBER)))
			pageNumber = jObject.get(Parameter.PAGE_NUMBER).getAsInt();

		int pageSize = 10;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_SIZE))
				&& !"0".equals(jObject.get(Parameter.PAGE_SIZE).getAsString())) {
			pageSize = jObject.get(Parameter.PAGE_SIZE).getAsInt();
		}

		int totalRow = 0;

		List<TbAdviseServicePricingModel> AdviseServicePricing = cobservice.getAdviseServicesPricing(adviseServiceId,
				serviceUnitId, languageCode, contentSearch);
		// paging
		totalRow = AdviseServicePricing.size();
		BigDecimal totalpages = new BigDecimal(0);
		totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize), 2, RoundingMode.UP);
		totalpages = totalpages.setScale(0, RoundingMode.UP);
		List<TbAdviseServicePricingModel> pricingPaging = new ArrayList<TbAdviseServicePricingModel>();
		int getFrom = (pageNumber - 1) * pageSize;
		int getTo = (getFrom + pageSize) > totalRow ? totalRow : getFrom + pageSize;
		if (getTo > getFrom) {
			pricingPaging = AdviseServicePricing.subList(getFrom, getTo);
		}

		Map<Object, Object> map = new HashMap<Object, Object>();
		// put to map
		Map<Object, Object> pageInfo = new HashMap<Object, Object>();
		pageInfo.put("totalrows", totalRow);
		pageInfo.put("pagesize", pageSize);
		pageInfo.put("currentpage", pageNumber);
		pageInfo.put("totalpages", totalpages);
		map.put("pageInfo", pageInfo);

		map.put("AdviseServicePricing", pricingPaging);

		respone.getBody().setResponseData(map);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return respone;
	}

	@RequestMapping(path = "/getAdviseServicesDetail", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getAdviseServicesDetail(@RequestBody Object input) {

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		String adviseServiceId = "";
		if (!StringUtils.isEmpty(jObject.get("adviseServiceId"))) {
			adviseServiceId = jObject.get("adviseServiceId").getAsString();
		}
		int pageNumber = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_NUMBER)))
			pageNumber = jObject.get(Parameter.PAGE_NUMBER).getAsInt();

		int pageSize = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_SIZE))) {
			pageSize = jObject.get(Parameter.PAGE_SIZE).getAsInt();
			if (pageSize == 0) {
				pageSize = 1;
			}
		}

		int totalRow = 0;
		List<TbAdviseServiceDetail> AdviseServicesDetail = cobservice.getAdviseServicesDetail(adviseServiceId);

		// paging
		totalRow = AdviseServicesDetail.size();
		BigDecimal totalpages = new BigDecimal(0);
		totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize), 2, RoundingMode.UP);
		totalpages = totalpages.setScale(0, RoundingMode.UP);
		List<TbAdviseServiceDetail> serviceDetailPaging = new ArrayList<TbAdviseServiceDetail>();
		int getFrom = (pageNumber - 1) * pageSize;
		int getTo = (getFrom + pageSize) > totalRow ? totalRow : getFrom + pageSize;
		if (getTo > getFrom) {
			serviceDetailPaging = AdviseServicesDetail.subList(getFrom, getTo);
		}

		HashMap<String, Object> result = new HashMap<>();
		Map<Object, Object> pageInfo = new HashMap<Object, Object>();
		pageInfo.put("totalrows", totalRow);
		pageInfo.put("pagesize", pageSize);
		pageInfo.put("currentpage", pageNumber);
		pageInfo.put("totalpages", totalpages);
		result.put("pageInfo", pageInfo);
		result.put("AdviseServicesDetails", serviceDetailPaging);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@PostMapping("/registerAgent")
	public ResponseEntity<ResponseMessage> registerAgent(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = cobservice.registerAgent(jObject);

		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.agent.create.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.agent.create.success.msg"));
			respone.getBody().setResponseData(result);
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.agent.create.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.agent.create.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@PostMapping("/agentUpdate")
	public ResponseEntity<ResponseMessage> agentUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = cobservice.agentUpdate(jObject);

		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.agent.update.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.agent.update.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.agent.update.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.agent.update.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@PutMapping("/adviseServicesUpdate")
	public ResponseEntity<ResponseMessage> adviseServicesUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateAdviseServicesInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbAdviseService entity = new TbAdviseService();
			entity.setNAdviseServiceIsAvailable(jObject.get(Parameter.ADVISE_SERVICE_IS_AVAILABLE).getAsInt());
			entity.setNAdviseServiceRating(jObject.get(Parameter.ADVISE_SERVICE_RATING).getAsBigDecimal());
			entity.setSAdviseServiceIcon(jObject.get(Parameter.ADVISE_SERVICE_ICON).getAsString());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSAdviseServiceNameCn(jObject.get(Parameter.ADVISE_SERVICE_NAME_CN).getAsString());
			entity.setSAdviseServiceNameVn(jObject.get(Parameter.ADVISE_SERVICE_NAME_VN).getAsString());
			entity.setSAdviseServiceNameEn(jObject.get(Parameter.ADVISE_SERVICE_NAME_EN).getAsString());
			entity.setSAdviseServiceShortdescCn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_CN).getAsString());
			entity.setSAdviseServiceShortdescEn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_EN).getAsString());
			entity.setSAdviseServiceShortdescVn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_VN).getAsString());
			Integer result = cobservice.updateAdviseServices(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-not-exist.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-not-exist.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerAdviseServices")
	public ResponseEntity<ResponseMessage> registerAdviseServices(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateAdviseServicesInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbAdviseService entity = new TbAdviseService();
			entity.setNAdviseServiceIsAvailable(jObject.get(Parameter.ADVISE_SERVICE_IS_AVAILABLE).getAsInt());
			entity.setNAdviseServiceRating(jObject.get(Parameter.ADVISE_SERVICE_RATING).getAsBigDecimal());
			entity.setSAdviseServiceIcon(jObject.get(Parameter.ADVISE_SERVICE_ICON).getAsString());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSAdviseServiceNameCn(jObject.get(Parameter.ADVISE_SERVICE_NAME_CN).getAsString());
			entity.setSAdviseServiceNameVn(jObject.get(Parameter.ADVISE_SERVICE_NAME_VN).getAsString());
			entity.setSAdviseServiceNameEn(jObject.get(Parameter.ADVISE_SERVICE_NAME_EN).getAsString());
			entity.setSAdviseServiceShortdescCn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_CN).getAsString());
			entity.setSAdviseServiceShortdescEn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_EN).getAsString());
			entity.setSAdviseServiceShortdescVn(jObject.get(Parameter.ADVISE_SERVICE_SHORTDESC_VN).getAsString());
			Integer result = cobservice.registerAdviseServices(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-existed.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerAdviseServicesDetail")
	public ResponseEntity<ResponseMessage> registerAdviseServicesDetail(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateAdviseServiceDetailsInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbAdviseServiceDetail entity = new TbAdviseServiceDetail();
			entity.setAdviseServiceDetailId(jObject.get(Parameter.ADVISE_SERVICE_DETAIL_ID).getAsString());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSContent(jObject.get(Parameter.CONTENT).getAsString());
			Integer result = cobservice.registerAdviseServicesDetail(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-existed.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.reference-not-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.reference-not-existed.error.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/adviseServicesDetailUpdate")
	public ResponseEntity<ResponseMessage> adviseServicesDetailUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateAdviseServiceDetailsInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbAdviseServiceDetail entity = new TbAdviseServiceDetail();
			entity.setAdviseServiceDetailId(jObject.get(Parameter.ADVISE_SERVICE_DETAIL_ID).getAsString());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSContent(jObject.get(Parameter.CONTENT).getAsString());
			Integer result = cobservice.updateAdviseServicesDetail(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-not-exist.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-not-exist.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.reference-not-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.reference-not-existed.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerServiceUnit")
	public ResponseEntity<ResponseMessage> registerServiceUnit(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateServiceUnitInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbServiceUnit entity = new TbServiceUnit();
			entity.setSServiceUnitId(jObject.get(Parameter.SERVICE_UNIT_ID).getAsString());
			entity.setSDurationType(jObject.get(Parameter.DURATION_TYPE).getAsString());
			entity.setSUnitNameCn(jObject.get(Parameter.UNIT_NAME_CN).getAsString());
			entity.setSUnitNameEn(jObject.get(Parameter.UNIT_NAME_EN).getAsString());
			entity.setSUnitNameEn(jObject.get(Parameter.UNIT_NAME_VN).getAsString());
			Integer result = cobservice.registerServiceUnit(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-existed.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.error.msg"));
				break;

			}
		}
		return response;
	}

	@PutMapping("/serviceUnitUpdate")
	public ResponseEntity<ResponseMessage> serviceUnitUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateServiceUnitInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbServiceUnit entity = new TbServiceUnit();
			entity.setSServiceUnitId(jObject.get(Parameter.SERVICE_UNIT_ID).getAsString());
			entity.setSDurationType(jObject.get(Parameter.DURATION_TYPE).getAsString());
			entity.setSUnitNameCn(jObject.get(Parameter.UNIT_NAME_CN).getAsString());
			entity.setSUnitNameEn(jObject.get(Parameter.UNIT_NAME_EN).getAsString());
			entity.setSUnitNameEn(jObject.get(Parameter.UNIT_NAME_VN).getAsString());
			Integer result = cobservice.updateServiceUnit(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-not-exist.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-not-exist.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/registerStarupPackage")
	public ResponseEntity<ResponseMessage> registerStarupPackage(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateStartupPackageInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbStartupPackage entity = new TbStartupPackage();
			entity.setNDiscountPrice(jObject.get(Parameter.DISCOUNT_PRICE).getAsString());
			entity.setNIsAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setNPrice(jObject.get(Parameter.PRICE).getAsString());
			entity.setNSalePricce(jObject.get(Parameter.SALE_PRICE).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setSPackageDescCn(jObject.get(Parameter.PACKAGE_DESC_CN).getAsString());
			entity.setSPackageDescEn(jObject.get(Parameter.PACKAGE_DESC_EN).getAsString());
			entity.setSPackageDescVn(jObject.get(Parameter.PACKAGE_DESC_VN).getAsString());
			entity.setSPackageIcon(jObject.get(Parameter.PACKAGE_ICON).getAsString());
			entity.setSPackageNameCn(jObject.get(Parameter.PACKAGE_NAME_CN).getAsString());
			entity.setSPackageNameEn(jObject.get(Parameter.PACKAGE_NAME_EN).getAsString());
			entity.setSPackageNameVn(jObject.get(Parameter.PACKAGE_NAME_VN).getAsString());
			entity.setSStartupPackageId(jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString());
			Integer result = cobservice.registerStarupPackage(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-existed.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.error.msg"));
				break;

			}
		}
		return response;
	}

	@PutMapping("/starupPackageUpdate")
	public ResponseEntity<ResponseMessage> starupPackageUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateStartupPackageInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbStartupPackage entity = new TbStartupPackage();
			entity.setNDiscountPrice(jObject.get(Parameter.DISCOUNT_PRICE).getAsString());
			entity.setNIsAvailable(jObject.get(Parameter.IS_AVAILABLE).getAsInt());
			entity.setNPrice(jObject.get(Parameter.PRICE).getAsString());
			entity.setNSalePricce(jObject.get(Parameter.SALE_PRICE).getAsString());
			entity.setSGroupBusinessId(jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString());
			entity.setSPackageDescCn(jObject.get(Parameter.PACKAGE_DESC_CN).getAsString());
			entity.setSPackageDescEn(jObject.get(Parameter.PACKAGE_DESC_EN).getAsString());
			entity.setSPackageDescVn(jObject.get(Parameter.PACKAGE_DESC_VN).getAsString());
			entity.setSPackageIcon(jObject.get(Parameter.PACKAGE_ICON).getAsString());
			entity.setSPackageNameCn(jObject.get(Parameter.PACKAGE_NAME_CN).getAsString());
			entity.setSPackageNameEn(jObject.get(Parameter.PACKAGE_NAME_EN).getAsString());
			entity.setSPackageNameVn(jObject.get(Parameter.PACKAGE_NAME_VN).getAsString());
			entity.setSStartupPackageId(jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString());
			Integer result = cobservice.updateStarupPackage(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-not-exist.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-not-exist.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.error.msg"));
				break;
			}
		}
		return response;
	}

	@GetMapping("/getStarupPackage")
	public ResponseEntity<ResponseMessage> getStarupPackage(@RequestBody Object input) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.STARTUP_PACKAGE_ID))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		} else {
			String id = jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString();
			TbStartupPackage sPackage = cobservice.getStarupPackagesById(id);

			if (sPackage != null) {
				TbCurrency c = cobservice.getByCurrencyId(sPackage.getsCurrencyId());
				if (c != null) {
					TbStartupPackageModel model = new TbStartupPackageModel(sPackage, c.getSCurrencySymbol());
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("StartupPackage", model);
					response.getBody().setResponseData(map);
				}
				response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			}
			return response;
		}
	}

	@PostMapping("/getStarupPackages")
	public ResponseEntity<ResponseMessage> getStarupPackages(@RequestBody Object input) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.GROUP_BUSINESS_ID))
				|| StringUtility.isEmpty(jObject.get(Parameter.IS_AVAILABLE))
				|| StringUtility.isEmpty(jObject.get(Parameter.LANGUAGE_CODE))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		} else {
			String language = jObject.get(Parameter.LANGUAGE_CODE).getAsString();
			String id = jObject.get(Parameter.GROUP_BUSINESS_ID).getAsString();
			Integer isAvailable = jObject.get(Parameter.IS_AVAILABLE).getAsInt();
			List<TbStartupPackageModel> sPackage = cobservice.getStarupPackagesByGroupBusinessId(id, isAvailable,
					language);
			// List<TbStartupPackageModel> models = new ArrayList<TbStartupPackageModel>();

			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("StartupPackages", sPackage);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
			return response;
		}
	}

	@PostMapping("/registerStarupPackageItem")
	public ResponseEntity<ResponseMessage> registerStarupPackageItem(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateStartupPackageItemInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbStartupPackageItem entity = new TbStartupPackageItem();
			entity.setDFromDate(jObject.get(Parameter.FROM_DATE).getAsString());
			entity.setDToDate(jObject.get(Parameter.TO_DATE).getAsString());
			entity.setNDuration(jObject.get(Parameter.DURATION_TYPE).getAsInt());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSDurationType(jObject.get(Parameter.DURATION_TYPE).getAsString());
			entity.setSServiceUnitId(jObject.get(Parameter.SERVICE_UNIT_ID).getAsString());
			entity.setSStartupPackageId(jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString());
			entity.setSStartupPackageItemsId(jObject.get(Parameter.STARTUP_PACKAGE_ITEM_ID).getAsString());
			Integer result = cobservice.registerStarupPackageItem(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-existed.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.add.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.add.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.reference-not-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.reference-not-existed.error.msg"));
				break;
			}
		}
		return response;
	}

	@PutMapping("/starupPackageItemUpdate")
	public ResponseEntity<ResponseMessage> starupPackageItemUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateStartupPackageItemInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			TbStartupPackageItem entity = new TbStartupPackageItem();
			entity.setDFromDate(jObject.get(Parameter.FROM_DATE).getAsString());
			entity.setDToDate(jObject.get(Parameter.TO_DATE).getAsString());
			entity.setNDuration(jObject.get(Parameter.DURATION_TYPE).getAsInt());
			entity.setSAdviseServiceId(jObject.get(Parameter.ADVISE_SERVICE_ID).getAsString());
			entity.setSDurationType(jObject.get(Parameter.DURATION_TYPE).getAsString());
			entity.setSServiceUnitId(jObject.get(Parameter.SERVICE_UNIT_ID).getAsString());
			entity.setSStartupPackageId(jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString());
			entity.setSStartupPackageItemsId(jObject.get(Parameter.STARTUP_PACKAGE_ITEM_ID).getAsString());
			Integer result = cobservice.updateStarupPackageItem(entity);
			switch (result) {
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.success.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.success.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.id-not-exist.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.id-not-exist.error.msg"));
				break;
			case 3:
				response.getBody().setResponseCode(configUtil.getProperty("cob.update.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.update.error.msg"));
				break;
			case 4:
				response.getBody().setResponseCode(configUtil.getProperty("cob.reference-not-existed.error.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.reference-not-existed.error.msg"));
				break;
			}
		}
		return response;
	}

	@PostMapping("/getStarupPackageItems")
	public ResponseEntity<ResponseMessage> getStarupPackageItems(@RequestBody Object input) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.STARTUP_PACKAGE_ID))
				|| StringUtility.isEmpty(jObject.get(Parameter.LANGUAGE_CODE))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		}
		// excuse
		String id = jObject.get(Parameter.STARTUP_PACKAGE_ID).getAsString();
		String languageCode = jObject.get(Parameter.LANGUAGE_CODE).getAsString();
		List<StartupPackageItemInfo> sPackages = cobservice.getItemsByPackageId(id, languageCode);
		// return
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StartupPackageItems", sPackages);
		//
		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		response.getBody().setResponseData(map);
		return response;
	}

	@GetMapping("/getStarupPackageItem")
	public ResponseEntity<ResponseMessage> getStarupPackageItem(@RequestBody Object input) {

		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.STARTUP_PACKAGE_ITEM_ID))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
			return response;
		} else {
			String id = jObject.get(Parameter.STARTUP_PACKAGE_ITEM_ID).getAsString();
			TbStartupPackageItem sPackages = cobservice.getItem(id);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("StartupPackage", sPackages);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
			return response;
		}
	}
	
	@GetMapping("/getImage/{tablename}/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable String tablename, @PathVariable String id) {

		String imageData = cobservice.getImage(tablename, id);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
				.body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
	}

}