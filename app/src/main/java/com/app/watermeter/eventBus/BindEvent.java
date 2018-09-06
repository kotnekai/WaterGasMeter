package com.app.watermeter.eventBus;

import com.app.okhttputils.Model.Result;

/**
 * Create by Admin on 2018/9/3
 */
public class BindEvent {
    private Result result;

    public BindEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
