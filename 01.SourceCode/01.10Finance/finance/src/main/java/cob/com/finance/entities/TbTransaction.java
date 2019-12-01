package cob.com.finance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_transactions database table.
 * 
 */
@Entity
@Table(name="tb_transactions")
@NamedQuery(name="TbTransaction.findAll", query="SELECT t FROM TbTransaction t")
public class TbTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="d_cashback_approved_date")
	private Date dCashbackApprovedDate;

	@Column(name="d_cashback_validate_date")
	private Date dCashbackValidateDate;

	@Column(name="d_trans_confirm_date")
	private Date dTransConfirmDate;

	@Column(name="d_transaction_date")
	private Date dTransactionDate;

	@Column(name="n_amount")
	private BigDecimal nAmount;

	@Column(name="n_cashback_amt")
	private BigDecimal nCashbackAmt;

	@Column(name="n_otp_auth")
	private String nOtpAuth;

	@Column(name="n_otp_confirmed")
	private String nOtpConfirmed;

	@Column(name="n_otp_confirmed_times")
	private Integer nOtpConfirmedTimes;

	@Column(name="n_transaction_status_id")
	private Integer nTransactionStatusId;

	@Column(name="s_cashback_approved_by")
	private String sCashbackApprovedBy;

	@Column(name="s_cashback_validate_by")
	private String sCashbackValidateBy;

	@Column(name="s_channel_id")
	private String sChannelId;

	@Column(name="s_currency_id")
	private String sCurrencyId;

	@Column(name="s_e_contract_no")
	private String sEContractNo;

	@Column(name="s_from_user_id")
	private String sFromUserId;

	@Column(name="s_pocket_id")
	private String sPocketId;

	@Column(name="s_to_user_id")
	private String sToUserId;

	@Column(name="s_trans_message")
	private String sTransMessage;

	@Column(name="s_transaction_id")
	private String sTransactionId;

	@Column(name="s_transaction_type_id")
	private String sTransactionTypeId;

	public TbTransaction() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDCashbackApprovedDate() {
		return this.dCashbackApprovedDate;
	}

	public void setDCashbackApprovedDate(Date dCashbackApprovedDate) {
		this.dCashbackApprovedDate = dCashbackApprovedDate;
	}

	public Date getDCashbackValidateDate() {
		return this.dCashbackValidateDate;
	}

	public void setDCashbackValidateDate(Date dCashbackValidateDate) {
		this.dCashbackValidateDate = dCashbackValidateDate;
	}

	public Date getDTransConfirmDate() {
		return this.dTransConfirmDate;
	}

	public void setDTransConfirmDate(Date dTransConfirmDate) {
		this.dTransConfirmDate = dTransConfirmDate;
	}

	public Date getDTransactionDate() {
		return this.dTransactionDate;
	}

	public void setDTransactionDate(Date dTransactionDate) {
		this.dTransactionDate = dTransactionDate;
	}

	public BigDecimal getNAmount() {
		return this.nAmount;
	}

	public void setNAmount(BigDecimal nAmount) {
		this.nAmount = nAmount;
	}

	public BigDecimal getNCashbackAmt() {
		return this.nCashbackAmt;
	}

	public void setNCashbackAmt(BigDecimal nCashbackAmt) {
		this.nCashbackAmt = nCashbackAmt;
	}

	public String getNOtpAuth() {
		return this.nOtpAuth;
	}

	public void setNOtpAuth(String nOtpAuth) {
		this.nOtpAuth = nOtpAuth;
	}

	public String getNOtpConfirmed() {
		return this.nOtpConfirmed;
	}

	public void setNOtpConfirmed(String nOtpConfirmed) {
		this.nOtpConfirmed = nOtpConfirmed;
	}

	public Integer getNOtpConfirmedTimes() {
		return this.nOtpConfirmedTimes;
	}

	public void setNOtpConfirmedTimes(Integer nOtpConfirmedTimes) {
		this.nOtpConfirmedTimes = nOtpConfirmedTimes;
	}

	public Integer getNTransactionStatusId() {
		return this.nTransactionStatusId;
	}

	public void setNTransactionStatusId(Integer nTransactionStatusId) {
		this.nTransactionStatusId = nTransactionStatusId;
	}

	public String getSCashbackApprovedBy() {
		return this.sCashbackApprovedBy;
	}

	public void setSCashbackApprovedBy(String sCashbackApprovedBy) {
		this.sCashbackApprovedBy = sCashbackApprovedBy;
	}

	public String getSCashbackValidateBy() {
		return this.sCashbackValidateBy;
	}

	public void setSCashbackValidateBy(String sCashbackValidateBy) {
		this.sCashbackValidateBy = sCashbackValidateBy;
	}

	public String getSChannelId() {
		return this.sChannelId;
	}

	public void setSChannelId(String sChannelId) {
		this.sChannelId = sChannelId;
	}

	public String getSCurrencyId() {
		return this.sCurrencyId;
	}

	public void setSCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getSEContractNo() {
		return this.sEContractNo;
	}

	public void setSEContractNo(String sEContractNo) {
		this.sEContractNo = sEContractNo;
	}

	public String getSFromUserId() {
		return this.sFromUserId;
	}

	public void setSFromUserId(String sFromUserId) {
		this.sFromUserId = sFromUserId;
	}

	public String getSPocketId() {
		return this.sPocketId;
	}

	public void setSPocketId(String sPocketId) {
		this.sPocketId = sPocketId;
	}

	public String getSToUserId() {
		return this.sToUserId;
	}

	public void setSToUserId(String sToUserId) {
		this.sToUserId = sToUserId;
	}

	public String getSTransMessage() {
		return this.sTransMessage;
	}

	public void setSTransMessage(String sTransMessage) {
		this.sTransMessage = sTransMessage;
	}

	public String getSTransactionId() {
		return this.sTransactionId;
	}

	public void setSTransactionId(String sTransactionId) {
		this.sTransactionId = sTransactionId;
	}

	public String getSTransactionTypeId() {
		return this.sTransactionTypeId;
	}

	public void setSTransactionTypeId(String sTransactionTypeId) {
		this.sTransactionTypeId = sTransactionTypeId;
	}

}