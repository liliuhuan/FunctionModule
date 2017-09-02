package com.liliuhuan.com.simplyskill.annotation.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liliuhuan on 2017/9/2.
 */
@Module
public class LCacheModule {
    /**
     * 提供缓存对象
     * @return 返回缓存对象
     */
    @Provides
    @Singleton
    LCache provideLCache() {
        return new LCache("lcj",500);
    }
}
