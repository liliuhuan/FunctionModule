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
    protected abstract void initView();

      /**
       * setContentView之前执行的事件
       */
    protected void afterContentView(){}
      /**
       * 初始化数据
       */
    protected void initDate(){}
    /**
     * 描述：获取数据源（网络请求或者数据库读取）
     */
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
  public void onResume() {
    super.onResume();
  //  MobclickAgent.onPageStart(this.getClass().getSimpleName());
   // MobclickAgent.onResume(this);
  }

  @Override
  public void onPause() {
    super.onPause();
  //  MobclickAgent.onPageEnd(this.getClass().getSimpleName());
   // MobclickAgent.onPause(this);
  }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

//    /**
//     * 检查用户是否登录，未登录直接跳登录页
//     * @return
//     */
//    public boolean checkLogin(){
//        if(CommentUtils.isLogin()){
//            return true;
//        }else {
//            IntentUtils.startIntent(this, LoginActivity.class);
//            return false;
//        }
//    }
}
