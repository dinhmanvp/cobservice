package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import cob.com.business.ws.param.Parameter;


/**
 * The persistent class for the tb_myproduct database table.
 * 
 */
@Entity
@Table(name="tb_myproduct")
@NamedQuery(name="TbMyproduct.findAll", query="SELECT t FROM TbMyproduct t")
public class TbMyproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_discount")
	private Integer nDiscount;

	@Column(name="n_price")
	private Integer nPrice;

	@Column(name="n_saleprice")
	private Integer nSaleprice;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_product_cate_id")
	private String sProductCateId;

	@Column(name="s_product_desc_cn")
	private String sProductDescCn;

	@Column(name="s_product_desc_en")
	private String sProductDescEn;

	@Column(name="s_product_desc_vn")
	private String sProductDescVn;

	@Column(name="s_product_header_cn")
	private String sProductHeaderCn;

	@Column(name="s_product_header_en")
	private String sProductHeaderEn;

	@Column(name="s_product_header_vn")
	private String sProductHeaderVn;

	@Column(name="s_product_id")
	private String sProductId;

	@Column(name="s_product_image_2")
	private String sProductImage2;

	@Column(name="s_product_image_3")
	private String sProductImage3;

	@Column(name="s_product_image_4")
	private String sProductImage4;

	@Column(name="s_product_image_main")
	private String sProductImageMain;

	@Column(name="s_user_id")
	private String sUserId;
	
	@Column(name="n_rating")
	private Integer nRating;

	@Column(name="s_currency")
	private String sCurrency;

	public TbMyproduct() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNDiscount() {
		return this.nDiscount;
	}

	public void setNDiscount(Integer nDiscount) {
		this.nDiscount = nDiscount;
	}

	public Integer getNPrice() {
		return this.nPrice;
	}

	public void setNPrice(Integer nPrice) {
		this.nPrice = nPrice;
	}

	public Integer getNSaleprice() {
		return this.nSaleprice;
	}

	public void setNSaleprice(Integer nSaleprice) {
		this.nSaleprice = nSaleprice;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSProductCateId() {
		return this.sProductCateId;
	}

	public void setSProductCateId(String sProductCateId) {
		this.sProductCateId = sProductCateId;
	}

	public String getSProductDescCn() {
		return this.sProductDescCn;
	}

	public void setSProductDescCn(String sProductDescCn) {
		this.sProductDescCn = sProductDescCn;
	}

	public String getSProductDescEn() {
		return this.sProductDescEn;
	}

	public void setSProductDescEn(String sProductDescEn) {
		this.sProductDescEn = sProductDescEn;
	}

	public String getSProductDescVn() {
		return this.sProductDescVn;
	}

	public void setSProductDescVn(String sProductDescVn) {
		this.sProductDescVn = sProductDescVn;
	}

	public String getSProductHeaderCn() {
		return this.sProductHeaderCn;
	}

	public void setSProductHeaderCn(String sProductHeaderCn) {
		this.sProductHeaderCn = sProductHeaderCn;
	}

	public String getSProductHeaderEn() {
		return this.sProductHeaderEn;
	}

	public void setSProductHeaderEn(String sProductHeaderEn) {
		this.sProductHeaderEn = sProductHeaderEn;
	}

	public String getSProductHeaderVn() {
		return this.sProductHeaderVn;
	}

	public void setSProductHeaderVn(String sProductHeaderVn) {
		this.sProductHeaderVn = sProductHeaderVn;
	}

	public String getSProductId() {
		return this.sProductId;
	}

	public void setSProductId(String sProductId) {
		this.sProductId = sProductId;
	}

	public String getSProductImage2() {
		//return this.sProductImage2;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_PRODUCT + "/" + this.sProductId  + "/2";
		return this.sProductImage2 == null?"":uri;
	}

	public void setSProductImage2(String sProductImage2) {
		this.sProductImage2 = sProductImage2;
	}

	public String getSProductImage3() {
		//return this.sProductImage3;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_PRODUCT + "/" + this.sProductId  + "/3";
		return this.sProductImage3 == null?"":uri;
	}

	public void setSProductImage3(String sProductImage3) {
		this.sProductImage3 = sProductImage3;
	}

	public String getSProductImage4() {
		//return this.sProductImage4;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_PRODUCT + "/" + this.sProductId  + "/4";
		return this.sProductImage4 == null?"":uri;
	}

	public void setSProductImage4(String sProductImage4) {
		this.sProductImage4 = sProductImage4;
	}

	public String getSProductImageMain() {
		//return this.sProductImageMain;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_PRODUCT + "/" + this.sProductId  + "/1";
		return this.sProductImageMain == null?"":uri;
	}

	public void setSProductImageMain(String sProductImageMain) {
		this.sProductImageMain = sProductImageMain;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	
	public Integer getNRating() {
		return this.nRating;
	}

	public void setNRating(Integer nRating) {
		this.nRating = nRating;
	}

	public String getSCurrency() {
		return this.sCurrency;
	}

	public void setSCurrency(String sCurrency) {
		this.sCurrency = sCurrency;
	}

}