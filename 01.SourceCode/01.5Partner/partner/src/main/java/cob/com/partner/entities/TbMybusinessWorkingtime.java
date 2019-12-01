package cob.com.partner.entities;

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
	
	@Column(name="s_my_business_setting_id")
	private String sMyBusinessSettingId;

	@Column(name="s_my_business_id")
	private String sMyBusinessId;

	@Column(name="s_partner_working_id")
	private String sPartnerWorkingId;
	

	public TbMybusinessWorkingtime() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
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

	
}