package com.app.watermeter.model;

/**
 * Create by Admin on 2018/10/25
 */
public class NetVersionModel {
    private int status_code;
    private String message;
    private int erro_code;
    private VersionData data;

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

    public int getErro_code() {
        return erro_code;
    }

    public void setErro_code(int erro_code) {
        this.erro_code = erro_code;
    }

    public VersionData getData() {
        return data;
    }

    public void setData(VersionData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetVersionModel{" +
                "status_code=" + status_code +
                ", message='" + message + '\'' +
                ", erro_code=" + erro_code +
                ", data=" + data +
                '}';
    }
}
