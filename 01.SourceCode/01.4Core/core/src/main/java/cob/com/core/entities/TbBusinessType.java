package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_business_type database table.
 * 
 */
@Entity
@Table(name="tb_business_type")
@NamedQuery(name="TbBusinessType.findAll", query="SELECT t FROM TbBusinessType t")
public class TbBusinessType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_business_type_desc_cn")
	private String sBusinessTypeDescCn;

	@Column(name="s_business_type_desc_en")
	private String sBusinessTypeDescEn;

	@Column(name="s_business_type_desc_vn")
	private String sBusinessTypeDescVn;

	@Column(name="s_business_type_id")
	private String sBusinessTypeId;

	@Column(name="s_business_type_name_cn")
	private String sBusinessTypeNameCn;

	@Column(name="s_business_type_name_en")
	private String sBusinessTypeNameEn;

	@Column(name="s_business_type_name_vn")
	private String sBusinessTypeNameVn;
	
	@Column(name="s_group_business_id")
	private String sBusinessGroupId;

	@Column(name="n_order")
	private String nOrder;

	public TbBusinessType() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSBusinessTypeDescCn() {
		return this.sBusinessTypeDescCn;
	}

	public void setSBusinessTypeDescCn(String sBusinessTypeDescCn) {
		this.sBusinessTypeDescCn = sBusinessTypeDescCn;
	}

	public String getSBusinessTypeDescEn() {
		return this.sBusinessTypeDescEn;
	}

	public void setSBusinessTypeDescEn(String sBusinessTypeDescEn) {
		this.sBusinessTypeDescEn = sBusinessTypeDescEn;
	}

	public String getSBusinessTypeDescVn() {
		return this.sBusinessTypeDescVn;
	}

	public void setSBusinessTypeDescVn(String sBusinessTypeDescVn) {
		this.sBusinessTypeDescVn = sBusinessTypeDescVn;
	}

	public String getSBusinessTypeId() {
		return this.sBusinessTypeId;
	}

	public void setSBusinessTypeId(String sBusinessTypeId) {
		this.sBusinessTypeId = sBusinessTypeId;
	}

	public String getSBusinessTypeNameCn() {
		return this.sBusinessTypeNameCn;
	}

	public void setSBusinessTypeNameCn(String sBusinessTypeNameCn) {
		this.sBusinessTypeNameCn = sBusinessTypeNameCn;
	}

	public String getSBusinessTypeNameEn() {
		return this.sBusinessTypeNameEn;
	}

	public void setSBusinessTypeNameEn(String sBusinessTypeNameEn) {
		this.sBusinessTypeNameEn = sBusinessTypeNameEn;
	}

	public String getSBusinessTypeNameVn() {
		return this.sBusinessTypeNameVn;
	}

	public void setSBusinessTypeNameVn(String sBusinessTypeNameVn) {
		this.sBusinessTypeNameVn = sBusinessTypeNameVn;
	}
	public String getsBusinessGroupId() {
		return sBusinessGroupId;
	}

	public void setsBusinessGroupId(String sBusinessGroupId) {
		this.sBusinessGroupId = sBusinessGroupId;
	}

	public String getnOrder() {
		return nOrder;
	}

	public void setnOrder(String nOrder) {
		this.nOrder = nOrder;
	}

}