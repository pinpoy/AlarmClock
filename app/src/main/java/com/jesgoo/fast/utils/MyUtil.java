/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.jesgoo.fast.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.jesgoo.fast.bean.AlarmClock;
import com.jesgoo.fast.brocast.AlarmClockBroadcast;
import com.jesgoo.fast.common.WeacConstants;

import java.util.Calendar;


/**
 * 工具类
 *
 * @author 咖枯
 * @version 1.0 2015/07
 */
public class MyUtil {

    /**
     * Log tag ：MyUtil
     */
    private static final String LOG_TAG = "MyUtil";



    /**
     * 开启闹钟
     *
     * @param context    context
     * @param alarmClock 闹钟实例
     */
    @TargetApi(19)
    public static void startAlarmClock(Context context, AlarmClock alarmClock) {
//        Intent intent = new Intent("com.kaku.weac.broadcast.ALARM_CLOCK_ONTIME");
        Intent intent = new Intent(context, AlarmClockBroadcast.class);
        intent.putExtra(WeacConstants.ALARM_CLOCK, alarmClock);
        // FLAG_UPDATE_CURRENT：如果PendingIntent已经存在，保留它并且只替换它的extra数据。
        // FLAG_CANCEL_CURRENT：如果PendingIntent已经存在，那么当前的PendingIntent会取消掉，然后产生一个新的PendingIntent。
        PendingIntent pi = PendingIntent.getBroadcast(context,
                alarmClock.getId(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        // 取得下次响铃时间
        long nextTime = calculateNextTime(alarmClock.getHour(),
                alarmClock.getMinute(), alarmClock.getWeeks());
        // 设置闹钟
        // 当前版本为19（4.4）或以上使用精准闹钟
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
        }

    }


    /**
     * 取消闹钟
     *
     * @param context        context
     * @param alarmClockCode 闹钟启动code
     */
    public static void cancelAlarmClock(Context context, int alarmClockCode) {
        Intent intent = new Intent(context, AlarmClockBroadcast.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, alarmClockCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context
                .getSystemService(Activity.ALARM_SERVICE);
        am.cancel(pi);
    }



    /**
     * 取得下次响铃时间
     *
     * @param hour   小时
     * @param minute 分钟
     * @param weeks  周
     * @return 下次响铃时间
     */
    public static long calculateNextTime(int hour, int minute, String weeks) {
        // 当前系统时间
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 下次响铃时间
        long nextTime = calendar.getTimeInMillis();
        // 当单次响铃时
        if (weeks == null) {
            // 当设置时间大于系统时间时
            if (nextTime > now) {
                return nextTime;
            } else {
                // 设置的时间加一天
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                nextTime = calendar.getTimeInMillis();
                return nextTime;
            }
        } else {
            nextTime = 0;
            // 临时比较用响铃时间
            long tempTime;
            // 取得响铃重复周期
            final String[] weeksValue = weeks.split(",");
            for (String aWeeksValue : weeksValue) {
                int week = Integer.parseInt(aWeeksValue);
                // 设置重复的周
                calendar.set(Calendar.DAY_OF_WEEK, week);
                tempTime = calendar.getTimeInMillis();
                // 当设置时间小于等于当前系统时间时
                if (tempTime <= now) {
                    // 设置时间加7天
                    tempTime += AlarmManager.INTERVAL_DAY * 7;
                }

                if (nextTime == 0) {
                    nextTime = tempTime;
                } else {
                    // 比较取得最小时间为下次响铃时间
                    nextTime = Math.min(tempTime, nextTime);
                }

            }

            return nextTime;
        }
    }


    /**
     * 去掉文件扩展名
     *
     * @param fileName 文件名
     * @return 没有扩展名的文件名
     */
    public static String removeEx(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < fileName.length())) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;
    }

    /**
     * 格式化时间
     *
     * @param hour   小时
     * @param minute 分钟
     * @return 格式化后的时间:[xx:xx]
     */
    public static String formatTime(int hour, int minute) {
        return addZero(hour) + ":" + addZero(minute);
    }


    /**
     * 格式化时间
     *
     * @param hour   小时
     * @param minute 分钟
     * @return 格式化后的时间:[xx:xx]
     */
    public static String formatTimeSpace(int hour, int minute) {
        return addZero(hour) + "  " + addZero(minute);
    }

    /**
     * 时间补零
     *
     * @param time 需要补零的时间
     * @return 补零后的时间
     */
    public static String addZero(int time) {
        if (String.valueOf(time).length() == 1) {
            return "0" + time;
        }

        return String.valueOf(time);
    }




}
