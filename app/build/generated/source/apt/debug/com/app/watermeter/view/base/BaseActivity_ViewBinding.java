// Generated code from Butter Knife. Do not modify!
package com.app.watermeter.view.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.watermeter.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseActivity_ViewBinding<T extends BaseActivity> implements Unbinder {
  protected T target;

  private View view2131230820;

  private View view2131230939;

  private View view2131230834;

  @UiThread
  public BaseActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawerLayout, "field 'drawerLayout'", DrawerLayout.class);
    target.tvTitleBar = Utils.findRequiredView(source, R.id.tvTitleBar, "field 'tvTitleBar'");
    target.rlBaseTitleLayout = Utils.findRequiredViewAsType(source, R.id.rlBaseTitleLayout, "field 'rlBaseTitleLayout'", RelativeLayout.class);
    target.ivGoBack = Utils.findRequiredViewAsType(source, R.id.ivGoBack, "field 'ivGoBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ivNext, "field 'ivNext' and method 'onClick'");
    target.ivNext = Utils.castView(view, R.id.ivNext, "field 'ivNext'", ImageView.class);
    view2131230820 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvNext, "field 'tvNext' and method 'onClick'");
    target.tvNext = Utils.castView(view, R.id.tvNext, "field 'tvNext'", TextView.class);
    view2131230939 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.llCenterView = Utils.findRequiredViewAsType(source, R.id.llCenterView, "field 'llCenterView'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.llEmptyPage, "field 'llEmptyPage' and method 'onClick'");
    target.llEmptyPage = Utils.castView(view, R.id.llEmptyPage, "field 'llEmptyPage'", LinearLayout.class);
    view2131230834 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlAddSelectContain = Utils.findRequiredViewAsType(source, R.id.rlAddSelectContain, "field 'rlAddSelectContain'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.drawerLayout = null;
    target.tvTitleBar = null;
    target.rlBaseTitleLayout = null;
    target.ivGoBack = null;
    target.tvTitle = null;
    target.ivNext = null;
    target.tvNext = null;
    target.llCenterView = null;
    target.llEmptyPage = null;
    target.rlAddSelectContain = null;

    view2131230820.setOnClickListener(null);
    view2131230820 = null;
    view2131230939.setOnClickListener(null);
    view2131230939 = null;
    view2131230834.setOnClickListener(null);
    view2131230834 = null;

    this.target = null;
  }
}
