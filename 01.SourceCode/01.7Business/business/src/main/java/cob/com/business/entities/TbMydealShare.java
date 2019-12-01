package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_mydeal_share database table.
 * 
 */
@Entity
@Table(name="tb_mydeal_share")
public class TbMydealShare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="d_like_date")
	private Date dLikeDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_id")
	private Integer nId;
	
	@Column(name="s_mydeal_id")
	private String sMydealId;

	@Column(name="s_user_id")
	private String sUserId;

	public TbMydealShare() {
	}

	public Date getDLikeDate() {
		return this.dLikeDate;
	}

	public void setDLikeDate(Date dLikeDate) {
		this.dLikeDate = dLikeDate;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getsMydealId() {
		return sMydealId;
	}

	public void setsMydealId(String sMydealId) {
		this.sMydealId = sMydealId;
	}

	public String getsUserId() {
		return sUserId;
	}

	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}