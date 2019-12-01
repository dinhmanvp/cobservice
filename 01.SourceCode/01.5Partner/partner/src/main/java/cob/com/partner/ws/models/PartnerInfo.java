package cob.com.partner.ws.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import cob.com.partner.ws.param.Parameter;

/**
 * The persistent class for the tb_listpartners database table.
 * 
 */
@Entity
public class PartnerInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "s_partner_id")
	private String spartnerid;

	@Column(name = "s_partner_name")
	private String spartnername;

	@Column(name = "s_business_phone")
	private String sbusinessphone;

	@Column(name = "s_business_email")
	private String sbusinessemail;

	@Column(name = "s_address_no")
	private String saddressno;

	@Column(name = "s_street")
	private String sstreet;

	@Column(name = "s_company_logo")
	private String scompanylogo;

	@Column(name = "s_taxcode")
	private String staxcode;

	@Column(name = "s_owner_name")
	private String sownername;

	@Column(name = "d_registration_date")
	private Date dregistrationdate;

	@Column(name = "s_opening_time")
	private String sopeningtime;

	@Column(name = "s_close_time")
	private String sclosetime;

	@Column(name = "s_group_business_id")
	private String sgroupbusinessid;

	@Column(name = "s_business_type_id")
	private String sbusinesstypeid;

	@Column(name = "s_created_by")
	private String screatedby;

	@Column(name = "s_partner_desc")
	private String spartnerdesc;

	@Column(name = "n_partner_rating")
	private Integer npartnerrating;

	@Column(name = "s_full_address")
	private String sfulladdress;

	@Column(name = "n_is_approved")
	private Integer nisapproved;

	@Column(name = "n_count_order")
	private Integer ncountorder;
	
	@Column(name="s_group_business_name")
	private String sgroupBusinessName;
	
	@Column(name="s_my_business_id")
	private String smybusinessid;

	public String getSgroupBusinessName() {
		return sgroupBusinessName;
	}

	public String getSmybusinessid() {
		return smybusinessid;
	}

	public void setSmybusinessid(String smybusinessid) {
		this.smybusinessid = smybusinessid;
	}

	public void setSgroupBusinessName(String sgroupBusinessName) {
		this.sgroupBusinessName = sgroupBusinessName;
	}

	@Transient
	private List<PartnerBusinessServiceInfo> partnerBusinessServiceInfos;

	public List<PartnerBusinessServiceInfo> getPartnerBusinessServiceInfos() {
		return partnerBusinessServiceInfos;
	}

	public void setPartnerBusinessServiceInfos(List<PartnerBusinessServiceInfo> partnerBusinessServiceInfo) {
		this.partnerBusinessServiceInfos = partnerBusinessServiceInfo;
	}

	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public String getSpartnerid() {
		return spartnerid;
	}

	public void setSpartnerid(String spartnerid) {
		this.spartnerid = spartnerid;
	}

	public String getSpartnername() {
		return spartnername;
	}

	public void setSpartnername(String spartnername) {
		this.spartnername = spartnername;
	}

	public String getSbusinessphone() {
		return sbusinessphone;
	}

	public void setSbusinessphone(String sbusinessphone) {
		this.sbusinessphone = sbusinessphone;
	}

	public String getSbusinessemail() {
		return sbusinessemail;
	}

	public void setSbusinessemail(String sbusinessemail) {
		this.sbusinessemail = sbusinessemail;
	}

	public String getSaddressno() {
		return saddressno;
	}

	public void setSaddressno(String saddressno) {
		this.saddressno = saddressno;
	}

	public String getSstreet() {
		return sstreet;
	}

	public void setSstreet(String sstreet) {
		this.sstreet = sstreet;
	}

	public String getScompanylogo() {
		//return scompanylogo;
		String uri = Parameter.IMAGE_URI + this.spartnerid;
		return this.scompanylogo == null?"":uri;
	}

	public void setScompanylogo(String scompanylogo) {
		this.scompanylogo = scompanylogo;
	}

	public String getStaxcode() {
		return staxcode;
	}

	public void setStaxcode(String staxcode) {
		this.staxcode = staxcode;
	}

	public String getSownername() {
		return sownername;
	}

	public void setSownername(String sownername) {
		this.sownername = sownername;
	}

	public Date getDregistrationdate() {
		return dregistrationdate;
	}

	public void setDregistrationdate(Date dregistrationdate) {
		this.dregistrationdate = dregistrationdate;
	}

	public String getSopeningtime() {
		return sopeningtime;
	}

	public void setSopeningtime(String sopeningtime) {
		this.sopeningtime = sopeningtime;
	}

	public String getSclosetime() {
		return sclosetime;
	}

	public void setSclosetime(String sclosetime) {
		this.sclosetime = sclosetime;
	}

	public String getSgroupbusinessid() {
		return sgroupbusinessid;
	}

	public void setSgroupbusinessid(String sgroupbusinessid) {
		this.sgroupbusinessid = sgroupbusinessid;
	}

	public String getSbusinesstypeid() {
		return sbusinesstypeid;
	}

	public void setSbusinesstypeid(String sbusinesstypeid) {
		this.sbusinesstypeid = sbusinesstypeid;
	}

	public String getScreatedby() {
		return screatedby;
	}

	public void setScreatedby(String screatedby) {
		this.screatedby = screatedby;
	}

	public String getSpartnerdesc() {
		return spartnerdesc;
	}

	public void setSpartnerdesc(String spartnerdesc) {
		this.spartnerdesc = spartnerdesc;
	}

	public Integer getNpartnerrating() {
		return npartnerrating;
	}

	public void setNpartnerrating(Integer npartnerrating) {
		this.npartnerrating = npartnerrating;
	}

	public String getSfulladdress() {
		return sfulladdress;
	}

	public void setSfulladdress(String sfulladdress) {
		this.sfulladdress = sfulladdress;
	}

	public Integer getNisapproved() {
		return nisapproved;
	}

	public void setNisapproved(Integer nisapproved) {
		this.nisapproved = nisapproved;
	}

	public Integer getNcountorder() {
		return ncountorder;
	}

	public void setNcountorder(Integer ncountorder) {
		this.ncountorder = ncountorder;
	}
}