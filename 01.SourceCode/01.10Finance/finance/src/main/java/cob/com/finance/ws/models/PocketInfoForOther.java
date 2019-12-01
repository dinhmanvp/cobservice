package cob.com.finance.ws.models;

import java.io.Serializable;

public class PocketInfoForOther implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer nId;
	private String nAvailableBalance;
	private String nBalance;
	private String nBlockedBalance;
	private String sCurrencyId;
	private String sPocketId;
	private String sUserId;
	private String sCurrencyImage;
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

	public PocketInfoForOther() {
		super();
	}
	
}
