package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterInfoModel;

import java.util.ArrayList;
import java.util.List;


public class GetHomeMeterListEvent {

    private int meterType;

    public int getMeterType() {
        return meterType;
    }

    public void setMeterType(int meterType) {
        this.meterType = meterType;
    }

    private List<MeterInfoModel> list = new ArrayList<>();

    public GetHomeMeterListEvent(List<MeterInfoModel> list, int meterType) {
        this.list = list;
        this.meterType = meterType;
    }

    public List<MeterInfoModel> getList() {
        return list;
    }
}
