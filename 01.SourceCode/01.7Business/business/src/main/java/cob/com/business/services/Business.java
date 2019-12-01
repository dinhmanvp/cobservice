package cob.com.business.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cob.com.business.entities.TbMyproduct;
import cob.com.business.entities.TbOrder;
import cob.com.business.entities.TbProductCate;
import cob.com.business.entities.TbWorkinginfos;
import cob.com.business.ws.models.MedicalEmailDataModel;
import cob.com.business.ws.models.MydealInfo;
import cob.com.business.ws.models.ResponseMessage;

public interface Business {
	boolean registerProductCate(JsonObject productCateInfo);

	boolean productCateUpdate(JsonObject productCateInfo);

	List<TbProductCate> getProductCate(String partnerId);

	boolean registerProduct(JsonObject productInfo);

	boolean productUpdate(JsonObject productInfo);

	List<TbMyproduct> getProduct(String partnerId, String userId, String productId);

	boolean registerMydeal(JsonObject mydealInfo);

	boolean updateMydeal(JsonObject mydealInfo);

	List<MydealInfo> getMydeal(JsonObject input);

	List<TbWorkinginfos> getWorkingInfo(String PartnerId, String monthOfYear, String BusinessServiceId);

	String registerMedicalAppointment(JsonObject orderInfo);

	boolean OderUpdate(JsonArray orderInfo);

	List<TbOrder> getOder(String partnerId, String sbusinessServiceId);

	List<TbOrder> getOrderBuyerUserId(String buyerUserId, String orderStatusId);

	ResponseEntity<ResponseMessage> getPartnerNames();

	ResponseEntity<ResponseMessage> groupBusinessCateName();

	List<TbOrder> getOrderBySellerPartnerIdAndStatus(String sellerPartnerId, Integer status);

	TbOrder registerOrderByEcom(JsonObject orderInfo);

	List<MydealInfo> getDealByFriends(String userId, String languageCode, String contentSearch, int pageNumber,
			int pageSize, String partnerId, String groubBusId, String contenElement);

	Object sendEmail(Object object);
	
	MedicalEmailDataModel getMedicalEmailData(String orderId, String languageCode);
	
	void testSendEmail(String orderId, String languageCode);
	
	Integer likeDeal(String dealId, String userId);
	
	String getImage(String tablename, String id, String imageId);

	TbOrder getByOrderNumber (String orderId);
}
