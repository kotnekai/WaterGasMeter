package com.app.watermeter.model;

/**
 * Create by Admin on 2018/12/25
 *
 * @author https://www.showdoc.cc/137924192608060?page_id=1348398341523813
 */
public class MeterTransactionModel {
    private int machine_id;
    private String machine_sn;
    private int machine_type_id;
    private String type;
    private float rvalue;
    private float fee;
    private float before;
    private float after;
    private String created_at;

    public int getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public String getMachine_sn() {
        return machine_sn;
    }

    public void setMachine_sn(String machine_sn) {
        this.machine_sn = machine_sn;
    }

    public int getMachine_type_id() {
        return machine_type_id;
    }

    public void setMachine_type_id(int machine_type_id) {
        this.machine_type_id = machine_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRvalue() {
        return rvalue;
    }

    public void setRvalue(float rvalue) {
        this.rvalue = rvalue;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public float getBefore() {
        return before;
    }

    public void setBefore(float before) {
        this.before = before;
    }

    public float getAfter() {
        return after;
    }

    public void setAfter(float after) {
        this.after = after;
    }
}
