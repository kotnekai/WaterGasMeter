package com.app.watermeter.model;

/**
 * Create by Admin on 2018/9/6
 *
 * @author
 * https://www.showdoc.cc/web/#/137924192608060?page_id=789819584135405
 */
public class MeterReChargeModel {
    private int id;
    private int user_id;
    private String user_real_name;
    private int machine_id;
    private int machine_type_id;
    private String machine_sn;
    private float recharge_fee;
    private float before_recharge_balance;
    private float after_recharge_balance;
    private String created_at;
    private String updated_at;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
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

    public float getRecharge_fee() {
        return recharge_fee;
    }

    public void setRecharge_fee(float recharge_fee) {
        this.recharge_fee = recharge_fee;
    }

    public float getBefore_recharge_balance() {
        return before_recharge_balance;
    }

    public void setBefore_recharge_balance(float before_recharge_balance) {
        this.before_recharge_balance = before_recharge_balance;
    }

    public float getAfter_recharge_balance() {
        return after_recharge_balance;
    }

    public void setAfter_recharge_balance(float after_recharge_balance) {
        this.after_recharge_balance = after_recharge_balance;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
