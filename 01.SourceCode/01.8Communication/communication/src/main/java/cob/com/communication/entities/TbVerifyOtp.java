package cob.com.communication.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_verify_otp database table.
 * 
 */
@Entity
@Table(name="tb_verify_otp")
@NamedQuery(name="TbVerifyOtp.findAll", query="SELECT t FROM TbVerifyOtp t")
public class TbVerifyOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbVerifyOtpPK id;

	@Column(name="d_created_date")
	private Date dCreatedDate;

	@Column(name="n_is_verified")
	private Integer nIsVerified;

	@Column(name="s_otp")
	private String sOtp;

	public TbVerifyOtp() {
	}

	public TbVerifyOtpPK getId() {
		return this.id;
	}

	public void setId(TbVerifyOtpPK id) {
		this.id = id;
	}

	public Date getDCreatedDate() {
		return this.dCreatedDate;
	}

	public void setDCreatedDate(Date dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	public Integer getNIsVerified() {
		return this.nIsVerified;
	}

	public void setNIsVerified(Integer nIsVerified) {
		this.nIsVerified = nIsVerified;
	}

	public String getSOtp() {
		return this.sOtp;
	}

	public void setSOtp(String sOtp) {
		this.sOtp = sOtp;
	}

}