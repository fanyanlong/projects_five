package com.zd.retrofit_rxjava2.shopcar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zd.retrofit_rxjava2.R;
import com.zd.retrofit_rxjava2.view.ShopAddView;

import java.util.List;

import base.BaseRecyclerAdapter;
import bean.ShopCarBean;
/**
 *@describe(描述)：ShopCarItemAdapter 商品列表
 *@data（日期）: 2019/10/16
 *@time（时间）: 11:28
 *@author（作者）: fanyanlong
 **/
public class ShopCarItemAdapter extends BaseRecyclerAdapter<ShopCarBean.ResultBean.ShoppingCartListBean> {
     Context context;
    private ShopAddView shopAddView;
    private ImageView mCricle;
    OnChangeDataListener mOnChangeDataListener;
    public ShopCarItemAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_shopcaritem;
    }

    @Override
    public void bindViewDataPosition(BaseViewHolder baseViewHolder, final ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean, final int i) {
        super.bindViewDataPosition(baseViewHolder, shoppingCartListBean, i);
        baseViewHolder.setText(R.id.shop_car_title,shoppingCartListBean.getCommodityName());
        baseViewHolder.setText(R.id.shop_car_price,shoppingCartListBean.getPrice());
        baseViewHolder.setPic(R.id.shop_car_image,shoppingCartListBean.getPic());
        shopAddView = (ShopAddView) baseViewHolder.get(R.id.shop_add);
        mCricle = (ImageView) baseViewHolder.get(R.id.iv_cricle);
        //设置默认商品数量
        shopAddView.setCount(Integer.valueOf(shoppingCartListBean.getCount()));
        //修改后的商品数量
        shopAddView.setOnNumListener(new ShopAddView.OnNumListener() {
            @Override
            public void count(int count) {
                shoppingCartListBean.setCount(String.valueOf(count));
               if (mOnChangeDataListener != null) {
                    mOnChangeDataListener.changeData(getList());
                }
            }
        });
        if (shoppingCartListBean.isSelected()) {
            mCricle.setImageDrawable(context.getResources().getDrawable(R.mipmap.shop_car_all));
        } else {
            mCricle.setImageDrawable(context.getResources().getDrawable(R.drawable.shop_car_cricle));
        }

        //选中点击事件
        mCricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartListBean.setSelected(!shoppingCartListBean.isSelected());
                //条目刷新
                notifyItemChanged(i);
                if (mOnChangeDataListener != null) {
                    mOnChangeDataListener.changeData(getList());
                }
            }
        });

    }

    @Override
    public void bindViewData(BaseViewHolder baseViewHolder, final ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean) {

    }

    public void setOnChangeDataListener(OnChangeDataListener mOnChangeDataListener) {
        this.mOnChangeDataListener = mOnChangeDataListener;
    }

    public interface OnChangeDataListener {
        void changeData(List<ShopCarBean.ResultBean.ShoppingCartListBean> list);
    }





































   /* public static final String TAG="ShopCarItemAdapter";
    private Context context;
    private MyViewHolder myViewHolder;
    ShopCarBean.ResultBean resultBean;
    private ShopCarBean.ResultBean.ShoppingCartListBean shoppingCartListBean;

    public ShopCarItemAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           //加载子布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopcaritem, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof  MyViewHolder){
            shoppingCartListBean = resultBean.getShoppingCartList().get(position);
            ((MyViewHolder) holder).mTitletextView.setText(shoppingCartListBean.getCommodityName());
            Log.d(TAG, "onBindViewHolder: "+shoppingCartListBean.getCommodityName());
            ((MyViewHolder) holder).mPricetextview.setText(shoppingCartListBean.getPrice());
            Glide.with(context).load(shoppingCartListBean.getPic()).into(((MyViewHolder) holder).mImageview);
            ((MyViewHolder) holder).mShopviewadd.setCount(Integer.valueOf(shoppingCartListBean.getCount()));
            ((MyViewHolder) holder).mCricle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingCartListBean.setSelected(!shoppingCartListBean.isSelected());
                    notifyItemChanged(position);
                }
            });
            //判断商品是否选中状态
            if (shoppingCartListBean.isSelected()) {
                ((MyViewHolder) holder).mCricle.setImageResource(R.mipmap.shop_car_all);
            } else {
                ((MyViewHolder) holder).mCricle.setImageResource(R.drawable.shop_car_cricle);
            }


            ((MyViewHolder) holder).mShopviewadd.setOnNumListener(new ShopAddView.OnNumListener() {
                @Override
                public void count(int count) {
                    Log.d(TAG, "count: "+count);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return resultBean.getShoppingCartList().size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder {

        private final TextView mTitletextView,mPricetextview;
        private final ShopAddView mShopviewadd;
        private final ImageView mImageview,mCricle;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitletextView = itemView.findViewById(R.id.shop_car_title);
            mPricetextview = itemView.findViewById(R.id.shop_car_price);
            mImageview = itemView.findViewById(R.id.shop_car_image);
            mCricle = itemView.findViewById(R.id.iv_cricle);
            mShopviewadd = itemView.findViewById(R.id.shop_add);



        }

    }*/
}
