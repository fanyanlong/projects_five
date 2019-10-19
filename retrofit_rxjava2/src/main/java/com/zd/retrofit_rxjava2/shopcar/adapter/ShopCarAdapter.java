package com.zd.retrofit_rxjava2.shopcar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.zd.retrofit_rxjava2.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerAdapter;
import bean.ShopCarBean;

/**
 *@describe(描述)：ShopCarAdapter 商家
 *@data（日期）: 2019/10/16
 *@time（时间）: 10:39
 *@author（作者）: fanyanlong
 **/
public class ShopCarAdapter  extends BaseRecyclerAdapter<ShopCarBean.ResultBean> {

    private SwipeRecyclerView swipeRecyclerView;
    private ShopCarItemAdapter shopCarItemAdapter;

    public ShopCarAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_shopcar;
    }

    @Override
    public void bindViewData(BaseViewHolder baseViewHolder, ShopCarBean.ResultBean resultBean) {
        //一级（商家）
        baseViewHolder.setText(R.id.shop_car_name,resultBean.getCategoryName());
        //二级RecyclerView（商品）
        swipeRecyclerView = (SwipeRecyclerView) baseViewHolder.get(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        swipeRecyclerView.setLayoutManager(linearLayoutManager);
        shopCarItemAdapter = new ShopCarItemAdapter(context);
        shopCarItemAdapter.setList(resultBean.getShoppingCartList());
        swipeRecyclerView.setAdapter(shopCarItemAdapter);
        shopCarItemAdapter.setOnChangeDataListener(new ShopCarItemAdapter.OnChangeDataListener() {
            @Override
            public void changeData(List<ShopCarBean.ResultBean.ShoppingCartListBean> list) {
                List<ShopCarBean.ResultBean> list1 = getList();
                if (mOnChangeDataListener != null) {
                    mOnChangeDataListener.changeData(list1);
                }
            }
        });
        //get/set
    }

    private OnChangeDataListener mOnChangeDataListener;

    public void setOnChangeDataListener(OnChangeDataListener mOnChangeDataListener) {
        this.mOnChangeDataListener = mOnChangeDataListener;
    }

    public interface OnChangeDataListener {
        void changeData(List<ShopCarBean.ResultBean> list);
    }



}
