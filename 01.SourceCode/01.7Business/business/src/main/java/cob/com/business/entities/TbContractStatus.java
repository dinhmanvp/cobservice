package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_contract_status database table.
 * 
 */
@Entity
@Table(name="tb_contract_status")
public class TbContractStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_contract_status_id")
	private Integer nContractStatusId;

	@Column(name="s_contract_status_name_cn")
	private String sContractStatusNameCn;

	@Column(name="s_contract_status_name_en")
	private String sContractStatusNameEn;

	@Column(name="s_contract_status_name_vn")
	private String sContractStatusNameVn;

	public TbContractStatus() {
	}

	public Integer getNContractStatusId() {
		return this.nContractStatusId;
	}

	public void setNContractStatusId(Integer nContractStatusId) {
		this.nContractStatusId = nContractStatusId;
	}

	public String getSContractStatusNameCn() {
		return this.sContractStatusNameCn;
	}

	public void setSContractStatusNameCn(String sContractStatusNameCn) {
		this.sContractStatusNameCn = sContractStatusNameCn;
	}

	public String getSContractStatusNameEn() {
		return this.sContractStatusNameEn;
	}

	public void setSContractStatusNameEn(String sContractStatusNameEn) {
		this.sContractStatusNameEn = sContractStatusNameEn;
	}

	public String getSContractStatusNameVn() {
		return this.sContractStatusNameVn;
	}

	public void setSContractStatusNameVn(String sContractStatusNameVn) {
		this.sContractStatusNameVn = sContractStatusNameVn;
	}

}