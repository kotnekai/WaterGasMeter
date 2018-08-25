package com.app.watermeter.eventBus;

import com.app.watermeter.model.ComResponseModel;

/**
 * Create by Admin on 2018/8/25
 */
public class SuccessEvent {
    private ComResponseModel successModel;

    public SuccessEvent(ComResponseModel successModel) {
        this.successModel = successModel;
    }

    public ComResponseModel getSuccessModel() {
        return successModel;
    }
}
