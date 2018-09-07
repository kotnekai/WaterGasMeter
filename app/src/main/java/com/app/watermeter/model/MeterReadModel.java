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
    private int degree;
    private int read_fee;
    private int read_degree;
    private int before_read_balance;
    private int after_read_balance;
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

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getRead_fee() {
        return read_fee;
    }

    public void setRead_fee(int read_fee) {
        this.read_fee = read_fee;
    }

    public int getRead_degree() {
        return read_degree;
    }

    public void setRead_degree(int read_degree) {
        this.read_degree = read_degree;
    }

    public int getBefore_read_balance() {
        return before_read_balance;
    }

    public void setBefore_read_balance(int before_read_balance) {
        this.before_read_balance = before_read_balance;
    }

    public int getAfter_read_balance() {
        return after_read_balance;
    }

    public void setAfter_read_balance(int after_read_balance) {
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
