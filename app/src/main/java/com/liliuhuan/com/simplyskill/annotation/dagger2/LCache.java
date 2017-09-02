package com.liliuhuan.com.simplyskill.annotation.dagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * 处理缓存
 */

public class LCache {
    private static  final  String DEFAULT_CACHE_NAME="LCache";//默认缓存名字
    private static  final  int DEFAULT_MAX_CACHE_SIZE=1024;//默认缓存名字
    private String cacheName=DEFAULT_CACHE_NAME;//缓存名字
    private int maxCacheSize=DEFAULT_MAX_CACHE_SIZE;


    public LCache (){
    }

    @Inject
    public  LCache(String cacheName,int maxCacheSize){
        this.cacheName=cacheName;
        this.maxCacheSize=maxCacheSize;
    }


    public void saveCache(String key ,String value){
        Log.e(LCacheManager.TAG,"cacheName:  = "+cacheName);
        Log.e(LCacheManager.TAG,"maxCacheSize:  = "+maxCacheSize);
        Log.e(LCacheManager.TAG,"saveCache: key = "+key +" value = "+value);
    }

    public  void readCache(String key){
        Log.e(LCacheManager.TAG,"readCache: key:  = "+key);
    }
}
