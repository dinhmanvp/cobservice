package cob.com.accounts.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tb_my_business database table.
 * 
 */
@Entity
@Table(name="tb_my_business")
@NamedQuery(name="TbMyBusiness.findAll", query="SELECT t FROM TbMyBusiness t")
public class TbMyBusiness implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	//@SequenceGenerator(name = "TbMyBusiness_sequence", sequenceName = "TbMyBusiness_n_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;
	
	@Column(name="s_my_business_id")
	private String sMyBusinessId;
	
	@Column(name="s_user_id")
	private String sUserId;
	
	
	@Column(name="s_partner_id")
	private String sPartnerId;
	
	@Column(name="s_partner_bizcate_id")
	private String sPartnerBizcateId;
	
	@Column(name="s_partner_business_service_id")
	private String sPartnerBusinessServiceId;


	public TbMyBusiness() {
	}

	public String getsUserId() {
		return sUserId;
	}

	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}

	public String getSMyBusinessId() {
		return this.sMyBusinessId;
	}
	
	public void setSMyBusinessId(String sMyBusinessId) {
		this.sMyBusinessId = sMyBusinessId;
	}
	
	public String getSPartnerId() {
		return this.sPartnerId;
	}
	public void setSPartnerId(String sPartnerId) {
		this.sPartnerId = sPartnerId;
	}
	
	public String getSPartnerBizcateId() {
		return this.sPartnerBizcateId;
	}
	
	public void setSPartnerBizcateId(String sPartnerBizcateId) {
		this.sPartnerBizcateId = sPartnerBizcateId;
	}
	
	public String getSPartnerBusinessServiceId() {
		return this.sPartnerBusinessServiceId;
	}
	
	public void setSPartnerBusinessServiceId(String sPartnerBusinessServiceId) {
		this.sPartnerBusinessServiceId = sPartnerBusinessServiceId;
	}
}