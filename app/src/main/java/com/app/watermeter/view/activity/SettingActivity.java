package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.Constants;
import com.app.watermeter.eventBus.LanguageChangedEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.utils.LanguageUtils;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.views.BottomDialogView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.llModifyPwd)
    LinearLayout llModifyPwd;
    @BindView(R.id.llChangedLanguage)
    RelativeLayout llChangedLanguage;
    @BindView(R.id.tvLanguage)
    TextView tvLanguage;
    @BindView(R.id.tvLoginOut)
    TextView tvLoginOut;

    View view;
    BottomDialogView bottomDialog;

    TextView tvChinese;
    TextView tvEnglish;
    TextView tvKm;
    TextView tvCancel;


    private Context mContext;
    private int currentLanguage;

    @Override
    protected int getCenterView() {
        return R.layout.activity_setting;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.mine_other_setting));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SettingActivity.this;
        initView();
        initLanguage();
    }

    private void initView() {

        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_language, null);
        bottomDialog = new BottomDialogView(mContext, view, false, false);
        tvChinese = view.findViewById(R.id.tvChinese);
        tvEnglish = view.findViewById(R.id.tvEnglish);
        tvKm = view.findViewById(R.id.tvKm);
        tvCancel = view.findViewById(R.id.tvCancel);
        tvChinese.setOnClickListener(this);
        tvEnglish.setOnClickListener(this);
        tvKm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        llModifyPwd.setOnClickListener(this);
        llChangedLanguage.setOnClickListener(this);
        tvLoginOut.setOnClickListener(this);
    }

    /**
     * 加载本地语言
     */
    private void initLanguage() {

        currentLanguage = LanguageUtils.getAppLanguage();

        switch (currentLanguage) {
            case Constants.LANGUAGE_DEFAULT:
                tvLanguage.setText(getString(R.string.language_cn));
                currentLanguage = Constants.LANGUAGE_CHINA;
                break;
            case Constants.LANGUAGE_CHINA:
                tvLanguage.setText(getString(R.string.language_cn));
                break;
            case Constants.LANGUAGE_ENGLISH:
                tvLanguage.setText(getString(R.string.language_en));
                break;
            case Constants.LANGUAGE_KH:
                tvLanguage.setText(getString(R.string.language_km));
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llModifyPwd:
                startActivity(ResetPswActivity.makeIntent(mContext, ResetPswActivity.TYPE_MODIFY));
                break;
            case R.id.llChangedLanguage:
                bottomDialog.show();
                break;
            case R.id.tvLoginOut:
                checkOutPushBinding();
                UserManager.getInstance().loginOut();
                PreferencesUtils.putString(CommonParams.USER_TOKEN, null);
                startActivity(LoginActivity.makeIntent(this));
                ComApplication.getApp().removeAllActivity();
                break;
            case R.id.tvChinese:
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage != Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_CHINA) {
                    tvLanguage.setText(getString(R.string.language_cn));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_CHINA);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }
                break;
            case R.id.tvEnglish:
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage != Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_ENGLISH) {
                    tvLanguage.setText(getString(R.string.language_en));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_ENGLISH);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }
                break;
            case R.id.tvKm:
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage != Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_KH) {
                    tvLanguage.setText(getString(R.string.language_km));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_KH);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }

                break;
            case R.id.tvCancel:
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                break;
        }
    }

    /**
     * 解绑推送
     */
    private void checkOutPushBinding() {
        PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
            }

            @Override
            public void onFailed(String errorCode, String errorMsg) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bottomDialog.isShowing()) {
            bottomDialog.dismiss();
            bottomDialog = null;
        }
    }
}
