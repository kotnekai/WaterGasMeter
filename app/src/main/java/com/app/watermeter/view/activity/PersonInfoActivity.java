package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.eventBus.PersonInfoEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.UserInfoModel;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvMail)
    TextView tvMail;
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    private Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_person_info;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PersonInfoActivity.class);
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.mine_person_info));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PersonInfoActivity.this;
        UserManager.getInstance().getPersonInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPersonInfoEvent(PersonInfoEvent event) {
        UserInfoModel userInfoModel = event.getUserInfoModel();
        if (userInfoModel == null) {
            ToastUtil.showShort(getString(R.string.request_data_error));
            return;
        }
        updateUI(userInfoModel);
    }

    private void updateUI(UserInfoModel userInfoModel) {
        tvUserName.setText(userInfoModel.getReal_name());
        tvMail.setText(userInfoModel.getEmail());
        tvPhone.setText(userInfoModel.getContact());
    }
}
