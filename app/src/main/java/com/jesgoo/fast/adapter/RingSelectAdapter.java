package com.jesgoo.fast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesgoo.fast.R;
import com.jesgoo.fast.bean.RingSelectItem;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.utils.AudioPlayer;

import java.util.List;
import java.util.Map;


/**
 * Desc
 * Created by xupeng on 2018/1/31.
 */

public class RingSelectAdapter extends RecyclerView.Adapter<RingSelectAdapter.ItemViewHolder> {

    private Context mContext;
    private List<Map<String, String>> data;
    private int mCurrentPositin = -1;    //记录当前位置

    public RingSelectAdapter(Context mContext, List<Map<String, String>> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ring_select, parent, false);
        ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.ringName.setText(data.get(position).get(WeacConstants.RING_NAME));
        if (mCurrentPositin == position) {
            holder.ringMark.setVisibility(View.VISIBLE);
        } else {
            holder.ringMark.setVisibility(View.GONE);
        }


        //条目点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = data.get(position);
                // 取得铃声名
                String ringName = map.get(WeacConstants.RING_NAME);
                // 取得播放地址
                String ringUrl = map.get(WeacConstants.RING_URL);
//                // 更新当前铃声选中的位置
//                mSystemRingAdapter.updateSelection(ringName);
//                // 更新适配器刷新铃声列表显示
//                mSystemRingAdapter.notifyDataSetChanged();
                // 设置最后一次选中的铃声选择界面位置为系统铃声界面

                //记录选中的铃声信息
                RingSelectItem.getInstance().setName(ringName);
                RingSelectItem.getInstance()
                        .setUrl(map.get(WeacConstants.RING_URL));

                RingSelectItem.getInstance().setRingPager(0);

                // 播放音频文件
                switch (ringUrl) {
                    case WeacConstants.DEFAULT_RING_URL:
                        // 当为默认铃声时
                        AudioPlayer.getInstance(mContext).playRaw(
                                R.raw.ring_weac_alarm_clock_default, false, false);
                        // 无铃声
                        break;
                    case WeacConstants.NO_RING_URL:
                        AudioPlayer.getInstance(mContext).stop();
                        break;
                    default:
                        AudioPlayer.getInstance(mContext).play(ringUrl, false, false);
                        break;
                }

                mCurrentPositin = position;
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView ringName;
        private ImageView ringMark;


        public ItemViewHolder(View view) {
            super(view);
            ringName = (TextView) view.findViewById(R.id.tv_voice_name);
            ringMark = (ImageView) view.findViewById(R.id.iv_ring_mark);

        }
    }


}
