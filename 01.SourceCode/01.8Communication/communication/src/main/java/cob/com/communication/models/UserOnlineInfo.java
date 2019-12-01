package cob.com.communication.models;
import java.io.Serializable;

public class UserOnlineInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String terminalId;
	private String terminalInfo;
	private String ecashId;
	private String uuid;
	private String connected;
	private String disconnected;
	private String status;
	private String channelId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalInfo() {
		return terminalInfo;
	}

	public void setTerminalInfo(String terminalInfo) {
		this.terminalInfo = terminalInfo;
	}

	public String getEcashId() {
		return ecashId;
	}

	public void setEcashId(String ecashId) {
		this.ecashId = ecashId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getConnected() {
		return connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	public String getDisconnected() {
		return disconnected;
	}

	public void setDisconnected(String disconnected) {
		this.disconnected = disconnected;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
