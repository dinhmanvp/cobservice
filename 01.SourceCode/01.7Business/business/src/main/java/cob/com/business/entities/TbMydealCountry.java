package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_mydeal_country database table.
 * 
 */
@Entity
@Table(name="tb_mydeal_country")
@NamedQuery(name="TbMydealCountry.findAll", query="SELECT t FROM TbMydealCountry t")
public class TbMydealCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_country_id")
	private String sCountryId;

	@Column(name="s_mydeal_country_id")
	private String sMydealCountryId;

	@Column(name="s_mydeal_id")
	private String sMydealId;

	public TbMydealCountry() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
	}

	public String getSMydealCountryId() {
		return this.sMydealCountryId;
	}

	public void setSMydealCountryId(String sMydealCountryId) {
		this.sMydealCountryId = sMydealCountryId;
	}

	public String getSMydealId() {
		return this.sMydealId;
	}

	public void setSMydealId(String sMydealId) {
		this.sMydealId = sMydealId;
	}

}