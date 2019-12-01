package cob.com.cobservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cob.com.cobservice.ws.param.Parameter;


/**
 * The persistent class for the tb_startup_package database table.
 * 
 */
@Entity
@Table(name="tb_startup_package")
@NamedQuery(name="TbStartupPackage.findAll", query="SELECT t FROM TbStartupPackage t")
public class TbStartupPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_discount_price")
	private String nDiscountPrice;

	@Column(name="n_is_available")
	private Integer nIsAvailable;

	@Column(name="n_price")
	private String nPrice;

	@Column(name="n_sale_pricce")
	private String nSalePricce;

	@Column(name="s_group_business_id")
	private String sGroupBusinessId;

	@Column(name="s_package_desc_cn")
	private String sPackageDescCn;

	@Column(name="s_package_desc_en")
	private String sPackageDescEn;

	@Column(name="s_package_desc_vn")
	private String sPackageDescVn;

	@Column(name="s_package_icon")
	private String sPackageIcon;

	@Column(name="s_package_name_cn")
	private String sPackageNameCn;

	@Column(name="s_package_name_en")
	private String sPackageNameEn;

	@Column(name="s_package_name_vn")
	private String sPackageNameVn;

	@Column(name="s_startup_package_id")
	private String sStartupPackageId;
	
	@Column(name="s_currency_id")
	private String sCurrencyId;
	
	@Column(name="amount_click_star")
	private BigDecimal amountClickStar;

	public TbStartupPackage() {
	}
	
	public BigDecimal getAmountClickStar() {
		return amountClickStar;
	}

	public void setAmountClickStar(BigDecimal amountClickStar) {
		this.amountClickStar = amountClickStar;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getNDiscountPrice() {
		return this.nDiscountPrice;
	}

	public void setNDiscountPrice(String nDiscountPrice) {
		this.nDiscountPrice = nDiscountPrice;
	}

	public Integer getNIsAvailable() {
		return this.nIsAvailable;
	}

	public void setNIsAvailable(Integer nIsAvailable) {
		this.nIsAvailable = nIsAvailable;
	}

	public String getNPrice() {
		return this.nPrice;
	}

	public void setNPrice(String nPrice) {
		this.nPrice = nPrice;
	}

	public String getNSalePricce() {
		return this.nSalePricce;
	}

	public void setNSalePricce(String nSalePricce) {
		this.nSalePricce = nSalePricce;
	}

	public String getSGroupBusinessId() {
		return this.sGroupBusinessId;
	}

	public void setSGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public String getSPackageDescCn() {
		return this.sPackageDescCn;
	}

	public void setSPackageDescCn(String sPackageDescCn) {
		this.sPackageDescCn = sPackageDescCn;
	}

	public String getSPackageDescEn() {
		return this.sPackageDescEn;
	}

	public void setSPackageDescEn(String sPackageDescEn) {
		this.sPackageDescEn = sPackageDescEn;
	}

	public String getSPackageDescVn() {
		return this.sPackageDescVn;
	}

	public void setSPackageDescVn(String sPackageDescVn) {
		this.sPackageDescVn = sPackageDescVn;
	}

	public String getSPackageIcon() {
		String imageUrl = "cobservice/" + Parameter.TABLE_STARTUP_PACKAGE + "/" + this.sStartupPackageId;
		return this.sPackageIcon == null ? "" : imageUrl;
	}

	public void setSPackageIcon(String sPackageIcon) {
		this.sPackageIcon = sPackageIcon;
	}

	public String getSPackageNameCn() {
		return this.sPackageNameCn;
	}

	public void setSPackageNameCn(String sPackageNameCn) {
		this.sPackageNameCn = sPackageNameCn;
	}

	public String getSPackageNameEn() {
		return this.sPackageNameEn;
	}

	public void setSPackageNameEn(String sPackageNameEn) {
		this.sPackageNameEn = sPackageNameEn;
	}

	public String getSPackageNameVn() {
		return this.sPackageNameVn;
	}

	public void setSPackageNameVn(String sPackageNameVn) {
		this.sPackageNameVn = sPackageNameVn;
	}

	public String getSStartupPackageId() {
		return this.sStartupPackageId;
	}

	public void setSStartupPackageId(String sStartupPackageId) {
		this.sStartupPackageId = sStartupPackageId;
	}

	public String getsCurrencyId() {
		return sCurrencyId;
	}

	public void setsCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}
}