package com.liliuhuan.com.simplyskill.aop;

import android.Manifest;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.aop.login.CheckLogin;
import com.liliuhuan.com.simplyskill.aop.net.CheckNetAnnotation;
import com.liliuhuan.com.simplyskill.aop.test.AspectJAnnotation;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @CheckLogin
    @OnClick(R.id.button11)
    public void onClick() {
        Toast.makeText(this, "已登录，", Toast.LENGTH_SHORT).show();
    }

    @AspectJAnnotation(value = Manifest.permission.CAMERA)
    @OnClick(R.id.button13)
    public void checkPermission() {
        Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
    }

    @CheckNetAnnotation
    @OnClick(R.id.button12)
    public void checkNet() {
        Toast.makeText(this, "网络状况良好", Toast.LENGTH_LONG).show();
    }


}
