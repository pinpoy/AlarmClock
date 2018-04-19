package com.jesgoo.fast;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jesgoo.fast.adapter.MainViewPagerAdapter;
import com.jesgoo.fast.api.ApiServiceFactory;
import com.jesgoo.fast.api.HttpUrls;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.Message;
import com.jesgoo.fast.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Desc
 * Created by xupeng on 2018/3/23.
 */

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {
//    @Bind(R.id.tv_title)
//    TextView tvTitle;

    @Bind(R.id.slidingViewPager)
    ViewPager slidingViewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private final String[] mTabs = new String[4];
    private Fragment mCurFragment;

    private static final int INDEX_HOME = 0;            // 首页
    private static final int INDEX_ORDER = 1;           // 订单
    private static final int INDEX_GOODS = 2;           // 商品
    private static final int INDEX_ACCOUNT = 3;         // 账户

    private static final int DEFAULT_SELECTED_INDEX = INDEX_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabData();
        initView();
    }

    private void initTabData() {
        mTabs[0] = getString(R.string.tab_first);
        mTabs[1] = getString(R.string.tab_order);
        mTabs[2] = getString(R.string.tab_goods);
        mTabs[3] = getString(R.string.tab_account);
    }

    private void initView() {
        // 添加四个Fragment
        initFragments();
        // 添加Tab标题
        initTabTitle();
        // 绑定Fragments与Tabs
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mFragments, mTabs);
        slidingViewPager.setOffscreenPageLimit(3);
        slidingViewPager.setAdapter(mAdapter);
        //slidingViewPager.setCanSliding(false);
//        tabLayout.setupWithViewPager(slidingViewPager);
        slidingViewPager.setCurrentItem(DEFAULT_SELECTED_INDEX);
        slidingViewPager.addOnPageChangeListener(this);

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());


        mCurFragment = mFragments.get(DEFAULT_SELECTED_INDEX);

    }

    private void initTabTitle() {
        int size = mTabs.length;
        boolean isSelect;

        for (int i = 0; i < size; i++) {
            isSelect = (i == DEFAULT_SELECTED_INDEX);

            TabLayout.Tab tab = tabLayout.newTab();
            switch (i) {
                case INDEX_HOME:
                    tab.setIcon(R.drawable.selector_main_home);
                    break;

                case INDEX_ORDER:
                    tab.setIcon(R.drawable.selector_main_orders);
                    break;

                case INDEX_GOODS:
                    tab.setIcon(R.drawable.selector_main_goods);
                    break;

                case INDEX_ACCOUNT:
                    tab.setIcon(R.drawable.selector_main_user);
                    break;

                default:
                    break;
            }

            tab.setText(mTabs[i]);
            tabLayout.addTab(tab, isSelect);
        }
        tabLayout.setOnTabSelectedListener(this);
    }


    /**
     * 请求数据
     */
    private void requestData(String type) {
        ApiServiceFactory.getApiService(HttpUrls.HTTP_BASE_URL)
                .getFromJuhe(getDataMap(type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Message>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Message message) {
                        if (null != message) {
                        }
                    }
                });
    }


    private Map<String, String> getDataMap(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("key", "dca490edff1205ae40eab07d90c71d8b");

        return map;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurFragment = mFragments.get(position);
    }

    //--------------------------------

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab == tabLayout.getTabAt(INDEX_HOME)) {                                // 首页
            slidingViewPager.setCurrentItem(INDEX_HOME);
            mFragments.get(INDEX_HOME).onResume();
        } else if (tab == tabLayout.getTabAt(INDEX_ORDER)) {                        // 订单
            slidingViewPager.setCurrentItem(INDEX_ORDER);
        } else if (tab == tabLayout.getTabAt(INDEX_GOODS)) {                        // 商品
            slidingViewPager.setCurrentItem(INDEX_GOODS);
        } else if (tab == tabLayout.getTabAt(INDEX_ACCOUNT)) {                      // 账户
            slidingViewPager.setCurrentItem(INDEX_ACCOUNT);
            mFragments.get(INDEX_ACCOUNT).onResume();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

//    @OnClick(R.id.tv_title)
//    public void clickEvent(View view) {
//        switch (view.getId()) {
//            case R.id.tv_title:
//                requestData("tiyu");
//                break;
//        }
//    }


}
