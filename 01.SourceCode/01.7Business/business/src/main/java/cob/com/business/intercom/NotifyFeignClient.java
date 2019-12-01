package cob.com.business.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "communication")
public interface NotifyFeignClient {
	@PostMapping("/sendEmail")
	Object sendEmail(@RequestBody Object object);
}
