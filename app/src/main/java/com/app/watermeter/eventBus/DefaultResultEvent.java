package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;

/**
 * Create by Admin on 2018/9/3
 */
public class DefaultResultEvent {
    private Result result;

    public DefaultResultEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
