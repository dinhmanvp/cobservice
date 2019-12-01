package cob.com.business.services;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.business.dao.TbMydealCountryRepository;
import cob.com.business.dao.TbMydealRepository;
import cob.com.business.dao.TbMyproductRepository;
import cob.com.business.dao.TbOrderDetailRepository;
import cob.com.business.dao.TbOrderRepository;
import cob.com.business.dao.TbProductCateRepository;
import cob.com.business.entities.TbMappingHoliday;
import cob.com.business.entities.TbMydeal;
import cob.com.business.entities.TbMydealCountry;
import cob.com.business.entities.TbMyproduct;
import cob.com.business.entities.TbOrder;
import cob.com.business.entities.TbOrderDetail;
import cob.com.business.entities.TbProductCate;
import cob.com.business.entities.TbWorkinginfos;
import cob.com.business.intercom.BusinessFeignClient;
import cob.com.business.intercom.CoreFeignClient;
import cob.com.business.intercom.NotifyFeignClient;
import cob.com.business.log.LogMongoTemplate;
import cob.com.business.log.LoggingAspect;
import cob.com.business.log.models.AddLogDetailInfo;
import cob.com.business.utils.ConfigUtility;
import cob.com.business.utils.StringUtility;
import cob.com.business.ws.models.MedicalEmailDataModel;
import cob.com.business.ws.models.MydealInfo;
import cob.com.business.ws.models.ResponseMessage;
import cob.com.business.ws.param.Parameter;

@Component
public class BusinessImpl implements Business {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TbProductCateRepository tbProductCateRepository;

	@Autowired
	private TbMyproductRepository tbMyproductRepository;

	@Autowired
	private TbMydealRepository tbMydealRepository;

	@Autowired
	private TbMydealCountryRepository tbMydealCountryRepository;

	@Autowired
	private BusinessFeignClient businessFeignClient;

	@Autowired
	private TbOrderRepository tbOrderRepository;

	@Autowired
	private CoreFeignClient coreFeignClient;

	@Autowired
	private NotifyFeignClient notifyFeignClient;

	@Autowired
	private TbOrderDetailRepository tbOrderDetailRepository;
	
	@Autowired
	private ConfigUtility configUtil;
	
	private Object emailInput;
		
