package cob.com.business.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/**
 * @author ldman 2019/07/06
 */
@Configuration
public class ConfigUtility {

	@Autowired
	private Environment enviroment;

    public String getProperty(String pPropertyKey) {
        return enviroment.getProperty(pPropertyKey);
    }
}
