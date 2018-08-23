// Generated code from Butter Knife. Do not modify!
package com.app.watermeter.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity_ViewBinding;
import com.app.watermeter.view.views.NoScrollViewPager;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> extends BaseActivity_ViewBinding<T> {
  private View view2131230816;

  private View view2131230818;

  private View view2131230819;

  private View view2131230922;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    super(target, source);

    View view;
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", NoScrollViewPager.class);
    view = Utils.findRequiredView(source, R.id.ivHomeTab, "field 'ivHomeTab' and method 'onClick'");
    target.ivHomeTab = Utils.castView(view, R.id.ivHomeTab, "field 'ivHomeTab'", ImageView.class);
    view2131230816 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivMeterTab, "field 'ivMeterTab' and method 'onClick'");
    target.ivMeterTab = Utils.castView(view, R.id.ivMeterTab, "field 'ivMeterTab'", ImageView.class);
    view2131230818 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivMineTab, "field 'ivMineTab' and method 'onClick'");
    target.ivMineTab = Utils.castView(view, R.id.ivMineTab, "field 'ivMineTab'", ImageView.class);
    view2131230819 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvFourthTab, "field 'tvFourthTab' and method 'onClick'");
    target.tvFourthTab = Utils.castView(view, R.id.tvFourthTab, "field 'tvFourthTab'", TextView.class);
    view2131230922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    super.unbind();

    target.viewPager = null;
    target.ivHomeTab = null;
    target.ivMeterTab = null;
    target.ivMineTab = null;
    target.tvFourthTab = null;

    view2131230816.setOnClickListener(null);
    view2131230816 = null;
    view2131230818.setOnClickListener(null);
    view2131230818 = null;
    view2131230819.setOnClickListener(null);
    view2131230819 = null;
    view2131230922.setOnClickListener(null);
    view2131230922 = null;
  }
}
