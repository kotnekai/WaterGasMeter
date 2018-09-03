package com.app.watermeter.eventBus;

import com.app.watermeter.model.LoginInfoModel;

/**
 * Create by Admin on 2018/9/3
 */
public class LoginEvent {
    private LoginInfoModel loginInfoModel;

    public LoginEvent(LoginInfoModel loginInfoModel) {
        this.loginInfoModel = loginInfoModel;
    }

    public LoginInfoModel getLoginInfoModel() {
        return loginInfoModel;
    }
}
