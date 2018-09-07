package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterReChargeModel;

import java.util.ArrayList;
import java.util.List;


public class GetWaterReChargeListEvent {

    private List<MeterReChargeModel> list = new ArrayList<>();

    public GetWaterReChargeListEvent(List<MeterReChargeModel> list) {
        this.list = list;
    }

    public List<MeterReChargeModel> getList() {
        return list;
    }
}
