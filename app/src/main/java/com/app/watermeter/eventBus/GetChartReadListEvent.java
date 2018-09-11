package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterReadModel;

import java.util.ArrayList;
import java.util.List;


public class GetChartReadListEvent {


    private List<MeterReadModel> list = new ArrayList<>();

    public GetChartReadListEvent(List<MeterReadModel> list) {
        this.list = list;
    }

    public List<MeterReadModel> getList() {
        return list;
    }
}
