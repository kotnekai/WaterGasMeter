package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterTypeModel;

import java.util.ArrayList;
import java.util.List;


public class GetMeterListEvent {

    private int meterType;

    public int getMeterType() {
        return meterType;
    }

    public void setMeterType(int meterType) {
        this.meterType = meterType;
    }
    
    private List<MeterInfoModel> list = new ArrayList<>();

    public GetMeterListEvent(List<MeterInfoModel> list,int meterType) {
        this.list = list;
        this.meterType = meterType;
    }

    public List<MeterInfoModel> getList() {
        return list;
    }
}
