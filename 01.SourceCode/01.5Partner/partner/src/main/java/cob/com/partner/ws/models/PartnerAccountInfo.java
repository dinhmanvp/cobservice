package cob.com.partner.ws.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_partner_account database table.
 * 
 */
@Entity
public class PartnerAccountInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="n_id")
	private Integer nid;	

	@Temporal(TemporalType.DATE)
	@Column(name="d_joined_date")
	private Date djoineddate;

	@Column(name="n_is_approved")
	private Integer nisapproved;

	@Column(name="n_is_deleted")
	private Integer nisdeleted;

	@Column(name="s_comment")
	private String scomment;
	
	@Column(name="s_partner_account_id")
	private String spartneraccountid;

	@Column(name="s_partner_rule_id")
	private String spartnerruleid;

	@Column(name="s_staff_number")
	private String sStaffNumber;

	@Column(name="s_user_id")
	private String suserid;
	
	@Column(name="s_partner_id")
	private String spartnerid;
	
	@Column(name="s_full_name")
	private String sfullname;
	
	@Column(name="b_avatar")
	private String bavatar;
	
	@Column(name="s_rule_name")
	private String srulename;

	public PartnerAccountInfo() {
	}
	
	public String getSrulename() {
		return srulename;
	}
	
	public void setSrulename(String srulename) {
		this.srulename = srulename;
	}
	
	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Date getDjoineddate() {
		return djoineddate;
	}

	public void setDjoineddate(Date djoineddate) {
		this.djoineddate = djoineddate;
	}

	public Integer getNisapproved() {
		return nisapproved;
	}

	public void setNisapproved(Integer nisapproved) {
		this.nisapproved = nisapproved;
	}

	public Integer getNisdeleted() {
		return nisdeleted;
	}

	public void setNisdeleted(Integer nisdeleted) {
		this.nisdeleted = nisdeleted;
	}

	public String getScomment() {
		return scomment;
	}

	public void setScomment(String scomment) {
		this.scomment = scomment;
	}

	public String getSpartneraccountid() {
		return spartneraccountid;
	}

	public void setSpartneraccountid(String spartneraccountid) {
		this.spartneraccountid = spartneraccountid;
	}

	public String getSpartnerruleid() {
		return spartnerruleid;
	}

	public void setSpartnerruleid(String spartnerruleid) {
		this.spartnerruleid = spartnerruleid;
	}

	public String getsStaffNumber() {
		return sStaffNumber;
	}

	public void setsStaffNumber(String sStaffNumber) {
		this.sStaffNumber = sStaffNumber;
	}

	public String getSuserid() {
		return suserid;
	}

	public void setSuserid(String suserid) {
		this.suserid = suserid;
	}

	public String getSpartnerid() {
		return spartnerid;
	}

	public void setSpartnerid(String spartnerid) {
		this.spartnerid = spartnerid;
	}

	public String getSfullname() {
		return sfullname;
	}

	public void setSfullname(String sfullname) {
		this.sfullname = sfullname;
	}

	public String getBavatar() {
		return bavatar;
	}

	public void setBavatar(String bavatar) {
		this.bavatar = bavatar;
	}
}