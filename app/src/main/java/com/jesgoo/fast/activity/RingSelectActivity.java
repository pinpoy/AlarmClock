package com.jesgoo.fast.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jesgoo.fast.R;
import com.jesgoo.fast.adapter.RingSelectAdapter;
import com.jesgoo.fast.base.BaseActivity;
import com.jesgoo.fast.bean.RingSelectItem;
import com.jesgoo.fast.common.WeacConstants;
import com.jesgoo.fast.utils.AudioPlayer;
import com.jesgoo.fast.utils.MyUtil;
import com.jesgoo.fast.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc 铃声选择页面
 * Created by xupeng on 2018/4/4.
 */

public class RingSelectActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>
        , View.OnClickListener {

    /**
     * loader Id
     */
    private static final int LOADER_ID = 1;
    @Bind(R.id.recycler_voice)
    RecyclerView recyclerVoice;
    /**
     * 铃声选择位置
     */
    private int mPosition = 0;
    /**
     * 铃声名
     */
    public static String sRingName;

    /**
     * 铃声请求类型
     */
    public static int sRingRequestType;
    private RingSelectAdapter ringSelectAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_select);
        ButterKnife.bind(this);

        // 管理cursor
        LoaderManager loaderManager = getSupportLoaderManager();
        // 注册Loader
        loaderManager.initLoader(LOADER_ID, null, this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_left:    //左侧标题
                drawAnimationFinish();
                break;
            case R.id.rl_title_right:   //右侧标题----保存
                // 取得选中的铃声信息
                String ringName = RingSelectItem.getInstance().getName();
                String ringUrl = RingSelectItem.getInstance().getUrl();
                int ringPager = RingSelectItem.getInstance().getRingPager();
                if (StringUtils.isStringEmpty(ringName)) {
                    toast("请先选择铃声，再点击保存！");
                    return;
                }
                // 保存选中的铃声信息
                SharedPreferences share = getSharedPreferences(
                        WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
                SharedPreferences.Editor edit = share.edit();

                // 来自闹钟请求
                if (sRingRequestType == 0) {
                    edit.putString(WeacConstants.RING_NAME, ringName);
                    edit.putString(WeacConstants.RING_URL, ringUrl);
                    edit.putInt(WeacConstants.RING_PAGER, ringPager);
                    // 计时器请求
                } else {
                    edit.putString(WeacConstants.RING_NAME_TIMER, ringName);
                    edit.putString(WeacConstants.RING_URL_TIMER, ringUrl);
                    edit.putInt(WeacConstants.RING_PAGER_TIMER, ringPager);
                }
                edit.apply();

                // 传递选中的铃声信息
                Intent i = new Intent();
                i.putExtra(WeacConstants.RING_NAME, ringName);
                i.putExtra(WeacConstants.RING_URL, ringUrl);
                i.putExtra(WeacConstants.RING_PAGER, ringPager);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;

        }

    }

    /**
     * 目的：获取手机的系统铃声
     * ----------------------------------------------------------------
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // 查询内部存储音频文件
        return new CursorLoader(this,
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA}, null, null,
                MediaStore.Audio.Media.DISPLAY_NAME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                // 当为编辑闹钟状态时，铃声名为闹钟单例铃声名
                String ringName1;
                if (sRingName != null) {
                    ringName1 = sRingName;
                } else {
                    SharedPreferences share = getSharedPreferences(
                            WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
                    // 当为新建闹钟状态时，铃声名为最近一次选择保存的铃声名,没有的话为默认铃声
                    ringName1 = share.getString(WeacConstants.RING_NAME,
                            getString(R.string.default_ring));
                }

                // 过滤重复音频文件的Set
                HashSet<String> set = new HashSet<>();

                //  保存铃声信息的List
                List<Map<String, String>> list = new ArrayList<>();
                // 添加默认铃声
                Map<String, String> defaultRing = new HashMap<>();
                defaultRing.put(WeacConstants.RING_NAME, getString(R.string.default_ring));
                defaultRing.put(WeacConstants.RING_URL, WeacConstants.DEFAULT_RING_URL);
                list.add(defaultRing);
                set.add(getString(R.string.default_ring));

                // 保存的铃声名为默认铃声，设置该列表的显示位置
                if (getString(R.string.default_ring).equals(ringName1)) {
                    mPosition = 0;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                // 添加无铃声
                Map<String, String> noRing = new HashMap<>();
                noRing.put(WeacConstants.RING_NAME, getString(R.string.no_ring));
                noRing.put(WeacConstants.RING_URL, WeacConstants.NO_RING_URL);
                list.add(noRing);
                set.add(getString(R.string.no_ring));

                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                if (getString(R.string.no_ring).equals(ringName1)) {
                    mPosition = list.size() - 1;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        // 音频文件名
                        String ringName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        if (ringName != null) {
                            // 当过滤集合里不存在此音频文件
                            if (!set.contains(ringName)) {
                                // 添加音频文件到列表过滤同名文件
                                set.add(ringName);
                                // 去掉音频文件的扩展名
                                ringName = MyUtil.removeEx(ringName);
                                // 取得音频文件的地址
                                String ringUrl = cursor.getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DATA));
                                Map<String, String> map = new HashMap<>();
                                map.put(WeacConstants.RING_NAME, ringName);
                                map.put(WeacConstants.RING_URL, ringUrl);
                                list.add(map);
                                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                                if (ringName.equals(ringName1)) {
                                    mPosition = list.size() - 1;
                                    RingSelectItem.getInstance().setRingPager(0);
                                }
                            }
                        }
                    }
                }


                //线性布局管理器
                linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                ringSelectAdapter = new RingSelectAdapter(this, list);
                recyclerVoice.setLayoutManager(linearLayoutManager);
                recyclerVoice.setAdapter(ringSelectAdapter);
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * ----------------------------------------------------------------
     */


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 当没有播放录音停止播放音时
        if (!AudioPlayer.sIsRecordStopMusic) {
            // 停止播放
            AudioPlayer.getInstance(this).stop();
        }
    }
}
