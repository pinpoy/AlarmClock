package com.jesgoo.fast.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Desc     计时器
 * Created by xupeng on 2018/4/11.
 */

public class Timer extends DataSupport implements Parcelable {
    /**
     * 闹钟id
     */
    private int id;
    /**
     * 分钟
     */
    private int minute;
    /**
     * 秒
     */
    private int seconds;

    /**
     * 铃声名
     */
    private String ringName;
    /**
     * 铃声地址
     */
    private String ringUrl;
    /**
     * 音量
     */
    private int volume;
    /**
     * 标签
     */
    private String tag;

    public Timer() {
        super();
    }


    protected Timer(Parcel in) {
        id = in.readInt();
        minute = in.readInt();
        seconds = in.readInt();
        ringName = in.readString();
        ringUrl = in.readString();
        volume = in.readInt();
        tag = in.readString();
    }

    public static final Creator<Timer> CREATOR = new Creator<Timer>() {
        @Override
        public Timer createFromParcel(Parcel in) {
            return new Timer(in);
        }

        @Override
        public Timer[] newArray(int size) {
            return new Timer[size];
        }
    };

    public int getId() {
        return id;
    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public String getRingUrl() {
        return ringUrl;
    }

    public void setRingUrl(String ringUrl) {
        this.ringUrl = ringUrl;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(minute);
        out.writeInt(seconds);
        out.writeString(ringName);
        out.writeString(ringUrl);
        out.writeInt(volume);
        out.writeString(tag);

    }
}
