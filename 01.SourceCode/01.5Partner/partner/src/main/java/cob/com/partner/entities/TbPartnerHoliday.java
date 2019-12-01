package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_partner_holiday database table.
 * 
 */
@Entity
@Table(name="tb_partner_holiday")
@NamedQuery(name="TbPartnerHoliday.findAll", query="SELECT t FROM TbPartnerHoliday t")
public class TbPartnerHoliday implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_partner_holiday_id")
	private String sPartnerHolidayId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_holiday_date")
	private Date dHolidayDate;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_holiday_desc")
	private String sHolidayDesc;

	@Column(name="s_partner_id")
	private String sPartnerId;

	public TbPartnerHoliday() {
	}

	public String getSPartnerHolidayId() {
		return this.sPartnerHolidayId;
	}

	public void setSPartnerHolidayId(String sPartnerHolidayId) {
		this.sPartnerHolidayId = sPartnerHolidayId;
	}

	public Date getDHolidayDate() {
		return this.dHolidayDate;
	}

	public void setDHolidayDate(Date dHolidayDate) {
		this.dHolidayDate = dHolidayDate;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSHolidayDesc() {
		return this.sHolidayDesc;
	}

	public void setSHolidayDesc(String sHolidayDesc) {
		this.sHolidayDesc = sHolidayDesc;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

}