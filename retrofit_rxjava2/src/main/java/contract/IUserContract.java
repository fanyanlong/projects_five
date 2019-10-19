package contract;

import java.io.IOException;

/*
 *Name：fanyanlong
 *Time：10:38
 *Name:day02_mvp_mode
 * 契约类
 */
public interface IUserContract {
    //view  interface
    interface  Iview{
        //view 与presenter 交互 获取presenter 的数据传递给view
        void onLoginSuccess(Object data);
        void onLoginFailure(String e);
        void onRegisterSuccess(Object data);
        void onRegisterFailure(String e);

    }
    //model interface
    interface Imodel{
        //获取数据
        void  getLoginData(String phone,String pwd, IModelCallback iModelCallback) throws IOException;
        void  getRegisterData(String phone,String pwd, IModelCallback iModelCallback) throws IOException;
        /**
         * model层中的接口回调
         */
        interface IModelCallback {
            void onUserSuccess(Object data);
            void onUserFailure(String railure);
        }

    }
    //presenter  interface
    interface Ipresenter{
        //绑定view
       void attachView(IUserContract.Iview iview);
        //解绑view
       void detachView();
        // 初始化
       void getLoginPresenter(String phone, String pwd);
        void getRegisterPresenter(String phone, String pwd);
    }

}
