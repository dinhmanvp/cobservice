package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_partner_bizcate database table.
 * 
 */
@Entity
@Table(name = "tb_partner_bizcate")
@NamedQuery(name = "TbPartnerBizcate.findAll", query = "SELECT t FROM TbPartnerBizcate t")
public class TbPartnerBizcate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	@SequenceGenerator(name = "TbPartnerBizcate_sequence", sequenceName = "TbPartnerBizcate_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TbPartnerBizcate_sequence")
	private Integer nId;

	@Column(name = "s_group_business_cate_id")
	private String sGroupBusinessCateId;

	@Column(name = "s_partner_bizcate_id")
	private String sPartnerBizcateId;

	@Column(name = "s_partner_id")
	private String sPartnerId;

	@Column(name = "n_is_activated")
	private Integer nIsActivated;

	@Column(name = "s_group_business_cate_name_vn", insertable = false, updatable = false)
	private String sGroupBusinessCateNameVn;

	@Column(name = "s_group_business_name_cate_en", insertable = false, updatable = false)
	private String sGroupBusinessCateNameEn;

	@Column(name = "s_group_business_name_cate_cn", insertable = false, updatable = false)
	private String sGroupBusinessCateNameCn;

	public TbPartnerBizcate() {
	}

	public String getsGroupBusinessCateNameCn() {
		return sGroupBusinessCateNameCn == null ? "" : sGroupBusinessCateNameCn;
	}

	public void setsGroupBusinessCateNameCn(String sGroupBusinessCateNameCn) {
		this.sGroupBusinessCateNameCn = sGroupBusinessCateNameCn;
	}

	public String getsGroupBusinessCateNameEn() {
		return sGroupBusinessCateNameEn == null ? "" : sGroupBusinessCateNameEn;
	}

	public void setsGroupBusinessCateNameEn(String sGroupBusinessCateNameEn) {
		this.sGroupBusinessCateNameEn = sGroupBusinessCateNameEn;
	}

	public String getsGroupBusinessCateNameVn() {
		return sGroupBusinessCateNameVn == null ? "" : sGroupBusinessCateNameVn;
	}

	public void setsGroupBusinessCateNameVn(String sGroupBusinessCateNameVn) {
		this.sGroupBusinessCateNameVn = sGroupBusinessCateNameVn;
	}

	public Integer getNIsActivated() {
		return this.nIsActivated;
	}

	public void setNIsActivated(Integer nIsActivated) {
		this.nIsActivated = nIsActivated;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSGroupBusinessCateId() {
		return this.sGroupBusinessCateId;
	}

	public void setSGroupBusinessCateId(String sGroupBusinessCateId) {
		this.sGroupBusinessCateId = sGroupBusinessCateId;
	}

	public String getSPartnerBizcateId() {
		return this.sPartnerBizcateId;
	}

	public void setSPartnerBizcateId(String sPartnerBizcateId) {
		this.sPartnerBizcateId = sPartnerBizcateId;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

}