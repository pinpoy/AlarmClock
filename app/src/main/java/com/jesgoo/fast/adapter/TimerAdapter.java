package com.jesgoo.fast.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jesgoo.fast.R;
import com.jesgoo.fast.activity.TimerEditActivity;
import com.jesgoo.fast.activity.TimerOnTimeActivity;
import com.jesgoo.fast.bean.Timer;
import com.jesgoo.fast.bean.event.AlarmClockDeleteEvent;
import com.jesgoo.fast.db.TimerOperate;
import com.jesgoo.fast.service.CountDownService;
import com.jesgoo.fast.utils.MyUtil;
import com.jesgoo.fast.utils.OttoAppConfig;

import org.litepal.util.LogUtil;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 保存计时器信息的adapter
 *
 * @author 徐鹏
 * @version 1.0 2018/04
 */
public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.MyViewHolder> {

    /**
     * Log tag ：TimeFragment
     */
    private static final String LOG_TAG = "TimerAdapter";

    private final Context mContext;
    private List<Timer> mList;
    /**
     * 是否显示删除按钮
     */
    private boolean mIsDisplayDeleteBtn = false;
    /**
     * 目前的位置
     */
    private TextView currentTvTime;
    private Timer currentTimer;
    private ToggleButton lastToggleBtn;
    private int LastPosition;

    /**
     * 保存闹钟信息的adapter
     *
     * @param context Activity上下文
     * @param list    闹钟信息List
     */
    @SuppressWarnings("deprecation")
    public TimerAdapter(Context context, List<Timer> list) {
        mContext = context;
        mList = list;
        //初始化
        // GMT（格林尼治标准时间）一般指世界时.中国时间（GST）与之相差-8小时
        TimeZone tz = TimeZone.getTimeZone("GMT");
        mTimeRemain = Calendar.getInstance();
        mTimeRemain.clear();
        mTimeRemain.setTimeZone(tz);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.lv_alarm_clock_timer, parent, false));
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        //添加头布局
        if (position == 0) {
            viewHolder.rlHeaderView.setVisibility(View.VISIBLE);
            viewHolder.timerAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, TimerEditActivity.class));
                }
            });
        } else {
            viewHolder.rlHeaderView.setVisibility(View.GONE);
        }
        final Timer timer = mList.get(position);
        // 取得格式化后的时间
        String time = MyUtil.formatTime(timer.getMinute(),
                timer.getSeconds());
        // 设定闹钟时间的显示
        viewHolder.time.setText(time);
        // 设定标签的显示
        viewHolder.tag.setText(timer.getTag());

        // 显示删除按钮
        if (mIsDisplayDeleteBtn) {
            viewHolder.deleteBtn.setVisibility(View.VISIBLE);
            viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TimerOperate.getInstance().deleteAlarmClock(timer);
                    OttoAppConfig.getInstance().post(new AlarmClockDeleteEvent(viewHolder.getAdapterPosition(), timer));


                }
            });
        } else {
            viewHolder.deleteBtn.setVisibility(View.GONE);
        }

        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCanClick) {
                        mOnItemClickListener.onItemClick(timer, viewHolder.getLayoutPosition());
                    }
                }
            });


        }
        //开关
        viewHolder.toggleBtn
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // ToggleButton默认为false
                        if (timer.getMinute() == 0 && timer.getSeconds() == 0)
                            return;


                        if (null != lastToggleBtn && LastPosition != position)
                            lastToggleBtn.setChecked(false);

                        if (isChecked) {
                            currentTvTime = viewHolder.time;    //对象时间显示的textView
                            currentTimer = timer;               //当前的timer实例
                            setStartTime(timer.getMinute(), timer.getSeconds());//为剩余时间赋值
                            //开启倒计时
                            startCountDownService();

                        } else {
                            //停止倒计时
                            stopCountDown();
                            setCurrentAndUpdateTime();

                        }

                        lastToggleBtn = viewHolder.toggleBtn;
                        LastPosition = position;
                    }


                });

    }

    /**
     * 保存显示时间并存入数据库
     */
    private void setCurrentAndUpdateTime() {
        currentTimer.setMinute(mTimeRemain.get(Calendar.MINUTE));
        currentTimer.setSeconds(mTimeRemain.get(Calendar.SECOND));
        TimerOperate.getInstance().updateTimer(currentTimer.getId(),
                currentTimer.getMinute(), currentTimer.getSeconds());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 保存控件实例
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        // 时间
        TextView time;

        // 标签
        TextView tag;

        // 删除
        ImageView deleteBtn;
        //头布局
        RelativeLayout rlHeaderView;

        // 添加
        ImageView timerAdd;

        // 开关
        ToggleButton toggleBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            tag = (TextView) itemView.findViewById(R.id.tv_tag);
            deleteBtn = (ImageView) itemView.findViewById(R.id.alarm_list_delete_btn);
            rlHeaderView = (RelativeLayout) itemView.findViewById(R.id.rl_header_view);
            timerAdd = (ImageView) itemView.findViewById(R.id.iv_timer_add);
            toggleBtn = (ToggleButton) itemView.findViewById(R.id.toggle_btn);
        }
    }

    /**
     * 更新删除闹钟按钮状态
     *
     * @param isDisplayDeleteBtn 是否显示删除按钮
     */
    public void displayDeleteButton(boolean isDisplayDeleteBtn) {
        mIsDisplayDeleteBtn = isDisplayDeleteBtn;
    }


    public interface OnItemClickListener {
        void onItemClick(Timer timer, int position);


    }


    private boolean isCanClick = true;

    public void setIsCanClick(boolean isCanClick) {
        this.isCanClick = isCanClick;
    }


    /**
     * 开启倒计时的服务
     */
    private void startCountDownService() {
        Intent intent = new Intent(mContext, CountDownService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

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
                        mContext.startActivity(new Intent(mContext, TimerOnTimeActivity.class));
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


    // Handler异步方式下载图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    setDisplayNumber();
                    currentTvTime.setText(mDisplayRemainTime);
                    break;
            }
        }
    };

    /**
     * 显示的剩余时间
     */
    private String mDisplayRemainTime;

    /**
     * 倒计时剩余时间
     */
    private Calendar mTimeRemain;

    /**
     * 设置显示的剩余时间
     */
    private void setDisplayNumber() {
        mDisplayRemainTime = MyUtil.formatTime(mTimeRemain.get(Calendar.MINUTE),
                mTimeRemain.get(Calendar.SECOND));
    }

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
        mContext.unbindService(mConnection);
    }
}
