package com.app.watermeter.model;


/**
 * Create by Admin on 2018/9/3
 */
public class LoginInfoModel {
    private int status_code;
    private String message;
    private int err_code;
    private UserInfoModel data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public UserInfoModel getData() {
        return data;
    }

    public void setData(UserInfoModel data) {
        this.data = data;
    }
}
