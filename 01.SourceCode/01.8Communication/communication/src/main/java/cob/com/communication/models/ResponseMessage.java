package cob.com.communication.models;

/**
 * @author ldman 2019/07/06
 * Response templet
 */
public class ResponseMessage {
	private String responseCode;
	private String responseMessage;
	private Object responseData;
	
	public String getResponseCode() {
		return responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public Object getResponseData() {
		return responseData;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