	final Runnable tSendEmail = new Runnable() {
		
	    @Override
	    public void run() {
	        try {
	        	notifyFeignClient.sendEmail(emailInput);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	};

	@Override
	public boolean registerProductCate(JsonObject productCateInfo) {
		try {
			TbProductCate pro = new TbProductCate();

			Date date = new Date();
			Long getid = date.getTime();
			String Proid = Long.toString(getid);
			pro.setSProductCateId(Proid);

			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_ICON)))
				pro.setSProductCateIcon(productCateInfo.get(Parameter.S_PRODUCT_CATE_ICON).getAsString());
			else
				pro.setSProductCateIcon(null);

			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_VN)))
				pro.setSProductCateNameVn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_VN).getAsString());
			else
				pro.setSProductCateNameVn(null);

			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_EN)))
				pro.setSProductCateNameEn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_EN).getAsString());
			else
				pro.setSProductCateNameEn(null);

			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_CN)))
				pro.setSProductCateNameCn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_CN).getAsString());
			else
				pro.setSProductCateNameCn(null);

			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PARTNER_ID)))
				pro.setSPartnerId(productCateInfo.get(Parameter.S_PARTNER_ID).getAsString());
			else
				pro.setSPartnerId(null);

			tbProductCateRepository.save(pro);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public boolean productCateUpdate(JsonObject productCateInfo) {
		try {
			if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_ID))) {
				String Proid = productCateInfo.get(Parameter.S_PRODUCT_CATE_ID).getAsString();
				TbProductCate pro = tbProductCateRepository.getProCatebyId(Proid);
				if (pro != null) {
					if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_ICON)))
						pro.setSProductCateIcon(productCateInfo.get(Parameter.S_PRODUCT_CATE_ICON).getAsString());

					if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_VN)))
						pro.setSProductCateNameVn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_VN).getAsString());

					if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_EN)))
						pro.setSProductCateNameEn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_EN).getAsString());

					if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_CN)))
						pro.setSProductCateNameCn(productCateInfo.get(Parameter.S_PRODUCT_CATE_NAME_CN).getAsString());

					if (!StringUtility.isEmpty(productCateInfo.get(Parameter.S_PARTNER_ID)))
						pro.setSPartnerId(productCateInfo.get(Parameter.S_PARTNER_ID).getAsString());

					tbProductCateRepository.saveAndFlush(pro);
					return true;
				}
			}
			return false;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<TbProductCate> getProductCate(String partnerId) {
		List<TbProductCate> lstProCate = tbProductCateRepository.getProCatebypartid(partnerId);
		return lstProCate;
	}

	@Override
	public boolean registerProduct(JsonObject productInfo) {
		try {
			TbMyproduct Mypro = new TbMyproduct();

			Date dtpro = new Date();
			Long idpro = dtpro.getTime();

			Mypro.setSProductId(Long.toString(idpro));

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PARTNER_ID)))
				Mypro.setSPartnerId(productInfo.get(Parameter.S_PARTNER_ID).getAsString());
			else
				Mypro.setSPartnerId(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_USER_ID)))
				Mypro.setSUserId(productInfo.get(Parameter.S_USER_ID).getAsString());
			else
				Mypro.setSUserId(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_CATE_ID)))
				Mypro.setSProductCateId(productInfo.get(Parameter.S_PRODUCT_CATE_ID).getAsString());
			else
				Mypro.setSProductCateId(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_VN)))
				Mypro.setSProductHeaderVn(productInfo.get(Parameter.S_PRODUCT_HEADER_VN).getAsString());
			else
				Mypro.setSProductHeaderVn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_EN)))
				Mypro.setSProductHeaderEn(productInfo.get(Parameter.S_PRODUCT_HEADER_EN).getAsString());
			else
				Mypro.setSProductHeaderEn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_CN)))
				Mypro.setSProductHeaderCn(productInfo.get(Parameter.S_PRODUCT_HEADER_CN).getAsString());
			else
				Mypro.setSProductHeaderCn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_VN)))
				Mypro.setSProductDescVn(productInfo.get(Parameter.S_PRODUCT_DESC_VN).getAsString());
			else
				Mypro.setSProductDescVn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_EN)))
				Mypro.setSProductDescEn(productInfo.get(Parameter.S_PRODUCT_DESC_EN).getAsString());
			else
				Mypro.setSProductDescEn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_CN)))
				Mypro.setSProductDescCn(productInfo.get(Parameter.S_PRODUCT_DESC_CN).getAsString());
			else
				Mypro.setSProductDescCn(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.N_PRICE)))
				Mypro.setNPrice(productInfo.get(Parameter.N_PRICE).getAsInt());
			else
				Mypro.setNPrice(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.N_DISCOUNT)))
				Mypro.setNDiscount(productInfo.get(Parameter.N_DISCOUNT).getAsInt());
			else
				Mypro.setNDiscount(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.N_SALEPRICE)))
				Mypro.setNSaleprice(productInfo.get(Parameter.N_SALEPRICE).getAsInt());
			else
				Mypro.setNSaleprice(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.N_SALEPRICE)))
				Mypro.setNSaleprice(productInfo.get(Parameter.N_SALEPRICE).getAsInt());
			else
				Mypro.setNSaleprice(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE_MAIN)))
				Mypro.setSProductImageMain(productInfo.get(Parameter.S_PRODUCT_IMAGE_MAIN).getAsString());
			else
				Mypro.setSProductImageMain(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE2)))
				Mypro.setSProductImage2(productInfo.get(Parameter.S_PRODUCT_IMAGE2).getAsString());
			else
				Mypro.setSProductImage2(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE3)))
				Mypro.setSProductImage3(productInfo.get(Parameter.S_PRODUCT_IMAGE3).getAsString());
			else
				Mypro.setSProductImage3(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE4)))
				Mypro.setSProductImage4(productInfo.get(Parameter.S_PRODUCT_IMAGE4).getAsString());
			else
				Mypro.setSProductImage4(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_CURRENCY)))
				Mypro.setSCurrency(productInfo.get(Parameter.S_CURRENCY).getAsString());
			else
				Mypro.setSCurrency(null);

			if (!StringUtility.isEmpty(productInfo.get(Parameter.N_RATING)))
				Mypro.setNRating(productInfo.get(Parameter.N_RATING).getAsInt());
			else
				Mypro.setNRating(null);

			tbMyproductRepository.save(Mypro);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public boolean productUpdate(JsonObject productInfo) {
		try {
			if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_ID))) {
				String Proid = productInfo.get(Parameter.S_PRODUCT_ID).getAsString();
				TbMyproduct Mypro = tbMyproductRepository.getProbyid(Proid);
				if (Mypro != null) {
					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PARTNER_ID)))
						Mypro.setSPartnerId(productInfo.get(Parameter.S_PARTNER_ID).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_USER_ID)))
						Mypro.setSUserId(productInfo.get(Parameter.S_USER_ID).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_CATE_ID)))
						Mypro.setSProductCateId(productInfo.get(Parameter.S_PRODUCT_CATE_ID).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_VN)))
						Mypro.setSProductHeaderVn(productInfo.get(Parameter.S_PRODUCT_HEADER_VN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_EN)))
						Mypro.setSProductHeaderEn(productInfo.get(Parameter.S_PRODUCT_HEADER_EN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_HEADER_CN)))
						Mypro.setSProductHeaderCn(productInfo.get(Parameter.S_PRODUCT_HEADER_CN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_VN)))
						Mypro.setSProductDescVn(productInfo.get(Parameter.S_PRODUCT_DESC_VN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_EN)))
						Mypro.setSProductDescEn(productInfo.get(Parameter.S_PRODUCT_DESC_EN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_DESC_CN)))
						Mypro.setSProductDescCn(productInfo.get(Parameter.S_PRODUCT_DESC_CN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.N_PRICE)))
						Mypro.setNPrice(productInfo.get(Parameter.N_PRICE).getAsInt());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.N_DISCOUNT)))
						Mypro.setNDiscount(productInfo.get(Parameter.N_DISCOUNT).getAsInt());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.N_SALEPRICE)))
						Mypro.setNSaleprice(productInfo.get(Parameter.N_SALEPRICE).getAsInt());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.N_SALEPRICE)))
						Mypro.setNSaleprice(productInfo.get(Parameter.N_SALEPRICE).getAsInt());
					else
						Mypro.setNSaleprice(null);

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE_MAIN)))
						Mypro.setSProductImageMain(productInfo.get(Parameter.S_PRODUCT_IMAGE_MAIN).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE2)))
						Mypro.setSProductImage2(productInfo.get(Parameter.S_PRODUCT_IMAGE2).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE3)))
						Mypro.setSProductImage3(productInfo.get(Parameter.S_PRODUCT_IMAGE3).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_PRODUCT_IMAGE4)))
						Mypro.setSProductImage4(productInfo.get(Parameter.S_PRODUCT_IMAGE4).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.S_CURRENCY)))
						Mypro.setSCurrency(productInfo.get(Parameter.S_CURRENCY).getAsString());

					if (!StringUtility.isEmpty(productInfo.get(Parameter.N_RATING)))
						Mypro.setNRating(productInfo.get(Parameter.N_RATING).getAsInt());

					tbMyproductRepository.saveAndFlush(Mypro);
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbMyproduct> getProduct(String partnerId, String userId, String productId) {

		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_business.getProduct", TbMyproduct.class)
				.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("productId", String.class, ParameterMode.IN)
				.setParameter("partnerId", partnerId).setParameter("userId", userId)
				.setParameter("productId", productId);

		List<TbMyproduct> result = q.getResultList();
		return result;
	}

	@Override
	public boolean registerMydeal(JsonObject mydealInfo) {
		try {
			TbMydeal myde = new TbMydeal();

			Date dtmy = new Date();
			Long idmyde = dtmy.getTime();

			String MydealId = Long.toString(idmyde);

			myde.setSMydealId(MydealId);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_PARTNER_ID)))
				myde.setSPartnerId(mydealInfo.get(Parameter.S_PARTNER_ID).getAsString());
			else
				myde.setSPartnerId(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_USER_ID)))
				myde.setSUserId(mydealInfo.get(Parameter.S_USER_ID).getAsString());
			else
				myde.setSUserId(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_IS_BUYER)))
				myde.setNIsBuyer(mydealInfo.get(Parameter.N_IS_BUYER).getAsInt());
			else
				myde.setNIsBuyer(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_HEADER)))
				myde.setSDealHeader(mydealInfo.get(Parameter.S_DEAL_HEADER).getAsString());
			else
				myde.setSDealHeader(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_CONTENT)))
				myde.setSDealContent(mydealInfo.get(Parameter.S_DEAL_CONTENT).getAsString());
			else
				myde.setSDealContent(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE1)))
				myde.setSDealImage1(mydealInfo.get(Parameter.S_DEAL_IMAGE1).getAsString());
			else
				myde.setSDealImage1(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE2)))
				myde.setSDealImage2(mydealInfo.get(Parameter.S_DEAL_IMAGE2).getAsString());
			else
				myde.setSDealImage2(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE3)))
				myde.setSDealImage3(mydealInfo.get(Parameter.S_DEAL_IMAGE3).getAsString());
			else
				myde.setSDealImage3(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_PRICE)))
				myde.setNPrice(mydealInfo.get(Parameter.N_PRICE).getAsInt());
			else
				myde.setNPrice(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_IS_BUYER_POSTING)))
				myde.setNIsBuyerPosting(mydealInfo.get(Parameter.N_IS_BUYER_POSTING).getAsInt());
			else
				myde.setNIsBuyerPosting(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID)))
				myde.setSGroupBusinessServiceId(mydealInfo.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID).getAsString());
			else
				myde.setSGroupBusinessServiceId(null);

			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_CURRENCY_ID)))
				myde.setScurrencyid(mydealInfo.get(Parameter.S_CURRENCY_ID).getAsString());
			
			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_BUSINESS_SERVICE_ID)))
				myde.setSbusinessserviceid(mydealInfo.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString());
			
			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_GROUP_BUSINESS_CATE_ID)))
				myde.setSgroupbusinesscateid(mydealInfo.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString());
			
			if (!StringUtility.isEmpty(mydealInfo.get("sproductId")))
				myde.setsProductId(mydealInfo.get("sproductId").getAsString());
			
			if (!StringUtility.isEmpty(mydealInfo.get("nQuantity")))
				myde.setnQuantity(mydealInfo.get("nQuantity").getAsInt());
							
			DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			Date Dtcr, Dtfr, Dtto;
