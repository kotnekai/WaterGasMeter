package com.app.watermeter.model;

/**
 * Create by Admin on 2018/8/27
 */
public class PreSaveModel {
    private String waterMeterCode;
    private String saveTime;
    private int saveMoney;

    public PreSaveModel(String waterMeterCode, String saveTime, int saveMoney) {
        this.waterMeterCode = waterMeterCode;
        this.saveTime = saveTime;
        this.saveMoney = saveMoney;
    }

    public String getWaterMeterCode() {
        return waterMeterCode;
    }

    public void setWaterMeterCode(String waterMeterCode) {
        this.waterMeterCode = waterMeterCode;
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
