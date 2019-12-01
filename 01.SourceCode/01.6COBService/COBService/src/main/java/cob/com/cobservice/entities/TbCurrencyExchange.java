package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_currency_exchange database table.
 * 
 */
@Entity
@Table(name="tb_currency_exchange")
@NamedQuery(name="TbCurrencyExchange.findAll", query="SELECT t FROM TbCurrencyExchange t")
public class TbCurrencyExchange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="d_updated_date")
	private Timestamp dUpdatedDate;

	@Column(name="n_exchange_rate")
	private Integer nExchangeRate;

	@Column(name="n_is_available")
	private Integer nIsAvailable;

	@Column(name="s_from_currency_id")
	private String sFromCurrencyId;

	@Column(name="s_to_currency_id")
	private String sToCurrencyId;

	public TbCurrencyExchange() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Timestamp getDUpdatedDate() {
		return this.dUpdatedDate;
	}

	public void setDUpdatedDate(Timestamp dUpdatedDate) {
		this.dUpdatedDate = dUpdatedDate;
	}

	public Integer getNExchangeRate() {
		return this.nExchangeRate;
	}

	public void setNExchangeRate(Integer nExchangeRate) {
		this.nExchangeRate = nExchangeRate;
	}

	public Integer getNIsAvailable() {
		return this.nIsAvailable;
	}

	public void setNIsAvailable(Integer nIsAvailable) {
		this.nIsAvailable = nIsAvailable;
	}

	public String getSFromCurrencyId() {
		return this.sFromCurrencyId;
	}

	public void setSFromCurrencyId(String sFromCurrencyId) {
		this.sFromCurrencyId = sFromCurrencyId;
	}

	public String getSToCurrencyId() {
		return this.sToCurrencyId;
	}

	public void setSToCurrencyId(String sToCurrencyId) {
		this.sToCurrencyId = sToCurrencyId;
	}

}