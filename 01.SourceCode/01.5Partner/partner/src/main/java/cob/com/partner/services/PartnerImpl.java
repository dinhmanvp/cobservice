package cob.com.partner.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.partner.dao.TbListPartnerRepository;
import cob.com.partner.dao.TbMybusinessWorkingtimeRepository;
import cob.com.partner.dao.TbPartnerAccountRepository;
import cob.com.partner.dao.TbPartnerBizcateRepository;
import cob.com.partner.dao.TbPartnerBusinessServiceRepository;
import cob.com.partner.dao.TbPartnerContractRepository;
import cob.com.partner.dao.TbPartnerHolidayRepository;
import cob.com.partner.dao.TbPartnerRuleRepository;
import cob.com.partner.dao.TbPartnerWorkingtimeRepository;
import cob.com.partner.entities.TbListPartner;
import cob.com.partner.entities.TbMybusinessWorkingtime;
import cob.com.partner.entities.TbPartnerAccount;
import cob.com.partner.entities.TbPartnerBizcate;
import cob.com.partner.entities.TbPartnerBusinessService;
import cob.com.partner.entities.TbPartnerContract;
import cob.com.partner.entities.TbPartnerHoliday;
import cob.com.partner.entities.TbPartnerInfo;
import cob.com.partner.entities.TbPartnerName;
import cob.com.partner.entities.TbPartnerRule;
import cob.com.partner.entities.TbPartnerWorkingtime;
import cob.com.partner.utils.DateUtility;
import cob.com.partner.utils.StringUtility;
import cob.com.partner.ws.models.GroupbusinessCateInfo;
import cob.com.partner.ws.models.OrderModel;
import cob.com.partner.ws.models.PartnerAccountInfo;
import cob.com.partner.ws.models.PartnerBizcateInfo;
import cob.com.partner.ws.models.PartnerBusinessServiceInfo;
import cob.com.partner.ws.models.PartnerInfo;
import cob.com.partner.ws.models.PartnerInfoItem;
import cob.com.partner.ws.param.Parameter;;

@Component
public class PartnerImpl implements Partner {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TbListPartnerRepository tbListPartnerRepository;

	@Autowired
	private TbPartnerRuleRepository tbPartnerRuleRepository;

	@Autowired
	private TbPartnerBizcateRepository tbPartnerBizcateRepository;

	@Autowired
	private TbPartnerBusinessServiceRepository tbPartnerBusinessServiceRepository;

	@Autowired
	private TbPartnerAccountRepository tbPartnerAccountRepository;

	@Autowired
	private TbPartnerWorkingtimeRepository tbPartnerWorkingtimeRepository;

	@Autowired
	private TbMybusinessWorkingtimeRepository tbMybusinessWorkingtimeRepository;

	@Autowired
	private TbPartnerContractRepository tbPartnerContractRepository;

