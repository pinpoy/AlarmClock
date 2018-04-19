package com.jesgoo.fast.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jesgoo.fast.R;
import com.jesgoo.fast.manager.ActivityStack;
import com.jesgoo.fast.widget.SystemStatusManager;


/**
 * Created by xupeng on 2017/12/9.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无标题栏

        transparentStatusBar();


        ActivityStack.getInstanse().addActivity(this);
    }

    /**
     * 检测系统版本并使状态栏全透明（content可以显示在状态栏之下）
     */
    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//状态栏透明
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 透明状态栏并可自定义设置状态栏的颜色
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemStatusManager tintManager = new SystemStatusManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // 设置状态栏的颜色
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstanse().removeActivity(this);
    }


    protected void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);       //友盟统计
    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


    /**
     * 结束新建闹钟界面时开启渐变缩小效果动画（关闭的动画）
     */
    protected void drawAnimationFinish() {
        finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    protected View getRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
