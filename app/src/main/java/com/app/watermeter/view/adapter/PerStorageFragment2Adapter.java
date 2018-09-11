package com.app.watermeter.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.watermeter.view.base.BaseFragment;
import com.app.watermeter.view.fragment.ReadAndReChargeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 * @date 2018/8/23
 */

public class PerStorageFragment2Adapter extends FragmentStatePagerAdapter {

    private List<String> titles;
    private List<BaseFragment> viewPagerFragments;
    private int pageType;

    public PerStorageFragment2Adapter(FragmentManager fm, int type, List<BaseFragment> fragments) {
        super(fm);
        this.pageType = type;
        this.viewPagerFragments = fragments;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

//    public void setFragments(ArrayList<ReadAndReChargeFragment> viewPagerFragments) {
//        this.viewPagerFragments = viewPagerFragments;
//    }

    @Override
    public Fragment getItem(int position) {
        return viewPagerFragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(viewPagerFragments != null){
            ret = viewPagerFragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}