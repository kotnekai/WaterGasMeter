package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterReadModel;

import java.util.ArrayList;
import java.util.List;


public class GetReadListEvent {

    private int meterType;

    public int getMeterType() {
        return meterType;
    }

    public void setMeterType(int meterType) {
        this.meterType = meterType;
    }

    private List<MeterReadModel> list = new ArrayList<>();

    public GetReadListEvent(List<MeterReadModel> list, int meterType) {
        this.list = list;
        this.meterType = meterType;
    }

    public List<MeterReadModel> getList() {
        return list;
    }
}
