package cob.com.partner.ws.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_groupbusiness_cate database table.
 * 
 */
@Entity
public class GroupbusinessCateInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="i_group_business_cate_icon")
	private String iGroupBusinessCateIcon;

	@Column(name="s_group_business_cate_id")
	private String sGroupBusinessCateId;

	@Column(name="s_group_business_cate_name_vn")
	private String sGroupBusinessCateNameVn;

	@Column(name="s_group_business_description_cn")
	private String sGroupBusinessDescriptionCn;

	@Column(name="s_group_business_description_en")
	private String sGroupBusinessDescriptionEn;

	@Column(name="s_group_business_description_vn")
	private String sGroupBusinessDescriptionVn;

	@Column(name="s_group_business_id")
	private String sGroupBusinessId;

	@Column(name="s_group_business_name_cate_cn")
	private String sGroupBusinessNameCateCn;

	@Column(name="s_group_business_name_cate_en")
	private String sGroupBusinessNameCateEn;
	
	@Column(name="s_partner_id")
	private String spartnerId;
	
	@Column(name="s_partner_bizcate_id")
	private String sPartnerBizcateId;
	
	@Column(name="n_is_activated")
	private String nIsActivated;

	public GroupbusinessCateInfo() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getIGroupBusinessCateIcon() {
		return this.iGroupBusinessCateIcon;
	}

	public void setIGroupBusinessCateIcon(String iGroupBusinessCateIcon) {
		this.iGroupBusinessCateIcon = iGroupBusinessCateIcon;
	}

	public String getSGroupBusinessCateId() {
		return this.sGroupBusinessCateId;
	}

	public void setSGroupBusinessCateId(String sGroupBusinessCateId) {
		this.sGroupBusinessCateId = sGroupBusinessCateId;
	}

	public String getSGroupBusinessCateNameVn() {
		return this.sGroupBusinessCateNameVn;
	}

	public void setSGroupBusinessCateNameVn(String sGroupBusinessCateNameVn) {
		this.sGroupBusinessCateNameVn = sGroupBusinessCateNameVn;
	}

	public String getSGroupBusinessDescriptionCn() {
		return this.sGroupBusinessDescriptionCn;
	}

	public void setSGroupBusinessDescriptionCn(String sGroupBusinessDescriptionCn) {
		this.sGroupBusinessDescriptionCn = sGroupBusinessDescriptionCn;
	}

	public String getSGroupBusinessDescriptionEn() {
		return this.sGroupBusinessDescriptionEn;
	}

	public void setSGroupBusinessDescriptionEn(String sGroupBusinessDescriptionEn) {
		this.sGroupBusinessDescriptionEn = sGroupBusinessDescriptionEn;
	}

	public String getSGroupBusinessDescriptionVn() {
		return this.sGroupBusinessDescriptionVn;
	}

	public void setSGroupBusinessDescriptionVn(String sGroupBusinessDescriptionVn) {
		this.sGroupBusinessDescriptionVn = sGroupBusinessDescriptionVn;
	}

	public String getSGroupBusinessId() {
		return this.sGroupBusinessId;
	}

	public void setSGroupBusinessId(String sGroupBusinessId) {
		this.sGroupBusinessId = sGroupBusinessId;
	}

	public String getSGroupBusinessNameCateCn() {
		return this.sGroupBusinessNameCateCn;
	}

	public void setSGroupBusinessNameCateCn(String sGroupBusinessNameCateCn) {
		this.sGroupBusinessNameCateCn = sGroupBusinessNameCateCn;
	}

	public String getSGroupBusinessNameCateEn() {
		return this.sGroupBusinessNameCateEn;
	}

	public void setSGroupBusinessNameCateEn(String sGroupBusinessNameCateEn) {
		this.sGroupBusinessNameCateEn = sGroupBusinessNameCateEn;
	}

	public String getSpartnerId() {
		return spartnerId;
	}

	public void setSpartnerId(String spartnerId) {
		this.spartnerId = spartnerId;
	}

	public String getsPartnerBizcateId() {
		return sPartnerBizcateId;
	}

	public void setsPartnerBizcateId(String sPartnerBizcateId) {
		this.sPartnerBizcateId = sPartnerBizcateId;
	}

	public String getnIsActivated() {
		return nIsActivated;
	}

	public void setnIsActivated(String nIsActivated) {
		this.nIsActivated = nIsActivated;
	}	
}