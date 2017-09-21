package com.liliuhuan.com.mylibrary;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.liliuhuan.com.mylibrary.db.RealmHelper;
import com.pixplicity.easyprefs.library.Prefs;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by liliuhuan on 2017/9/20.
 */

public class LibraryApp  extends Application {
    private static LibraryApp instance;
    private static Context context;
    public static String token;
    public static int mMainThreadId;
    private static Handler mMainHandler;

    public static LibraryApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        LibraryApp.token = token;
    }

    public static int getmMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getmMainHandler() {
        return mMainHandler;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        mMainThreadId = android.os.Process.myTid();
        mMainHandler = new Handler();
        initPrefs();
        initRealm();
      //  initUpdateConfig();
        initLoadingConfig();
    }



    private void initPrefs() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(RealmHelper.DB_NAME)
                .schemaVersion(1)
                .rxFactory(new RealmObservableFactory())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
//    private void initUpdateConfig() {
//        UpdateConfig.getConfig()// 必填：初始化一个Application框架内使用
//                .init(this)// 必填：数据更新接口,url与checkEntity两种方式任选一种填写
//                //.url(Constants.UPDATE_APP_URL)
//                .checkEntity(new CheckEntity().setMethod(HttpMethod.GET).setUrl(Config.UPDATE_APP_URL))
//                // 必填：用于从数据更新接口获取的数据response中。解析出Update实例。以便框架内部处理
//                .jsonParser(new UpdateParser() {
//                    @Override
//                    public Update parse(String response) {
//                        /* 此处根据上面url或者checkEntity设置的检查更新接口的返回数据response解析出
//                          一个update对象返回即可。更新启动时框架内部即可根据update对象的数据进行处理
//                         */
//                        Update update = new Update(response);
//                        UpdateEntity updateEntity = JSONObject.parseObject(response, UpdateEntity.class);
//                        if(updateEntity!=null){
//                            if(updateEntity.IsSuccess){
//                                // 此apk包的更新时间
//                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                                try {
//                                    Date date = null;
//                                    date = formatter.parse(updateEntity.Data.UpdateTime);
//                                    update.setUpdateTime(date.getTime());
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                // 此apk包的下载地址
//                                update.setUpdateUrl(updateEntity.Data.DownloadUrl);
//                                // 此apk包的版本号
//                                update.setVersionCode(updateEntity.Data.VersionNum);
//                                // 此apk包的版本名称
//                                update.setVersionName(updateEntity.Data.VersionName);
//                                // 此apk包的更新内容
//                                update.setUpdateContent(updateEntity.Data.UpdateInfo);
//                                // 此apk包是否为强制更新
//                                update.setForced(updateEntity.Data.IsForcedUpdate);
//
//                                // 是否显示忽略此次版本更新按钮
//                                update.setIgnore(true);
//                            }
//                        }
//
//                        return update;
//                    }
//                })
//                // TODO: 2016/5/11 除了以上两个参数为必填。以下的参数均为非必填项。
//                .checkCB(new UpdateCheckCB() {
//
//                    @Override
//                    public void onCheckError(int code, String errorMsg) {
//                    }
//
//                    @Override
//                    public void onUserCancel() {
//                    }
//
//                    @Override
//                    public void onCheckIgnore(Update update) {
//                    }
//
//                    @Override
//                    public void onCheckStart() {
//                        // 此方法的回调所处线程异于其他回调。其他回调所处线程为UI线程。
//                        // 此方法所处线程为你启动更新任务是所在线程
//                        HandlerUtil.getMainHandler().post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void hasUpdate(Update update) {
//                    }
//
//                    @Override
//                    public void noUpdate() {
//                    }
//                })
//                // apk下载的回调
//                .downloadCB(new UpdateDownloadCB(){
//                    @Override
//                    public void onUpdateStart() {
//                    }
//
//                    @Override
//                    public void onUpdateComplete(File file) {
//                        ToastUtils.showToast(R.string.update_finish);
//                    }
//
//                    @Override
//                    public void onUpdateProgress(long current, long total) {
//                    }
//
//                    @Override
//                    public void onUpdateError(int code, String errorMsg) {
//                        ToastUtils.showToast(R.string.update_fail);
//                    }
//                }).strategy(new UpdateStrategy() {
//            @Override
//            public boolean isShowUpdateDialog(Update update) {
//                // 是否在检查到有新版本更新时展示Dialog。
//                return true;
//            }
//
//            @Override public boolean isAutoInstall() {
//                return true;
//            }
//
//            @Override
//            public boolean isShowDownloadDialog() {
//                // 在APK下载时。是否显示下载进度的Dialog
//                return true;
//            }
//        });
//    }

    private void initLoadingConfig() {
//        LoadingLayout.getConfig()
//                .setErrorText("出错啦~请稍后重试！")
//                .setEmptyText("抱歉，暂无数据")
//                .setNoNetworkText("无网络连接，请检查您的网络···")
//                .setErrorImage(R.mipmap.define_error)
//                .setEmptyImage(R.mipmap.define_empty)
//                .setNoNetworkImage(R.mipmap.define_nonetwork)
//                .setAllTipTextColor(R.color.gray)
//                .setAllTipTextSize(14)
//                .setReloadButtonText("点我重试哦")
//                .setReloadButtonTextSize(14)
//                .setReloadButtonTextColor(R.color.gray)
//                .setReloadButtonWidthAndHeight(150,40)
//                .setAllPageBackgroundColor(R.color.background);
//        .setLoadingPageLayout(R.layout.define_loading_page)
    }
}
