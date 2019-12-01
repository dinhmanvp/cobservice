package cob.com.cobservice.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cob.com.cobservice.entities.TbAdviseServicePricing;
import cob.com.cobservice.ws.param.Parameter;

/**
 * The persistent class for the tb_advise_service_pricing database table.
 * 
 */
@Entity
public class TbAdviseServicePricingModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "s_advise_service_pricing_id")
	private String sAdviseServicePricingId;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_from_date")
	private Date dFromDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_to_date")
	private Date dToDate;

	@Column(name = "n_discount_amount")
	private String nDiscountAmount;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "n_is_available")
	private Integer nIsAvailable;

	@Column(name = "n_price")
	private String nPrice;

	@Column(name = "n_qty")
	private Integer nQty;

	@Column(name = "n_saleprice")
	private String nSaleprice;

	@Column(name = "s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name = "s_service_unit_id")
	private String sServiceUnitId;

	@Column(name = "s_currency_id")
	private String sCurrencyId;

	@Column(name = "s_currency_symbol")
	private String sCurrencySymbol;

	@Column(name = "n_sale_price_cob")
	private BigDecimal nSalePriceCob;

	@Column(name = "s_advise_service_name")
	private String sAdviseServiceName;

	@Column(name = "s_advise_service_shortdesc")
	private String sAdviseServiceShortdesc;

	@Column(name = "s_advise_service_icon")
	private String sAdviseServiceIcon;

	@Column(name = "n_advise_service_rating")
	private BigDecimal nAdviseServiceRating;

	@Column(name = "s_unit_name")
	private String sUnitName;

	@Column(name = "s_duration_type")
	private String sDurationTypeName;

	public BigDecimal getnAdviseServiceRating() {
		return nAdviseServiceRating;
	}

	public void setnAdviseServiceRating(BigDecimal nAdviseServiceRating) {
		this.nAdviseServiceRating = nAdviseServiceRating;
	}

	public String getsAdviseServiceName() {
		return sAdviseServiceName;
	}

	public void setsAdviseServiceName(String sAdviseServiceName) {
		this.sAdviseServiceName = sAdviseServiceName;
	}

	public String getsDurationTypeName() {
		return sDurationTypeName;
	}

	public void setsDurationTypeName(String sDurationTypeName) {
		this.sDurationTypeName = sDurationTypeName;
	}

	public String getsUnitName() {
		return sUnitName;
	}

	public void setsUnitName(String sUnitName) {
		this.sUnitName = sUnitName;
	}

	public String getsAdviseServiceIcon() {
		String imageUrl = Parameter.TABLE_ADVISE_SERVICE + "/" + this.sAdviseServiceId;
		return sAdviseServiceIcon == null ? "" :  imageUrl;
	}

	public void setsAdviseServiceIcon(String sAdviseServiceIcon) {		
		this.sAdviseServiceIcon = sAdviseServiceIcon;
	}

	public String getsAdviseServiceShortdesc() {
		return sAdviseServiceShortdesc;
	}

	public void setsAdviseServiceShortdesc(String sAdviseServiceShortdesc) {
		this.sAdviseServiceShortdesc = sAdviseServiceShortdesc;
	}

	public BigDecimal getnSalePriceCob() {
		return nSalePriceCob.setScale(2, RoundingMode.HALF_UP);
	}

	public void setnSalePriceCob(BigDecimal nSalePriceCob) {
		this.nSalePriceCob = nSalePriceCob;
	}

	public TbAdviseServicePricingModel() {
	}

	public TbAdviseServicePricingModel(TbAdviseServicePricing entity, String sCurrencySymbol) {
		this.sAdviseServicePricingId = entity.getSAdviseServicePricingId();
		this.dFromDate = entity.getDFromDate();
		this.dToDate = entity.getDToDate();
		this.nDiscountAmount = entity.getNDiscountAmount();
		this.nId = entity.getNId();
		this.nIsAvailable = entity.getNIsAvailable();
		this.nPrice = entity.getNPrice();
		this.nQty = entity.getNQty();
		this.nSaleprice = entity.getNSaleprice();
		this.sAdviseServiceId = entity.getSAdviseServiceId();
		this.sServiceUnitId = entity.getSServiceUnitId();
		this.sCurrencyId = entity.getsCurrencyId();
		this.sCurrencySymbol = sCurrencySymbol;
	}

	public String getSAdviseServicePricingId() {
		return this.sAdviseServicePricingId;
	}

	public void setSAdviseServicePricingId(String sAdviseServicePricingId) {
		this.sAdviseServicePricingId = sAdviseServicePricingId;
	}

	public Date getDFromDate() {
		return this.dFromDate;
	}

	public void setDFromDate(Date dFromDate) {
		this.dFromDate = dFromDate;
	}

	public Date getDToDate() {
		return this.dToDate;
	}

	public void setDToDate(Date dToDate) {
		this.dToDate = dToDate;
	}

	public BigDecimal getNDiscountAmount() {
		return new BigDecimal(this.nDiscountAmount).setScale(2, RoundingMode.HALF_UP);
	}

	public void setNDiscountAmount(String nDiscountAmount) {
		this.nDiscountAmount = nDiscountAmount;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsAvailable() {
		return this.nIsAvailable;
	}

	public void setNIsAvailable(Integer nIsAvailable) {
		this.nIsAvailable = nIsAvailable;
	}

	public BigDecimal getNPrice() {
		return new BigDecimal(this.nPrice).setScale(2, RoundingMode.HALF_UP);
	}

	public void setNPrice(String nPrice) {
		this.nPrice = nPrice;
	}

	public Integer getNQty() {
		return this.nQty;
	}

	public void setNQty(Integer nQty) {
		this.nQty = nQty;
	}

	public BigDecimal getNSaleprice() {
		return new BigDecimal(this.nSaleprice).setScale(2, RoundingMode.HALF_UP);
	}

	public void setNSaleprice(String nSaleprice) {
		this.nSaleprice = nSaleprice;
	}

	public String getsCurrencySymbol() {
		return sCurrencySymbol;
	}

	public void setsCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}

	public String getSAdviseServiceId() {
		return this.sAdviseServiceId;
	}

	public void setSAdviseServiceId(String sAdviseServiceId) {
		this.sAdviseServiceId = sAdviseServiceId;
	}

	public String getSServiceUnitId() {
		return this.sServiceUnitId;
	}

	public void setSServiceUnitId(String sServiceUnitId) {
		this.sServiceUnitId = sServiceUnitId;
	}

	public String getsCurrencyId() {
		return sCurrencyId;
	}

	public void setsCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}
}