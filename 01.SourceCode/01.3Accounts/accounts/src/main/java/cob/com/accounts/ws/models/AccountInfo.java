package cob.com.accounts.ws.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cob.com.accounts.ws.param.Parameter;

@Entity
public class AccountInfo {

	@Id
	@Column(name = "n_id")
	private Integer nId;

//	@Column(name = "b_password")
//	private String bPassword;

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
	
	@Column(name="b_avatar")
	private String bAvatar;
	
	@Column(name="s_currency_symbol")
	private String sCurrencySymbol;
	
	@Column(name="s_currency_image")
	private String sCurrencyImage;
	
	@JsonIgnoreProperties
	@Column(name="n_available_balance")
	private String nAvailablevBalance;
	
	@Column(name = "s_fullname")
	private String sFullname;
	
	@Column(name = "s_card_id_no")
	private String sCardIdNo;
	
	@Transient
	@Column(name = "n_hash_partner")
	private Integer nhashpartner;
	
//	@Transient
//	@JsonInclude()
//	private BigDecimal availableBalance;

	public Integer getNhashpartner() {
		return nhashpartner;
	}

	public void setNhashpartner(Integer nhashpartner) {
		this.nhashpartner = nhashpartner;
	}

	public AccountInfo() {
	}

	public String getsFullname() {
		return sFullname;
	}

	public void setsFullname(String sFullname) {
		this.sFullname = sFullname;
	}

	public String getsCurrencySymbol() {
		return sCurrencySymbol;
	}

	public void setsCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}

	public String getsCurrencyImage() {
		return sCurrencyImage;
	}

	public void setsCurrencyImage(String sCurrencyImage) {
		this.sCurrencyImage = sCurrencyImage;
	}

//	public BigDecimal getAvailable_balance() {
//		return available_balance;
//	}
//
//	public void setAvailable_balance(BigDecimal available_balance) {
//		this.available_balance = available_balance;
//	}

	public String getnAvailablevBalance() {
		return nAvailablevBalance;
	}

	public void setnAvailablevBalance(String nAvailablevBalance) {
		this.nAvailablevBalance = nAvailablevBalance;
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

//	public String getBPassword() {
//		return this.bPassword;
//	}
//
//	public void setBPassword(String bPassword) {
//		this.bPassword = bPassword;
//	}

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
	
	public String getBAvatar() {
		String imageUrl = Parameter.IMAGE_PREFIX + "/" + this.sCustomerId;
		return this.bAvatar == null ? "" : imageUrl;
	}

	public void setBAvatar(String bAvatar) {
		this.bAvatar = bAvatar;
	}

	public String getsCardIdNo() {
		return sCardIdNo;
	}

	public void setsCardIdNo(String sCardIdNo) {
		this.sCardIdNo = sCardIdNo;
	}	
}
