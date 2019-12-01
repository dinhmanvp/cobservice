package cob.com.cobservice.ws.param;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Parameter {
	public static final String S_AGENT_ID = "sagentId";
	public static final String S_ADVISE_SERVICE_ID = "sadviseServiceId";
	public static final String S_PARTNER_ID = "spartnerId";
	public static final String S_SERVICE_UNIT_ID = "sserviceUnitId";
	public static final String N_PRICE_BID = "npriceBid";
	public static final String N_COB_PRICE = "ncobPrice";
	public static final String N_APPROVAL_PRICE = "napprovalPrice";
	public static final String D_APPROVAL_DATE = "dapprovalDate";
	public static final String S_APPROVAL_BY = "sapprovalBy";
	public static final String N_IS_STOPED = "nisStoped";
	public static final String N_PARTNER_CONFIRMED = "npartnerConfirmed";
	public static final String N_PARTNER_COMMENT = "spartnerComment";
	public static final String S_COB_COMMENT = "scobComment";
	public static final String D_VALID_FROM = "dvalidFrom";
	public static final String D_VALID_TO = "dvalidTo";

	// advise service
	public static final String ADVISE_SERVICE_ID = "adviseServiceId";
	public static final String ADVISE_SERVICE_IS_AVAILABLE = "adviseServiceIsAvailable";
	public static final String ADVISE_SERVICE_RATING = "adviseServiceRating";
	public static final String ADVISE_SERVICE_ICON = "adviseServiceIcon";
	public static final String ADVISE_SERVICE_NAME_CN = "adviseServiceNameCn";
	public static final String ADVISE_SERVICE_NAME_EN = "adviseServiceNameEn";
	public static final String ADVISE_SERVICE_NAME_VN = "adviseServiceNameVn";
	public static final String ADVISE_SERVICE_SHORTDESC_CN = "adviseServiceShortdescCn";
	public static final String ADVISE_SERVICE_SHORTDESC_EN = "adviseServiceShortdescEn";
	public static final String ADVISE_SERVICE_SHORTDESC_VN = "adviseServiceShortdescVn";

	// advice serive detail
	public static final String ADVISE_SERVICE_DETAIL_ID = "adviseServiceDetailId";
	//public static final String ADVISE_SERVICE_ID = "adviseServiceId";
	public static final String CONTENT = "content";
	
	//service unit
	public static final String SERVICE_UNIT_ID = "serviceUnitId";
	public static final String DURATION_TYPE = "durationType";
	public static final String UNIT_NAME_CN = "unitNameCn";
	public static final String UNIT_NAME_EN = "unitNameEn";
	public static final String UNIT_NAME_VN = "unitNameVn";

	//startup package

	public static final String DISCOUNT_PRICE = "discountPrice";
	public static final String IS_AVAILABLE = "isAvailable";
	public static final String PRICE = "price";
	public static final String SALE_PRICE = "salePricce";
	public static final String GROUP_BUSINESS_ID = "groupBusinessId";
	public static final String PACKAGE_DESC_CN =  "packageDescCn";
	public static final String PACKAGE_DESC_EN = "packageDescEn";
	public static final String PACKAGE_DESC_VN = "packageDescVn";
	public static final String PACKAGE_ICON = "packageIcon";
	public static final String PACKAGE_NAME_CN = "packageNameCn";
	public static final String PACKAGE_NAME_EN = "packageNameEn";
	public static final String PACKAGE_NAME_VN = "packageNameVn";
	public static final String STARTUP_PACKAGE_ID = "startupPackageId";
	public static final String LANGUAGE_CODE = "languageCode";
	
	//start  up package item

	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	public static final String DURATION = "duration";
//	public static final String ADVISE_SERVICE_ID = "adviseServiceId";
//	public static final String DURATION_TYPE = "durationType";
//	public static final String SERVICE_UNIT_ID = "serviceUnitId";
//	public static final String STARTUP_PACKAGE_ID = "startupPackageId";
	public static final String STARTUP_PACKAGE_ITEM_ID = "startupPackageItemsId";
	public static final String CURRENCY_ID_DEFAULT = "COB";	
	public static final String PAGE_NUMBER = "pageNumber";
	public static final String PAGE_SIZE = "pageSize";
	
	public static final String TABLE_ADVISE_SERVICE = "adviseservice";
	public static final String TABLE_STARTUP_PACKAGE = "startuppackage";
}
