package presenter;


import java.lang.ref.WeakReference;

import contract.IUserContract;
import model.UserModel;

/*
 *Name：fanyanlong
 *Time：11:23
 *Name:day02_mvp_mode
 */public class UserPrestenter implements IUserContract.Ipresenter {

    private WeakReference<IUserContract.Iview> viewWeakReference;
    public static final String TAG="UserPrestenter";
    private UserModel userModel;

    @Override
    public void attachView(IUserContract.Iview iview) {
        //如何绑定view
        //弱引用
        viewWeakReference = new WeakReference<IUserContract.Iview>(iview);
        userModel = new UserModel();

    }
    public IUserContract.Iview getView() {
        return viewWeakReference.get();
    }
    @Override
    public void detachView() {
        // 如何解绑view
        if (viewWeakReference != null) {
            viewWeakReference.clear();//清空
            viewWeakReference = null;
        }
    }
    //登录方法
    @Override
    public void getLoginPresenter(String phone, String pwd) {
        userModel.getLoginData(phone, pwd, new IUserContract.Imodel.IModelCallback() {
            @Override
            public void onUserSuccess(Object data) {
                getView().onLoginSuccess(data);
            }

            @Override
            public void onUserFailure(String railure) {
                getView().onRegisterFailure(railure);
            }
        });

    }
  //注册方法
    @Override
    public void getRegisterPresenter(String phone, String pwd) {
        userModel.getRegisterData(phone, pwd, new IUserContract.Imodel.IModelCallback() {
            @Override
            public void onUserSuccess(Object data) {
               getView().onRegisterSuccess(data);
            }

            @Override
            public void onUserFailure(String railure) {
             getView().onRegisterFailure(railure);
            }
        });
    }

}
