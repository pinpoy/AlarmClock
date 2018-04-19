package com.jesgoo.fast.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.db.AlarmClockOperate;
import com.jesgoo.fast.service.CountDownService;
import com.jesgoo.fast.utils.MyUtil;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc
 * Created by xupeng on 2018/3/30.
 */

public class HomeActivity extends BaseActivity implements CountDownService.TimerUpdateListener {
    @Bind(R.id.tv_click)
    TextView tvClick;

    /**
     * Log tag ：TimeFragment
     */
    private static final String LOG_TAG = "HomeActivity";
    @Bind(R.id.tv_show_number)
    TextView tvShowNumber;
    private int number = 50;

    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_click, R.id.tv_cutdown})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_click:

//                //创建闹钟
//                AlarmClock ac = new AlarmClock();
//                ac.setOnOff(true);   //闹钟的开关
//                ac.setHour(19);     //19:25
//                ac.setMinute(44);
//                ac.setRingUrl("default_ring_url");//默认铃声
//
//
//                // 初始化闹钟实例的振动，默认振动
//                ac.setVibrate(true);
//                // 初始化闹钟实例的小睡信息
//                // 默认小睡
//                ac.setNap(true);
//                // 小睡间隔10分钟
//                ac.setNapInterval(1);
//                // 小睡3次
//                ac.setNapTimes(3);
//
//                AlarmClockOperate.getInstance().saveAlarmClock(ac);
//                addList(ac);
//                toast("设置成功");

                startActivity(new Intent(this, AlarmClockEditActivity.class));
                break;
            case R.id.tv_cutdown:   //开启倒计时
                startCountDownService();
                break;
        }
    }

    /**
     * 开启倒计时的服务
     */
    private void startCountDownService() {
        Intent intent = new Intent(this, CountDownService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d(LOG_TAG, ": onServiceConnected");
            //执行service内部的方法
            CountDownService.TimerBinder binder = (CountDownService.TimerBinder) service;
            binder.setTimerUpdateListener(HomeActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d(LOG_TAG, ": onServiceDisconnected");
        }
    };

    /**
     * 添加闹钟实例并开启闹钟的服务
     * @param ac
     */
    private void addList(AlarmClock ac) {
        mAlarmClockList.clear();

        int id = ac.getId();
        int count = 0;
        int position = 0;
        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);

            if (id == alarmClock.getId()) {
                position = count;
                if (alarmClock.isOnOff()) {
                    MyUtil.startAlarmClock(this, alarmClock);
                }
            }
            count++;
        }

//        checkIsEmpty(list);

//        mAdapter.notifyItemInserted(position);
//        mRecyclerView.scrollToPosition(position);
    }

    /**
     * 每个一秒进行回调
     */
    @Override
    public void OnUpdateTime() {
        number--;
        Message message = new Message();
        message.what = 1;
        // 发送消息到消息队列中
        handler.sendMessage(message);


    }

    // Handler异步方式下载图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    // 下载成功
                    tvShowNumber.setText(number + "");
                    break;
            }
        }
    };
}
