package com.zd.retrofit_rxjava2.shopcar.presenter;

import com.zd.retrofit_rxjava2.shopcar.model.ShopCarModel;

import java.lang.ref.WeakReference;

import contract.IShopCarContract;
import contract.IUserContract;
import model.UserModel;

/**
 *@describe(描述)：ShopCarPresenter
 *@data（日期）: 2019/10/15
 *@time（时间）: 15:51
 *@author（作者）: fanyanlong
 **/


public class ShopCarPresenter implements IShopCarContract.Ipresenter{
    private WeakReference<IShopCarContract.Iview> viewWeakReference;
    public static final String TAG="ShopCarPresenter";
    private ShopCarModel shopCarModel;

    @Override
    public void attachView(IShopCarContract.Iview iview) {
        //如何绑定view
        //弱引用
        viewWeakReference = new WeakReference(iview);
        shopCarModel = new ShopCarModel();
    }

    @Override
    public void detachView() {
        // 如何解绑view
        if (viewWeakReference != null) {
            viewWeakReference.clear();//清空
            viewWeakReference = null;
        }
    }
    public IShopCarContract.Iview getView() {
        return viewWeakReference.get();
    }
    @Override
    public void getPresenter(String userId, String sessionId) {
        shopCarModel.getShopCarData(userId, sessionId, new IShopCarContract.Imodel.IModelCallback() {
            @Override
            public void onShopCarSuccess(Object data) {
                getView().onShopCarSuccess(data);
            }

            @Override
            public void onShopCarFailure(String railure) {
                getView().onShopCarFailure(railure);
            }
        });
    }


}
