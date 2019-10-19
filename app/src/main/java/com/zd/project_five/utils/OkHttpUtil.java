package com.zd.project_five.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *@describe(描述)：OkHttpUtil 网络请求
 *@data（日期）: 2019/9/28
 *@time（时间）: 8:42
 *@author（作者）: fanyanlong
 **/


public class OkHttpUtil {
    public static final String TAG="OkHttpUtil";
    private volatile static OkHttpUtil okHttpUtil;//会被多线程使用，所以使用关键字volatile
    private OkHttpClient client;
    private Handler mHandler;
    //私有化构造方法
    private OkHttpUtil(Context context){
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 *1024;//设置缓存大小
        OkHttpClient.Builder builder= new OkHttpClient.Builder()
                .addInterceptor(new OkhttpInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(),cacheSize));//设置缓存的路径
        client = builder.build();
        mHandler = new Handler();
    }
    //单例模式，全局得到一个OkHttpUtil对象
    public static OkHttpUtil getInstance(Context context){
        if (okHttpUtil == null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil == null){
                    okHttpUtil = new OkHttpUtil(context);
                }
            }
        }
        return okHttpUtil;
    }

    /**get异步请求
     * @param url
     * @param callback
     */
    public void getAsynHttp(String url, final ResultCallback callback){
        Request request = new Request.Builder()
                .url(url).method("GET",null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                sendFailedCallback(call,e,callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response,callback);
            }
        });
    }

    /**提交表单数据
     * @param url
     * @param map
     * @param callback
     */
    public void postForm(String url, Map<String,String > map, final ResultCallback callback){
        FormBody.Builder form = new FormBody.Builder();//表单对象，包含以input开始的对象,以html表单为主
        if (map != null && !map.isEmpty()){
            //遍历Map集合
            for(Map.Entry<String ,String> entry : map.entrySet()){
                form.add(entry.getKey(),entry.getValue());
            }
            RequestBody body = form.build();
            Request request = new Request.Builder().url(url).
            post(body).build();//采用post提交数据
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //子线程
                    sendFailedCallback(call,e,callback);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //子线程
                    if (response.isSuccessful()&&response != null){
                        sendSuccessCallback(response,callback);
                    }
                }
            });
        }

    }

    /**当请求失败时，都会调用这个方法
     * @param call
     * @param e
     * @param callback
     */
    private void sendFailedCallback(final Call call, final IOException e, final ResultCallback callback){
        //handler 子线程切换主线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("main","当前线程："+Thread.currentThread().getName());
                if (callback != null){
                    callback.onError(call.request(),e);
                }
            }
        });
    }

    /**请求成功调用该方法
     * @param response  返回的数据
     * @param callback 回调的接口
     */
    private void sendSuccessCallback(final Response response, final ResultCallback callback){
        //handler 子线程切换主线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("main","当前线程："+Thread.currentThread().getName());
                if (callback != null){
                    try {
                        callback.onResponse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //创建接口，回调给调用者
    public   interface ResultCallback{
        void onError(Request request,Exception e);
        void onResponse(Response response) throws IOException;
    }

}
