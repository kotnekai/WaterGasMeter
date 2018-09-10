package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;

/**
 * Create by Admin on 2018/9/3
 */
public class UnBindErrEvent {
    private String errStr;

    public UnBindErrEvent(String errStr) {
        this.errStr = errStr;
    }

    public String getResult() {
        return errStr;
    }
}
