package cob.com.partner.ws.rest;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.partner.entities.TbPartnerBusinessService;
import cob.com.partner.entities.TbPartnerHoliday;
import cob.com.partner.entities.TbPartnerInfo;
import cob.com.partner.entities.TbPartnerName;
import cob.com.partner.entities.TbPartnerRule;
import cob.com.partner.entities.TbPartnerWorkingtime;
import cob.com.partner.services.Partner;
import cob.com.partner.utils.ConfigUtility;
import cob.com.partner.utils.StringUtility;
import cob.com.partner.validate.ValidateInput;
import cob.com.partner.ws.models.GroupbusinessCateInfo;
import cob.com.partner.ws.models.OrderModel;
import cob.com.partner.ws.models.PartnerAccountInfo;
import cob.com.partner.ws.models.PartnerInfo;
import cob.com.partner.ws.models.ResponseMessage;
import cob.com.partner.ws.param.Parameter;


//import cob.com.core.ws.param.Parameter;

/*
 * @author ldman 2019/07/06 REST API
 */
@RestController
public class Api {

	@Autowired
	private ConfigUtility configUtil;
	
	@Autowired 
	private Partner partner;
	
	
	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> testApi() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		//;
		
		
		respone.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		respone.getBody().setResponseData("contact info vuvanbao@gmail.com");
		return respone;
	}
	
	@RequestMapping(path = "/partnerCreate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> PartnerCreate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.partnerCreate(jObject);
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.create.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.create.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.create.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.create.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@RequestMapping(path = "/partnerUpdate", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> PartnerUpdate(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.partnerUpdate(jObject);
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.update.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.update.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.update.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.update.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/getPartners")
	public ResponseEntity<ResponseMessage> getPartners(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		String UserID = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(jObject.get("UserID"))) {
			UserID = jObject.get("UserID").getAsString();
		}
		
		String GroupBusinessId = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(jObject.get("GroupBusinessId"))) {
			GroupBusinessId = jObject.get("GroupBusinessId").getAsString();
		}
		
		String BusinessServiceId = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(jObject.get("BusinessServiceId"))) {
			BusinessServiceId = jObject.get("BusinessServiceId").getAsString();
		}
		
		String GroupBusinessTypeId = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(jObject.get("GroupBusinessTypeId"))) {
			GroupBusinessTypeId = jObject.get("GroupBusinessTypeId").getAsString();
		}
		
		Integer isApprove = -1;
		if(!StringUtility.isEmpty(jObject.get("isApprove"))) {
			isApprove = jObject.get("isApprove").getAsInt();
		}
		
		List<TbPartnerInfo> result = partner.getPartners(UserID,GroupBusinessId, BusinessServiceId,GroupBusinessTypeId,isApprove);
		if(result != null)
		{
			HashMap<String, List<TbPartnerInfo>> resultinfo = new HashMap<>();
			resultinfo.put("PartnerInfo", result);
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.get.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.get.success.msg"));
			respone.getBody().setResponseData(resultinfo);
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.get.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.get.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/getPartnersForWorking")
	public ResponseEntity<ResponseMessage> getPartnersForWorking(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		//valiudate
		ValidateInput validate = new ValidateInput(configUtil);
		responseMessage = validate.getPartnersForWorking(jObject);
		if(!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			respone.getBody().setResponseCode(responseMessage.getResponseCode());
			respone.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return respone;
		}		
		//excuse		
		List<PartnerInfo> result = partner.getPartnersForWorking(jObject);
		//return
		HashMap<String, Object> resultinfo = new HashMap<>();
		resultinfo.put("PartnerInfo", result);
		
		respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.get.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.get.success.msg"));
		respone.getBody().setResponseData(resultinfo);
		return respone;
	}
	
	@RequestMapping(path = "/getPartnerBizcates/{partnerId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getPartnerBizcates(@PathVariable("partnerId")String partnerId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		//excuse
		List<GroupbusinessCateInfo> lstPartnerBiz = partner.getPartnerBizcates(partnerId);
		//				
		if(lstPartnerBiz != null)
		{
			HashMap<String, List<GroupbusinessCateInfo>> result = new HashMap<>();
			result.put("PartnerBiz", lstPartnerBiz);
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerBiz.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerBiz.success.msg"));
			respone.getBody().setResponseData(result);
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerBiz.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerBiz.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	
	
	@RequestMapping(path = "/getPartnerRules", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getPartnerRules() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		List<TbPartnerRule> lstPartnerRule = partner.getPartnerRules();
		
		if(lstPartnerRule != null)
		{
			HashMap<String, List<TbPartnerRule>> result = new HashMap<>();
			result.put("PartnerRules", lstPartnerRule);
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerRule.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerRule.success.msg"));
			respone.getBody().setResponseData(result);
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerRule.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerRule.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@RequestMapping(path = "/getPartnerBusinessServices/{partnerId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getPartnerBusinessServices(@PathVariable("partnerId")String partnerId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		List<TbPartnerBusinessService> lstPartnerBuse = partner.getPartnerBusinessServices(partnerId);
		
		if(lstPartnerBuse != null)
		{
			HashMap<String, List<TbPartnerBusinessService>> result = new HashMap<>();
			result.put("PartnerBusinessServices", lstPartnerBuse);
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerBuse.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerBuse.success.msg"));
			respone.getBody().setResponseData(result);
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerBuse.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerBuse.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	
	@RequestMapping(path = "/getPartnerAccounts", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getPartnerAccounts(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		//validate
		ValidateInput validate = new ValidateInput(configUtil);	
		responseMessage = validate.getPartnerAccounts(jObject);
		if(!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			respone.getBody().setResponseCode(responseMessage.getResponseCode());
			respone.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return respone;
		}
		//excuse
		List<PartnerAccountInfo> lstPartnerAcc = partner.getPartnerAccounts(jObject);
		
		HashMap<String, Object> result = new HashMap<>();
		result.put("PartnerAccounts", lstPartnerAcc);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getPartnerBuse.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getPartnerBuse.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}
	
	@PostMapping(path = "/partnerAccountRemove/{partnerAccountId}")
	public ResponseEntity<ResponseMessage> partnerAccountRemove(@PathVariable("partnerAccountId")String partnerAccountId) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		boolean result = partner.partnerAccountRemove(partnerAccountId);
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.removePartnerAccount.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.removePartnerAccount.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.removePartnerAccount.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.removePartnerAccount.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/partnerAccountCreate")
	public ResponseEntity<ResponseMessage> RegisterAccount(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.partnerAccountCreate(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.createPartnerAccount.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.createPartnerAccount.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.createPartnerAccount.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.createPartnerAccount.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/partnerAccountUpdate")
	public ResponseEntity<ResponseMessage> partnerAccountUpdate(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.partnerAccountUpdate(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.updatePartnerAccount.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.updatePartnerAccount.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.updatePartnerAccount.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.updatePartnerAccount.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@RequestMapping(path = "/getPartnerWorkingtime", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> getPartnerWorkingtime(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
		String BusinessServiceId = jObject.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString();
		
		List<TbPartnerWorkingtime> Workingtime = partner.getPartnerWorkingtime(partnerId,BusinessServiceId);
		
		HashMap<String, List<TbPartnerWorkingtime>> result = new HashMap<>();
		result.put("PartnerWorkingtime", Workingtime);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getWorkingtime.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getWorkingtime.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
	}
	
	@PostMapping("/registerPartnerWorkingtime")
	public ResponseEntity<ResponseMessage> registerPartnerWorkingtime(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.registerPartnerWorkingtime(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.registWorkingtime.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.registWorkingtime.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.registWorkingtime.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.registWorkingtime.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/registerPartnerContract")
	public ResponseEntity<ResponseMessage> registerPartnerContract(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.registerPartnerContract(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.registContract.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.registContract.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.registContract.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.registContract.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/registerPartnerHoliday")
	public ResponseEntity<ResponseMessage> registerPartnerHoliday(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.registerPartnerHoliday(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.regispartho.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.regispartho.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.regispartho.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.regispartho.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	@PostMapping("/partnerHolidayUpdate")
	public ResponseEntity<ResponseMessage> partnerHolidayUpdate(@RequestBody Object input)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		boolean result = partner.partnerHolidayUpdate(jObject);
		
		
		if(result == true)
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.updatepartho.success.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.updatepartho.success.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
		else 
		{
			respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.updatepartho.error.code"));
			respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.updatepartho.error.msg"));
			respone.getBody().setResponseData("");
			return respone;
		}
	}
	
	
	@RequestMapping(path = "/getPartnerHoliday/{partnerId}/{monthOfYear}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getPartnerHoliday(@PathVariable("partnerId") String partnerId, @PathVariable("monthOfYear") String monthOfYear)
	{
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> respone = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		
		
		List<TbPartnerHoliday> LstPartHo = partner.getPartnerHoliday(partnerId, monthOfYear);
		
		HashMap<String, List<TbPartnerHoliday>> result = new HashMap<>();
		result.put(Parameter.PARTNER_HOLIDAYS, LstPartHo);
		respone.getBody().setResponseCode(configUtil.getProperty("cob.partner.getpartho.success.code"));
		respone.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getpartho.success.msg"));
		respone.getBody().setResponseData(result);
		return respone;
		
	}
	
	@GetMapping("/partnerNames")
	public ResponseEntity<ResponseMessage> getAllParterName() {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		List<TbPartnerName> resultList = partner.getAllParterName();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("data", resultList);		
		response.getBody().setResponseCode(configUtil.getProperty("cob.partner.getpartho.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.partner.getpartho.success.msg"));
		response.getBody().setResponseData(map);

		return response;
	}
	
	@PostMapping("/loadOrderForChoosing")
	public ResponseEntity<ResponseMessage> loadOrderForChoosing(@RequestBody Object input){
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		if(jObject.get(Parameter.S_PARTNER_ID) != null 
				&& jObject.get(Parameter.S_BUSINESS_SERVICE_ID) != null	
				&& jObject.get("appointmentDate")!= null) {
			String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
			String businesServiceId = jObject.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString();
			String groupBuzCate = jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID) == null ? ""
					: jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString();
			String apDate = jObject.get("appointmentDate").getAsString();
			String languageCode = jObject.get("languageCode") == null ? "" :jObject.get("languageCode").getAsString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date(System.currentTimeMillis());
			try {
				d = df.parse(apDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			List<OrderModel> orders = partner.getOrderForChoosing(partnerId, businesServiceId, groupBuzCate, d, languageCode);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("orders", orders);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
		}
		else {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		}
		return response;
	}
	
	@PostMapping("/loadOrderForSeller")
	public ResponseEntity<ResponseMessage> loadOrderForSeller(@RequestBody Object input){
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		if(jObject.get(Parameter.S_PARTNER_ID) != null 
				|| jObject.get(Parameter.S_BUSINESS_SERVICE_ID) != null	
				|| jObject.get("sSellerUserId")!= null
				|| jObject.get("appointmentDate")!= null) {
			String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
			String businesServiceId = jObject.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString();
			String sSellerUserId = jObject.get("sSellerUserId").getAsString();
			String groupBuzCate = jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID) == null ? ""
					: jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString();
			String apDate = jObject.get("appointmentDate").getAsString();
			String languageCode = jObject.get("languageCode") == null ? "" :jObject.get("languageCode").getAsString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date(System.currentTimeMillis());
			try {
				d = df.parse(apDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			List<OrderModel> orders = partner.getOrderForSeller(partnerId, businesServiceId, groupBuzCate, d, sSellerUserId, languageCode);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("orders", orders);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
		}
		else {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		}
		return response;
	}
	
	@PostMapping("/handleOrder")
	public ResponseEntity<ResponseMessage> handleOrder(@RequestBody Object input){
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		if(jObject.get(Parameter.S_PARTNER_ID) != null 
				|| jObject.get(Parameter.S_BUSINESS_SERVICE_ID) != null	
				|| jObject.get("sSellerUserId")!= null
				|| jObject.get("appointmentDate")!= null) {
			String partnerId = jObject.get(Parameter.S_PARTNER_ID).getAsString();
			String businesServiceId = jObject.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString();
			String sSellerUserId = jObject.get("sSellerUserId").getAsString();
			String groupBuzCate = jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID) == null ? ""
					: jObject.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString();
			String apDate = jObject.get("appointmentDate").getAsString();
			String languageCode = jObject.get("languageCode") == null ? "" :jObject.get("languageCode").getAsString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date(System.currentTimeMillis());
			try {
				d = df.parse(apDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			List<OrderModel> orders = partner.handleOrder(partnerId, businesServiceId, groupBuzCate, d, sSellerUserId, languageCode);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("orders", orders);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
		}
		else {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input.null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input.null.msg"));
		}
		return response;
	}
	
	@GetMapping("/getImage/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable String id) {
		
		String imageData = partner.getImage(id);
		
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
                .body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
    }
}