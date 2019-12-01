package cob.com.communication.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_customer database table.
 * 
 */
@Entity
@Table(name="tb_customer", schema = "mdl_account")
@NamedQuery(name="TbCustomer.findAll", query="SELECT t FROM TbCustomer t")
public class TbCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@SequenceGenerator(name = "TbCustomer_sequence", sequenceName = "TbCustomer_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TbCustomer_sequence")
	private Integer nId;

	@Column(name="b_avatar")
	private String bAvatar;

	@Temporal(TemporalType.DATE)
	@Column(name="d_birthday")
	private Date dBirthday;

	@Column(name="n_gender")
	private Integer nGender;

	@Column(name="s_customer_id")
	private String sCustomerId;

	@Column(name="s_firstname")
	private String sFirstname;

	@Column(name="s_lastname")
	private String sLastname;
	
	@Column(name="s_contact_address_no")
	private String sContactAddressNo;
	
	@Column(name="s_contact_street")
	private String sContactStreet;  
	
	@Column(name="s_contact_zipcode")
	private String sContactZipcode;
	
	@Column(name="s_contact_country_id")
	private String sContactCountryId;
	
	@Column(name="s_contact_city_id")
	private String sContactCityId;
	
	@Column(name="s_billing_address_no")
	private String sBillingAddressNo;
	
	@Column(name="s_billing_street")
	private String sBillingStreet;
	
	@Column(name="s_billing_zipcode")
	private String sBillingZipcode;
	
	@Column(name="s_billing_country_id")
	private String sBillingCountryId;
	
	@Column(name="s_billing_city_id")
	private String sBillingCityId;
	
	@Column(name="s_shipping_address_no")
	private String sShippingAddressNo;
	
	@Column(name="s_shipping_street")
	private String sShippingStreet;
	
	@Column(name="s_shipping_zipcode")
	private String sShippingZipcode;
	
	@Column(name="s_shipping_country_id")
	private String sShippingCountryId;
	
	@Column(name="s_shipping_city_id")
	private String sShippingCityId;
	 
	@Column(name="s_referalId")
	private String sreferalId;
	
	@Column(name="s_card_id_no")
	private String scardIdNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="d_cardid_issue_date")
	private Date dcardidIssueDate;
	
	@Column(name="s_cardid_issue_place")
	private String scardidIssuePlace;
	 
	public TbCustomer() {
	}
	
	public String getScardIdNo() {
		return this.scardIdNo;
	}

	public void setScardIdNo(String scardIdNo) {
		this.scardIdNo = scardIdNo;
	}
	
	public String getScardidIssuePlace() {
		return this.scardidIssuePlace;
	}

	public void setScardidIssuePlace(String scardidIssuePlace) {
		this.scardidIssuePlace = scardidIssuePlace;
	}
	
	public Date getDcardidIssueDate() {
		return this.dcardidIssueDate;
	}

	public void setDcardidIssueDate(Date dcardidIssueDate) {
		this.dcardidIssueDate = dcardidIssueDate;
	}
	

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getBAvatar() {
		return this.bAvatar;
	}

	public void setBAvatar(String bAvatar) {
		this.bAvatar = bAvatar;
	}

	public Date getDBirthday() {
		return this.dBirthday;
	}

	public void setDBirthday(Date dBirthday) {
		this.dBirthday = dBirthday;
	}

	public Integer getNGender() {
		return this.nGender;
	}

	public void setNGender(Integer nGender) {
		this.nGender = nGender;
	}

	public String getSCustomerId() {
		return this.sCustomerId;
	}

	public void setSCustomerId(String sCustomerId) {
		this.sCustomerId = sCustomerId;
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
	
	public String getSContactAddressNo() {
		return this.sContactAddressNo;
	}

	public void setSContactAddressNo(String sContactAddressNo) {
		this.sContactAddressNo = sContactAddressNo;
	}
	
	public String getSContactStreet() {
		return this.sContactStreet;
	}

	public void setSContactStreet(String sContactStreet) {
		this.sContactStreet = sContactStreet;
	}
	
	public String getSContactZipcode() {
		return this.sContactZipcode;
	}

	public void setSContactZipcode(String sContactZipcode) {
		this.sContactZipcode = sContactZipcode;
	}
	
	public String getSContactCountryIde() {
		return this.sContactCountryId;
	}

	public void setSContactCountryId(String sContactCountryId) {
		this.sContactCountryId = sContactCountryId;
	}
	
	public String getSContactCityId() {
		return this.sContactCityId;
	}

	public void setSContactCityId(String sContactCityId) {
		this.sContactCityId = sContactCityId;
	}
	
	public String getSBillingAddressNo() {
		return this.sBillingAddressNo;
	}

	public void setSBillingAddressNo(String sBillingAddressNo) {
		this.sBillingAddressNo = sBillingAddressNo;
	}
	
	public String getSBillingStreet() {
		return this.sBillingStreet;
	}

	public void setSBillingStreet(String sBillingStreet) {
		this.sBillingStreet = sBillingStreet;
	}
	
	public String getSBillingZipcode() {
		return this.sBillingZipcode;
	}

	public void setSBillingZipcode(String sBillingZipcode) {
		this.sBillingZipcode = sBillingZipcode;
	}
	
	public String getSBillingCountryIde() {
		return this.sBillingCountryId;
	}

	public void setSBillingCountryId(String sBillingCountryId) {
		this.sBillingCountryId = sBillingCountryId;
	}
	
	public String getSBillingCityId() {
		return this.sBillingCityId;
	}

	public void setSBillingCityId(String sBillingCityId) {
		this.sBillingCityId = sBillingCityId;
	}
	
	public String getSShippingAddressNo() {
		return this.sShippingAddressNo;
	}

	public void setSShippingAddressNo(String sShippingAddressNo) {
		this.sShippingAddressNo = sShippingAddressNo;
	}
	
	public String getSShippingStreet() {
		return this.sShippingStreet; 
	}

	public void setSShippingStreet(String sShippingStreet) {
		this.sShippingStreet = sShippingStreet;
	}
	
	public String getSShippingZipcode() {
		return this.sShippingZipcode;
	}

	public void setSShippingZipcode(String sShippingZipcode) {
		this.sShippingZipcode = sShippingZipcode;
	}
	
	public String getSShippingCountryIde() {
		return this.sShippingCountryId;
	}

	public void setSShippingCountryId(String sShippingCountryId) {
		this.sShippingCountryId = sShippingCountryId;
	}
	
	public String getSShippingCityId() {
		return this.sShippingCityId;
	}

	public void setSShippingCityId(String sShippingCityId) {
		this.sShippingCityId = sShippingCityId;
	}
	public String getSreferalId() {
		return this.sreferalId;
	}

	public void setSreferalId(String sreferalId) {
		this.sreferalId = sreferalId;
	}
}