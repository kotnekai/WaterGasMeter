package com.app.watermeter.eventBus;

import com.app.watermeter.model.UserInfoModel;

/**
 * Create by Admin on 2018/9/3
 */
public class PersonInfoEvent {
    private UserInfoModel userInfoModel;

    public PersonInfoEvent(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }
}
