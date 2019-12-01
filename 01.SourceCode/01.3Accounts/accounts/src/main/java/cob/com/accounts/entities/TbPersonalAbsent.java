package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_personal_absent database table.
 * 
 */
@Entity
@Table(name="tb_personal_absent")
@NamedQuery(name="TbPersonalAbsent.findAll", query="SELECT t FROM TbPersonalAbsent t")
public class TbPersonalAbsent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_absent_date")
	private Date dAbsentDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="d_from_date")
	private Date dfromdate;

	@Temporal(TemporalType.DATE)
	@Column(name="d_to_date")
	private Date dtodate;
	
	@Column(name="s_absent_reson")
	private String sAbsentReson;

	@Column(name="s_my_business_id")
	private String sMyBusinessId;

	@Column(name="s_personal_absent_id")
	private String sPersonalAbsentId;

	public TbPersonalAbsent() {
	}

	public Date getDtodate() {
		return dtodate;
	}

	public void setDtodate(Date dtodate) {
		this.dtodate = dtodate;
	}

	public Date getDfromdate() {
		return dfromdate;
	}

	public void setDfromdate(Date dfromdate) {
		this.dfromdate = dfromdate;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDAbsentDate() {
		return this.dAbsentDate;
	}

	public void setDAbsentDate(Date dAbsentDate) {
		this.dAbsentDate = dAbsentDate;
	}

	public String getSAbsentReson() {
		return this.sAbsentReson;
	}

	public void setSAbsentReson(String sAbsentReson) {
		this.sAbsentReson = sAbsentReson;
	}

	public String getSMyBusinessId() {
		return this.sMyBusinessId;
	}

	public void setSMyBusinessId(String sMyBusinessId) {
		this.sMyBusinessId = sMyBusinessId;
	}

	public String getSPersonalAbsentId() {
		return this.sPersonalAbsentId;
	}

	public void setSPersonalAbsentId(String sPersonalAbsentId) {
		this.sPersonalAbsentId = sPersonalAbsentId;
	}

}