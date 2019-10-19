package app;

import android.app.Application;
import android.content.Context;


/*
 *Name：fanyanlong
 *Time：9:54
 *Name:day02_mvp_mode
 */public class App extends Application {
    public static   Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //应用程序入口
        //初始化公共工具
        context=this;

    }
}
