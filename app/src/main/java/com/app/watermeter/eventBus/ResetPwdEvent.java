package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;

/**
 * Create by Admin on 2018/8/25
 */
public class ResetPwdEvent {
    private Result result;

    public ResetPwdEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
