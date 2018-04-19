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
package com.jesgoo.fast.db;

import android.content.ContentValues;

import com.jesgoo.fast.bean.Timer;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;


/**
 * 计时器db操作类
 *
 * @author 咖枯
 * @version 1.0 2016/02/12
 */
public class TimerOperate {
    private static TimerOperate mTImeDBOperate;

    private TimerOperate() {
        Connector.getDatabase();
    }

    public synchronized static TimerOperate getInstance() {
        if (mTImeDBOperate == null) {
            mTImeDBOperate = new TimerOperate();
        }
        return mTImeDBOperate;
    }

    public boolean saveAlarmClock(Timer timer) {
        return timer != null && timer.saveFast();
    }


    public List<Timer> loadTimer() {
        List<Timer> timerList;
        timerList = DataSupport.order(null).find(Timer.class);
        return timerList;
    }

    public void deleteAlarmClock(Timer timer) {
        if (timer != null) {
            timer.delete();
        }
    }


    /**
     * 更新Timer表的开关信息
     */
    public void updateTimer(int id, int minute, int seconds) {
        ContentValues cv = new ContentValues();
        cv.put(WeacDBMetaDataLitePal.AC_TIMER_MIN, minute);
        cv.put(WeacDBMetaDataLitePal.AC_TIMER_SECONDS, seconds);
        DataSupport.update(Timer.class, cv, id);
    }


}
