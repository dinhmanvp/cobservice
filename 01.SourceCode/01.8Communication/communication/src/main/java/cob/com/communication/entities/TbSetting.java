package cob.com.communication.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_setting database table.
 * 
 */
@Entity
@Table(name="tb_setting")
@NamedQuery(name="TbSetting.findAll", query="SELECT t FROM TbSetting t")
public class TbSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_value")
	private Integer nValue;

	@Column(name="s_name")
	private String sName;

	public TbSetting() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNValue() {
		return this.nValue;
	}

	public void setNValue(Integer nValue) {
		this.nValue = nValue;
	}

	public String getSName() {
		return this.sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

}