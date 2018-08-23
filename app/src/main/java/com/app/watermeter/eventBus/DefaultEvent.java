package com.app.watermeter.eventBus;

import com.app.watermeter.model.DefaultEventModel;

public class DefaultEvent {
    private DefaultEventModel eventModel;

    public DefaultEvent(DefaultEventModel eventModel) {
        this.eventModel = eventModel;
    }

    public DefaultEventModel getEventModel() {
        return eventModel;
    }
}
