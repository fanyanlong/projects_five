package com.zd.project_five;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zd.project_five.bean.User;
import com.zd.project_five.greendao.gen.DaoMaster;
import com.zd.project_five.greendao.gen.DaoSession;
import com.zd.project_five.greendao.gen.UserDao;
import com.zd.project_five.utils.EventMessage;
import com.zd.project_five.utils.OkHttpUtil;
import com.zd.project_five.view.ShowActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @describe(描述)：zxing 二维码
 * @data（日期）: 2019/9/27
 * @time（时间）: 11:23
 * @author（作者）: fanyanlong
 * 问题：1.权限（普通权限，危险权限）
 * zxing 使用流程：1.加依赖
 * 2.调起依赖
 * 3.添加动态权限，静态权限
 * 4.打开相机，调起二维扫描
 * 5.通过自定义view，实现定制二维码
 **/


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    String path = "http://172.17.8.100/small/commodity/v1/commodityList";
    @BindView(R.id.zxbutton)
    Button zxbutton;
    @BindView(R.id.okhttpasny)
    Button okhttpasny;
    @BindView(R.id.main_tv)
    TextView mainTv;
    @BindView(R.id.eventbus)
    Button eventbus;
    @BindView(R.id.eventbus2)
    Button eventbus2;
    private EventMessage eventMessage;
    private User user;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase writableDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化
        ZXingLibrary.initDisplayOpinion(this);
        eventMessage = new EventMessage("小明");
        EventBus.getDefault().postSticky(eventMessage);//粘性事件
        setDatabase();
        user = new User();
        user.setAge(18);
        user.setName("苍老师");
        user.setSex("女");
        user.setLike("喜欢徐晨曦");
        //添加数据
        userDao.insert(user);

    }

    //初始化 数据库
    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(MainActivity.this, "user-db", null);
        writableDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @OnClick({R.id.zxbutton, R.id.okhttpasny, R.id.eventbus, R.id.eventbus2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zxbutton:
                //判断危险权限是否开启 ContextCompat.checkSelfPermission
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //添加动态权限，相机，相册
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.CLEAR_APP_CACHE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 100);

                } else {
                    //调起二维码
                    startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 100);
                }
            break;
            case R.id.okhttpasny:
                OkHttpUtil.getInstance(MainActivity.this).getAsynHttp(path, new OkHttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        mainTv.setText(response.body().string());

                    }
                });

                break;
            case R.id.eventbus:
              startActivity(new Intent(MainActivity.this, ShowActivity.class));
              finish();

                break;
            case R.id.eventbus2:
                break;
        }
    }
}
