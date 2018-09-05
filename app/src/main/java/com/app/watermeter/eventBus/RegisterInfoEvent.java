package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;
import com.app.watermeter.model.LoginInfoModel;

/**
 * Create by Admin on 2018/9/3
 */
public class RegisterInfoEvent {
    private Result result;

    public RegisterInfoEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
