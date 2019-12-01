package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import cob.com.core.ws.param.Parameter;

import java.math.BigDecimal;

/**
 * The persistent class for the tb_group_business database table.
 * 
 */
@Entity
@Table(name = "tb_group_business")

@NamedQueries({ @NamedQuery(name = "TbGroupBusiness.findAll", query = "SELECT t FROM TbGroupBusiness t "),
		@NamedQuery(name = "TbGroupBusiness.findByIsHome", query = "SELECT t FROM TbGroupBusiness t where nHomeDisplay = 1"),
		// @NamedQuery(name="TbGroupBusiness.update1", query="UPDATE TbGroupBusiness t
		// set t.nHomeDisplay = 1 where t.sGroupBusinessId = :sGroupBusinessId")

})
public class TbGroupBusiness implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name = "i_group_business_icon")
	private String iGroupBusinessIcon;

	@Column(name = "n_group_business_availble")
	private Integer nGroupBusinessAvailble;

	@Column(name = "n_group_business_rating")
	private BigDecimal nGroupBusinessRating;

	@Column(name = "n_home_display")
	private Integer nHomeDisplay;

	@Column(name = "n_order")
	private Integer nOrder;

	@Column(name = "s_dashboard_id")
	private String sDashboardId;

	@Column(name = "s_group_business_desc_cn")
	private String sGroupBusinessDescCn;

	@Column(name = "s_group_business_desc_en")
	private String sGroupBusinessDescEn;

	@Column(name = "s_group_business_desc_shrink_cn")
	private String sGroupBusinessDescShrinkCn;

	@Column(name = "s_group_business_desc_shrink_en")
	private String sGroupBusinessDescShrinkEn;

	@Column(name = "s_group_business_desc_shrink_vn")
	private String sGroupBusinessDescShrinkVn;

	@Column(name = "s_group_business_desc_vn")
	private String sGroupBusinessDescVn;

	@Column(name = "s_group_business_id")
	private String sGroupBusinessId;

	@Column(name = "s_group_business_name_cn")
	private String sGroupBusinessNameCn;

	@Column(name = "s_group_business_name_en")
	private String sGroupBusinessNameEn;

	@Column(name = "s_group_business_name_vn")
	private String sGroupBusinessNameVn;

	public TbGroupBusiness() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getIGroupBusinessIcon() {
		//return this.iGroupBusinessIcon;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_GROUP_BUSINESS + "/" + this.sGroupBusinessId;
		return this.iGroupBusinessIcon == null?"":uri;
	}

	public void setIGroupBusinessIcon(String iGroupBusinessIcon) {
		this.iGroupBusinessIcon = iGroupBusinessIcon;
	}

	public Integer getNGroupBusinessAvailble() {
		return this.nGroupBusinessAvailble;
	}

	public void setNGroupBusinessAvailble(Integer nGroupBusinessAvailble) {
		this.nGroupBusinessAvailble = nGroupBusinessAvailble;
	}

	public BigDecimal getNGroupBusinessRating() {
		return this.nGroupBusinessRating;
	}

	public void setNGroupBusinessRating(BigDecimal nGroupBusinessRating) {
		this.nGroupBusinessRating = nGroupBusinessRating;
	}

	public Integer getNHomeDisplay() {
		return this.nHomeDisplay;
	}

	public void setNHomeDisplay(Integer nHomeDisplay) {
		this.nHomeDisplay = nHomeDisplay;
	}

	public Integer getNOrder() {
		return this.nOrder;
	}

	public void setNOrder(Integer nOrder) {
		this.nOrder = nOrder;
	}

	public String getSDashboardId() {
		return this.sDashboardId;
	}

	public void setSDashboardId(String sDashboardId) {
		this.sDashboardId = sDashboardId;
	}

	public String getSGroupBusinessDescCn() {
		return this.sGroupBusinessDescCn;
	}

	public void setSGroupBusinessDescCn(String sGroupBusinessDescCn) {
		this.sGroupBusinessDescCn = sGroupBusinessDescCn;
	}

	public String getSGroupBusinessDescEn() {
		return this.sGroupBusinessDescEn;
	}

	public void setSGroupBusinessDescEn(String sGroupBusinessDescEn) {
		this.sGroupBusinessDescEn = sGroupBusinessDescEn;
	}

	public String getSGroupBusinessDescShrinkCn() {
		return this.sGroupBusinessDescShrinkCn;
	}

	public void setSGroupBusinessDescShrinkCn(String sGroupBusinessDescShrinkCn) {
		this.sGroupBusinessDescShrinkCn = sGroupBusinessDescShrinkCn;
	}

	public String getSGroupBusinessDescShrinkEn() {
		return this.sGroupBusinessDescShrinkEn;
	}

	public void setSGroupBusinessDescShrinkEn(String sGroupBusinessDescShrinkEn) {
		this.sGroupBusinessDescShrinkEn = sGroupBusinessDescShrinkEn;
	}

	public String getSGroupBusinessDescShrinkVn() {
		return this.sGroupBusinessDescShrinkVn;
	}

	public void setSGroupBusinessDescShrinkVn(String sGroupBusinessDescShrinkVn) {
		this.sGroupBusinessDescShrinkVn = sGroupBusinessDescShrinkVn;
	}

	public String getSGroupBusinessDescVn() {
		return this.sGroupBusinessDescVn;
	}

	public void setSGroupBusinessDescVn(String sGroupBusinessDescVn) {
		this.sGroupBusinessDescVn = sGroupBusinessDescVn;
	}

	public String getSGroupBusinessId() {
		return this.sGroupBusinessId;
	}

	public void setSGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public String getSGroupBusinessNameCn() {
		return this.sGroupBusinessNameCn;
	}

	public void setSGroupBusinessNameCn(String sGroupBusinessNameCn) {
		this.sGroupBusinessNameCn = sGroupBusinessNameCn;
	}

	public String getSGroupBusinessNameEn() {
		return this.sGroupBusinessNameEn;
	}

	public void setSGroupBusinessNameEn(String sGroupBusinessNameEn) {
		this.sGroupBusinessNameEn = sGroupBusinessNameEn;
	}

	public String getSGroupBusinessNameVn() {
		return this.sGroupBusinessNameVn;
	}

	public void setSGroupBusinessNameVn(String sGroupBusinessNameVn) {
		this.sGroupBusinessNameVn = sGroupBusinessNameVn;
	}

}