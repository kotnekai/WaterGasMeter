package com.app.watermeter.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.watermeter.R;
import com.app.watermeter.eventBus.BindEvent;
import com.app.watermeter.eventBus.BindingStatusEvent;
import com.app.watermeter.eventBus.GetMeterTypeEvent;
import com.app.watermeter.eventBus.UnBindEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.utils.DialogUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class BindingFragment extends BaseFragment {

    private final int BIND_SUCCESS = 0;
    private final int METER_EMPTY = 1;
    private final int BINDED = 2;
    private final int SERVER_ERR = 5;

    @BindView(R.id.llScanQrCode)
    LinearLayout llScanQrCode;
    @BindView(R.id.edtDeviceNumber)
    EditText edtDeviceNumber;
    @BindView(R.id.tvAddNumber)
    TextView tvAddNumber;
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
        return R.layout.fragment_binding;

    }

    @OnClick({R.id.llScanQrCode, R.id.tvAddNumber})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llScanQrCode:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tvAddNumber:
                String sn = edtDeviceNumber.getText().toString().trim();
                if (TextUtils.isEmpty(sn)) {
                    ToastUtil.showLong(getString(R.string.bind_sn_empty));
                }
                MeterManager.getInstance().bindMeter(sn);
                break;

        }
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BindEvent event) {
        switch (event.getResult().getErr_code()) {
            case BIND_SUCCESS:
                DialogUtils.showBindingSuccessHints(getActivity());
                EventBus.getDefault().post(new BindingStatusEvent(BindingStatusEvent.BINDING_SUCCESS));
                break;
            case METER_EMPTY:
                ToastUtil.showLong(getString(R.string.bind_empty));
                break;
            case BINDED:
                ToastUtil.showLong(getString(R.string.bind_already));
                break;
            case SERVER_ERR:
                ToastUtil.showLong(getString(R.string.bind_server_error));
                break;
        }
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
                        if (res.length > 2) {
                            MeterManager.getInstance().bindMeter(res[2]);
                        } else {
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
