package com.jesgoo.fast.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.activity.TimerDetailActivity;
import com.jesgoo.fast.adapter.AlarmClockAdapter;
import com.jesgoo.fast.adapter.TimerAdapter;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.bean.Timer;
import com.jesgoo.fast.bean.event.AlarmClockDeleteEvent;
import com.jesgoo.fast.bean.event.AlarmClockUpdateEvent;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.db.AlarmClockOperate;
import com.jesgoo.fast.db.TimerOperate;
import com.jesgoo.fast.utils.MyUtil;
import com.jesgoo.fast.utils.OttoAppConfig;
import com.jesgoo.fast.widget.BanMoveRecyclerView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc     时钟中心
 * Created by xupeng on 2018/4/9.
 */

public class ClockCenterFragment extends Fragment implements TimerAdapter.OnItemClickListener {
    //    @Bind(R.id.id_item_remove_recyclerview)
//    RecyclerView recyclerView;
    @Bind(R.id.list_view)
    BanMoveRecyclerView mRecyclerView;

    /**
     * 新建闹钟的requestCode
     */
    private static final int REQUEST_ALARM_CLOCK_NEW = 1;
    @Bind(R.id.tv_clock_center_edit)
    TextView mEditAction;
    @Bind(R.id.tv_clock_center_accept)
    TextView mAcceptAction;
    @Bind(R.id.list_view_timer)
    BanMoveRecyclerView TimerRecylerView;

    /**
     * 保存闹钟信息的list
     */
    private List<AlarmClock> mAlarmClockList;

    /**
     * 保存计时器信息的list
     */
    private List<Timer> mTimerkList;

    /**
     * 保存闹钟信息的adapter
     */
    private AlarmClockAdapter mAdapter;
    private ClockCenterReceiver receiver;
    private TimerAdapter timerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        OttoAppConfig.getInstance().register(this);
        //注册广播
        //新添代码，在代码中注册广播接收程序
        receiver = new ClockCenterReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("polly.liu.Image");
        filter.addAction("polly.liu.Image.timer");
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 计时器的条目点击事件
     *
     * @param timer
     * @param position
     */
    @Override
    public void onItemClick(Timer timer, int position) {
        Intent intent = new Intent(getActivity(), TimerDetailActivity.class);
        intent.putExtra(WeacConstants.Timer_detail, timer);
        startActivity(intent);

    }

    //内部类，实现BroadcastReceiver
    public class ClockCenterReceiver extends BroadcastReceiver {
        //必须要重载的方法，用来监听是否有广播发送
        @Override
        public void onReceive(Context context, Intent intent) {
            //返回的得到的闹钟数据
            AlarmClock ac = intent
                    .getParcelableExtra(WeacConstants.ALARM_CLOCK);
            Timer mTimer = intent
                    .getParcelableExtra(WeacConstants.Timer);
            if (null != ac) {
                // 插入新闹钟数据
                AlarmClockOperate.getInstance().saveAlarmClock(ac);
                addList(ac);
            }
            if (null != mTimer) {
                TimerOperate.getInstance().saveAlarmClock(mTimer);
                addTimerList(mTimer);
            }


        }
    }


    /**
     * 当有OttoAppConfig.getInstance().post(new AlarmClockUpdateEvent());
     * post的对象与入参对象类型相同时，会走该回调
     *
     * @param event
     */
    @Subscribe
    public void onAlarmClockUpdate(AlarmClockUpdateEvent event) {
        updateList();
    }

    @Subscribe
    public void OnAlarmClockDelete(AlarmClockDeleteEvent event) {
        if (null != event.getAlarmClock())
            deleteList(event);
        if (null != event.getAlarmClockTimer())
            deleteTimerList(event); //删除计时器


    }

    private void deleteTimerList(AlarmClockDeleteEvent event) {
        mTimerkList.clear();

        List<Timer> list = TimerOperate.getInstance().loadTimer();
        for (Timer timer : list) {
            mTimerkList.add(timer);
        }
        // 列表为空时不显示删除，完成按钮
        if (mTimerkList.size() == 0) {
            mAcceptAction.setVisibility(View.GONE);
            mEditAction.setVisibility(View.VISIBLE);
            timerAdapter.displayDeleteButton(false);
        }

        checkTimerIsEmpty(list);

        timerAdapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock_center, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        mAlarmClockList = new ArrayList<>();
        mAdapter = new AlarmClockAdapter(getActivity(), mAlarmClockList);
        mRecyclerView.setHasFixedSize(true);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        updateList();

        mTimerkList = new ArrayList<>();
        timerAdapter = new TimerAdapter(getActivity(), mTimerkList);
        timerAdapter.setOnItemClickListener(this);
        TimerRecylerView.setHasFixedSize(true);
        //设置布局管理器
        TimerRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TimerRecylerView.setAdapter(timerAdapter);
//        TimerRecylerView.setNestedScrollingEnabled(false);//禁止滑动
        updateTimerList();
    }


