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
 *
 * @author admin
 * @date 2018/8/23
 */

public class PerStorageFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private ArrayList<PerStorageFragment> viewPagerFragments;
    private int pageType;

    public PerStorageFragmentAdapter(FragmentManager fm, int type) {
        super(fm);
        this.pageType = type;
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