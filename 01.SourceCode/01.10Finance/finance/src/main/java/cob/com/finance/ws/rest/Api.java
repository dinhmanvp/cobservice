package cob.com.finance.ws.rest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.finance.entities.TbTransaction;
import cob.com.finance.param.Parameter;
import cob.com.finance.serivces.Finance;
import cob.com.finance.utils.ConfigUtility;
import cob.com.finance.utils.StringUtility;
import cob.com.finance.ws.models.PocketInfoForOther;
import cob.com.finance.ws.models.ResponseMessage;
import cob.com.finance.ws.models.TbMypocketModel;
import cob.com.finance.ws.validate.ValidateInput;

@RestController
public class Api {
	@Autowired
	private ConfigUtility configUtil;

	@Autowired
	private Finance finance;

	@PostMapping("/getPoketByUserId")
	public ResponseEntity<ResponseMessage> getPoketByUserId(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		if (StringUtils.isEmpty(jObject.get(Parameter.USER_ID))) {
			response.getBody().setResponseCode(configUtil.getProperty("cob.input-null.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.input-null.msg"));
		} else {
			String userId = jObject.get(Parameter.USER_ID).getAsString();
			List<TbMypocketModel> pockets = finance.getByUserId(userId);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("Pockets", pockets);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
		}
		return response;
	}

