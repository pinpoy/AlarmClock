package com.jesgoo.fast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.Timer;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.utils.StringUtils;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc     计时器编辑页面
 * Created by xupeng on 2018/4/11.
 */

public class TimerEditActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.loopView_minute)
    LoopView loopViewMinute;
    @Bind(R.id.loopView_seconds)
    LoopView loopViewSeconds;
    @Bind(R.id.et_edit_timer_tag)
    EditText etEditTimerTag;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_edit);
        ButterKnife.bind(this);
        mTimer = new Timer();

        initLoopView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_left:
                drawAnimationFinish();
                break;
            case R.id.rl_title_right:   //保存计时器的编辑
                //默认tag为闹钟，保存tag备注
                if (!StringUtils.isStringEmpty(etEditTimerTag.getText().toString()))
                    mTimer.setTag(etEditTimerTag.getText().toString());
                else {
                    mTimer.setTag("计时器");   //默认
                }
                //将数据timer传递给上一个activity
                Intent data = new Intent();
                data.putExtra(WeacConstants.Timer, mTimer);
                data.setAction("polly.liu.Image.timer");//用隐式意图来启动广播
                sendBroadcast(data);
                drawAnimationFinish();
                break;
        }
    }


    /**
     * 初始化时间滚轮
     */
    private void initLoopView() {

        ArrayList<String> listMinute = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listMinute.add(String.format("%02d", i));
        }

        ArrayList<String> listSeconds = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listSeconds.add(String.format("%02d", i));
        }
        //设置原始数据
        loopViewMinute.setItems(listMinute);
        //设置初始位置
        loopViewMinute.setInitPosition(0);


        //设置原始数据
        loopViewSeconds.setItems(listSeconds);
        //设置初始位置
        loopViewSeconds.setInitPosition(0);


        //滚动监听
        loopViewMinute.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mTimer.setMinute(index);
            }
        });
        //滚动监听
        loopViewSeconds.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mTimer.setSeconds(index);
            }
        });
    }


}
