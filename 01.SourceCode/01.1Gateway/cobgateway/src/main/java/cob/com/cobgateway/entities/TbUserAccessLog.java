package cob.com.cobgateway.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_user_access_log database table.
 * 
 */
@Entity
@Table(name="tb_user_access_log",schema="mdl_gateway")
@NamedQuery(name="TbUserAccessLog.findAll", query="SELECT t FROM TbUserAccessLog t")
public class TbUserAccessLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="d_access_date")
	private Date dAccessDate;

	@Column(name="s_channel")
	private String sChannel;

	@Column(name="s_function_id")
	private String sFunctionId;

	@Column(name="s_ip_access")
	private String sIpAccess;

	@Column(name="s_result")
	private String sResult;

	@Column(name="s_user_id")
	private String sUserId;

	public TbUserAccessLog() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDAccessDate() {
		return this.dAccessDate;
	}

	public void setDAccessDate(Date dAccessDate) {
		this.dAccessDate = dAccessDate;
	}

	public String getSChannel() {
		return this.sChannel;
	}

	public void setSChannel(String sChannel) {
		this.sChannel = sChannel;
	}

	public String getSFunctionId() {
		return this.sFunctionId;
	}

	public void setSFunctionId(String sFunctionId) {
		this.sFunctionId = sFunctionId;
	}

	public String getSIpAccess() {
		return this.sIpAccess;
	}

	public void setSIpAccess(String sIpAccess) {
		this.sIpAccess = sIpAccess;
	}

	public String getSResult() {
		return this.sResult;
	}

	public void setSResult(String sResult) {
		this.sResult = sResult;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}