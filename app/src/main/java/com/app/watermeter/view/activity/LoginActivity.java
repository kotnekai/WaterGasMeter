package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.okhttputils.Model.Result;
import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.LoginEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.LoginInfoModel;
import com.app.watermeter.model.AccountExtraModel;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.base.BaseActivity;
import com.google.gson.Gson;

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

    public static Intent makeIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }
    public static Intent makeIntent(Context context, boolean isExit) {

        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(CommonParams.isExit, isExit);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        boolean isExit = intent.getBooleanExtra(CommonParams.isExit, false);
        if (isExit) {
            ComApplication.getApp().removeAllActivity(ComApplication.getApp().getCurrentActivity());
            Toast.makeText(LoginActivity.this, UIUtils.getValueString(R.string.login_invalid_tip), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginInfoEvent(LoginEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        Result infoModel = event.getResult();
        int status_code = infoModel.getStatus_code();
        String message = infoModel.getMessage();
        int err_code = infoModel.getErr_code();
        ToastUtil.showShort(message);

        if (status_code == 200&&err_code==0) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(infoModel.getData());
            AccountExtraModel data = gson.fromJson(jsonString,AccountExtraModel.class);

                if (data != null) {
                    Log.d("admin", "onRegisterInfoEvent: data=" + data);
                    PreferencesUtils.putString(CommonParams.USER_TOKEN, data.getAccess_token());
                    PreferencesUtils.putInt(CommonParams.TOKEN_PERIOD, data.getExpires_in());
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
                UserManager.getInstance().login(phoneNumber,password);
                break;
            case R.id.tvGoRegister:
                startActivity(RegisterPhoneActivity.makeIntent(this,CommonParams.fromTypeRegister));
                break;
            case R.id.tvForgetPsw:
                startActivity(RegisterPhoneActivity.makeIntent(this,CommonParams.fromTypeReset));
                break;

        }
    }
}
