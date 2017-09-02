package com.liliuhuan.com.simplyskill.annotation.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liliuhuan on 2017/9/2.
 */
@Module
public class LExecutorModule {
    /**
     * 提供app 多任务最少维护线程个数
     * @return 返回多任务最少维护线程个数
     */
    @Provides
    @Singleton
    LExecutor provideLExecutor() {
        return new LExecutor(10);
    }
}
