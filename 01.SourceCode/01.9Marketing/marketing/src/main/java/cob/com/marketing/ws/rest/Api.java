package cob.com.marketing.ws.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.marketing.entities.TbAdvertise;
import cob.com.marketing.services.Marketing;
import cob.com.marketing.utils.ConfigUtility;
import cob.com.marketing.utils.StringUtility;
import cob.com.marketing.ws.models.ResponseMessage;
import cob.com.marketing.ws.param.Parameter;
import cob.com.marketing.ws.validate.ValidateInput;

@RestController
public class Api {
	@Autowired
	private Marketing marketing;
	@Autowired
	private ConfigUtility configUtil;

	@PostMapping(value = "/advertises")
	public ResponseEntity<ResponseMessage> getAdvertise(@RequestBody Object input) {
		ResponseMessage responseMessage = new ResponseMessage();
		ResponseEntity<ResponseMessage> response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(input).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		// Validate
		ValidateInput validate = new ValidateInput(this.configUtil);
		responseMessage = validate.getAdvertiseValidateInput(jObject);
		if (responseMessage.getResponseCode() != null) {
			response = new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
			return response;
		} else {
			Integer isDisplay = jObject.get(Parameter.IS_DISPLAY).getAsInt();
			List<TbAdvertise> ads = new ArrayList<TbAdvertise>();
			ads = marketing.getAdvertises(isDisplay);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("advertises", ads);
			response.getBody().setResponseCode(configUtil.getProperty("cob.success.code"));
			response.getBody().setResponseMessage(configUtil.getProperty("cob.success.msg"));
			response.getBody().setResponseData(map);
			return response;
		}

	}

	@GetMapping("/getImage/{tablename}/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable String tablename, @PathVariable String id) {

		String imageData = marketing.getImage(tablename, id);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".svg\"")
				.body(new ByteArrayResource(StringUtility.ImageBase64tobytes(imageData)));
	}
}
