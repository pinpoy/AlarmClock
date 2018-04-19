package com.jesgoo.fast.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jesgoo.fast.R;


/**
 * @author: 徐鹏android
 * @Description: 添加页面
 * @time: create at 2018/4/8 16:05
 */
public class HomeAddWebPop {
    private Context mContext;
    private static HomeAddWebPop instance;
    private PopupWindow mPopupWindow;
    private TextView tvAddClock;
    private TextView tvAddTimer;
    private TextView tvAddTimerSleep;
    private TextView tvAddCutdown;
    private ImageView ivCancle;

    private HomeAddWebPop(Context context) {
        this.mContext = context;

    }

    public static HomeAddWebPop getInstance(Context context) {
        if (null == instance) {
            instance = new HomeAddWebPop(context);
        }

        return instance;
    }


    private void initPopAtLocation(Context context) {
        View contentView = getContentView(context);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mPopupWindow.setAnimationStyle(R.style.pop_anim_style_from_center);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // measure the view's width and height
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            contentView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        }
//
//        mPopupWindow.setOnDismissListener(() -> {
//            dismissPopupView();
//        });
    }

    private View getContentView(Context context) {
        View contentView = View.inflate(context, R.layout.pop_home_add_web, null);
        tvAddClock = (TextView) contentView.findViewById(R.id.tv_add_clock);
        tvAddTimer = (TextView) contentView.findViewById(R.id.tv_add_timer);
        tvAddTimerSleep = (TextView) contentView.findViewById(R.id.tv_add_timer_sleep);
        tvAddCutdown = (TextView) contentView.findViewById(R.id.tv_add_cutdown);
        ivCancle = (ImageView) contentView.findViewById(R.id.iv_home_add_cancel);

        ivCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupView();
            }
        });
        //闹钟
        tvAddClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupView();
                listener.onAddClock();
            }
        });
        //计时器
        tvAddTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupView();
                listener.onAddTimer();
            }
        });
        //睡眠计时器
        tvAddTimerSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupView();
                listener.onAddTimerSleep();
            }
        });
        //倒计时
        tvAddCutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupView();
                listener.onAddCutdown();
            }
        });


        return contentView;
    }


    /**
     * 关闭弹出框
     */
    public void dismissPopupView() {
        //resetViewAndData();

        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 指定View为依附类
     *
     * @param parentView down at parentView
     */
    public void showPopupView(View parentView) {
        initPopAtLocation(mContext);

        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }




    private HomeAddWebListener listener;

    public void setHomeAddWebListener(HomeAddWebListener listener) {
        this.listener = listener;
    }


    /**
     * PopupWindow选择Item的监听器
     */
    public interface HomeAddWebListener {

        /**
         * 添加闹钟
         */
        void onAddClock();

        /**
         * 继续计时器
         */
        void onAddTimer();

        /**
         * 添加睡眠计时器
         */
        void onAddTimerSleep();


        /**
         * 添加倒计时
         */
        void onAddCutdown();
    }


}
