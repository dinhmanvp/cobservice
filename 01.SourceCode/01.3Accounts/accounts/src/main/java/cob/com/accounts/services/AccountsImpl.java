package cob.com.accounts.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.accounts.dao.TbAcountRepository;
import cob.com.accounts.dao.TbCustomerLoyaltyProgramRepository;
import cob.com.accounts.dao.TbCustomerRepository;
import cob.com.accounts.dao.TbMyBusinessRepository;
import cob.com.accounts.dao.TbMybusinessWorkingtimeRepository;
import cob.com.accounts.dao.TbMypocketRepository;
import cob.com.accounts.dao.TbPersonalAbsentRepository;
import cob.com.accounts.dao.TbUserAuthenticationRepository;
import cob.com.accounts.dao.TbUserRepository;
import cob.com.accounts.entities.TbCustomer;
import cob.com.accounts.entities.TbCustomerLoyaltyProgram;
import cob.com.accounts.entities.TbMyBusiness;
import cob.com.accounts.entities.TbMybusinessWorkingtime;
import cob.com.accounts.entities.TbMypocket;
import cob.com.accounts.entities.TbPersonalAbsent;
import cob.com.accounts.entities.TbUser;
import cob.com.accounts.entities.TbUserAuthentication;
import cob.com.accounts.entities.ViewNumberMyBusiness;
import cob.com.accounts.intercom.CommunicationFeignClient;
import cob.com.accounts.intercom.TransactionsFeignClient;
import cob.com.accounts.utils.StringUtility;
import cob.com.accounts.ws.models.AccountInfo;
import cob.com.accounts.ws.models.ResponseMessage;
import cob.com.accounts.ws.param.Parameter;

@Component
public class AccountsImpl implements Accounts {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CommunicationFeignClient communicationFeignClient;

	@Autowired
	private TbAcountRepository tbAcountRepository;

	@Autowired
	private TbCustomerRepository tbCustomerRepository;

	@Autowired
	private TbUserRepository tbUserRepository;

	@Autowired
	private TbMyBusinessRepository tbMyBusinessRepository;

	@Autowired
	private TbUserAuthenticationRepository tbUserAuthenticationRepository;

	@Autowired
	private TbPersonalAbsentRepository tbPersonalAbsentRepository;

	@Autowired
	private TbMybusinessWorkingtimeRepository tbMybusinessWorkingtimeRepository;
	
	@Autowired
	private TbMypocketRepository tbMypocketRepository;
	
	@Autowired
	private TbCustomerLoyaltyProgramRepository tbCustomerLoyaltyProgramRepository;
	
	@Autowired
	private TransactionsFeignClient transactionsFeignClient;

//	BEGIN feign client communication
	@Override
	public TbUser getByUserName(String username) {

		return tbUserRepository.getUserByUserName(username);
	}

	@Override
	public Object sendEmail(Object object) {
		return communicationFeignClient.sendEmail(object);
	}

	@Override
	public Object sendNotify(Object object) {
		return communicationFeignClient.sendNotify(object);
	}

	@Override
	public ResponseMessage verifiedOTP(Object object) {
		return communicationFeignClient.verifiedOTP(object);
	}

//	END feign client communication

