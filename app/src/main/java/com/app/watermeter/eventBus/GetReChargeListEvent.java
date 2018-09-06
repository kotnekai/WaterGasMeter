package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterReChargeModel;

import java.util.ArrayList;
import java.util.List;


public class GetReChargeListEvent {

    private int meterType;

    public int getMeterType() {
        return meterType;
    }

    public void setMeterType(int meterType) {
        this.meterType = meterType;
    }

    private List<MeterReChargeModel> list = new ArrayList<>();

    public GetReChargeListEvent(List<MeterReChargeModel> list, int meterType) {
        this.list = list;
        this.meterType = meterType;
    }

    public List<MeterReChargeModel> getList() {
        return list;
    }
}
