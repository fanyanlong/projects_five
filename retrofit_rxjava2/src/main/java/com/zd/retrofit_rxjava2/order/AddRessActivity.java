package com.zd.retrofit_rxjava2.order;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.zd.retrofit_rxjava2.R;

import base.BaseAcitivity;
import bean.LoginBean;
import utils.RetrofitUtil;
import utils.SharedPreUtils;

public class AddRessActivity extends BaseAcitivity {
    public static final String TAG="AddRessActivity";
    private TextView mTvAddress, mAddress, mAddressAdd;
    private EditText mAddressNameet, mPhoneet, mAllAddresset, mPostCodeet;
    private String mPhone,mName,mRess,mCode;
    private Object loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_ress;
    }

    @Override
    protected void initdata() {
        mPhone = mPhoneet.getText().toString();
        mName = mAddressNameet.getText().toString();
        mRess =mAllAddresset.getText().toString();
        mCode = mPostCodeet.getText().toString();
        String mSessionId = SharedPreUtils.getParam(AddRessActivity.this, "sid", "").toString();
        String mUserId = SharedPreUtils.getParam(AddRessActivity.this, "uid", "").toString();

        if (!mPhone.isEmpty()&&!mName.isEmpty()&&!mRess.isEmpty() &&!mCode.isEmpty()){

            RetrofitUtil.getApiServer().address(mSessionId,mUserId,mName,mPhone,mRess,mCode);
        }



    }

    @Override
    protected void initview() {
        mTvAddress =findViewById(R.id.address);
        mAddressNameet = findViewById(R.id.address_name);
        mPhoneet = findViewById(R.id.phone);
        mAddress = findViewById(R.id.address);
        mAllAddresset = findViewById(R.id.all_address);
        mPostCodeet = findViewById(R.id.post_code);
        mAddressAdd = findViewById(R.id.address_add);
    }
}
