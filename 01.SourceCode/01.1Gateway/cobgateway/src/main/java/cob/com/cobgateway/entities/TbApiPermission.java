package cob.com.cobgateway.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_api_permission database table.
 * 
 */
@Entity
@Table(name="tb_api_permission", schema="mdl_gateway")
@NamedQuery(name="TbApiPermission.findAll", query="SELECT t FROM TbApiPermission t")
public class TbApiPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="n_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer nId;

	@Column(name="n_permission_require")
	private Integer nPermissionRequire;

	@Column(name="s_api")
	private String sApi;

	public TbApiPermission() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNPermissionRequire() {
		return this.nPermissionRequire;
	}

	public void setNPermissionRequire(Integer nPermissionRequire) {
		this.nPermissionRequire = nPermissionRequire;
	}

	public String getSApi() {
		return this.sApi;
	}

	public void setSApi(String sApi) {
		this.sApi = sApi;
	}

}