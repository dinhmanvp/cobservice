package cob.com.business.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cob.com.business.ws.models.ResponseMessage;

@FeignClient(name = "partner")
public interface BusinessFeignClient {

	@RequestMapping(path = "/getPartnerHoliday/{partnerId}/{monthOfYear}", method = RequestMethod.GET)
	ResponseMessage getPartnerHoliday(@PathVariable("partnerId") String partnerId, @PathVariable("monthOfYear") String monthOfYear);
	
	@RequestMapping(path = "/getPartnerWorkingtime", method = RequestMethod.POST)
	public ResponseMessage getPartnerWorkingtime(@RequestBody Object input);
	
	@GetMapping("/partnerNames")
	public ResponseEntity<ResponseMessage> getPartnerNames();
}
