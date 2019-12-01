package cob.com.communication.services;

import java.util.Map;

import javax.mail.MessagingException;

import cob.com.communication.entities.TbCustomer;
import cob.com.communication.entities.TbNotificationTemplate;
import cob.com.communication.entities.TbSendEmailLog;
import cob.com.communication.entities.TbTokenUser;
import cob.com.communication.entities.TbUser;
import cob.com.communication.entities.TbVerifyOtp;

public interface Communication {
	Boolean sendEmail(String sendTo,String templateName, Map<String,String> data) throws MessagingException;
	String[] getParamListByTemplate(String templateName);
	void saveLog(TbSendEmailLog log);
	TbTokenUser getByUsername(String username);
	TbNotificationTemplate getByName (String templateName);
	Boolean addOrUpdateClientAppTokenId(String userId, String token);
	TbVerifyOtp save(TbVerifyOtp entity);
	Integer verifyOtp(String transactionId,String username,String otp);
	void updateStatus(Object object);
	TbUser getUserByUsername(String username);
	TbCustomer getCustomerByCustomerId(String customerId);
}
