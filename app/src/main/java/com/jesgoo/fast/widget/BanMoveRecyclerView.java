package com.jesgoo.fast.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Desc 禁止上下滑动的RecyclerView
 * Created by xupeng on 2018/4/17.
 */

public class BanMoveRecyclerView extends RecyclerView {


    public BanMoveRecyclerView(Context context) {
        super(context);
    }

    public BanMoveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BanMoveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startY = (int) e.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int currentY = (int) e.getY();
//                // 总共滑动的距离
//                int deltaY = Math.abs(startY - currentY);
//                // 向左滑动删除，请求父控件不要处理滑动事件，交给本控件开处理
//                if (deltaY > 0) {
//                    return false;
//                }
//                break;
//
//        }
//        return super.onTouchEvent(e);
//    }

//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        return true;
//    }
}
