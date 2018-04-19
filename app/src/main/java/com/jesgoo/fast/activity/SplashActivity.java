package com.jesgoo.fast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jesgoo.fast.MainActivity;
import com.jesgoo.fast.R;
import com.jesgoo.fast.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Desc
 * Created by xupeng on 2018/3/27.
 */

public class SplashActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView view = new ImageView(this);
        view.setBackgroundResource(R.mipmap.loading);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, com.jesgoo.fast.activity.MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 1500);
    }
}
