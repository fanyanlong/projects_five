package utils;

import android.util.Log;

import androidx.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *@describe(描述)：RetorfitUtil
 *@data（日期）: 2019/10/10
 *@time（时间）: 15:05
 *@author（作者）: fanyanlong
 **/
public class RetrofitUtil {
    public static final String TAG="RetrofitUtil";
     //3.Rxjava 替换
    private static ApiServer apiServer;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    //单利模式
    /**
     * 单例模式
     */
    public static ApiServer getApiServer() {
        if (apiServer == null) {
            synchronized (RetrofitUtil.class) {
                if (apiServer == null) {
                    apiServer = new RetrofitUtil().getRetrofit();
                }
            }
        }
        return apiServer;
    }

    private RetrofitUtil(){}

    public ApiServer getRetrofit() {
        // 初始化Retrofit
        ApiServer apiServer = initRetrofit(initOkHttp()).create(ApiServer.class);
        return  apiServer;
    }

        //1.初始化 okhttp
    private OkHttpClient initOkHttp() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(httpLoggingInterceptor)//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
        return  okHttpClient;
    }
    //2.初始化 Retrofit
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constans.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjava 线程切换
                .addConverterFactory(GsonConverterFactory.create())//数据解析
                .build();
        return retrofit;
    }
    }