//			try {
//
//				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_CREATE_DATE))) {
//					Dtcr = dt.parse(mydealInfo.get(Parameter.D_DEAL_CREATE_DATE).getAsString());
//					myde.setDDealCreateDate(Dtcr);
//				}
////				else
////					myde.setDDealCreateDate(null);
//			} catch (ParseException e) {
//				myde.setDDealCreateDate(null);
//			}
			myde.setDDealCreateDate(new Date(System.currentTimeMillis()));
			try {

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_VALID_FROM))) {
					Dtfr = dt.parse(mydealInfo.get(Parameter.D_DEAL_VALID_FROM).getAsString());
					myde.setDDealVlidFrom(Dtfr);
				} else
					myde.setDDealVlidFrom(null);
			} catch (ParseException e) {
				myde.setDDealVlidFrom(null);
			}

			try {

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_VALID_TO))) {
					Dtto = dt.parse(mydealInfo.get(Parameter.D_DEAL_VALID_TO).getAsString());
					myde.setDDealValidTo(Dtto);
				} else
					myde.setDDealValidTo(null);
			} catch (ParseException e) {
				myde.setDDealValidTo(null);
			}

			JsonArray countrysId = mydealInfo.getAsJsonArray(Parameter.COUNTRY_ID);
			if (countrysId.size() > 0) {
				for (Integer i = 0; i < countrysId.size(); i++) {
					TbMydealCountry decoun = new TbMydealCountry();
					JsonObject jsondecoun = (JsonObject) countrysId.get(i);
					Date dtcoun = new Date();
					Long idmycoun = dtcoun.getTime();
					String MyDealCounId = Long.toString(idmycoun);
					decoun.setSMydealCountryId(MyDealCounId);
					decoun.setSMydealId(MydealId);
					if (!StringUtility.isEmpty(jsondecoun.get(Parameter.Count_ID))) {
						decoun.setSCountryId(jsondecoun.get(Parameter.Count_ID).getAsString());
					} else
						decoun.setSCountryId(null);

					tbMydealCountryRepository.save(decoun);
				}
			}

			tbMydealRepository.save(myde);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean updateMydeal(JsonObject mydealInfo) {
		try {
			if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_MYDEAL_ID))) {
				TbMydeal myde = new TbMydeal();
				String MydealId = mydealInfo.get(Parameter.S_MYDEAL_ID).getAsString();
				myde = tbMydealRepository.Mydealbyid(MydealId);

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_PARTNER_ID)))
					myde.setSPartnerId(mydealInfo.get(Parameter.S_PARTNER_ID).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_USER_ID)))
					myde.setSUserId(mydealInfo.get(Parameter.S_USER_ID).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_IS_BUYER)))
					myde.setNIsBuyer(mydealInfo.get(Parameter.N_IS_BUYER).getAsInt());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_HEADER)))
					myde.setSDealHeader(mydealInfo.get(Parameter.S_DEAL_HEADER).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_CONTENT)))
					myde.setSDealContent(mydealInfo.get(Parameter.S_DEAL_CONTENT).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE1)))
					myde.setSDealImage1(mydealInfo.get(Parameter.S_DEAL_IMAGE1).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE2)))
					myde.setSDealImage2(mydealInfo.get(Parameter.S_DEAL_IMAGE2).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_DEAL_IMAGE3)))
					myde.setSDealImage3(mydealInfo.get(Parameter.S_DEAL_IMAGE3).getAsString());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_PRICE)))
					myde.setNPrice(mydealInfo.get(Parameter.N_PRICE).getAsInt());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.N_IS_BUYER_POSTING)))
					myde.setNIsBuyerPosting(mydealInfo.get(Parameter.N_IS_BUYER_POSTING).getAsInt());

				if (!StringUtility.isEmpty(mydealInfo.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID)))
					myde.setSGroupBusinessServiceId(
							mydealInfo.get(Parameter.S_GROUP_BUSINESS_SERVICE_ID).getAsString());
				
				if (!StringUtility.isEmpty(mydealInfo.get("sproductId")))
					myde.setsProductId(mydealInfo.get("sproductId").getAsString());
				
				if (!StringUtility.isEmpty(mydealInfo.get("nQuantity")))
					myde.setnQuantity(mydealInfo.get("nQuantity").getAsInt());

				DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				Date Dtcr, Dtfr, Dtto;
				try {

					if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_CREATE_DATE))) {
						Dtcr = dt.parse(mydealInfo.get(Parameter.D_DEAL_CREATE_DATE).getAsString());
						myde.setDDealCreateDate(Dtcr);
					}
				} catch (ParseException e) {
				}

				try {

					if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_VALID_FROM))) {
						Dtfr = dt.parse(mydealInfo.get(Parameter.D_DEAL_VALID_FROM).getAsString());
						myde.setDDealVlidFrom(Dtfr);
					}
				} catch (ParseException e) {
				}

				try {

					if (!StringUtility.isEmpty(mydealInfo.get(Parameter.D_DEAL_VALID_TO))) {
						Dtto = dt.parse(mydealInfo.get(Parameter.D_DEAL_VALID_TO).getAsString());
						myde.setDDealValidTo(Dtto);
					}
				} catch (ParseException e) {
				}

				JsonArray countrysId = mydealInfo.getAsJsonArray(Parameter.COUNTRY_ID);
				if (countrysId.size() > 0) {
					DeleteDealCountry(MydealId);

					// q.notifyAll();

					// String a = tbMydealCountryRepository.deletebydealid(MydealId);
					for (Integer i = 0; i < countrysId.size(); i++) {
						TbMydealCountry decoun = new TbMydealCountry();
						JsonObject jsondecoun = (JsonObject) countrysId.get(i);

						Date dtcoun = new Date();
						Thread.sleep(1);
						Long idmycoun = dtcoun.getTime();

						String MyDealCounId = Long.toString(idmycoun);

						decoun.setSMydealCountryId(MyDealCounId);
						decoun.setSMydealId(MydealId);
						if (!StringUtility.isEmpty(jsondecoun.get(Parameter.Count_ID))) {
							decoun.setSCountryId(jsondecoun.get(Parameter.Count_ID).getAsString());
						} else
							decoun.setSCountryId(null);
						tbMydealCountryRepository.save(decoun);
					}
				}

				tbMydealRepository.saveAndFlush(myde);
				return true;
			}

		} catch (Exception ex) {
		}
		return false;
	}

	private Integer DeleteDealCountry(String MydealId) {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_business.deletebymydealid")
				.registerStoredProcedureParameter("mydealId", String.class, ParameterMode.IN)
				.setParameter("mydealId", MydealId);
		Integer a = (Integer) q.getSingleResult();
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MydealInfo> getMydeal(JsonObject input) {

		String partnerId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.S_PARTNER_ID)))
			partnerId = input.get(Parameter.S_PARTNER_ID).getAsString();

		String userId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.S_USER_ID)))
			userId = input.get(Parameter.S_USER_ID).getAsString();

		String mydealId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.S_MYDEAL_ID)))
			mydealId = input.get(Parameter.S_MYDEAL_ID).getAsString();

		String languageCode = "VN";
		if (!StringUtility.isEmpty(input.get(Parameter.LANGUAGE_CODE)))
			languageCode = StringUtils.upperCase(input.get(Parameter.LANGUAGE_CODE).getAsString());

		String contentSearch = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.CONTENT_SEARCH)))
			contentSearch = StringUtils.upperCase(input.get(Parameter.CONTENT_SEARCH).getAsString());

		int pageNumber = 1;
		if (!StringUtility.isEmpty(input.get(Parameter.PAGE_NUMBER)))
			pageNumber = input.get(Parameter.PAGE_NUMBER).getAsInt();

		// pageSize
		int pageSize = 0;
		if (!StringUtility.isEmpty(input.get(Parameter.PAGE_NUMBER)))
			pageSize = input.get(Parameter.PAGE_SIZE).getAsInt();

		String countryId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.Count_ID)))
			countryId = input.get(Parameter.Count_ID).getAsString();
		
		Integer isBuyer = 0;
		if(!StringUtility.isEmpty(input.get("isbuyer"))) {
			isBuyer = input.get("isbuyer").getAsInt();
		}
		
		String groupBiz = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(input.get("groupbusinessid"))) {
			groupBiz = input.get("groupbusinessid").getAsString();
		}
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_business.getMydeal", MydealInfo.class)
				.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("mydealId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("contentSearch", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("countryId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pageNumber", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("isbuyer", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("groupbusinessid", String.class, ParameterMode.IN)
				.setParameter("partnerId", partnerId).setParameter("userId", userId).setParameter("mydealId", mydealId)
				.setParameter("languageCode", languageCode).setParameter("contentSearch", contentSearch)
				.setParameter("countryId", countryId).setParameter("pageNumber", pageNumber)
				.setParameter("pageSize", pageSize).setParameter("isbuyer", isBuyer).setParameter("groupbusinessid", groupBiz);
		List<MydealInfo> lstmydeal = q.getResultList();
//		for (Integer i = 0; i < lstmydeal.size(); i++) {
//			MydealInfo mydeal = new MydealInfo();
//			mydeal = lstmydeal.get(i);
//			mydealInfo mydealinfo = new mydealInfo();
//			mydealinfo.mydeal = mydeal;
//
//			List<TbMydealCountry> lstmydealCountry = new ArrayList<TbMydealCountry>();
//			lstmydealCountry = tbMydealCountryRepository.Mydecountbydeid(mydeal.getSMydealId());
//
//			List<CountryId> lstcountryid = new ArrayList<CountryId>();
//			for (Integer j = 0; j < lstmydealCountry.size(); j++) {
//				TbMydealCountry mydealcountry = new TbMydealCountry();
//				mydealcountry = lstmydealCountry.get(j);
//				CountryId countryid = new CountryId();
//				countryid.countryId = mydealcountry.getSCountryId();
//				lstcountryid.add(countryid);
//			}
//			mydealinfo.countrysId = lstcountryid;
//			LstMydealInfo.add(mydealinfo);
//		}
		return lstmydeal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbWorkinginfos> getWorkingInfo(String PartnerId, String monthOfYear, String BusinessServiceId) {

		// Get DateStart, EndDate;
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(monthOfYear.substring(0, 4)), Integer.parseInt(monthOfYear.substring(5)), 1);
		int DayofWeek = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int DayofWeekL = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<Date> lst = ConvertMonthOfYear(monthOfYear);

		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_business.getworkinginfo", TbWorkinginfos.class)
				.registerStoredProcedureParameter("partnerid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("DateStart", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("DateEnd", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("BusinessServiceId", String.class, ParameterMode.IN)
				.setParameter("partnerid", PartnerId).setParameter("DateStart", lst.get(0))
				.setParameter("DateEnd", lst.get(1)).setParameter("BusinessServiceId", BusinessServiceId);
		List<TbWorkinginfos> lstorder = q.getResultList();

		// List<TbWorkinginfos> workinfo = InsertListWorkingInfos(lstorder);

		lstorder = InsertCounPerDay(lstorder, PartnerId, BusinessServiceId);
		lstorder = InsertHoliday(lstorder, PartnerId, monthOfYear);

		// return workinfo;
		return lstorder;
	}

	public List<Date> ConvertMonthOfYear(String monthOfYear) {
		String year = monthOfYear.substring(0, 4);
		String month = monthOfYear.substring(4, 6);
		Integer Y = Integer.parseInt(year);
		Integer M = Integer.parseInt(month);
		StringBuilder Start = new StringBuilder();
		StringBuilder End = new StringBuilder();
		Date dateStart = null, dateEnd = null;
		boolean Nhuan = false;

		List<Date> lst = new ArrayList<Date>();

		if (Y % 400 == 0 || (Y % 4 == 0 && Y % 100 != 0)) {
			Nhuan = true;
		}

		if (Nhuan == true) {
			if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12) {
				End.append("31");
			}
			if (M == 4 || M == 6 || M == 9 || M == 11) {
				End.append("30");
			}
			if (M == 2) {
				End.append("29");
			}
		}
		if (Nhuan == false) {
			if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12) {
				End.append("31");
			}
			if (M == 4 || M == 6 || M == 9 || M == 11) {
				End.append("30");
			}
			if (M == 2) {
				End.append("28");
			}

		}
		End.append("/");
		End.append(month);
		End.append("/");
		End.append(year);
		Start.append("1/");
		Start.append(month);
		Start.append("/");
		Start.append(year);

		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dateStart = dt.parse(Start.toString());
			dateEnd = dt.parse(End.toString());
		} catch (ParseException e) {
		}

		lst.add(dateStart);
		lst.add(dateEnd);
		return lst;
	}

