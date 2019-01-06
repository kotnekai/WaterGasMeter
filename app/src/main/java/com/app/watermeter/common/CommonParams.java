package com.app.watermeter.common;

public class CommonParams {
    public static int FINISH_CODE = 300;
    public static boolean IS_WRITE_LOG = true;//是否写日志
    public static final String USER_TOKEN = "token";
    public static final String TOKEN_PERIOD = "expires_in";
    public static final String USER_ACCOUNT = "user_account";

    public static final String isExit = "isExit";

    //apk的路径
    public static String APK_PATH = "/apk";

    public static final String BUSS_REGISTER_TYPE = "register";

    public static final String BUSS_RESET_TYPE = "resetpasswd";



    public static final int PAY_RESULT = 100; //支付成功finish


    public static final int PAY_RESULT_PENDING = 1;
    public static final int PAY_RESULT_SUCCESS = 3;
    public static final int PAY_RESULT_CANCEL = 4;

    public static final int TYPE_WATER = 1;
    public static final int TYPE_ELECT = 2;
    public static final int TYPE_GAS = 3;

    public static final int PAGE_TYPE_RECHARGE = 1;//预存明细
    public static final int PAGE_TYPE_READ = 2;//缴费明细
    public static final int PAGE_TYPE_TRANSACTION = 0;//交易明细

    public static final String METTER_TYPE_JSON = "meterTypeJson";

    public static final String PAGE_TYPE = "pageType";

    public static final String METER_TYPE = "meterType";
    public static final String METER_SN = "meterSN";
    public static final String METER_ID = "meterId";
    public static final String METER_TIME = "meterTime";
    public static final String METER_INFO = "meterInfo";

    public static final String FROM_SCAN = "fromScan";


    public static int mMeterWater;
    public static int mMeterElect;
    public static int mMeterGas;

    public static final int fromTypeRegister = 1;//注册
    public static final String fromType = "fromType";//
    public static final int fromTypeReset = 2;//重置

    public static final String USD = "USD";
    public static final String KDR = "KDR";



    public static final String SECURITY_KEY = "ACE_API_KEY_SHRWE_AOOP";
    public static final String PARTNER  = "1819";

    public static final String PHONE_NUMBER  = "phone_number";

    //https://www.showdoc.cc/137924192608060?page_id=895267016994174
    public static final String ACTION_FROM_SCAN = "scan";
    public static final String ACTION_FROM_DIRECT = "direct";


}
