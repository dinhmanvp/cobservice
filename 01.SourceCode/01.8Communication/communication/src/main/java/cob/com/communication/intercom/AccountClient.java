package cob.com.communication.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accounts")
public interface AccountClient {
	@PostMapping("/updateAccount")
	void updateAccountStatus(@RequestBody Object object);
}
