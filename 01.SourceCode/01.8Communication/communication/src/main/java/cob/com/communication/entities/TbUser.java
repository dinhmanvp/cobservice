package cob.com.communication.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the tb_user database table.
 * 
 */
@Entity
@Table(name = "tb_user",schema = "mdl_account")
@NamedQuery(name = "TbUser.findAll", query = "SELECT t FROM TbUser t")
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	@SequenceGenerator(name = "tb_user_sequence", sequenceName = "tb_user_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_user_sequence")
	private Integer nId;

	@Column(name = "b_password")
	private String bPassword;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_lastlogin")
	private Date dLastlogin;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_lastlogout")
	private Date dLastlogout;

	@Column(name = "s_customer_id")
	private String sCustomerId;

	@Column(name = "s_email")
	private String sEmail;

	@Column(name = "s_phone")
	private String sPhone;

	@Column(name = "s_user_id")
	private String sUserId;

	@Column(name = "s_username")
	private String sUsername;

	@Column(name = "is_validate")
	private Integer isValiDate;

	@Column(name = "is_change_password")
	private Integer ischangePassword;

	@Column(name = "s_key")
	private String sKey;

	public TbUser() {
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public Integer getischangePassword() {
		return this.ischangePassword;
	}

	public void setischangePassword(Integer ischangePassword) {
		this.ischangePassword = ischangePassword;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getisValiDate() {
		return this.isValiDate;
	}

	public void setisValiDate(Integer isValiDate) {
		this.isValiDate = isValiDate;
	}

	public String getBPassword() {
		return this.bPassword;
	}

	public void setBPassword(String bPassword) {
		this.bPassword = bPassword;
	}

	public Date getDLastlogin() {
		return this.dLastlogin;
	}

	public void setDLastlogin(Date dLastlogin) {
		this.dLastlogin = dLastlogin;
	}

	public Date getDLastlogout() {
		return this.dLastlogout;
	}

	public void setDLastlogout(Date dLastlogout) {
		this.dLastlogout = dLastlogout;
	}

	public String getSCustomerId() {
		return this.sCustomerId;
	}

	public void setSCustomerId(String sCustomerId) {
		this.sCustomerId = sCustomerId;
	}

	public String getSEmail() {
		return this.sEmail;
	}

	public void setSEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getSPhone() {
		return this.sPhone;
	}

	public void setSPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getSUsername() {
		return this.sUsername;
	}

	public void setSUsername(String sUsername) {
		this.sUsername = sUsername;
	}

}