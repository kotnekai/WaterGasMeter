package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.okhttputils.Model.Result;
import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.CheckSmsCodeEvent;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.views.SecurityCodeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCodeActivity extends BaseActivity {
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @BindView(R.id.tvGoNext)
    TextView tvGoNext;

    private final int millisInFuture = 60000;
    private final int countDownInterval = 1000;
    @BindView(R.id.scv_editText)
    SecurityCodeView scvEditText;
    private CountTimer countTimer;
    private StringBuffer veryCode;
    private String countryCode;
    private String phoneNumber;
    private final int MAX_CODE_LENGTH = 4;//验证码长度


    @Override
    protected int getCenterView() {
        return R.layout.activity_register_code;
    }

    public static Intent makeIntent(Context context, String countryCode, String phoneNumber) {
        Intent intent = new Intent(context, RegisterCodeActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("countryCode", countryCode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            countryCode = intent.getStringExtra("countryCode");
            phoneNumber = intent.getStringExtra("phoneNumber");
            String formatNumber = String.format(getString(R.string.register_verify_phone), countryCode, phoneNumber);
            tvPhone.setText(formatNumber);
        }
        if (countTimer == null) {
            countTimer = new CountTimer(millisInFuture, countDownInterval);
            countTimer.start();///开启倒计时
        }


        scvEditText.setDefaultCount(MAX_CODE_LENGTH);
        scvEditText.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
                 @Override
                 public void inputComplete() {

                     InputMethodManager imm = (InputMethodManager) scvEditText.getContext()
                             .getSystemService(Context.INPUT_METHOD_SERVICE);
                     if (imm.isActive()) {
                         imm.hideSoftInputFromWindow(scvEditText.getApplicationWindowToken(), 0);
                     }

                     String code = scvEditText.getEditContent();
                     ProgressUtils.getIntance().setProgressDialog(getString(R.string.com_loading_tips), RegisterCodeActivity.this);
                     UserManager.getInstance().checkSmsCode(phoneNumber, CommonParams.BUSS_REGISTER_TYPE, code);
                 }

                 @Override
                 public void deleteContent(boolean isDelete) {

                 }
           });
        UserManager.getInstance().sendSmsToCheck(phoneNumber, CommonParams.BUSS_REGISTER_TYPE);

    }




    /**
     * 发送验证码结果接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        Result result = event.getResult();
        int status_code = result.getStatus_code();
        String message = result.getMessage();
        int errCode = result.getErr_code();//业务码
        ToastUtil.showShort(message);
    }

    /**
     * 校验验证码结果接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckSmsCodeEvent(CheckSmsCodeEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        Result result = event.getResult();
        int status_code = result.getStatus_code();
        String message = result.getMessage();
        int errCode = result.getErr_code();//业务码
        ToastUtil.showShort(message);
        if (status_code == 200 && errCode == 0) {
            startActivity(RegisterInfoActivity.makeIntent(this, phoneNumber));
            scvEditText.clearEditText();
        }

    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_verify_code));
    }

    @OnClick({R.id.tvGoNext})
    public void getCode() {
        startActivity(RegisterInfoActivity.makeIntent(this, phoneNumber));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimer != null) {
            countTimer.cancel();
        }
    }


    /**
     * 点击按钮后倒计时
     */
    class CountTimer extends CountDownTimer {

        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 倒计时过程中调用
         *
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            tvGoNext.setText(String.format(getString(R.string.register_count_time), (millisUntilFinished / 1000) + ""));
            tvGoNext.setEnabled(false);
        }

        /**
         * 倒计时完成后调用
         */
        @Override
        public void onFinish() {
            tvGoNext.setEnabled(true);
            tvGoNext.setText(getString(R.string.register_next_step));
        }
    }
}
