package cob.com.cobservice.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_startup_package_items database table.
 * 
 */
@Entity
@Table(name="tb_startup_package_items")
@NamedQuery(name="TbStartupPackageItem.findAll", query="SELECT t FROM TbStartupPackageItem t")
public class TbStartupPackageItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="d_from_date")
	private Date dFromDate;

	@Column(name="d_to_date")
	private Date dToDate;

	@Column(name="n_duration")
	private Integer nDuration;

	@Column(name="s_advise_service_id")
	private String sAdviseServiceId;

	@Column(name="s_duration_type")
	private String sDurationType;

	@Column(name="s_service_unit_id")
	private String sServiceUnitId;

	@Column(name="s_startup_package_id")
	private String sStartupPackageId;

	@Column(name="s_startup_package_items_id")
	private String sStartupPackageItemsId;

	public TbStartupPackageItem() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Date getDFromDate() {
		return this.dFromDate;
	}

	public void setDFromDate(String dFromDate) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.dFromDate = date.parse(dFromDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getDToDate() {
		return this.dToDate;
	}

	public void setDToDate(String dToDate) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {			
			this.dToDate = date.parse(dToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	public Integer getNDuration() {
		return this.nDuration;
	}

	public void setNDuration(Integer nDuration) {
		this.nDuration = nDuration;
	}

	public String getSAdviseServiceId() {
		return this.sAdviseServiceId;
	}

	public void setSAdviseServiceId(String sAdviseServiceId) {
		this.sAdviseServiceId = sAdviseServiceId;
	}

	public String getSDurationType() {
		return this.sDurationType;
	}

	public void setSDurationType(String sDurationType) {
		this.sDurationType = sDurationType;
	}

	public String getSServiceUnitId() {
		return this.sServiceUnitId;
	}

	public void setSServiceUnitId(String sServiceUnitId) {
		this.sServiceUnitId = sServiceUnitId;
	}

	public String getSStartupPackageId() {
		return this.sStartupPackageId;
	}

	public void setSStartupPackageId(String sStartupPackageId) {
		this.sStartupPackageId = sStartupPackageId;
	}

	public String getSStartupPackageItemsId() {
		return this.sStartupPackageItemsId;
	}

	public void setSStartupPackageItemsId(String sStartupPackageItemsId) {
		this.sStartupPackageItemsId = sStartupPackageItemsId;
	}

}