	// Kiểm tra Usernam với Phone có tồn tại không!
	@Override
	public Integer CheckUsernamPhone(String Username, String Phone, String email, String cardId) {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_account.checkusernamephonecustomer")
				.registerStoredProcedureParameter("username", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("phone", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("cardId", String.class, ParameterMode.IN)
				.setParameter("username", Username).setParameter("phone", Phone).setParameter("email", email).setParameter("cardId", cardId);
		return (Integer) q.getSingleResult();
	}

	// lấy Account cuối cùng để tạo ID account mới.
	private Integer GetEndCusID() {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_account.getendaccountid");
		return (Integer) q.getSingleResult();
	}

	// @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {
	// RuntimeException.class, Error.class,
	// Exception.class, ParseException.class })

	// Tạo mới một Account
	@Override
	public Integer RegisCustomerUser(JsonObject jsonInput) {
		try {
			TbCustomer Cus = new TbCustomer();
			TbUser Us = new TbUser();
			Integer a = GetEndCusID();

			// Insert Customer
			String CusID = "0000000000";
			Integer b = 0;
			if (a == null) {
				CusID = "0000000001";
			} else {
				b = (a + 1);
				CusID = "0000000000" + b.toString();
				CusID = CusID.substring(CusID.length() - 10, CusID.length());
			}
			Cus.setSCustomerId(StringUtility.HexSHA(CusID));

			Cus.setSFirstname(jsonInput.get(Parameter.FIRSTNAME).getAsString());

			Cus.setSLastname(jsonInput.get(Parameter.LASTNAME).getAsString());

			Cus.setNGender(jsonInput.get(Parameter.GENDER).getAsInt());

			Cus.setSContactCountryId(jsonInput.get(Parameter.CONTACTCOUNTRYID).getAsString());

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.REFFERRAL_ID))) {
				String Useridrefe = tbUserRepository
						.GetUserIdbyphone(jsonInput.get(Parameter.REFFERRAL_ID).getAsString());
				if (Useridrefe == null) {
					return 0;
				} else
					Cus.setSreferalId(Useridrefe);

			} else
				Cus.setSreferalId(null);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date Bthday, carddate;
			try {

				Bthday = df.parse(jsonInput.get(Parameter.BIRTHDAY).getAsString());
				Cus.setDBirthday(Bthday);
			} catch (ParseException e) {
				Cus.setDBirthday(null);
			}

			try {

				carddate = df.parse(jsonInput.get(Parameter.D_CARDID_ISSUE_DATE).getAsString());
				Cus.setDcardidIssueDate(carddate);
			} catch (ParseException e) {
				Cus.setDBirthday(null);
			}

			Cus.setScardidIssuePlace(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE).getAsString());

			Cus.setScardIdNo(jsonInput.get(Parameter.S_CARDID_NO).getAsString());
			Cus.setScardidIssuePlace(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE).getAsString());
			// Insert User

			Us.setSUserId(StringUtility.HexSHA(CusID));

			Us.setSUsername(jsonInput.get(Parameter.USERNAME).getAsString());

			/*
			 * if (!StringUtility.isEmpty(jsonInput.get("password")))
			 * Us.setBPassword(jsonInput.get("password").getAsString()); else
			 * Us.setBPassword(null);
			 */

			Us.setSEmail(jsonInput.get(Parameter.EMAIL).getAsString());

			Us.setSPhone(jsonInput.get(Parameter.PHONE).getAsString());

			Us.setSCustomerId(StringUtility.HexSHA(CusID));
			
			Us.setischangePassword(1);
			//thupa add key
			String key = StringUtility.generaKey();
			Us.setsKey(key);
			
			tbCustomerRepository.save(Cus);
			tbUserRepository.save(Us);
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}