//	@SuppressWarnings("unused")
//	private List<TbWorkinginfos> InsertListWorkingInfos(List<TbOrder> lstorder) {
//		List<TbWorkinginfos> lstinfo = new ArrayList<TbWorkinginfos>();
//		for (Integer i = 0; i < lstorder.size(); i++) {
//			TbWorkinginfos info = new TbWorkinginfos();
//			info.setDAppointmentDate(lstorder.get(i).getDAppointmentDate());
//			info.setnAppointmentNumber(lstorder.get(i).getNAppointmentNumber());
//			info.setNCountPerDay(0);
//			info.setNisHoliday(0);
//			info.setSBusinessServiceId(lstorder.get(i).getSBusinessServiceId());
//			info.setSPartnerId(lstorder.get(i).getSSellerPartnerId());
//			lstinfo.add(info);
//		}
//		return lstinfo;
//	}

	private List<TbWorkinginfos> InsertCounPerDay(List<TbWorkinginfos> lstworkinfo, String Partnerid,
			String BusinessServiceId) {

		JsonObject jObject = GetPartnerWorkingtime(Partnerid, BusinessServiceId);

		JsonArray arraycounper = jObject.getAsJsonArray(Parameter.PARTNER_WORKINGTIME);
		Integer Sizeelst = lstworkinfo.size();
		Integer Sizearray = arraycounper.size();
		for (Integer i = 0; i < Sizeelst; i++) {
			TbWorkinginfos infos = lstworkinfo.get(i);
			for (Integer j = 0; j < Sizearray; j++) {
				JsonObject json = (JsonObject) arraycounper.get(j);
				String bu = json.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString();
				Integer countpe = json.get(Parameter.N_COUNT_PER_DAY).getAsInt();
				String buserget = infos.getSBusinessServiceId();
				if (buserget != null) {
					if (buserget.equals(bu)) {
						lstworkinfo.get(i).setNCountPerDay(countpe);
						infos.setIsChosen(json.get(Parameter.IS_CHOSEN).getAsInt());
						Calendar c = Calendar.getInstance();
						c.setTime(lstworkinfo.get(i).getDAppointmentDate());
						int DayofWeek = c.get(Calendar.DAY_OF_WEEK);

						// lay gio bat dau cua ngay lam viec
						boolean sisOffDay = false;
						lstworkinfo.get(i).setSisOffDay("0");
						if (DayofWeek == 2 && !StringUtility.isEmpty(json.get(Parameter.S_MO_IS_OFF))
								&& "1".equals(json.get(Parameter.S_MO_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 3 && !StringUtility.isEmpty(json.get(Parameter.S_TU_IS_OFF))
								&& "1".equals(json.get(Parameter.S_TU_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 4 && !StringUtility.isEmpty(json.get(Parameter.S_WE_IS_OFF))
								&& "1".equals(json.get(Parameter.S_WE_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 5 && !StringUtility.isEmpty(json.get(Parameter.S_TH_IS_OFF))
								&& "1".equals(json.get(Parameter.S_TH_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 6 && !StringUtility.isEmpty(json.get(Parameter.S_FR_IS_OFF))
								&& "1".equals(json.get(Parameter.S_FR_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 7 && !StringUtility.isEmpty(json.get(Parameter.s_SA_IS_OFF))
								&& "1".equals(json.get(Parameter.s_SA_IS_OFF).getAsString())) {
							sisOffDay = true;
						} else if (DayofWeek == 1 && !StringUtility.isEmpty(json.get(Parameter.S_SU_IS_OFF))
								&& "1".equals(json.get(Parameter.S_SU_IS_OFF).getAsString())) {
							sisOffDay = true;
						}

						if (sisOffDay)
							lstworkinfo.get(i).setSisOffDay("1");
					}
				}
			}
		}
		return lstworkinfo;
	}

	private List<TbWorkinginfos> InsertHoliday(List<TbWorkinginfos> workinfo, String PartnerId, String monthOfYear) {
		ResponseMessage Res = getPartnerHoliday(PartnerId, monthOfYear);

		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(Res.getResponseData()).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();

		List<TbMappingHoliday> lstholi = getHoliday(jObject);// chuyển từ Object sang List Table

		List<TbWorkinginfos> result = InsertisHoliday(workinfo, lstholi);
		return result;
	}

	private Integer getPartnercounPerDay(String Partnerid, String BuserviceId) {
		/*
		 * ResponseMessage counper =
		 * businessFeignClient.getPartnerWorkingtime(Partnerid); Gson gson = new Gson();
		 * JsonElement jElement =
		 * gson.toJsonTree(counper.getResponseData()).getAsJsonObject(); JsonObject
		 * jObject = jElement.getAsJsonObject();
		 * 
		 * JsonArray arraycounper =
		 * jObject.getAsJsonArray(Parameter.PARTNER_WORKINGTIME); JsonObject json =
		 * (JsonObject) arraycounper.get(0);
		 */
		JsonObject jObject = GetPartnerWorkingtime(Partnerid, BuserviceId);

		JsonArray arraycounper = jObject.getAsJsonArray(Parameter.PARTNER_WORKINGTIME);

		JsonObject json = (JsonObject) arraycounper.get(0);

		Integer counperday = 0;
		if (!StringUtility.isEmpty(json.get(Parameter.N_COUNT_PER_DAY)))
			counperday = json.get(Parameter.N_COUNT_PER_DAY).getAsInt();

		return counperday;
	}

	private List<TbWorkinginfos> InsertisHoliday(List<TbWorkinginfos> lstwork, List<TbMappingHoliday> mappholi) {
		for (Integer i = 0; i < lstwork.size(); i++) {
			for (Integer j = 0; j < mappholi.size(); j++) {
				if (lstwork.get(i).getDAppointmentDate().getTime() == mappholi.get(j).dholidayDate.getTime()) {
					lstwork.get(i).setNisHoliday(1);
					lstwork.get(i).setSholidayDesc(mappholi.get(j).sholidayDesc);
				}
			}
			if (lstwork.get(i).getNAppointmentNumber() == null) {
				lstwork.get(i).setnAppointmentNumber(0);
			}
			if (lstwork.get(i).getNCountPerDay() == null) {
				lstwork.get(i).setNCountPerDay(0);
			}
			if (lstwork.get(i).getNAppointmentNumber() >= lstwork.get(i).getNCountPerDay()) {
				lstwork.get(i).setSIsFullDay("true");
			} else {
				lstwork.get(i).setSIsFullDay("false");
			}

		}
		return lstwork;
	}

	private List<TbMappingHoliday> getHoliday(JsonObject object) {
		List<TbMappingHoliday> lstmappholi = new ArrayList<TbMappingHoliday>();
		JsonArray arrayobjectholiday = object.getAsJsonArray(Parameter.PARTNER_HOLIDAYS);
		if (arrayobjectholiday.size() > 0) {
			for (Integer i = 0; i < arrayobjectholiday.size(); i++) {
				TbMappingHoliday holi = new TbMappingHoliday();
				JsonObject ob = (JsonObject) arrayobjectholiday.get(i);
				// holi.dholidayDate = ob.get(Parameter.D_HOLIDAY_DATE);

				DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
				Date Dtcr;
				try {

					if (!StringUtility.isEmpty(ob.get(Parameter.D_HOLIDAY_DATE))) {
						Dtcr = dt.parse(ob.get(Parameter.D_HOLIDAY_DATE).getAsString());
						holi.dholidayDate = Dtcr;
					} else
						holi.dholidayDate = null;
				} catch (ParseException e) {
					holi.dholidayDate = null;
				}

				if (!StringUtility.isEmpty(ob.get(Parameter.S_HOLIDAY_DESC)))
					holi.sholidayDesc = ob.get(Parameter.S_HOLIDAY_DESC).getAsString();
				else
					holi.sholidayDesc = null;

				if (!StringUtility.isEmpty(ob.get(Parameter.S_PARTNER_HOLIDAY_ID)))
					holi.spartnerHolidayId = ob.get(Parameter.S_PARTNER_HOLIDAY_ID).getAsString();
				else
					holi.spartnerHolidayId = null;

				if (!StringUtility.isEmpty(ob.get(Parameter.S_PARTNER_ID)))
					holi.spartnerId = ob.get(Parameter.S_PARTNER_ID).getAsString();
				else
					holi.spartnerId = null;

				lstmappholi.add(holi);
			}
		}
		return lstmappholi;
	}

	private ResponseMessage getPartnerHoliday(String PartnerID, String monthOfYear) {

		return businessFeignClient.getPartnerHoliday(PartnerID, monthOfYear);
	}

	// 0: lich kham da day, 1 insert thanh cong, 2 insert that bai
	@SuppressWarnings("unused")
	@Override
	public String registerMedicalAppointment(JsonObject orderInfo) {
			String Orderid;
			try {
				Orderid = StringUtility.HexSHA(StringUtility.Generating());
			} catch (NoSuchAlgorithmException e1) {
				return "error";// 2;
			}
			orderInfo.addProperty("Orderid", Orderid);			
			Integer DayofWeek = null;
			DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			Date Dtap, Dtod;
			try {
				Dtap = dt.parse(orderInfo.get(Parameter.D_APPOINTMETN_DATE).getAsString());
				Calendar c = Calendar.getInstance();
				c.setTime(Dtap);
				DayofWeek = c.get(Calendar.DAY_OF_WEEK);

				if (compareDate(Dtap)) {
					return "ngaycu";
				}
			} catch (ParseException e) {
				return "notwork";// 3;
			}
			orderInfo.addProperty("DayofWeek", DayofWeek);
					
			try {
				Gson gson = new Gson();	
				String order =gson.toJson(orderInfo);
				StoredProcedureQuery q = entityManager
						.createStoredProcedureQuery("mdl_business.registerMedicalAppointment")
						.registerStoredProcedureParameter("jsonInput", String.class, ParameterMode.IN)
						.setParameter("jsonInput", order);
				Integer result = (Integer) q.getSingleResult();
				if(result == 1) {				
					String languageCode = orderInfo.get(Parameter.LANGUAGE_CODE) == null ? ""
							: orderInfo.get(Parameter.LANGUAGE_CODE).getAsString();
					try {
						MedicalEmailDataModel data = getMedicalEmailData(Orderid, languageCode);
						Map<Object, Object> object = new HashMap<Object, Object>();
						object.put(Parameter.USERNAME, data.getUsername());
						object.put(Parameter.TEMPLATE_NAME, Parameter.MEDICAL_ORDER);
						Map<Object, Object> params = new HashMap<Object, Object>();
						params.put("name", data.getP_name());
						params.put("age", data.getP_age());
						params.put("gender", data.getGender());
						params.put("hospital", data.getHospital());
						params.put("department", data.getDepartment());
						params.put("date", data.getD_appointment_date());
						params.put("time", data.getS_appointment_time());
						object.put(Parameter.PARAMS, params);
						this.emailInput = object;
						new Thread(tSendEmail).start();
						//sendEmail(object);
					} catch (Exception e) {
						AddLogDetailInfo testApiDetail = new AddLogDetailInfo();
						testApiDetail.setLogDetailId(LoggingAspect.logId);
						testApiDetail.set_detailname("sendEmail Error:");
						testApiDetail.set_detail(e.getMessage());
						String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
						String collectionName = configUtil.getProperty("spring.data.mongodb.collectiondetailrefix") + timeStamp;
						LogMongoTemplate.logInfo(testApiDetail, collectionName);
					}				
					return Orderid;// 1
				}
				else if(result == -1) {
					return "appointed";
				}
				else if(result == 2) {
					return "closed";
				}
				else {
					return "Caerror";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return "error";// 2;
			}
	}
	private boolean compareDate(Date da) {
		Date date = Calendar.getInstance().getTime();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String today = formatter.format(date);
		Date a;
		try {
			a = formatter.parse(today);
		} catch (ParseException e) {
			a = date;
			e.printStackTrace();
		}
		if (da.compareTo(a) < 0) {
			return true;
		}
		return false;
	}

	// lay gio kham cho khach hang
	private String GetAppointmentTime(String ParnetId, Integer Dayofweek, Integer appointnumber,
			String Businessserviceid) {
		try {
			JsonObject jObject = GetPartnerWorkingtime(ParnetId, Businessserviceid);

			JsonArray arraycounper = jObject.getAsJsonArray(Parameter.PARTNER_WORKINGTIME);
			JsonObject json = (JsonObject) arraycounper.get(0);

			// Integer appointnumber = appointnumber;
			Integer durationForSession = 1;
			String FromTime = null;
			String Hours = "";
			String Minutes = "";

			if (!StringUtility.isEmpty(json.get(Parameter.N_DURATION_FOR_SESSION))) {
				durationForSession = json.get(Parameter.N_DURATION_FOR_SESSION).getAsInt();
			}

			// lay gio bat dau cua ngay lam viec
			if (Dayofweek == 2) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_MO_FROM))) {
					FromTime = json.get(Parameter.S_MO_FROM).getAsString();

				}
			} else if (Dayofweek == 3) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_TU_FROM))) {
					FromTime = json.get(Parameter.S_TU_FROM).getAsString();
				}
			} else if (Dayofweek == 4) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_WE_FROM))) {
					FromTime = json.get(Parameter.S_WE_FROM).getAsString();
				}
			} else if (Dayofweek == 5) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_TH_FROM))) {
					FromTime = json.get(Parameter.S_TH_FROM).getAsString();
				}
			} else if (Dayofweek == 6) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_FR_FROM))) {
					FromTime = json.get(Parameter.S_FR_FROM).getAsString();
				}
			} else if (Dayofweek == 7) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_SA_FROM))) {
					FromTime = json.get(Parameter.S_SA_FROM).getAsString();
				}
			} else if (Dayofweek == 1) {
				if (!StringUtility.isEmpty(json.get(Parameter.S_SU_FROM))) {
					FromTime = json.get(Parameter.S_SU_FROM).getAsString();
				}
			}
			try {
				String[] time = FromTime.split(":");
				Hours = time[0];
				Minutes = time[1];
				Integer temph = durationForSession * appointnumber / 60;
				Integer tempm = durationForSession * appointnumber % 60;
				tempm = tempm + Integer.parseInt(Minutes);

				if (tempm >= 60) {
					tempm = tempm - 60;
					temph = temph + 1 + Integer.parseInt(Hours);
				}
				temph = temph + Integer.parseInt(Hours);
				String phut = "00";
				if (tempm < 10) {
					phut = "0" + tempm.toString();
				} else
					phut = tempm.toString();
				FromTime = temph.toString() + ":" + phut;
			} catch (Exception ex) {
			}
			return FromTime;
		} catch (Exception ex) {
			return "";
		}

	}

	private Integer GetPointNumber(String ParnetId, String BuserviceId, Date AppointDate) {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_business.getmaxappointmentnumber")
				.registerStoredProcedureParameter("Partnerid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("BuserviceId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("appointDate", Date.class, ParameterMode.IN)
				.setParameter("Partnerid", ParnetId).setParameter("BuserviceId", BuserviceId)
				.setParameter("appointDate", AppointDate);
		Integer number = (Integer) q.getSingleResult();
		if (number == null)
			number = 0;
		Integer coun = getPartnercounPerDay(ParnetId, BuserviceId);
		if (coun == 0)
			return -1;
		if (number + 1 > coun) {
			return 0;
		}
		return number + 1;
	}

	private JsonObject GetPartnerWorkingtime(String PartnerId, String BusinessServiceId) {
		// Object input = new Object();
		HashMap<String, String> input = new HashMap<>();
		input.put("spartnerId", PartnerId);
		input.put("sbusinessServiceId", BusinessServiceId);

		ResponseMessage counper = businessFeignClient.getPartnerWorkingtime(input);
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(counper.getResponseData()).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		/*
		 * JsonArray arraycounper =
		 * jObject.getAsJsonArray(Parameter.PARTNER_WORKINGTIME); JsonObject json =
		 * (JsonObject) arraycounper.get(0); return json;
		 */
		return jObject;
	}

	@Override
	public boolean OderUpdate(JsonArray jArray) {
		try {			
			for (JsonElement jsonElement : jArray) {
				JsonObject orderInfo = new JsonObject();
				orderInfo = jsonElement.getAsJsonObject();
				if (!StringUtility.isEmpty(orderInfo .get(Parameter.S_ORDER_NUMBER))) {
					String orderNumber = orderInfo.get(Parameter.S_ORDER_NUMBER).getAsString();
					TbOrder or = tbOrderRepository.Orderbyid(orderNumber);
					if (or != null) {

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_BUYER_PARTNER_ID)))
							or.setSBuyerPartnerId(orderInfo.get(Parameter.S_BUYER_PARTNER_ID).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_GROUP_BUSINESS_CATE_ID)))
							or.setSGroupBusinessCateId(orderInfo.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_BUYER_USER_ID)))
							or.setSBuyerUserId(orderInfo.get(Parameter.S_BUYER_USER_ID).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_ORDER_STATUS_ID)))
							or.setNOrderStatusId(orderInfo.get(Parameter.N_ORDER_STATUS_ID).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_PATIENT_NAME)))
							or.setSPatientName(orderInfo.get(Parameter.S_PATIENT_NAME).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_PATIENT_AGE)))
							or.setSPatientAge(orderInfo.get(Parameter.S_PATIENT_AGE).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_PATIENT_GENDER)))
							or.setSPatientGender(orderInfo.get(Parameter.S_PATIENT_GENDER).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_BHYT)))
							or.setNBhyt(orderInfo.get(Parameter.N_BHYT).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_IS_BUYER_PARTNER_CONFIRMED)))
							or.setNIsBuyerPartnerConfirmed(
									orderInfo.get(Parameter.N_IS_BUYER_PARTNER_CONFIRMED).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_BUYER_PARTNER_CONFIRMEDBY)))
							or.setSBuyerPartnerConfirmedBy(
									orderInfo.get(Parameter.S_BUYER_PARTNER_CONFIRMEDBY).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_IS_DONE)))
							or.setNIsDone(orderInfo.get(Parameter.N_IS_DONE).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_IS_PAID)))
							or.setNIsPaid(orderInfo.get(Parameter.N_IS_PAID).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_SELLER_USER_ID)))
							or.setSSellerUserId(orderInfo.get(Parameter.S_SELLER_USER_ID).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED)))
							or.setNIsSellerPartnerConfirmed(
									orderInfo.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED)))
							or.setNIsSellerPartnerConfirmed(
									orderInfo.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_SELLER_PARTNER_CONFIRMED_BY)))
							or.setSSellerPartnerConfirmedBy(
									orderInfo.get(Parameter.S_SELLER_PARTNER_CONFIRMED_BY).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_TOTAL_AMOUNT)))
							or.setNTotalAmount(orderInfo.get(Parameter.N_TOTAL_AMOUNT).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_CURRENCY)))
							or.setSCurrency(orderInfo.get(Parameter.S_CURRENCY).getAsString());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.N_BUYER_RAKING)))
							or.setNBuyerRaking(orderInfo.get(Parameter.N_BUYER_RAKING).getAsInt());

						if (!StringUtility.isEmpty(orderInfo.get(Parameter.S_ORDER_SUMMARY)))
							or.setSOderSummary(orderInfo.get(Parameter.S_ORDER_SUMMARY).getAsString());	

						tbOrderRepository.saveAndFlush(or);
						return true;
					}
				}				
			}
			return false;

		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<TbOrder> getOder(String partnerId, String sbusinessServiceId) {
		List<TbOrder> lstor = new ArrayList<TbOrder>();
		lstor = tbOrderRepository.getOrderby(partnerId, sbusinessServiceId);
		return lstor;
	}

	@Override
	public ResponseEntity<ResponseMessage> getPartnerNames() {
		return businessFeignClient.getPartnerNames();
	}

	@Override
	public List<TbOrder> getOrderBuyerUserId(String buyerUserId, String orderStatusId) {
		List<TbOrder> orders = new ArrayList<TbOrder>();
		try {
			orders = tbOrderRepository.getOrderByBuyerUserId(buyerUserId, Integer.valueOf(orderStatusId));
		} catch (Exception e) {
			return orders;
		}
		return orders;
	}

	@Override
	public ResponseEntity<ResponseMessage> groupBusinessCateName() {
		return coreFeignClient.groupBusinessCateName();
	}

	@Override
	public List<TbOrder> getOrderBySellerPartnerIdAndStatus(String sellerPartnerId, Integer status) {
		List<TbOrder> orders = new ArrayList<TbOrder>();
		try {
			orders = tbOrderRepository.getOrderBySellerPartnerIdAndStatus(sellerPartnerId, status);
		} catch (Exception e) {
			return orders;
		}
		return orders;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	@Override
	public TbOrder registerOrderByEcom(JsonObject input) {
		try {

			TbOrder entity = new TbOrder();

			String Orderid = StringUtility.HexSHA(StringUtility.Generating());
			entity.setSOrderNumber(Orderid);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			if (!StringUtility.isEmpty(input.get(Parameter.D_APPOINTMETN_DATE))) {
				Date AppointmentDate = df.parse(input.get(Parameter.D_APPOINTMETN_DATE).getAsString());
				entity.setDAppointmentDate(AppointmentDate);
			}

			String strDate = df.format(new Date());
			Date orderDate = df.parse(strDate);
			entity.setDOrderDate(orderDate);

			if (!StringUtility.isEmpty(input.get(Parameter.N_APPOINTMENT_NUMBER))) {
				entity.setNAppointmentNumber(input.get(Parameter.N_APPOINTMENT_NUMBER).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_BHYT))) {
				entity.setNBhyt(input.get(Parameter.N_BHYT).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_BUYER_RAKING))) {
				entity.setNBuyerRaking(input.get(Parameter.N_BUYER_RAKING).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_IS_BUYER_PARTNER_CONFIRMED))) {
				entity.setNIsBuyerPartnerConfirmed(input.get(Parameter.N_IS_BUYER_PARTNER_CONFIRMED).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_IS_DONE))) {
				entity.setNIsDone(input.get(Parameter.N_IS_DONE).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_IS_PAID))) {
				entity.setNIsPaid(input.get(Parameter.N_IS_PAID).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED))) {
				entity.setNIsSellerPartnerConfirmed(input.get(Parameter.N_IS_SELLER_PARTNER_CONFIRMED).getAsInt());
			}

			entity.setNOrderStatusId(1);

			if (!StringUtility.isEmpty(input.get(Parameter.N_TOTAL_AMOUNT))) {
				entity.setNTotalAmount(input.get(Parameter.N_TOTAL_AMOUNT).getAsInt());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_APPOINTMENT_TIME))) {
				entity.setSAppointmentTime(input.get(Parameter.S_APPOINTMENT_TIME).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_BUSINESS_SERVICE_ID))) {
				entity.setSBusinessServiceId(input.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_BUYER_PARTNER_CONFIRMEDBY))) {
				entity.setSBuyerPartnerConfirmedBy(input.get(Parameter.S_BUYER_PARTNER_CONFIRMEDBY).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_BUYER_PARTNER_ID))) {
				entity.setSBuyerPartnerId(input.get(Parameter.S_BUYER_PARTNER_ID).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_BUYER_USER_ID))) {
				entity.setSBuyerUserId(input.get(Parameter.S_BUYER_USER_ID).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_CURRENCY))) {
				entity.setSCurrency(input.get(Parameter.S_CURRENCY).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_GROUP_BUSINESS_CATE_ID))) {
				entity.setSGroupBusinessCateId(input.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_ORDER_SUMMARY))) {
				entity.setSOderSummary(input.get(Parameter.S_ORDER_SUMMARY).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_PATIENT_AGE))) {
				entity.setSPatientAge(input.get(Parameter.S_PATIENT_AGE).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_PATIENT_GENDER))) {
				entity.setSPatientGender(input.get(Parameter.S_PATIENT_GENDER).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_PATIENT_NAME))) {
				entity.setSPatientName(input.get(Parameter.S_PATIENT_NAME).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_SELLER_PARTNER_CONFIRMED_BY))) {
				entity.setSSellerPartnerConfirmedBy(input.get(Parameter.S_SELLER_PARTNER_CONFIRMED_BY).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_SELLER_PARTNER_ID))) {
				entity.setSSellerPartnerId(input.get(Parameter.S_SELLER_PARTNER_ID).getAsString());
			}
			if (!StringUtility.isEmpty(input.get(Parameter.S_SELLER_USER_ID))) {
				entity.setSSellerUserId(input.get(Parameter.S_SELLER_USER_ID).getAsString());
			}
			// save
			tbOrderRepository.save(entity);
			// TbOrderDetail
			if (input.get(Parameter.ORDER_DETAILS) != null && input.get(Parameter.ORDER_DETAILS).isJsonArray()) {
				for ( JsonElement element : input.get(Parameter.ORDER_DETAILS).getAsJsonArray()) {
					TbOrderDetail det = new TbOrderDetail();
					String OrderDetailId = StringUtility.HexSHA(StringUtility.Generating());
					det.setSOrderNumber(Orderid);
					det.setSOrderDetailId(OrderDetailId);
					
					JsonObject item = element.getAsJsonObject();
					if(!StringUtility.isEmpty(item.get(Parameter.ORDER_DETAILS_IS_PRODUCT)))
						det.setNIsProduct(item.get(Parameter.ORDER_DETAILS_IS_PRODUCT).getAsInt());
					if(!StringUtility.isEmpty(item.get(Parameter.ORDER_DETAILS_QTY)))
						det.setNQty(item.get(Parameter.ORDER_DETAILS_QTY).getAsBigDecimal());
					if(!StringUtility.isEmpty(item.get(Parameter.ORDER_DETAILS_SUBPRICE)))
						det.setNSubprice(item.get(Parameter.ORDER_DETAILS_SUBPRICE).getAsBigDecimal());
					if(!StringUtility.isEmpty(item.get(Parameter.ORDER_DETAILS_TOTAL)))
						det.setNTotal(item.get(Parameter.ORDER_DETAILS_TOTAL).getAsBigDecimal());
					if(!StringUtility.isEmpty(item.get(Parameter.S_MYDEAL_ID)))
						det.setSMydealId(item.get(Parameter.S_MYDEAL_ID).getAsString());
					if(!StringUtility.isEmpty(item.get(Parameter.S_PRODUCT_ID)))
						det.setSProductId(item.get(Parameter.S_PRODUCT_ID).getAsString());
					tbOrderDetailRepository.save(det);
				}
			}

			return entity;
		} catch (Exception e) {
			// set rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new TbOrder();
		}
	}

	@SuppressWarnings("all")
	@Override
	public List<MydealInfo> getDealByFriends(String userId, String languageCode, String contentSearch, int pageNumber,
			int pageSize, String partnerId, String groubBusId, String contenElement) {

		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_business.getMydealByFriendsTEST", MydealInfo.class)
				.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("contentSearch", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pageNumber", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pageSize", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("groubBusinessId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("contenElement", String.class, ParameterMode.IN)
				.setParameter("userId", userId).setParameter("languageCode", languageCode)
				.setParameter("contentSearch", contentSearch).setParameter("pageNumber", pageNumber)
				.setParameter("pageSize", pageSize).setParameter("partnerId", partnerId)
				.setParameter("groubBusinessId", groubBusId).setParameter("contenElement", contenElement);
		List<MydealInfo> lstmydeal = q.getResultList();
		return lstmydeal;
	}

	@Override
	public Object sendEmail(Object object) {
		return notifyFeignClient.sendEmail(object);
	}

	public MedicalEmailDataModel getMedicalEmailData(String orderId, String languageCode) {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("mdl_business.medicalemaildata", MedicalEmailDataModel.class)
				.registerStoredProcedureParameter("orderId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.setParameter("orderId", orderId).setParameter("languageCode", languageCode);
		MedicalEmailDataModel data = (MedicalEmailDataModel) query.getSingleResult();
		return data;
	}

	@SuppressWarnings("all")
	public void testSendEmail(String orderId, String languageCode) {
		MedicalEmailDataModel data = getMedicalEmailData(orderId, languageCode);
		Map<Object, Object> object = new HashMap<Object, Object>();
		object.put(Parameter.USERNAME, data.getUsername());
		object.put(Parameter.TEMPLATE_NAME, Parameter.MEDICAL_ORDER);
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("name", data.getP_name());
		params.put("age", data.getP_age());
		params.put("gender", data.getGender());
		params.put("hospital", data.getHospital());
		params.put("department", data.getDepartment());
		try {
			params.put("date", data.getD_appointment_date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		params.put("time", data.getS_appointment_time());
		object.put(Parameter.PARAMS, params);
		Object o = sendEmail(object);
	}

	@Override
	public Integer likeDeal(String dealId, String userId) {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_business.likedeal")
				.registerStoredProcedureParameter("dealId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("userIdLike", String.class, ParameterMode.IN)
				.setParameter("dealId", dealId)
				.setParameter("userIdLike", userId);
		Integer result = (Integer) q.getSingleResult();
		return result;
	}

	@Override
	public String getImage(String tablename, String id, String imageId) {
		Gson gson = new Gson();
		String imageData = StringUtils.EMPTY;
		if (Parameter.TABLE_MY_DEAL.equals(tablename.trim())) {
			TbMydeal image = tbMydealRepository.Mydealbyid(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			
			if("1".equals(imageId)) {
				imageData = jObject.get("sDealImage1").getAsString();
			}else if ("2".equals(imageId)) {
				imageData = jObject.get("sDealImage2").getAsString();
			}else {
				imageData = jObject.get("sDealImage3").getAsString();
			}
			
		} else if (Parameter.TABLE_MY_PRODUCT.equals(tablename.trim())) {
			TbMyproduct image = tbMyproductRepository.getProbyid(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			
			if("1".equals(imageId)) {
				imageData = jObject.get("sProductImageMain").getAsString();
			}else if ("2".equals(imageId)) {
				imageData = jObject.get("sProductImage2").getAsString();
			}else if ("3".equals(imageId)) {
				imageData = jObject.get("sProductImage3").getAsString();
			}else {
				imageData = jObject.get("sProductImage4").getAsString();
			}
		} else if (Parameter.TABLE_MY_PRODUCT_CATE.equals(tablename.trim())) {
			TbProductCate image = tbProductCateRepository.getProCatebyId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("sProductCateIcon").getAsString();
		}
		return imageData;
	}

	@Override
	public TbOrder getByOrderNumber(String orderId) {
		return tbOrderRepository.Orderbyid(orderId);
	}
}
