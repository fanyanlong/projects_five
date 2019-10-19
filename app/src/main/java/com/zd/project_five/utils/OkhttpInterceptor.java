package com.zd.project_five.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *@describe(描述)：OkhttpInterceptor 自定义拦截器
 *@data（日期）: 2019/9/28
 *@time（时间）: 11:32
 *@author（作者）: fanyanlong
 **/
public class OkhttpInterceptor implements Interceptor {
     public static final String TAG="OkhttpInterceptor";
    private Request request;
    private Headers headers;

    @Override
    public Response intercept(Chain chain) throws IOException {
        request = chain.request();
        headers = chain.request().headers();
        Log.d(TAG, "intercept: "+request.toString());
        return chain.proceed(request);
    }
}
