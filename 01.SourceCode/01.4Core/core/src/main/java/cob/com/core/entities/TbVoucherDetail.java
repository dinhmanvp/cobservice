package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_voucher_details database table.
 * 
 */
@Entity
@Table(name="tb_voucher_details")
@NamedQuery(name="TbVoucherDetail.findAll", query="SELECT t FROM TbVoucherDetail t")
public class TbVoucherDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_status")
	private Integer nStatus;

	@Column(name="s_description")
	private String sDescription;

	@Column(name="s_voucher_id")
	private String sVoucherId;

	@Column(name="s_voucher_serial")
	private String sVoucherSerial;

	public TbVoucherDetail() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNStatus() {
		return this.nStatus;
	}

	public void setNStatus(Integer nStatus) {
		this.nStatus = nStatus;
	}

	public String getSDescription() {
		return this.sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
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