package cob.com.communication.services;

import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cob.com.communication.dao.TbCustomerRepository;
import cob.com.communication.dao.TbNotificationTemplateRepository;
import cob.com.communication.dao.TbSendEmailLogRepository;
import cob.com.communication.dao.TbSettingRepository;
import cob.com.communication.dao.TbTemplateRepository;
import cob.com.communication.dao.TbTokenUserRepository;
import cob.com.communication.dao.TbUserRepository;
import cob.com.communication.dao.TbVerifyOtpRepository;
import cob.com.communication.entities.TbCustomer;
import cob.com.communication.entities.TbNotificationTemplate;
import cob.com.communication.entities.TbSendEmailLog;
import cob.com.communication.entities.TbSetting;
import cob.com.communication.entities.TbTemplate;
import cob.com.communication.entities.TbTokenUser;
import cob.com.communication.entities.TbUser;
import cob.com.communication.entities.TbVerifyOtp;
import cob.com.communication.entities.TbVerifyOtpPK;
import cob.com.communication.intercom.AccountClient;
import cob.com.communication.param.Parameter;
import cob.com.communication.utils.DateUtility;

@Component
public class CommunicationImpl implements Communication {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	TbTemplateRepository tbTemplateRepository;
	
	@Autowired
	TbSendEmailLogRepository tbSendEmailLogRepository;
	
	@Autowired
	TbTokenUserRepository tbTokenUserRepository;
	
	@Autowired
	TbNotificationTemplateRepository tbNotificationTemplateRepository;
	
	@Autowired
	TbVerifyOtpRepository tbVerifyOtpRepository;
	
	@Autowired
	TbSettingRepository tbSettingRepository;
	
	@Autowired
	AccountClient accountClient;
	
	@Autowired
	TbUserRepository tbUserRepository;
	
	@Autowired
	TbCustomerRepository tbCustomerRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(CommunicationImpl.class);


//	@Value("${spring.mail.username}")
//	private String sendFrom;
	@Override
	public Boolean sendEmail(String sendTo, String templateName, Map<String, String> data){
		try {
			TbTemplate template = tbTemplateRepository.getTemplateByName(templateName);
			if(template == null) {
				return false;
			}
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(sendTo);
			helper.setSubject(template.getsSubject());
			String templateFormat = template.getSContent();		
			StrSubstitutor sub = new StrSubstitutor(data);
			String resolvedString = sub.replace(templateFormat);
			helper.setText(resolvedString, true);
			//helper.setText(resolvedString);
			javaMailSender.send(msg);
			
			return true;
		} catch (MessagingException e) {
			return false;
		}		
	}

	@Override
	public String[] getParamListByTemplate(String templateName) {
		TbTemplate template = tbTemplateRepository.getTemplateByName(templateName);
		if (template != null) {
			String[] params = template.getsParmas().split("-");
			if(params.length == 1 && params[0].equals("")) {
				return new String[0];
			}
			else {
				return params;
			}			
		} else {
			return null;
		}

	}

	@Override
	public void saveLog(TbSendEmailLog log) {
		tbSendEmailLogRepository.save(log);		
	}

	@Override
	public TbTokenUser getByUsername(String username) {
		return tbTokenUserRepository.findByUsername(username);
	}

	@Override
	public TbNotificationTemplate getByName(String templateName) {		
		return tbNotificationTemplateRepository.getByName(templateName);
	}

	@Override
	public Boolean addOrUpdateClientAppTokenId(String username, String token) {
		//check exist
		TbTokenUser tbTokenUser = tbTokenUserRepository.findByUsername(username);
		if(tbTokenUser == null) {
			//add new
			try {
				tbTokenUser = new TbTokenUser();
				tbTokenUser.setDCreated(new Date(System.currentTimeMillis()));
				tbTokenUser.setSToken(token);
				tbTokenUser.setSUsername(username);
				tbTokenUserRepository.save(tbTokenUser);
			} catch (Exception e) {
				return false;
			}
		}
		else {
			try {
				tbTokenUser.setDCreated(new Date(System.currentTimeMillis()));
				tbTokenUser.setSToken(token);
				tbTokenUserRepository.save(tbTokenUser);
			} catch (Exception e) {
				return false;
			}
			
		}
		return true;
	}
		
	public TbVerifyOtp save(TbVerifyOtp entity) {
		return tbVerifyOtpRepository.save(entity);
	}

	@Override
	public Integer verifyOtp(String transactionId, String username, String otp) {
		try {
			TbVerifyOtp tbVerifyOtp =  tbVerifyOtpRepository.getById(transactionId, username);
			if(tbVerifyOtp == null) {
				return 0; // khong ton tai giao dich 
			}else {
				if(tbVerifyOtp.getSOtp().equals(otp) && tbVerifyOtp.getNIsVerified() == 0) {		
					Date current = new Date(System.currentTimeMillis());
					TbSetting tbSetting = tbSettingRepository.getOne(Parameter.SETTING_ID_OTP_EXPIRE_IN);
					if(tbSetting != null) {
						Integer expireIn = tbSetting.getNValue();
						Date expireTime = DateUtility.AddMinute(tbVerifyOtp.getDCreatedDate(), expireIn);
						log.info("====expireTime:" + expireTime);						
						log.info("====current:" + current);
						if(expireTime.after(current)) {
							tbVerifyOtp.setNIsVerified(1);
							tbVerifyOtpRepository.save(tbVerifyOtp);
							//update account status
							return 1; // xac thuc thanh cong
						}
						else {
							return 4; // otp het hieu luc
						}
					}
					//return 3; // loi he thong;
				}else
					return 2;// Ma OTP khong dung
			}		
		} catch (Exception e) {
			return 3; //loi he thong
		}	
		return 3; // loi he thong
	}
	
	public void updateStatus(Object object) {
		accountClient.updateAccountStatus(object);
	}

	@Override
	public TbUser getUserByUsername(String username) {		
		return tbUserRepository.getUserByUserName(username);
	}

	@Override
	public TbCustomer getCustomerByCustomerId(String customerId) {
		
		return tbCustomerRepository.getCustomerByCustomerId(customerId);
	}
}
