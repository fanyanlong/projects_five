package com.zd.retrofit_rxjava2.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zd.retrofit_rxjava2.R;

import base.BaseAcitivity;
/**
 *@describe(描述)：OrderByActivity  订单列表
 *@data（日期）: 2019/10/17
 *@time（时间）: 11:28
 *@author（作者）: fanyanlong
 **/


public class OrderByActivity extends BaseAcitivity {

    private Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_by;
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void initview() {
        button = findViewById(R.id.addshdz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderByActivity.this,AddRessActivity.class));
            }
        });


    }
}
