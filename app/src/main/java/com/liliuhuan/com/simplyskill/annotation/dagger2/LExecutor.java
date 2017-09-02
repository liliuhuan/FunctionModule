package com.liliuhuan.com.simplyskill.annotation.dagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liliuhuan on 2017/9/2.
 */

public class LExecutor {
    private static final int DEFAULT_CPU_CORE = Runtime.getRuntime().availableProcessors();//默认线程池维护线程的最少数量
    private int coreSize = DEFAULT_CPU_CORE;//线程池维护线程的最少数量

    @Inject
    public LExecutor(int coreSize) {
        this.coreSize = coreSize;
    }

    public void runTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        Log.e(LCacheManager.TAG,"coreSize:  = "+coreSize);
        Log.e(LCacheManager.TAG, "runTask");
        runnable.run();
    }
}
