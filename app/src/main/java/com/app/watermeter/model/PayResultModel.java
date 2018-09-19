package com.app.watermeter.model;

/**
 * Created by admin on 2018/9/18.
 */

public class PayResultModel {
    private int status;
    private String time_complete;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime_complete() {
        return time_complete;
    }

    public void setTime_complete(String time_complete) {
        this.time_complete = time_complete;
    }
}
