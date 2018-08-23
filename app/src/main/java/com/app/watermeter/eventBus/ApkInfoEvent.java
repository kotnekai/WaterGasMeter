package com.app.watermeter.eventBus;

import com.app.watermeter.model.ApkInfoModel;

/**
 * Created by hasee on 2018/2/9.
 */

public class ApkInfoEvent {
    private ApkInfoModel apkInfoModel;
    private boolean isSelfCheck = false;
    public ApkInfoEvent(ApkInfoModel apkInfoModel, boolean isSelfCheck) {
        this.apkInfoModel = apkInfoModel;
        this.isSelfCheck = isSelfCheck;
    }

    public boolean isSelfCheck() {
        return isSelfCheck;
    }

    public ApkInfoModel getApkInfoModel() {
        return apkInfoModel;
    }
}
