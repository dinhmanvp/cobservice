package cob.com.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import cob.com.communication.utils.ConfigUtility;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAsync
public class CommunicationApplication
{
	@Autowired
	private ConfigUtility configUtil;

	@Value("${nettyserver.server.host}")
	private String host;

	@Value("${nettyserver.server.port}")
	private Integer port;
	
//	@Bean
//	public SocketIOServer socketIOServer() {
//		Configuration config = new Configuration();
//		config.setHostname(host);
//		config.setPort(port);
//		return new SocketIOServer(config, configUtil/* , gatewayClient, synchronization */);
//	}
//	

	public static void main(String[] args) {
		SpringApplication.run(CommunicationApplication.class, args);
	}

}
