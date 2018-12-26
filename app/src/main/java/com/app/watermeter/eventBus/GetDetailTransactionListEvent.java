package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterTransactionModel;

import java.util.ArrayList;
import java.util.List;


/**
 * https://www.showdoc.cc/137924192608060?page_id=1348398341523813
 */
public class GetDetailTransactionListEvent {

    private List<MeterTransactionModel> list = new ArrayList<>();

    public GetDetailTransactionListEvent(List<MeterTransactionModel> list) {
        this.list = list;
    }

    public List<MeterTransactionModel> getList() {
        return list;
    }
}
