package cob.com.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ldman 2019/07/06
 * customers module service
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAsync
public class App  
{
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
