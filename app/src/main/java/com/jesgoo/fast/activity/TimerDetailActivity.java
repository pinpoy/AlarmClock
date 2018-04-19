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
import android.widget.Button;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.Timer;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.db.TimerOperate;
import com.jesgoo.fast.service.CountDownService;
import com.jesgoo.fast.utils.MyUtil;

import org.litepal.util.LogUtil;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc
 * Created by xupeng on 2018/4/16.
 */

public class TimerDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.bt_timer_start)
    Button btTimerStart;
    @Bind(R.id.tv_detail_time)
    TextView tvDetailTime;
    private Timer mTimer;

    private static final String LOG_TAG = "TimerDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_detail);
        ButterKnife.bind(this);

        mTimer = getIntent().getParcelableExtra(WeacConstants.Timer_detail);
        btTimerStart.setOnClickListener(this);
        initView();
    }

    /**
     * 初始化时间
     */
    private void initView() {
        //初始化
        // GMT（格林尼治标准时间）一般指世界时.中国时间（GST）与之相差-8小时
        TimeZone tz = TimeZone.getTimeZone("GMT");
        mTimeRemain = Calendar.getInstance();
        mTimeRemain.clear();
        mTimeRemain.setTimeZone(tz);


        setStartTime(mTimer.getMinute(), mTimer.getSeconds());//为剩余时间赋值
        setDisplayNumber();
        tvDetailTime.setText(mDisplayRemainTime);


    }

    @OnClick({R.id.tv_back})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                drawAnimationFinish();
                break;

        }
    }

    boolean state = true;

    @Override
    public void onClick(View v) {
        if (mTimeRemain.get(Calendar.MINUTE) == 0 && mTimeRemain.get(Calendar.SECOND) == 0)
            return;

        if (state) {
            state = false;
            btTimerStart.setBackgroundResource(R.mipmap.timer_detail_cancel);
            //执行方法一

            //开启倒计时
            startCountDownService();

        } else {
            state = true;
            btTimerStart.setBackgroundResource(R.mipmap.timer_detail_start);
            //执行方法二
            //停止倒计时
            stopCountDown();
            setCurrentAndUpdateTime();

        }
    }

    /**
     * 倒计时剩余时间
     */
    private Calendar mTimeRemain;

    /**
     * 设置开始的时间
     *
     * @param minute
     * @param seconds
     */
    private void setStartTime(int minute, int seconds) {
        mTimeRemain.set(Calendar.MINUTE, minute);
        mTimeRemain.set(Calendar.SECOND, seconds);
    }

    /**
     * 显示的剩余时间
     */
    private String mDisplayRemainTime;

    /**
     * 设置显示的剩余时间
     */
    private void setDisplayNumber() {
        mDisplayRemainTime = MyUtil.formatTimeSpace(mTimeRemain.get(Calendar.MINUTE),
                mTimeRemain.get(Calendar.SECOND));
    }


    /**
     * 开启倒计时的服务
     */
    private void startCountDownService() {
        Intent intent = new Intent(this, CountDownService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    // Handler异步方式下载图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    setDisplayNumber();
                    tvDetailTime.setText(mDisplayRemainTime);
                    break;
            }
        }
    };

    /**
     * 开启及时服务后的回调
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d(LOG_TAG, ": onServiceConnected");
            //执行service内部的方法
            CountDownService.TimerBinder binder = (CountDownService.TimerBinder) service;
            binder.setTimerUpdateListener(new CountDownService.TimerUpdateListener() {
                @Override       //时间更新回调
                public void OnUpdateTime() {
                    //时间递减一秒的逻辑
                    if (!isTimeEmpty()) {
                        mTimeRemain.add(Calendar.MILLISECOND, -1000);
                    } else {
                        //停止倒计时
                        stopCountDown();
                        setCurrentAndUpdateTime();

                        //开启提醒页面
                        startActivity(new Intent(TimerDetailActivity.this, TimerOnTimeActivity.class));
                    }

                    Message message = new Message();
                    message.what = 1;
                    // 发送消息到消息队列中
                    handler.sendMessage(message);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d(LOG_TAG, ": onServiceDisconnected");
        }
    };


    /**
     * 计时时间是否为0
     *
     * @return 计时时间是否为0
     */
    private boolean isTimeEmpty() {
        return !(mTimeRemain.get(Calendar.HOUR_OF_DAY) != 0
                || mTimeRemain.get(Calendar.MINUTE) != 0
                || mTimeRemain.get(Calendar.SECOND) != 0
                || mTimeRemain.get(Calendar.MILLISECOND) != 0);
    }


    private void stopCountDown() {
        unbindService(mConnection);
    }


    /**
     * 保存显示时间并存入数据库
     */
    private void setCurrentAndUpdateTime() {
        TimerOperate.getInstance().updateTimer(mTimer.getId(),
                mTimeRemain.get(Calendar.MINUTE), mTimeRemain.get(Calendar.SECOND));
    }

}
