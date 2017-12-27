package com.liliuhuan.com.appdevelop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by liliuhuan on 2017/12/23.
 */

public abstract class BaseActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        initView();
        requestNet();
    }

    protected abstract void requestNet();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();


    protected void toast(String str) {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    protected void startAc(Class<?> clazz) {
        startActivity(new Intent(this,clazz));
    }

}
