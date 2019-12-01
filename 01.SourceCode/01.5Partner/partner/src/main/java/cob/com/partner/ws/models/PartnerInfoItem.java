package cob.com.partner.ws.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cob.com.partner.ws.param.Parameter;

/**
 * The persistent class for the tb_listpartners database table.
 * 
 */
@Entity
public class PartnerInfoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "s_partner_id")
	private String sPartnerId;

	@Column(name = "d_registration_date")
	private Date dRegistrationDate;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "n_partner_rating")
	private Integer nPartnerRating;

	@Column(name = "s_address_no")
	private String sAddressNo;

	@Column(name = "s_business_email")
	private String sBusinessEmail;

	@Column(name = "s_business_phone")
	private String sBusinessPhone;

	@Column(name = "s_business_type_id")
	private String sBusinessTypeId;

	@Column(name = "s_city_id")
	private String sCityId;

	@Column(name = "s_close_time")
	private String sCloseTime;

	@Column(name = "s_company_logo")
	private String sCompanyLogo;

	@Column(name = "s_country_id")
	private String sCountryId;

	@Column(name = "s_created_by")
	private String sCreatedBy;

	@Column(name = "s_group_business_id")
	private String sGroupBusinessId;

	@Column(name = "s_opening_time")
	private String sOpeningTime;

	@Column(name = "s_owner_name")
	private String sOwnerName;

	@Column(name = "s_partner_desc_cn")
	private String sPartnerDescCn;

	@Column(name = "s_partner_desc_en")
	private String sPartnerDescEn;

	@Column(name = "s_partner_desc_vn")
	private String sPartnerDescVn;

	@Column(name = "s_partner_name_cn")
	private String sPartnerNameCn;

	@Column(name = "s_partner_name_en")
	private String sPartnerNameEn;

	@Column(name = "s_partner_name_vn")
	private String sPartnerNameVn;

	@Column(name = "s_street")
	private String sStreet;

	@Column(name = "s_taxcode")
	private String sTaxcode;

	@Column(name = "s_full_address")
	private String sFullAddress;

	@Column(name = "n_is_approved")
	private Integer nIsApproved;

	@Column(name = "s_company_type_id")
	private String sCompanyTypeId;
	
	@Column(name="s_phone_contact")
	private String sPhoneContact;
	
	@Column(name="s_group_business_name_en")
	private String sgroupBusinessNameEn;
	
	@Column(name="s_group_business_name_vn")
	private String sgroupBusinessNameVn;
	
	@Column(name="s_group_business_name_cn")
	private String sgroupBusinessNameCn;
	
	public String getSgroupBusinessNameEn() {
		return sgroupBusinessNameEn;
	}

	public void setSgroupBusinessNameEn(String sgroupBusinessNameEn) {
		this.sgroupBusinessNameEn = sgroupBusinessNameEn;
	}

	public String getSgroupBusinessNameVn() {
		return sgroupBusinessNameVn;
	}

	public void setSgroupBusinessNameVn(String sgroupBusinessNameVn) {
		this.sgroupBusinessNameVn = sgroupBusinessNameVn;
	}

	public String getSgroupBusinessNameCn() {
		return sgroupBusinessNameCn;
	}

	public void setSgroupBusinessNameCn(String sgroupBusinessNameCn) {
		this.sgroupBusinessNameCn = sgroupBusinessNameCn;
	}

	public String getsPhoneContact() {
		return sPhoneContact;
	}

	public void setsPhoneContact(String sPhoneContact) {
		this.sPhoneContact = sPhoneContact;
	}

	public PartnerInfoItem() {
	}

	public String getsCompanyTypeId() {
		return sCompanyTypeId;
	}

	public void setsCompanyTypeId(String sCompanyTypeId) {
		this.sCompanyTypeId = sCompanyTypeId;
	}

	public String getSfullAddress() {
		return sFullAddress;
	}

	public void setSfullAddress(String sfullAddress) {
		this.sFullAddress = sfullAddress;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public Date getDRegistrationDate() {
		return this.dRegistrationDate;
	}

	public void setDRegistrationDate(Date dRegistrationDate) {
		this.dRegistrationDate = dRegistrationDate;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNPartnerRating() {
		return this.nPartnerRating;
	}

	public void setNPartnerRating(Integer nPartnerRating) {
		this.nPartnerRating = nPartnerRating;
	}

	public String getSAddressNo() {
		return this.sAddressNo;
	}

	public void setSAddressNo(String sAddressNo) {
		this.sAddressNo = sAddressNo;
	}

	public String getSBusinessEmail() {
		return this.sBusinessEmail;
	}

	public void setSBusinessEmail(String sBusinessEmail) {
		this.sBusinessEmail = sBusinessEmail;
	}

	public String getSBusinessPhone() {
		return this.sBusinessPhone;
	}

	public void setSBusinessPhone(String sBusinessPhone) {
		this.sBusinessPhone = sBusinessPhone;
	}

	public String getSBusinessTypeId() {
		return this.sBusinessTypeId;
	}

	public void setSBusinessTypeId(String sBusinessTypeId) {
		this.sBusinessTypeId = sBusinessTypeId;
	}

	public String getSCityId() {
		return this.sCityId;
	}

	public void setSCityId(String sCityId) {
		this.sCityId = sCityId;
	}

	public String getSCloseTime() {
		return this.sCloseTime;
	}

	public void setSCloseTime(String sCloseTime) {
		this.sCloseTime = sCloseTime;
	}

	public String getSCompanyLogo() {
		//return this.sCompanyLogo;
		String uri = Parameter.IMAGE_URI + this.sPartnerId;
		return this.sCompanyLogo == null?"":uri;
	}

	public void setSCompanyLogo(String sCompanyLogo) {
		this.sCompanyLogo = sCompanyLogo;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
	}

	public String getSCreatedBy() {
		return this.sCreatedBy;
	}

	public void setSCreatedBy(String sCreatedBy) {
		this.sCreatedBy = sCreatedBy;
	}

	public String getSGroupBusinessId() {
		return this.sGroupBusinessId;
	}

	public void setSGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public String getSOpeningTime() {
		return this.sOpeningTime;
	}

	public void setSOpeningTime(String sOpeningTime) {
		this.sOpeningTime = sOpeningTime;
	}

	public String getSOwnerName() {
		return this.sOwnerName;
	}

	public void setSOwnerName(String sOwnerName) {
		this.sOwnerName = sOwnerName;
	}

	public String getSPartnerDescCn() {
		return this.sPartnerDescCn;
	}

	public void setSPartnerDescCn(String sPartnerDescCn) {
		this.sPartnerDescCn = sPartnerDescCn;
	}

	public String getSPartnerDescEn() {
		return this.sPartnerDescEn;
	}

	public void setSPartnerDescEn(String sPartnerDescEn) {
		this.sPartnerDescEn = sPartnerDescEn;
	}

	public String getSPartnerDescVn() {
		return this.sPartnerDescVn;
	}

	public void setSPartnerDescVn(String sPartnerDescVn) {
		this.sPartnerDescVn = sPartnerDescVn;
	}

	public String getSPartnerNameCn() {
		return this.sPartnerNameCn;
	}

	public void setSPartnerNameCn(String sPartnerNameCn) {
		this.sPartnerNameCn = sPartnerNameCn;
	}

	public String getSPartnerNameEn() {
		return this.sPartnerNameEn;
	}

	public void setSPartnerNameEn(String sPartnerNameEn) {
		this.sPartnerNameEn = sPartnerNameEn;
	}

	public String getSPartnerNameVn() {
		return this.sPartnerNameVn;
	}

	public void setSPartnerNameVn(String sPartnerNameVn) {
		this.sPartnerNameVn = sPartnerNameVn;
	}

	public String getSStreet() {
		return this.sStreet;
	}

	public void setSStreet(String sStreet) {
		this.sStreet = sStreet;
	}

	public String getSTaxcode() {
		return this.sTaxcode;
	}

	public void setSTaxcode(String sTaxcode) {
		this.sTaxcode = sTaxcode;
	}

	public Integer getNIsApproved() {
		return this.nIsApproved;
	}

	public void setNIsApproved(Integer nIsApproved) {
		this.nIsApproved = nIsApproved;
	}
}