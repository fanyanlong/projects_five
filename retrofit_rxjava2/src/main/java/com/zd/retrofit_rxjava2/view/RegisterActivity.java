package com.zd.retrofit_rxjava2.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zd.retrofit_rxjava2.MainActivity;
import com.zd.retrofit_rxjava2.R;

import app.App;
import base.BaseAcitivity;
import bean.LoginBean;
import bean.RegisterBean;
import contract.IUserContract;
import presenter.UserPrestenter;
import utils.SharedPreUtils;

public class RegisterActivity extends BaseAcitivity implements IUserContract.Iview {
    public static final String TAG="RegisterActivity";
    private EditText et_userName;
    private EditText et_password;
    private Button bu_register,bu_login;
    private UserPrestenter userPrestenter;
    private String phone;
    private String pswd;
    private Intent intent;
    public  static   String sessionId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initdata() {
        userPrestenter = new UserPrestenter();
        userPrestenter.attachView(this);
    }
    @Override
    protected void initview() {
        et_password = findViewById(R.id.user_et_password);
        et_userName= findViewById(R.id.user_et_username);
        bu_register= findViewById(R.id.user_bu_register);
        bu_login= findViewById(R.id.user_bu_login);

        bu_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = et_userName.getText().toString();
                pswd = et_password.getText().toString();
                if (!phone.isEmpty()&&!pswd.isEmpty()){
                    userPrestenter.getRegisterPresenter(phone,pswd);
                }
            }
        });
        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = et_userName.getText().toString();
                pswd = et_password.getText().toString();
                if (!phone.isEmpty()&&!pswd.isEmpty()){
                    userPrestenter.getLoginPresenter(phone,pswd);
                }
            }
        });

    }

    @Override
    public void onLoginSuccess(Object data) {
        LoginBean  loginBean= (LoginBean) data;
        Log.d(TAG, "onLoginSuccess: "+loginBean.getMessage());
        if (loginBean!=null){
            if ( loginBean.getStatus().equals("0000")){
               String sid= loginBean.getResult().getSessionId();
               String uid=loginBean.getResult().getUserId();
                //sp 存储
                SharedPreUtils.setParam(RegisterActivity.this,"sid",sid);
                SharedPreUtils.setParam(RegisterActivity.this,"uid",uid);
                intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("sid",sid);
                startActivity(intent);
            }
        }

    }

    @Override
    public void onLoginFailure(String e) {

    }

    @Override
    public void onRegisterSuccess(Object data) {
        RegisterBean  registerBean= (RegisterBean) data;
        Log.d(TAG, "onLoginSuccess: "+registerBean.getMessage());
        Toast.makeText(App.context,""+registerBean.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRegisterFailure(String e) {

    }
}
