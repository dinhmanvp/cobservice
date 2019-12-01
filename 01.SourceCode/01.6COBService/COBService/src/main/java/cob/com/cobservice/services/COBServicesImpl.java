package cob.com.cobservice.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.cobservice.dao.TbAdviseServiceDetailRepository;
import cob.com.cobservice.dao.TbAdviseServicePricingRepository;
import cob.com.cobservice.dao.TbAdviseServiceRepository;
import cob.com.cobservice.dao.TbCurrencyExchangeRepository;
import cob.com.cobservice.dao.TbCurrencyRepository;
import cob.com.cobservice.dao.TbPartnerRegistrationAgentRepository;
import cob.com.cobservice.dao.TbServiceUnitRepository;
import cob.com.cobservice.dao.TbStartupPackageItemRepository;
import cob.com.cobservice.dao.TbStartupPackageRepository;
import cob.com.cobservice.entities.TbAdviseService;
import cob.com.cobservice.entities.TbAdviseServiceDetail;
import cob.com.cobservice.entities.TbCurrency;
import cob.com.cobservice.entities.TbCurrencyExchange;
import cob.com.cobservice.entities.TbPartnerRegistrationAgent;
import cob.com.cobservice.entities.TbServiceUnit;
import cob.com.cobservice.entities.TbStartupPackage;
import cob.com.cobservice.entities.TbStartupPackageItem;
import cob.com.cobservice.utils.StringUtility;
import cob.com.cobservice.ws.models.StartupPackageItemInfo;
import cob.com.cobservice.ws.models.TbAdviseServicePricingModel;
import cob.com.cobservice.ws.models.TbStartupPackageModel;
import cob.com.cobservice.ws.param.Parameter;

@Component
public class COBServicesImpl implements COBServices{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TbAdviseServiceRepository tbAdviseServiceRepository;
	
	@Autowired
	private TbAdviseServiceDetailRepository tbAdviseServiceDetailRepository;
	
	@Autowired
	private TbServiceUnitRepository tbServiceUnitRepository;
	
	@Autowired
	private TbAdviseServicePricingRepository tbAdviseServicePricingRepository;
	
	@Autowired
	private TbPartnerRegistrationAgentRepository tbPartnerRegistrationAgentRepository; 
	
	@Autowired
	TbStartupPackageRepository tbStartupPackageRepository;
	
	@Autowired
	TbStartupPackageItemRepository tbStartupPackageItemRepository;
	
	@Autowired
	TbCurrencyRepository tbCurrencyRepository;
	
	@Autowired
	TbCurrencyExchangeRepository tbCurrencyExchangeRepository;

	@Override
	public List<TbAdviseService> getAdviseServices() {
		List<TbAdviseService> lstAdviseService = new ArrayList<TbAdviseService>();
		try
		{
			lstAdviseService = tbAdviseServiceRepository.findAll();
		}catch(Exception ex)
		{
			return null;
		}
		
		return lstAdviseService;
	}
	
	@Override
	public List<TbServiceUnit> getServiceUnit() {
		List<TbServiceUnit> lstServiceUnit = new ArrayList<TbServiceUnit>();
		lstServiceUnit = tbServiceUnitRepository.findAll();
		return lstServiceUnit;
	}

	
	@Override
	public List<TbAdviseServiceDetail> getAdviseServicesDetail(String adviseServiceId) {
		List<TbAdviseServiceDetail> AdviseServiceDetail = new ArrayList<TbAdviseServiceDetail>();
		AdviseServiceDetail = tbAdviseServiceDetailRepository.findAdseByID(adviseServiceId);
		return AdviseServiceDetail;
	}

