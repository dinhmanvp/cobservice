package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_partner_rules database table.
 * 
 */
@Entity
@Table(name="tb_partner_rules")
@NamedQuery(name="TbPartnerRule.findAll", query="SELECT t FROM TbPartnerRule t")
public class TbPartnerRule implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="n_id")
	@SequenceGenerator(name = "TbPartnerRule_sequence", sequenceName = "TbPartnerRule_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TbPartnerRule_sequence")
	private Integer nId;

	@Column(name="n_is_addnew")
	private Integer nIsAddnew;

	@Column(name="n_is_delete")
	private Integer nIsDelete;

	@Id
	@Column(name="s_partner_rule_id")
	private String sPartnerRuleId;

	@Column(name="s_rule_name_cn")
	private String sRuleNameCn;

	@Column(name="s_rule_name_en")
	private String sRuleNameEn;

	@Column(name="s_rule_name_vn")
	private String sRuleNameVn;

	public TbPartnerRule() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsAddnew() {
		return this.nIsAddnew;
	}

	public void setNIsAddnew(Integer nIsAddnew) {
		this.nIsAddnew = nIsAddnew;
	}

	public Integer getNIsDelete() {
		return this.nIsDelete;
	}

	public void setNIsDelete(Integer nIsDelete) {
		this.nIsDelete = nIsDelete;
	}

	public String getSPartnerRuleId() {
		return this.sPartnerRuleId;
	}

	public void setSPartnerRuleId(String sPartnerRuleId) {
		this.sPartnerRuleId = sPartnerRuleId;
	}

	public String getSRuleNameCn() {
		return this.sRuleNameCn;
	}

	public void setSRuleNameCn(String sRuleNameCn) {
		this.sRuleNameCn = sRuleNameCn;
	}

	public String getSRuleNameEn() {
		return this.sRuleNameEn;
	}

	public void setSRuleNameEn(String sRuleNameEn) {
		this.sRuleNameEn = sRuleNameEn;
	}

	public String getSRuleNameVn() {
		return this.sRuleNameVn;
	}

	public void setSRuleNameVn(String sRuleNameVn) {
		this.sRuleNameVn = sRuleNameVn;
	}

}