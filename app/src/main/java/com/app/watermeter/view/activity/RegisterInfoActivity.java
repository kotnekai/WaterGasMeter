package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.ComResponseModel;
import com.app.watermeter.model.UserInfoParam;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

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
    public void onSuccessEvent(SuccessEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        ComResponseModel successModel = event.getSuccessModel();
        int status_code = successModel.getStatus_code();
        String message = successModel.getMessage();
        if (EmptyUtil.isEmpty(message)) {
            return;
        }
        if (status_code == 200) {
            startActivity(MainActivity.makeIntent(this));
        }
        ToastUtil.showShort(message);
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String userName = edtUserName.getText().toString();
        String emails = edtEmails.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPsw = edtConfirmPsw.getText().toString();
        if (EmptyUtil.isEmpty(phoneNumber)) {
            ToastUtil.showShort(getString(R.string.phone_number_error));
            return;
        }
        if (EmptyUtil.isEmpty(userName) || EmptyUtil.isEmpty(emails) || EmptyUtil.isEmpty(password) || EmptyUtil.isEmpty(confirmPsw)) {
            ToastUtil.showShort(getString(R.string.com_input_complete_tips));
            return;
        }
        UserInfoParam params = new UserInfoParam(phoneNumber, password, confirmPsw, emails, userName);
        ProgressUtils.getIntance().setProgressDialog(getString(R.string.com_loading_tips), this);
        UserManager.getInstance().register(params);
    }
}
