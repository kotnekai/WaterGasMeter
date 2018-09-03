package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.ComResponseModel;
import com.app.watermeter.utils.AccountValidatorUtil;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class ResetPswActivity extends BaseActivity {

    public final static String Pwd_TYPE = "pwdType";

    public final static int TYPE_MODIFY = 101;
    public final static int TYPE_RESET = 102;

    private int type;

    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPsw)
    EditText edtConfirmPsw;
    @BindView(R.id.TvSubmitInfo)
    TextView TvSubmitInfo;

    Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_reset_psw;
    }

    public static Intent makeIntent(Context context, int type) {
        Intent intent = new Intent(context, ResetPswActivity.class);
        intent.putExtra(Pwd_TYPE, type);
        return intent;
    }

    @Override
    protected void initHeader() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ResetPswActivity.this;
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Pwd_TYPE, 0);
        if (type == TYPE_MODIFY) {
            setHeaderTitle(getString(R.string.reset_modify_title));
        } else if (type == TYPE_RESET) {
            setHeaderTitle(getString(R.string.reset_psw_title));
        }
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {
        String password = edtPassword.getText().toString();
        boolean isValidPsw = AccountValidatorUtil.isPassword(password);
        Log.d("admin", "submitUserInfo: isValidPsw=" + isValidPsw);
        String confirmPsw = edtConfirmPsw.getText().toString();
        if (!isValidPsw) {
            ToastUtil.showShort(getString(R.string.password_no_valid));
            return;
        }
        if (!password.equals(confirmPsw)) {
            ToastUtil.showShort(getString(R.string.password_no_match));
            return;
        }
        UserManager.getInstance().resetPassword(password, confirmPsw);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        ComResponseModel successModel = event.getSuccessModel();
        if (successModel == null) {
            ToastUtil.showShort(getString(R.string.request_data_error));
            return;
        }
        int status_code = successModel.getStatus_code();
        int err_code = successModel.getErr_code();
        String message = successModel.getMessage();
        ToastUtil.showShort(message);
        if (err_code == 0 && status_code == 200) {
            finish();
        }
    }
}
