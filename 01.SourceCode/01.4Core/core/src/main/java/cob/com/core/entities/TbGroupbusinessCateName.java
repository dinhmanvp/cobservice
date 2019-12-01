package cob.com.core.entities;

import java.io.Serializable;

public class TbGroupbusinessCateName implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sGroupBusinessCateId;
	private String sGroupBusinessCateNameVn;
	private String sGroupBusinessNameCateCn;
	private String sGroupBusinessNameCateEn;
	public String getsGroupBusinessCateId() {
		return sGroupBusinessCateId;
	}
	public void setsGroupBusinessCateId(String sGroupBusinessCateId) {
		this.sGroupBusinessCateId = sGroupBusinessCateId;
	}
	public String getsGroupBusinessCateNameVn() {
		return sGroupBusinessCateNameVn;
	}
	public void setsGroupBusinessCateNameVn(String sGroupBusinessCateNameVn) {
		this.sGroupBusinessCateNameVn = sGroupBusinessCateNameVn;
	}
	public String getsGroupBusinessNameCateCn() {
		return sGroupBusinessNameCateCn;
	}
	public void setsGroupBusinessNameCateCn(String sGroupBusinessNameCateCn) {
		this.sGroupBusinessNameCateCn = sGroupBusinessNameCateCn;
	}
	public String getsGroupBusinessNameCateEn() {
		return sGroupBusinessNameCateEn;
	}
	public void setsGroupBusinessNameCateEn(String sGroupBusinessNameCateEn) {
		this.sGroupBusinessNameCateEn = sGroupBusinessNameCateEn;
	}
	public TbGroupbusinessCateName(String sGroupBusinessCateId, String sGroupBusinessCateNameVn,
			String sGroupBusinessNameCateCn, String sGroupBusinessNameCateEn) {
		super();
		this.sGroupBusinessCateId = sGroupBusinessCateId;
		this.sGroupBusinessCateNameVn = sGroupBusinessCateNameVn;
		this.sGroupBusinessNameCateCn = sGroupBusinessNameCateCn;
		this.sGroupBusinessNameCateEn = sGroupBusinessNameCateEn;
	}
}
	
	
