package com.ultils;

public class Constants {
	public static final String NOTFOUND = "404";
	public static final String EXISTED = "403";
	public static final String SUCCESS = "200";
	public static final String FAILED = "102";
	public static final String SERVER_ERR = "501";
	public static final String OTP_MESSAGE = "Mã xác minh SPRS của bạn là ";
	public static final String[] NONE_AUTH = { "/utilities/**", "/swagger-ui.html", "/swagger-ui/**",
			"/sprs/api/address/**", "/v3/**", "/sprs/api/item", "/authenticate",
			"/sprs/api/reliefPoint-manage/get",
			"/sprs/api/organization-manage/origanization", "/sprs/api/user", "/sprs/api/users_v2/user",
			"/sprs/api/users_v2/organizationlAdmin", "/sprs/api/users_V2/user", "/sprs/api/users_v2/organizationalUser",
			"/sprs/api/group", "/sprs/api/group/{id}", "/sprs/api/generateOtp", "/sprs/api/validateOtp" };// ,
																											// "/sprs/api/generateOtp",
																											// "/sprs/api/validateOtp"
	// constants config otp
	public static final String ACCOUNT_SID = "ACaef9117a42d80b9837a3d4bc0acf8fe0";
	public static final String AUTH_TOKEN = "a1744f889a0ed0c8eac7ef0074fa06c0";
	public static final String FROM_NUMBER = "+14128967877";

	// constants for request status
	public static final String REQUEST_STATUS_ACCEPT = "accept";
	public static final String REQUEST_STATUS_REJECT = "reject";
	public static final String REQUEST_STATUS_UNCHECK = "uncheck";
	// constants for request status
}
