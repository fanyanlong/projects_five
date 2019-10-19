package model;


import bean.LoginBean;
import bean.RegisterBean;
import contract.IUserContract;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import utils.RetrofitUtil;

/*
 *Name：fanyanlong
 *Time：11:24
 *Name:day02_mvp_mode
 */public class UserModel implements IUserContract.Imodel {
    private Observable observable;
    private Observer<LoginBean> loginBeanObserver;
    private Observer<RegisterBean> registerBeanObserver;
    //登录方法
    @Override
    public void getLoginData(String phone, String pwd, final IModelCallback iModelCallback){
         //获取网络数据 返回对象 被观察者
        observable = RetrofitUtil.getApiServer().login(phone,pwd);
         // 初始化 观察者
        loginBeanObserver = new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean userBean) {
                iModelCallback.onUserSuccess(userBean);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onUserFailure(e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        };
        //订阅
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBeanObserver);
    }
   //注册方法
    @Override
    public void getRegisterData(String phone, String pwd, final IModelCallback iModelCallback) {
        observable = RetrofitUtil.getApiServer().register(phone,pwd);
        registerBeanObserver = new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean userBean) {
                iModelCallback.onUserSuccess(userBean);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onUserFailure(e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registerBeanObserver);
    }
}
