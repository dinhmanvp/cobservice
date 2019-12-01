package cob.com.core.services;

import java.util.List;

import com.google.gson.JsonObject;

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
import cob.com.core.ws.models.BusinessServiceInfo;
import cob.com.core.ws.models.CompanyTypeInfo;
import cob.com.core.ws.models.PromotionModel;

public interface Core {
	List<TbDashboard> getDashboardList(Integer typeId);

	List<TbGroupBusiness> getGroupBusinessByDashboardId(String DbId);

	List<BusinessServiceInfo> getBusinessServicesByGroupBusinessId(String gbId);

	List<TbBusinessType> getBusinessType(String groupBusinessId);

	List<TbCity> getCity(JsonObject input);

	List<TbCountry> getCountry();

	List<TbGroupbusinessCate> getGroupbusinessCate(String btId);

	List<TbGroupBusiness> getGroupBusinessisHome();

	Object groupBusinessisHomeCretate(String typeId);

	Object groupBusinessisHomeRemove(String typeId);

	List<TbContractPolicyTypeDetail> getContractPolicyTypeDetail(String tdId);

	List<TbContractPolicyType> getContractPolicyType();

	Integer updateDashboard(TbDashboard entity);

	Integer addDashboard(TbDashboard entity);

	Integer groupBusinessUpdate(TbGroupBusiness entity);

	Integer addgroupBusiness(TbGroupBusiness entity);

	Integer updateBusinessService(TbBusinessService entity);

	Integer addBusinessService(TbBusinessService entity);

	Integer updategroupBusinessCate(TbGroupbusinessCate entity);

	Integer addgroupBusinessCate(TbGroupbusinessCate entity);

	Integer addBusinessType(TbBusinessType entity);

	Integer updateBusinessType(TbBusinessType entity);

	Integer addContractPolicyType(TbContractPolicyType entity);

	Integer updateContractPolicyType(TbContractPolicyType entity);

	Integer addContractPolicyTypeDetail(TbContractPolicyTypeDetail entity);

	Integer updateContractPolicyTypeDetail(TbContractPolicyTypeDetail entity);
	
	Integer addCountry(TbCountry entity);
	
	Integer updateCountry(TbCountry entity);
	
	Integer addCity(TbCity entity);
	
	Integer updateCity(TbCity entity);
	
	List<TbGroupbusinessCateName> getAllGroupbusinessCateName();
	
	List<CompanyTypeInfo> loadCompanyType(JsonObject input);

	List<TbCurrency> getAllCurrencies();
	
	String getImage(String tablename, String id);

	List<PromotionModel> voucherPromote(JsonObject jObject);
	
	TbPromotionHistory getPromotionByUserIdPromotionId(String userId, String promotionId);
}
