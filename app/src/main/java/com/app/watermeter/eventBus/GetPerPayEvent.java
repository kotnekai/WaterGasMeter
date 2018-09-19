package com.app.watermeter.eventBus;

import com.app.watermeter.model.PerPayModel;


public class GetPerPayEvent {

    private PerPayModel perPayModel;

    public GetPerPayEvent(PerPayModel model) {
        this.perPayModel = model;
    }

    public PerPayModel getModelInfo() {
        return perPayModel;
    }
}
