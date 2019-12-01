package cob.com.business.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_mydeal database table.
 * 
 */
@Entity
public class MydealInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_deal_create_date")
	private Date dDealCreateDate;

	@Temporal(TemporalType.DATE)
	@Column(name="d_deal_valid_to")
	private Date dDealValidTo;

	@Temporal(TemporalType.DATE)
	@Column(name="d_deal_vlid_from")
	private Date dDealVlidFrom;

	@Column(name="n_is_buyer")
	private Integer nIsBuyer;

	@Column(name="n_is_buyer_posting")
	private Integer nIsBuyerPosting;

	@Column(name="n_price")
	private Integer nPrice;	

	@Column(name="s_deal_content")
	private String sDealContent;

	@Column(name="s_deal_header")
	private String sDealHeader;

	@Column(name="s_deal_image1")
	private String sDealImage1;

	@Column(name="s_deal_image2")
	private String sDealImage2;

	@Column(name="s_deal_image3")
	private String sDealImage3;

	@Column(name="s_mydeal_id")
	private String sMydealId;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_user_id")
	private String sUserId;
	
	@Column(name="s_group_business_service_id")
	private String sGroupBusinessServiceId;
	
	@Column(name="s_partner_name")
	private String sPartnerName;
	
	@Column(name="s_company_logo")
	private String sCompanyLogo;
	
	@Column(name="total_rows")
	private Integer totalRows;
	
	@Column(name="row_number")
	private Integer rowNumber;
	
	@Column(name="i_group_business_icon")
	private String iGroupBusinessIcon;
	
	@Column(name="s_group_business_name")
	private String sGroupBusinessName;
	
	@Column(name="number_like")
	private Integer numberLike;
	
	@Column(name="s_currency_id")
	private String scurrencyid;
	
	@Column(name="s_currency_symbol")
	private String scurrencysymbol;
	
	@Column(name="n_price_cob")
	private BigDecimal npricecob;
	
	@Column(name="number_of_comment")
	private Integer numberofcomment;
	
	@Column(name="number_of_share")
	private Integer numberofshare;
	
	@Column(name="s_group_business_cate_id")
	private String sgroupbusinesscateid;
	
	@Column(name="s_business_service_id")
	private String sbusinessserviceid;

	@Column(name="s_group_business_cate_name ")
	private String sgroupbusinesscatename ;
	//@Column(name="s_comments")
	//private BigDecimal scomments;
	
	@Column(name="is_like ")
	private Integer yourliked ;
	

	public Integer getYourliked() {
		return yourliked;
	}

	public void setYourliked(Integer yourliked) {
		this.yourliked = yourliked;
	}

	public String getSbusinessserviceid() {
		return sbusinessserviceid == null ? "" : sbusinessserviceid;
	}

	public String getSgroupbusinesscatename() {
		return sgroupbusinesscatename == null ? "" : sgroupbusinesscatename;
	}

	public void setSgroupbusinesscatename(String sgroupbusinesscatename) {
		this.sgroupbusinesscatename = sgroupbusinesscatename;
	}

	public void setSbusinessserviceid(String sbusinessserviceid) {
		this.sbusinessserviceid = sbusinessserviceid;
	}

	public String getSgroupbusinesscateid() {
		return sgroupbusinesscateid == null ? "" : sgroupbusinesscateid;
	}

	public void setSgroupbusinesscateid(String sgroupbusinesscateid) {
		this.sgroupbusinesscateid = sgroupbusinesscateid;
	}

	public Integer getNumberofcomment() {
		return numberofcomment == null ? 0 : numberofcomment;
	}

	public Integer getNumberofshare() {
		return numberofshare == null ? 0 : numberofshare;
	}

	public void setNumberofshare(Integer numberofshare) {
		this.numberofshare = numberofshare;
	}

	public void setNumberofcomment(Integer numberofcomment) {
		this.numberofcomment = numberofcomment;
	}

	public MydealInfo() {
	}
	
