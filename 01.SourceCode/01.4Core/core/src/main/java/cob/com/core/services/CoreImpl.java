package cob.com.core.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.core.dao.TbBusinessServiceRepository;
import cob.com.core.dao.TbBusinessTypeRepository;
import cob.com.core.dao.TbCityRepository;
import cob.com.core.dao.TbContractPolicyTypeDetailRepository;
import cob.com.core.dao.TbContractPolicyTypeRepository;
import cob.com.core.dao.TbCountryRepository;
import cob.com.core.dao.TbCurrencyRepository;
import cob.com.core.dao.TbDashboardRepository;
import cob.com.core.dao.TbGroupBusinessCateRepository;
import cob.com.core.dao.TbGroupBusinessRepository;
import cob.com.core.dao.TbPromotionHistoryRepository;
import cob.com.core.entities.TbBusinessService;
import cob.com.core.entities.TbBusinessType;
import cob.com.core.entities.TbCity;
import cob.com.core.entities.TbContractPolicyType;
import cob.com.core.entities.TbContractPolicyTypeDetail;
import cob.com.core.entities.TbCountry;
import cob.com.core.entities.TbCurrency;
import cob.com.core.entities.TbDashboard;
import cob.com.core.entities.TbGroupBusiness;
import cob.com.core.entities.TbGroupbusinessCate;
import cob.com.core.entities.TbGroupbusinessCateName;
import cob.com.core.entities.TbPromotionHistory;
import cob.com.core.utils.StringUtility;
import cob.com.core.ws.models.BusinessServiceInfo;
import cob.com.core.ws.models.CompanyTypeInfo;
import cob.com.core.ws.models.PromotionModel;
import cob.com.core.ws.param.Parameter;

@Component
public class CoreImpl implements Core {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private TbBusinessServiceRepository tbBusinessServiceRepository;
	@Autowired
	private TbDashboardRepository tbDashboardRepository;
	@Autowired
	private TbGroupBusinessRepository tbGroupBusinessRepository;
	@Autowired
	private TbBusinessTypeRepository tbBusinessTypeRepository;
	@Autowired
	private TbCityRepository tbCityRepository;
	@Autowired
	private TbCountryRepository tbCountryRepository;
	@Autowired
	private TbGroupBusinessCateRepository tbGroupBusinessCateRepository;
	@Autowired
	private TbContractPolicyTypeDetailRepository tbContractPolicyTypeDetailRepository;
	@Autowired
	private TbContractPolicyTypeRepository tbContractPolicyTypeRepository;
	@Autowired
	private TbCurrencyRepository tbCurrencyRepository;
	@Autowired
	private TbPromotionHistoryRepository tbPromotionHistoryRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<TbDashboard> getDashboardList(Integer typeId) {

		List<TbDashboard> tbDashboards = new ArrayList<TbDashboard>();
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("mdl_core.getdashboardbytype", TbDashboard.class)
				.registerStoredProcedureParameter("is_available", Integer.class, ParameterMode.IN)
				.setParameter("is_available", typeId);
		tbDashboards = (List<TbDashboard>) query.getResultList();
		return tbDashboards;
	}

	@SuppressWarnings("unchecked")
	public List<TbGroupBusiness> getGroupBusinessByDashboardId(String DbId) {
		List<TbGroupBusiness> groupBusinesses = new ArrayList<TbGroupBusiness>();
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_core.getgroupbusinessbydashboardid", TbGroupBusiness.class)
				.registerStoredProcedureParameter("dashboard_id", String.class, ParameterMode.IN)
				.setParameter("dashboard_id", DbId);
		groupBusinesses = (List<TbGroupBusiness>) q.getResultList();
		return groupBusinesses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessServiceInfo> getBusinessServicesByGroupBusinessId(String gbId) {
		List<BusinessServiceInfo> businessServices = new ArrayList<BusinessServiceInfo>();
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_core.getbusinessservice", BusinessServiceInfo.class)
				.registerStoredProcedureParameter("groupbusiness_id", String.class, ParameterMode.IN)
				.setParameter("groupbusiness_id", gbId);
		businessServices = (List<BusinessServiceInfo>) q.getResultList();
		return businessServices;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbContractPolicyTypeDetail> getContractPolicyTypeDetail(String tdId) {
		List<TbContractPolicyTypeDetail> detail = new ArrayList<TbContractPolicyTypeDetail>();
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_core.getcontractpolicytypedetail", TbContractPolicyTypeDetail.class)
				.registerStoredProcedureParameter("contractPolicyTypeId", String.class, ParameterMode.IN)
				.setParameter("contractPolicyTypeId", tdId);
		detail = (List<TbContractPolicyTypeDetail>) q.getResultList();
		return detail;
	}

	@Override
	public List<TbBusinessType> getBusinessType(String groupBusinessId) {
		List<TbBusinessType> businesstype = tbBusinessTypeRepository.findByGroupBusinessId(groupBusinessId);
		return businesstype;
	}

	@SuppressWarnings("all")
	@Override
	public List<TbCity> getCity(JsonObject input) {
		String countryId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.COUNTRY_ID)))
			countryId = input.get(Parameter.COUNTRY_ID).getAsString();

