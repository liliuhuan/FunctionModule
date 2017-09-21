package com.liliuhuan.com.mylibrary.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.liliuhuan.com.mylibrary.event.InitEvent;
import com.liliuhuan.com.mylibrary.utils.glide.GlideUtils;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public abstract class BaseActivity extends FragmentActivity {
    private final String TAG = "MPermissions";
    private int REQUEST_CODE_PERMISSION = 0x00099;
    public int flag;

    public GlideUtils glideUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getClass().getSimpleName();
        afterContentView();
        setContentView(initLoadResId());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        glideUtils = new GlideUtils(this);
        initDate();
        initView();
        prepareData();
    }

    public abstract int initLoadResId() ;
    protected void initDate(){}
    protected abstract void initView();
    protected void afterContentView(){}
    protected void prepareData() {}
    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public void onEventMainThread(InitEvent event) {
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
