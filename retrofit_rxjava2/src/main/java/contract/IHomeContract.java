package contract;

import java.io.IOException;

/*
 *Name：fanyanlong
 *Time：10:38
 *Name:day02_mvp_mode
 * 契约类
 */
public interface IHomeContract {
    //view  interface
    interface  Iview{
        //view 与presenter 交互 获取presenter 的数据传递给view
        void onHomeSuccess(String data);
        void onHomeFailure(String e);
        void onXbanneromeSuccess(String data);
        void onXbannerHomeFailure(String e);
    }
    //model interface
    interface Imodel{
        //获取列表数据
        void  getHomeData(String path, IModelCallback iModelCallback) throws IOException;
        //获取xbanner
        void  getXbannerData(String path, IModelCallback iModelCallback) throws IOException;
        /**
         * model层中的接口回调
         */
        interface IModelCallback {
            void onHomeSuccess(String data);
            void onHomeFailure(String railure);
        }

    }
    //presenter  interface
    interface Ipresenter{
        //绑定view
       void attachView(IHomeContract.Iview iview);
        //解绑view
       void detachView();
        // 初始化列表数据
       void getPresenter(String path);
        void getXbannerPresenter(String path);
    }

}
