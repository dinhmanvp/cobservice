package cob.com.cobgateway.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cob.com.gateway.models.ResponseMessage;
@FeignClient(name = "accounts")
public interface UserClient {
	@RequestMapping(path = "/loginAccount/{Username}/{Password}", method = RequestMethod.GET)
	ResponseMessage checkLogin(@PathVariable(value = "Username")String Username, @PathVariable(value = "Password")String Password);
	
	@RequestMapping(path = "/testApi", method = RequestMethod.GET)
	ResponseMessage checkTest();
	
}
