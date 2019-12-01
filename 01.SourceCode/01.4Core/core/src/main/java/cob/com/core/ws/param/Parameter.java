package cob.com.core.ws.param;



import javax.persistence.Column;

public class Parameter {
	public static final String LANGUAGE = "language";
	public static final String LOAD_TYPE = "loadType";
	public static final String GROUP_BUSINESS_ID = "groupBusinessId";
	public static final String CONTRACT_POLICY_TYPE_ID = "contractPolicyTypeId";

	// dashboard param name
	public static final String N_ID = "id";
	public static final String DASHBOARD_ID = "dashboardId";
	public static final String DASHBOARD_IMAGE = "iItemImage";
	public static final String IS_AVAILABLE = "isAvailable";
	public static final String DASHBOARD_NAME_CN = "itemNameCn";
	public static final String DASHBOARD_NAME_EN = "itemNameEn";
	public static final String DASHBOARD_NAME_VN = "itemNameVn";
	public static final String ORDER = "order";

	// groupbusiness param name
	public static final String GROUP_BUSINESS_ICON = "groupBusinessIcon";
	public static final String GROUP_BUSINESS_AVAILABLE = "groupBusinessAvailble";
	public static final String GROUP_BUSINESS_RATING = "groupBusinessRating";
	public static final String HOME_DISPLAY = "homeDisplay";
	// public static final Integer ORDER = "order";
	// public static final String DASHBOARD_ID = "dashboardId";
	public static final String GROUP_BUSINESS_DESC_CN = "groupBusinessDescCn";
	public static final String GROUP_BUSINESS_DESC_EN = "groupBusinessDescEn";
	public static final String GROUP_BUSINESS_DESC_SHRINK_CN = "groupBusinessDescShrinkCn";
	public static final String GROUP_BUSINESS_DESC_SHRINK_EN = "groupBusinessDescShrinkEn";
	public static final String GROUP_BUSINESS_DESC_SHRINK_VN = "groupBusinessDescShrinkVn";
	public static final String GROUP_BUSINESS_DESC_VN = "groupBusinessDescVn";
	// public static final String GROUP_BUSINESS_ID = "groupBusinessId";
	public static final String GROUP_BUSINESS_NAME_CN = "groupBusinessNameCn";
	public static final String GROUP_BUSINESS_NAME_EN = "groupBusinessNameEn";
	public static final String GROUP_BUSINESS_NAME_VN = "groupBusinessNameVn";

	// BusinessService

	public static final String BUSINESS_SERVICE_ICON = "businessServiceIcon";
	public static final String BUSINESS_SERVICE_AVAILABLE = "businessServiceAvailable";
	public static final String BUSINESS_SERVICE_RATING = "bnBusinessServiceRating";
	// public static final Integer "order";
	public static final String BUSINESS_SERVICE_DESC_CN = "businessServiceDescCn";
	public static final String BUSINESS_SERVICE_DESC_EN = "businessServiceDescEn";
	public static final String BUSINESS_SERVICE_DESC_SHRINK_CN = "businessServiceDescShrinkCn";
	public static final String BUSINESS_SERVICE_DESC_SHRINK_EN = "businessServiceDescShrinkEn";
	public static final String BUSINESS_SERVICE_DESC_SHRINK_VN = "businessServiceDescShrinkVn";
	public static final String BUSINESS_SERVICE_DESC_VN = "businessServiceDescVn";
	public static final String BUSINESS_SERVICE_ID = "businessServiceId";
	public static final String BUSINESS_SERVICE_NAME_CN = "businessServiceNameCn";
	public static final String BUSINESS_SERVICE_NAME_EN = "businessServiceNameEn";
	public static final String BUSINESS_SERVICE_NAME_VN = "businessServiceNameVn";
	// public static final String GROUP_BUSINESS_ID = "groupBusinessId";

	
	//group business cate
	public static final String GROUP_BUSINESS_CATE_ICON = "groupBusinescateIcon";
	public static final String GROUP_BUSINESS_CATE_ID =  "groupBusinescateId";
	public static final String GROUP_BUSINESS_CATE_NAME_VN = "groupBusinescateNameVn";
	public static final String GROUP_BUSINESS_DESCRIPTION_CN =  "groupBusinessDescriptionCn";
	public static final String GROUP_BUSINESS_DESCRIPTION_EN =  "groupBusinessDescriptionEn";
	public static final String GROUP_BUSINESS_DESCRIPTION_VN =  "groupBusinessDescriptionVn";
//	public static final String GROUP_BUSINESS_ID = "groupBusinessId";
	public static final String  GROUP_BUSINESS_CATE_NAME_CN = "groupBusinessNameCateCn";
	public static final String  GROUP_BUSINESS_CATE_NAME_EN = "groupBusinessNameCateEn";
	
