package base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
 *Name：fanyanlong
 *Time：9:57
 *Name:day02_mvp_mode
 */public abstract class BaseAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initview();
        initdata();



    }
    //加载布局
    protected abstract int getLayoutId();
    //初始化数据
    protected abstract void initdata();
    //初始化view
    protected abstract void initview();





}
