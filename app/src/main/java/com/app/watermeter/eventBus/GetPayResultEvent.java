package com.app.watermeter.eventBus;

import com.app.watermeter.model.PayResultModel;


public class GetPayResultEvent {

    private PayResultModel payResultModel;

    public GetPayResultEvent(PayResultModel model) {
        this.payResultModel = model;
    }

    public PayResultModel getModelInfo() {
        return payResultModel;
    }
}
