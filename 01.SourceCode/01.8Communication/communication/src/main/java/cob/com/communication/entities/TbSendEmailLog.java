package cob.com.communication.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_send_email_log database table.
 * 
 */
@Entity
@Table(name="tb_send_email_log")
@NamedQuery(name="TbSendEmailLog.findAll", query="SELECT t FROM TbSendEmailLog t")
public class TbSendEmailLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="d_date_send")
	private Date dDateSend;

	@Column(name="s_template_name")
	private String sTemplateName;

	@Column(name="s_params_value")
	private String sParamsValue;

	@Column(name="s_username")
	private String sUsername;

	public TbSendEmailLog() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDDateSend() {
		return this.dDateSend;
	}

	public void setDDateSend(Date dDateSend) {
		this.dDateSend = dDateSend;
	}

	public String getsTemplateName() {
		return sTemplateName;
	}

	public void setsTemplateName(String sTemplateName) {
		this.sTemplateName = sTemplateName;
	}

	public String getSParamsValue() {
		return this.sParamsValue;
	}

	public void setSParamsValue(String sParamsValue) {
		this.sParamsValue = sParamsValue;
	}

	public String getSUsername() {
		return this.sUsername;
	}

	public void setSUsername(String sUsername) {
		this.sUsername = sUsername;
	}

}