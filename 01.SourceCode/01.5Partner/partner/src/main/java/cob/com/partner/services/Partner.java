package cob.com.partner.services;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonObject;

import cob.com.partner.entities.TbPartnerBusinessService;
import cob.com.partner.entities.TbPartnerHoliday;
import cob.com.partner.entities.TbPartnerInfo;
import cob.com.partner.entities.TbPartnerName;
import cob.com.partner.entities.TbPartnerRule;
import cob.com.partner.entities.TbPartnerWorkingtime;
import cob.com.partner.ws.models.GroupbusinessCateInfo;
import cob.com.partner.ws.models.OrderModel;
import cob.com.partner.ws.models.PartnerAccountInfo;
import cob.com.partner.ws.models.PartnerInfo;

public interface Partner {
	boolean partnerCreate(JsonObject jsonInput);
	boolean partnerUpdate(JsonObject jsonInput);
	boolean partnerAccountCreate(JsonObject jsonInput);
	List<TbPartnerRule> getPartnerRules();	
	List<GroupbusinessCateInfo> getPartnerBizcates(String partnerId);
	List<TbPartnerBusinessService> getPartnerBusinessServices(String partnerId);	
	List<PartnerAccountInfo> getPartnerAccounts(JsonObject input);
	boolean partnerAccountRemove(String partnerAccountId);
	boolean partnerAccountUpdate(JsonObject jsonInput);
	List<TbPartnerInfo> getPartners(String UserID, String GroupBusinessId, String BusinessServiceId, String GroupBusinessTypeId, Integer isApprove);	
	boolean registerPartnerWorkingtime(JsonObject jsonInput);
	List<TbPartnerWorkingtime> getPartnerWorkingtime(String partnerId, String BusinessServiceId);
	boolean registerPartnerContract(JsonObject jsonInput);	
	boolean registerPartnerHoliday(JsonObject partnerHoliday);
	boolean partnerHolidayUpdate(JsonObject partnerHoliday);
	List<TbPartnerHoliday>getPartnerHoliday(String spartnerId, String monthOfYear);	
	List<TbPartnerName> getAllParterName();	
	List<PartnerInfo> getPartnersForWorking(JsonObject jsonInput);

	List<OrderModel> getOrderForChoosing(String partnerId, String businessServiceId, String buzCate, Date date,
			String languageCode);
	List<OrderModel> getOrderForSeller(String partnerId, String businessServiceId, String buzCate, Date date,
			String sellerUserId, String languageCode);
	List<OrderModel> handleOrder(String partnerId, String businesServiceId, String groupBuzCate, Date d,
			String sSellerUserId, String languageCode);
	String getImage(String id);
}
