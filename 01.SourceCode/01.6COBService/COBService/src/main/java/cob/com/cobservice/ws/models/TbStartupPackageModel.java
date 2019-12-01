package cob.com.cobservice.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cob.com.cobservice.entities.TbStartupPackage;
import cob.com.cobservice.ws.param.Parameter;

/**
 * The persistent class for the tb_startup_package database table.
 * 
 */
@Entity
public class TbStartupPackageModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "n_discount_price")
	private String nDiscountPrice;

	@Column(name = "n_is_available")
	private Integer nIsAvailable;

	@Column(name = "n_price")
	private String nPrice;

	@Column(name = "n_sale_pricce")
	private String nSalePrice;

	@Column(name = "s_group_business_id")
	private String sGroupBusinessId;

	@Column(name = "s_package_desc")
	private String sPackageDesc;

	@Column(name = "s_package_icon")
	private String sPackageIcon;

	@Column(name = "s_package_name")
	private String sPackageName;

	@Column(name = "s_startup_package_id")
	private String sStartupPackageId;

	@Column(name = "s_currency_id")
	private String sCurrencyId;

	@Column(name = "s_currency_symbol")
	private String sCurrencySymbol;

	@Column(name = "amount_click_star")
	private BigDecimal amountClickStar;

	@Column(name = "n_sale_price_cob")
	private BigDecimal nSalePriceCob;

	public BigDecimal getnSalePriceCob() {
		return this.nSalePriceCob.setScale(2);
	}

	public void setnSalePriceCob(BigDecimal nSalePriceCob) {
		this.nSalePriceCob = nSalePriceCob;
	}

	public BigDecimal getAmountClickStar() {
		return amountClickStar == null ? new BigDecimal(0) : amountClickStar;
	}

	public void setAmountClickStar(BigDecimal amountClickStar) {
		this.amountClickStar = amountClickStar;
	}

	public TbStartupPackageModel() {
	}

	public TbStartupPackageModel(TbStartupPackage entity, String sCurrencySymbol) {
		this.nId = entity.getNId();
		this.nDiscountPrice = entity.getNDiscountPrice();
		this.nIsAvailable = entity.getNIsAvailable();
		this.nPrice = entity.getNPrice();
		this.nSalePrice = entity.getNSalePricce();
		this.sGroupBusinessId = entity.getSGroupBusinessId();
		this.sPackageDesc = entity.getSPackageDescEn();
		this.sPackageIcon = entity.getSPackageIcon();
		this.sPackageName = entity.getSPackageNameEn();
		this.sStartupPackageId = entity.getSStartupPackageId();
		this.sCurrencyId = entity.getsCurrencyId();
		this.sCurrencySymbol = sCurrencySymbol;
		this.amountClickStar = entity.getAmountClickStar();
	}

	public BigDecimal getnDiscountPrice() {
		return new BigDecimal(nDiscountPrice).setScale(2);
	}

	public void setnDiscountPrice(String nDiscountPrice) {
		this.nDiscountPrice = nDiscountPrice;
	}

	public Integer getnIsAvailable() {
		return nIsAvailable;
	}

	public void setnIsAvailable(Integer nIsAvailable) {
		this.nIsAvailable = nIsAvailable;
	}

	public BigDecimal getnPrice() {
		return new BigDecimal(nPrice).setScale(2);
	}

	public void setnPrice(String nPrice) {
		this.nPrice = nPrice;
	}

	public BigDecimal getnSalePrice() {
		return new BigDecimal(nSalePrice).setScale(2);
	}

	public void setnSalePrice(String nSalePrice) {
		this.nSalePrice = nSalePrice;
	}

	public String getsGroupBusinessId() {
		return sGroupBusinessId;
	}

	public void setsGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public String getsPackageDesc() {
		return sPackageDesc;
	}

	public void setsPackageDesc(String sPackageDesc) {
		this.sPackageDesc = sPackageDesc;
	}

	public String getsPackageIcon() {
		String imageUrl = Parameter.TABLE_STARTUP_PACKAGE + "/" + this.sStartupPackageId;
		return sPackageIcon == null ? "" : imageUrl;
	}

	public void setsPackageIcon(String sPackageIcon) {
		this.sPackageIcon = sPackageIcon;
	}

	public String getsPackageName() {
		return sPackageName;
	}

	public void setsPackageName(String sPackageName) {
		this.sPackageName = sPackageName;
	}

	public String getsStartupPackageId() {
		return sStartupPackageId;
	}

	public void setsStartupPackageId(String sStartupPackageId) {
		this.sStartupPackageId = sStartupPackageId;
	}

	public String getsCurrencyId() {
		return sCurrencyId;
	}

	public void setsCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getsCurrencySymbol() {
		return sCurrencySymbol;
	}

	public void setsCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}

	
}