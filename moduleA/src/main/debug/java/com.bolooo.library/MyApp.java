package com.bolooo.library;

import android.app.Application;

import com.github.mzule.activityrouter.annotation.Modules;

/**
 * Created by erfli on 11/2/16.
 */
@Modules({"app"})
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}