	@Autowired
	private TbPartnerHolidayRepository tbPartnerHolidayRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(PartnerImpl.class);


	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	@Override
	public boolean partnerCreate(JsonObject jsonInput) {
		try {

			Date date = new Date();
			long getid = date.getTime();
			String PartnerId = Long.toString(getid);

			TbListPartner pa = new TbListPartner();
			
			if(!StringUtility.isEmpty(jsonInput.get(Parameter.S_PHONE_CONTACT)))
				pa.setsPhoneContact(jsonInput.get(Parameter.S_PHONE_CONTACT).getAsString());
			
			pa.setSPartnerId(PartnerId);
			pa.setNIsApproved(0);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_EN)))
				pa.setSPartnerNameEn(jsonInput.get(Parameter.S_PARTNER_NAME_EN).getAsString());
			else
				pa.setSPartnerNameEn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_VN)))
				pa.setSPartnerNameVn(jsonInput.get(Parameter.S_PARTNER_NAME_VN).getAsString());
			else
				pa.setSPartnerNameVn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_CN)))
				pa.setSPartnerNameCn(jsonInput.get(Parameter.S_PARTNER_NAME_CN).getAsString());
			else
				pa.setSPartnerNameCn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_PHONE)))
				pa.setSBusinessPhone(jsonInput.get(Parameter.S_BUSINESS_PHONE).getAsString());
			else
				pa.setSBusinessPhone(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_EMAIL)))
				pa.setSBusinessEmail(jsonInput.get(Parameter.S_BUSINESS_EMAIL).getAsString());
			else
				pa.setSBusinessEmail(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_ADDRESS_NO)))
				pa.setSAddressNo(jsonInput.get(Parameter.S_ADDRESS_NO).getAsString());
			else
				pa.setSAddressNo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_STREET)))
				pa.setSStreet(jsonInput.get(Parameter.S_STREET).getAsString());
			else
				pa.setSStreet(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMPANY_LOGO)))
				pa.setSCompanyLogo(jsonInput.get(Parameter.S_COMPANY_LOGO).getAsString());
			else
				pa.setSCompanyLogo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TAXCODE)))
				pa.setSTaxcode(jsonInput.get(Parameter.S_TAXCODE).getAsString());
			else
				pa.setSTaxcode(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_OWNER_NAME)))
				pa.setSOwnerName(jsonInput.get(Parameter.S_OWNER_NAME).getAsString());
			else
				pa.setSOwnerName(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_OPENING_TIME)))
				pa.setSOpeningTime(jsonInput.get(Parameter.S_OPENING_TIME).getAsString());
			else
				pa.setSOpeningTime(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CLOSE_TIME)))
				pa.setSCloseTime(jsonInput.get(Parameter.S_CLOSE_TIME).getAsString());
			else
				pa.setSCloseTime(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COUNTRY_ID)))
				pa.setSCountryId(jsonInput.get(Parameter.S_COUNTRY_ID).getAsString());
			else
				pa.setSCountryId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CITY_ID)))
				pa.setSCityId(jsonInput.get(Parameter.S_CITY_ID).getAsString());
			else
				pa.setSCityId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_GROUP_BUSINESS_ID)))
				pa.setSGroupBusinessId(jsonInput.get(Parameter.S_GROUP_BUSINESS_ID).getAsString());
			else
				pa.setSGroupBusinessId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_TYPE_ID)))
				pa.setSBusinessTypeId(jsonInput.get(Parameter.S_BUSINESS_TYPE_ID).getAsString());
			else
				pa.setSBusinessTypeId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CREATE_BY)))
				pa.setSCreatedBy(jsonInput.get(Parameter.S_CREATE_BY).getAsString());
			else
				pa.setSCreatedBy(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_CN)))
				pa.setSPartnerDescCn(jsonInput.get(Parameter.S_PARTNER_DESC_CN).getAsString());
			else
				pa.setSPartnerDescCn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_VN)))
				pa.setSPartnerDescVn(jsonInput.get(Parameter.S_PARTNER_DESC_VN).getAsString());
			else
				pa.setSPartnerDescVn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_EN)))
				pa.setSPartnerDescEn(jsonInput.get(Parameter.S_PARTNER_DESC_EN).getAsString());
			else
				pa.setSPartnerDescEn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_FULL_ADDRESS)))
				pa.setSfullAddress(jsonInput.get(Parameter.S_FULL_ADDRESS).getAsString());
			else
				pa.setSfullAddress(null);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dtRegis;
			try {
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_DREGISTRATION_DATE))) {
					dtRegis = df.parse(jsonInput.get(Parameter.D_DREGISTRATION_DATE).getAsString());
					pa.setDRegistrationDate(dtRegis);
				} else {
					pa.setDRegistrationDate(null);
				}

			} catch (ParseException e) {
				pa.setDRegistrationDate(null);
			}
			
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMPANY_TYPE_ID)))
				pa.setsCompanyTypeId(jsonInput.get(Parameter.S_COMPANY_TYPE_ID).getAsString());
			
			// Start Insert table Partner_Account
			TbPartnerAccount paacc = new TbPartnerAccount();
			Date datepacc = new Date();
			long getpaccid = datepacc.getTime();
			String PartneraccountId = Long.toString(getpaccid);

			paacc.setSPartnerAccountId(PartneraccountId);
			paacc.setSPartnerId(PartnerId);
			paacc.setNIsDeleted(0);
			paacc.setNIsApproved(1);
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMMENT)))
				paacc.setSComment(jsonInput.get(Parameter.S_COMMENT).getAsString());
			else
				paacc.setSComment(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_USER_ID)))
				paacc.setSUserId(jsonInput.get(Parameter.S_USER_ID).getAsString());
			else
				paacc.setSUserId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_RULE_ID)))
				paacc.setSPartnerRuleId(jsonInput.get(Parameter.S_PARTNER_RULE_ID).getAsString());
			else
				paacc.setSPartnerRuleId(null);
			
			Date datepaac = new Date();
			paacc.setDJoinedDate(datepaac);

			// End Insert table Partner_Account

			JsonArray arrayBizcates = jsonInput.getAsJsonArray(Parameter.PARTNER_BIZCATES);
			JsonArray arrrBusinessServices = jsonInput.getAsJsonArray(Parameter.PARTNER_BUSINESS_SERVICES);

			// Insert vao bang TbPartnerBizcate
			if (arrayBizcates != null && arrayBizcates.size() > 0) {
				for (Integer i = 0; i < arrayBizcates.size(); i++) {
					JsonObject JsonBizcate = (JsonObject) arrayBizcates.get(i);
					TbPartnerBizcate PaBiz = new TbPartnerBizcate();
					if (!StringUtility.isEmpty(JsonBizcate.get(Parameter.S_GROUP_BUSINESS_CATE_ID))) {
						//Date datebiz = new Date();
						//long getidbiz = datebiz.getTime();
						String PartnerbizId = UUID.randomUUID().toString();//Long.toString(getidbiz);

						PaBiz.setSPartnerBizcateId(PartnerbizId);
						PaBiz.setSPartnerId(PartnerId);
						PaBiz.setSGroupBusinessCateId(
								JsonBizcate.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString());
						PaBiz.setNIsActivated(1);
						tbPartnerBizcateRepository.save(PaBiz);
					}
				}
			}

			// Insert vao bang TbPartnerBusinessService
			if (arrrBusinessServices != null && arrrBusinessServices.size() > 0) {
				for (Integer i = 0; i < arrrBusinessServices.size(); i++) {
					JsonObject JsonBusinessService = (JsonObject) arrrBusinessServices.get(i);
					TbPartnerBusinessService PaBusi = new TbPartnerBusinessService();
					if (!StringUtility.isEmpty(JsonBusinessService.get(Parameter.S_BUSINESS_ID))) {
						//Date datebusi = new Date();
						//long getidbusi = datebusi.getTime();
						String PartnerBusinessServiceId = UUID.randomUUID().toString();//Long.toString(getidbusi);

						PaBusi.setSPartnerBusinessServiceId(PartnerBusinessServiceId);
						PaBusi.setSPartnerId(PartnerId);
						PaBusi.setSBusinessServiceId(JsonBusinessService.get(Parameter.S_BUSINESS_ID).getAsString());
						PaBusi.setNIsActivated(1);
						tbPartnerBusinessServiceRepository.save(PaBusi);
					}
				}
			}

			tbListPartnerRepository.save(pa);
			tbPartnerAccountRepository.save(paacc);

			return true;
		} catch (Exception e) {
			// set rollback
			log.error(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	@Override
	public boolean partnerUpdate(JsonObject jsonInput) {
		try {
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID))) {

				String PartnerId = jsonInput.get(Parameter.S_PARTNER_ID).getAsString();

				TbListPartner pa = new TbListPartner();
				List<TbPartnerBusinessService> lstPaBusi = new ArrayList<TbPartnerBusinessService>();
				List<TbPartnerBizcate> lstPaBiz = new ArrayList<TbPartnerBizcate>();

				pa = tbListPartnerRepository.findBypartnerid(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
				lstPaBusi = tbPartnerBusinessServiceRepository
						.findBusiid(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
				lstPaBiz = tbPartnerBizcateRepository.findbizid(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_EN)))
					pa.setSPartnerNameEn(jsonInput.get(Parameter.S_PARTNER_NAME_EN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_VN)))
					pa.setSPartnerNameVn(jsonInput.get(Parameter.S_PARTNER_NAME_VN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_NAME_CN)))
					pa.setSPartnerNameCn(jsonInput.get(Parameter.S_PARTNER_NAME_CN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_PHONE)))
					pa.setSBusinessPhone(jsonInput.get(Parameter.S_BUSINESS_PHONE).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_EMAIL)))
					pa.setSBusinessEmail(jsonInput.get(Parameter.S_BUSINESS_EMAIL).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_ADDRESS_NO)))
					pa.setSAddressNo(jsonInput.get(Parameter.S_ADDRESS_NO).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_STREET)))
					pa.setSStreet(jsonInput.get(Parameter.S_STREET).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMPANY_LOGO)))
					pa.setSCompanyLogo(jsonInput.get(Parameter.S_COMPANY_LOGO).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TAXCODE)))
					pa.setSTaxcode(jsonInput.get(Parameter.S_TAXCODE).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_OWNER_NAME)))
					pa.setSOwnerName(jsonInput.get(Parameter.S_OWNER_NAME).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_OPENING_TIME)))
					pa.setSOpeningTime(jsonInput.get(Parameter.S_OPENING_TIME).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CLOSE_TIME)))
					pa.setSCloseTime(jsonInput.get(Parameter.S_CLOSE_TIME).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COUNTRY_ID)))
					pa.setSCountryId(jsonInput.get(Parameter.S_COUNTRY_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CITY_ID)))
					pa.setSCityId(jsonInput.get(Parameter.S_CITY_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_GROUP_BUSINESS_ID)))
					pa.setSGroupBusinessId(jsonInput.get(Parameter.S_GROUP_BUSINESS_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_TYPE_ID)))
					pa.setSBusinessTypeId(jsonInput.get(Parameter.S_BUSINESS_TYPE_ID).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CREATE_BY)))
					pa.setSCreatedBy(jsonInput.get(Parameter.S_CREATE_BY).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_CN)))
					pa.setSPartnerDescCn(jsonInput.get(Parameter.S_PARTNER_DESC_CN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_VN)))
					pa.setSPartnerDescVn(jsonInput.get(Parameter.S_PARTNER_DESC_VN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_DESC_EN)))
					pa.setSPartnerDescEn(jsonInput.get(Parameter.S_PARTNER_DESC_EN).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_FULL_ADDRESS)))
					pa.setSfullAddress(jsonInput.get(Parameter.S_FULL_ADDRESS).getAsString());

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_IS_APPROVED)))
					pa.setNIsApproved(jsonInput.get(Parameter.N_IS_APPROVED).getAsInt());
				
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMPANY_TYPE_ID)))
					pa.setsCompanyTypeId(jsonInput.get(Parameter.S_COMPANY_TYPE_ID).getAsString());
				
				if(!StringUtility.isEmpty(jsonInput.get(Parameter.S_PHONE_CONTACT)))
					pa.setsPhoneContact(jsonInput.get(Parameter.S_PHONE_CONTACT).getAsString());

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date dtRegis;
				try {
					if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_DREGISTRATION_DATE))) {
						dtRegis = df.parse(jsonInput.get(Parameter.D_DREGISTRATION_DATE).getAsString());
						pa.setDRegistrationDate(dtRegis);
					}
				} catch (ParseException e) {
				}

				JsonArray arrayBizcates = jsonInput.getAsJsonArray(Parameter.PARTNER_BIZCATES);
				JsonArray arrrBusinessServices = jsonInput.getAsJsonArray(Parameter.PARTNER_BUSINESS_SERVICES);

				// Insert vao bang TbPartnerBizcate
				if (arrayBizcates.size() > 0) {
					// updatePartnerBizcate(PartnerId);
					for (Integer y = 0; y < lstPaBiz.size(); y++) {
						lstPaBiz.get(y).setNIsActivated(1);
						tbPartnerBizcateRepository.saveAndFlush(lstPaBiz.get(y));
					}

					for (Integer i = 0; i < arrayBizcates.size(); i++) {
						JsonObject JsonBizcate = (JsonObject) arrayBizcates.get(i);
						TbPartnerBizcate PaBiz = new TbPartnerBizcate();

						if (!StringUtility.isEmpty(JsonBizcate.get(Parameter.S_GROUP_BUSINESS_CATE_ID))) {
							Integer a = 0;
							Integer b = -1;
							for (Integer j = 0; j < lstPaBiz.size(); j++) {

								// kiểm tra xem giá trị update đã có chưa
								String BuCaid = (String) lstPaBiz.get(j).getSGroupBusinessCateId();
								String GrBucaid = (String) JsonBizcate.get(Parameter.S_GROUP_BUSINESS_CATE_ID)
										.getAsString().toString();
								if (!GrBucaid.equals(BuCaid)) {
									a++;
								} else {
									lstPaBiz.get(j).setNIsActivated(0);
									b = j;
								}
							}
							if (b != -1) {
								// update isactive cuar groupbusenesscateid
								tbPartnerBizcateRepository.saveAndFlush(lstPaBiz.get(b));
							}
							// Kiểm tra xem giá trị update chưa tồn tại nên sẽ insert vào

							if (a == lstPaBiz.size()) {
								// Insert
								Date datebiz = new Date();
								long getidbiz = datebiz.getTime();
								String PartnerbizId = Long.toString(getidbiz);

								PaBiz.setSPartnerBizcateId(PartnerbizId);
								PaBiz.setSPartnerId(PartnerId);
								PaBiz.setSGroupBusinessCateId(
										JsonBizcate.get(Parameter.S_GROUP_BUSINESS_CATE_ID).getAsString());
								PaBiz.setNIsActivated(0);
								tbPartnerBizcateRepository.save(PaBiz);
							}

						}
					}
				}

				//
				// Insert vao bang TbPartnerBusinessService
				if (arrrBusinessServices.size() > 0) {
					updatePartnerBusinessService(PartnerId);
					for (Integer z = 0; z < lstPaBusi.size(); z++) {
						lstPaBusi.get(z).setNIsActivated(1);
						tbPartnerBusinessServiceRepository.saveAndFlush(lstPaBusi.get(z));
					}
					for (Integer i = 0; i < arrrBusinessServices.size(); i++) {
						JsonObject JsonBusinessService = (JsonObject) arrrBusinessServices.get(i);
						TbPartnerBusinessService PaBusi = new TbPartnerBusinessService();
						if (!StringUtility.isEmpty(JsonBusinessService.get(Parameter.S_BUSINESS_ID))) {

							Integer c = 0;
							Integer d = -1;

							for (Integer j = 0; j < lstPaBusi.size(); j++) {

								// kiểm tra xem giá trị update đã có chưa
								if (lstPaBusi.get(j).getSBusinessServiceId()
										.equals(JsonBusinessService.get(Parameter.S_BUSINESS_ID).getAsString())) {
									lstPaBusi.get(j).setNIsActivated(0);
									d = j;
									// break;
								} else {
									c++;
								}
							}

							if (d != -1) {
								// update isactive cuar groupbusenesscateid
								tbPartnerBusinessServiceRepository.saveAndFlush(lstPaBusi.get(d));
							}
							if (c == lstPaBusi.size()) {
								Date datebusi = new Date();
								long getidbusi = datebusi.getTime();
								String PartnerBusinessServiceId = Long.toString(getidbusi);

								PaBusi.setSPartnerBusinessServiceId(PartnerBusinessServiceId);
								PaBusi.setSPartnerId(PartnerId);
								PaBusi.setSBusinessServiceId(
										JsonBusinessService.get(Parameter.S_BUSINESS_ID).getAsString());
								PaBusi.setNIsActivated(0);
								tbPartnerBusinessServiceRepository.save(PaBusi);
							}
						}
					}
				}

				tbListPartnerRepository.saveAndFlush(pa);
				return true;
			}
			return false;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@SuppressWarnings("all")
	@Override
	public boolean partnerAccountCreate(JsonObject jsonInput) {
		try {
			Date date = new Date();
			long getid = date.getTime();
			TbPartnerAccount pa = new TbPartnerAccount();

			pa.setSPartnerAccountId(Long.toString(getid));
			pa.setNIsApproved(1);
			pa.setNIsDeleted(0);
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_USER_ID)))
				pa.setSUserId(jsonInput.get(Parameter.S_USER_ID).getAsString());
			else
				pa.setSUserId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMMENT)))
				pa.setSComment(jsonInput.get(Parameter.S_COMMENT).getAsString());
			else
				pa.setSComment(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_STAFF_NUMBER)))
				pa.setSStaffNumber(jsonInput.get(Parameter.S_STAFF_NUMBER).getAsString());
			else
				pa.setSStaffNumber(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_RULE_ID)))
				pa.setSPartnerRuleId(jsonInput.get(Parameter.S_PARTNER_RULE_ID).getAsString());
			else
				pa.setSPartnerRuleId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
				pa.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
			else
				pa.setSPartnerId(null);

			Date dateasdd = new Date();
			pa.setDJoinedDate(dateasdd);

			// JsonObject arr = new JsonObject(jsonInput);
			for (int i = 0; i < jsonInput.size(); i++) { // Walk through the Array.
				// JsonObject obj = jsonInput .getAsJsonObject(i) .getJSONObject(i);
				JsonArray arr2 = jsonInput.getAsJsonArray("partnerBizcates");
				// Do whatever.
			}

			tbPartnerAccountRepository.save(pa);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Lấy danh sách trong bảng TbPartnerRule
	@Override
	public List<TbPartnerRule> getPartnerRules() {
		List<TbPartnerRule> lstPartnerRule = new ArrayList<TbPartnerRule>();
		lstPartnerRule = tbPartnerRuleRepository.findAll();
		return lstPartnerRule;
	}

	// Hàm lấy về danh sách trong bảng TbPartnerBizcate theo partnerID
	@SuppressWarnings("all")
	@Override
	public List<GroupbusinessCateInfo> getPartnerBizcates(String partnerId) {
		List<GroupbusinessCateInfo> lstPartnerBizcate = new ArrayList<GroupbusinessCateInfo>();

		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.getpartnerbizcates", GroupbusinessCateInfo.class)
				.registerStoredProcedureParameter("partnerid", String.class, ParameterMode.IN)
				.setParameter("partnerid", partnerId);
		lstPartnerBizcate = q.getResultList();
		return lstPartnerBizcate;
	}

	// Hàm lấy về danh sách trong bảng TbPartnerBusinessService theo partnerID
	@Override
	public List<TbPartnerBusinessService> getPartnerBusinessServices(String partnerId) {
		List<TbPartnerBusinessService> lstPartnerBusinessService = new ArrayList<TbPartnerBusinessService>();
		lstPartnerBusinessService = partnerBizServiceByPartnerId(partnerId);
//		lstPartnerBusinessService = tbPartnerBusinessServiceRepository.findBusiid(partnerId);
		return lstPartnerBusinessService;
	}

	@SuppressWarnings("all")
	@Override
	public List<PartnerAccountInfo> getPartnerAccounts(JsonObject input) {
		
		String partnerId = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(input.get(Parameter.S_PARTNER_ID)))
			partnerId = input.get(Parameter.S_PARTNER_ID).getAsString();
		String languageCode = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(input.get(Parameter.LANGUAGE_CODE)))
			languageCode = input.get(Parameter.LANGUAGE_CODE).getAsString();
		String appointmentDate = StringUtils.EMPTY;
		if(!StringUtility.isEmpty(input.get("dappointmentDate")))
			appointmentDate = input.get("dappointmentDate").getAsString();
		Integer dayOfWeek = 0;
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date appointDate = dt.parse(appointmentDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(appointDate);
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		} catch (ParseException e) {			
			e.printStackTrace();
		} 
		List<PartnerAccountInfo> lsttbPartnerAccount = new ArrayList<PartnerAccountInfo>();
		if(dayOfWeek != 0 && !StringUtils.EMPTY.equals(appointmentDate) && !StringUtils.EMPTY.equals(partnerId)) {
			StoredProcedureQuery q = entityManager
					.createStoredProcedureQuery("mdl_partner.getpartneraccounts", PartnerAccountInfo.class)
					.registerStoredProcedureParameter("partnerid", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("appointmentDate", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("p_dayofWeek", Integer.class, ParameterMode.IN)
					.setParameter("partnerid", partnerId)
					.setParameter("languageCode", languageCode)
					.setParameter("appointmentDate", appointmentDate)
					.setParameter("p_dayofWeek", dayOfWeek);
			lsttbPartnerAccount = q.getResultList();
		}		
		return lsttbPartnerAccount;
	}

	// Hàm xóa một dòng trong bảng TbPartnerAccount theo partnerAccountId
	@Override
	public boolean partnerAccountRemove(String partnerAccountId) {
		try {
			// StoredProcedureQuery q = entityManager
			// .createStoredProcedureQuery("mdl_partner.removepartneraccountbyid")
			// .registerStoredProcedureParameter("partneraccountid", String.class,
			// ParameterMode.IN)
			// .setParameter("partneraccountid",partnerAccountId);

			// tbPartnerAccountRepository.deleteById(partnerAccountId);
			TbPartnerAccount partneraccount = tbPartnerAccountRepository.getPartnerAccount(partnerAccountId);
			if (partneraccount != null) {
				partneraccount.setNIsDeleted(1);
				tbPartnerAccountRepository.saveAndFlush(partneraccount);
				return true;
			}
			return false;
		} catch (Exception ex) {
			return false;
		}
	}

	// Hàm cập nhật một dòng trong bảng TbPartnerAccount
	@Override
	public boolean partnerAccountUpdate(JsonObject jsonInput) {

		try {
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ACCOUNT_ID))) {
				TbPartnerAccount pa = tbPartnerAccountRepository
						.getPartnerAccount(jsonInput.get(Parameter.S_PARTNER_ACCOUNT_ID).getAsString());
				if (pa != null) {
					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_USER_ID)))
						pa.setSUserId(jsonInput.get(Parameter.S_USER_ID).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_IS_APPROVED)))
						pa.setNIsApproved(jsonInput.get(Parameter.N_IS_APPROVED).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_IS_DELETED)))
						pa.setNIsDeleted(jsonInput.get(Parameter.N_IS_DELETED).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COMMENT)))
						pa.setSComment(jsonInput.get(Parameter.S_COMMENT).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_STAFF_NUMBER)))
						pa.setSStaffNumber(jsonInput.get(Parameter.S_STAFF_NUMBER).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_RULE_ID)))
						pa.setSPartnerRuleId(jsonInput.get(Parameter.S_PARTNER_RULE_ID).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
						pa.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());

					DateFormat dfpa = new SimpleDateFormat("dd/MM/yyyy");
					Date JoinedDate;
					try {

						if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_JOINED_DATE))) {
							JoinedDate = dfpa.parse(jsonInput.get(Parameter.D_JOINED_DATE).getAsString());
							pa.setDJoinedDate(JoinedDate);
						}
					} catch (ParseException e) {
					}
					tbPartnerAccountRepository.save(pa);

					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Hàm lấy danh sách PartnerInfo theo userid
	@SuppressWarnings("unchecked")
	@Override
	public List<TbPartnerInfo> getPartners(String UserID, String GroupBusinessId, String BusinessServiceId,
			String GroupBusinessTypeId, Integer isApprove) {
		List<PartnerInfoItem> result = new ArrayList<PartnerInfoItem>();

		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.getpartnerbyuseridgroupid", PartnerInfoItem.class)
				.registerStoredProcedureParameter("userid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("groupbusinessid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("businessserviceid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("businesstypeid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("isapprove", Integer.class, ParameterMode.IN)

				.setParameter("userid", UserID).setParameter("groupbusinessid", GroupBusinessId)
				.setParameter("businessserviceid", BusinessServiceId)
				.setParameter("businesstypeid", GroupBusinessTypeId).setParameter("isapprove", isApprove);

		List<TbPartnerInfo> lstpainfo = new ArrayList<TbPartnerInfo>();
		result = q.getResultList();

		if (result != null && result.size() > 0) {
			for (Integer i = 0; i < result.size(); i++) {
				TbPartnerInfo painfo = new TbPartnerInfo();
				painfo.ListPartner = result.get(i);
				lstpainfo.add(painfo);
			}
		}

		if (lstpainfo.size() > 0) {
			for (Integer i = 0; i < lstpainfo.size(); i++) {
				String partnerid = lstpainfo.get(i).ListPartner.getSPartnerId();
				lstpainfo.get(i).PartnerBizcate = partnerBizcatesByPartnerId(partnerid);
				lstpainfo.get(i).PartnerBusinessService = partnerBizServiceByPartnerId(partnerid);
			}
		}

		return lstpainfo;
	}
	
	@SuppressWarnings("unchecked")
	List<TbPartnerBizcate> partnerBizcatesByPartnerId(String partnerId){
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("mdl_partner.getgroupbizcateinfobypartner", TbPartnerBizcate.class)
				.registerStoredProcedureParameter("partnerid", String.class, ParameterMode.IN)
				.setParameter("partnerid", partnerId);
		List<TbPartnerBizcate> lst = query.getResultList();
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	List<TbPartnerBusinessService> partnerBizServiceByPartnerId(String partnerId){
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("mdl_partner.getbusinessserviceinfobypartner", TbPartnerBusinessService.class)
				.registerStoredProcedureParameter("partnerid", String.class, ParameterMode.IN)
				.setParameter("partnerid", partnerId);
		List<TbPartnerBusinessService> lst = query.getResultList();
		return lst;
	}
	
	@SuppressWarnings("all")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	@Override
	public List<PartnerInfo> getPartnersForWorking(JsonObject jsonInput) {
		//List<PartnerInfo> result = new ArrayList<PartnerInfo>();
		Integer type = 0;
		String UserID = jsonInput.get(Parameter.S_USER_ID).getAsString();		
		String languageCode = StringUtils.EMPTY;
		String fromDate = StringUtils.EMPTY;
		String toDate = StringUtils.EMPTY;
		String INNERJOIN = "mdl_partner.getpartnerforworking";
		String LEFTOUTERJOIN = "mdl_partner.getpartnerforworkingleftouter";
		String query = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(jsonInput.get("type") != null) {
			type =  jsonInput.get("type").getAsInt();
		}
		
		if(!StringUtility.isEmpty(jsonInput.get(Parameter.LANGUAGE_CODE)))
			languageCode = StringUtils.upperCase(jsonInput.get(Parameter.LANGUAGE_CODE).getAsString());
		if(!StringUtility.isEmpty(jsonInput.get("fromDate")))
			fromDate = StringUtils.upperCase(jsonInput.get("fromDate").getAsString());
		
		if(!StringUtility.isEmpty(jsonInput.get("toDate")))
			toDate = StringUtils.upperCase(jsonInput.get("toDate").getAsString());
			
		//List<PartnerInfoForWorking> lstpainfo = new ArrayList<PartnerInfoForWorking>();
		List<PartnerInfo> partners = new ArrayList<PartnerInfo>();
		if(type == 1) {
			query = LEFTOUTERJOIN;
		}
		else {
			query = INNERJOIN;
		}
		StoredProcedureQuery q1 = entityManager
				.createStoredProcedureQuery(query, PartnerInfo.class)
				.registerStoredProcedureParameter("userid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languagecode", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("fromDate", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("toDate", String.class, ParameterMode.IN)
				.setParameter("userid", UserID)
				.setParameter("languagecode", languageCode)
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
		partners = q1.getResultList();		
		for (PartnerInfo partnerInfo : partners) {
			List<PartnerBusinessServiceInfo> pbs = new ArrayList<PartnerBusinessServiceInfo>();
			StoredProcedureQuery q2 =  entityManager
					.createStoredProcedureQuery("mdl_partner.getbizserviceforworking", PartnerBusinessServiceInfo.class)
					.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("languagecode", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("fromDate", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("toDate", String.class, ParameterMode.IN)
					.setParameter("partnerId", partnerInfo.getSpartnerid())
					.setParameter("languagecode", languageCode)
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
			q2.execute();
			pbs = q2.getResultList();
			partnerInfo.setPartnerBusinessServiceInfos(pbs);
			for (PartnerBusinessServiceInfo item : pbs) {
				List<PartnerBizcateInfo> pbc = new ArrayList<PartnerBizcateInfo>();
				StoredProcedureQuery q3 =  entityManager
						.createStoredProcedureQuery("mdl_partner.getbusinesscate", PartnerBizcateInfo.class)
						.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("buzserviceId", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("languagecode", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDate", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDate", String.class, ParameterMode.IN)
						.setParameter("partnerId", partnerInfo.getSpartnerid())
						.setParameter("buzserviceId", item.getsBusinessServiceId())
						.setParameter("languagecode", languageCode)
						.setParameter("fromDate", fromDate).setParameter("toDate", toDate);
				q3.execute();
				pbc = q3.getResultList();
				item.setPartnerBizcateInfos(pbc);
				entityManager.clear();
			}
			entityManager.clear();
		}
		return partners;
	}

	@SuppressWarnings("all")
	private void updatePartnerBizcate(String PartnerID) {
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_partner.updatepartnerbizcateisactive")
				.registerStoredProcedureParameter("PartnerID", String.class, ParameterMode.IN)
				.setParameter("PartnerID", PartnerID);
	}

	@SuppressWarnings("all")
	private void updatePartnerBusinessService(String PartnerID) {
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.updatepartnerbusiessserviceisactive")
				.registerStoredProcedureParameter("PartnerID", String.class, ParameterMode.IN)
				.setParameter("PartnerID", PartnerID);
	}

	@Override
	public boolean registerPartnerWorkingtime(JsonObject jsonInput) {
		TbPartnerWorkingtime PartnerWorkingTime = new TbPartnerWorkingtime();
		TbMybusinessWorkingtime MybusinessWorkingtime = new TbMybusinessWorkingtime();

		try {
			Date datetime = new Date();
			long getid = datetime.getTime();
			String PartnerWorkingID = Long.toString(getid);

			// INSSERT VAO TABLE TbPartnerWorkingtime
			PartnerWorkingTime.setSPartnerWorkingId(PartnerWorkingID);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
				PartnerWorkingTime.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
			else
				PartnerWorkingTime.setSPartnerId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_DURATION_FOR_SESSION)))
				PartnerWorkingTime.setNDurationForSession(jsonInput.get(Parameter.N_DURATION_FOR_SESSION).getAsInt());
			else
				PartnerWorkingTime.setNDurationForSession(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_COUNT_PER_DAY)))
				PartnerWorkingTime.setNCountPerDay(jsonInput.get(Parameter.N_COUNT_PER_DAY).getAsInt());
			else
				PartnerWorkingTime.setNCountPerDay(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_MO_FROM)))
				PartnerWorkingTime.setSMoFrom(jsonInput.get(Parameter.S_MO_FROM).getAsString());
			else
				PartnerWorkingTime.setSMoFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_MO_TO)))
				PartnerWorkingTime.setSMoTo(jsonInput.get(Parameter.S_MO_TO).getAsString());
			else
				PartnerWorkingTime.setSMoTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_MO_IS_OFF)))
				PartnerWorkingTime.setSMoIsOff(jsonInput.get(Parameter.S_MO_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSMoIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TU_FROM)))
				PartnerWorkingTime.setSTuFrom(jsonInput.get(Parameter.S_TU_FROM).getAsString());
			else
				PartnerWorkingTime.setSTuFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TU_TO)))
				PartnerWorkingTime.setSTuTo(jsonInput.get(Parameter.S_TU_TO).getAsString());
			else
				PartnerWorkingTime.setSTuTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TU_IS_OFF)))
				PartnerWorkingTime.setSTuIsOff(jsonInput.get(Parameter.S_TU_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSTuIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_WE_FROM)))
				PartnerWorkingTime.setSWeFrom(jsonInput.get(Parameter.S_WE_FROM).getAsString());
			else
				PartnerWorkingTime.setSWeFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_WE_TO)))
				PartnerWorkingTime.setSWeTo(jsonInput.get(Parameter.S_WE_TO).getAsString());
			else
				PartnerWorkingTime.setSWeTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_WE_IS_OFF)))
				PartnerWorkingTime.setSWeIsOff(jsonInput.get(Parameter.S_WE_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSWeIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TH_FROM)))
				PartnerWorkingTime.setSThFrom(jsonInput.get(Parameter.S_TH_FROM).getAsString());
			else
				PartnerWorkingTime.setSThFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TH_TO)))
				PartnerWorkingTime.setSThTo(jsonInput.get(Parameter.S_TH_TO).getAsString());
			else
				PartnerWorkingTime.setSThTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_TH_IS_OFF)))
				PartnerWorkingTime.setSThIsOff(jsonInput.get(Parameter.S_TH_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSThIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_FR_FROM)))
				PartnerWorkingTime.setSFrFrom(jsonInput.get(Parameter.S_FR_FROM).getAsString());
			else
				PartnerWorkingTime.setSFrFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_FR_TO)))
				PartnerWorkingTime.setSFrTo(jsonInput.get(Parameter.S_FR_TO).getAsString());
			else
				PartnerWorkingTime.setSFrTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_FR_IS_OFF)))
				PartnerWorkingTime.setSFrIsOff(jsonInput.get(Parameter.S_FR_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSFrIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SA_FROM)))
				PartnerWorkingTime.setSSaFrom(jsonInput.get(Parameter.S_SA_FROM).getAsString());
			else
				PartnerWorkingTime.setSSaFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SA_TO)))
				PartnerWorkingTime.setSSaTo(jsonInput.get(Parameter.S_SA_TO).getAsString());
			else
				PartnerWorkingTime.setSSaTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.s_SA_IS_OFF)))
				PartnerWorkingTime.setSSaIsOff(jsonInput.get(Parameter.s_SA_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSSaIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SU_FROM)))
				PartnerWorkingTime.setSSuFrom(jsonInput.get(Parameter.S_SU_FROM).getAsString());
			else
				PartnerWorkingTime.setSSuFrom(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SU_TO)))
				PartnerWorkingTime.setSSuTo(jsonInput.get(Parameter.S_SU_TO).getAsString());
			else
				PartnerWorkingTime.setSSuTo(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SU_IS_OFF)))
				PartnerWorkingTime.setSSuIsOff(jsonInput.get(Parameter.S_SU_IS_OFF).getAsString());
			else
				PartnerWorkingTime.setSSuIsOff(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_SERVICE_ID)))
				PartnerWorkingTime.setSbusinessServiceId(jsonInput.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString());
			else
				PartnerWorkingTime.setSbusinessServiceId(null);

			// INSERT VAO BANG TbMybusinessWorkingtime
			Date datemy = new Date();
			long getmyid = datemy.getTime();
			String sMyBusinessSettingId = Long.toString(getmyid);
			MybusinessWorkingtime.setSMyBusinessSettingId(sMyBusinessSettingId);
			MybusinessWorkingtime.setSPartnerWorkingId(PartnerWorkingID);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_MY_BUSIESS_ID)))
				MybusinessWorkingtime.setSMyBusinessId(jsonInput.get(Parameter.S_MY_BUSIESS_ID).getAsString());
			else
				MybusinessWorkingtime.setSMyBusinessId(null);

			// Luu vao hai bang
			tbPartnerWorkingtimeRepository.save(PartnerWorkingTime);
			tbMybusinessWorkingtimeRepository.save(MybusinessWorkingtime);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<TbPartnerWorkingtime> getPartnerWorkingtime(String partnerId, String BusinessServiceId) {
		List<TbPartnerWorkingtime> lstPartnerWorkingtime = tbPartnerWorkingtimeRepository
				.getworkingbypartnerid(partnerId, BusinessServiceId);
		return lstPartnerWorkingtime;
	}

	@Override
	public boolean registerPartnerContract(JsonObject jsonInput) {
		TbPartnerContract PartnerContract = new TbPartnerContract();
		try {
			Date date = new Date();
			long getid = date.getTime();
			String PartnerContractID = Long.toString(getid);

			PartnerContract.setSContractNumber(PartnerContractID);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
				PartnerContract.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
			else
				PartnerContract.setSPartnerId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SALENAM_ID)))
				PartnerContract.setSSalemanId(jsonInput.get(Parameter.S_SALENAM_ID).getAsString());
			else
				PartnerContract.setSSalemanId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CONTRACT_POLICY_TYPE_ID)))
				PartnerContract
						.setSContractPolicyTypeId(jsonInput.get(Parameter.S_CONTRACT_POLICY_TYPE_ID).getAsString());
			else
				PartnerContract.setSContractPolicyTypeId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_CONTRACT_DURATION)))
				PartnerContract.setNContractDuration(jsonInput.get(Parameter.N_CONTRACT_DURATION).getAsInt());
			else
				PartnerContract.setNContractDuration(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_CONTRACT_UNIT)))
				PartnerContract.setSContractUnit(jsonInput.get(Parameter.S_CONTRACT_UNIT).getAsString());
			else
				PartnerContract.setSContractUnit(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_TOTAL)))
				PartnerContract.setNTotal(jsonInput.get(Parameter.N_TOTAL).getAsInt());
			else
				PartnerContract.setNTotal(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PERCETN_CHARGE_PER_TXN)))
				PartnerContract.setNPercentChargePerTxn(jsonInput.get(Parameter.N_PERCETN_CHARGE_PER_TXN).getAsInt());
			else
				PartnerContract.setNPercentChargePerTxn(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_BUSINESS_SERVICE_ID)))
				PartnerContract.setSBusinessServiceId(jsonInput.get(Parameter.S_BUSINESS_SERVICE_ID).getAsString());
			else
				PartnerContract.setSBusinessServiceId(null);

			DateFormat dfd = new SimpleDateFormat("dd/MM/yyyy");

			Date frDate;
			try {

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_FROM_DATE))) {
					frDate = dfd.parse(jsonInput.get(Parameter.D_FROM_DATE).getAsString());
					PartnerContract.setDFromDate(frDate);
				}
			} catch (ParseException e) {
				PartnerContract.setDFromDate(null);
			}

			Date toDate;
			try {

				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_TO_DATE))) {
					toDate = dfd.parse(jsonInput.get(Parameter.D_TO_DATE).getAsString());
					PartnerContract.setDToDate(toDate);
				}
			} catch (ParseException e) {
				PartnerContract.setDToDate(null);
			}

			tbPartnerContractRepository.save(PartnerContract);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean registerPartnerHoliday(JsonObject partnerHoliday) {
		try {
			TbPartnerHoliday Paho = new TbPartnerHoliday();
			Date dt = new Date();
			Long getid = dt.getTime();
			String PartHoId = Long.toString(getid);
			Paho.setSPartnerHolidayId(PartHoId);

			if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.S_HOLIDAY_DESC)))
				Paho.setSHolidayDesc(partnerHoliday.get(Parameter.S_HOLIDAY_DESC).getAsString());
			else
				Paho.setSHolidayDesc(null);

			if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.S_PARTNER_ID)))
				Paho.setSPartnerId(partnerHoliday.get(Parameter.S_PARTNER_ID).getAsString());
			else
				Paho.setSPartnerId(null);

			DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
			Date dtpartho;
			try {

				if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.D_HOLIDAY_DATE))) {
					dtpartho = da.parse(partnerHoliday.get(Parameter.D_HOLIDAY_DATE).getAsString());
					Paho.setDHolidayDate(dtpartho);
				} else
					Paho.setDHolidayDate(null);
			} catch (ParseException e) {
				Paho.setDHolidayDate(null);
			}

			tbPartnerHolidayRepository.save(Paho);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public boolean partnerHolidayUpdate(JsonObject partnerHoliday) {
		try {
			if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.S_PARTNER_HOLIDAY_ID))) {
				String PartHoId = partnerHoliday.get(Parameter.S_PARTNER_HOLIDAY_ID).getAsString();
				TbPartnerHoliday Paho = tbPartnerHolidayRepository.getPartHo(PartHoId);

				if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.S_HOLIDAY_DESC)))
					Paho.setSHolidayDesc(partnerHoliday.get(Parameter.S_HOLIDAY_DESC).getAsString());

				if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.S_PARTNER_ID)))
					Paho.setSPartnerId(partnerHoliday.get(Parameter.S_PARTNER_ID).getAsString());

				DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
				Date dtpartho;
				try {

					if (!StringUtility.isEmpty(partnerHoliday.get(Parameter.D_HOLIDAY_DATE))) {
						dtpartho = da.parse(partnerHoliday.get(Parameter.D_HOLIDAY_DATE).getAsString());
						Paho.setDHolidayDate(dtpartho);
					}
				} catch (ParseException e) {
				}

				tbPartnerHolidayRepository.saveAndFlush(Paho);
				return true;
			}
			return false;
		} catch (Exception ex) {
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbPartnerHoliday> getPartnerHoliday(String PartnerID, String monthOfYear) {
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.getpartnerholiday", TbPartnerHoliday.class)
				.registerStoredProcedureParameter("PartnerID", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("monthOfYear", String.class, ParameterMode.IN)
				.setParameter("PartnerID", PartnerID).setParameter("monthOfYear", monthOfYear);
		List<TbPartnerHoliday> LstPartHo = (List<TbPartnerHoliday>) q.getResultList();
		return LstPartHo;
	}

	@Override
	public List<TbPartnerName> getAllParterName() {

		// TypeQuery<TbPartnerName> = entityManager.createQuery("")
		return tbListPartnerRepository.getPartnerNames();

	}

	@SuppressWarnings("all")
	@Override
	public List<OrderModel> getOrderForChoosing(String partnerId, String businessServiceId, String buzCate, Date date,
			String languageCode) {
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.getorderforchoosing", OrderModel.class)
				.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("businessServiceId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("cateId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("apdate", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.setParameter("partnerId", partnerId)
				.setParameter("businessServiceId", businessServiceId)
				.setParameter("cateId", buzCate)
				.setParameter("apdate", date)
				.setParameter("languageCode", languageCode);
		List<OrderModel> orders = q.getResultList();
		return orders;
	}

	@SuppressWarnings("all")
	@Override
	public List<OrderModel> getOrderForSeller(String partnerId, String businessServiceId, String buzCate, Date date,
			String sellerUserId, String languageCode) {
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_partner.getorderbyselleruserId", OrderModel.class)
				.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("businessServiceId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("cateId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("apdate", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("sellerUserId", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.setParameter("partnerId", partnerId)
				.setParameter("businessServiceId", businessServiceId)
				.setParameter("cateId", buzCate)
				.setParameter("apdate", date)
				.setParameter("sellerUserId", sellerUserId)
				.setParameter("languageCode", languageCode);
		List<OrderModel> orders = q.getResultList();
		return orders;
	}

	@SuppressWarnings("all")
	@Override
	public List<OrderModel> handleOrder(String partnerId, String businesServiceId, String groupBuzCate, Date d,
			String sSellerUserId, String languageCode) {
			StoredProcedureQuery q = entityManager
					.createStoredProcedureQuery("mdl_partner.handleorder", OrderModel.class)
					.registerStoredProcedureParameter("partnerId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("businessServiceId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("cateId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("apdate", Date.class, ParameterMode.IN)
					.registerStoredProcedureParameter("sellerUserId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
					.setParameter("partnerId", partnerId)
					.setParameter("businessServiceId", businesServiceId)
					.setParameter("cateId", groupBuzCate)
					.setParameter("apdate", d)
					.setParameter("sellerUserId", sSellerUserId)
					.setParameter("languageCode", languageCode);
			List<OrderModel> orders = q.getResultList();
			return orders;
	}

	@Override
	public String getImage(String id) {
		TbListPartner image = tbListPartnerRepository.findBypartnerid(id);
		// parse
		Gson gson = new Gson();
		JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
		JsonObject jObject = jElement.getAsJsonObject();
		String imageData = jObject.get("sCompanyLogo").getAsString();
		return imageData;
	}
}
