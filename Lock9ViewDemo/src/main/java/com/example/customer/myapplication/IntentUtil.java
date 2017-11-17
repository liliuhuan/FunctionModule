package com.example.customer.myapplication;

import android.content.Context;
import android.content.Intent;

/**
 * Created by liliuhuan on 2017/11/17.
 */

public class IntentUtil {
    public static void start(Context thisC, Class target) {
        thisC.startActivity(new Intent(thisC,target));
    }
}
