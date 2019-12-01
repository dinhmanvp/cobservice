package cob.com.cobservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cob.com.cobservice.ws.param.Parameter;


/**
 * The persistent class for the tb_advise_services database table.
 * 
 */
@Entity
@Table(name="tb_advise_services" )
@NamedQuery(name="TbAdviseService.findAll", query="SELECT t FROM TbAdviseService t")
public class TbAdviseService implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name="n_advise_service_is_available")
	private Integer nAdviseServiceIsAvailable;

	@Column(name="n_advise_service_rating")
	private BigDecimal nAdviseServiceRating;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_advise_service_icon")
	private String sAdviseServiceIcon;

	@Column(name="s_advise_service_name_cn")
	private String sAdviseServiceNameCn;

	@Column(name="s_advise_service_name_en")
	private String sAdviseServiceNameEn;

	@Column(name="s_advise_service_name_vn")
	private String sAdviseServiceNameVn;

	@Column(name="s_advise_service_shortdesc_cn")
	private String sAdviseServiceShortdescCn;

	@Column(name="s_advise_service_shortdesc_en")
	private String sAdviseServiceShortdescEn;

	@Column(name="s_advise_service_shortdesc_vn")
	private String sAdviseServiceShortdescVn;

	public TbAdviseService() {
	}

	public String getSAdviseServiceId() {
		return this.sAdviseServiceId;
	}

	public void setSAdviseServiceId(String sAdviseServiceId) {
		this.sAdviseServiceId = sAdviseServiceId;
	}

	public Integer getNAdviseServiceIsAvailable() {
		return this.nAdviseServiceIsAvailable;
	}

	public void setNAdviseServiceIsAvailable(Integer nAdviseServiceIsAvailable) {
		this.nAdviseServiceIsAvailable = nAdviseServiceIsAvailable;
	}

	public BigDecimal getNAdviseServiceRating() {
		return this.nAdviseServiceRating;
	}

	public void setNAdviseServiceRating(BigDecimal nAdviseServiceRating) {
		this.nAdviseServiceRating = nAdviseServiceRating;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSAdviseServiceIcon() {
		String imageUrl = "cobservice/" + Parameter.TABLE_ADVISE_SERVICE + "/" + this.sAdviseServiceId;
		return this.sAdviseServiceIcon == null ? "" : imageUrl;
	}

	public void setSAdviseServiceIcon(String sAdviseServiceIcon) {
		this.sAdviseServiceIcon = sAdviseServiceIcon;
	}

	public String getSAdviseServiceNameCn() {
		return this.sAdviseServiceNameCn;
	}

	public void setSAdviseServiceNameCn(String sAdviseServiceNameCn) {
		this.sAdviseServiceNameCn = sAdviseServiceNameCn;
	}

	public String getSAdviseServiceNameEn() {
		return this.sAdviseServiceNameEn;
	}

	public void setSAdviseServiceNameEn(String sAdviseServiceNameEn) {
		this.sAdviseServiceNameEn = sAdviseServiceNameEn;
	}

	public String getSAdviseServiceNameVn() {
		return this.sAdviseServiceNameVn;
	}

	public void setSAdviseServiceNameVn(String sAdviseServiceNameVn) {
		this.sAdviseServiceNameVn = sAdviseServiceNameVn;
	}

	public String getSAdviseServiceShortdescCn() {
		return this.sAdviseServiceShortdescCn;
	}

	public void setSAdviseServiceShortdescCn(String sAdviseServiceShortdescCn) {
		this.sAdviseServiceShortdescCn = sAdviseServiceShortdescCn;
	}

	public String getSAdviseServiceShortdescEn() {
		return this.sAdviseServiceShortdescEn;
	}

	public void setSAdviseServiceShortdescEn(String sAdviseServiceShortdescEn) {
		this.sAdviseServiceShortdescEn = sAdviseServiceShortdescEn;
	}

	public String getSAdviseServiceShortdescVn() {
		return this.sAdviseServiceShortdescVn;
	}

	public void setSAdviseServiceShortdescVn(String sAdviseServiceShortdescVn) {
		this.sAdviseServiceShortdescVn = sAdviseServiceShortdescVn;
	}

}