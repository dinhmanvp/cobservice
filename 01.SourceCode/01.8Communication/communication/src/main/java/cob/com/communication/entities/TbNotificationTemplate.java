package cob.com.communication.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_notification_template database table.
 * 
 */
@Entity
@Table(name = "tb_notification_template")
@NamedQuery(name = "TbNotificationTemplate.findAll", query = "SELECT t FROM TbNotificationTemplate t")
public class TbNotificationTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "s_content")
	private String sContent;

	@Column(name = "s_name")
	private String sName;

	@Column(name = "s_params")
	private String sParams;

	public TbNotificationTemplate() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSContent() {
		return this.sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	public String getSName() {
		return this.sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public String getSParams() {
		return this.sParams;
	}

	public void setSParams(String sParams) {
		this.sParams = sParams;
	}

}