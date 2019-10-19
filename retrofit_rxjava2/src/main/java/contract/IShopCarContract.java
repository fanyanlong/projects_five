package contract;

import java.io.IOException;

import retrofit2.http.Header;

/**
 *@describe(描述)：IShopCarContract 购物车契约类
 *@data（日期）: 2019/10/15
 *@time（时间）: 15:37
 *@author（作者）: fanyanlong
 **/



public interface IShopCarContract {

    //view  interface
    interface  Iview{
        //view 与presenter 交互 获取presenter 的数据传递给view
        void onShopCarSuccess(Object data);
        void onShopCarFailure(String e);
        
    }
    //model interface
    interface Imodel{
        //获取列表数据
        void  getShopCarData(String userId,String sessionId, IShopCarContract.Imodel.IModelCallback iModelCallback) ;
        /**
         * model层中的接口回调
         */
        interface IModelCallback {
            void onShopCarSuccess(Object data);
            void onShopCarFailure(String railure);
        }

    }
    //presenter  interface
    interface Ipresenter{
        //绑定view
        void attachView(IShopCarContract.Iview iview);
        //解绑view
        void detachView();
        // 初始化列表数据
        void getPresenter(String userId,String sessionId);
    }



}
