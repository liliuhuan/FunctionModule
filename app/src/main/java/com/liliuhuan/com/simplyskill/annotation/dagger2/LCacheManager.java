package com.liliuhuan.com.simplyskill.annotation.dagger2;

/**
 * Created by liliuhuan on 2017/9/2.
 */

public class LCacheManager {
    public static  final  String TAG=LCacheManager.class.getSimpleName();
    private  LCacheComponent cacheComponent;

    private static class SingletonHolder {
        private static LCacheManager instance = new LCacheManager();
    }

    private LCacheManager(){

        //cacheComponent = DaggerLCacheComponent.builder().lCacheModule(new LCacheModule()).build();
        cacheComponent.inject(this);
    }

    public static LCacheManager getInstance() {
        return SingletonHolder.instance;
    }

    public  void saveCache(final String key , final String value) {
        cacheComponent.lExecutor().runTask(new Runnable() {
            @Override
            public void run() {
                cacheComponent.lCache().saveCache(key,value);
            }
        });
    }

    public  void readCache(final String key){
        cacheComponent.lExecutor().runTask(new Runnable() {
            @Override
            public void run() {
                cacheComponent.lCache().readCache(key);
            }
        });
    }
}
