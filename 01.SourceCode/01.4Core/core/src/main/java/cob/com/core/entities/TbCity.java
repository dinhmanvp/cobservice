package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_city database table.
 * 
 */
@Entity
@Table(name="tb_city")
@NamedQuery(name="TbCity.findAll", query="SELECT t FROM TbCity t")
public class TbCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="i_city_map_latitude")
	private BigDecimal iCityMapLatitude;

	@Column(name="i_city_map_longitude")
	private BigDecimal iCityMapLongitude;

	@Column(name="s_city_id")
	private String sCityId;

	@Column(name="s_city_name_cn")
	private String sCityNameCn;

	@Column(name="s_city_name_en")
	private String sCityNameEn;

	@Column(name="s_city_name_vn")
	private String sCityNameVn;

	@Column(name="s_country_id")
	private String sCountryId;

	public TbCity() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public BigDecimal getICityMapLatitude() {
		return this.iCityMapLatitude;
	}

	public void setICityMapLatitude(BigDecimal iCityMapLatitude) {
		this.iCityMapLatitude = iCityMapLatitude;
	}

	public BigDecimal getICityMapLongitude() {
		return this.iCityMapLongitude;
	}

	public void setICityMapLongitude(BigDecimal iCityMapLongitude) {
		this.iCityMapLongitude = iCityMapLongitude;
	}

	public String getSCityId() {
		return this.sCityId;
	}

	public void setSCityId(String sCityId) {
		this.sCityId = sCityId;
	}

	public String getSCityNameCn() {
		return this.sCityNameCn;
	}

	public void setSCityNameCn(String sCityNameCn) {
		this.sCityNameCn = sCityNameCn;
	}

	public String getSCityNameEn() {
		return this.sCityNameEn;
	}

	public void setSCityNameEn(String sCityNameEn) {
		this.sCityNameEn = sCityNameEn;
	}

	public String getSCityNameVn() {
		return this.sCityNameVn;
	}

	public void setSCityNameVn(String sCityNameVn) {
		this.sCityNameVn = sCityNameVn;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
	}

}