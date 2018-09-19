package com.app.watermeter.model;

/**
 * Created by admin on 2018/9/18.
 * https://www.showdoc.cc/137924192608060?page_id=895267016994174
 */

public class PerPayModel {
    private int trade_id;// 消息标题
    private String out_trade_no;// 消息id

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
