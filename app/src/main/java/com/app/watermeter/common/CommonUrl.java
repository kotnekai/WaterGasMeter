package com.app.watermeter.common;

public class CommonUrl {
    public static final String BASE_URL = "http://202.79.30.60/api"; // 14 测试 16开发

    public static final String BASE_PAY_URL = "https://uat-api.asiaweiluy.com/web/"; // 14 测试 16开发
    public static final String GET_APK_VERSION_INFO = BASE_URL + "/app/version/latest?type=android";
    public static final String LOGIN = BASE_URL + "/auth/login";
    public static final String SEND_SMS = BASE_URL + "/sms/send";
    public static final String CHECK_SMS_CODE = BASE_URL + "/sms/check";
    public static final String REGISTER = BASE_URL + "/auth/register";
    public static final String GET_PERSON_INFO = BASE_URL + "/me";
    public static final String RESET_PASSWORD = BASE_URL + "/passwd/reset";
    public static final String TEST = BASE_URL + "/api/test";
    public static final String METER_TYPE_URL = BASE_URL + "/machine/types";
    public static final String LOGIN_OUT = BASE_URL + "/auth/logout";
    public static final String METER_LIST_URL = BASE_URL + "/machines";
    public static final String METER_DETAIL_URL = BASE_URL + "/machine";
    public static final String METER_BIND_URL = BASE_URL + "/machine/bind";
    public static final String METER_UNBIND_URL = BASE_URL + "/machine/disbind";
    public static final String METER_READ_LIST_URL = BASE_URL + "/record/read";
    public static final String METER_RECHARGE_LIST_URL = BASE_URL + "/record/recharge";
    public static final String PREPAY_URL = BASE_URL + "/recharge/prepay";
    public static final String ORDER_INFO_URL = BASE_URL + "/order/info";
    public static final String METER_RECHARGE_LIST_FROM_SCAN_URL = BASE_URL + "/record/recharge_from_scan";
    public static final String DEVICE_UPLOAD_URL = BASE_URL + "/device/update";

   public static final String PAY_URL = "https://uat-api.asiaweiluy.com/gateway.php?method=ace.trade.pay";
}
