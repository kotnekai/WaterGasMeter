package com.app.watermeter.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.eventBus.BindEvent;
import com.app.watermeter.eventBus.BindingStatusEvent;
import com.app.watermeter.eventBus.GetMeterInfoEvent;
import com.app.watermeter.eventBus.GetScanMeterInfoEvent;
import com.app.watermeter.eventBus.GetScanMeterInfoFailEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.utils.DialogUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.activity.PayActionActivity;
import com.app.watermeter.view.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanFragment extends BaseFragment {

    private final int BIND_SUCCESS = 0;
    private final int METER_EMPTY = 1;
    private final int BINDED = 2;
    private final int SERVER_ERR = 5;

    @BindView(R.id.llScan)
    LinearLayout llScan;
    private final int REQUEST_CODE = 101;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        return R.layout.fragment_scan;

    }

    @OnClick({R.id.llScan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llScan:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetScanMeterInfoEvent event) {
       MeterInfoModel model = event.getModelInfo();
        if (model != null) {
            switch (model.getStatus())
            {
                case 1:
                    getActivity().startActivity(PayActionActivity.makeIntent(getContext(), model.getId(),true,model.getMachine_sn(),model));
                    break;
                case 0:
                    //表已被停用，不能进行充值
                    DialogUtils.showMeterDisEnabledHints(getActivity());
                    break;
            }
        }
    }
    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetScanMeterInfoFailEvent event) {
        DialogUtils.showScanMeterFailHints(getActivity());
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    if (result != null) {

                        String[] res = result.split("-");
                        if (res.length>2)
                        {
                            MeterManager.getInstance().getMeterDetail(res[2],true);
                        }
                        else
                        {
                            DialogUtils.showScanMeterFailHints(getActivity());
                        }

                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showShort(UIUtils.getValueString(R.string.scan_code));
                }
            }
        }
    }
}
