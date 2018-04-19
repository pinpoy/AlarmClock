package com.jesgoo.fast.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.activity.AlarmClockEditActivity;
import com.jesgoo.fast.activity.TimerEditActivity;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.db.AlarmClockOperate;
import com.jesgoo.fast.pop.HomeAddWebPop;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc
 * Created by xupeng on 2018/3/28.
 */

public class HomeFragment extends Fragment implements HomeAddWebPop.HomeAddWebListener, Runnable {
    /**
     * 新建闹钟的requestCode
     */
    private static final int REQUEST_ALARM_CLOCK_NEW = 1;

    @Bind(R.id.tv_hh_mm)
    TextView tvHhMm;
    @Bind(R.id.tv_ss)
    TextView tvSs;
    @Bind(R.id.tv_week)
    TextView tvWeek;


    private HomeAddWebPop mHomeAddWebPop;
    private static final int msgKey1 = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    getTime();
                    tvHhMm.setText(mHour + ":" + mMinute);
                    tvSs.setText(mSecond);
                    tvWeek.setText(String.format("星期%s", mWay));
                    break;
                default:
                    break;
            }
        }
    };
    private String mHour;
    private String mMinute;
    private String mSecond;
    private String mWay;
    private String mYear;
    private String mMonth;
    private String mDay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    /**
     * 初始化布局
     */
    private void initView() {
        new Thread(this).start();
    }

    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(BUNDLE_TYPE, type);
        // Fragment传递数据方式
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.iv_home_add})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_home_add:
                showAddPop();
                break;
        }
    }


    /**
     * 点击添加
     */
    private void showAddPop() {
        if (null == mHomeAddWebPop) {
            mHomeAddWebPop = HomeAddWebPop.getInstance(getActivity());
        }
        mHomeAddWebPop.setHomeAddWebListener(this);
        mHomeAddWebPop.showPopupView(getRootView());
    }

    /**
     * 闹钟
     */
    @Override
    public void onAddClock() {
        Intent intent = new Intent(getActivity(), AlarmClockEditActivity.class);
        // 开启新建闹钟界面
        startActivityForResult(intent, REQUEST_ALARM_CLOCK_NEW);
    }

    /**
     * 计时器
     */
    @Override
    public void onAddTimer() {
        Intent intent = new Intent(getActivity(), TimerEditActivity.class);
        startActivity(intent);
    }

    /**
     * 睡眠计时器
     */
    @Override
    public void onAddTimerSleep() {

    }

    /**
     * 倒计时
     */
    @Override
    public void onAddCutdown() {

    }

    //获得当前年月日时分秒星期
    public void getTime() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        // 获取当前年份
        mYear = String.format("%02d", c.get(Calendar.YEAR));
        // 获取当前月份
        mMonth = String.format("%02d", c.get(Calendar.MONTH) + 1);
        // 获取当前月份的日期号码
        mDay = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
        //星期
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        //时(格式化：十位不足自动补零)
        mHour = String.format("%02d", c.get(Calendar.HOUR_OF_DAY));
        //分
        mMinute = String.format("%02d", c.get(Calendar.MINUTE));
        //秒
        mSecond = String.format("%02d", c.get(Calendar.SECOND));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = msgKey1;
                mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }


    protected View getRootView() {
        return getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        //返回的得到的闹钟数据
        AlarmClock ac = data
                .getParcelableExtra(WeacConstants.ALARM_CLOCK);
        switch (requestCode) {
            // 返回新建闹钟的信息
            case REQUEST_ALARM_CLOCK_NEW:

                break;

        }
    }
}
