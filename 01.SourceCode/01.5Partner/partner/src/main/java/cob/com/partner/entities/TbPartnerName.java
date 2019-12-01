package cob.com.partner.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class TbPartnerName implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sPartnerNameCn;

	private String sPartnerNameEn;

	private String sPartnerNameVn;

	private String sPartnerId;

	public String getsPartnerNameCn() {
		return sPartnerNameCn;
	}

	public TbPartnerName(String sPartnerId, String sPartnerNameEn, String sPartnerNameVn, String sPartnerNameCn) {
		super();
		this.sPartnerNameCn = sPartnerNameCn;
		this.sPartnerNameEn = sPartnerNameEn;
		this.sPartnerNameVn = sPartnerNameVn;
		this.sPartnerId = sPartnerId;
	}

	public String getsPartnerNameEn() {
		return sPartnerNameEn;
	}

	public String getsPartnerNameVn() {
		return sPartnerNameVn;
	}

	public String getsPartnerId() {
		return sPartnerId;
	}

	public void setsPartnerNameCn(String sPartnerNameCn) {
		this.sPartnerNameCn = sPartnerNameCn;
	}

	public void setsPartnerNameEn(String sPartnerNameEn) {
		this.sPartnerNameEn = sPartnerNameEn;
	}

	public void setsPartnerNameVn(String sPartnerNameVn) {
		this.sPartnerNameVn = sPartnerNameVn;
	}

	public void setsPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	/**
	 * 
	 */

}
