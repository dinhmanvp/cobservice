package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_acounts database table.
 * 
 */
@Entity
@Table(name="tb_accounts")
public class TbAcount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Long nId;

	@Column(name="s_user_id")
	private String sUserId;

	@Column(name="s_user_name")
	private String sUserName;

	public TbAcount() {
	}

	public Long getNId() {
		return this.nId;
	}

	public void setNId(Long nId) {
		this.nId = nId;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getSUserName() {
		return this.sUserName;
	}

	public void setSUserName(String sUserName) {
		this.sUserName = sUserName;
	}

}