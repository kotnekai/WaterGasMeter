package com.app.watermeter.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.watermeter.R;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class BingingFragment extends BaseFragment {
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

    @OnClick({R.id.llScanQrCode,R.id.tvAddNumber})
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.llScanQrCode:
                 Intent intent = new Intent(getActivity(), CaptureActivity.class);
                 startActivityForResult(intent, REQUEST_CODE);
                 break;
             case R.id.tvAddNumber:
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
                    ToastUtil.showShort(UIUtils.getValueString(R.string.scan_code));

                    if (result != null) {
                        ToastUtil.showShort(result);
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showShort(UIUtils.getValueString(R.string.scan_code));
                }
            }
        }
    }
}
