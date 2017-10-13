package com.liliuhuan.com.simplyskill;

import android.app.Application;
import android.content.Context;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-10-13
 * DES : ${}
 * =======================================
 */

public class MyApplication extends Application{
    public static boolean isLogin = false; //是否登录
    public static Context mContext; //是否登录

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
