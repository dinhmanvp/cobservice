package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import cob.com.business.ws.param.Parameter;


/**
 * The persistent class for the tb_product_cate database table.
 * 
 */
@Entity
@Table(name="tb_product_cate")
@NamedQuery(name="TbProductCate.findAll", query="SELECT t FROM TbProductCate t")
public class TbProductCate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_partner_id")
	private String sPartnerId;

	@Column(name="s_product_cate_icon")
	private String sProductCateIcon;

	@Column(name="s_product_cate_id")
	private String sProductCateId;

	@Column(name="s_product_cate_name_cn")
	private String sProductCateNameCn;

	@Column(name="s_product_cate_name_en")
	private String sProductCateNameEn;

	@Column(name="s_product_cate_name_vn")
	private String sProductCateNameVn;

	public TbProductCate() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

	public String getSProductCateIcon() {
		//return this.sProductCateIcon;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_MY_PRODUCT_CATE + "/" + this.sProductCateId  + "/1";
		return this.sProductCateIcon == null?"":uri;
	}

	public void setSProductCateIcon(String sProductCateIcon) {
		this.sProductCateIcon = sProductCateIcon;
	}

	public String getSProductCateId() {
		return this.sProductCateId;
	}

	public void setSProductCateId(String sProductCateId) {
		this.sProductCateId = sProductCateId;
	}

	public String getSProductCateNameCn() {
		return this.sProductCateNameCn;
	}

	public void setSProductCateNameCn(String sProductCateNameCn) {
		this.sProductCateNameCn = sProductCateNameCn;
	}

	public String getSProductCateNameEn() {
		return this.sProductCateNameEn;
	}

	public void setSProductCateNameEn(String sProductCateNameEn) {
		this.sProductCateNameEn = sProductCateNameEn;
	}

	public String getSProductCateNameVn() {
		return this.sProductCateNameVn;
	}

	public void setSProductCateNameVn(String sProductCateNameVn) {
		this.sProductCateNameVn = sProductCateNameVn;
	}

}