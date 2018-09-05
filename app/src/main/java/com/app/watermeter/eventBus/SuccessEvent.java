package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;

/**
 * Create by Admin on 2018/8/25
 */
public class SuccessEvent {
    private Result result;

    public SuccessEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
