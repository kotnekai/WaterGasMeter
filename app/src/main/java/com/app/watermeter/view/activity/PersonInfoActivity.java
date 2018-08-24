package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

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

    }

}