//	public BigDecimal getScomments() {
//		return scomments;
//	}
//
//	public void setScomments(BigDecimal scomments) {
//		this.scomments = scomments;
//	}

	public String getScurrencyid() {
		return scurrencyid;
	}

	public void setScurrencyid(String scurrencyid) {
		this.scurrencyid = scurrencyid;
	}

	public String getScurrencysymbol() {
		return scurrencysymbol;
	}

	public void setScurrencysymbol(String scurrencysymbol) {
		this.scurrencysymbol = scurrencysymbol;
	}

	public BigDecimal getNpricecob() {
		return npricecob.setScale(2, RoundingMode.HALF_UP);
	}

	public void setNpricecob(BigDecimal npricecob) {
		this.npricecob = npricecob;
	}

	public String getSGroupBusinessServiceId() {
		return this.sGroupBusinessServiceId;
	}

	public void setSGroupBusinessServiceId(String sGroupBusinessServiceId) {
		this.sGroupBusinessServiceId = sGroupBusinessServiceId;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDDealCreateDate() {
		return this.dDealCreateDate;
	}

	public void setDDealCreateDate(Date dDealCreateDate) {
		this.dDealCreateDate = dDealCreateDate;
	}

	public Date getDDealValidTo() {
		return this.dDealValidTo;
	}

	public void setDDealValidTo(Date dDealValidTo) {
		this.dDealValidTo = dDealValidTo;
	}

	public Date getDDealVlidFrom() {
		return this.dDealVlidFrom;
	}

	public void setDDealVlidFrom(Date dDealVlidFrom) {
		this.dDealVlidFrom = dDealVlidFrom;
	}

	public Integer getNIsBuyer() {
		return this.nIsBuyer;
	}

	public void setNIsBuyer(Integer nIsBuyer) {
		this.nIsBuyer = nIsBuyer;
	}

	public Integer getNIsBuyerPosting() {
		return this.nIsBuyerPosting;
	}

	public void setNIsBuyerPosting(Integer nIsBuyerPosting) {
		this.nIsBuyerPosting = nIsBuyerPosting;
	}

	public Integer getNPrice() {
		return this.nPrice;
	}

	public void setNPrice(Integer nPrice) {
		this.nPrice = nPrice;
	}

	

	public String getSDealContent() {
		return this.sDealContent;
	}

	public void setSDealContent(String sDealContent) {
		this.sDealContent = sDealContent;
	}

	public String getSDealHeader() {
		return this.sDealHeader;
	}

	public void setSDealHeader(String sDealHeader) {
		this.sDealHeader = sDealHeader;
	}

	public String getSDealImage1() {
		return this.sDealImage1;
	}

	public void setSDealImage1(String sDealImage1) {
		this.sDealImage1 = sDealImage1;
	}

	public String getSDealImage2() {
		return this.sDealImage2;
	}

	public void setSDealImage2(String sDealImage2) {
		this.sDealImage2 = sDealImage2;
	}

	public String getSDealImage3() {
		return this.sDealImage3;
	}

	public void setSDealImage3(String sDealImage3) {
		this.sDealImage3 = sDealImage3;
	}

	public String getSMydealId() {
		return this.sMydealId;
	}

	public void setSMydealId(String sMydealId) {
		this.sMydealId = sMydealId;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getsPartnerName() {
		return sPartnerName;
	}

	public void setsPartnerName(String sPartnerName) {
		this.sPartnerName = sPartnerName;
	}

	public String getsCompanyLogo() {
		return sCompanyLogo;
	}

	public void setsCompanyLogo(String sCompanyLogo) {
		this.sCompanyLogo = sCompanyLogo;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getNumberLike() {
		return numberLike == null ? 0 : numberLike;
	}

	public void setNumberLike(Integer numberLike) {
		this.numberLike = numberLike;
	}

	public String getiGroupBusinessIcon() {
		return iGroupBusinessIcon;
	}

	public void setiGroupBusinessIcon(String iGroupBusinessIcon) {
		this.iGroupBusinessIcon = iGroupBusinessIcon;
	}

	public String getsGroupBusinessName() {
		return sGroupBusinessName;
	}

	public void setsGroupBusinessName(String sGroupBusinessName) {
		this.sGroupBusinessName = sGroupBusinessName;
	}	
}