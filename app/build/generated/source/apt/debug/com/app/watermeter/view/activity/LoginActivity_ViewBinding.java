// Generated code from Butter Knife. Do not modify!
package com.app.watermeter.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity_ViewBinding;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> extends BaseActivity_ViewBinding<T> {
  private View view2131230839;

  private View view2131230927;

  private View view2131230924;

  private View view2131230921;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    super(target, source);

    View view;
    view = Utils.findRequiredView(source, R.id.llSerialNumber, "field 'llSerialNumber' and method 'onClick'");
    target.llSerialNumber = Utils.castView(view, R.id.llSerialNumber, "field 'llSerialNumber'", LinearLayout.class);
    view2131230839 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvSelectSerial = Utils.findRequiredViewAsType(source, R.id.tvSelectSerial, "field 'tvSelectSerial'", TextView.class);
    target.edtPhoneNumber = Utils.findRequiredViewAsType(source, R.id.edtPhoneNumber, "field 'edtPhoneNumber'", EditText.class);
    target.edtPassword = Utils.findRequiredViewAsType(source, R.id.edtPassword, "field 'edtPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tvLoginBtn, "field 'tvLoginBtn' and method 'onClick'");
    target.tvLoginBtn = Utils.castView(view, R.id.tvLoginBtn, "field 'tvLoginBtn'", TextView.class);
    view2131230927 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvGoRegister, "field 'tvGoRegister' and method 'onClick'");
    target.tvGoRegister = Utils.castView(view, R.id.tvGoRegister, "field 'tvGoRegister'", TextView.class);
    view2131230924 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvForgetPsw, "field 'tvForgetPsw' and method 'onClick'");
    target.tvForgetPsw = Utils.castView(view, R.id.tvForgetPsw, "field 'tvForgetPsw'", TextView.class);
    view2131230921 = view;
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

    target.llSerialNumber = null;
    target.tvSelectSerial = null;
    target.edtPhoneNumber = null;
    target.edtPassword = null;
    target.tvLoginBtn = null;
    target.tvGoRegister = null;
    target.tvForgetPsw = null;

    view2131230839.setOnClickListener(null);
    view2131230839 = null;
    view2131230927.setOnClickListener(null);
    view2131230927 = null;
    view2131230924.setOnClickListener(null);
    view2131230924 = null;
    view2131230921.setOnClickListener(null);
    view2131230921 = null;
  }
}
