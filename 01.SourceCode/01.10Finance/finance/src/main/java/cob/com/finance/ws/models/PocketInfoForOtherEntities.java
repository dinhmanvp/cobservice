package cob.com.finance.ws.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PocketInfoForOtherEntities implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;
	
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
	
	@Column(name = "s_key")
	private String sKey;
	
	@Column(name = "s_currency_image")
	private String sCurrencyImage;
	
	@Column(name = "s_currency_symbol")
	private String sCurrencySymbol;	
	
	public String getsCurrencyImage() {
		return sCurrencyImage;
	}

	public void setsCurrencyImage(String sCurrencyImage) {
		this.sCurrencyImage = sCurrencyImage;
	}

	public String getsCurrencySymbol() {
		return sCurrencySymbol;
	}

	public void setsCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}

	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public String getsKey() {
		return sKey;
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

	public PocketInfoForOtherEntities() {
		super();
	}
	
}
