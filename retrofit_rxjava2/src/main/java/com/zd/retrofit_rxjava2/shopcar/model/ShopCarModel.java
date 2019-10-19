package com.zd.retrofit_rxjava2.shopcar.model;

import android.util.Log;

import java.io.IOException;

import bean.ShopCarBean;
import bean.ShowBean;
import contract.IShopCarContract;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import utils.RetrofitUtil;

/**
 *@describe(描述)：ShopCarModel  购物车数据
 *@data（日期）: 2019/10/15
 *@time（时间）: 15:39
 *@author（作者）: fanyanlong
 **/
public class ShopCarModel implements IShopCarContract.Imodel {
    public static final String TAG="ShopCarModel";
    private Observable<ShopCarBean> shopcar;
    private Observer<ShopCarBean> shopCarBeanObserver;

    //获取购物车数据
    @Override
    public void getShopCarData(String userId, String sessionId, final IModelCallback iModelCallback){
         //被观察者
        shopcar = RetrofitUtil.getApiServer().shopcar(userId, sessionId);
         // 观察者
        shopCarBeanObserver = new Observer<ShopCarBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShopCarBean shopCarBean) {
                Log.d(TAG, "onNext: "+shopCarBean.getMessage());
                iModelCallback.onShopCarSuccess(shopCarBean);

            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onShopCarFailure(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        shopcar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shopCarBeanObserver);
    }


}
