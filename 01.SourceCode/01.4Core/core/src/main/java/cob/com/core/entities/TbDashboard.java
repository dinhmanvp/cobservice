package cob.com.core.entities;

import java.io.Serializable;
import javax.persistence.*;

import cob.com.core.ws.param.Parameter;

/**
 * The persistent class for the tb_dashboard database table.
 * 
 */

@Entity
@Table(name = "tb_dashboard")
@NamedQuery(name = "TbDashboard.findAll", query = "SELECT t FROM TbDashboard t")
public class TbDashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name = "i_item_image")
	private String iItemImage;

	@Column(name = "n_is_available")
	private Integer nIsAvailable;

	@Column(name = "s_dashboard_id")
	private String sDashboardId;

	@Column(name = "s_item_name_cn")
	private String sItemNameCn;

	@Column(name = "s_item_name_en")
	private String sItemNameEn;

	@Column(name = "s_item_name_vn")
	private String sItemNameVn;

	@Column(name = "n_order")
	private Integer nOrder;

	public Integer getnOrder() {
		return nOrder;
	}

	public void setnOrder(Integer nOrder) {
		this.nOrder = nOrder;
	}

	public TbDashboard() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public String getIItemImage() {
		// return this.iItemImage;
		String uri = Parameter.IMAGE_URI + Parameter.TABLE_DASHBOARD + "/" + this.sDashboardId;
		return this.iItemImage == null ? "" : uri;
	}

	public void setIItemImage(String iItemImage) {
		this.iItemImage = iItemImage;
	}

	public Integer getNIsAvailable() {
		return this.nIsAvailable;
	}

	public void setNIsAvailable(Integer nIsAvailable) {
		this.nIsAvailable = nIsAvailable;
	}

	public String getSDashboardId() {
		return this.sDashboardId;
	}

	public void setSDashboardId(String sDashboardId) {
		this.sDashboardId = sDashboardId;
	}

	public String getSItemNameCn() {
		return this.sItemNameCn;
	}

	public void setSItemNameCn(String sItemNameCn) {
		this.sItemNameCn = sItemNameCn;
	}

	public String getSItemNameEn() {
		return this.sItemNameEn;
	}

	public void setSItemNameEn(String sItemNameEn) {
		this.sItemNameEn = sItemNameEn;
	}

	public String getSItemNameVn() {
		return this.sItemNameVn;
	}

	public void setSItemNameVn(String sItemNameVn) {
		this.sItemNameVn = sItemNameVn;
	}
}