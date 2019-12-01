package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_service_unit database table.
 * 
 */
@Entity
@Table(name="tb_service_unit")
@NamedQuery(name="TbServiceUnit.findAll", query="SELECT t FROM TbServiceUnit t")
public class TbServiceUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_service_unit_id")
	private String sServiceUnitId;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_duration_type")
	private String sDurationType;

	@Column(name="s_unit_name_cn")
	private String sUnitNameCn;

	@Column(name="s_unit_name_en")
	private String sUnitNameEn;

	@Column(name="s_unit_name_vn")
	private String sUnitNameVn;

	public TbServiceUnit() {
	}

	public String getSServiceUnitId() {
		return this.sServiceUnitId;
	}

	public void setSServiceUnitId(String sServiceUnitId) {
		this.sServiceUnitId = sServiceUnitId;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSDurationType() {
		return this.sDurationType;
	}

	public void setSDurationType(String sDurationType) {
		this.sDurationType = sDurationType;
	}

	public String getSUnitNameCn() {
		return this.sUnitNameCn;
	}

	public void setSUnitNameCn(String sUnitNameCn) {
		this.sUnitNameCn = sUnitNameCn;
	}

	public String getSUnitNameEn() {
		return this.sUnitNameEn;
	}

	public void setSUnitNameEn(String sUnitNameEn) {
		this.sUnitNameEn = sUnitNameEn;
	}

	public String getSUnitNameVn() {
		return this.sUnitNameVn;
	}

	public void setSUnitNameVn(String sUnitNameVn) {
		this.sUnitNameVn = sUnitNameVn;
	}

}