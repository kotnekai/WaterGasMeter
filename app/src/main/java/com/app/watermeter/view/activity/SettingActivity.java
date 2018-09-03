package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.Constants;
import com.app.watermeter.eventBus.LanguageChangedEvent;
import com.app.watermeter.utils.LanguageUtils;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.views.BottomDialogView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.llModifyPwd)
    LinearLayout llModifyPwd;
    @BindView(R.id.llChangedLanguage)
    RelativeLayout llChangedLanguage;
    @BindView(R.id.tvLanguage)
    TextView tvLanguage;


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
        initLanguage();
    }

    /**
     * 加载本地语言
     */
    private void initLanguage() {

         currentLanguage = LanguageUtils.getAppLanguage();

        switch (currentLanguage) {
            case Constants.LANGUAGE_DEFAULT:
                tvLanguage.setText(getString(R.string.language_cn));
                break;
            case Constants.LANGUAGE_CHINA:
                tvLanguage.setText(getString(R.string.language_cn));
                break;
            case Constants.LANGUAGE_ENGLISH:
                tvLanguage.setText(getString(R.string.language_en));
                break;
            case Constants.LANGUAGE_KP:
                tvLanguage.setText(getString(R.string.language_km));
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.llModifyPwd, R.id.llChangedLanguage,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llModifyPwd:
                startActivity(ResetPswActivity.makeIntent(mContext, ResetPswActivity.TYPE_MODIFY));
                break;
            case R.id.llChangedLanguage:
                showLanguageSelectDialog();
                break;

        }
    }


    private void showLanguageSelectDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_language, null);
        final BottomDialogView bottomDialog = new BottomDialogView(mContext, view, false, false);

        TextView tvChinese = view.findViewById(R.id.tvChinese);
        TextView tvEnglish = view.findViewById(R.id.tvEnglish);
        TextView tvKm = view.findViewById(R.id.tvKm);
        TextView tvCancel = view.findViewById(R.id.tvCancel);

        //切换中文
        tvChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage!=Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_CHINA) {
                    tvLanguage.setText(getString(R.string.language_cn));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_CHINA);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }
            }
        });
        //切换英文
        tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage!=Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_ENGLISH) {
                    tvLanguage.setText(getString(R.string.language_en));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_ENGLISH);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }
            }
        });
        //切换柬埔寨
        tvKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                if (currentLanguage!=Constants.LANGUAGE_DEFAULT && currentLanguage != Constants.LANGUAGE_KP) {
                    tvLanguage.setText(getString(R.string.language_km));
                    LanguageUtils.setAppLanguage(Constants.LANGUAGE_KP);
                    LanguageUtils.applyChange(mContext);
                    EventBus.getDefault().post(new LanguageChangedEvent());
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
            }
        });
        bottomDialog.show();

    }


}
