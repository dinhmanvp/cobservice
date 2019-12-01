package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_customer_loyalty_program database table.
 * 
 */
@Entity
@Table(name="tb_customer_loyalty_program", schema="mdl_core")
@NamedQuery(name="TbCustomerLoyaltyProgram.findAll", query="SELECT t FROM TbCustomerLoyaltyProgram t")
public class TbCustomerLoyaltyProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="d_from_date")
	private Timestamp dFromDate;

	@Column(name="d_to_date")
	private Timestamp dToDate;

	@Column(name="n_is_condition")
	private Integer nIsCondition;

	@Column(name="n_quantity")
	private Integer nQuantity;

	@Column(name="n_slot")
	private Integer nSlot;

	@Column(name="s_description")
	private String sDescription;

	@Column(name="s_program_id")
	private String sProgramId;

	@Column(name="s_program_type")
	private String sProgramType;

	public TbCustomerLoyaltyProgram() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Timestamp getDFromDate() {
		return this.dFromDate;
	}

	public void setDFromDate(Timestamp dFromDate) {
		this.dFromDate = dFromDate;
	}

	public Timestamp getDToDate() {
		return this.dToDate;
	}

	public void setDToDate(Timestamp dToDate) {
		this.dToDate = dToDate;
	}

	public Integer getNIsCondition() {
		return this.nIsCondition;
	}

	public void setNIsCondition(Integer nIsCondition) {
		this.nIsCondition = nIsCondition;
	}

	public Integer getNQuantity() {
		return this.nQuantity;
	}

	public void setNQuantity(Integer nQuantity) {
		this.nQuantity = nQuantity;
	}

	public Integer getNSlot() {
		return this.nSlot;
	}

	public void setNSlot(Integer nSlot) {
		this.nSlot = nSlot;
	}

	public String getSDescription() {
		return this.sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	public String getSProgramId() {
		return this.sProgramId;
	}

	public void setSProgramId(String sProgramId) {
		this.sProgramId = sProgramId;
	}

	public String getSProgramType() {
		return this.sProgramType;
	}

	public void setSProgramType(String sProgramType) {
		this.sProgramType = sProgramType;
	}

}