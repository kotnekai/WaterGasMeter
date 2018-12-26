package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterTransactionModel;

import java.util.ArrayList;
import java.util.List;


public class GetWaterTransactionListEvent {

    private List<MeterTransactionModel> list = new ArrayList<>();

    public GetWaterTransactionListEvent(List<MeterTransactionModel> list) {
        this.list = list;
    }

    public List<MeterTransactionModel> getList() {
        return list;
    }
}
