package cob.com.marketing.log.models;
/**
 * @author ldman 2019/07/06
 * model write log into mongoDB
 */
public class AddLogDetailInfo {
	private String _logDetailId;
	private String _detailname;
	private String _detail;
	public String getLogDetailId() {
		return _logDetailId;
	}
	public void setLogDetailId(String logDetailId) {
		this._logDetailId = logDetailId;
	}
	public String get_detailname() {
		return _detailname;
	}
	public void set_detailname(String _detailname) {
		this._detailname = _detailname;
	}
	public String get_detail() {
		return _detail;
	}
	public void set_detail(String _detail) {
		this._detail = _detail;
	}
}
