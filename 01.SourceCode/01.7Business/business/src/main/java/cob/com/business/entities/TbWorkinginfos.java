package cob.com.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class TbWorkinginfos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_partner_id")
	private String sPartnerId;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="d_appointment_date")
	private Date dAppointmentDate;

	@Column(name="n_appointment_number")
	private Integer nAppointmentNumber;
	
	@Column(name="s_business_service_id")
	private String sBusinessServiceId;
	
	@Column(name="n_count_per_day")
	private Integer nCountPerDay;
	
	@Column(name="is_Holiday")
	private Integer isHoliday;
	
	@Column(name="s_is_fullday")
	private String IsFullDay;
	
	@Column(name="is_holiday_desc")
	private String sholidayDesc;
	
	@Column(name="s_is_off_day")
	private String sisOffDay;
	
	@Transient
	private Integer isChosen;
	
	public TbWorkinginfos() {
	}
	
	public Integer getIsChosen() {
		return isChosen == null ? 0 : isChosen;
	}

	public void setIsChosen(Integer isChosen) {
		this.isChosen = isChosen;
	}



	public String getSIsFullDay() {
		return this.IsFullDay;
	}

	public void setSIsFullDay(String IsFullDay) {
		this.IsFullDay = IsFullDay;
	}
	
	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}
	
	public Date getDAppointmentDate() {
		return this.dAppointmentDate;
	}

	public void setDAppointmentDate(Date dAppointmentDate) {
		this.dAppointmentDate = dAppointmentDate;
	}
	
	public Integer getNAppointmentNumber() {
		return this.nAppointmentNumber;
	}

	public void setnAppointmentNumber(Integer nAppointmentNumber) {
		this.nAppointmentNumber = nAppointmentNumber;
	}
	
	public Integer getNCountPerDay() {
		return this.nCountPerDay;
	}

	public void setNCountPerDay(Integer nCountPerDay) {
		this.nCountPerDay = nCountPerDay;
	}
	
	public Integer getNisHoliday() {
		return this.isHoliday;
	}

	public void setNisHoliday(Integer isHoliday) {
		this.isHoliday = isHoliday;
	}
	
	public String getSBusinessServiceId() {
		return this.sBusinessServiceId;
	}

	public void setSBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSholidayDesc() {
		return sholidayDesc;
	}

	public void setSholidayDesc(String sholidayDesc) {
		this.sholidayDesc = sholidayDesc;
	}

	public String getSisOffDay() {
		return sisOffDay;
	}

	public void setSisOffDay(String sisOffDay) {
		this.sisOffDay = sisOffDay;
	}
	
}