package com.jesgoo.fast.api;


import com.jesgoo.fast.bean.Message;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Desc
 * Created by xupeng on 2018/1/31.
 */

public interface ApiService {

    /**
     * 获取城市列表
     *
     * @return
     */
    @GET(ServerApis.API_TOUTIAO)
    Observable<Message> getFromJuhe(@QueryMap Map<String, String> param);
}
