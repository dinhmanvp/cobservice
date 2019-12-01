package cob.com.gateway.params;

public class Parameter {
	public static final String LOGIN_SUCCESS_CODE = "309";
	// header param request
	public static final String LOGIN_URL_REGEX = "(.*\\/api\\/v1\\/accounts\\/loginAccount\\/[a-z,A-Z,0-9,$&+,:;=?@#|'<>.^*()%!-]*\\/[a-z,A-Z,0-9,$&+,:;=?@#|'<>.^*()%!-]*)?";
	//"(https|http)://(localhost|192.168.100.201):8990\\/api\\/v1\\/accounts\\/loginAccount\\/[a-z,A-Z,0-9]*\\/[a-z,A-Z,0-9]*";
	public static final String PUBLIC_TOKEN = "publicToken";
	public static final String PRIVATE_TOKEN = "privateToken";
	public static final String AUTH_TOKEN = "authToken";
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String USER_ID = "suserId";
	public static final String CHANNEL_ID = "channelId";
	// header param response
	public static final String RESPONSE_STATUS = "ERROR_STATUS";
	public static final String TOKEN_RESPONSE = "PUBLIC_TOKEN";
	// header message response
	public static final String HEADER_INFO_IS_LACKED_MSG_LOGIN = "Header thieu username,password,hoac channelId";
	public static final String HEADER_INFO_IS_LACKED_MSG = "Header thieu username,publicToken,hoac channelId";
	public static final String TOKEN_NOT_VALID = "Token khong hop le hoac het han";
	public static final String IP_NOT_VALID = "IP khong hop le";
	public static final String PARAM_HEADER_WRONG = "Sai thong tin dang nhap o header";
	public static final String USERNAME_NOT_VALID = "username khong ton tai";
	public static final String USER_HAS_NOT_AUTHEN = "User chua dang nhap vao channel nay hoac channel khong hop le";
	public static final String PERMISSION_REQUIRE = "Request yeu cau thong tin xac thuc";
}
