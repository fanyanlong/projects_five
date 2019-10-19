package utils;





import java.io.File;

import bean.LoginBean;
import bean.PhoneBean;
import bean.RegisterBean;
import bean.ShopCarBean;
import bean.ShowBean;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 *@describe(描述)：ApiServer
 *@data（日期）: 2019/10/10
 *@time（时间）: 15:33
 *@author（作者）: fanyanlong
 **/


public interface ApiServer {
    //?keyword=板鞋&page=1&count=5
      @GET("commodity/v1/findCommodityByKeyword")
      Observable<ShowBean> getdata(@Query("keyword") String keyword, @Query("page") String page, @Query("count") String count);
       //登录
      @POST("user/v1/login")
      Observable<LoginBean> login(@Query("phone") String phone, @Query("pwd")  String pwd);
       //注册
      @POST("user/v1/register")
      Observable<RegisterBean> register(@Query("phone") String phone, @Query("pwd")  String pwd);
      //上传头像
      @Multipart
      @POST("user/verify/v1/modifyHeadPic")
      Observable<PhoneBean> uploadImage(@Header("userId") String userId,@Header("sessionId")
              String sessionId,@Part MultipartBody.Part image);
      //获取购物车数据
    @GET("order/verify/v1/findShoppingCart")
    Observable<ShopCarBean> shopcar(@Header("userId") String userId, @Header("sessionId")String sessionId);
     //添加收货地址
    @GET("user/verify/v1/addReceiveAddress")
    Observable<ShopCarBean> address(@Header("userId") String userId, @Header("sessionId")String sessionId,
    @Query("realName") String realName, @Query("phone")  String phone,@Query("address")  String address,@Query("zipCode")  String zipCode);


}
