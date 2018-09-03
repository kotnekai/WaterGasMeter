package com.app.watermeter.common;

public class CommonUrl {
    public static final String BASE_URL = "http://119.23.12.11/api"; // 14 测试 16开发
    public static final String GET_APK_VERSION_INFO = BASE_URL + "apk/che"  + "ck";
    public static final String LOGIN = BASE_URL+"/auth/login";
    public static final String SEND_SMS = BASE_URL+"/sms/send";
    public static final String CHECK_SMS_CODE = BASE_URL+"/sms/check";
    public static final String REGISTER = BASE_URL+"/auth/register";
    public static final String TEST = BASE_URL+"/api/test";
}
