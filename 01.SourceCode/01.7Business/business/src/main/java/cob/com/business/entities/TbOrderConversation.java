package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_order_conversation database table.
 * 
 */
@Entity
@Table(name="tb_order_conversation")
public class TbOrderConversation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="s_order_conversation_id")
	private String sOrderConversationId;

	@Temporal(TemporalType.DATE)
	@Column(name="d_like_date")
	private Date dLikeDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_id")
	private Integer nId;

	@Column(name="s_order_number")
	private String sOrderNumber;

	@Column(name="s_user_id")
	private String sUserId;

	public TbOrderConversation() {
	}

	public String getSOrderConversationId() {
		return this.sOrderConversationId;
	}

	public void setSOrderConversationId(String sOrderConversationId) {
		this.sOrderConversationId = sOrderConversationId;
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

	public String getSOrderNumber() {
		return this.sOrderNumber;
	}

	public void setSOrderNumber(String sOrderNumber) {
		this.sOrderNumber = sOrderNumber;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}