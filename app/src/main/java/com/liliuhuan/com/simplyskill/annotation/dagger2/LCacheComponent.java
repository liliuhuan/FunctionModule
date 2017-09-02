package com.liliuhuan.com.simplyskill.annotation.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liliuhuan on 2017/9/2.
 */
@Component(modules = {LCacheModule.class,LExecutorModule.class})
@Singleton
public interface LCacheComponent {
    LCache lCache();   // app缓存

    LExecutor lExecutor();  // app多任务线程池

    void inject(LCacheManager lCacheManager);
}
