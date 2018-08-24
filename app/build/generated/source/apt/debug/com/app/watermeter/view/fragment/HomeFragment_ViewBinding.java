// Generated code from Butter Knife. Do not modify!
package com.app.watermeter.view.fragment;

import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseFragment_ViewBinding;
import java.lang.Override;

public class HomeFragment_ViewBinding<T extends HomeFragment> extends BaseFragment_ViewBinding<T> {
  private View view2131230938;

  private View view2131230936;

  private View view2131230937;

  @UiThread
  public HomeFragment_ViewBinding(final T target, View source) {
    super(target, source);

    View view;
    view = Utils.findRequiredView(source, R.id.tvMoreWater, "field 'tvMoreWater' and method 'onClick'");
    target.tvMoreWater = Utils.castView(view, R.id.tvMoreWater, "field 'tvMoreWater'", TextView.class);
    view2131230938 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvMoreElectricity, "field 'tvMoreElectricity' and method 'onClick'");
    target.tvMoreElectricity = Utils.castView(view, R.id.tvMoreElectricity, "field 'tvMoreElectricity'", TextView.class);
    view2131230936 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvMoreGas, "field 'tvMoreGas' and method 'onClick'");
    target.tvMoreGas = Utils.castView(view, R.id.tvMoreGas, "field 'tvMoreGas'", TextView.class);
    view2131230937 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.vpWater = Utils.findRequiredViewAsType(source, R.id.vpWater, "field 'vpWater'", ViewPager.class);
    target.vpElectricity = Utils.findRequiredViewAsType(source, R.id.vpElectricity, "field 'vpElectricity'", ViewPager.class);
    target.vpGas = Utils.findRequiredViewAsType(source, R.id.vpGas, "field 'vpGas'", ViewPager.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    super.unbind();

    target.tvMoreWater = null;
    target.tvMoreElectricity = null;
    target.tvMoreGas = null;
    target.vpWater = null;
    target.vpElectricity = null;
    target.vpGas = null;

    view2131230938.setOnClickListener(null);
    view2131230938 = null;
    view2131230936.setOnClickListener(null);
    view2131230936 = null;
    view2131230937.setOnClickListener(null);
    view2131230937 = null;
  }
}
