package cob.com.finance.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_fee_setting database table.
 * 
 */
@Entity
@Table(name="tb_fee_setting")
@NamedQuery(name="TbFeeSetting.findAll", query="SELECT t FROM TbFeeSetting t")
public class TbFeeSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_cashback")
	private BigDecimal nCashback;

	@Column(name="n_fee_charge")
	private BigDecimal nFeeCharge;

	@Column(name="n_total_amount")
	private BigDecimal nTotalAmount;

	@Column(name="s_currency_id")
	private String sCurrencyId;

	@Column(name="s_transaction_name")
	private String sTransactionName;

	public TbFeeSetting() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public BigDecimal getNCashback() {
		return this.nCashback;
	}

	public void setNCashback(BigDecimal nCashback) {
		this.nCashback = nCashback;
	}

	public BigDecimal getNFeeCharge() {
		return this.nFeeCharge;
	}

	public void setNFeeCharge(BigDecimal nFeeCharge) {
		this.nFeeCharge = nFeeCharge;
	}

	public BigDecimal getNTotalAmount() {
		return this.nTotalAmount;
	}

	public void setNTotalAmount(BigDecimal nTotalAmount) {
		this.nTotalAmount = nTotalAmount;
	}

	public String getSCurrencyId() {
		return this.sCurrencyId;
	}

	public void setSCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getSTransactionName() {
		return this.sTransactionName;
	}

	public void setSTransactionName(String sTransactionName) {
		this.sTransactionName = sTransactionName;
	}

}