	@PostMapping("/excuseTransfer")
	public ResponseEntity<ResponseMessage> excuseTransfer(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.validateTransactionInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		} else {
			TbTransaction entity = new TbTransaction();
			String sTransactionId = UUID.randomUUID().toString();
			entity.setSTransactionId(sTransactionId);
			Date transactionDate = new Date(System.currentTimeMillis());
			entity.setDTransactionDate(transactionDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sCashbackApprovedDate = jObject.get(Parameter.D_CASHBACK_APPROVED_DATE) == null ? null
					: jObject.get(Parameter.D_CASHBACK_APPROVED_DATE).getAsString();
			String sCashbackValidateDate = jObject.get(Parameter.D_CASHBACK_VALIDATE_DATE) == null ? null
					: jObject.get(Parameter.D_CASHBACK_VALIDATE_DATE).getAsString();
			String sTransactionDate = jObject.get(Parameter.D_TRANS_CONFIRM_DATE) == null ? null
					: jObject.get(Parameter.D_TRANS_CONFIRM_DATE).getAsString();
			try {
				Date dCashbackApprovedDate = df.parse(sCashbackApprovedDate);
				entity.setDCashbackApprovedDate(dCashbackApprovedDate);
				Date dCashbackValidateDate = df.parse(sCashbackValidateDate);
				entity.setDCashbackApprovedDate(dCashbackValidateDate);
				Date dTransactionDate = df.parse(sTransactionDate);
				entity.setDTransactionDate(dTransactionDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			BigDecimal nAmount = jObject.get(Parameter.N_AMOUNT).getAsBigDecimal();
			entity.setNAmount(nAmount);
			BigDecimal nCashbackAmt = jObject.get(Parameter.N_CASHBACK_AMT) == null ? null
					: jObject.get(Parameter.N_CASHBACK_AMT).getAsBigDecimal();
			entity.setNCashbackAmt(nCashbackAmt);
//			String otp = StringUtility.OTP(6);
//			entity.setNOtpAuth(Integer.parseInt(otp));
			String nOtpConfirmed = jObject.get(Parameter.N_OTP_CONFIRMED) == null ? null
					: jObject.get(Parameter.N_OTP_CONFIRMED).getAsString();
			entity.setNOtpConfirmed(nOtpConfirmed);
			Integer nOtpConfirmedTimes = jObject.get(Parameter.N_OTP_CONFIRMED_TIMES) == null ? null
					: jObject.get(Parameter.N_OTP_CONFIRMED_TIMES).getAsInt();
			entity.setNOtpConfirmedTimes(nOtpConfirmedTimes);
			String sCashbackApprovedBy = jObject.get(Parameter.S_CASHBACK_APPROVED_BY) == null ? null
					: jObject.get(Parameter.S_CASHBACK_APPROVED_BY).getAsString();
			entity.setSCashbackApprovedBy(sCashbackApprovedBy);
			String sCashbackValidateBy = jObject.get(Parameter.S_CASHBACK_VALIDATE_BY) == null ? null
					: jObject.get(Parameter.S_CASHBACK_VALIDATE_BY).getAsString();
			entity.setSCashbackValidateBy(sCashbackValidateBy);
			String sChannelId = jObject.get(Parameter.S_CHANNEL_ID) == null ? null
					: jObject.get(Parameter.S_CHANNEL_ID).getAsString();
			entity.setSChannelId(sChannelId);
			String sCurrencyId = jObject.get(Parameter.S_CURRENCY_ID) == null ? null
					: jObject.get(Parameter.S_CURRENCY_ID).getAsString();
			entity.setSCurrencyId(sCurrencyId);
			String sEContractNo = jObject.get(Parameter.S_E_CONTRACT_NO) == null ? null
					: jObject.get(Parameter.S_E_CONTRACT_NO).getAsString();
			entity.setSEContractNo(sEContractNo);
			String sFromUserId = jObject.get(Parameter.S_FROM_USER_ID) == null ? null
					: jObject.get(Parameter.S_FROM_USER_ID).getAsString();
			entity.setSFromUserId(sFromUserId);
			String sPocketId = jObject.get(Parameter.S_POCKET_ID) == null ? null
					: jObject.get(Parameter.S_POCKET_ID).getAsString();
			entity.setSPocketId(sPocketId);
			String sToUserId = jObject.get(Parameter.S_TO_USER_ID) == null ? null
					: jObject.get(Parameter.S_TO_USER_ID).getAsString();
			entity.setSToUserId(sToUserId);
			String sTransMessage = jObject.get(Parameter.S_TRANS_MESSAGE) == null ? null
					: jObject.get(Parameter.S_TRANS_MESSAGE).getAsString();
			entity.setSTransMessage(sTransMessage);
			String sTransactionTypeId = jObject.get(Parameter.S_TRANSACTION_TYPE_ID) == null ? null
					: jObject.get(Parameter.S_TRANSACTION_TYPE_ID).getAsString();
			entity.setSTransactionTypeId(sTransactionTypeId);
			Integer method = jObject.get(Parameter.METHOD) == null ? 0 : jObject.get(Parameter.METHOD).getAsInt();
			Integer result = finance.excuseTransfer(entity, method);
			switch (result) {
			case -1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction--1.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction--1.result.msg"));
				break;
			case 0:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-0.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-0.result.msg"));
				break;
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-1.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-1.result.msg"));
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("transactionId", sTransactionId);
				response.getBody().setResponseData(map);
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-2.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-2.result.msg"));
				break;
			default:
				break;
			}
		}
		return response;
	}

	@PostMapping("/updateTransfer")
	public ResponseEntity<ResponseMessage> updateTransfer(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		TbTransaction entity = new TbTransaction();

		if (jObject.get(Parameter.TRANSACTION_ID) != null) {
			entity.setSTransactionId(jObject.get(Parameter.TRANSACTION_ID).getAsString());
		} else {
			response.getBody().setResponseCode(configUtil.getProperty("cob.fail.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.fail.msg"));
			return response;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (jObject.get(Parameter.D_CASHBACK_APPROVED_DATE) != null) {
			String sCashbackApprovedDate = jObject.get(Parameter.D_CASHBACK_APPROVED_DATE).getAsString();
			Date dCashbackApprovedDate;
			try {
				dCashbackApprovedDate = df.parse(sCashbackApprovedDate);
				entity.setDCashbackApprovedDate(dCashbackApprovedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (jObject.get(Parameter.D_CASHBACK_VALIDATE_DATE) != null) {
			String sCashbackValidateDate = jObject.get(Parameter.D_CASHBACK_VALIDATE_DATE).getAsString();
			Date dCashbackValidateDate;
			try {
				dCashbackValidateDate = df.parse(sCashbackValidateDate);
				entity.setDCashbackValidateDate(dCashbackValidateDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (jObject.get(Parameter.D_TRANS_CONFIRM_DATE) != null) {
			String sTransactionDate = jObject.get(Parameter.D_TRANS_CONFIRM_DATE).getAsString();
			Date dTransactionDate;
			try {
				dTransactionDate = df.parse(sTransactionDate);
				entity.setDTransactionDate(dTransactionDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (jObject.get(Parameter.N_AMOUNT) != null) {
			entity.setNAmount(jObject.get(Parameter.N_AMOUNT).getAsBigDecimal());
		}

		if (jObject.get(Parameter.N_ID) != null) {
			entity.setNId(jObject.get(Parameter.N_ID).getAsInt());
		}

		if (jObject.get(Parameter.N_CASHBACK_AMT) != null) {
			entity.setNCashbackAmt(jObject.get(Parameter.N_CASHBACK_AMT).getAsBigDecimal());
		}

		if (jObject.get(Parameter.OTP_PARAMETER) != null) {
			entity.setNOtpAuth(jObject.get(Parameter.OTP_PARAMETER).getAsString());
		}
		if (jObject.get(Parameter.N_OTP_CONFIRMED) != null) {
			entity.setNOtpConfirmed(jObject.get(Parameter.N_OTP_CONFIRMED).getAsString());
		}

		if (jObject.get(Parameter.N_OTP_CONFIRMED_TIMES) != null) {
			entity.setNOtpConfirmedTimes(jObject.get(Parameter.N_OTP_CONFIRMED_TIMES).getAsInt());
		}

		if (jObject.get(Parameter.S_CASHBACK_APPROVED_BY) != null) {
			entity.setSCashbackApprovedBy(jObject.get(Parameter.S_CASHBACK_APPROVED_BY).getAsString());
		}

		if (jObject.get(Parameter.S_CASHBACK_VALIDATE_BY) != null) {
			entity.setSCashbackValidateBy(jObject.get(Parameter.S_CASHBACK_VALIDATE_BY).getAsString());
		}

		if (jObject.get(Parameter.S_CHANNEL_ID) != null) {
			entity.setSChannelId(jObject.get(Parameter.S_CHANNEL_ID).getAsString());
		}

		if (jObject.get(Parameter.S_CURRENCY_ID) != null) {
			entity.setSCurrencyId(jObject.get(Parameter.S_CURRENCY_ID).getAsString());
		}

		if (jObject.get(Parameter.S_E_CONTRACT_NO) != null) {
			entity.setSEContractNo(jObject.get(Parameter.S_E_CONTRACT_NO).getAsString());
		}

		if (jObject.get(Parameter.S_FROM_USER_ID) != null) {
			entity.setSFromUserId(jObject.get(Parameter.S_FROM_USER_ID).getAsString());
		}

		if (jObject.get(Parameter.S_POCKET_ID) != null) {
			entity.setSPocketId(jObject.get(Parameter.S_POCKET_ID).getAsString());
		}

		if (jObject.get(Parameter.S_TO_USER_ID) != null) {
			entity.setSToUserId(jObject.get(Parameter.S_TO_USER_ID).getAsString());
		}

		if (jObject.get(Parameter.S_TRANS_MESSAGE) != null) {
			entity.setSTransMessage(jObject.get(Parameter.S_TRANS_MESSAGE).getAsString());
		}

		if (jObject.get(Parameter.S_TRANSACTION_TYPE_ID) != null) {
			entity.setSTransactionTypeId(jObject.get(Parameter.S_TRANSACTION_TYPE_ID).getAsString());
		}

		Integer result = finance.updateTransfer(entity);
		switch (result) {

		case 0:
			response.getBody().setResponseCode(configUtil.getProperty("cob.fail.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.fail.msg"));
			break;
		case 1:
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			break;

		default:
			break;
		}
		return response;
	}
	
	@PostMapping("/getPoketInfoForOther")
	public ResponseEntity<ResponseMessage> getPoketInfoForOther(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		//validate
		ValidateInput validate = new ValidateInput(configUtil);
		responseMessage = validate.getPoketInfoForOther(jObject);
		if(!configUtil.getProperty("cob.success.code").equals(responseMessage.getResponseCode())) {
			response.getBody().setResponseCode(responseMessage.getResponseCode());
			response.getBody().setResponseMessage(responseMessage.getResponseMessage());
			return response;
		}
		//excuse
		List<PocketInfoForOther> result = finance.getPoketInfoForOther(jObject);
		//return
		HashMap<String, Object> outdata = new HashMap<String, Object>();
		outdata.put("poketInfos", result);
		
		response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
		response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
		response.getBody().setResponseData(outdata);
		
		return response;
	}
	
	@PostMapping("/sentCoinToUser")
	public ResponseEntity<ResponseMessage> sentCoinToUser(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		
		return response;
	}
	@PostMapping("/transferCOBCoin")
	public ResponseEntity<ResponseMessage> transferCOBCoin(@RequestBody Object input){
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		
		ValidateInput validate = new ValidateInput(this.configUtil);
		ResponseMessage rm = validate.validateTransferCoinInput(jObject);
		if(rm.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
			return response;
		}
		else {
			String transactionTypeId = jObject.get(Parameter.TRANSACTION_TYPE_ID).getAsString();
			String toUserId = jObject.get(Parameter.TO_USER_ID).getAsString();
			//String toPocketId = jObject.get(Parameter.TO_POCKET_ID).getAsString();
			BigDecimal amount = jObject.get(Parameter.AMOUNT).getAsBigDecimal();
			String channelId = jObject.get(Parameter.CHANNEL_ID).getAsString();
			String fromUserId = jObject.get(Parameter.FROM_USER_ID).getAsString();
			//String fromPocketId = jObject.get(Parameter.FROM_POCKET_ID).getAsString();
			String transMessage = jObject.get(Parameter.TRANS_MESSAGE) == null ? ""
					: jObject.get(Parameter.TRANS_MESSAGE).getAsString();
			Integer result = finance.transferCOBCoin(transactionTypeId, toUserId, amount, channelId,
					fromUserId, transMessage);
			switch (result) {
			case -1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction--1.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction--1.result.msg"));
				break;
			case 0:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-0.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-0.result.msg"));
				break;
			case 1:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-1.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-1.result.msg"));
				break;
			case 2:
				response.getBody().setResponseCode(configUtil.getProperty("cob.excuse-transaction-2.result.code"));
				response.getBody().setResponseMessage(configUtil.getProperty("cob.excuse-transaction-2.result.msg"));
				break;
			default:
				break;
			}
			return response;
		}
	}
}
