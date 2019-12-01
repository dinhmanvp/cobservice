package cob.com.core.ws.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_company_type database table.
 * 
 */
@Entity
public class CompanyTypeInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="s_company_type_id")
	private String scompanytypeid;

	@Column(name="s_company_type_name")
	private String scompanytypename;

	public CompanyTypeInfo() {
	}

	public String getScompanytypeid() {
		return scompanytypeid;
	}

	public void setScompanytypeid(String scompanytypeid) {
		this.scompanytypeid = scompanytypeid;
	}

	public String getScompanytypename() {
		return scompanytypename;
	}

	public void setScompanytypename(String scompanytypename) {
		this.scompanytypename = scompanytypename;
	}
	
}