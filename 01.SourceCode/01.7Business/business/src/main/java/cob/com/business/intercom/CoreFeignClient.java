package cob.com.business.intercom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import cob.com.business.ws.models.ResponseMessage;

@FeignClient(name = "core")
public interface CoreFeignClient {
	@GetMapping("/groupBusinessCateName")
	public ResponseEntity<ResponseMessage> groupBusinessCateName();
}
