package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_currencies database table.
 * 
 */
@Entity
@Table(name="tb_currencies", schema ="mdl_core")
@NamedQuery(name="TbCurrency.findAll", query="SELECT t FROM TbCurrency t")
public class TbCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="s_currency_id")
	private String sCurrencyId;

	@Column(name="s_currency_image")
	private String sCurrencyImage;

	@Column(name="s_currency_symbol")
	private String sCurrencySymbol;

	public TbCurrency() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSCurrencyId() {
		return this.sCurrencyId;
	}

	public void setSCurrencyId(String sCurrencyId) {
		this.sCurrencyId = sCurrencyId;
	}

	public String getSCurrencyImage() {
		return this.sCurrencyImage;
	}

	public void setSCurrencyImage(String sCurrencyImage) {
		this.sCurrencyImage = sCurrencyImage;
	}

	public String getSCurrencySymbol() {
		return this.sCurrencySymbol;
	}

	public void setSCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}

}