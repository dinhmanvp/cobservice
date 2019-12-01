package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_partner_registration_agent database table.
 * 
 */
@Entity
@Table(name="tb_partner_registration_agent")
@NamedQuery(name="TbPartnerRegistrationAgent.findAll", query="SELECT t FROM TbPartnerRegistrationAgent t")
public class TbPartnerRegistrationAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_agent_id")
	private String sAgentId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_approval_date")
	private Date dApprovalDate;

	@Temporal(TemporalType.DATE)
	@Column(name="d_valid_from")
	private Date dValidFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="d_valid_to")
	private Date dValidTo;

	@Column(name="n_approval_price")
	private Integer nApprovalPrice;

	@Column(name="n_cob_price")
	private Integer nCobPrice;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_is_stoped")
	private Integer nIsStoped;

	@Column(name="n_partner_confirmed")
	private Integer nPartnerConfirmed;

	@Column(name="n_price_bid")
	private Integer nPriceBid;

	@Column(name="s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name="s_approval_by")
	private String sApprovalBy;

	@Column(name="s_cob_comment")
	private String sCobComment;

	@Column(name="s_partner_comment")
	private String sPartnerComment;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_service_unit_id")
	private String sServiceUnitId;

	public TbPartnerRegistrationAgent() {
	}

	public String getSAgentId() {
		return this.sAgentId;
	}

	public void setSAgentId(String sAgentId) {
		this.sAgentId = sAgentId;
	}

	public Date getDApprovalDate() {
		return this.dApprovalDate;
	}

	public void setDApprovalDate(Date dApprovalDate) {
		this.dApprovalDate = dApprovalDate;
	}

	public Date getDValidFrom() {
		return this.dValidFrom;
	}

	public void setDValidFrom(Date dValidFrom) {
		this.dValidFrom = dValidFrom;
	}

	public Date getDValidTo() {
		return this.dValidTo;
	}

	public void setDValidTo(Date dValidTo) {
		this.dValidTo = dValidTo;
	}

	public Integer getNApprovalPrice() {
		return this.nApprovalPrice;
	}

	public void setNApprovalPrice(Integer nApprovalPrice) {
		this.nApprovalPrice = nApprovalPrice;
	}

	public Integer getNCobPrice() {
		return this.nCobPrice;
	}

	public void setNCobPrice(Integer nCobPrice) {
		this.nCobPrice = nCobPrice;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsStoped() {
		return this.nIsStoped;
	}

	public void setNIsStoped(Integer nIsStoped) {
		this.nIsStoped = nIsStoped;
	}

	public Integer getNPartnerConfirmed() {
		return this.nPartnerConfirmed;
	}

	public void setNPartnerConfirmed(Integer nPartnerConfirmed) {
		this.nPartnerConfirmed = nPartnerConfirmed;
	}

	public Integer getNPriceBid() {
		return this.nPriceBid;
	}

	public void setNPriceBid(Integer nPriceBid) {
		this.nPriceBid = nPriceBid;
	}

	public String getSAdviseServiceId() {
		return this.sAdviseServiceId;
	}

	public void setSAdviseServiceId(String sAdviseServiceId) {
		this.sAdviseServiceId = sAdviseServiceId;
	}

	public String getSApprovalBy() {
		return this.sApprovalBy;
	}

	public void setSApprovalBy(String sApprovalBy) {
		this.sApprovalBy = sApprovalBy;
	}

	public String getSCobComment() {
		return this.sCobComment;
	}

	public void setSCobComment(String sCobComment) {
		this.sCobComment = sCobComment;
	}

	public String getSPartnerComment() {
		return this.sPartnerComment;
	}

	public void setSPartnerComment(String sPartnerComment) {
		this.sPartnerComment = sPartnerComment;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSServiceUnitId() {
		return this.sServiceUnitId;
	}

	public void setSServiceUnitId(String sServiceUnitId) {
		this.sServiceUnitId = sServiceUnitId;
	}

}