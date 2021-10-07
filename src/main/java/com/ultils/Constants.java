package com.ultils;

public class Constants {
	public static final String NOTFOUND = "404";
	public static final String EXISTED = "403";
	public static final String SUCCESS = "200";
	public static final String FAILED = "102";
	public static final String SERVER_ERR = "501";
	public static final String OTP_MESSAGE = "Mã xác minh SPRS của bạn là ";
	public static final String[] NONE_AUTH = {"/authenticate", "/sprs/api/user", "/sprs/api/group", "/sprs/api/group/{id}"};//, "/sprs/api/generateOtp", "/sprs/api/validateOtp"
}
