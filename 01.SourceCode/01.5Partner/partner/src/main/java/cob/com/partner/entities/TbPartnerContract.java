package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_partner_contract database table.
 * 
 */
@Entity
@Table(name="tb_partner_contract")
@NamedQuery(name="TbPartnerContract.findAll", query="SELECT t FROM TbPartnerContract t")
public class TbPartnerContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_contract_number")
	private String sContractNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="d_from_date")
	private Date dFromDate;

	@Temporal(TemporalType.DATE)
	@Column(name="d_to_date")
	private Date dToDate;

	@Column(name="n_contract_duration")
	private Integer nContractDuration;
	
	@Column(name="n_percent_charge_per_txn")
	private Integer nPercentChargePerTxn;

	@Column(name="n_total")
	private Integer nTotal;

	@Column(name="s_business_service_id")
	private String sBusinessServiceId;

	@Column(name="s_contract_policy_type_id")
	private String sContractPolicyTypeId;

	@Column(name="s_contract_unit")
	private String sContractUnit;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_partner_signature")
	private String sPartnerSignature;

	@Column(name="s_saleman_id")
	private String sSalemanId;

	public TbPartnerContract() {
	}

	public String getSContractNumber() {
		return this.sContractNumber;
	}

	public void setSContractNumber(String sContractNumber) {
		this.sContractNumber = sContractNumber;
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

	public Integer getNContractDuration() {
		return this.nContractDuration;
	}

	public void setNContractDuration(Integer nContractDuration) {
		this.nContractDuration = nContractDuration;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNPercentChargePerTxn() {
		return this.nPercentChargePerTxn;
	}

	public void setNPercentChargePerTxn(Integer nPercentChargePerTxn) {
		this.nPercentChargePerTxn = nPercentChargePerTxn;
	}

	public Integer getNTotal() {
		return this.nTotal;
	}

	public void setNTotal(Integer nTotal) {
		this.nTotal = nTotal;
	}

	public String getSBusinessServiceId() {
		return this.sBusinessServiceId;
	}

	public void setSBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSContractPolicyTypeId() {
		return this.sContractPolicyTypeId;
	}

	public void setSContractPolicyTypeId(String sContractPolicyTypeId) {
		this.sContractPolicyTypeId = sContractPolicyTypeId;
	}

	public String getSContractUnit() {
		return this.sContractUnit;
	}

	public void setSContractUnit(String sContractUnit) {
		this.sContractUnit = sContractUnit;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSPartnerSignature() {
		return this.sPartnerSignature;
	}

	public void setSPartnerSignature(String sPartnerSignature) {
		this.sPartnerSignature = sPartnerSignature;
	}

	public String getSSalemanId() {
		return this.sSalemanId;
	}

	public void setSSalemanId(String sSalemanId) {
		this.sSalemanId = sSalemanId;
	}

}