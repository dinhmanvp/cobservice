package cob.com.accounts.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "finance")
public interface TransactionsFeignClient {
	@PostMapping("/transferCOBCoin")
	Object transferCOBCoin(@RequestBody Object input);
}
