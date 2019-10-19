package presenter;

import android.util.Log;

import java.lang.ref.WeakReference;

import contract.IHomeContract;

/*
 *Name：fanyanlong
 *Time：11:37
 *Name:day02_mvp_mode
 */public class HomePresenter implements IHomeContract.Ipresenter {
    @Override
    public void attachView(IHomeContract.Iview iview) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getPresenter(String path) {

    }

    @Override
    public void getXbannerPresenter(String path) {

    }
  /*  private WeakReference<IHomeContract.Iview> viewWeakReference;
    private HomeModel homeModel;
    public static final String TAG="HomePresenter";

    *//*
     * 绑定view
     * *//*
    @Override
    public void attachView(IHomeContract.Iview view) {
        //如何在presenter层 获取model层。
        homeModel = new HomeModel();
        //如何绑定view
        //弱引用
        viewWeakReference = new WeakReference<IHomeContract.Iview>(view);
    }

    public IHomeContract.Iview getView() {
        return viewWeakReference.get();
    }
    //是否挂载
    *//*
     * 解绑view
     * *//*
    @Override
    public void detachView() {
        // 如何解绑view
        if (viewWeakReference != null) {
            viewWeakReference.clear();//清空
            viewWeakReference = null;
        }
    }
    @Override
    public void getPresenter(String path) {
        Log.d(TAG, "path==: "+path);
            homeModel.getHomeData(path, new IHomeContract.Imodel.IModelCallback() {

                @Override
                public void onHomeSuccess(String data) {
                    Log.d(TAG, "onHomeSuccess: "+data);
                    getView().onHomeSuccess(data);

                }

                @Override
                public void onHomeFailure(String railure) {
                    getView().onHomeFailure(railure);

                }
            });
    }

    @Override
    public void getXbannerPresenter(String path) {
        homeModel.getXbannerData(path, new IHomeContract.Imodel.IModelCallback() {
            @Override
            public void onHomeSuccess(String data) {
                getView().onXbanneromeSuccess(data);

            }

            @Override
            public void onHomeFailure(String railure) {
                getView().onXbannerHomeFailure(railure);

            }
        });
    }*/
}
