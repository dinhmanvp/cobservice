package cob.com.accounts.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cob.com.accounts.ws.models.ResponseMessage;

@FeignClient(name = "communication")
public interface CommunicationFeignClient {
	@PostMapping("/sendEmail")
	Object sendEmail(@RequestBody Object object);

	@PostMapping("/pushNotification")
	Object sendNotify(@RequestBody Object object);
	
	@PostMapping("/verifiedOTP")
	ResponseMessage verifiedOTP(@RequestBody Object object);
}