    public static Fragment newInstance() {
        ClockCenterFragment fragment = new ClockCenterFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(BUNDLE_TYPE, type);
        // Fragment传递数据方式
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.tv_clock_center_edit, R.id.tv_clock_center_accept})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_clock_center_edit:
                // 当列表内容为空时禁止响应编辑事件
                if (mAlarmClockList.size() == 0) {
                    return;
                }
                displayDeleteAccept();
                break;
            case R.id.tv_clock_center_accept:   //完成
                // 隐藏删除，完成按钮,显示修改按钮
                hideDeleteAccept();
                break;
        }
    }


    /**
     * 更新并刷新所有闹钟的开启状态
     */
    private void updateList() {
        mAlarmClockList.clear();

        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);

            // 当闹钟为开时刷新开启闹钟
            if (alarmClock.isOnOff()) {
                MyUtil.startAlarmClock(getActivity(), alarmClock);
            }
        }

        checkIsEmpty(list);

        mAdapter.notifyDataSetChanged();
    }

    private void deleteList(AlarmClockDeleteEvent event) {
        mAlarmClockList.clear();

        int position = event.getPosition();
        List<AlarmClock> list = AlarmClockOperate.getInstance().loadAlarmClocks();
        for (AlarmClock alarmClock : list) {
            mAlarmClockList.add(alarmClock);
        }
        // 列表为空时不显示删除，完成按钮
        if (mAlarmClockList.size() == 0) {
            mAcceptAction.setVisibility(View.GONE);
            mEditAction.setVisibility(View.VISIBLE);
            mAdapter.displayDeleteButton(false);
        }

        checkIsEmpty(list);

        mAdapter.notifyDataSetChanged();
//        mAdapter.notifyItemRemoved(position);
//        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
    }

    private void checkIsEmpty(List<AlarmClock> list) {
        if (list.size() != 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }


    /**
     * 添加新的闹钟时，开启指定闹钟的服务
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
                    MyUtil.startAlarmClock(getActivity(), alarmClock);
                }
            }
            count++;
        }

        checkIsEmpty(list);

//        mAdapter.notifyItemInserted(position);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(position);
    }

    /**
     * 添加计时器
     *
     * @param mTimer
     */
    private void addTimerList(Timer mTimer) {
        mTimerkList.clear();
        List<Timer> list = TimerOperate.getInstance().loadTimer();
        mTimerkList.addAll(list);

        checkTimerIsEmpty(list);

        timerAdapter.notifyDataSetChanged();
    }


    private void checkTimerIsEmpty(List<Timer> list) {
        if (list.size() != 0) {
            TimerRecylerView.setVisibility(View.VISIBLE);
        } else {
            TimerRecylerView.setVisibility(View.GONE);
        }
    }

    private void updateTimerList() {
        mTimerkList.clear();
        mTimerkList.addAll(TimerOperate.getInstance().loadTimer());
    }


    /**
     * 显示删除，完成按钮，隐藏修改按钮
     */
    private void displayDeleteAccept() {
        mAdapter.setIsCanClick(false);
        mAdapter.displayDeleteButton(true);
        mAdapter.notifyDataSetChanged();

        timerAdapter.setIsCanClick(false);
        timerAdapter.displayDeleteButton(true);
        timerAdapter.notifyDataSetChanged();

        mEditAction.setVisibility(View.GONE);
        mAcceptAction.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏删除，完成按钮,显示修改按钮
     */
    private void hideDeleteAccept() {
        mAdapter.setIsCanClick(true);
        mAdapter.displayDeleteButton(false);
        mAdapter.notifyDataSetChanged();

        timerAdapter.setIsCanClick(true);
        timerAdapter.displayDeleteButton(false);
        timerAdapter.notifyDataSetChanged();

        mAcceptAction.setVisibility(View.GONE);
        mEditAction.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoAppConfig.getInstance().unregister(this);
    }


}
