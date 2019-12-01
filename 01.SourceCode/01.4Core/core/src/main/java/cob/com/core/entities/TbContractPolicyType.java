package cob.com.core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tb_contract_policy_type database table.
 * 
 */
@Entity
@Table(name = "tb_contract_policy_type")
@NamedQuery(name = "TbContractPolicyType.findAll", query = "SELECT t FROM TbContractPolicyType t")
public class TbContractPolicyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name = "m_from_amount")
	private Integer mFromAmount;

	@Column(name = "m_to_amount")
	private Integer mToAmount;

	@Column(name = "n_is_charge_in_transaction")
	private Integer nIsChargeInTransaction;

	@Column(name = "n_is_montlyfee_applied")
	private Integer nIsMontlyfeeApplied;

	@Column(name = "s_contract_policy_type_id")
	private String sContractPolicyTypeId;

	@Column(name = "s_policy_type_name_cn")
	private String sPolicyTypeNameCn;

	@Column(name = "s_policy_type_name_en")
	private String sPolicyTypeNameEn;

	@Column(name = "s_policy_type_name_vn")
	private String sPolicyTypeNameVn;

	public TbContractPolicyType() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getMFromAmount() {
		return this.mFromAmount;
	}

	public void setMFromAmount(Integer mFromAmount) {
		this.mFromAmount = mFromAmount;
	}

	public Integer getMToAmount() {
		return this.mToAmount;
	}

	public void setMToAmount(Integer mToAmount) {
		this.mToAmount = mToAmount;
	}

	public Integer getNIsChargeInTransaction() {
		return this.nIsChargeInTransaction;
	}

	public void setNIsChargeInTransaction(Integer nIsChargeInTransaction) {
		this.nIsChargeInTransaction = nIsChargeInTransaction;
	}

	public Integer getNIsMontlyfeeApplied() {
		return this.nIsMontlyfeeApplied;
	}

	public void setNIsMontlyfeeApplied(Integer nIsMontlyfeeApplied) {
		this.nIsMontlyfeeApplied = nIsMontlyfeeApplied;
	}

	public String getSContractPolicyTypeId() {
		return this.sContractPolicyTypeId;
	}

	public void setSContractPolicyTypeId(String sContractPolicyTypeId) {
		this.sContractPolicyTypeId = sContractPolicyTypeId;
	}

	public String getSPolicyTypeNameCn() {
		return this.sPolicyTypeNameCn;
	}

	public void setSPolicyTypeNameCn(String sPolicyTypeNameCn) {
		this.sPolicyTypeNameCn = sPolicyTypeNameCn;
	}

	public String getSPolicyTypeNameEn() {
		return this.sPolicyTypeNameEn;
	}

	public void setSPolicyTypeNameEn(String sPolicyTypeNameEn) {
		this.sPolicyTypeNameEn = sPolicyTypeNameEn;
	}

	public String getSPolicyTypeNameVn() {
		return this.sPolicyTypeNameVn;
	}

	public void setSPolicyTypeNameVn(String sPolicyTypeNameVn) {
		this.sPolicyTypeNameVn = sPolicyTypeNameVn;
	}

}