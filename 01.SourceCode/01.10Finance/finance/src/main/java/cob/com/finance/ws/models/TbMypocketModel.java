package cob.com.finance.ws.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * The persistent class for the tb_mypocket database table.
 * 
 */
@Entity
public class TbMypocketModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nid;

	@Column(name="n_available_balance")
	private String navailablebalance;

	@Column(name="n_balance")
	private String nbalance;

	@Column(name="n_blocked_balance")
	private String nblockedbalance;

	@Column(name="s_currency_id")
	private String scurrencyid;

	@Column(name="s_pocket_id")
	private String spocketid;

	@Column(name="s_user_id")
	private String suserid;
	
	@Column(name="s_currency_symbol")
	private String scurrencysymbol;
	
	@Column(name="s_currency_image")
	private String scurrencyimage;

	public TbMypocketModel() {
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getNavailablebalance() {
		return navailablebalance;
	}

	public void setNavailablebalance(String navailablebalance) {
		this.navailablebalance = navailablebalance;
	}

	public String getNbalance() {
		return nbalance;
	}

	public void setNbalance(String nbalance) {
		this.nbalance = nbalance;
	}

	public String getNblockedbalance() {
		return nblockedbalance;
	}

	public void setNblockedbalance(String nblockedbalance) {
		this.nblockedbalance = nblockedbalance;
	}

	public String getScurrencyid() {
		return scurrencyid;
	}

	public void setScurrencyid(String scurrencyid) {
		this.scurrencyid = scurrencyid;
	}

	public String getSpocketid() {
		return spocketid;
	}

	public void setSpocketid(String spocketid) {
		this.spocketid = spocketid;
	}

	public String getSuserid() {
		return suserid;
	}

	public void setSuserid(String suserid) {
		this.suserid = suserid;
	}

	public String getScurrencysymbol() {
		return scurrencysymbol;
	}

	public void setScurrencysymbol(String scurrencysymbol) {
		this.scurrencysymbol = scurrencysymbol;
	}

	public String getScurrencyimage() {
		return scurrencyimage;
	}

	public void setScurrencyimage(String scurrencyimage) {
		this.scurrencyimage = scurrencyimage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}