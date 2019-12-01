package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_promotion_history database table.
 * 
 */
@Entity
@Table(name="tb_promotion_history")
@NamedQuery(name="TbPromotionHistory.findAll", query="SELECT t FROM TbPromotionHistory t")
public class TbPromotionHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="d_createddate")
	private Timestamp dCreateddate;

	@Column(name="n_status")
	private Integer nStatus;

	@Column(name="s_loyalty_program_id")
	private String sLoyaltyProgramId;

	@Column(name="s_user_id")
	private String sUserId;

	@Column(name="s_voucher_code")
	private String sVoucherCode;

	@Column(name="s_voucher_id")
	private String sVoucherId;

	@Column(name="s_voucher_serial")
	private String sVoucherSerial;

	public TbPromotionHistory() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Timestamp getDCreateddate() {
		return this.dCreateddate;
	}

	public void setDCreateddate(Timestamp dCreateddate) {
		this.dCreateddate = dCreateddate;
	}

	public Integer getNStatus() {
		return this.nStatus;
	}

	public void setNStatus(Integer nStatus) {
		this.nStatus = nStatus;
	}

	public String getSLoyaltyProgramId() {
		return this.sLoyaltyProgramId;
	}

	public void setSLoyaltyProgramId(String sLoyaltyProgramId) {
		this.sLoyaltyProgramId = sLoyaltyProgramId;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getSVoucherCode() {
		return this.sVoucherCode;
	}

	public void setSVoucherCode(String sVoucherCode) {
		this.sVoucherCode = sVoucherCode;
	}

	public String getSVoucherId() {
		return this.sVoucherId;
	}

	public void setSVoucherId(String sVoucherId) {
		this.sVoucherId = sVoucherId;
	}

	public String getSVoucherSerial() {
		return this.sVoucherSerial;
	}

	public void setSVoucherSerial(String sVoucherSerial) {
		this.sVoucherSerial = sVoucherSerial;
	}

}