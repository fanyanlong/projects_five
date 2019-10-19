package com.zd.project_five.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zd.project_five.R;
import com.zd.project_five.utils.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ShowActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        textView = findViewById(R.id.tv);
        //注册
        EventBus.getDefault().register(ShowActivity.this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public  void  handlevet(EventMessage eventMessage){
        textView.setText(eventMessage.getName());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ShowActivity.this);
    }
}
