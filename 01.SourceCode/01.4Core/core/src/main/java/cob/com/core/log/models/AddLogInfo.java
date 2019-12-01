package cob.com.core.log.models;

public class AddLogInfo {
	
	private String _sessionId;	
	private String _dateTime;
	private String _hostIP;
	private String _className;
	private String _methodName;
	private String _methodType;
	private String _arguments;
	private Object _argumentsReturn;
	/*thời điểm method start */
	private long _timeStart;
	/*thời điểm method return */
	private long _timeEnd;
	/*Tổng thời gian run Method */
	private long _timeTotal;	
	
	public long get_timeTotal() {
		return _timeTotal;
	}
	public void set_timeTotal(long _timeTotal) {
		this._timeTotal = _timeTotal;
	}
	public String get_className() {
		return _className;
	}
	public void set_className(String _className) {
		this._className = _className;
	}
	public String get_methodName() {
		return _methodName;
	}
	public void set_methodName(String _methodName) {
		this._methodName = _methodName;
	}
	public String get_methodType() {
		return _methodType;
	}
	public void set_methodType(String _methodType) {
		this._methodType = _methodType;
	}
	public String get_arguments() {
		return _arguments;
	}
	public void set_arguments(String _arguments) {
		this._arguments = _arguments;
	}
	public long get_timeStart() {
		return _timeStart;
	}
	public void set_timeStart(long _timeStart) {
		this._timeStart = _timeStart;
	}
	public long get_timeEnd() {
		return _timeEnd;
	}
	public void set_timeEnd(long _timeEnd) {
		this._timeEnd = _timeEnd;
	}
	public String get_sessionId() {
		return _sessionId;
	}
	public void set_sessionId(String _sessionId) {
		this._sessionId = _sessionId;
	}
	public String get_hostIP() {
		return _hostIP;
	}
	public void set_hostIP(String _hostIP) {
		this._hostIP = _hostIP;
	}
	public Object get_argumentsReturn() {
		return _argumentsReturn;
	}
	public void set_argumentsReturn(Object _argumentsReturn) {
		this._argumentsReturn = _argumentsReturn;
	}
	public String get_dateTime() {
		return _dateTime;
	}
	public void set_dateTime(String _dateTime) {
		this._dateTime = _dateTime;
	}
}