//Tạo mới 1 WorkingTime
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	@Override
	public boolean RegisWorkingTime(JsonObject jsonInput) {
		try {
			TbMybusinessWorkingtime wort = new TbMybusinessWorkingtime();

//			if (!StringUtility.isEmpty(jsonInput.get("myBusinessSettingID")))
//				wort.setSMyBusinessSettingId(jsonInput.get("myBusinessSettingID").getAsString());
//			else
			String myBusinessSettingID = UUID.randomUUID().toString();
				wort.setSMyBusinessSettingId(myBusinessSettingID);
				
			if (!StringUtility.isEmpty(jsonInput.get("nCountPerDay")))
				wort.setNCountPerDay(jsonInput.get("nCountPerDay").getAsInt());
			
			if (!StringUtility.isEmpty(jsonInput.get("ndurationForSession")))
				wort.setNDurationForSession(jsonInput.get("ndurationForSession").getAsInt());

			if (!StringUtility.isEmpty(jsonInput.get("myBusinessID")))
				wort.setSMyBusinessId(jsonInput.get("myBusinessID").getAsString());
			else
				wort.setSMyBusinessId(null);

			if (!StringUtility.isEmpty(jsonInput.get("moFrom")))
				wort.setSMoFrom(jsonInput.get("moFrom").getAsString());
			else
				wort.setSMoFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("moTo")))
				wort.setSMoTo(jsonInput.get("moTo").getAsString());
			else
				wort.setSMoTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("moIsOFF")))
				wort.setSMoIsoff(jsonInput.get("moIsOFF").getAsString());
			else
				wort.setSMoIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("tuFrom")))
				wort.setSTuFrom(jsonInput.get("tuFrom").getAsString());
			else
				wort.setSTuFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("tuTo")))
				wort.setSTuTo(jsonInput.get("tuTo").getAsString());
			else
				wort.setSTuTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("tuIsOFF")))
				wort.setSTuIsoff(jsonInput.get("tuIsOFF").getAsString());
			else
				wort.setSTuIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("weFrom")))
				wort.setSWeFrom(jsonInput.get("weFrom").getAsString());
			else
				wort.setSWeFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("weTo")))
				wort.setSWeTo(jsonInput.get("weTo").getAsString());
			else
				wort.setSWeTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("weIsOFF")))
				wort.setSWeIsoff(jsonInput.get("weIsOFF").getAsString());
			else
				wort.setSWeIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("thFrom")))
				wort.setSThFrom(jsonInput.get("thFrom").getAsString());
			else
				wort.setSThFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("thTo")))
				wort.setSThTo(jsonInput.get("thTo").getAsString());
			else
				wort.setSThTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("thIsOFF")))
				wort.setSThIsoff(jsonInput.get("thIsOFF").getAsString());
			else
				wort.setSThIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("frFrom")))
				wort.setSFrFrom(jsonInput.get("frFrom").getAsString());
			else
				wort.setSFrFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("frTo")))
				wort.setSFrTo(jsonInput.get("frTo").getAsString());
			else
				wort.setSFrTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("frIsOFF")))
				wort.setSFrIsoff(jsonInput.get("frIsOFF").getAsString());
			else
				wort.setSFrIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("saFrom")))
				wort.setSSaFrom(jsonInput.get("saFrom").getAsString());
			else
				wort.setSSaFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("saTo")))
				wort.setSSaTo(jsonInput.get("saTo").getAsString());
			else
				wort.setSSaTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("saIsOFF")))
				wort.setSSaIsoff(jsonInput.get("saIsOFF").getAsString());
			else
				wort.setSSaIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("suFrom")))
				wort.setSSuFrom(jsonInput.get("suFrom").getAsString());
			else
				wort.setSSuFrom(null);
			if (!StringUtility.isEmpty(jsonInput.get("suTo")))
				wort.setSSuTo(jsonInput.get("suTo").getAsString());
			else
				wort.setSSuTo(null);
			if (!StringUtility.isEmpty(jsonInput.get("suIsOFF")))
				wort.setSSuIsoff(jsonInput.get("suIsOFF").getAsString());
			else
				wort.setSSuIsoff(null);

			if (!StringUtility.isEmpty(jsonInput.get("partnerWorkingID")))
				wort.setSPartnerWorkingId(jsonInput.get("partnerWorkingID").getAsString());
			else
				wort.setSPartnerWorkingId(null);
			tbMybusinessWorkingtimeRepository.save(wort);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Update Một Account
	@Override
	public boolean UpdateCustomerUser(JsonObject jsonInput) {
		try {
			TbCustomer Cus = new TbCustomer();
			TbUser Us = new TbUser();

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.USERNAME))) {
				// Lấy thông tin user để update.
				Us = GetUser(jsonInput.get(Parameter.USERNAME).getAsString());
				// Update User
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.EMAIL)))
					Us.setSEmail(jsonInput.get(Parameter.EMAIL).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.PHONE)))
					Us.setSPhone(jsonInput.get(Parameter.PHONE).getAsString());
				/*
				 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date lastin, lastout; try
				 * {
				 * 
				 * if (!StringUtility.isEmpty(jsonInput.get("lastlogin"))) { lastin =
				 * df.parse(jsonInput.get("lastlogin").getAsString()); Us.setDLastlogin(lastin);
				 * }
				 * 
				 * if (!StringUtility.isEmpty(jsonInput.get("lastlogout"))) { lastout =
				 * df.parse(jsonInput.get("lastlogout").getAsString());
				 * Us.setDLastlogout(lastout); }
				 * 
				 * } catch (ParseException e) {}
				 */

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.IS_VALIDATE)))
					Us.setisValiDate(jsonInput.get(Parameter.IS_VALIDATE).getAsInt());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.PASSWORD)))
					Us.setBPassword(jsonInput.get(Parameter.PASSWORD).getAsString());

				tbUserRepository.saveAndFlush(Us);

				// Lấy thông tin của Customer để update.
				Cus = GetCustomer(jsonInput.get(Parameter.USERNAME).getAsString());
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.FIRSTNAME)))
					Cus.setSFirstname(jsonInput.get(Parameter.FIRSTNAME).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.LASTNAME)))
					Cus.setSLastname(jsonInput.get(Parameter.LASTNAME).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.GENDER)))
					Cus.setNGender(jsonInput.get(Parameter.GENDER).getAsInt());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BIRTHDAY))) {
					DateFormat dfcus = new SimpleDateFormat("dd/MM/yyyy");
					Date Bthday, carddate;
					try {

						Bthday = dfcus.parse(jsonInput.get(Parameter.BIRTHDAY).getAsString());
						Cus.setDBirthday(Bthday);
					} catch (ParseException e) {
					}
					try {

						carddate = dfcus.parse(jsonInput.get(Parameter.D_CARDID_ISSUE_DATE).getAsString());
						Cus.setDcardidIssueDate(carddate);
					} catch (ParseException e) {
						Cus.setDBirthday(null);
					}
				}
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE))) {
					Cus.setScardidIssuePlace(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE).getAsString());
				}
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CARDID_NO))) {
					Cus.setScardIdNo(jsonInput.get(Parameter.S_CARDID_NO).getAsString());
				}

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE))) {
					Cus.setScardidIssuePlace(jsonInput.get(Parameter.S_CARDID_ISSUE_PLACE).getAsString());
				}
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.AVATAR)))
					Cus.setBAvatar(jsonInput.get(Parameter.AVATAR).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.CONTACTADDRESSNO)))
					Cus.setSContactAddressNo(jsonInput.get(Parameter.CONTACTADDRESSNO).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.CONTACTSTREET)))
					Cus.setSContactStreet(jsonInput.get(Parameter.CONTACTSTREET).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.CONTACTZIPCODE)))
					Cus.setSContactZipcode(jsonInput.get(Parameter.CONTACTZIPCODE).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.CONTACTCOUNTRYID)))
					Cus.setSContactCountryId(jsonInput.get(Parameter.CONTACTCOUNTRYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.CONTACTCITYID)))
					Cus.setSContactCityId(jsonInput.get(Parameter.CONTACTCITYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BILLINGADDRESSNO)))
					Cus.setSBillingAddressNo(jsonInput.get(Parameter.BILLINGADDRESSNO).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BILLINGSTREET)))
					Cus.setSBillingStreet(jsonInput.get(Parameter.BILLINGSTREET).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BILLINGZIPCODE)))
					Cus.setSBillingZipcode(jsonInput.get(Parameter.BILLINGZIPCODE).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BILLINGOCUNTRYID)))
					Cus.setSBillingCountryId(jsonInput.get(Parameter.BILLINGOCUNTRYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.BILLINGOCITYID)))
					Cus.setSBillingCityId(jsonInput.get(Parameter.BILLINGOCITYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.SHIPPINGADDRESSNO)))
					Cus.setSShippingAddressNo(jsonInput.get(Parameter.SHIPPINGADDRESSNO).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.SHIPPINGSTREET)))
					Cus.setSShippingStreet(jsonInput.get(Parameter.SHIPPINGSTREET).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.SHIPPINGZIPCODE)))
					Cus.setSShippingZipcode(jsonInput.get(Parameter.SHIPPINGZIPCODE).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.SHIPPINGCOUNTRYID)))
					Cus.setSShippingCountryId(jsonInput.get(Parameter.SHIPPINGCOUNTRYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.SHIPPINGCITYID)))
					Cus.setSShippingCityId(jsonInput.get(Parameter.SHIPPINGCITYID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.REFFERRAL_ID)))
					Cus.setSreferalId(jsonInput.get(Parameter.REFFERRAL_ID).getAsString());

				tbCustomerRepository.saveAndFlush(Cus);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Hàm login trả về thông tin của account
	@SuppressWarnings("all")
	@Override
	public AccountInfo AcountLogin(String Username, String Password) {
		AccountInfo user = new AccountInfo();
		try {
			// thupa decrypt password 
			TbUser u = tbUserRepository.getUserByUserName(Username);
			String userKey = "";
			if(u != null) {
				userKey = u.getsKey();				
				String passwordEncrypt = StringUtility.encryptData(userKey, Password);
					StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_account.usernamelogin", AccountInfo.class)
							.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("Password", String.class, ParameterMode.IN)
							.setParameter("Username", Username).setParameter("Password", passwordEncrypt);
					user = (AccountInfo) q.getSingleResult();
					//decrypt
					String balanceDecrypt = StringUtility.decryptData(userKey, user.getnAvailablevBalance());
					//BigDecimal available = new BigDecimal(balanceDecrypt);
					user.setnAvailablevBalance(balanceDecrypt);
					if(!StringUtils.isEmpty(user.getSCustomerId())) {
						entityManager.clear();
						q = entityManager.createStoredProcedureQuery("mdl_partner.getpartnerbyuserid")
								.registerStoredProcedureParameter("UserId", String.class, ParameterMode.IN)
								.setParameter("UserId", user.getSUserId());
						List lstPartner = q.getResultList();
						user.setNhashpartner(0);
						if(lstPartner.size() > 0)
							user.setNhashpartner(1);
					}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
	}

	// Hàm đổi password của account
	@Override
	public Integer ChangePassAccount(String Username, String PassOld, String PassNew) {
		Integer result = 4;
		TbUser u = tbUserRepository.getUserByUserName(Username);
		if(u != null) {
			try {
				String passwordOldEncrypt = StringUtility.encryptData(u.getsKey(), PassOld);
				String passwordNewEncrypt = StringUtility.encryptData(u.getsKey(), PassNew);
				StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_account.accountchangepassword")
						.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("PasswordOld", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("Passwordnew", String.class, ParameterMode.IN)
						.setParameter("Username", Username).setParameter("PasswordOld", passwordOldEncrypt)
						.setParameter("Passwordnew", passwordNewEncrypt);
				result = (Integer) q.getSingleResult();
				if (result == 0) {
					try {
						TbUser user = new TbUser();
						user = GetUser(Username);
						user.setBPassword(passwordNewEncrypt);
						user.setischangePassword(0);
						tbUserRepository.saveAndFlush(user);
						result = 0;
					} catch (Exception e) {
						result = 4;
					}
				}	
			} catch (Exception e) {				
			}		
		}
		return result;
	}

	// Hàm resetPassword
	@Override
	public Integer forgotPassWord(String Username, String Email) {
		try {
			String Passreset = RandomRestPass();// Random 5 so ngau nhien;
			TbUser user = new TbUser();
			user = GetUser(Username);
			if (user.getSEmail().equals(Email)) {
				user.setBPassword(Passreset);
				tbUserRepository.saveAndFlush(user);
				return 0;
			} else {
				return 1;
			}

		} catch (Exception e) {
			return 2;
		}
	}

	// Hào Tạo My Business
	@Override
	public boolean myBusinessCreate(JsonObject jsonInput) {
		try {
			TbMyBusiness mybu = new TbMyBusiness();

			if (!StringUtility.isEmpty(jsonInput.get("userid"))
					&& !StringUtility.isEmpty(jsonInput.get("partnerid"))) {
				String Cusid = jsonInput.get("userid").getAsString();
				String Partid = jsonInput.get("partnerid").getAsString();
				StringBuilder mybuid = new StringBuilder();
				mybuid.append(Cusid);
				mybuid.append(Partid);
				mybu.setsUserId(Cusid);
				mybu.setSPartnerId(Partid);
				mybu.setSMyBusinessId(mybuid.toString());

				if (!StringUtility.isEmpty(jsonInput.get("partnerbizcateid")))
					mybu.setSPartnerBizcateId(jsonInput.get("partnerbizcateid").getAsString());
				else
					mybu.setSPartnerBizcateId(null);

				if (!StringUtility.isEmpty(jsonInput.get("partnerbusinessserviceid")))
					mybu.setSPartnerBusinessServiceId(jsonInput.get("partnerbusinessserviceid").getAsString());
				else
					mybu.setSPartnerBusinessServiceId(null);
			}

			tbMyBusinessRepository.save(mybu);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Hàm lấy về một Mybusiness cho Api
	@Override
	public TbMyBusiness getMyBusinessInfo(String MyBuID) {
		TbMyBusiness MyBu = new TbMyBusiness();
		try {
			MyBu = GetMyBusiness(MyBuID);
			return MyBu;
		} catch (Exception ex) {
			return MyBu;
		}
	}

	// Hàm Update một MyBusiness cho Api
	@Override
	public boolean myBusinessUpdate(JsonObject jsonInput) {
		try {
			TbMyBusiness mybu = new TbMyBusiness();
			if (!StringUtility.isEmpty(jsonInput.get("mybuinessid"))) {
				mybu = GetMyBusiness(jsonInput.get("mybuinessid").getAsString());
				if (mybu != null) {
					if (!StringUtility.isEmpty(jsonInput.get("userid")))
						mybu.setsUserId(jsonInput.get("userid").getAsString());

					if (!StringUtility.isEmpty(jsonInput.get("partnerid")))
						mybu.setSPartnerId(jsonInput.get("partnerid").getAsString());

					if (!StringUtility.isEmpty(jsonInput.get("partnerbusinessserviceid")))
						mybu.setSPartnerBusinessServiceId(jsonInput.get("partnerbusinessserviceid").getAsString());

					if (!StringUtility.isEmpty(jsonInput.get("partnerbizcateid")))
						mybu.setSPartnerBizcateId(jsonInput.get("partnerbizcateid").getAsString());

					if (!StringUtility.isEmpty(jsonInput.get("partnerbusinessserviceid")))
						mybu.setSPartnerBusinessServiceId(jsonInput.get("partnerbusinessserviceid").getAsString());

					tbMyBusinessRepository.saveAndFlush(mybu);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	// Hàm kiểm tra authentication còn hiệu lực hay không
	@SuppressWarnings({ "all" })
	public Integer CheckAuthen(String Token, String UserId) {
		try {
			TbUserAuthentication authen = new TbUserAuthentication();
			StoredProcedureQuery q = entityManager
					.createStoredProcedureQuery("mdl_account.checkauthentication", TbUserAuthentication.class)
					.registerStoredProcedureParameter("Privatetoken", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("UserId", String.class, ParameterMode.IN)
					.setParameter("Privatetoken", Token).setParameter("UserId", UserId);
			authen = (TbUserAuthentication) q.getSingleResult();

			if (authen != null) {
				long timestamp = authen.getDCreatedDate().getTime();
				long milliseconds = (authen.getBIsExprited() * 60000);
				timestamp = timestamp + milliseconds;

				Date date = new Date();
				long time = date.getTime();
				if (timestamp > time) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch (Exception ex) {
			return 2;
		}
		return 2;
	}

	// Hàm trả về danh sách list các numbermybusiness
	@SuppressWarnings("unchecked")
	@Override
	public List<ViewNumberMyBusiness> GetNumberMyBusiness() {
		List<ViewNumberMyBusiness> lstnumbusiness = new ArrayList<ViewNumberMyBusiness>();
		try {
			StoredProcedureQuery qq = entityManager.createStoredProcedureQuery("mdl_account.getnumbermybusiness",
					ViewNumberMyBusiness.class);
			lstnumbusiness = (List<ViewNumberMyBusiness>) qq.getResultList();
		} catch (Exception ex) {

		}
		return lstnumbusiness;
	}

	// Hàm trả về password mới cho account khi reset password
	private String RandomRestPass() {
		Random rd = new Random();
		StringBuilder str = new StringBuilder();
		for (Integer i = 0; i < 5; i++) {
			str.append(rd.nextInt(9));
		}
		return str.toString();
	}

	// Hàm lấy một user theo username
	private TbUser GetUser(String Username) {
		StoredProcedureQuery qq = entityManager
				.createStoredProcedureQuery("mdl_account.getuserbyusername", TbUser.class)
				.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN)
				.setParameter("Username", Username);
		return (TbUser) qq.getSingleResult();
	}

	// Hàm lấy một customer theo username
	private TbCustomer GetCustomer(String Username) {
		StoredProcedureQuery qq = entityManager
				.createStoredProcedureQuery("mdl_account.getcustomerbyusername", TbCustomer.class)
				.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN)
				.setParameter("Username", Username);
		return (TbCustomer) qq.getSingleResult();
	}

	// Hàm lấy một my business
	private TbMyBusiness GetMyBusiness(String MyBuID) {
		StoredProcedureQuery qq = entityManager
				.createStoredProcedureQuery("mdl_account.getmybusiness", TbMyBusiness.class)
				.registerStoredProcedureParameter("myBusnessId", String.class, ParameterMode.IN)
				.setParameter("myBusnessId", MyBuID);
		return (TbMyBusiness) qq.getSingleResult();
	}

	@SuppressWarnings("all")
	private void Guiemail() throws Exception {
		Properties props = new Properties();
		props.put("mail.host", "mail.cloud9.net");

		Session mailConnection = Session.getInstance(props, null);
		Message msg = new MimeMessage(mailConnection);

		Address a = new InternetAddress("hanvansinh@gmail.com", "havasinhnn");
		Address b = new InternetAddress("hanvansinh@gmail.com");

		msg.setContent("Mail contect", "text/plain");
		msg.setFrom(a);
		msg.setRecipient(Message.RecipientType.TO, b);
		msg.setSubject("subject");
		Transport.send(msg);
	}

	public String sendEmails() throws AddressException, MessagingException, IOException {
		sendmail();
		return "Email sent successfully";
	}

	private void sendmail() throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.transport.protocol", "smtp");
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(Parameter.EMAIL_COB));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hanvansinh@gmail.com", false));
			msg.setSubject("Tutorials point email");
			msg.setText("message");
			Transport.send(msg);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = Parameter.EMAIL_COB;
			String password = Parameter.PASS_EMAIL_COB;
			return new PasswordAuthentication(username, password);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TbMybusinessWorkingtime> MybusinessWorkingtime(String wk) {
		List<TbMybusinessWorkingtime> time = new ArrayList<TbMybusinessWorkingtime>();
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_account.getMyBusinessWorkingTime", TbMybusinessWorkingtime.class)
				.registerStoredProcedureParameter("smyBusinessId", String.class, ParameterMode.IN)
				.setParameter("smyBusinessId", wk);
		time = (List<TbMybusinessWorkingtime>) q.getResultList();
		return time;
	}

	@Override
	public boolean registerPersonalAbsent(JsonObject jsonInput) {
		try {
			String absentId = UUID.randomUUID().toString();
			jsonInput.addProperty("sabsentid", absentId);
			Gson gson = new Gson();
			String value = gson.toJson(jsonInput);
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("mdl_account.regispersonalabsent")
					.registerStoredProcedureParameter("jsonInput", String.class, ParameterMode.IN)
					.setParameter("jsonInput", value);
			Integer result = (Integer) query.getSingleResult();
			return result == 1 ? true : false;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public boolean personalAbsentUpdate(JsonObject jsonInput) {
		try {
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PERSONAL_ABSENT_ID))) {
				TbPersonalAbsent Per = new TbPersonalAbsent();
				String Perid = jsonInput.get(Parameter.S_PERSONAL_ABSENT_ID).getAsString();
				Per = tbPersonalAbsentRepository.GetPerbyid(Perid);

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_MY_BUSINESS_ID)))
					Per.setSMyBusinessId(jsonInput.get(Parameter.S_MY_BUSINESS_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_ABSENT_RESON)))
					Per.setSAbsentReson(jsonInput.get(Parameter.S_ABSENT_RESON).getAsString());

				DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
				Date dateabsent;
				try {

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_ABSENT_DATE))) {
						dateabsent = da.parse(jsonInput.get(Parameter.D_ABSENT_DATE).getAsString());
						Per.setDAbsentDate(dateabsent);
					}
				} catch (ParseException e) {
				}

				tbPersonalAbsentRepository.saveAndFlush(Per);
				return true;
			}
			return false;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<TbPersonalAbsent> getPersonalAbsent(JsonObject getPersonalAbsent) {
		List<TbPersonalAbsent> lstPersonalAbsent = new ArrayList<TbPersonalAbsent>();
		Gson gson = new Gson();	
		String value =gson.toJson(getPersonalAbsent); 
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("mdl_account.getpersonalabsent", TbPersonalAbsent.class)
				.registerStoredProcedureParameter("jsonInput", String.class, ParameterMode.IN)
				.setParameter("jsonInput", value);
		lstPersonalAbsent = query.getResultList();
		return lstPersonalAbsent;
	}

	@Override
	public TbCustomer getCustomerByCustomerId(String customerId) {
		return tbCustomerRepository.getCustomerByCustomerId(customerId);
	}

	@Override
	public void addPocket(TbMypocket pocket) {
		tbMypocketRepository.save(pocket);
	}

	@Override
	public boolean getPocketByUserIdAndCurrencyI(String userId, String currencyId) {
		List<TbMypocket> pocket = tbMypocketRepository.getPocketByUserIdAndCurrencyI(userId, currencyId);
		if(pocket.size() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getImage(String id) {
		TbCustomer image = tbCustomerRepository.getCustomerByCustomerId(id);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		String imageData = jObject.get("bAvatar").getAsString();
		return imageData;
	}

	@Override
	public TbCustomerLoyaltyProgram checkPromotionForNewUser(String programId, String type, Date date) {
		TbCustomerLoyaltyProgram p = tbCustomerLoyaltyProgramRepository.getByIdandType(programId, type, date);		
		return p;
	}

	@Override
	public Object transferCobCoin(Object input) {		
		return transactionsFeignClient.transferCOBCoin(input);
	}
}