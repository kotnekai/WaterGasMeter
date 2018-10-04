package com.app.watermeter.eventBus;

import com.app.watermeter.model.OrderInfoModel;
import com.app.watermeter.model.PerPayModel;


public class GetOrderInfoEvent {

    private OrderInfoModel orderInfoModel;

    public GetOrderInfoEvent(OrderInfoModel model) {
        this.orderInfoModel = model;
    }

    public OrderInfoModel getOrderInfo() {
        return orderInfoModel;
    }
}
