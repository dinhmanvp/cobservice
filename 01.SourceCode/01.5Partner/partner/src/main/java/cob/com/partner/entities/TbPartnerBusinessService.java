package cob.com.partner.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_partner_business_services database table.
 * 
 */
@Entity
@Table(name="tb_partner_business_services")
@NamedQuery(name="TbPartnerBusinessService.findAll", query="SELECT t FROM TbPartnerBusinessService t")
public class TbPartnerBusinessService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	//@SequenceGenerator(name = "TbPartnerBusinessService_sequence", sequenceName = "tb_partner_business_services_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)//, generator = "TbPartnerBusinessService_sequence")
	private Integer nId;

	@Column(name="n_is_activated")
	private Integer nIsActivated;

	
	@Column(name="s_business_service_id")
	private String sBusinessServiceId;

	@Column(name="s_partner_business_service_id")
	private String sPartnerBusinessServiceId;

	@Column(name="s_partner_id")
	private String sPartnerId;
	
	@Column(name="s_business_service_name_en",insertable = false, updatable = false)
	private String sBusinessServiceNameEn;

	@Column(name="s_business_service_name_vn",insertable = false, updatable = false)
	private String sBusinessServiceNameVn;
	
	@Column(name="s_business_service_name_cn",insertable = false, updatable = false)
	private String sBusinessServiceNameCn;
	
	public TbPartnerBusinessService() {
	}

	public String getsBusinessServiceNameCn() {
		return sBusinessServiceNameCn == null ? "" : sBusinessServiceNameCn;
	}

	public void setsBusinessServiceNameCn(String sBusinessServiceNameCn) {
		this.sBusinessServiceNameCn = sBusinessServiceNameCn;
	}

	public String getsBusinessServiceNameVn() {
		return sBusinessServiceNameVn == null ? "" : sBusinessServiceNameVn;
	}

	public void setsBusinessServiceNameVn(String sBusinessServiceNameVn) {
		this.sBusinessServiceNameVn = sBusinessServiceNameVn;
	}

	public String getsBusinessServiceNameEn() {
		return sBusinessServiceNameEn == null ? "" : sBusinessServiceNameEn;
	}

	public void setsBusinessServiceNameEn(String sBusinessServiceNameEn) {
		this.sBusinessServiceNameEn = sBusinessServiceNameEn;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsActivated() {
		return this.nIsActivated;
	}

	public void setNIsActivated(Integer nIsActivated) {
		this.nIsActivated = nIsActivated;
	}

	public String getSBusinessServiceId() {
		return this.sBusinessServiceId;
	}

	public void setSBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSPartnerBusinessServiceId() {
		return this.sPartnerBusinessServiceId;
	}

	public void setSPartnerBusinessServiceId(String sPartnerBusinessServiceId) {
		this.sPartnerBusinessServiceId = sPartnerBusinessServiceId;
	}

	public String getSPartnerId() {
		return this.sPartnerId;
	}

	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}

}