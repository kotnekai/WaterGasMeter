package com.app.okhttputils.Model;

import com.google.gson.Gson;

/**
 * Created by admin on 2018/9/4.
 */

public class Result<T> {

    public static final int STATE_OK = 200;
    public static final int TOKEN_FAILD = 401;

    private int status_code;
    private int return_code;

    private String message;
    private String error_message;

    private int err_code;
    private  T data;

    private long timestamp;


    public Result() {
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.status_code == STATE_OK;
    }

    public String toString() {
        return (new Gson()).toJson(this);
    }


    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public static <T> Result<T> httpError(String msg) {
        Result<T> result = new Result();
        result.setMessage(msg);
        return result;
    }
    public static <T> Result<T> empty() {
        return new Result();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