	//business type
	public static final String BUSINESS_TYPE_DESC_CN = "businessTypeDescCn";
	public static final String BUSINESS_TYPE_DESC_EN = "businessTypeDescEn";
	public static final String BUSINESS_TYPE_DESC_VN =  "businessTypeDescVn";
	public static final String BUSINESS_TYPE_ID = "businessTypeId";
	public static final String BUSINESS_TYPE_NAME_CN = "businessTypeNameCn";
	public static final String  BUSINESS_TYPE_NAME_EN ="businessTypeNameEn";
	public static final String  BUSINESS_TYPE_NAME_VN ="businessTypeNameVn";
	public static final String BUSINESS_GROUP_ID = "businessGroupId";
	
	//contract policy type
	public static final String FROM_AMOUNT = "fromAmount";
	public static final String TO_AMOUNT = "toAmount";
	public static final String CHARGE_IN_TRANSACTION = "chargeInTransaction";
	public static final String IS_MONTHLY_FEE_APPLIED = "isMontlyfeeApplied";
//	public static final String CONTRACT_POLICY_TYPE_ID = "contractPolicyTypeId";
	public static final String POLICY_TYPE_NAME_CN = "policyTypeNameCn";
	public static final String POLICY_TYPE_NAME_EN =  "policyTypeNameEn";
	public static final String POLICY_TYPE_NAME_VN =  "policyTypeNameVn";

	//contract policy type detail
	public static final String PRICE = "price";
	public static final String COUNT_FROM = "countFrom";
	public static final String COUNT_TO = "countTo";
	public static final String CONTRACT_POLICY_TYPE_DETAIL_ID = "contractPolicyTypeDetailId";
//	public static final String CONTRACT_POLICY_TYPE_ID = "contractPolicyTypeId";
	
	//country

	public static final String COUNTRY_FLAG_IMAGE = "countryFlagImage";
	public static final String COUNTRY_ID = "countryId";
	public static final String COUNTRY_NAME_CN = "countryNameCn";
	public static final String COUNTRY_NAME_EN =  "countryNameEn";
	public static final String COUNTRY_NAME_VN =  "countryNameVn";
	
	//city
	public static final String CITY_MAP_LATITUDE = "cityMapLatitude";
	public static final String CITY_MAP_LONGTITUDE = "cityMapLongitude";
	public static final String CITY_ID = "cityId";
	public static final String CITY_NAME_CN = "cityNameCn";
	public static final String CITY_NAME_EN =  "cityNameEn";
	public static final String CITY_NAME_VN =  "cityNameVn";
//	public static final String COUNTRY_ID = "countryId";
	public static final String LANGUAGE_CODE = "languageCode";
	public static final String TABLE_DASHBOARD = "dashboard";
	public static final String TABLE_GROUP_BUSINESS = "groupbusiness";
	public static final String TABLE_BUSINESS_SERVICE = "businessservices";
	public static final String TABLE_GROUP_BUSINESS_CATE = "groupbusinesscate";
	public static final String IMAGE_URI = "core/getImage/";
	
}