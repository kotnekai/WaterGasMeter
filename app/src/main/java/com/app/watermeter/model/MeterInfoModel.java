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

    String location_zh;
    String location_en;
    String location_kh;
    String position_zh;
    String position_en;
    String position_kh;
    int measurement_id;
    String unit;
    float balance;
    float degree;
    String final_recharged_at;
    float old_degree;
    String old_read_at;
    String final_read_at;
    String created_at;
    String updated_at;
    int status;
    int is_opened;
    float start_month_degree;
    float this_month_degree;

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

    public String getLocation_zh() {
        return location_zh;
    }

    public void setLocation_zh(String location_zh) {
        this.location_zh = location_zh;
    }

    public String getLocation_en() {
        return location_en;
    }

    public void setLocation_en(String location_en) {
        this.location_en = location_en;
    }

    public String getLocation_kh() {
        return location_kh;
    }

    public void setLocation_kh(String location_kh) {
        this.location_kh = location_kh;
    }

    public String getPosition_zh() {
        return position_zh;
    }

    public void setPosition_zh(String position_zh) {
        this.position_zh = position_zh;
    }

    public String getPosition_en() {
        return position_en;
    }

    public void setPosition_en(String position_en) {
        this.position_en = position_en;
    }

    public String getPosition_kh() {
        return position_kh;
    }

    public void setPosition_kh(String position_kh) {
        this.position_kh = position_kh;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurement_id = measurement_id;
    }

    public String getFinal_recharged_at() {
        return final_recharged_at;
    }

    public void setFinal_recharged_at(String final_recharged_at) {
        this.final_recharged_at = final_recharged_at;
    }

    public float getOld_degree() {
        return old_degree;
    }

    public void setOld_degree(float old_degree) {
        this.old_degree = old_degree;
    }

    public String getOld_read_at() {
        return old_read_at;
    }

    public void setOld_read_at(String old_read_at) {
        this.old_read_at = old_read_at;
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

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public int getIs_opened() {
        return is_opened;
    }

    public void setIs_opened(int is_opened) {
        this.is_opened = is_opened;
    }

    public float getStart_month_degree() {
        return start_month_degree;
    }

    public void setStart_month_degree(float start_month_degree) {
        this.start_month_degree = start_month_degree;
    }

    public float getThis_month_degree() {
        return this_month_degree;
    }

    public void setThis_month_degree(float this_month_degree) {
        this.this_month_degree = this_month_degree;
    }
}
