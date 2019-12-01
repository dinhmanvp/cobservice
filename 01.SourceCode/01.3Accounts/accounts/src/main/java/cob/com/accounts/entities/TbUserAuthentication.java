package cob.com.accounts.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_user_authentication database table.
 * 
 */
@Entity
@Table(name="tb_user_authentication")
@NamedQuery(name="TbUserAuthentication.findAll", query="SELECT t FROM TbUserAuthentication t")
public class TbUserAuthentication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@SequenceGenerator(name = "TbUserAuthentication_sequence", sequenceName = "TbUserAuthentication_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TbUserAuthentication_sequence")
	private Integer nId;

	@Column(name="b_is_exprited")
	private Integer bIsExprited;

	@Column(name="bi_authen_index")
	private Long biAuthenIndex;

	@Column(name="d_created_date")
	private Timestamp dCreatedDate;

	@Column(name="s_privatetoken")
	private String sPrivatetoken;
	
	@Column(name="s_user_id")
	private String sUserId;

	public TbUserAuthentication() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getBIsExprited() {
		return this.bIsExprited;
	}

	public void setBIsExprited(Integer bIsExprited) {
		this.bIsExprited = bIsExprited;
	}

	public Long getBiAuthenIndex() {
		return this.biAuthenIndex;
	}

	public void setBiAuthenIndex(Long biAuthenIndex) {
		this.biAuthenIndex = biAuthenIndex;
	}

	public Timestamp getDCreatedDate() {
		return this.dCreatedDate;
	}

	public void setDCreatedDate(Timestamp dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	public String getSPrivatetoken() {
		return this.sPrivatetoken;
	}

	public void setSPrivatetoken(String sPrivatetoken) {
		this.sPrivatetoken = sPrivatetoken;
	}
	
	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}