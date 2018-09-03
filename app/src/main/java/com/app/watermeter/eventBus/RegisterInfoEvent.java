package com.app.watermeter.eventBus;

import com.app.watermeter.model.LoginInfoModel;

/**
 * Create by Admin on 2018/9/3
 */
public class RegisterInfoEvent {
    private LoginInfoModel infoModel;

    public RegisterInfoEvent(LoginInfoModel infoModel) {
        this.infoModel = infoModel;
    }

    public LoginInfoModel getInfoModel() {
        return infoModel;
    }
}
