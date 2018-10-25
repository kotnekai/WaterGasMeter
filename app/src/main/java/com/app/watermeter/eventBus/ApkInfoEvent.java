package com.app.watermeter.eventBus;

import com.app.watermeter.model.VersionData;

/**
 * Created by hasee on 2018/2/9.
 */

public class ApkInfoEvent {
    private VersionData apkInfoModel;
    private boolean isSelfCheck = false;
    public ApkInfoEvent(VersionData apkInfoModel, boolean isSelfCheck) {
        this.apkInfoModel = apkInfoModel;
        this.isSelfCheck = isSelfCheck;
    }

    public boolean isSelfCheck() {
        return isSelfCheck;
    }

    public VersionData getApkInfoModel() {
        return apkInfoModel;
    }
}
