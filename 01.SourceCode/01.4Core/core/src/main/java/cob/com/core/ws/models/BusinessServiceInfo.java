package cob.com.core.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cob.com.core.ws.param.Parameter;

/**
 * The persistent class for the tb_business_services database table.
 * 
 */
@Entity
public class BusinessServiceInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "i_business_service_icon")
	private String iBusinessServiceIcon;

	@Column(name = "n_business_service_available")
	private Integer nBusinessServiceAvailable;

	@Column(name = "n_business_service_rating")
	private BigDecimal nBusinessServiceRating;

	@Column(name = "n_order")
	private Integer nOrder;

	@Column(name = "s_business_service_desc_cn")
	private String sBusinessServiceDescCn;

	@Column(name = "s_business_service_desc_en")
	private String sBusinessServiceDescEn;

	@Column(name = "s_business_service_desc_shrink_cn")
	private String sBusinessServiceDescShrinkCn;

	@Column(name = "s_business_service_desc_shrink_en")
	private String sBusinessServiceDescShrinkEn;

	@Column(name = "s_business_service_desc_shrink_vn")
	private String sBusinessServiceDescShrinkVn;

	@Column(name = "s_business_service_desc_vn")
	private String sBusinessServiceDescVn;

	@Column(name = "s_business_service_id")
	private String sBusinessServiceId;

	@Column(name = "s_business_service_name_cn")
	private String sBusinessServiceNameCn;

	@Column(name = "s_business_service_name_en")
	private String sBusinessServiceNameEn;

	@Column(name = "s_business_service_name_vn")
	private String sBusinessServiceNameVn;

	@Column(name = "s_group_business_id")
	private String sGroupBusinessId;
	
	@Column(name = "number_partners")
	private Long numberParnes;

	public BusinessServiceInfo() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getIBusinessServiceIcon() {
		//return this.iBusinessServiceIcon;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_BUSINESS_SERVICE + "/" + this.sBusinessServiceId;
		return this.iBusinessServiceIcon == null?"":uri;
	}

	public void setIBusinessServiceIcon(String iBusinessServiceIcon) {
		this.iBusinessServiceIcon = iBusinessServiceIcon;
	}

	public Integer getNBusinessServiceAvailable() {
		return this.nBusinessServiceAvailable;
	}

	public void setNBusinessServiceAvailable(Integer nBusinessServiceAvailable) {
		this.nBusinessServiceAvailable = nBusinessServiceAvailable;
	}

	public BigDecimal getNBusinessServiceRating() {
		return this.nBusinessServiceRating;
	}

	public void setNBusinessServiceRating(BigDecimal nBusinessServiceRating) {
		this.nBusinessServiceRating = nBusinessServiceRating;
	}

	public Integer getNOrder() {
		return this.nOrder;
	}

	public void setNOrder(Integer nOrder) {
		this.nOrder = nOrder;
	}

	public String getSBusinessServiceDescCn() {
		return this.sBusinessServiceDescCn;
	}

	public void setSBusinessServiceDescCn(String sBusinessServiceDescCn) {
		this.sBusinessServiceDescCn = sBusinessServiceDescCn;
	}

	public String getSBusinessServiceDescEn() {
		return this.sBusinessServiceDescEn;
	}

	public void setSBusinessServiceDescEn(String sBusinessServiceDescEn) {
		this.sBusinessServiceDescEn = sBusinessServiceDescEn;
	}

	public String getSBusinessServiceDescShrinkCn() {
		return this.sBusinessServiceDescShrinkCn;
	}

	public void setSBusinessServiceDescShrinkCn(String sBusinessServiceDescShrinkCn) {
		this.sBusinessServiceDescShrinkCn = sBusinessServiceDescShrinkCn;
	}

	public String getSBusinessServiceDescShrinkEn() {
		return this.sBusinessServiceDescShrinkEn;
	}

	public void setSBusinessServiceDescShrinkEn(String sBusinessServiceDescShrinkEn) {
		this.sBusinessServiceDescShrinkEn = sBusinessServiceDescShrinkEn;
	}

	public String getSBusinessServiceDescShrinkVn() {
		return this.sBusinessServiceDescShrinkVn;
	}

	public void setSBusinessServiceDescShrinkVn(String sBusinessServiceDescShrinkVn) {
		this.sBusinessServiceDescShrinkVn = sBusinessServiceDescShrinkVn;
	}

	public String getSBusinessServiceDescVn() {
		return this.sBusinessServiceDescVn;
	}

	public void setSBusinessServiceDescVn(String sBusinessServiceDescVn) {
		this.sBusinessServiceDescVn = sBusinessServiceDescVn;
	}

	public String getSBusinessServiceId() {
		return this.sBusinessServiceId;
	}

	public void setSBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSBusinessServiceNameCn() {
		return this.sBusinessServiceNameCn;
	}

	public void setSBusinessServiceNameCn(String sBusinessServiceNameCn) {
		this.sBusinessServiceNameCn = sBusinessServiceNameCn;
	}

	public String getSBusinessServiceNameEn() {
		return this.sBusinessServiceNameEn;
	}

	public void setSBusinessServiceNameEn(String sBusinessServiceNameEn) {
		this.sBusinessServiceNameEn = sBusinessServiceNameEn;
	}

	public String getSBusinessServiceNameVn() {
		return this.sBusinessServiceNameVn;
	}

	public void setSBusinessServiceNameVn(String sBusinessServiceNameVn) {
		this.sBusinessServiceNameVn = sBusinessServiceNameVn;
	}

	public String getSGroupBusinessId() {
		return this.sGroupBusinessId;
	}

	public void setSGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public Long getNumberParnes() {
		return numberParnes;
	}

	public void setNumberParnes(Long numberParnes) {
		this.numberParnes = numberParnes;
	}
	
}