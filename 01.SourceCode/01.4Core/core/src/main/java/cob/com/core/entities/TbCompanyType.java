package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_company_type database table.
 * 
 */
@Entity
@Table(name="tb_company_type")
public class TbCompanyType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nid;

	@Column(name="s_company_type_id")
	private String scompanytypeid;

	@Column(name="s_company_type_name_cn")
	private String scompanytypenamecn;

	@Column(name="s_company_type_name_en")
	private String scompanytypenameen;

	@Column(name="s_company_type_name_vn")
	private String scompanytypenamevn;

	public TbCompanyType() {
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getScompanytypeid() {
		return scompanytypeid;
	}

	public void setScompanytypeid(String scompanytypeid) {
		this.scompanytypeid = scompanytypeid;
	}

	public String getScompanytypenamecn() {
		return scompanytypenamecn;
	}

	public void setScompanytypenamecn(String scompanytypenamecn) {
		this.scompanytypenamecn = scompanytypenamecn;
	}

	public String getScompanytypenameen() {
		return scompanytypenameen;
	}

	public void setScompanytypenameen(String scompanytypenameen) {
		this.scompanytypenameen = scompanytypenameen;
	}

	public String getScompanytypenamevn() {
		return scompanytypenamevn;
	}

	public void setScompanytypenamevn(String scompanytypenamevn) {
		this.scompanytypenamevn = scompanytypenamevn;
	}
}