	@Override
	public List<TbAdviseServicePricingModel> getAdviseServicesPricing(String adviseServiceId, String serviceUnitId, String languageCode, String contentSearch) {
		List<TbAdviseServicePricingModel> AdviseServicePricings = new ArrayList<TbAdviseServicePricingModel>();
//		AdviseServicePricing = tbAdviseServicePricingRepository.findsepribyID(adviseServiceId,serviceUnitId);
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("mdl_cob.getadviseservicespricing", TbAdviseServicePricingModel.class)
					.registerStoredProcedureParameter("adviseServiceId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("serviceUnitId",String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("languegeCode",String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("contentSearch",String.class, ParameterMode.IN)
					.setParameter("adviseServiceId", adviseServiceId)
					.setParameter("serviceUnitId", serviceUnitId)
					.setParameter("languegeCode", languageCode)
					.setParameter("contentSearch", contentSearch);
			AdviseServicePricings = (List<TbAdviseServicePricingModel>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return AdviseServicePricings;
		}
		return AdviseServicePricings;
	}

	

	@Override
	public boolean registerAgent(JsonObject jsonInput) {
		TbPartnerRegistrationAgent agent = new TbPartnerRegistrationAgent();
		try 
		{
			Date time = new Date();
			Long getid = time.getTime();
			
			agent.setSAgentId(Long.toString(getid));
			
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_ADVISE_SERVICE_ID)))
				agent.setSAdviseServiceId(jsonInput.get(Parameter.S_ADVISE_SERVICE_ID).getAsString());
			else agent.setSAdviseServiceId(null);
			
			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
				agent.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());
			else agent.setSPartnerId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SERVICE_UNIT_ID)))
				agent.setSServiceUnitId(jsonInput.get(Parameter.S_SERVICE_UNIT_ID).getAsString());
			else agent.setSServiceUnitId(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PRICE_BID)))
				agent.setNPriceBid(jsonInput.get(Parameter.N_PRICE_BID).getAsInt());
			else agent.setNPriceBid(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_COB_PRICE)))
				agent.setNCobPrice(jsonInput.get(Parameter.N_COB_PRICE).getAsInt());
			else agent.setNCobPrice(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_APPROVAL_PRICE)))
				agent.setNApprovalPrice(jsonInput.get(Parameter.N_APPROVAL_PRICE).getAsInt());
			else agent.setNApprovalPrice(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_APPROVAL_BY)))
				agent.setSApprovalBy(jsonInput.get(Parameter.S_APPROVAL_BY).getAsString());
			else agent.setSApprovalBy(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_IS_STOPED)))
				agent.setNIsStoped(jsonInput.get(Parameter.N_IS_STOPED).getAsInt());
			else agent.setNIsStoped(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PARTNER_CONFIRMED)))
				agent.setNPartnerConfirmed(jsonInput.get(Parameter.N_PARTNER_CONFIRMED).getAsInt());
			else agent.setNPartnerConfirmed(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PARTNER_COMMENT)))
				agent.setSPartnerComment(jsonInput.get(Parameter.N_PARTNER_COMMENT).getAsString());
			else agent.setSPartnerComment(null);

			if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COB_COMMENT)))
				agent.setSCobComment(jsonInput.get(Parameter.S_COB_COMMENT).getAsString());
			else agent.setSCobComment(null);
			
			
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date datefm;
	        
			try {
				
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_APPROVAL_DATE)))
				{
					datefm = df.parse(jsonInput.get(Parameter.D_APPROVAL_DATE).getAsString());
					agent.setDApprovalDate(datefm);
				}
				else
					agent.setDApprovalDate(null);
			} catch (ParseException e) {agent.setDApprovalDate(null);}
			
			try {
				
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_VALID_FROM)))
				{
					datefm = df.parse(jsonInput.get(Parameter.D_VALID_FROM).getAsString());
					agent.setDValidFrom(datefm);
				}
				else
					agent.setDValidFrom(null);
			} catch (ParseException e) {agent.setDValidFrom(null);}
			

			try {
				
				if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_VALID_TO)))
				{
					datefm = df.parse(jsonInput.get(Parameter.D_VALID_TO).getAsString());
					agent.setDValidTo(datefm);
				}
				else
					agent.setDValidTo(null);
			} catch (ParseException e) {agent.setDValidTo(null);}
			
			tbPartnerRegistrationAgentRepository.save(agent);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}

	@Override
	public boolean agentUpdate(JsonObject jsonInput) {
		if(!StringUtility.isEmpty(jsonInput.get(Parameter.S_AGENT_ID)))
		{
			
			TbPartnerRegistrationAgent agent =  tbPartnerRegistrationAgentRepository.getAgentByID(jsonInput.get(Parameter.S_AGENT_ID).getAsString());
			if(agent != null)
			{
				try 
				{
					
					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_ADVISE_SERVICE_ID)))
						agent.setSAdviseServiceId(jsonInput.get(Parameter.S_ADVISE_SERVICE_ID).getAsString());
					
					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_PARTNER_ID)))
						agent.setSPartnerId(jsonInput.get(Parameter.S_PARTNER_ID).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_SERVICE_UNIT_ID)))
						agent.setSServiceUnitId(jsonInput.get(Parameter.S_SERVICE_UNIT_ID).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PRICE_BID)))
						agent.setNPriceBid(jsonInput.get(Parameter.N_PRICE_BID).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_COB_PRICE)))
						agent.setNCobPrice(jsonInput.get(Parameter.N_COB_PRICE).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_APPROVAL_PRICE)))
						agent.setNApprovalPrice(jsonInput.get(Parameter.N_APPROVAL_PRICE).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_APPROVAL_BY)))
						agent.setSApprovalBy(jsonInput.get(Parameter.S_APPROVAL_BY).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_IS_STOPED)))
						agent.setNIsStoped(jsonInput.get(Parameter.N_IS_STOPED).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PARTNER_CONFIRMED)))
						agent.setNPartnerConfirmed(jsonInput.get(Parameter.N_PARTNER_CONFIRMED).getAsInt());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.N_PARTNER_COMMENT)))
						agent.setSPartnerComment(jsonInput.get(Parameter.N_PARTNER_COMMENT).getAsString());

					if (!StringUtility.isEmpty(jsonInput.get(Parameter.S_COB_COMMENT)))
						agent.setSCobComment(jsonInput.get(Parameter.S_COB_COMMENT).getAsString());
					
					
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			        Date datefm;
			        
					try {
						
						if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_APPROVAL_DATE)))
						{
							datefm = df.parse(jsonInput.get(Parameter.D_APPROVAL_DATE).getAsString());
							agent.setDApprovalDate(datefm);
						}
					} catch (ParseException e) {}
					
					try {
						
						if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_VALID_FROM)))
						{
							datefm = df.parse(jsonInput.get(Parameter.D_VALID_FROM).getAsString());
							agent.setDValidFrom(datefm);
						}
					} catch (ParseException e) {}
					

					try {
						
						if (!StringUtility.isEmpty(jsonInput.get(Parameter.D_VALID_TO)))
						{
							datefm = df.parse(jsonInput.get(Parameter.D_VALID_TO).getAsString());
							agent.setDValidTo(datefm);
						}
					} catch (ParseException e) {}
					
					tbPartnerRegistrationAgentRepository.saveAndFlush(agent);
					return true;
				}catch(Exception ex)
				{
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public Integer registerAdviseServices(TbAdviseService entity) {
		try {			
			TbAdviseService inDatabase = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			if(inDatabase != null) {
				return 2;
			}
			else {
				tbAdviseServiceRepository.save(entity);
			}
		} catch (Exception e) {
			return 3;
		}
		
		return 1;
	}

	@Override
	public Integer updateAdviseServices(TbAdviseService entity) {
		
		try {
			TbAdviseService inDatabase = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			if(inDatabase == null) {
				return 2;
			}else {
				inDatabase.setNAdviseServiceIsAvailable(entity.getNAdviseServiceIsAvailable());
				inDatabase.setNAdviseServiceRating(entity.getNAdviseServiceRating());
				inDatabase.setSAdviseServiceIcon(entity.getSAdviseServiceIcon());
				inDatabase.setSAdviseServiceNameCn(entity.getSAdviseServiceNameCn());
				inDatabase.setSAdviseServiceNameEn(entity.getSAdviseServiceNameEn());
				inDatabase.setSAdviseServiceNameVn(entity.getSAdviseServiceNameVn());
				inDatabase.setSAdviseServiceShortdescCn(entity.getSAdviseServiceShortdescCn());
				inDatabase.setSAdviseServiceShortdescEn(entity.getSAdviseServiceShortdescEn());
				inDatabase.setSAdviseServiceShortdescVn(entity.getSAdviseServiceShortdescVn());
				tbAdviseServiceRepository.save(inDatabase);
			}			
		} catch (Exception e) {
			return 3;
		}
		return 1;
	}

	@Override
	public Integer registerAdviseServicesDetail(TbAdviseServiceDetail entity) {
		try {			
			TbAdviseServiceDetail inDatabase = tbAdviseServiceDetailRepository.findByAdviseServiceDetailId(entity.getAdviseServiceDetailId());
			TbAdviseService reference = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			if(reference == null) {
				return 4;
			}
			else if(inDatabase != null) {
				return 2;
			}
			else {
				tbAdviseServiceDetailRepository.save(entity);
			}
		} catch (Exception e) {
			return 3;
		}
		
		return 1;
	}

	@Override
	public Integer updateAdviseServicesDetail(TbAdviseServiceDetail entity) {
		try {			
			TbAdviseServiceDetail inDatabase = tbAdviseServiceDetailRepository.findByAdviseServiceDetailId(entity.getAdviseServiceDetailId());
			TbAdviseService reference = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			if(reference == null) {
				return 4;
			}
			else if(inDatabase == null) {
				return 2;
			}
			else {
				inDatabase.setAdviseServiceDetailId(entity.getAdviseServiceDetailId());
				inDatabase.setSAdviseServiceId(entity.getSAdviseServiceId());
				inDatabase.setSContent(entity.getSContent());
				tbAdviseServiceDetailRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		
		return 1;
	}

	@Override
	public Integer registerServiceUnit(TbServiceUnit entity) {
		try {			
			TbServiceUnit inDatabase = tbServiceUnitRepository.findById(entity.getSServiceUnitId());
			if(inDatabase != null) {
				return 2;
			}
			else {
				tbServiceUnitRepository.save(entity);
			}
		} catch (Exception e) {
			return 3;
		}
		
		return 1;
	}

	@Override
	public Integer updateServiceUnit(TbServiceUnit entity) {
		try {			
			TbServiceUnit inDatabase = tbServiceUnitRepository.findById(entity.getSServiceUnitId());
			if(inDatabase == null) {
				return 2;
			}
			else {
				inDatabase.setSDurationType(entity.getSDurationType());
				inDatabase.setSUnitNameCn(entity.getSUnitNameCn());
				inDatabase.setSUnitNameVn(entity.getSUnitNameVn());
				inDatabase.setSUnitNameEn(entity.getSUnitNameEn());				
				tbServiceUnitRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}
		
		return 1;
	}

	@Override
	public Integer registerStarupPackage(TbStartupPackage entity) {
		try {			
			TbStartupPackage inDatabase = tbStartupPackageRepository.findById(entity.getSStartupPackageId());
			if(inDatabase != null) {
				return 2;
			}
			else {
				tbStartupPackageRepository.save(entity);
			}
		} catch (Exception e) {
			return 3;
		}		
		return 1;
	}

	@Override
	public Integer updateStarupPackage(TbStartupPackage entity) {
		try {			
			TbStartupPackage inDatabase = tbStartupPackageRepository.findById(entity.getSStartupPackageId());			
			if(inDatabase == null) {
				return 2;
			}
			else {
				inDatabase.setNDiscountPrice(entity.getNDiscountPrice());
				inDatabase.setNIsAvailable(entity.getNIsAvailable());
				inDatabase.setNPrice(entity.getNPrice());
				inDatabase.setNSalePricce(entity.getNSalePricce());
				inDatabase.setSGroupBusinessId(entity.getSGroupBusinessId());
				inDatabase.setSPackageDescCn(entity.getSPackageNameCn());
				inDatabase.setSPackageDescVn(entity.getSPackageNameVn());
				inDatabase.setSPackageDescEn(entity.getSPackageNameEn());
				inDatabase.setSPackageIcon(entity.getSPackageIcon());
				inDatabase.setSPackageNameCn(entity.getSPackageNameCn());
				inDatabase.setSPackageNameEn(entity.getSPackageNameEn());
				inDatabase.setSPackageNameVn(entity.getSPackageNameVn());				
				tbStartupPackageRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}		
		return 1;
	}

	@Override
	public TbStartupPackage getStarupPackagesById(String id) {
		TbStartupPackage startupPackage = new TbStartupPackage();
		try {
			startupPackage = tbStartupPackageRepository.findById(id);
		} catch (Exception e) {
			return startupPackage;
		}
		return startupPackage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbStartupPackageModel> getStarupPackagesByGroupBusinessId(String id, Integer isAvailable, String language) {
		 List<TbStartupPackageModel> startupPackages = new ArrayList<TbStartupPackageModel>();
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("mdl_cob.getstartuppackagebygroupbusinessid", TbStartupPackageModel.class)
					.registerStoredProcedureParameter("id", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("isavailable", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("language_code", String.class, ParameterMode.IN)
					.setParameter("id", id)
					.setParameter("isavailable", isAvailable)
					.setParameter("language_code", language);
			startupPackages = (List<TbStartupPackageModel>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return startupPackages;
		}
		return startupPackages;
	}

	@Override
	public Integer registerStarupPackageItem(TbStartupPackageItem entity) {
		try {			
			TbStartupPackageItem inDatabase = tbStartupPackageItemRepository.findById(entity.getSStartupPackageItemsId());
			TbAdviseService as = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			TbStartupPackage sp = tbStartupPackageRepository.findById(entity.getSStartupPackageId());
			TbServiceUnit su = tbServiceUnitRepository.findById(entity.getSServiceUnitId());
			if(as == null || sp == null || su == null) {
				return 4;
			}
			else if(inDatabase != null) {
				return 2;
			}
			else {
				tbStartupPackageItemRepository.save(entity);
			}
		} catch (Exception e) {
			return 3;
		}		
		return 1;
	}

	@Override
	public Integer updateStarupPackageItem(TbStartupPackageItem entity) {
		try {			
			TbStartupPackageItem inDatabase = tbStartupPackageItemRepository.findById(entity.getSStartupPackageItemsId());
			TbAdviseService as = tbAdviseServiceRepository.findByAdviseServiceId(entity.getSAdviseServiceId());
			TbStartupPackage sp = tbStartupPackageRepository.findById(entity.getSStartupPackageId());
			TbServiceUnit su = tbServiceUnitRepository.findById(entity.getSServiceUnitId());
			if(as == null || sp == null || su == null) {
				return 4;
			}
			else if(inDatabase == null) {
				return 2;
			}
			else {
				inDatabase.setDFromDate(entity.getDFromDate().toString());
				inDatabase.setDToDate(entity.getDToDate().toString());
				inDatabase.setNDuration(entity.getNDuration());
				inDatabase.setSAdviseServiceId(entity.getSAdviseServiceId());
				inDatabase.setSDurationType(entity.getSDurationType());
				inDatabase.setSServiceUnitId(entity.getSServiceUnitId());
				inDatabase.setSStartupPackageId(entity.getSServiceUnitId());
				tbStartupPackageItemRepository.save(inDatabase);
			}
		} catch (Exception e) {
			return 3;
		}		
		return 1;
	}

	@Override
	public TbStartupPackageItem getItem(String id) {
		TbStartupPackageItem item = tbStartupPackageItemRepository.findById(id);
		return item;
	}

	@SuppressWarnings("all")
	@Override
	public List<StartupPackageItemInfo> getItemsByPackageId(String packageId, String languageCode) {
		
		StoredProcedureQuery query = em.createStoredProcedureQuery("mdl_cob.getstaruppackageitems", StartupPackageItemInfo.class)
				.registerStoredProcedureParameter("startuppackageid", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("languegecode", String.class, ParameterMode.IN)
				.setParameter("startuppackageid", packageId)
				.setParameter("languegecode", languageCode);
		
		
		List<StartupPackageItemInfo> items = query.getResultList();
		return items;
	}

	@Override
	public TbCurrency getByCurrencyId(String currencyId) {		
		return tbCurrencyRepository.getByCurrencyId(currencyId);
	}

	@Override
	public TbCurrencyExchange getCurrencyByFrom(String currencyFrom) {
		return tbCurrencyExchangeRepository.getCurrencyFrom(currencyFrom);
	}
	
	@Override
	public String getImage(String tablename, String id) {
		Gson gson = new Gson();
		String imageData = StringUtils.EMPTY;
		if (Parameter.TABLE_ADVISE_SERVICE.equals(tablename.trim())) {			
			TbAdviseService image = tbAdviseServiceRepository.findByAdviseServiceId(id);
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("sAdviseServiceIcon").getAsString();					
		}
		else
		{
			TbStartupPackage image = tbStartupPackageRepository.findById(id);
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("sPackageIcon").getAsString();
		}
		return imageData;
	}
	

}
