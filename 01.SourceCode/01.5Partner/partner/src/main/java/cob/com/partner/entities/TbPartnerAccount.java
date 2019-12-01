package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_partner_account database table.
 * 
 */
@Entity
@Table(name="tb_partner_account")
@NamedQuery(name="TbPartnerAccount.findAll", query="SELECT t FROM TbPartnerAccount t")
public class TbPartnerAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="n_id")
	@SequenceGenerator(name = "TbPartnerAccount_sequence", sequenceName = "TbPartnerAccount_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TbPartnerAccount_sequence")
	private Integer nId;
	
	

	@Temporal(TemporalType.DATE)
	@Column(name="d_joined_date")
	private Date dJoinedDate;

	@Column(name="n_is_approved")
	private Integer nIsApproved;

	@Column(name="n_is_deleted")
	private Integer nIsDeleted;

	@Column(name="s_comment")
	private String sComment;

	
	@Column(name="s_partner_account_id")
	private String sPartnerAccountId;

	@Column(name="s_partner_rule_id")
	private String sPartnerRuleId;

	@Column(name="s_staff_number")
	private String sStaffNumber;

	@Column(name="s_user_id")
	private String sUserId;
	
	@Column(name="s_partner_id")
	private String sPartnerId;
	

	public TbPartnerAccount() {
	}
	
	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDJoinedDate() {
		return this.dJoinedDate;
	}

	public void setDJoinedDate(Date dJoinedDate) {
		this.dJoinedDate = dJoinedDate;
	}

	public Integer getNIsApproved() {
		return this.nIsApproved;
	}

	public void setNIsApproved(Integer nIsApproved) {
		this.nIsApproved = nIsApproved;
	}

	public Integer getNIsDeleted() {
		return this.nIsDeleted;
	}

	public void setNIsDeleted(Integer nIsDeleted) {
		this.nIsDeleted = nIsDeleted;
	}

	public String getSComment() {
		return this.sComment;
	}

	public void setSComment(String sComment) {
		this.sComment = sComment;
	}

	public String getSPartnerAccountId() {
		return this.sPartnerAccountId;
	}

	public void setSPartnerAccountId(String sPartnerAccountId) {
		this.sPartnerAccountId = sPartnerAccountId;
	}

	public String getSPartnerRuleId() {
		return this.sPartnerRuleId;
	}

	public void setSPartnerRuleId(String sPartnerRuleId) {
		this.sPartnerRuleId = sPartnerRuleId;
	}

	public String getSStaffNumber() {
		return this.sStaffNumber;
	}

	public void setSStaffNumber(String sStaffNumber) {
		this.sStaffNumber = sStaffNumber;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}