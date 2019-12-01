package cob.com.cobgateway.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_rank_ip_countries database table.
 * 
 */
@Entity
@Table(name="tb_rank_ip_countries", schema="mdl_gateway")
@NamedQuery(name="TbRankIpCountry.findAll", query="SELECT t FROM TbRankIpCountry t")
public class TbRankIpCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="b_is_allowed")
	private Integer bIsAllowed;

	@Column(name="ip_index")
	private Long ipIndex;

	@Column(name="s_country_id")
	private String sCountryId;

	@Column(name="s_country_ip_blocks")
	private String sCountryIpBlocks;

	public TbRankIpCountry() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getBIsAllowed() {
		return this.bIsAllowed;
	}

	public void setBIsAllowed(Integer bIsAllowed) {
		this.bIsAllowed = bIsAllowed;
	}

	public Long getIpIndex() {
		return this.ipIndex;
	}

	public void setIpIndex(Long ipIndex) {
		this.ipIndex = ipIndex;
	}

	public String getSCountryId() {
		return this.sCountryId;
	}

	public void setSCountryId(String sCountryId) {
		this.sCountryId = sCountryId;
	}

	public String getSCountryIpBlocks() {
		return this.sCountryIpBlocks;
	}

	public void setSCountryIpBlocks(String sCountryIpBlocks) {
		this.sCountryIpBlocks = sCountryIpBlocks;
	}

}