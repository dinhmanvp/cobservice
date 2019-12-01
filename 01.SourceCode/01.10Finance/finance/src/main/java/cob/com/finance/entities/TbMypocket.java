package cob.com.finance.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_mypocket database table.
 * 
 */
@Entity
@Table(name="tb_mypocket")
@NamedQuery(name="TbMypocket.findAll", query="SELECT t FROM TbMypocket t")
public class TbMypocket implements Serializable {
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

	public TbMypocket() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getNAvailableBalance() {
		return this.nAvailableBalance;
	}

	public void setNAvailableBalance(String nAvailableBalance) {
		this.nAvailableBalance = nAvailableBalance;
	}

	public String getNBalance() {
		return this.nBalance;
	}

	public void setNBalance(String nBalance) {
		this.nBalance = nBalance;
	}

	public String getNBlockedBalance() {
		return this.nBlockedBalance;
	}

	public void setNBlockedBalance(String nBlockedBalance) {
		this.nBlockedBalance = nBlockedBalance;
	}

	public String getSCurrencyId() {
		return this.sCurrencyId;
	}

	public void setSCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getSPocketId() {
		return this.sPocketId;
	}

	public void setSPocketId(String sPocketId) {
		this.sPocketId = sPocketId;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}