package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterTypeModel;

import java.util.ArrayList;
import java.util.List;


public class GetMeterTypeEvent {

    private List<MeterTypeModel> typeList = new ArrayList<>();

    public GetMeterTypeEvent(List<MeterTypeModel> list) {
        this.typeList = list;
    }

    public List<MeterTypeModel> getTypeList() {
        return typeList;
    }
}
