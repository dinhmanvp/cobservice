package cob.com.cobgateway.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the tb_user_locked database table.
 * 
 */
@Entity
@Table(name="tb_user_locked")
@NamedQuery(name="TbUserLocked.findAll", query="SELECT t FROM TbUserLocked t")
public class TbUserLocked implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="b_is_locked")
	private Integer bIsLocked;

	@Temporal(TemporalType.DATE)
	@Column(name="d_locked_date")
	private Date dLockedDate;

	@Column(name="d_locked_time")
	private Time dLockedTime;

	@Temporal(TemporalType.DATE)
	@Column(name="d_unlocked_date")
	private Date dUnlockedDate;

	@Column(name="d_unlocked_time")
	private Time dUnlockedTime;

	@Column(name="s_channel_id")
	private String sChannelId;

	@Column(name="s_cob_staff_id")
	private String sCobStaffId;

	@Column(name="s_reason")
	private String sReason;

	@Column(name="s_user_id")
	private String sUserId;

	public TbUserLocked() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getBIsLocked() {
		return this.bIsLocked;
	}

	public void setBIsLocked(Integer bIsLocked) {
		this.bIsLocked = bIsLocked;
	}

	public Date getDLockedDate() {
		return this.dLockedDate;
	}

	public void setDLockedDate(Date dLockedDate) {
		this.dLockedDate = dLockedDate;
	}

	public Time getDLockedTime() {
		return this.dLockedTime;
	}

	public void setDLockedTime(Time dLockedTime) {
		this.dLockedTime = dLockedTime;
	}

	public Date getDUnlockedDate() {
		return this.dUnlockedDate;
	}

	public void setDUnlockedDate(Date dUnlockedDate) {
		this.dUnlockedDate = dUnlockedDate;
	}

	public Time getDUnlockedTime() {
		return this.dUnlockedTime;
	}

	public void setDUnlockedTime(Time dUnlockedTime) {
		this.dUnlockedTime = dUnlockedTime;
	}

	public String getSChannelId() {
		return this.sChannelId;
	}

	public void setSChannelId(String sChannelId) {
		this.sChannelId = sChannelId;
	}

	public String getSCobStaffId() {
		return this.sCobStaffId;
	}

	public void setSCobStaffId(String sCobStaffId) {
		this.sCobStaffId = sCobStaffId;
	}

	public String getSReason() {
		return this.sReason;
	}

	public void setSReason(String sReason) {
		this.sReason = sReason;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}