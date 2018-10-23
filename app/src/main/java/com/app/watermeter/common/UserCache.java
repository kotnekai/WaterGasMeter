package com.app.watermeter.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.app.watermeter.model.VersionModel;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.PreferencesUtils;

/**
 * Create by Admin on 2018/10/23
 */
public class UserCache {
    private String mToken;
    private String mPhoneNumber;

    private static UserCache instance = null;

    public static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }
        return instance;
    }

    public void setToken(String token) {
        mToken = token;
        PreferencesUtils.putString(CommonParams.USER_TOKEN, token);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (EmptyUtil.isEmpty(phoneNumber)) {
            return;
        }
        mPhoneNumber = phoneNumber;
        PreferencesUtils.putString(CommonParams.PHONE_NUMBER, phoneNumber);

    }

    /**
     * 获取token
     *
     * @return
     */
    public String getToken() {
        if (mToken == null) {
            mToken = PreferencesUtils.getString(CommonParams.USER_TOKEN);
        }
        return mToken;
    }

    /**
     * 获取手机号
     *
     * @return
     */
    public String getPhoneNumber() {
        if (mPhoneNumber == null) {
            mPhoneNumber = PreferencesUtils.getString(CommonParams.PHONE_NUMBER);
        }
        return mPhoneNumber;
    }

}
