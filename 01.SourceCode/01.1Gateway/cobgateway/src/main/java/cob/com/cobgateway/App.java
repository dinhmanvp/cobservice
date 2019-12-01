package cob.com.cobgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ldman application gateway
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableZuulProxy
@EnableFeignClients
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
