package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.app.okhttputils.Model.Result;
import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.UserCache;
import com.app.watermeter.eventBus.RegisterInfoEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.LoginInfoModel;
import com.app.watermeter.model.AccountExtraModel;
import com.app.watermeter.model.UserInfoParam;
import com.app.watermeter.utils.AccountValidatorUtil;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterInfoActivity extends BaseActivity {

    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtEmails)
    EditText edtEmails;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPsw)
    EditText edtConfirmPsw;
    @BindView(R.id.TvSubmitInfo)
    TextView TvSubmitInfo;

    String phoneNumber;

    @Override
    protected int getCenterView() {
        return R.layout.activity_register_info;
    }

    public static Intent makeIntent(Context context, String phoneNumber) {
        Intent intent = new Intent(context, RegisterInfoActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        return intent;
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_info_title));
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterInfoEvent(RegisterInfoEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        Result infoModel = event.getResult();
        int status_code = infoModel.getStatus_code();
        String message = infoModel.getMessage();
        int err_code = infoModel.getErr_code();
        ToastUtil.showShort(message);

        if (status_code == 200 && err_code == 0) {

            PreferencesUtils.putString(CommonParams.USER_ACCOUNT, phoneNumber);
            //绑定推送
            initPushBinding(phoneNumber);

            Gson gson = new Gson();
            String jsonString = gson.toJson(infoModel.getData());
            AccountExtraModel data = gson.fromJson(jsonString,AccountExtraModel.class);

                if (data != null) {
                    Log.d("admin", "onRegisterInfoEvent: data=" + data);
                    UserCache.getInstance().setToken(data.getAccess_token());
                    PreferencesUtils.putInt(CommonParams.TOKEN_PERIOD, data.getExpires_in());
                }
                startActivity(MainActivity.makeIntent(this));
                finish();
            }
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        String userName = edtUserName.getText().toString();
        String emails = edtEmails.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPsw = edtConfirmPsw.getText().toString();
//        if (!AccountValidatorUtil.isMobile(phoneNumber)) {
//            ToastUtil.showShort(getString(R.string.phone_number));
//            return;
//        }
        if (EmptyUtil.isEmpty(userName)  || EmptyUtil.isEmpty(password) || EmptyUtil.isEmpty(confirmPsw)) {
            ToastUtil.showShort(getString(R.string.com_input_complete_tips));
            return;
        }
        UserInfoParam params = new UserInfoParam(phoneNumber, password, confirmPsw, emails, userName);
        ProgressUtils.getIntance().setProgressDialog(getString(R.string.com_loading_tips), this);
        UserManager.getInstance().register(params);
    }


    /**
     * 推送绑定账号
     */
    private void initPushBinding(String phone) {


        PushServiceFactory.getCloudPushService().bindAccount(phone, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                System.out.print("====bind account " + "" + " success\n");
                MeterManager.getInstance().deviceUpload( PushServiceFactory.getCloudPushService().getDeviceId());
            }

            @Override
            public void onFailed(String errorCode, String errorMsg) {
                System.out.print("===bind account " + "" + " failed." +
                        "errorCode: " + errorCode + ", errorMsg:" + errorMsg);
            }
        });
    }
}
