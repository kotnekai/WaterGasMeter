package com.app.watermeter.eventBus;

import com.app.watermeter.model.MeterInfoModel;


public class GetScanMeterInfoEvent {

    private MeterInfoModel meterInfo;

    public GetScanMeterInfoEvent(MeterInfoModel model) {
        this.meterInfo = model;
    }

    public MeterInfoModel getModelInfo() {
        return meterInfo;
    }
}
