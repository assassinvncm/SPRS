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
			"/sprs/api/organization-manage/origanization", "/sprs/api/user", "/sprs/api/users_v2/user", "/sprs/api/forgotPassword",
			"/sprs/api/reliefPoint-manage/get",
			"/sprs/api/organization-manage/origanization", "/sprs/api/user", "/sprs/api/users_v2/user",
			"/sprs/api/manage-map/*",
			"/sprs/api/users_v2/organizationlAdmin", "/sprs/api/users_V2/user", "/sprs/api/users_v2/organizationalUser",
			"/sprs/api/group", "/sprs/api/group/{id}", "/sprs/api/generateOtp", "/sprs/api/validateOtp", "/sprs/api/store-manage/uploadImg" };// ,
	public static final String RESET_PASSWORD_MESSAGE = "Mật khẩu mới của bạn là ";																							// "/sprs/api/generateOtp",
																											// "/sprs/api/validateOtp"
	// constants config otp
	public static final String ACCOUNT_SID = "ACaef9117a42d80b9837a3d4bc0acf8fe0";
	public static final String AUTH_TOKEN = "373186f659196db7acbd79a06a6a5bac";
	public static final String FROM_NUMBER = "+14128967877";

	// constants for request status
	public static final String REQUEST_STATUS_ACCEPT = "accept";
	public static final String REQUEST_STATUS_REJECT = "reject";
	public static final String REQUEST_STATUS_UNCHECK = "uncheck";
	// constants for request status
	public static final String IMAGE_URL = "https://image-bucket-sprs.s3.ap-southeast-1.amazonaws.com/";
	
	//constants notification type
	public static final String NOTIFICATION_TYPE_STORE = "st";
	public static final String NOTIFICATION_TYPE_RELIEFPOINT = "rp";
	public static final String NOTIFICATION_TYPE_ADMIN = "ad";
}
