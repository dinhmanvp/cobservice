package cob.com.cobservice.services;

import java.util.List;

import com.google.gson.JsonObject;

import cob.com.cobservice.entities.TbAdviseService;
import cob.com.cobservice.entities.TbAdviseServiceDetail;
import cob.com.cobservice.entities.TbCurrency;
import cob.com.cobservice.entities.TbCurrencyExchange;
import cob.com.cobservice.entities.TbServiceUnit;
import cob.com.cobservice.entities.TbStartupPackage;
import cob.com.cobservice.entities.TbStartupPackageItem;
import cob.com.cobservice.ws.models.StartupPackageItemInfo;
import cob.com.cobservice.ws.models.TbAdviseServicePricingModel;
import cob.com.cobservice.ws.models.TbStartupPackageModel;

public interface COBServices {

	List<TbAdviseService> getAdviseServices();

	List<TbServiceUnit> getServiceUnit();

	List<TbAdviseServiceDetail> getAdviseServicesDetail(String adviseServiceId);

	List<TbAdviseServicePricingModel> getAdviseServicesPricing(String adviseServiceId, String serviceUnitId,
			String languageCode, String contentSearch);

	boolean registerAgent(JsonObject input);

	boolean agentUpdate(JsonObject input);

	Integer registerAdviseServices(TbAdviseService entity);

	Integer updateAdviseServices(TbAdviseService entity);

	Integer registerAdviseServicesDetail(TbAdviseServiceDetail entity);

	Integer updateAdviseServicesDetail(TbAdviseServiceDetail entity);

	Integer registerServiceUnit(TbServiceUnit entity);

	Integer updateServiceUnit(TbServiceUnit entity);

	Integer registerStarupPackage(TbStartupPackage entity);

	Integer updateStarupPackage(TbStartupPackage entity);

	TbStartupPackage getStarupPackagesById(String id);

	List<TbStartupPackageModel> getStarupPackagesByGroupBusinessId(String id, Integer isAvailable, String language);

	Integer registerStarupPackageItem(TbStartupPackageItem entity);

	Integer updateStarupPackageItem(TbStartupPackageItem entity);

	TbStartupPackageItem getItem(String id);

	List<StartupPackageItemInfo> getItemsByPackageId(String packageId, String languageCode);

	TbCurrency getByCurrencyId(String currencyId);

	TbCurrencyExchange getCurrencyByFrom(String currencyFrom);
	
	String getImage(String tablename, String id);
}
