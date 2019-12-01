package cob.com.partner.ws.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * The persistent class for the tb_partner_business_services database table.
 * 
 */
@Entity
public class PartnerBusinessServiceInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="s_business_service_id")
	private String sBusinessServiceId;

	@Column(name="s_business_service_name")
	private String sbusinessservicename;

	@Column(name="i_business_service_icon")
	private String ibusinessserviceicon;
	
	@Column(name="s_business_service_desc_shrink")
	private String sbusinessservicedescshrink;
	
	@Column(name="s_business_service_desc")
	private String sbusinessservicedesc;
	
	@Column(name="n_business_service_rating")
	private BigDecimal nbusinessservicerating;
	
	@Column(name="n_count_order")
	private Integer ncountorder;
	
	@Transient
	private List<PartnerBizcateInfo> partnerBizcateInfos;
	

	public List<PartnerBizcateInfo> getPartnerBizcateInfos() {
		return partnerBizcateInfos;
	}

	public void setPartnerBizcateInfos(List<PartnerBizcateInfo> partnerBizcateInfos) {
		this.partnerBizcateInfos = partnerBizcateInfos;
	}

	public PartnerBusinessServiceInfo() {
	}

	public String getsBusinessServiceId() {
		return sBusinessServiceId;
	}

	public void setsBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSbusinessservicename() {
		return sbusinessservicename;
	}

	public void setSbusinessservicename(String sbusinessservicename) {
		this.sbusinessservicename = sbusinessservicename;
	}

	public String getIbusinessserviceicon() {
		return ibusinessserviceicon;
	}

	public void setIbusinessserviceicon(String ibusinessserviceicon) {
		this.ibusinessserviceicon = ibusinessserviceicon;
	}

	public String getSbusinessservicedescshrink() {
		return sbusinessservicedescshrink;
	}

	public void setSbusinessservicedescshrink(String sbusinessservicedescshrink) {
		this.sbusinessservicedescshrink = sbusinessservicedescshrink;
	}

	public String getSbusinessservicedesc() {
		return sbusinessservicedesc;
	}

	public void setSbusinessservicedesc(String sbusinessservicedesc) {
		this.sbusinessservicedesc = sbusinessservicedesc;
	}

	public BigDecimal getNbusinessservicerating() {
		return nbusinessservicerating;
	}

	public void setNbusinessservicerating(BigDecimal nbusinessservicerating) {
		this.nbusinessservicerating = nbusinessservicerating;
	}

	public Integer getNcountorder() {
		return ncountorder;
	}

	public void setNcountorder(Integer ncountorder) {
		this.ncountorder = ncountorder;
	}
	
}