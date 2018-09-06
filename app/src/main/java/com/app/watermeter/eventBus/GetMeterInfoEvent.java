package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterInfoModel;

import java.util.ArrayList;
import java.util.List;


public class GetMeterInfoEvent {

    private MeterInfoModel meterInfo;

    public GetMeterInfoEvent(MeterInfoModel model) {
        this.meterInfo = model;
    }

    public MeterInfoModel getModelInfo() {
        return meterInfo;
    }
}
