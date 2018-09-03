package com.app.watermeter.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.LoginEvent;
import com.app.watermeter.eventBus.RegisterInfoEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.LoginInfoModel;
import com.app.watermeter.model.UserInfoModel;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.llSerialNumber)
    LinearLayout llSerialNumber;
    @BindView(R.id.tvSelectSerial)
    TextView tvSelectSerial;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.tvLoginBtn)
    TextView tvLoginBtn;
    @BindView(R.id.tvGoRegister)
    TextView tvGoRegister;
    @BindView(R.id.tvForgetPsw)
    TextView tvForgetPsw;

    @Override
    protected int getCenterView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initHeader() {
        setHeaderVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginInfoEvent(LoginEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        LoginInfoModel infoModel = event.getLoginInfoModel();
        int status_code = infoModel.getStatus_code();
        String message = infoModel.getMessage();
        int err_code = infoModel.getErr_code();
        ToastUtil.showShort(message);

        if (status_code == 200&&err_code==0) {
            UserInfoModel data = infoModel.getData();
            if(data!=null){
                Log.d("admin", "onRegisterInfoEvent: data="+data);
                PreferencesUtils.putString(CommonParams.USER_TOKEN,data.getAccess_token());
                PreferencesUtils.putInt(CommonParams.TOKEN_PERIOD,data.getExpires_in());
            }
            startActivity(MainActivity.makeIntent(this));
            finish();
        }
    }

    @OnClick({R.id.llSerialNumber, R.id.tvLoginBtn, R.id.tvGoRegister, R.id.tvForgetPsw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llSerialNumber:
                break;
            case R.id.tvLoginBtn:
                String phoneNumber = edtPhoneNumber.getText().toString();
                String password = edtPassword.getText().toString();

                //startActivity(MainActivity.makeIntent(this));
                UserManager.getInstance().login(phoneNumber,password);
                //UserManager.getInstance().testIt();
              //  finish();
                break;
            case R.id.tvGoRegister:
                startActivity(RegisterPhoneActivity.makeIntent(this));
                break;
            case R.id.tvForgetPsw:
                startActivity(ResetPswActivity.makeIntent(this,ResetPswActivity.TYPE_RESET));
                break;

        }
    }
}
