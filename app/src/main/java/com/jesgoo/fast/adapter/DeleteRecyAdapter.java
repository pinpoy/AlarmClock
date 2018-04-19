package com.jesgoo.fast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.bean.RingSelectItem;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.utils.AudioPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Desc
 * Created by xupeng on 2018/1/31.
 */

public class DeleteRecyAdapter extends RecyclerView.Adapter<DeleteRecyAdapter.ItemViewHolder> {

    private Context mContext;
    private List<AlarmClock> data;

    public DeleteRecyAdapter(Context mContext, List<AlarmClock> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delete_recy, parent, false);
        ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final AlarmClock alarmClock = data.get(position);


//        // 当闹钟为开启状态时
//        if (alarmClock.isOnOff()) {
//            // 设置字体颜色为白色
//            viewHolder.time.setTextColor(mWhite);
//            viewHolder.repeat.setTextColor(mWhite);
//            viewHolder.tag.setTextColor(mWhite);
//        } else {
//            // 设置字体颜色为淡灰色
//            viewHolder.time.setTextColor(mWhiteTrans);
//            viewHolder.repeat.setTextColor(mWhiteTrans);
//            viewHolder.tag.setTextColor(mWhiteTrans);
//
//        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView content;


        public ItemViewHolder(View view) {
            super(view);
//            content = (TextView) view.findViewById(R.id.item_content);

        }
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


}
