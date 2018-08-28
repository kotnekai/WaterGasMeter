package com.app.watermeter.model;

/**
 * Create by Admin on 2018/8/27
 * @author Admin
 */
public class PerSaveModel {
    private String meterSn;
    private String saveTime;
    private int saveMoney;

    public PerSaveModel(String meterSn, String saveTime, int saveMoney) {
        this.meterSn = meterSn;
        this.saveTime = saveTime;
        this.saveMoney = saveMoney;
    }

    public String getMeterSn() {
        return meterSn;
    }

    public void setMeterSn(String meterSn) {
        this.meterSn = meterSn;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public int getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(int saveMoney) {
        this.saveMoney = saveMoney;
    }
}
