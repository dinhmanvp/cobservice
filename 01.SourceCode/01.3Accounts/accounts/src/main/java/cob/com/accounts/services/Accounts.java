package cob.com.accounts.services;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;

import cob.com.accounts.entities.TbCustomer;
import cob.com.accounts.entities.TbCustomerLoyaltyProgram;
import cob.com.accounts.entities.TbMyBusiness;
import cob.com.accounts.entities.TbMybusinessWorkingtime;
import cob.com.accounts.entities.TbMypocket;
import cob.com.accounts.entities.TbPersonalAbsent;
import cob.com.accounts.entities.TbUser;
import cob.com.accounts.entities.ViewNumberMyBusiness;
import cob.com.accounts.ws.models.AccountInfo;
import cob.com.accounts.ws.models.ResponseMessage;

public interface Accounts {

	Integer RegisCustomerUser(JsonObject jsonInput);
	boolean RegisWorkingTime(JsonObject jsonInput);
	Integer CheckUsernamPhone(String Username, String Phone, String email, String cardId);

	boolean UpdateCustomerUser(JsonObject jsonInput);

	Integer ChangePassAccount(String Username, String PassOld, String PassNew);

	AccountInfo AcountLogin(String Username, String Password);

	Integer forgotPassWord(String Username, String Email);

	boolean myBusinessCreate(JsonObject jsonInput);

	TbMyBusiness getMyBusinessInfo(String MyBuID);

	boolean myBusinessUpdate(JsonObject jsonInput);

	List<ViewNumberMyBusiness> GetNumberMyBusiness();

	Integer CheckAuthen(String Token, String UserId);
	
	boolean registerPersonalAbsent(JsonObject jsonInput);
	
	boolean personalAbsentUpdate(JsonObject jsonInput);
	
	List<TbPersonalAbsent> getPersonalAbsent(JsonObject input);
	

//	BEGIN FEIGN CLIENT COMMUNICAIONT
	TbUser getByUserName(String username);

	Object sendEmail(Object object);
	
	Object sendNotify(Object object);
	
	ResponseMessage verifiedOTP(Object object);
//	END FEIGN CLIENT COMMUNICAIONT
	List<TbMybusinessWorkingtime> MybusinessWorkingtime(String wk);
	
	TbCustomer getCustomerByCustomerId(String customerId);
	
	boolean getPocketByUserIdAndCurrencyI(String userId, String currencyId);
	
	void addPocket(TbMypocket pocket);
	
	String getImage(String id);
	
	TbCustomerLoyaltyProgram checkPromotionForNewUser(String programId, String type, Date date);
	
	Object transferCobCoin(Object input);
}