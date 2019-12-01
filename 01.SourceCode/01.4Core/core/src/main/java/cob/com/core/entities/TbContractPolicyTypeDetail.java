package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_contract_policy_type_detail database table.
 * 
 */
@Entity
@Table(name="tb_contract_policy_type_detail")
@NamedQuery(name="TbContractPolicyTypeDetail.findAll", query="SELECT t FROM TbContractPolicyTypeDetail t")
public class TbContractPolicyTypeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="m_price")
	private BigDecimal mPrice;

	@Column(name="n_count_from")
	private Integer nCountFrom;

	@Column(name="n_count_to")
	private Integer nCountTo;

	@Column(name="s_contract_policy_type_detail_id")
	private String sContractPolicyTypeDetailId;

	@Column(name="s_contract_policy_type_id")
	private String sContractPolicyTypeId;

	public TbContractPolicyTypeDetail() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public BigDecimal getMPrice() {
		return this.mPrice;
	}

	public void setMPrice(BigDecimal mPrice) {
		this.mPrice = mPrice;
	}

	public Integer getNCountFrom() {
		return this.nCountFrom;
	}

	public void setNCountFrom(Integer nCountFrom) {
		this.nCountFrom = nCountFrom;
	}

	public Integer getNCountTo() {
		return this.nCountTo;
	}

	public void setNCountTo(Integer nCountTo) {
		this.nCountTo = nCountTo;
	}

	public String getSContractPolicyTypeDetailId() {
		return this.sContractPolicyTypeDetailId;
	}

	public void setSContractPolicyTypeDetailId(String sContractPolicyTypeDetailId) {
		this.sContractPolicyTypeDetailId = sContractPolicyTypeDetailId;
	}

	public String getSContractPolicyTypeId() {
		return this.sContractPolicyTypeId;
	}

	public void setSContractPolicyTypeId(String sContractPolicyTypeId) {
		this.sContractPolicyTypeId = sContractPolicyTypeId;
	}

}