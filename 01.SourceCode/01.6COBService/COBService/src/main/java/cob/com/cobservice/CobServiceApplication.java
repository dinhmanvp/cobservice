package cob.com.cobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableAsync
public class CobServiceApplication
{	
	public static void main(String[] args) {
		SpringApplication.run(CobServiceApplication.class, args);
	}

}
