package cob.com.communication.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_template database table.
 * 
 */
@Entity
@Table(name="tb_template")
@NamedQuery(name="TbTemplate.findAll", query="SELECT t FROM TbTemplate t")
public class TbTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="s_content")
	private String sContent;

	@Column(name="s_name")
	private String sName;
	
	@Column(name="s_params")
	private String sParmas;
	
	@Column(name="s_subject")
	private String sSubject;

	public String getsSubject() {
		return sSubject;
	}

	public void setsSubject(String sSubject) {
		this.sSubject = sSubject;
	}

	public String getsParmas() {
		return sParmas;
	}

	public void setsParmas(String sParmas) {
		this.sParmas = sParmas;
	}

	public TbTemplate() {
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

}