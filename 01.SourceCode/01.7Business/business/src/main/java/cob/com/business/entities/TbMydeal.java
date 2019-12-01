package cob.com.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cob.com.business.ws.param.Parameter;


/**
 * The persistent class for the tb_mydeal database table.
 * 
 */
@Entity
@Table(name="tb_mydeal")
@NamedQuery(name="TbMydeal.findAll", query="SELECT t FROM TbMydeal t")
public class TbMydeal implements Serializable {
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
	
	@Column(name="s_currency_id")
	private String scurrencyid;
	
	@Column(name="s_business_service_id")
	private String sbusinessserviceid;
	
	@Column(name="s_group_business_cate_id")
	private String sgroupbusinesscateid;
	
	@Column(name="n_quantity")
	private Integer nQuantity;
	
	@Column(name="s_product_id")
	private String sProductId;

	
	public String getsProductId() {
		return sProductId == null ? "" : sProductId;
	}

	public void setsProductId(String sProductId) {
		this.sProductId = sProductId;
	}

	public Integer getnQuantity() {
		return nQuantity == null ? 0 : nQuantity;
	}

	public void setnQuantity(Integer nQuantity) {
		this.nQuantity = nQuantity;
	}

	public String getSbusinessserviceid() {
		return sbusinessserviceid;
	}

	public void setSbusinessserviceid(String sbusinessserviceid) {
		this.sbusinessserviceid = sbusinessserviceid;
	}

	public String getSgroupbusinesscateid() {
		return sgroupbusinesscateid;
	}

	public void setSgroupbusinesscateid(String sgroupbusinesscateid) {
		this.sgroupbusinesscateid = sgroupbusinesscateid;
	}

	public String getScurrencyid() {
		return scurrencyid;
	}

	public void setScurrencyid(String scurrencyid) {
		this.scurrencyid = scurrencyid;
	}

	public TbMydeal() {
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
		//return this.sDealImage1;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_DEAL + "/" + this.sMydealId  + "/1";
		return this.sDealImage1 == null?"":uri;
	}

	public void setSDealImage1(String sDealImage1) {
		this.sDealImage1 = sDealImage1;
	}

	public String getSDealImage2() {
		//return this.sDealImage2;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_DEAL + "/" + this.sMydealId  + "/2";
		return this.sDealImage2 == null?"":uri;
	}

	public void setSDealImage2(String sDealImage2) {
		this.sDealImage2 = sDealImage2;
	}

	public String getSDealImage3() {
		//return this.sDealImage3;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_DEAL + "/" + this.sMydealId  + "/3";
		return this.sDealImage3 == null?"":uri;
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

}