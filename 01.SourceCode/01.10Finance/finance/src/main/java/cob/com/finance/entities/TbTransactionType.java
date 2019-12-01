package cob.com.finance.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_transaction_type database table.
 * 
 */
@Entity
@Table(name="tb_transaction_type")
@NamedQuery(name="TbTransactionType.findAll", query="SELECT t FROM TbTransactionType t")
public class TbTransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="s_transaction_type_cn_name")
	private String sTransactionTypeCnName;

	@Column(name="s_transaction_type_en_name")
	private String sTransactionTypeEnName;

	@Column(name="s_transaction_type_id")
	private String sTransactionTypeId;

	@Column(name="s_transaction_type_vn_name")
	private String sTransactionTypeVnName;

	public TbTransactionType() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSTransactionTypeCnName() {
		return this.sTransactionTypeCnName;
	}

	public void setSTransactionTypeCnName(String sTransactionTypeCnName) {
		this.sTransactionTypeCnName = sTransactionTypeCnName;
	}

	public String getSTransactionTypeEnName() {
		return this.sTransactionTypeEnName;
	}

	public void setSTransactionTypeEnName(String sTransactionTypeEnName) {
		this.sTransactionTypeEnName = sTransactionTypeEnName;
	}

	public String getSTransactionTypeId() {
		return this.sTransactionTypeId;
	}

	public void setSTransactionTypeId(String sTransactionTypeId) {
		this.sTransactionTypeId = sTransactionTypeId;
	}

	public String getSTransactionTypeVnName() {
		return this.sTransactionTypeVnName;
	}

	public void setSTransactionTypeVnName(String sTransactionTypeVnName) {
		this.sTransactionTypeVnName = sTransactionTypeVnName;
	}

}