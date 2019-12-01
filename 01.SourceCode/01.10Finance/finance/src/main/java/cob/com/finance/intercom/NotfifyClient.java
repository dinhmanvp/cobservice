package cob.com.finance.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cob.com.finance.ws.models.ResponseMessage;

@FeignClient(name ="communication")
public interface NotfifyClient {
	@PostMapping("/sendEmail")
	Object sendEmail(@RequestBody Object object);

	@PostMapping("/pushNotification")
	Object sendNotify(@RequestBody Object object);
	
	@PostMapping("/verifiedOTP")
	ResponseMessage verifiedOTP(@RequestBody Object object);
}
