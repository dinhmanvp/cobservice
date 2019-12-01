package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_country database table.
 * 
 */
@Entity
@Table(name="tb_country")
@NamedQuery(name="TbCountry.findAll", query="SELECT t FROM TbCountry t")
public class TbCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_country_flag_image")
	private String sCountryFlagImage;

	@Column(name="s_country_id")
	private String sCountryId;

	@Column(name="s_country_name_cn")
	private String sCountryNameCn;

	@Column(name="s_country_name_en")
	private String sCountryNameEn;

	@Column(name="s_country_name_vn")
	private String sCountryNameVn;

	public TbCountry() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSCountryFlagImage() {
		return this.sCountryFlagImage;
	}

	public void setSCountryFlagImage(String sCountryFlagImage) {
		this.sCountryFlagImage = sCountryFlagImage;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
	}

	public String getSCountryNameCn() {
		return this.sCountryNameCn;
	}

	public void setSCountryNameCn(String sCountryNameCn) {
		this.sCountryNameCn = sCountryNameCn;
	}

	public String getSCountryNameEn() {
		return this.sCountryNameEn;
	}

	public void setSCountryNameEn(String sCountryNameEn) {
		this.sCountryNameEn = sCountryNameEn;
	}

	public String getSCountryNameVn() {
		return this.sCountryNameVn;
	}

	public void setSCountryNameVn(String sCountryNameVn) {
		this.sCountryNameVn = sCountryNameVn;
	}

}