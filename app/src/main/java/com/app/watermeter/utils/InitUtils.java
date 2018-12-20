package com.app.watermeter.utils;

import com.app.watermeter.model.SpinnerSelectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Admin on 2018/10/23
 */
public class InitUtils {

    public static List<SpinnerSelectModel> getCityCodeList() {
        List<SpinnerSelectModel> datas = new ArrayList<>();
        datas.add(new SpinnerSelectModel(0, "855"));
        datas.add(new SpinnerSelectModel(0, "86"));
        datas.add(new SpinnerSelectModel(0, "66"));
        return datas;
    }
}
