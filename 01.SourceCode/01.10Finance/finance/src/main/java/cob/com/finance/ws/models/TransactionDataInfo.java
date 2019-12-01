package cob.com.finance.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@Entity
public class TransactionDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="s_currency_id")
	private String scurrencyid;
	
	@Column(name="limit_balance")
	private String limitbalance;
	
	@Column(name="s_key_from")
	private String skeyfrom;
	
	@Column(name="n_balance_from")
	private String nbalancefrom;
	
	@Column(name="n_available_balance_from")
	private String navailablebalancefrom;
	
	@Column(name="n_blocked_balance_from")
	private String nblockedbalancefrom;
	
	@Column(name="s_username_from")
	private String susernamefrom;
	
	@Column(name="s_key_to")
	private String skeyto;
	
	@Column(name="n_balance_to")
	private String nbalanceto;
	
	@Column(name="n_available_balance_to")
	private String navailablebalanceto;
	
	@Column(name="n_blocked_balance_to")
	private String nblockedbalanceto;
	
	@Column(name="s_pocket_id_from")
	private String spocketidfrom;

	@Column(name="n_pocket_id_to")
	private String npocketidto;

	//nid
	@Column(name="n_id_pocket_from")
	private Integer nidpocketfrom;
	@Column(name="n_id_pocket_to")
	private Integer nidpocketto;
	
	public String getSpocketidfrom() {
		return spocketidfrom;
	}

	public void setSpocketidfrom(String spocketidfrom) {
		this.spocketidfrom = spocketidfrom;
	}

	public String getNpocketidto() {
		return npocketidto;
	}

	public void setNpocketidto(String npocketidto) {
		this.npocketidto = npocketidto;
	}

	public Integer getNidpocketfrom() {
		return nidpocketfrom;
	}

	public void setNidpocketfrom(Integer nidpocketfrom) {
		this.nidpocketfrom = nidpocketfrom;
	}

	public Integer getNidpocketto() {
		return nidpocketto;
	}

	public void setNidpocketto(Integer nidpocketto) {
		this.nidpocketto = nidpocketto;
	}

	public TransactionDataInfo() {
		super();
	}

	public String getScurrencyid() {
		return scurrencyid;
	}

	public void setScurrencyid(String scurrencyid) {
		this.scurrencyid = scurrencyid;
	}

	public String getLimitbalance() {
		return limitbalance;
	}

	public void setLimitbalance(String limitbalance) {
		this.limitbalance = limitbalance;
	}

	public String getSkeyfrom() {
		return skeyfrom;
	}

	public void setSkeyfrom(String skeyfrom) {
		this.skeyfrom = skeyfrom;
	}

	public String getNbalancefrom() {
		return nbalancefrom;
	}

	public void setNbalancefrom(String nbalancefrom) {
		this.nbalancefrom = nbalancefrom;
	}

	public String getNavailablebalancefrom() {
		return navailablebalancefrom;
	}

	public void setNavailablebalancefrom(String navailablebalancefrom) {
		this.navailablebalancefrom = navailablebalancefrom;
	}

	public String getNblockedbalancefrom() {
		return nblockedbalancefrom;
	}

	public void setNblockedbalancefrom(String nblockedbalancefrom) {
		this.nblockedbalancefrom = nblockedbalancefrom;
	}

	public String getSusernamefrom() {
		return susernamefrom;
	}

	public void setSusernamefrom(String susernamefrom) {
		this.susernamefrom = susernamefrom;
	}

	public String getSkeyto() {
		return skeyto;
	}

	public void setSkeyto(String skeyto) {
		this.skeyto = skeyto;
	}

	public String getNbalanceto() {
		return nbalanceto;
	}

	public void setNbalanceto(String nbalanceto) {
		this.nbalanceto = nbalanceto;
	}

	public String getNavailablebalanceto() {
		return navailablebalanceto;
	}

	public void setNavailablebalanceto(String navailablebalanceto) {
		this.navailablebalanceto = navailablebalanceto;
	}

	public String getNblockedbalanceto() {
		return nblockedbalanceto;
	}

	public void setNblockedbalanceto(String nblockedbalanceto) {
		this.nblockedbalanceto = nblockedbalanceto;
	}
	
	
}
