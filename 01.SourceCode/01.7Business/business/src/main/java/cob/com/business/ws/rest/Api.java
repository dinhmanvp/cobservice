package cob.com.business.ws.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.business.entities.TbMyproduct;
import cob.com.business.entities.TbOrder;
import cob.com.business.entities.TbProductCate;
import cob.com.business.entities.TbWorkinginfos;
import cob.com.business.services.Business;
import cob.com.business.utils.ConfigUtility;
import cob.com.business.utils.StringUtility;
import cob.com.business.ws.models.MydealInfo;
import cob.com.business.ws.models.ResponseMessage;
import cob.com.business.ws.param.Parameter;
import cob.com.business.ws.validate.ValidateInput;

//import cob.com.core.ws.param.Parameter;
/*
 * @author ldman 2019/07/06 REST API
 */
@RestController
public class Api {

	@Autowired
	private ConfigUtility configUtil;

	@Autowired
	private Business business;
	
	
	private static final Logger log = LoggerFactory.getLogger(Api.class);


	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> testApi() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// ;
		business.testSendEmail("a840e90291b6400265d9d0f5028cfe18cdb2dd2c22e94f33d21f6c5fed814ca7", "");
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData("contact info vuvanbao@gmail.com");
		return respone;
	}

	@RequestMapping(path = "/registerProductCate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> registerProductCate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.registerProductCate(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisProCate.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisProCate.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisProCate.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisProCate.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/productCateUpdate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> productCateUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.productCateUpdate(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateProCate.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateProCate.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateProCate.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateProCate.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/getProductCate/{partnerId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getProductCate(@PathVariable("partnerId") String partnerId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		List<TbProductCate> lstProCate = business.getProductCate(partnerId);

		HashMap<String, List<TbProductCate>> result = new HashMap<>();
		result.put("productCateInfos", lstProCate);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.getProductCate.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.getProductCate.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/registerProduct", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> registerProduct(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.registerProduct(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisPro.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisPro.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisPro.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisPro.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/productUpdate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> productUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.productUpdate(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateProCate.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateProCate.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateProCate.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateProCate.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/getProduct", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getProduct(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
		String userId = jObject.get(Parameter.S_SELLER_USER_ID).getAsString();
		String productId = jObject.get(Parameter.S_PRODUCT_ID).getAsString();

		List<TbMyproduct> lstPro = business.getProduct(partnerId, userId, productId);

		HashMap<String, List<TbMyproduct>> result = new HashMap<>();
		result.put("productInfos", lstPro);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.getProduct.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.getProduct.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/registerMydeal", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> registerMydeal(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.registerMydeal(jObject);
		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisMydeal.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisMydeal.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.regisMydeal.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.regisMydeal.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/updateMydeal", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> updateMydeal(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		boolean result = business.updateMydeal(jObject);

		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateMydeal.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateMydeal.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateMydeal.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateMydeal.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/getMydeal", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getMydeal(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
//		String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
//		String userId = jObject.get(Parameter.S_USER_ID).getAsString();
//		String mydealId = jObject.get(Parameter.S_MYDEAL_ID).getAsString();
		
		//log.info("input getMydeal:" + jObject.getAsString());
		
		int pageNumber = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_NUMBER)))
			pageNumber = jObject.get(Parameter.PAGE_NUMBER).getAsInt();

		// pageSize
		int pageSize = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_SIZE)))
		{
			pageSize = jObject.get(Parameter.PAGE_SIZE).getAsInt();	
			if(pageSize == 0) {
				pageSize = 1;
			}
		}
		int totalRow = 0;
		BigDecimal totalpages = new BigDecimal(0);
		
		List<MydealInfo> dealinfo = business.getMydeal(jObject);
		
		Map<Object, Object> pageInfo = new HashMap<Object, Object>();
		if (dealinfo.size() > 0) {
			totalRow = dealinfo.get(0).getTotalRows();
			
//			totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize)).setScale(2,
//					RoundingMode.CEILING);
			totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize), 2, RoundingMode.UP);
			totalpages = totalpages.setScale(0, RoundingMode.UP);
			
		}
		pageInfo.put("totalrows", totalRow);
		pageInfo.put("pagesize", pageSize);
		pageInfo.put("currentpage", pageNumber);
		pageInfo.put("totalpages", totalpages);

		HashMap<String, Object> result = new HashMap<>();
		result.put("mydealInfos", dealinfo);
		result.put("pageInfo", pageInfo);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.getMydeal.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.getMydeal.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/getMydealByFriends", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getMydealByFriends(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		String userId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(jObject.get(Parameter.S_USER_ID)))
			userId = jObject.get(Parameter.S_USER_ID).getAsString();

		String languageCode = "VN";
		if (!StringUtility.isEmpty(jObject.get(Parameter.LANGUAGE_CODE)))
			languageCode = StringUtils.upperCase(jObject.get(Parameter.LANGUAGE_CODE).getAsString());

		String contentSearch = StringUtils.EMPTY;
		String contenElement = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(jObject.get(Parameter.CONTENT_SEARCH)))
		{
			String contendata = jObject.get(Parameter.CONTENT_SEARCH).getAsString().trim();
			String contendataInsert = ("(\\w*" + contendata.replaceAll("( )+", "\\\\w*)|(\\\\w*") + "\\w*)");
			contenElement = StringUtils.upperCase(StringUtility.removeUnicodeString(contendataInsert));
			contentSearch = StringUtils.upperCase(StringUtility.removeUnicodeString(contendata));
		}
		
		int pageNumber = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_NUMBER)))
			pageNumber = jObject.get(Parameter.PAGE_NUMBER).getAsInt();

		// pageSize
		int pageSize = 1;
		if (!StringUtility.isEmpty(jObject.get(Parameter.PAGE_SIZE)))
		{
			pageSize = jObject.get(Parameter.PAGE_SIZE).getAsInt();	
			if(pageSize == 0) {
				pageSize = 1;
			}
		}
		int totalRow = 0;

		String partnerId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(jObject.get(Parameter.S_PARTNER_ID)))
			partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();

		String groubBusId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(jObject.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID)))
			groubBusId = jObject.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID).getAsString();
		BigDecimal totalpages = new BigDecimal(0);
		List<MydealInfo> dealinfo = business.getDealByFriends(userId, languageCode, contentSearch, pageNumber, pageSize,
				partnerId, groubBusId, contenElement);
		Map<Object, Object> pageInfo = new HashMap<Object, Object>();
		if (dealinfo.size() > 0) {
			totalRow = dealinfo.get(0).getTotalRows();
			
//			totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize)).setScale(2,
//					RoundingMode.CEILING);
			totalpages = new BigDecimal(totalRow).divide(new BigDecimal(pageSize), 2, RoundingMode.UP);
			totalpages = totalpages.setScale(0, RoundingMode.UP);
			
		}
		pageInfo.put("totalrows", totalRow);
		pageInfo.put("pagesize", pageSize);
		pageInfo.put("currentpage", pageNumber);
		pageInfo.put("totalpages", totalpages);

		HashMap<String, Object> result = new HashMap<>();
		result.put("mydealInfos", dealinfo);
		result.put("pageInfo", pageInfo);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/getWorkingInfo/{partnerId}/{monthOfYear}/{BusinessServiceId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getWorkingInfo(@PathVariable("partnerId") String partnerId,
			@PathVariable("monthOfYear") String monthOfYear,
			@PathVariable("BusinessServiceId") String BusinessServiceId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		List<TbWorkinginfos> workinfo = business.getWorkingInfo(partnerId, monthOfYear, BusinessServiceId);

		HashMap<String, List<TbWorkinginfos>> result = new HashMap<>();
		result.put("workingInfos", workinfo);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.getWorkingInfo.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.getWorkingInfo.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@RequestMapping(path = "/RegisterMedicalAppointment", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> RegisterMedicalAppointment(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		String result = business.registerMedicalAppointment(jObject);

		if (result == "error") {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.registerOder.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.registerOder.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (result == "Caerror") {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.registerOderCa.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.registerOderCa.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (result == "notwork") {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.registerOderCh.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.registerOderCh.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else if (result == "ngaycu") {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.registerOderkk.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.registerOderkk.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else if(result.equals("appointed")) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.regisorder.is-appointed.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.regisorder.is-appointed.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else if(result.equals("closed")) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.regisorder.closed.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.regisorder.closed.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		TbOrder order = business.getByOrderNumber(result);
		HashMap<String, Object> resultend = new HashMap<>();
		resultend.put("sorderNumber", order);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.registerOder.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.registerOder.success.msg"));
		respone.getBody().setResponseData(resultend);
		return respone;
	}

	@RequestMapping(path = "/OrderUpdate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> OderUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
//		JsonObject jObject = jElement.getAsJsonObject();
		JsonArray jObject = jElement.getAsJsonObject().get("orders").getAsJsonArray();

		boolean result = business.OderUpdate(jObject);

		if (result == true) {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateOrder.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateOrder.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		} else {
			respone.getBody().setResponseCode(configUtil.getProperty("cob.business.updateOrder.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.updateOrder.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}

	@RequestMapping(path = "/getOrder/{partnerId}/{sbusinessServiceId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getOder(@PathVariable("partnerId") String partnerId,
			@PathVariable("sbusinessServiceId") String sbusinessServiceId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		List<TbOrder> LstOrder = business.getOder(partnerId, sbusinessServiceId);

//		HashMap<String, List<TbOrder>> result = new HashMap<>();
//		result.put("OrderInfos", LstOrder);
		Map<String, Object> result = getOrderDetails(LstOrder);

		respone.getBody().setResponseCode(configUtil.getProperty("cob.business.getOder.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.business.getOder.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}

	@PostMapping("/getOrderByBuyerUser")
	public ResponseEntity<ResponseMessage> getOrderByBuyerUser(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> resposne = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.S_BUYER_USER_ID))) {
			resposne.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			resposne.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		} else {
			String buyerUserId = jObject.get(Parameter.S_BUYER_USER_ID).getAsString();
			String orderStatusId = jObject.get(Parameter.N_ORDER_STATUS_ID).getAsString();
			// orders
			List<TbOrder> orders = business.getOrderBuyerUserId(buyerUserId, orderStatusId);
			if (orders != null) {
				Map<String, Object> result = getOrderDetails(orders);
				resposne.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
				resposne.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
				resposne.getBody().setResponseData(result);
			}
		}
		return resposne;
	}

	@PostMapping("/getOderBySellerPartnerIdAndStatus")
	public ResponseEntity<ResponseMessage> getOderBySellerPartnerId(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> resposne = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		if (StringUtility.isEmpty(jObject.get(Parameter.S_SELLER_PARTNER_ID))
				|| StringUtility.isEmpty(jObject.get(Parameter.N_ORDER_STATUS_ID))) {
			resposne.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			resposne.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		} else {
			String sellerPartnerId = jObject.get(Parameter.S_SELLER_PARTNER_ID).getAsString();
			Integer status = jObject.get(Parameter.N_ORDER_STATUS_ID).getAsInt();
			List<TbOrder> orders = business.getOrderBySellerPartnerIdAndStatus(sellerPartnerId, status);
			if (orders != null) {
				Map<String, Object> result = getOrderDetails(orders);
				resposne.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
				resposne.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
				resposne.getBody().setResponseData(result);
			}
		}
		return resposne;
	}

	Map<String, Object> getOrderDetails(List<TbOrder> orders) {

		Gson gson = new Gson();
		// partner name parse
		Object partnerNames = business.getPartnerNames();
		JsonElement jElement = gson.toJsonTree(partnerNames).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		JsonObject body = jObject.get("body").getAsJsonObject();
		JsonObject responseData = body.get("responseData").getAsJsonObject();
		JsonArray data = responseData.get("data").getAsJsonArray();

		// groupbusiness parse
		Object groupBuzCateNames = business.groupBusinessCateName();
		JsonElement groupBuzjElement = gson.toJsonTree(groupBuzCateNames).getAsJsonObject();
		JsonObject groupBuzJObject = groupBuzjElement.getAsJsonObject();
		JsonObject groupBuzBody = groupBuzJObject.get("body").getAsJsonObject();
		JsonObject groupBuzResponseData = groupBuzBody.get("responseData").getAsJsonObject();
		JsonArray groupBuzData = groupBuzResponseData.get("data").getAsJsonArray();

		// List<TbOrder> orders = business.getOrderBuyerUserId(buyerUserId);

		Map<String, TbOrder> orderMap = new HashMap<String, TbOrder>();
		JsonArray jArray = new JsonArray();
		for (TbOrder tbOrder : orders) {
			orderMap.clear();
			orderMap.put("order", tbOrder);
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			JsonElement jE = gson.toJsonTree(orderMap).getAsJsonObject();
			JsonObject jO = jE.getAsJsonObject();
			JsonObject jNew = new JsonObject();
			jNew = jO.get("order").getAsJsonObject();
			// add partner name
			Iterator<JsonElement> iterator = data.iterator();
			while (iterator.hasNext()) {
				JsonElement je = iterator.next();
				JsonObject jo = je.getAsJsonObject();

				if (jo.get("sPartnerId") != null) {
					if (jo.get("sPartnerId").getAsString().equals(tbOrder.getSSellerPartnerId())) {
						String formatedValue = "ECO-" + jNew.get("nAppointmentNumber").getAsString();
						jNew.addProperty("nAppointmentNumber", formatedValue);
						jNew.add("sPartnerNameCn", jo.get("sPartnerNameCn"));
						jNew.add("sPartnerNameEn", jo.get("sPartnerNameEn"));
						jNew.add("sPartnerNameVn", jo.get("sPartnerNameVn"));
						break;
					}
				}
			}

			// add group business cate name
			Iterator<JsonElement> groupBuzIterator = groupBuzData.iterator();
			while (groupBuzIterator.hasNext()) {
				JsonElement je = groupBuzIterator.next();
				JsonObject jo = je.getAsJsonObject();
				if (jo.get("sGroupBusinessCateId") != null) {
					if (jo.get("sGroupBusinessCateId").getAsString().equals(tbOrder.getSGroupBusinessCateId())) {
						jNew.add("sGroupBusinessCateNameVn", jo.get("sGroupBusinessCateNameVn"));
						jNew.add("sGroupBusinessNameCateCn", jo.get("sGroupBusinessNameCateCn"));
						jNew.add("sGroupBusinessNameCateEn", jo.get("sGroupBusinessNameCateEn"));
						break;
					}
				}

			}
			jArray.add(jNew);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String orderJsonString = new Gson().toJson(jArray).replace("\"{\\", "{").replace("\\\"}\"", "\"}")
				.replace("\\\"", "\"");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		JsonNode node = null;
		try {
			node = mapper.readTree(orderJsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		result.put("order", node);
		return result;
	}

	@PostMapping("/registerOrderByEcom")
	public ResponseEntity<ResponseMessage> registerOrderByEcom(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		//validate
		ValidateInput validate = new ValidateInput(configUtil);
		responseMessage = validate.registerOrderByEcom(jObject);
		if(!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			response.getBody().setResponseCode(responseMessage.getResponseCode());
			response.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return response;
		}
		//excuse
		TbOrder isSuccess = business.registerOrderByEcom(jObject);
		if (StringUtils.isEmpty(isSuccess.getSOrderNumber())) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.regis-order.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.regis-order.success.msg"));
			return response;
		}
		//return
		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		return response;
	}
	
	@PostMapping("/likeDeal")
	public ResponseEntity<ResponseMessage> likeDeal(@RequestBody Object input){
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		if(jObject.get(Parameter.S_MYDEAL_ID) != null && jObject.get(Parameter.S_USER_ID) != null) {
			String dealId = jObject.get(Parameter.S_MYDEAL_ID).getAsString();
			String userId = jObject.get(Parameter.S_USER_ID).getAsString();
			Integer result = business.likeDeal(dealId, userId);
			if(result == 1) {
				response.getBody().setResponseCode(configUtil.getProperty("cob.like-deal.add.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.like-deal.add.msg"));
			}else {
				response.getBody().setResponseCode(configUtil.getProperty("cob.like-deal.delete.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.like-deal.delete.msg"));
			}
		}
		else {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		}
		return response;
	}

	@GetMapping("/getImage/{tablename}/{id}/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String tablename,@PathVariable String id, @PathVariable String imageId) {
		
		String imageData = business.getImage(tablename, id, imageId);
		
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
                .body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
    }
}
