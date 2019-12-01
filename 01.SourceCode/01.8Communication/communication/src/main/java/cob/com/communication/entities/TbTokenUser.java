package cob.com.communication.entities;

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
 * The persistent class for the tb_token_user database table.
 * 
 */
@Entity
@Table(name="tb_token_user")
@NamedQuery(name="TbTokenUser.findAll", query="SELECT t FROM TbTokenUser t")
public class TbTokenUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="d_created")
	private Date dCreated;

	@Column(name="s_token")
	private String sToken;

	@Column(name="s_username")
	private String sUsername;

	public TbTokenUser() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDCreated() {
		return this.dCreated;
	}

	public void setDCreated(Date dCreated) {
		this.dCreated = dCreated;
	}

	public String getSToken() {
		return this.sToken;
	}

	public void setSToken(String sToken) {
		this.sToken = sToken;
	}

	public String getSUsername() {
		return this.sUsername;
	}

	public void setSUsername(String sUsername) {
		this.sUsername = sUsername;
	}

}