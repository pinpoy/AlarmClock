package com.jesgoo.fast.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.db.AlarmClockOperate;
import com.jesgoo.fast.utils.MyUtil;
import com.jesgoo.fast.utils.StringUtils;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc
 * Created by xupeng on 2018/4/3.
 */

public class AlarmClockEditActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.time_picker)
    TimePicker timePicker;
    /**
     * 铃声的名称
     */
    @Bind(R.id.tv_edit_voice)
    TextView tvEditVoice;

    /**
     * 铃声选择按钮的requestCode
     */
    private static final int REQUEST_RING_SELECT = 1;

    /**
     * 小睡按钮的requestCode
     */
    private static final int REQUEST_NAP_EDIT = 2;
    @Bind(R.id.rl_edit_voice)
    RelativeLayout rlEditVoice;
    @Bind(R.id.rl_edit_time)
    RelativeLayout rlEditTime;
    @Bind(R.id.et_edit_tag)
    EditText etEditTag;
    @Bind(R.id.loopView_hour)
    LoopView loopViewHour;
    @Bind(R.id.loopView_minute)
    LoopView loopViewMinute;


    /**
     * 闹钟实例
     */
    private AlarmClock mAlarmClock;


    private TimePickerView pvTime;

    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock_edit);
        ButterKnife.bind(this);
        mAlarmClock = new AlarmClock();
        // 闹钟默认开启
        mAlarmClock.setOnOff(true);
        initRing();             //设置铃声
        initToggleButton();     //设置振动、小睡


        // 初始化重复
        initRepeat();
        // 初始化标签
        initTag();
//        initTimeSelect();
        initListener();

        initLoopView();


    }

    /**
     * 初始化时间滚轮
     */
    private void initLoopView() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        //时间初始化
        mAlarmClock.setHour(c.get(Calendar.HOUR));
        mAlarmClock.setMinute(c.get(Calendar.MINUTE));


        ArrayList<String> listHour = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            listHour.add(String.format("%02d", i));
        }

        ArrayList<String> listMinute = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listMinute.add(String.format("%02d", i));
        }
        //设置原始数据
        loopViewHour.setItems(listHour);
        //设置初始位置
        loopViewHour.setInitPosition(c.get(Calendar.HOUR_OF_DAY));


        //设置原始数据
        loopViewMinute.setItems(listMinute);
        //设置初始位置
        loopViewMinute.setInitPosition(c.get(Calendar.MINUTE));


        //滚动监听
        loopViewHour.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mAlarmClock.setHour(index);
            }
        });
        //滚动监听
        loopViewMinute.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mAlarmClock.setMinute(index);
            }
        });
    }

    private void initTag() {
        mAlarmClock.setTag("闹钟");
    }

    private void initRepeat() {
        mAlarmClock.setRepeat("只响一次");
    }

    private void initListener() {
        rlEditVoice.setOnClickListener(this);
        rlEditTime.setOnClickListener(this);

    }


    /**
     * 设置时间选择
     */
    private void initTimeSelect() {
        timePicker.setIs24HourView(true);

        SharedPreferences share = getSharedPreferences(
                WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
        int currentHour = share.getInt(WeacConstants.DEFAULT_ALARM_HOUR, timePicker.getCurrentHour());
        int currentMinute = share.getInt(WeacConstants.DEFAULT_ALARM_MINUTE, timePicker.getCurrentMinute());

        timePicker.setCurrentHour(currentHour);
        timePicker.setCurrentMinute(currentMinute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // 保存闹钟实例的小时
                mAlarmClock.setHour(hourOfDay);
                // 保存闹钟实例的分钟
                mAlarmClock.setMinute(minute);
                // 计算倒计时显示
//                displayCountDown();
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_left:
                drawAnimationFinish();
                break;
            case R.id.rl_title_right:   //保存并开启闹钟服务


//                AlarmClockOperate.getInstance().saveAlarmClock(mAlarmClock);
//                addList(mAlarmClock);
//                Intent intent = new Intent();
//                intent.setAction("polly.liu.Image");//用隐式意图来启动广播
//                intent.putExtra("msg", mAlarmClock);
//                sendBroadcast(intent);


                //默认tag为闹钟，保存tag备注
                if (!StringUtils.isStringEmpty(etEditTag.getText().toString()))
                    mAlarmClock.setTag(etEditTag.getText().toString());

                //将数据mAlarmClock传递给上一个activity
                Intent data = new Intent();
                data.putExtra(WeacConstants.ALARM_CLOCK, mAlarmClock);
                setResult(Activity.RESULT_OK, data);

                data.setAction("polly.liu.Image");//用隐式意图来启动广播
                sendBroadcast(data);

                drawAnimationFinish();


                break;
            case R.id.rl_edit_voice:    //声音编辑
                Intent intent = new Intent(this, RingSelectActivity.class);
                startActivityForResult(intent, REQUEST_RING_SELECT);
                break;
            case R.id.rl_edit_time:    //重复：编辑时间

                break;


        }

    }

    /**
     * 设置铃声
     */
    private void initRing() {
        // 取得铃声选择配置信息
        SharedPreferences share = getSharedPreferences(
                WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
        String ringName = share.getString(WeacConstants.RING_NAME,
                getString(R.string.default_ring));
        String ringUrl = share.getString(WeacConstants.RING_URL,
                WeacConstants.DEFAULT_RING_URL);

        // 初始化闹钟实例的铃声名
        mAlarmClock.setRingName(ringName);
        // 初始化闹钟实例的铃声播放地址
        mAlarmClock.setRingUrl(ringUrl);

    }

    /**
     * 设置振动、小睡
     */
    private void initToggleButton() {
        // 初始化闹钟实例的振动，默认振动
        mAlarmClock.setVibrate(false);

        // 初始化闹钟实例的小睡信息
        // 默认小睡
        mAlarmClock.setNap(true);
        // 小睡间隔10分钟
        mAlarmClock.setNapInterval(10);
        // 小睡3次
        mAlarmClock.setNapTimes(3);
    }

    /**
     * 添加闹钟实例并开启闹钟的服务
     *
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            // 铃声选择界面返回
            case REQUEST_RING_SELECT:
                // 铃声名
                String name = data.getStringExtra(WeacConstants.RING_NAME);
                // 铃声地址
                String url = data.getStringExtra(WeacConstants.RING_URL);
                // 铃声界面
                int ringPager = data.getIntExtra(WeacConstants.RING_PAGER, 0);

                tvEditVoice.setText(name);

                mAlarmClock.setRingName(name);
                mAlarmClock.setRingUrl(url);
                mAlarmClock.setRingPager(ringPager);
                break;
//            // 小睡编辑界面返回
//            case REQUEST_NAP_EDIT:
//                // 小睡间隔
//                int napInterval = data.getIntExtra(WeacConstants.NAP_INTERVAL, 10);
//                // 小睡次数
//                int napTimes = data.getIntExtra(WeacConstants.NAP_TIMES, 3);
//                mAlarmClock.setNapInterval(napInterval);
//                mAlarmClock.setNapTimes(napTimes);
//                break;
        }
    }


    /**
     * 使用pickerview唤醒时间选择
     */
    private void initTimeSelect2() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(AlarmClockEditActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .isDialog(false)//是否显示为对话框样式
                .build()
        ;
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


}
