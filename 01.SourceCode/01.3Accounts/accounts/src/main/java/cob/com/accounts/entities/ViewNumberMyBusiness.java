


package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cus_user database table.
 * 
 */
@Entity
@Table(name="number_my_business")
@NamedQuery(name="ViewNumberMyBusiness.findAll", query="SELECT c FROM ViewNumberMyBusiness c")
public class ViewNumberMyBusiness implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="sbusnessserviceid")
	private String sBusnessserviceid;

	@Column(name="number")
	private Integer Number;

	

	public ViewNumberMyBusiness() {
	}

	public String getSBusnessserviceid() {
		return this.sBusnessserviceid;
	}

	public void setSBusnessserviceid(String sBusnessserviceid) {
		this.sBusnessserviceid = sBusnessserviceid;
	}
	

	public Integer getSnumber() {
		return this.Number;
	}

	public void setSUsername(Integer Number) {
		this.Number = Number;
	}

}