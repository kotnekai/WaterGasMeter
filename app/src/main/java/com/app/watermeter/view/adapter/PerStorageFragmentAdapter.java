package com.app.watermeter.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.watermeter.view.fragment.ReadAndReChargeFragment;

import java.util.ArrayList;

/**
 *
 * @author admin
 * @date 2018/8/23
 */

public class PerStorageFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private ArrayList<ReadAndReChargeFragment> viewPagerFragments;
    private int pageType;

    public PerStorageFragmentAdapter(FragmentManager fm, int type) {
        super(fm);
        this.pageType = type;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(ArrayList<ReadAndReChargeFragment> viewPagerFragments) {
        this.viewPagerFragments = viewPagerFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return viewPagerFragments.get(position);
    }

    @Override
    public int getCount() {
        return viewPagerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}