package com.app.watermeter.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.view.activity.PersonInfoActivity;
import com.app.watermeter.view.activity.PreStorageSave2Activity;
import com.app.watermeter.view.activity.PreStorageSaveActivity;
import com.app.watermeter.view.activity.SettingActivity;
import com.app.watermeter.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.rlPreSave)
    RelativeLayout rlPreSave;

    @BindView(R.id.rlPayDes)
    RelativeLayout rlPayDes;

    @BindView(R.id.rlPersonInfo)
    RelativeLayout rlPersonInfo;

    @BindView(R.id.rlOtherSet)
    RelativeLayout rlOtherSet;

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
        return R.layout.fragment_mine;

    }

    @OnClick({R.id.rlPreSave,R.id.rlPayDes,R.id.rlPersonInfo,R.id.rlOtherSet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlPreSave:
                startActivity(PreStorageSaveActivity.makeIntent(getContext(), CommonParams.PAGE_TYPE_RECHARGE));
                break;
            case R.id.rlPayDes:
                startActivity(PreStorageSaveActivity.makeIntent(getContext(), CommonParams.PAGE_TYPE_READ));

                break;
            case R.id.rlPersonInfo:
                startActivity(PersonInfoActivity.makeIntent(getContext()));
                break;
            case R.id.rlOtherSet:
                startActivity(SettingActivity.makeIntent(getContext()));
                break;

        }
    }
}
