package cob.com.finance.ws.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PocketInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;
	
	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	@Column(name="n_available_balance")
	private String nAvailableBalance;

	@Column(name="n_balance")
	private String nBalance;

	@Column(name="n_blocked_balance")
	private String nBlockedBalance;

	@Column(name="s_currency_id")
	private String sCurrencyId;

	@Column(name="s_pocket_id")
	private String sPocketId;

	@Column(name="s_user_id")
	private String sUserId;
	
	@Column(name = "limit_balance")
	private String limitBalance;
	
	@Column(name = "s_key")
	private String sKey;
	
	@Column(name = "s_username")
	private String sUsername;

	public String getsKey() {
		return sKey;
	}

	public String getsUsername() {
		return sUsername;
	}

	public void setsUsername(String sUsername) {
		this.sUsername = sUsername;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public String getnAvailableBalance() {
		return nAvailableBalance;
	}

	public void setnAvailableBalance(String nAvailableBalance) {
		this.nAvailableBalance = nAvailableBalance;
	}

	public String getnBalance() {
		return nBalance;
	}

	public void setnBalance(String nBalance) {
		this.nBalance = nBalance;
	}

	public String getnBlockedBalance() {
		return nBlockedBalance;
	}

	public void setnBlockedBalance(String nBlockedBalance) {
		this.nBlockedBalance = nBlockedBalance;
	}

	public String getsCurrencyId() {
		return sCurrencyId;
	}

	public void setsCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getsPocketId() {
		return sPocketId;
	}

	public void setsPocketId(String sPocketId) {
		this.sPocketId = sPocketId;
	}

	public String getsUserId() {
		return sUserId;
	}

	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getLimitBalance() {
		return limitBalance;
	}

	public void setLimitBalance(String limitBalance) {
		this.limitBalance = limitBalance;
	}

	public PocketInfo() {
		super();
	}
	
}
