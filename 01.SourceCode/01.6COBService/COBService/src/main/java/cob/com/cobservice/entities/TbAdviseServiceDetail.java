package cob.com.cobservice.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_advise_service_detail database table.
 * 
 */
@Entity
@Table(name="tb_advise_service_detail")
@NamedQuery(name="TbAdviseServiceDetail.findAll", query="SELECT t FROM TbAdviseServiceDetail t")
public class TbAdviseServiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="advise_service_detail_id")
	private String adviseServiceDetailId;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name="s_content")
	private String sContent;

	public TbAdviseServiceDetail() {
	}

	public String getAdviseServiceDetailId() {
		return this.adviseServiceDetailId;
	}

	public void setAdviseServiceDetailId(String adviseServiceDetailId) {
		this.adviseServiceDetailId = adviseServiceDetailId;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getSAdviseServiceId() {
		return this.sAdviseServiceId;
	}

	public void setSAdviseServiceId(String sAdviseServiceId) {
		this.sAdviseServiceId = sAdviseServiceId;
	}

	public String getSContent() {
		return this.sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

}