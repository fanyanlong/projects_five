package base;

import java.lang.ref.WeakReference;

import contract.IUserContract;
import model.UserModel;

/**
 *@describe(描述)：BasePersent
 *@data（日期）: 2019/10/15
 *@time（时间）: 15:52
 *@author（作者）: fanyanlong
 **/
public class BasePersent {
  /*  public void attachView(IUserContract.Iview iview) {
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
    }*/
}
