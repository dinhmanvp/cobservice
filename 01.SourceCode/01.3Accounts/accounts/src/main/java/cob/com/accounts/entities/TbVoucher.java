package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_voucher database table.
 * 
 */
@Entity
@Table(name="tb_voucher")
@NamedQuery(name="TbVoucher.findAll", query="SELECT t FROM TbVoucher t")
public class TbVoucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="d_from_date")
	private Timestamp dFromDate;

	@Column(name="d_to_date")
	private Timestamp dToDate;

	@Column(name="n_quantity")
	private Integer nQuantity;

	@Column(name="n_status")
	private Integer nStatus;

	@Column(name="s_voucher_id")
	private String sVoucherId;

	public TbVoucher() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Timestamp getDFromDate() {
		return this.dFromDate;
	}

	public void setDFromDate(Timestamp dFromDate) {
		this.dFromDate = dFromDate;
	}

	public Timestamp getDToDate() {
		return this.dToDate;
	}

	public void setDToDate(Timestamp dToDate) {
		this.dToDate = dToDate;
	}

	public Integer getNQuantity() {
		return this.nQuantity;
	}

	public void setNQuantity(Integer nQuantity) {
		this.nQuantity = nQuantity;
	}

	public Integer getNStatus() {
		return this.nStatus;
	}

	public void setNStatus(Integer nStatus) {
		this.nStatus = nStatus;
	}

	public String getSVoucherId() {
		return this.sVoucherId;
	}

	public void setSVoucherId(String sVoucherId) {
		this.sVoucherId = sVoucherId;
	}

}