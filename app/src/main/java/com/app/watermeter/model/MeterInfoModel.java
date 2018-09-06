package com.app.watermeter.model;


/**
 * @author admin
 *         https://www.showdoc.cc/web/#/137924192608060?page_id=789809581022573
 */
public class MeterInfoModel {

    int id;
    String machine_sn;
    int machine_type_id;
    int collector_id;
    int community_id;
    String location;
    String unit;
    int balance;
    String final_recharged_at;
    int degree;
    String final_read_at;
    String created_at;
    String updated_at;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCollector_id() {
        return collector_id;
    }

    public void setCollector_id(int collector_id) {
        this.collector_id = collector_id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFinal_recharged_at() {
        return final_recharged_at;
    }

    public void setFinal_recharged_at(String final_recharged_at) {
        this.final_recharged_at = final_recharged_at;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getFinal_read_at() {
        return final_read_at;
    }

    public void setFinal_read_at(String final_read_at) {
        this.final_read_at = final_read_at;
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
