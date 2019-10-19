package com.zd.retrofit_rxjava2.shopcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zd.retrofit_rxjava2.MainActivity;
import com.zd.retrofit_rxjava2.R;
import com.zd.retrofit_rxjava2.order.OrderByActivity;
import com.zd.retrofit_rxjava2.shopcar.adapter.ShopCarAdapter;
import com.zd.retrofit_rxjava2.shopcar.presenter.ShopCarPresenter;

import java.util.ArrayList;
import java.util.List;

import base.BaseAcitivity;
import bean.ShopCarBean;
import contract.IShopCarContract;
import utils.SharedPreUtils;

/**
 *@describe(描述)：ShopCarActivity
 *@data（日期）: 2019/10/15
 *@time（时间）: 16:47
 *@author（作者）: fanyanlong
 **/


public class ShopCarActivity extends BaseAcitivity implements IShopCarContract.Iview {
    public static final String TAG="ShopCarActivity";
    private ShopCarPresenter shopCarPresenter;
    private String sid;
    private RecyclerView recyclerView;
    private TextView mJstextview,mAllPictextview;
    private ImageView mAllcricle;
    private ShopCarAdapter shopCarAdapter;
    private boolean mIsAll = true;
    private List<ShopCarBean.ResultBean> mShopCarList = new ArrayList<>();
    float allPrice = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected void initdata() {
        shopCarAdapter = new ShopCarAdapter(ShopCarActivity.this);
        shopCarPresenter = new ShopCarPresenter();
        String mSessionId = SharedPreUtils.getParam(ShopCarActivity.this, "sid", "").toString();
        String mUserId = SharedPreUtils.getParam(ShopCarActivity.this, "uid", "").toString();
        if (shopCarPresenter!=null){
            shopCarPresenter.attachView(ShopCarActivity.this);
            shopCarPresenter.getPresenter(mUserId,mSessionId);
        }

        shopCarAdapter.setOnChangeDataListener(new ShopCarAdapter.OnChangeDataListener() {
            @Override
            public void changeData(List<ShopCarBean.ResultBean> list) {
                // 修改后的购物车数据
                //总价
                float allPrice = 0;
                //总数量
                int allNum = 0;
                //list 大小
                int allListSize = 0;
                for (int i = 0; i < list.size(); i++) {
                    List<ShopCarBean.ResultBean.ShoppingCartListBean> listBean =
                            list.get(i).getShoppingCartList();
                    allListSize = allListSize + listBean.size();
                    for (int j = 0; j < listBean.size(); j++) {
                        if (listBean.get(j).isSelected()) {
                            ShopCarBean.ResultBean.ShoppingCartListBean bean = listBean.get(j);
                            int count = Integer.valueOf(bean.getCount());
                            float price = Float.valueOf(bean.getPrice());
                            allPrice = allPrice + (count * price);
                            allNum++;

                        }

                    }

                    Log.d(TAG, "count: "+allNum);
                    Log.d(TAG, "allPrice: "+allPrice);

                }

                mAllPictextview.setText(allPrice+"￥");

            }

        });
        //全选
        mAllcricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsAll) {
                    mIsAll = false;
                    mAllcricle.setImageDrawable(getResources().getDrawable(R.mipmap.shop_car_all));
                } else {
                    mIsAll = true;
                    mAllcricle.setImageDrawable(getResources().getDrawable(R.drawable.shop_car_cricle));
                }
                setAll(!mIsAll);
            }
        });
    }
    //反选
    //购物车传递过来的数据
    private boolean mShopListAllClick;
    public void changeShopList(int shopListSize) {
        if (shopListSize == 0) {
            mShopListAllClick = false;
            mIsAll = true;
            mAllcricle.setImageDrawable(getResources().getDrawable(R.drawable.shop_car_cricle));
            mAllPictextview.setText("¥0");
            allPrice = 0;
        } else {
            mShopListAllClick = true;
        }
    }
    @Override
    protected void initview() {
        recyclerView = findViewById(R.id.recycler);
        mJstextview = findViewById(R.id.jsbutton);
        mAllPictextview = findViewById(R.id.tv_add);
        mAllcricle = findViewById(R.id.iv_allcricle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mJstextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopCarActivity.this, OrderByActivity.class));
            }
        });
    }



    @Override
    public void onShopCarSuccess(Object data) {
        //类型转换
        ShopCarBean shopCarBean= (ShopCarBean) data;
        Log.d(TAG, "onShopCarSuccess: "+shopCarBean.getMessage());

        //获取数据
        mShopCarList=shopCarBean.getResult();
        shopCarAdapter.setList(mShopCarList);
        recyclerView.setAdapter(shopCarAdapter);
        changeShopList(mShopCarList.size());
    }

    @Override
    public void onShopCarFailure(String e) {

    }


    //是否全选
    public void setAll(boolean isAll) {

        for (int i = 0; i < mShopCarList.size(); i++) {
            List<ShopCarBean.ResultBean.ShoppingCartListBean> list =
                    mShopCarList.get(i).getShoppingCartList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(isAll);
                ShopCarBean.ResultBean.ShoppingCartListBean bean = list.get(j);
                int count = Integer.valueOf(bean.getCount());
                float price = Float.valueOf(bean.getPrice());
                allPrice = allPrice + (count * price);
            }
        }
        if (!isAll) {
            allPrice = 0;
        }
        mAllPictextview.setText(allPrice+"￥");
        shopCarAdapter.setList(mShopCarList);
    }
}
