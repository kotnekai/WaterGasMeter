// Generated code from Butter Knife. Do not modify!
package com.app.watermeter.view.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.watermeter.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseFragment_ViewBinding<T extends BaseFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BaseFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.llEmptyPage = Utils.findRequiredViewAsType(source, R.id.llEmptyPage, "field 'llEmptyPage'", LinearLayout.class);
    target.tvConnectTip = Utils.findRequiredViewAsType(source, R.id.tvConnectTip, "field 'tvConnectTip'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llEmptyPage = null;
    target.tvConnectTip = null;

    this.target = null;
  }
}
