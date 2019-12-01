package cob.com.cobgateway.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_ma_user_authentication database table.
 * 
 */
@Entity
@Table(name="tb_ma_user_authentication", schema="mdl_gateway")
@NamedQuery(name="TbMaUserAuthentication.findAll", query="SELECT t FROM TbMaUserAuthentication t")
public class TbMaUserAuthentication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="bi_is_expired")
	private Integer biIsExpired;

	
	@Column(name="d_created_date")
	private Date dCreatedDate;

	@Column(name="s_private_token")
	private String sPrivateToken;

	@Column(name="s_public_token")
	private String sPublicToken;

	@Column(name="s_user_id")
	private String sUsername;
	
	@Column(name="s_channel_id")
	private String sChannelId;
	
	public TbMaUserAuthentication() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getBiIsExpired() {
		return this.biIsExpired;
	}

	public void setBiIsExpired(Integer biIsExpired) {
		this.biIsExpired = biIsExpired;
	}

	public Date getDCreatedDate() {
		return this.dCreatedDate;
	}

	public void setDCreatedDate(Date dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	public String getSPrivateToken() {
		return this.sPrivateToken;
	}

	public void setSPrivateToken(String sPrivateToken) {
		this.sPrivateToken = sPrivateToken;
	}

	public String getSPublicToken() {
		return this.sPublicToken;
	}

	public void setSPublicToken(String sPublicToken) {
		this.sPublicToken = sPublicToken;
	}

	public String getSUserId() {
		return this.sUsername;
	}

	public void setSUserId(String sUserId) {
		this.sUsername = sUserId;
	}

	public String getsChannelId() {
		return sChannelId;
	}

	public void setsChannelId(String sChannelId) {
		this.sChannelId = sChannelId;
	}

}