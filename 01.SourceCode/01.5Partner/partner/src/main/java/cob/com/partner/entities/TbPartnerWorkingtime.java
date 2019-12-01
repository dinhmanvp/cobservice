package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_partner_workingtime database table.
 * 
 */
@Entity
@Table(name="tb_partner_workingtime")
@NamedQuery(name="TbPartnerWorkingtime.findAll", query="SELECT t FROM TbPartnerWorkingtime t")
public class TbPartnerWorkingtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;
	
	@Column(name="s_partner_working_id")
	private String sPartnerWorkingId;

	@Column(name="n_count_per_day")
	private Integer nCountPerDay;

	@Column(name="n_duration_for_session")
	private Integer nDurationForSession;

	@Column(name="s_business_service_id")
	private String sbusinessServiceId;

	@Column(name="s_fr_from")
	private String sFrFrom;

	@Column(name="s_fr_is_off")
	private String sFrIsOff;

	@Column(name="s_fr_to")
	private String sFrTo;

	@Column(name="s_mo_from")
	private String sMoFrom;

	@Column(name="s_mo_is_off")
	private String sMoIsOff;

	@Column(name="s_mo_to")
	private String sMoTo;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_sa_from")
	private String sSaFrom;

	@Column(name="s_sa_is_off")
	private String sSaIsOff;

	@Column(name="s_sa_to")
	private String sSaTo;

	@Column(name="s_su_from")
	private String sSuFrom;

	@Column(name="s_su_is_off")
	private String sSuIsOff;

	@Column(name="s_su_to")
	private String sSuTo;

	@Column(name="s_th_from")
	private String sThFrom;

	@Column(name="s_th_is_off")
	private String sThIsOff;

	@Column(name="s_th_to")
	private String sThTo;

	@Column(name="s_tu_from")
	private String sTuFrom;

	@Column(name="s_tu_is_off")
	private String sTuIsOff;

	@Column(name="s_tu_to")
	private String sTuTo;

	@Column(name="s_we_from")
	private String sWeFrom;

	@Column(name="s_we_is_off")
	private String sWeIsOff;

	@Column(name="s_we_to")
	private String sWeTo;
	
	@Column(name="is_chosen")
	private Integer isChosen;
	
	public TbPartnerWorkingtime() {
	}	

	public Integer getIsChosen() {
		return isChosen == null ? 0 : isChosen;
	}

	public void setIsChosen(Integer isChosen) {
		this.isChosen = isChosen;
	}

	public String getSPartnerWorkingId() {
		return this.sPartnerWorkingId;
	}

	public void setSPartnerWorkingId(String sPartnerWorkingId) {
		this.sPartnerWorkingId = sPartnerWorkingId;
	}
	
	public String getSbusinessServiceId() {
		return this.sbusinessServiceId;
	}

	public void setSbusinessServiceId(String sbusinessServiceId) {
		this.sbusinessServiceId = sbusinessServiceId;
	}

	public Integer getNCountPerDay() {
		return this.nCountPerDay;
	}

	public void setNCountPerDay(Integer nCountPerDay) {
		this.nCountPerDay = nCountPerDay;
	}

	public Integer getNDurationForSession() {
		return this.nDurationForSession;
	}

	public void setNDurationForSession(Integer nDurationForSession) {
		this.nDurationForSession = nDurationForSession;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSFrFrom() {
		return this.sFrFrom;
	}

	public void setSFrFrom(String sFrFrom) {
		this.sFrFrom = sFrFrom;
	}

	public String getSFrIsOff() {
		return this.sFrIsOff;
	}

	public void setSFrIsOff(String sFrIsOff) {
		this.sFrIsOff = sFrIsOff;
	}

	public String getSFrTo() {
		return this.sFrTo;
	}

	public void setSFrTo(String sFrTo) {
		this.sFrTo = sFrTo;
	}

	public String getSMoFrom() {
		return this.sMoFrom;
	}

	public void setSMoFrom(String sMoFrom) {
		this.sMoFrom = sMoFrom;
	}

	public String getSMoIsOff() {
		return this.sMoIsOff;
	}

	public void setSMoIsOff(String sMoIsOff) {
		this.sMoIsOff = sMoIsOff;
	}

	public String getSMoTo() {
		return this.sMoTo;
	}

	public void setSMoTo(String sMoTo) {
		this.sMoTo = sMoTo;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSSaFrom() {
		return this.sSaFrom;
	}

	public void setSSaFrom(String sSaFrom) {
		this.sSaFrom = sSaFrom;
	}

	public String getSSaIsOff() {
		return this.sSaIsOff;
	}

	public void setSSaIsOff(String sSaIsOff) {
		this.sSaIsOff = sSaIsOff;
	}

	public String getSSaTo() {
		return this.sSaTo;
	}

	public void setSSaTo(String sSaTo) {
		this.sSaTo = sSaTo;
	}

	public String getSSuFrom() {
		return this.sSuFrom;
	}

	public void setSSuFrom(String sSuFrom) {
		this.sSuFrom = sSuFrom;
	}

	public String getSSuIsOff() {
		return this.sSuIsOff;
	}

	public void setSSuIsOff(String sSuIsOff) {
		this.sSuIsOff = sSuIsOff;
	}

	public String getSSuTo() {
		return this.sSuTo;
	}

	public void setSSuTo(String sSuTo) {
		this.sSuTo = sSuTo;
	}

	public String getSThFrom() {
		return this.sThFrom;
	}

	public void setSThFrom(String sThFrom) {
		this.sThFrom = sThFrom;
	}

	public String getSThIsOff() {
		return this.sThIsOff;
	}

	public void setSThIsOff(String sThIsOff) {
		this.sThIsOff = sThIsOff;
	}

	public String getSThTo() {
		return this.sThTo;
	}

	public void setSThTo(String sThTo) {
		this.sThTo = sThTo;
	}

	public String getSTuFrom() {
		return this.sTuFrom;
	}

	public void setSTuFrom(String sTuFrom) {
		this.sTuFrom = sTuFrom;
	}

	public String getSTuIsOff() {
		return this.sTuIsOff;
	}

	public void setSTuIsOff(String sTuIsOff) {
		this.sTuIsOff = sTuIsOff;
	}

	public String getSTuTo() {
		return this.sTuTo;
	}

	public void setSTuTo(String sTuTo) {
		this.sTuTo = sTuTo;
	}

	public String getSWeFrom() {
		return this.sWeFrom;
	}

	public void setSWeFrom(String sWeFrom) {
		this.sWeFrom = sWeFrom;
	}

	public String getSWeIsOff() {
		return this.sWeIsOff;
	}

	public void setSWeIsOff(String sWeIsOff) {
		this.sWeIsOff = sWeIsOff;
	}

	public String getSWeTo() {
		return this.sWeTo;
	}

	public void setSWeTo(String sWeTo) {
		this.sWeTo = sWeTo;
	}

}