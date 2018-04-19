package com.jesgoo.fast.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jesgoo.fast.R;
import com.jesgoo.fast.adapter.MainViewPagerAdapter;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.fragment.ClockCenterFragment;
import com.jesgoo.fast.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc
 * Created by xupeng on 2018/4/8.
 */

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.slidingViewPager)
    ViewPager slidingViewPager;
    @Bind(R.id.ll_guide_container)
    LinearLayout llGuideContainer;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private ImageView[] mDotIVs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);

        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ClockCenterFragment.newInstance());
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mFragments, null);
        slidingViewPager.setOffscreenPageLimit(2);
        slidingViewPager.setAdapter(mAdapter);
        slidingViewPager.setOnPageChangeListener(this);

        //viewpager进行滑动指示点显示
        mDotIVs = new ImageView[2];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        for (int i = 0; i < 2; i++) {
            // 指示点的初始化
            mDotIVs[i] = new ImageView(this);
            if (0 == i) {
                mDotIVs[i].setBackgroundResource(R.drawable.shape_dot_selected);
            } else {
                mDotIVs[i].setBackgroundResource(R.drawable.shape_dot_unselected);
            }
            layoutParams.setMargins(20, 0, 20, 0);  //动态画点，进行布局显示
            mDotIVs[i].setLayoutParams(layoutParams);
            llGuideContainer.addView(mDotIVs[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mDotIVs.length; i++) {
            if (position == i) {
                mDotIVs[i].setBackgroundResource(R.drawable.shape_dot_selected);
            } else {
                mDotIVs[i].setBackgroundResource(R.drawable.shape_dot_unselected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @OnClick({R.id.iv_home_add})
//    public void onClickEvent(View view) {
//        switch (view.getId()) {
//            case R.id.iv_home_add:
//                break;
//        }
//    }

    static long lastBack = System.currentTimeMillis();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastBack < 2000) {
                return super.onKeyDown(keyCode, event);
            } else {
                lastBack = System.currentTimeMillis();
                Toast.makeText(this, "需退出请再按一次返回", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }



}
