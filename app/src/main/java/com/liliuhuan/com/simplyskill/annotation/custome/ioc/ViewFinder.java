package com.liliuhuan.com.simplyskill.annotation.custome.ioc;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

/**
 * Author :李刘欢
 * DATA : 2017-12-21
 * DES : 有问题
 */

public class ViewFinder {

    Object o ;
    public ViewFinder(Object o) {
        this.o = o;
    }

    public View findViewById(int viewId) {
        if (o instanceof  View || o instanceof Fragment){
            return ((View)o).findViewById(viewId);
        }
        if (o instanceof Activity){
            return findViewById(viewId);
        }
       return null;
    }
}
