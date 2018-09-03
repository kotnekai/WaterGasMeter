package com.app.watermeter.eventBus;

import com.app.watermeter.model.ComResponseModel;

/**
 * Create by Admin on 2018/9/3
 */
public class CheckSmsCodeEvent {
    private ComResponseModel successModel;

    public CheckSmsCodeEvent(ComResponseModel successModel) {
        this.successModel = successModel;
    }

    public ComResponseModel getSuccessModel() {
        return successModel;
    }
}
