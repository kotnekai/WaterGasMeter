package com.app.watermeter.model;

/**
 * Create by Admin on 2018/9/6
 *
 * @author https://www.showdoc.cc/web/#/137924192608060?page_id=789816901624533
 */
public class MeterReadModel {
    private int id;
    private int machine_id;
    private int machine_type_id;
    private String machine_sn;
    private float degree;
    private float read_fee;
    private float read_degree;
    private float before_read_balance;
    private float after_read_balance;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public int getMachine_type_id() {
        return machine_type_id;
    }

    public void setMachine_type_id(int machine_type_id) {
        this.machine_type_id = machine_type_id;
    }

    public String getMachine_sn() {
        return machine_sn;
    }

    public void setMachine_sn(String machine_sn) {
        this.machine_sn = machine_sn;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public float getRead_fee() {
        return read_fee;
    }

    public void setRead_fee(float read_fee) {
        this.read_fee = read_fee;
    }

    public float getRead_degree() {
        return read_degree;
    }

    public void setRead_degree(float read_degree) {
        this.read_degree = read_degree;
    }

    public float getBefore_read_balance() {
        return before_read_balance;
    }

    public void setBefore_read_balance(float before_read_balance) {
        this.before_read_balance = before_read_balance;
    }

    public float getAfter_read_balance() {
        return after_read_balance;
    }

    public void setAfter_read_balance(float after_read_balance) {
        this.after_read_balance = after_read_balance;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
