package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import cob.com.core.ws.param.Parameter;


/**
 * The persistent class for the tb_groupbusiness_cate database table.
 * 
 */
@Entity
@Table(name="tb_groupbusiness_cate")
@NamedQuery(name="TbGroupbusinessCate.findAll", query="SELECT t FROM TbGroupbusinessCate t")
public class TbGroupbusinessCate implements Serializable {
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

	public TbGroupbusinessCate() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getIGroupBusinessCateIcon() {
		//return this.iGroupBusinessCateIcon;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_GROUP_BUSINESS_CATE + "/" + this.sGroupBusinessCateId;
		return this.iGroupBusinessCateIcon == null?"":uri;
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

}