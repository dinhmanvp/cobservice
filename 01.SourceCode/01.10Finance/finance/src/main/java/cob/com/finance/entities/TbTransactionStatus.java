package cob.com.finance.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_transaction_status database table.
 * 
 */
@Entity
@Table(name="tb_transaction_status")
@NamedQuery(name="TbTransactionStatus.findAll", query="SELECT t FROM TbTransactionStatus t")
public class TbTransactionStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_transaction_status_id")
	private Integer nTransactionStatusId;

	@Column(name="s_trans_status_cn_name")
	private String sTransStatusCnName;

	@Column(name="s_trans_status_en_name")
	private String sTransStatusEnName;

	@Column(name="s_trans_status_vn_name")
	private String sTransStatusVnName;

	public TbTransactionStatus() {
	}

	public Integer getNTransactionStatusId() {
		return this.nTransactionStatusId;
	}

	public void setNTransactionStatusId(Integer nTransactionStatusId) {
		this.nTransactionStatusId = nTransactionStatusId;
	}

	public String getSTransStatusCnName() {
		return this.sTransStatusCnName;
	}

	public void setSTransStatusCnName(String sTransStatusCnName) {
		this.sTransStatusCnName = sTransStatusCnName;
	}

	public String getSTransStatusEnName() {
		return this.sTransStatusEnName;
	}

	public void setSTransStatusEnName(String sTransStatusEnName) {
		this.sTransStatusEnName = sTransStatusEnName;
	}

	public String getSTransStatusVnName() {
		return this.sTransStatusVnName;
	}

	public void setSTransStatusVnName(String sTransStatusVnName) {
		this.sTransStatusVnName = sTransStatusVnName;
	}

}