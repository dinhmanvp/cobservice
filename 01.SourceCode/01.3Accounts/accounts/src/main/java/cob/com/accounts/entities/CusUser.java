package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cus_user database table.
 * 
 */
@Entity
@Table(name="cus_user")
@NamedQuery(name="CusUser.findAll", query="SELECT c FROM CusUser c")
public class CusUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	//@SequenceGenerator(name = "cususer_sequence", sequenceName = "CusUser_n_id_seq", allocationSize = 1, initialValue = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CusUser_sequence")
	private Integer nId;
	
	@Column(name="b_avatar")
	private String bAvatar;

	@Column(name="b_password")
	private String bPassword;

	@Temporal(TemporalType.DATE)
	@Column(name="d_birthday")
	private Date dBirthday;

	@Temporal(TemporalType.DATE)
	@Column(name="d_lastlogin")
	private Date dLastlogin;

	@Temporal(TemporalType.DATE)
	@Column(name="d_lastlogout")
	private Date dLastlogout;

	@Column(name="n_gender")
	private Integer nGender;

	@Column(name="s_address_number")
	private String sAddressNumber;

	@Column(name="s_city_id")
	private String sCityId;

	@Column(name="s_country_id")
	private String sCountryId;

	@Column(name="s_customer_id")
	private String sCustomerId;

	@Column(name="s_email")
	private String sEmail;

	@Column(name="s_firstname")
	private String sFirstname;

	@Column(name="s_lastname")
	private String sLastname;

	@Column(name="s_phone")
	private String sPhone;

	@Column(name="s_user_id")
	private String sUserId;

	@Column(name="s_username")
	private String sUsername;

	public CusUser() {
	}

	public String getBAvatar() {
		return this.bAvatar;
	}

	public void setBAvatar(String bAvatar) {
		this.bAvatar = bAvatar;
	}

	public String getBPassword() {
		return this.bPassword;
	}

	public void setBPassword(String bPassword) {
		this.bPassword = bPassword;
	}

	public Date getDBirthday() {
		return this.dBirthday;
	}

	public void setDBirthday(Date dBirthday) {
		this.dBirthday = dBirthday;
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

	public Integer getNGender() {
		return this.nGender;
	}

	public void setNGender(Integer nGender) {
		this.nGender = nGender;
	}

	public String getSAddressNumber() {
		return this.sAddressNumber;
	}

	public void setSAddressNumber(String sAddressNumber) {
		this.sAddressNumber = sAddressNumber;
	}

	public String getSCityId() {
		return this.sCityId;
	}

	public void setSCityId(String sCityId) {
		this.sCityId = sCityId;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
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

	public String getSFirstname() {
		return this.sFirstname;
	}

	public void setSFirstname(String sFirstname) {
		this.sFirstname = sFirstname;
	}

	public String getSLastname() {
		return this.sLastname;
	}

	public void setSLastname(String sLastname) {
		this.sLastname = sLastname;
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