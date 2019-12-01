package cob.com.core.ws.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import cob.com.core.entities.TbDashboard;
import cob.com.core.ws.param.Parameter;

@NamedStoredProcedureQuery(
		name = "loadDashboard",
		procedureName = "mdl_core.test",
		resultClasses = TbDashboard.class,
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Void.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)				
			}
		)
@Entity
public class DashboardModel {
		@Id
		@Column(name = "n_id")
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
		private String nOrder;

		public Integer getnId() {
			return nId;
		}

		public void setnId(Integer nId) {
			this.nId = nId;
		}

		public String getiItemImage() {
			//return iItemImage;
			String uri = Parameter.IMAGE_URI + Parameter.TABLE_DASHBOARD + "/" + this.sDashboardId;
			return this.iItemImage == null ? "" : uri;
		}

		public void setiItemImage(String iItemImage) {
			this.iItemImage = iItemImage;
		}

		public Integer getnIsAvailable() {
			return nIsAvailable;
		}

		public void setnIsAvailable(Integer nIsAvailable) {
			this.nIsAvailable = nIsAvailable;
		}

		public String getsDashboardId() {
			return sDashboardId;
		}

		public void setsDashboardId(String sDashboardId) {
			this.sDashboardId = sDashboardId;
		}

		public String getsItemNameCn() {
			return sItemNameCn;
		}

		public void setsItemNameCn(String sItemNameCn) {
			this.sItemNameCn = sItemNameCn;
		}

		public String getsItemNameEn() {
			return sItemNameEn;
		}

		public void setsItemNameEn(String sItemNameEn) {
			this.sItemNameEn = sItemNameEn;
		}

		public String getsItemNameVn() {
			return sItemNameVn;
		}

		public void setsItemNameVn(String sItemNameVn) {
			this.sItemNameVn = sItemNameVn;
		}

		public String getnOrder() {
			return nOrder;
		}

		public void setnOrder(String nOrder) {
			this.nOrder = nOrder;
		}
		
		
}
