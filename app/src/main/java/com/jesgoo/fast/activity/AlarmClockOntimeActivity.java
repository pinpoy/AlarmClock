package com.jesgoo.fast.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.brocast.AlarmClockBroadcast;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.common.WeacStatus;
import com.jesgoo.fast.utils.AudioPlayer;
import com.jesgoo.fast.widget.MySlidingView;

import org.litepal.util.LogUtil;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc   闹钟响起画面Fragment
 * Created by xupeng on 2018/4/2.
 */

public class AlarmClockOntimeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * Log tag ：AlarmClockOntimeFragment
     */
    private static final String LOG_TAG = "AlarmClockOntimeFragme";
    /**
     * 当前时间
     */
    @Bind(R.id.ontime_time)
    TextView mTimeTv;
    @Bind(R.id.ontime_nap)
    TextView napTv;
    @Bind(R.id.my_sliding_view)
    MySlidingView mySlidingView;


    /**
     * 闹钟实例
     */
    private AlarmClock mAlarmClock;

    /**
     * 线程运行flag
     */
    private boolean mIsRun = true;

    /**
     * 线程标记
     */
    private static final int UPDATE_TIME = 1;

    /**
     * 通知消息管理
     */
    private NotificationManagerCompat mNotificationManager;

    /**
     * 小睡间隔
     */
    private int mNapInterval;

    /**
     * 小睡次数
     */
    private int mNapTimes;

    /**
     * 是否点击按钮
     */
    private boolean mIsOnclick = false;

    /**
     * 小睡已执行次数
     */
    private int mNapTimesRan;

    /**
     * 声音管理
     */
    private AudioManager mAudioManager;

    /**
     * 当前音量
     */
    private int mCurrentVolume;


    /**
     * 显示当前时间Handler
     */
    private ShowTimeHandler mShowTimeHandler;

    private ViewGroup mWeatherInfoGroup;
    private ProgressBar mWeatherPbar;
    private TextView mWeatherTypeTv;
    private TextView mUmbrellaTv;
    private String mCurrentTimeDisplay = "";

    /**
     * 显示当前时间
     */
    static class ShowTimeHandler extends Handler {
        private WeakReference<AlarmClockOntimeActivity> mWeakReference;

        public ShowTimeHandler(AlarmClockOntimeActivity alarmClockOntimeFragment) {
            mWeakReference = new WeakReference<>(alarmClockOntimeFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AlarmClockOntimeActivity alarmClockOntimeFragment = mWeakReference.get();

            switch (msg.what) {
                case UPDATE_TIME:
                    alarmClockOntimeFragment.mTimeTv.setText(msg.obj.toString());
                    alarmClockOntimeFragment.mCurrentTimeDisplay =
                            alarmClockOntimeFragment.mTimeTv.getText().toString();
                    break;
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        ButterKnife.bind(this);

        // 启动的Activity个数加1
        WeacStatus.sActivityNumber++;

        // 画面出现在解锁屏幕上,显示,常亮
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAlarmClock = getIntent()
                .getParcelableExtra(WeacConstants.ALARM_CLOCK);
        if (mAlarmClock != null) {
            // 取得小睡间隔
            mNapInterval = mAlarmClock.getNapInterval();
            // 取得小睡次数
            mNapTimes = mAlarmClock.getNapTimes();
        }
        // XXX:修正小睡数
        // mNapTimes = 1000;
        // 小睡已执行次数
        mNapTimesRan = getIntent().getIntExtra(
                WeacConstants.NAP_RAN_TIMES, 0);
        // 播放铃声
        playRing();

        mNotificationManager = NotificationManagerCompat.from(this);
        if (mAlarmClock != null) {
            // 取消下拉列表通知消息
            mNotificationManager.cancel(mAlarmClock.getId());
        }
        mShowTimeHandler = new ShowTimeHandler(this);

        initview();
    }

    /**
     * 初始化布局
     */
    private void initview() {
        // 显示现在时间
        mTimeTv.setText(new SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(new Date()));

        mCurrentTimeDisplay = mTimeTv.getText().toString();
        // 启动更新时间线程
        new Thread(new TimeUpdateThread()).start();


        // 小睡开启状态
        if (mAlarmClock != null && mAlarmClock.isNap()) {
            // 当执行X次小睡后隐藏小睡按钮
            if (mNapTimesRan != mNapTimes) {
                // 设置小睡
//                napTv.setText(String.format(
//                        getString(R.string.touch_here_nap), mNapInterval));
                napTv.setOnClickListener(this);
            } else {
                napTv.setVisibility(View.GONE);
            }
        } else {
            napTv.setVisibility(View.GONE);
        }

        Log.i(LOG_TAG, "小睡次数：" + mNapTimes);


        mySlidingView.setSlidingTipListener(new MySlidingView.SlidingTipListener() {
            @Override
            public void onSlidFinish() {
                // 执行关闭操作
                finishActivity();
            }
        });

    }


    /**
     * 播放铃声
     */
    private void playRing() {
        mAudioManager = (AudioManager) getSystemService(
                Context.AUDIO_SERVICE);
        mCurrentVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        if (mAlarmClock != null) {
            // 设置铃声音量
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    mAlarmClock.getVolume(), AudioManager.ADJUST_SAME);

            // 默认铃声
            if (mAlarmClock.getRingUrl().equals(WeacConstants.DEFAULT_RING_URL)
                    || TextUtils.isEmpty(mAlarmClock.getRingUrl())) {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    // 播放
                    AudioPlayer.getInstance(this).playRaw(
                            R.raw.ring_weac_alarm_clock_default, true, true);
                } else {
                    AudioPlayer.getInstance(this).playRaw(
                            R.raw.ring_weac_alarm_clock_default, true, false);
                }

                // 无铃声
            } else if (mAlarmClock.getRingUrl().equals(WeacConstants.NO_RING_URL)) {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    AudioPlayer.getInstance(this).stop();
                    AudioPlayer.getInstance(this).vibrate();
                } else {
                    AudioPlayer.getInstance(this).stop();
                }
            } else {
                // 振动模式
                if (mAlarmClock.isVibrate()) {
                    AudioPlayer.getInstance(this).play(
                            mAlarmClock.getRingUrl(), true, true);
                } else {
                    AudioPlayer.getInstance(this).play(
                            mAlarmClock.getRingUrl(), true, false);
                }
            }
        } else {
            AudioPlayer.getInstance(this).playRaw(
                    R.raw.ring_weac_alarm_clock_default, true, true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 点击小睡
            case R.id.ontime_nap:
                // 执行小睡操作
                onClickNapButton();
                break;
        }

    }


    /**
     * 显示时间的线程类
     */
    private class TimeUpdateThread implements Runnable {
        /**
         * 闹钟响铃时间
         */
        private int startedTime = 0;

        /**
         * 3分钟
         */
        private static final int TIME = 60 * 3;

        @Override
        public void run() {
            // Activity没有结束
            while (mIsRun) {
                LogUtil.d(LOG_TAG, "TimeUpdateThread(已启动时间): " + startedTime);

                try {
                    // 响铃XX分钟并且当前Activity没有被销毁进入小睡
                    if (startedTime == TIME) {
                        // 小睡开启状态
                        if (mAlarmClock != null && mAlarmClock.isNap()) {
                            if (!isFinishing()) {
                                onClickNapButton();
                                return;
                            } else {
                                Log.w(LOG_TAG,
                                        "准备进行自动小睡处理时，闹钟已经为Finishing状态");
                                return;
                            }
                        } else {
                            // 执行关闭操作
                            finishActivity();
                        }
                    }
                    Thread.sleep(1000);
                    startedTime++;
                    // 界面显示的时间
                    CharSequence currentTime = new SimpleDateFormat("HH:mm",
                            Locale.getDefault()).format(System
                            .currentTimeMillis());
                    if (mCurrentTimeDisplay.equals(currentTime)) {
                        continue;
                    }

                    Message msg = mShowTimeHandler.obtainMessage(UPDATE_TIME,
                            currentTime);
                    // 发送消息
                    mShowTimeHandler.sendMessage(msg);
                } catch (InterruptedException | NullPointerException e) {
                    Log.e(LOG_TAG, "run方法出现错误：" + e.toString());
                }
            }

        }
    }

    /**
     * 当点击小睡按钮
     */
    private void onClickNapButton() {
        if (!(mNapTimesRan == mNapTimes)) {
            // 小睡
            nap();
        }
        // 执行关闭操作
        finishActivity();
    }

    /**
     * 小睡
     */
    @TargetApi(19)
    private void nap() {
        // 当小睡执行了X次
        if (mNapTimesRan == mNapTimes || mAlarmClock == null) {
            return;
        }
        // 小睡次数加1
        mNapTimesRan++;
        LogUtil.d(LOG_TAG, "已执行小睡次数：" + mNapTimesRan);

        // 设置小睡相关信息
        Intent intent = new Intent(this, AlarmClockBroadcast.class);
        intent.putExtra(WeacConstants.ALARM_CLOCK, mAlarmClock);
        intent.putExtra(WeacConstants.NAP_RAN_TIMES, mNapTimesRan);
        PendingIntent pi = PendingIntent.getBroadcast(this,
                -mAlarmClock.getId(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        // XXX
        // 下次响铃时间
        long nextTime = System.currentTimeMillis() + 1000 * 60 * mNapInterval;

        Log.i(LOG_TAG, "小睡间隔:" + mNapInterval + "分钟");

        // 当前版本为19（4.4）或以上使用精准闹钟
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
        }

        // 设置通知相关信息
        Intent it = new Intent(this,
                AlarmClockNapNotificationActivity.class);
        it.putExtra(WeacConstants.ALARM_CLOCK, mAlarmClock);
        // FLAG_UPDATE_CURRENT 点击通知有时不会跳转！！
        // FLAG_ONE_SHOT 清除列表只响应一个
        PendingIntent napCancel = PendingIntent.getActivity(this,
                mAlarmClock.getId(), it,
                PendingIntent.FLAG_CANCEL_CURRENT);
        // 下拉列表通知显示的时间
        CharSequence time = new SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(nextTime);

        // 通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 设置PendingIntent
        Notification notification = builder.setContentIntent(napCancel)
                // 当清除下拉列表触发
                .setDeleteIntent(napCancel)
                // 设置下拉列表标题
                .setContentTitle(
                        String.format(getString(R.string.xx_naping),
                                mAlarmClock.getTag()))
                // 设置下拉列表显示内容
                .setContentText(String.format(getString(R.string.nap_to), time))
                // 设置状态栏显示的信息
                .setTicker(
                        String.format(getString(R.string.nap_time),
                                mNapInterval))
                // 设置状态栏（小图标）
                .setSmallIcon(R.mipmap.ic_nap_notification)
                // 设置下拉列表（大图标）
                .setLargeIcon(
                        BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_launcher)).setAutoCancel(true)
                // 默认呼吸灯
                .setDefaults(NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.FLAG_SHOW_LIGHTS)
                .build();
/*        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;*/

        // 下拉列表显示小睡信息
        mNotificationManager.notify(mAlarmClock.getId(), notification);
    }

    /**
     * 执行结束当前Activity操作
     */
    private void finishActivity() {
        // 点击按钮标记
        mIsOnclick = true;

        finish();
        overridePendingTransition(0, 0);
    }


    @Override
    public void onDestroy() {
        LogUtil.d(LOG_TAG, toString() + "：onDestroy");
        super.onDestroy();
        // 停止运行更新时间的线程
        mIsRun = false;

        // 当没有点击按钮，则当前响铃被新闹钟任务杀死，开启小睡
        if (!mIsOnclick) {
            // 小睡
            nap();
        }

        // 当前只有一个Activity
        if (WeacStatus.sActivityNumber <= 1) {
            // 停止播放
            AudioPlayer.getInstance(this).stop();
        }

        // 启动的Activity个数减一
        WeacStatus.sActivityNumber--;

        // If null, all callbacks and messages will be removed.
        if (mShowTimeHandler != null) {
            mShowTimeHandler.removeCallbacksAndMessages(null);
        }

        // 复原手机媒体音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                mCurrentVolume, AudioManager.ADJUST_SAME);
    }

}
