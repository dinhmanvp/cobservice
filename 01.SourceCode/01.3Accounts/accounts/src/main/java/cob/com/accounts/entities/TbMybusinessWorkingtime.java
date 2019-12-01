package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_mybusiness_workingtime database table.
 * 
 */
@Entity
@Table(name="tb_mybusiness_workingtime")
@NamedQuery(name="TbMybusinessWorkingtime.findAll", query="SELECT t FROM TbMybusinessWorkingtime t")
public class TbMybusinessWorkingtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_count_per_day")
	private Integer nCountPerDay;

	@Column(name="n_duration_for_session")
	private Integer nDurationForSession;

	@Column(name="s_fr_from")
	private String sFrFrom;

	@Column(name="s_fr_isoff")
	private String sFrIsoff;

	@Column(name="s_fr_to")
	private String sFrTo;

	@Column(name="s_mo_from")
	private String sMoFrom;

	@Column(name="s_mo_isoff")
	private String sMoIsoff;

	@Column(name="s_mo_to")
	private String sMoTo;

	@Column(name="s_my_business_id")
	private String sMyBusinessId;

	@Column(name="s_my_business_setting_id")
	private String sMyBusinessSettingId;

	@Column(name="s_partner_working_id")
	private String sPartnerWorkingId;

	@Column(name="s_sa_from")
	private String sSaFrom;

	@Column(name="s_sa_isoff")
	private String sSaIsoff;

	@Column(name="s_sa_to")
	private String sSaTo;

	@Column(name="s_su_from")
	private String sSuFrom;

	@Column(name="s_su_isoff")
	private String sSuIsoff;

	@Column(name="s_su_to")
	private String sSuTo;

	@Column(name="s_th_from")
	private String sThFrom;

	@Column(name="s_th_isoff")
	private String sThIsoff;

	@Column(name="s_th_to")
	private String sThTo;

	@Column(name="s_tu_from")
	private String sTuFrom;

	@Column(name="s_tu_isoff")
	private String sTuIsoff;

	@Column(name="s_tu_to")
	private String sTuTo;

	@Column(name="s_we_from")
	private String sWeFrom;

	@Column(name="s_we_isoff")
	private String sWeIsoff;

	@Column(name="s_we_to")
	private String sWeTo;

	public TbMybusinessWorkingtime() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
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

	public String getSFrFrom() {
		return this.sFrFrom;
	}

	public void setSFrFrom(String sFrFrom) {
		this.sFrFrom = sFrFrom;
	}

	public String getSFrIsoff() {
		return this.sFrIsoff;
	}

	public void setSFrIsoff(String sFrIsoff) {
		this.sFrIsoff = sFrIsoff;
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

	public String getSMoIsoff() {
		return this.sMoIsoff;
	}

	public void setSMoIsoff(String sMoIsoff) {
		this.sMoIsoff = sMoIsoff;
	}

	public String getSMoTo() {
		return this.sMoTo;
	}

	public void setSMoTo(String sMoTo) {
		this.sMoTo = sMoTo;
	}

	public String getSMyBusinessId() {
		return this.sMyBusinessId;
	}

	public void setSMyBusinessId(String sMyBusinessId) {
		this.sMyBusinessId = sMyBusinessId;
	}

	public String getSMyBusinessSettingId() {
		return this.sMyBusinessSettingId;
	}

	public void setSMyBusinessSettingId(String sMyBusinessSettingId) {
		this.sMyBusinessSettingId = sMyBusinessSettingId;
	}

	public String getSPartnerWorkingId() {
		return this.sPartnerWorkingId;
	}

	public void setSPartnerWorkingId(String sPartnerWorkingId) {
		this.sPartnerWorkingId = sPartnerWorkingId;
	}

	public String getSSaFrom() {
		return this.sSaFrom;
	}

	public void setSSaFrom(String sSaFrom) {
		this.sSaFrom = sSaFrom;
	}

	public String getSSaIsoff() {
		return this.sSaIsoff;
	}

	public void setSSaIsoff(String sSaIsoff) {
		this.sSaIsoff = sSaIsoff;
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

	public String getSSuIsoff() {
		return this.sSuIsoff;
	}

	public void setSSuIsoff(String sSuIsoff) {
		this.sSuIsoff = sSuIsoff;
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

	public String getSThIsoff() {
		return this.sThIsoff;
	}

	public void setSThIsoff(String sThIsoff) {
		this.sThIsoff = sThIsoff;
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

	public String getSTuIsoff() {
		return this.sTuIsoff;
	}

	public void setSTuIsoff(String sTuIsoff) {
		this.sTuIsoff = sTuIsoff;
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

	public String getSWeIsoff() {
		return this.sWeIsoff;
	}

	public void setSWeIsoff(String sWeIsoff) {
		this.sWeIsoff = sWeIsoff;
	}

	public String getSWeTo() {
		return this.sWeTo;
	}

	public void setSWeTo(String sWeTo) {
		this.sWeTo = sWeTo;
	}

}