package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterReChargeModel;

import java.util.ArrayList;
import java.util.List;


public class GetDetailReChargeListEvent {

    private List<MeterReChargeModel> list = new ArrayList<>();

    public GetDetailReChargeListEvent(List<MeterReChargeModel> list) {
        this.list = list;
    }

    public List<MeterReChargeModel> getList() {
        return list;
    }
}
