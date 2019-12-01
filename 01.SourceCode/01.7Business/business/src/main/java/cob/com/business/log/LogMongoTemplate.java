package cob.com.business
.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
/**
 * @author ldman 2019/07/06
 * mongoTemplate write log into mongoDB
 */
@Component
public class LogMongoTemplate {
	
	public static MongoTemplate mongoTemplate;
	
	@SuppressWarnings("all")
	@Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	public static void logInfo(Object info, String collectionName) {
		
		if(!LogMongoTemplate.mongoTemplate.collectionExists(collectionName)){
			LogMongoTemplate.mongoTemplate.createCollection(collectionName);
		}
		// add log info
		LogMongoTemplate.mongoTemplate.insert(info, collectionName);
		
	}
}
