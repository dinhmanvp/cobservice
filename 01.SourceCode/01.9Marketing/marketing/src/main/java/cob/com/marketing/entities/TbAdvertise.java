package cob.com.marketing.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_advertise database table.
 * 
 */
@Entity
@Table(name="tb_advertise")
@NamedQuery(name="TbAdvertise.findAll", query="SELECT t FROM TbAdvertise t")
public class TbAdvertise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_display_as")
	private Integer nDisplayAs;

	@Column(name="n_link_type")
	private Integer nLinkType;

	@Column(name="n_order")
	private Integer nOrder;

	@Column(name="s_image")
	private String sImage;

	@Column(name="s_parter_id")
	private String sParterId;

	@Column(name="s_url")
	private String sUrl;

	@Column(name="s_user_id")
	private String sUserId;
	
	@Column(name="d_start_date")
	private String dStartDate;

	@Column(name="d_end_date")
	private String dEndDate;
	
	public TbAdvertise() {
	}

	public String getdStartDate() {
		return dStartDate;
	}

	public void setdStartDate(String dStartDate) {
		this.dStartDate = dStartDate;
	}

	public String getdEndDate() {
		return dEndDate;
	}

	public void setdEndDate(String dEndDate) {
		this.dEndDate = dEndDate;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNDisplayAs() {
		return this.nDisplayAs;
	}

	public void setNDisplayAs(Integer nDisplayAs) {
		this.nDisplayAs = nDisplayAs;
	}

	public Integer getNLinkType() {
		return this.nLinkType;
	}

	public void setNLinkType(Integer nLinkType) {
		this.nLinkType = nLinkType;
	}

	public Integer getNOrder() {
		return this.nOrder;
	}

	public void setNOrder(Integer nOrder) {
		this.nOrder = nOrder;
	}

	public String getSImage() {
		return this.sImage;
	}

	public void setSImage(String sImage) {
		this.sImage = sImage;
	}

	public String getSParterId() {
		return this.sParterId;
	}

	public void setSParterId(String sParterId) {
		this.sParterId = sParterId;
	}

	public String getSUrl() {
		return this.sUrl;
	}

	public void setSUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	public String getSUserId() {
		return this.sUserId;
	}

	public void setSUserId(String sUserId) {
		this.sUserId = sUserId;
	}

}