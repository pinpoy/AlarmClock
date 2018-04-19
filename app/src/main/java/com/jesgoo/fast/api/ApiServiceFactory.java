package com.jesgoo.fast.api;


import retrofit.Retrofit;

/**
 * ProjectName: SpiderApiServiceFactory
 * Description: 接口工厂类
 * <p>
 * author: xupeng
 */
public class ApiServiceFactory {

    /**
     * 创建接口对象
     *
     * @return
     */
    public static ApiService getApiService(String url) {
        ApiService apiService = null;
        Retrofit retrofit = RetrofitManager.get(url);
        apiService = retrofit.create(ApiService.class);


        return apiService;
    }
}