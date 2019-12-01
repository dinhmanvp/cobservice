package cob.com.core.ws.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PromotionModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="n_id")
	private Integer nid;
	
	@Column(name="s_voucher_code")
	private String svouchercode;
	
	@Column(name="s_voucher_serial")
	private String svoucherserial;

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getSvouchercode() {
		return svouchercode;
	}

	public void setSvouchercode(String svouchercode) {
		this.svouchercode = svouchercode;
	}

	public String getSvoucherserial() {
		return svoucherserial;
	}

	public void setSvoucherserial(String svoucherserial) {
		this.svoucherserial = svoucherserial;
	}

	public PromotionModel() {
		super();
	}	
	
}