		String cityId = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.CITY_ID)))
			cityId = input.get(Parameter.CITY_ID).getAsString();

		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_core.getcityinfo", TbCity.class)
				.registerStoredProcedureParameter("countryid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("cityid", String.class, ParameterMode.IN)
				.setParameter("countryid", countryId).setParameter("cityid", cityId);

		List<TbCity> citys = (List<TbCity>) q.getResultList();
		return citys;
	}

	@Override
	public List<TbCountry> getCountry() {
		List<TbCountry> country = tbCountryRepository.findAll();
		return country;
	}

	@Override
	public List<TbContractPolicyType> getContractPolicyType() {
		List<TbContractPolicyType> policytype = tbContractPolicyTypeRepository.findAll();
		return policytype;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbGroupBusiness> getGroupBusinessisHome() {
		List<TbGroupBusiness> groupBusinesses = new ArrayList<TbGroupBusiness>();

		// Started Sinh sữa
		StoredProcedureQuery q = entityManager.createStoredProcedureQuery("mdl_core.getroupbusinessishome",
				TbGroupBusiness.class);
		groupBusinesses = (List<TbGroupBusiness>) q.getResultList();
		// End Sinh sữa

		// TypedQuery<TbGroupBusiness> q
		// =entityManager.createNamedQuery("TbGroupBusiness.findByIsHome",TbGroupBusiness.class);
		// groupBusinesses = (List<TbGroupBusiness>) q.getResultList();
		return groupBusinesses;
	}

	// @SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	public Object groupBusinessisHomeCretate(String typeId) {

		// TbGroupBusiness updateIsHome = tbGroupBusinessRepository.getOne(5);
		// List<TbGroupBusiness> groupBusinesses = new ArrayList<TbGroupBusiness>();
		Query q = entityManager.createNamedQuery("TbGroupBusiness.updateIsHome");
		q.setParameter(1, 1);
		q.setParameter(2, typeId);

		int result = q.executeUpdate();
		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Error.class,
			Exception.class, ParseException.class })
	public Object groupBusinessisHomeRemove(String typeId) {

		Query q = entityManager.createNamedQuery("TbGroupBusiness.updateIsHome");
		q.setParameter(1, 0);
		q.setParameter(2, typeId);

		int result = q.executeUpdate();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbGroupbusinessCate> getGroupbusinessCate(String btId) {
		List<TbGroupbusinessCate> cate = new ArrayList<TbGroupbusinessCate>();
		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_core.getGroupBusinessCate", TbGroupbusinessCate.class)
				.registerStoredProcedureParameter("groupBusinessId", String.class, ParameterMode.IN)
				.setParameter("groupBusinessId", btId);
		cate = (List<TbGroupbusinessCate>) q.getResultList();
		return cate;
	}

	@Override
	public Integer updateDashboard(TbDashboard entity) {
		try {
			TbDashboard inDatabase = tbDashboardRepository.findByDashBoardId(entity.getSDashboardId());
			if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setIItemImage(entity.getIItemImage());
				inDatabase.setNIsAvailable(entity.getNIsAvailable());
				inDatabase.setnOrder(entity.getnOrder());
				inDatabase.setSItemNameCn(entity.getSItemNameCn());
				inDatabase.setSItemNameEn(entity.getSItemNameEn());
				inDatabase.setSItemNameVn(entity.getSItemNameVn());
				TbDashboard dashboard = tbDashboardRepository.save(inDatabase);
				if (dashboard == null) {
					return 3;
				}
			}
		} catch (Exception e) {
			return 4;
		}

		return 1;
	}

	@Override
	public Integer addDashboard(TbDashboard entity) {
		try {
			TbDashboard inDatabase = tbDashboardRepository.findByDashBoardId(entity.getSDashboardId());
			if (inDatabase == null) {
				TbDashboard dashboard = tbDashboardRepository.save(entity);
				if (dashboard == null) {
					return 2;
				}
			} else {
				return 3;
			}
		} catch (Exception e) {
			return 4;
		}

		return 1;
	}

	public Integer groupBusinessUpdate(TbGroupBusiness entity) {
		try {
			TbGroupBusiness inDatabase = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			TbDashboard db = tbDashboardRepository.findByDashBoardId(entity.getSDashboardId());
			if (db == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setIGroupBusinessIcon(entity.getIGroupBusinessIcon());
				inDatabase.setNGroupBusinessAvailble(entity.getNGroupBusinessAvailble());
				inDatabase.setNGroupBusinessRating(entity.getNGroupBusinessRating());
				inDatabase.setNHomeDisplay(entity.getNHomeDisplay());
				inDatabase.setNOrder(entity.getNOrder());
				inDatabase.setSDashboardId(entity.getSDashboardId());
				inDatabase.setSGroupBusinessDescCn(entity.getSGroupBusinessDescCn());
				inDatabase.setSGroupBusinessDescEn(entity.getSGroupBusinessDescEn());
				inDatabase.setSGroupBusinessDescVn(entity.getSGroupBusinessDescVn());
				inDatabase.setSGroupBusinessDescShrinkCn(entity.getSGroupBusinessDescShrinkCn());
				inDatabase.setSGroupBusinessDescShrinkEn(entity.getSGroupBusinessDescShrinkEn());
				inDatabase.setSGroupBusinessDescShrinkVn(entity.getSGroupBusinessDescShrinkVn());
				inDatabase.setSGroupBusinessNameCn(entity.getSGroupBusinessNameCn());
				inDatabase.setSGroupBusinessNameEn(entity.getSGroupBusinessNameEn());
				inDatabase.setSGroupBusinessNameVn(entity.getSGroupBusinessNameVn());
				tbGroupBusinessRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}

		return 1;
	}

	public Integer addgroupBusiness(TbGroupBusiness entity) {
		try {
			TbGroupBusiness inDatabase = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			TbDashboard db = tbDashboardRepository.findByDashBoardId(entity.getSDashboardId());
			if (db == null) {
				return 4;
			} else if (inDatabase == null) {
				tbGroupBusinessRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateBusinessService(TbBusinessService entity) {
		try {
			TbBusinessService inDatabase = tbBusinessServiceRepository
					.findByBusinessServiceId(entity.getSBusinessServiceId());
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setIBusinessServiceIcon(entity.getIBusinessServiceIcon());
				inDatabase.setNBusinessServiceAvailable(entity.getNBusinessServiceAvailable());
				inDatabase.setNBusinessServiceRating(entity.getNBusinessServiceRating());
				inDatabase.setNOrder(entity.getNOrder());
				inDatabase.setSBusinessServiceDescCn(entity.getSBusinessServiceDescCn());
				inDatabase.setSBusinessServiceDescEn(entity.getSBusinessServiceDescEn());
				inDatabase.setSBusinessServiceDescVn(entity.getSBusinessServiceDescVn());
				inDatabase.setSBusinessServiceDescShrinkCn(entity.getSBusinessServiceDescShrinkCn());
				inDatabase.setSBusinessServiceDescShrinkVn(entity.getSBusinessServiceDescShrinkVn());
				inDatabase.setSBusinessServiceDescShrinkEn(entity.getSBusinessServiceDescShrinkEn());
				inDatabase.setSBusinessServiceNameCn(entity.getSBusinessServiceNameCn());
				inDatabase.setSBusinessServiceNameEn(entity.getSBusinessServiceNameEn());
				inDatabase.setSBusinessServiceNameVn(entity.getSBusinessServiceNameVn());
				inDatabase.setSGroupBusinessId(entity.getSGroupBusinessId());
				tbBusinessServiceRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addBusinessService(TbBusinessService entity) {
		try {
			TbBusinessService inDatabase = tbBusinessServiceRepository
					.findByBusinessServiceId(entity.getSBusinessServiceId());
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				tbBusinessServiceRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updategroupBusinessCate(TbGroupbusinessCate entity) {
		try {
			TbGroupbusinessCate inDatabase = tbGroupBusinessCateRepository
					.findByGroupBusinessCateId(entity.getSGroupBusinessCateId());
			// check references : group business id
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setIGroupBusinessCateIcon(entity.getIGroupBusinessCateIcon());
				inDatabase.setSGroupBusinessCateNameVn(entity.getSGroupBusinessCateNameVn());
				inDatabase.setSGroupBusinessNameCateEn(entity.getSGroupBusinessNameCateEn());
				inDatabase.setSGroupBusinessNameCateCn(entity.getSGroupBusinessNameCateCn());
				inDatabase.setSGroupBusinessDescriptionCn(entity.getSGroupBusinessDescriptionCn());
				inDatabase.setSGroupBusinessDescriptionVn(entity.getSGroupBusinessDescriptionVn());
				inDatabase.setSGroupBusinessDescriptionEn(entity.getSGroupBusinessDescriptionEn());
				inDatabase.setSGroupBusinessId(entity.getSGroupBusinessId());
				tbGroupBusinessCateRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addgroupBusinessCate(TbGroupbusinessCate entity) {
		try {
			TbGroupbusinessCate inDatabase = tbGroupBusinessCateRepository
					.findByGroupBusinessCateId(entity.getSGroupBusinessCateId());
			// check references : group business id
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getSGroupBusinessId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				tbGroupBusinessCateRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addBusinessType(TbBusinessType entity) {
		try {
			TbBusinessType inDatabase = tbBusinessTypeRepository.findByBusinessTypeId(entity.getSBusinessTypeId());
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getsBusinessGroupId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				tbBusinessTypeRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateBusinessType(TbBusinessType entity) {
		try {
			TbBusinessType inDatabase = tbBusinessTypeRepository.findByBusinessTypeId(entity.getSBusinessTypeId());
			TbGroupBusiness gb = tbGroupBusinessRepository.findByGroupBusinessId(entity.getsBusinessGroupId());
			if (gb == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setSBusinessTypeDescCn(entity.getSBusinessTypeDescCn());
				inDatabase.setSBusinessTypeDescEn(entity.getSBusinessTypeDescEn());
				inDatabase.setSBusinessTypeDescVn(entity.getSBusinessTypeDescVn());
				inDatabase.setsBusinessGroupId(entity.getsBusinessGroupId());
				inDatabase.setSBusinessTypeNameCn(entity.getSBusinessTypeNameCn());
				inDatabase.setSBusinessTypeNameEn(entity.getSBusinessTypeNameEn());
				inDatabase.setSBusinessTypeNameVn(entity.getSBusinessTypeNameEn());
				tbBusinessTypeRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addContractPolicyType(TbContractPolicyType entity) {
		try {
			TbContractPolicyType inDatabase = tbContractPolicyTypeRepository
					.findByContractPolicyTypeId(entity.getSContractPolicyTypeId());
			if (inDatabase == null) {
				tbContractPolicyTypeRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateContractPolicyType(TbContractPolicyType entity) {
		try {
			TbContractPolicyType inDatabase = tbContractPolicyTypeRepository
					.findByContractPolicyTypeId(entity.getSContractPolicyTypeId());
			if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setMFromAmount(entity.getMFromAmount());
				inDatabase.setMToAmount(entity.getMToAmount());
				inDatabase.setNIsChargeInTransaction(entity.getNIsChargeInTransaction());
				inDatabase.setNIsMontlyfeeApplied(entity.getNIsMontlyfeeApplied());
				inDatabase.setSPolicyTypeNameCn(entity.getSPolicyTypeNameCn());
				inDatabase.setSPolicyTypeNameEn(entity.getSPolicyTypeNameEn());
				inDatabase.setSPolicyTypeNameVn(entity.getSPolicyTypeNameVn());
				tbContractPolicyTypeRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addContractPolicyTypeDetail(TbContractPolicyTypeDetail entity) {
		try {
			TbContractPolicyTypeDetail inDatabase = tbContractPolicyTypeDetailRepository
					.findByContractPolicyTypeDetailId(entity.getSContractPolicyTypeDetailId());
			TbContractPolicyType cpt = tbContractPolicyTypeRepository
					.findByContractPolicyTypeId(entity.getSContractPolicyTypeId());
			if (cpt == null) {
				return 4;
			} else if (inDatabase == null) {
				tbContractPolicyTypeDetailRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateContractPolicyTypeDetail(TbContractPolicyTypeDetail entity) {
		try {
			TbContractPolicyTypeDetail inDatabase = tbContractPolicyTypeDetailRepository
					.findByContractPolicyTypeDetailId(entity.getSContractPolicyTypeDetailId());
			TbContractPolicyType cpt = tbContractPolicyTypeRepository
					.findByContractPolicyTypeId(entity.getSContractPolicyTypeId());
			if (cpt == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setMPrice(entity.getMPrice());
				inDatabase.setNCountFrom(entity.getNCountFrom());
				inDatabase.setNCountTo(entity.getNCountTo());
				inDatabase.setSContractPolicyTypeId(entity.getSContractPolicyTypeId());
				tbContractPolicyTypeDetailRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addCountry(TbCountry entity) {
		try {
			TbCountry inDatabase = tbCountryRepository.findByCountryId(entity.getSCountryId());
			if (inDatabase == null) {
				tbCountryRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateCountry(TbCountry entity) {
		try {
			TbCountry inDatabase = tbCountryRepository.findByCountryId(entity.getSCountryId());
			if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setSCountryFlagImage(entity.getSCountryFlagImage());
				inDatabase.setSCountryNameCn(entity.getSCountryNameCn());
				inDatabase.setSCountryNameEn(entity.getSCountryNameEn());
				inDatabase.setSCountryNameVn(entity.getSCountryNameVn());
				tbCountryRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer addCity(TbCity entity) {
		try {
			TbCity inDatabase = tbCityRepository.findByCityId(entity.getSCityId());
			TbCountry c = tbCountryRepository.findByCountryId(entity.getSCountryId());
			if (c == null) {
				return 4;
			} else if (inDatabase == null) {
				tbCityRepository.save(entity);
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer updateCity(TbCity entity) {
		try {
			TbCity inDatabase = tbCityRepository.findByCityId(entity.getSCityId());
			TbCountry c = tbCountryRepository.findByCountryId(entity.getSCountryId());
			if (c == null) {
				return 4;
			} else if (inDatabase == null) {
				return 2;
			} else {
				inDatabase.setICityMapLatitude(entity.getICityMapLatitude());
				inDatabase.setICityMapLongitude(entity.getICityMapLongitude());
				inDatabase.setSCityNameCn(entity.getSCityNameCn());
				inDatabase.setSCityNameEn(entity.getSCityNameEn());
				inDatabase.setSCityNameVn(entity.getSCityNameVn());
				inDatabase.setSCountryId(entity.getSCountryId());
				tbCityRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public List<TbGroupbusinessCateName> getAllGroupbusinessCateName() {
		List<TbGroupbusinessCateName> entities = new ArrayList<TbGroupbusinessCateName>();
		try {
			entities = tbGroupBusinessCateRepository.getAllGroupbusinessCateName();
		} catch (Exception e) {
			return entities;
		}
		return entities;
	}

	@SuppressWarnings("all")
	@Override
	public List<CompanyTypeInfo> loadCompanyType(JsonObject input) {

		String languageCode = StringUtils.EMPTY;
		if (!StringUtility.isEmpty(input.get(Parameter.LANGUAGE_CODE)))
			languageCode = input.get(Parameter.LANGUAGE_CODE).getAsString();

		StoredProcedureQuery q = entityManager
				.createStoredProcedureQuery("mdl_core.loadCompanyType", CompanyTypeInfo.class)
				.registerStoredProcedureParameter("languageCode", String.class, ParameterMode.IN)
				.setParameter("languageCode", languageCode);

		return q.getResultList();
	}

	@Override
	public List<TbCurrency> getAllCurrencies() {
		return tbCurrencyRepository.findAll();
	}

	@Override
	public String getImage(String tablename, String id) {

		Gson gson = new Gson();
		String imageData = StringUtils.EMPTY;
		if (Parameter.TABLE_DASHBOARD.equals(tablename.trim())) {
			TbDashboard image = tbDashboardRepository.findByDashBoardId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("iItemImage").getAsString();
		} else if (Parameter.TABLE_GROUP_BUSINESS.equals(tablename.trim())) {
			TbGroupBusiness image = tbGroupBusinessRepository.findByGroupBusinessId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("iGroupBusinessIcon").getAsString();
		} else if (Parameter.TABLE_BUSINESS_SERVICE.equals(tablename.trim())) {
			TbBusinessService image = tbBusinessServiceRepository.findByBusinessServiceId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("iBusinessServiceIcon").getAsString();
		} else if (Parameter.TABLE_GROUP_BUSINESS_CATE.equals(tablename.trim())) {
			TbGroupbusinessCate image = tbGroupBusinessCateRepository.findByGroupBusinessCateId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("iGroupBusinessCateIcon").getAsString();
		}

		return imageData;
	}

	@Override
	public List<PromotionModel> voucherPromote(JsonObject jObject) {
		Gson gson = new Gson();	
		String data =gson.toJson(jObject);
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("mdl_core.promote", PromotionModel.class)
				.registerStoredProcedureParameter("jsonInput", String.class, ParameterMode.IN)
				.setParameter("jsonInput", data);
		List<PromotionModel> result =  query.getResultList();
		return result;
	}

	@Override
	public TbPromotionHistory getPromotionByUserIdPromotionId(String userId, String promotionId) {
		return tbPromotionHistoryRepository.getPromotionByUserIdPromotionId(userId, promotionId);
	}
}
