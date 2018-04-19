package com.jesgoo.fast.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ProjectName: MainViewPagerAdapter
 * Description: 主页面的VPAdapter
 * <p>
 * author: JeyZheng
 * version: 2.0
 * created at: 2016/8/22 18:31
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] mTitles;

    public MainViewPagerAdapter(
            FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);

        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
