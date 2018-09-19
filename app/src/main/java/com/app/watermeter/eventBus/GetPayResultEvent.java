package com.app.watermeter.eventBus;

import com.app.watermeter.model.PayResultModel;


public class GetPayResultEvent {

    private PayResultModel payResultModel;

    private String  htmlStr;

    public GetPayResultEvent(String str) {
        this.htmlStr = str;
    }

    public String getHtmlStr() {
        return htmlStr;
    }
}
