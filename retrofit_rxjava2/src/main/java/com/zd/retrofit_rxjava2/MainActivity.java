package com.zd.retrofit_rxjava2;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.zd.retrofit_rxjava2.shopcar.ShopCarActivity;
import com.zd.retrofit_rxjava2.view.ShopAddView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import app.App;
import base.BaseAcitivity;
import bean.PhoneBean;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import utils.RetrofitUtil;

/**
 *@describe(描述)：MainActivity
 *@data（日期）: 2019/10/10
 *@time（时间）: 16:25
 *@author（作者）: fanyanlong
 * 需求
 * 《Android电商项目进阶》
 * 1.ViewPager + Fragment实现标签页切换
 * 2.MVP架构+基类进行封装（解决MVP内存泄漏、初始化布局、绑定事件）
 * 3.单例封装工具类，使用GreenDao对数据的基本操作（保存，查询等）
 * 4.RxJava+Retrofit进行网络请求并封装工具类（网络状态判断）
 * 4.登录、注册业务逻辑（非空及正则校验）
 * 5.实现图片回传及文件上传功能
 **/
public class MainActivity extends BaseAcitivity implements  View.OnClickListener,EasyPermissions.PermissionCallbacks{
   public static final String TAG="MainActivity";
    private ImageView mTxiv;
    private File cameraSavePath;//拍照照片路径

    private Uri uri;
    private Button mScbu,mXjbu,mXcbu,mShopcar;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Uri data1;
    Bitmap xjbitmap;
    private File file;
    private Flowable<PhoneBean> flowable;
    private Observable<PhoneBean> phoneBeanObservable;
    private Observer<PhoneBean> phoneBeanObserver;
    private String sid;
    private ShopAddView addView;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initdata() {
        sid = getIntent().getStringExtra("sid");
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");



    }
    @Override
    protected void initview() {
        mScbu = findViewById(R.id.scbu);
        mXcbu = findViewById(R.id.xcbu);
        mXjbu = findViewById(R.id.xjbu);
        //初始化组合组件
        addView = findViewById(R.id.addview);
        mShopcar = findViewById(R.id.shopcar);
        mTxiv = findViewById(R.id.iv);
        mScbu.setOnClickListener(this);
        mXcbu.setOnClickListener(this);
        mXjbu.setOnClickListener(this);
        mShopcar.setOnClickListener(this);
        //设置数量
        addView.setCount(2);
        //回调修改后的数量
        addView.setOnNumListener(new ShopAddView.OnNumListener() {
            @Override
            public void count(int count) {
                Log.d(TAG, "count: "+count);
            }
        });
        getPermission();


    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.shopcar:
                intent = new Intent(MainActivity.this, ShopCarActivity.class);
                intent.putExtra("sid",sid);
                startActivity(intent);
                break;
            case R.id.xjbu:
                //打开相机
                openCamera1();
                break;
            case R.id.xcbu:
                //打开相册
                goPhotoAlbum();
                break;
            case R.id.scbu:
             //上传图片
                //进行类型转换,因为在RetrofitService定义的是@Part MultipartBody.Part,所以要转成这样的格式
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(),requestBody);
                phoneBeanObservable = RetrofitUtil.getApiServer().uploadImage("10218", sid, image);
                phoneBeanObserver = new Observer<PhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhoneBean phoneBean) {
                        Toast.makeText(App.context,""+phoneBean.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };

                phoneBeanObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(phoneBeanObserver);

                break;
        }
    }
    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //框架要求必须这么写
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();

    }
    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }
    //调用相机
    private void openCamera1(){
        Intent intent;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                 xjbitmap=data.getParcelableExtra("data");
                 //mTxiv.setImageBitmap(xjbitmap);
                file = saveBitmapFile(xjbitmap);
                Glide.with(App.context).load(xjbitmap).into(mTxiv);


                break;
            case 2:
                Log.d(TAG, "onActivityResult:相册 " + data.getData().toString());
                ContentResolver resolver = getContentResolver();
                InputStream inputStream = null;
                try {
                    inputStream = resolver.openInputStream(data.getData());
                    Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream);
                    file = saveBitmapFile(bitmap2);
                    Glide.with(App.context).load(bitmap2).into(mTxiv);
                    //mTxiv.setImageBitmap(bitmap2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
                super.onActivityResult(requestCode, resultCode, data);
            }


    /**
     * 获取拍照相片存储文件
     * @param
     * @return
     */
    public File saveBitmapFile(Bitmap bitmap) {

            String timeStamp = String.valueOf(new Date().getTime());
            File  file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + timeStamp+".jpg");
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
           return file;
    }
}
