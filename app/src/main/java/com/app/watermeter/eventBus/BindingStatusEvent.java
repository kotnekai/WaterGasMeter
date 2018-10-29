package com.app.watermeter.eventBus;

/**
 * Create by Admin on 2018/9/3
 */
public class BindingStatusEvent {

    public static final int BINDING_SUCCESS=108;
    public static final int UNBINDING_SUCCESS=109;


    private int status;

    public BindingStatusEvent(int status) {
        this.status = status;
    }

    public int getResult() {
        return status;
    }
}
