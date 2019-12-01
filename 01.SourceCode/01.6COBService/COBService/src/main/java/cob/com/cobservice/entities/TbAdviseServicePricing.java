package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_advise_service_pricing database table.
 * 
 */
@Entity
@Table(name="tb_advise_service_pricing")
@NamedQuery(name="TbAdviseServicePricing.findAll", query="SELECT t FROM TbAdviseServicePricing t")
public class TbAdviseServicePricing implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_advise_service_pricing_id")
	private String sAdviseServicePricingId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_from_date")
	private Date dFromDate;

	@Temporal(TemporalType.DATE)
	@Column(name="d_to_date")
	private Date dToDate;

	@Column(name="n_discount_amount")
	private String nDiscountAmount;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_is_available")
	private Integer nIsAvailable;

	@Column(name="n_price")
	private String nPrice;

	@Column(name="n_qty")
	private Integer nQty;

	@Column(name="n_saleprice")
	private String nSaleprice;

	@Column(name="s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name="s_service_unit_id")
	private String sServiceUnitId;
	
	@Column(name="s_currency_id")
	private String sCurrencyId;

	public TbAdviseServicePricing() {
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

	public String getNDiscountAmount() {
		return this.nDiscountAmount;
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

	public String getNPrice() {
		return this.nPrice;
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

	public String getNSaleprice() {
		return this.nSaleprice;
	}

	public void setNSaleprice(String nSaleprice) {
		this.nSaleprice = nSaleprice;
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