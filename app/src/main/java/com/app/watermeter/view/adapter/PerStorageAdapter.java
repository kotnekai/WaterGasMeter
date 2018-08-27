package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.app.watermeter.R;
import com.app.watermeter.view.fragment.PerStorageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/8/23.
 */

public class PerStorageAdapter  extends FragmentStatePagerAdapter {

    private String[] titles;
    private ArrayList<PerStorageFragment> viewPagerFragments;

    public PerStorageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(ArrayList<PerStorageFragment> viewPagerFragments) {
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