package com.liliuhuan.com.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 跳转工具类
 */

public class IntentUtils {
    /*
    不带参数的跳转
     */
    public static void startIntent(Context activity, Class loginActivityClass) {
        Intent intent=new Intent(activity,loginActivityClass);
        activity.startActivity(intent);
    }
    /*
     带参数的跳转2
      */
    public static void startIntentBundle(Context context, Bundle bundle, Class targetClass) {
        Intent intent=new Intent(context,targetClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
