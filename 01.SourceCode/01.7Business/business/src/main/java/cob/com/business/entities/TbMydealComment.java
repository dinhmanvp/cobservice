package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_mydeal_comments database table.
 * 
 */
@Entity
@Table(name="tb_mydeal_comments")
public class TbMydealComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_id")
	private Integer nId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_commented_date")
	private Date dCommentedDate;

	@Column(name="s_comments")
	private String sComments;

	@Column(name="s_mydeal_comment_id")
	private String sMydealCommentId;

	@Column(name="s_mydeal_id")
	private String sMydealId;

	@Column(name="s_user_id")
	private String sUserId;

	public TbMydealComment() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDCommentedDate() {
		return this.dCommentedDate;
	}

	public void setDCommentedDate(Date dCommentedDate) {
		this.dCommentedDate = dCommentedDate;
	}

	public String getSComments() {
		return this.sComments;
	}

	public void setSComments(String sComments) {
		this.sComments = sComments;
	}

	public String getSMydealCommentId() {
		return this.sMydealCommentId;
	}

	public void setSMydealCommentId(String sMydealCommentId) {
		this.sMydealCommentId = sMydealCommentId;
	}

	public String getSMydealId() {
		return this.sMydealId;
	}

	public void setSMydealId(String sMydealId) {
		this.sMydealId = sMydealId;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}