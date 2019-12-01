package cob.com.partner.ws.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_partner_bizcate database table.
 * 
 */
@Entity
public class PartnerBizcateInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="s_group_business_cate_id")
	private String sgroupbusinesscateid;

	
	@Column(name="s_group_business_cate_name")
	private String sgroupbusinesscatename;

	@Column(name="s_group_business_description")
	private String sgroupbusinessdescription;
	
	@Column(name="i_group_business_cate_icon")
	private String igroupbusinesscateicon;
	
	@Column(name="n_count_order")
	private Integer ncountorder;

	public Integer getNcountorder() {
		return ncountorder;
	}

	public void setNcountorder(Integer ncountorder) {
		this.ncountorder = ncountorder;
	}

	public PartnerBizcateInfo() {
	}

	public String getSgroupbusinesscateid() {
		return sgroupbusinesscateid;
	}

	public void setSgroupbusinesscateid(String sgroupbusinesscateid) {
		this.sgroupbusinesscateid = sgroupbusinesscateid;
	}

	public String getSgroupbusinesscatename() {
		return sgroupbusinesscatename;
	}

	public void setSgroupbusinesscatename(String sgroupbusinesscatename) {
		this.sgroupbusinesscatename = sgroupbusinesscatename;
	}

	public String getSgroupbusinessdescription() {
		return sgroupbusinessdescription;
	}

	public void setSgroupbusinessdescription(String sgroupbusinessdescription) {
		this.sgroupbusinessdescription = sgroupbusinessdescription;
	}

	public String getIgroupbusinesscateicon() {
		return igroupbusinesscateicon;
	}

	public void setIgroupbusinesscateicon(String igroupbusinesscateicon) {
		this.igroupbusinesscateicon = igroupbusinesscateicon;
	}
}