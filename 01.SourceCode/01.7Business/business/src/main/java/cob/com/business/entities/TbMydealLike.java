package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_mydeal_like database table.
 * 
 */
@Entity
@Table(name="tb_mydeal_like")
public class TbMydealLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="d_like_date")
	private Date dLikeDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_id")
	private Integer nId;
	
	@Column(name="s_mydeal_like_id")
	private String sMydealLikeId;

	@Column(name="s_user_id")
	private String sUserId;

	public TbMydealLike() {
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

	public String getsMydealLikeId() {
		return sMydealLikeId;
	}

	public void setsMydealLikeId(String sMydealLikeId) {
		this.sMydealLikeId = sMydealLikeId;
	}

	public String getsUserId() {
		return sUserId;
	}

	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}