package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_esmart_contract database table.
 * 
 */
@Entity
@Table(name="tb_esmart_contract")
public class TbEsmartContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="s_econtract_no")
	private String sEcontractNo;

	@Temporal(TemporalType.DATE)
	@Column(name="d_create_date")
	private Date dCreateDate;

	@Column(name="n_amount")
	private BigDecimal nAmount;

	@Column(name="n_contract_status_id")
	private Integer nContractStatusId;

	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_taxamount")
	private BigDecimal nTaxamount;

	@Column(name="n_taxrate")
	private BigDecimal nTaxrate;

	@Column(name="s_buyer_partner_id")
	private String sBuyerPartnerId;

	@Column(name="s_buyer_user_id")
	private String sBuyerUserId;

	@Column(name="s_create_by")
	private String sCreateBy;

	@Column(name="s_order_number")
	private String sOrderNumber;

	@Column(name="s_seller_partner_id")
	private String sSellerPartnerId;

	@Column(name="s_seller_user_id")
	private String sSellerUserId;

	public TbEsmartContract() {
	}

	public String getSEcontractNo() {
		return this.sEcontractNo;
	}

	public void setSEcontractNo(String sEcontractNo) {
		this.sEcontractNo = sEcontractNo;
	}

	public Date getDCreateDate() {
		return this.dCreateDate;
	}

	public void setDCreateDate(Date dCreateDate) {
		this.dCreateDate = dCreateDate;
	}

	public BigDecimal getNAmount() {
		return this.nAmount;
	}

	public void setNAmount(BigDecimal nAmount) {
		this.nAmount = nAmount;
	}

	public Integer getNContractStatusId() {
		return this.nContractStatusId;
	}

	public void setNContractStatusId(Integer nContractStatusId) {
		this.nContractStatusId = nContractStatusId;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public BigDecimal getNTaxamount() {
		return this.nTaxamount;
	}

	public void setNTaxamount(BigDecimal nTaxamount) {
		this.nTaxamount = nTaxamount;
	}

	public BigDecimal getNTaxrate() {
		return this.nTaxrate;
	}

	public void setNTaxrate(BigDecimal nTaxrate) {
		this.nTaxrate = nTaxrate;
	}

	public String getSBuyerPartnerId() {
		return this.sBuyerPartnerId;
	}

	public void setSBuyerPartnerId(String sBuyerPartnerId) {
		this.sBuyerPartnerId = sBuyerPartnerId;
	}

	public String getSBuyerUserId() {
		return this.sBuyerUserId;
	}

	public void setSBuyerUserId(String sBuyerUserId) {
		this.sBuyerUserId = sBuyerUserId;
	}

	public String getSCreateBy() {
		return this.sCreateBy;
	}

	public void setSCreateBy(String sCreateBy) {
		this.sCreateBy = sCreateBy;
	}

	public String getSOrderNumber() {
		return this.sOrderNumber;
	}

	public void setSOrderNumber(String sOrderNumber) {
		this.sOrderNumber = sOrderNumber;
	}

	public String getSSellerPartnerId() {
		return this.sSellerPartnerId;
	}

	public void setSSellerPartnerId(String sSellerPartnerId) {
		this.sSellerPartnerId = sSellerPartnerId;
	}

	public String getSSellerUserId() {
		return this.sSellerUserId;
	}

	public void setSSellerUserId(String sSellerUserId) {
		this.sSellerUserId = sSellerUserId;
	}

}