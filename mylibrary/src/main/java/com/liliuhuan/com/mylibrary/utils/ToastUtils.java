package com.liliuhuan.com.mylibrary.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.liliuhuan.com.mylibrary.LibraryApp;

/**
 * Toast工具类
 */
public class ToastUtils {
    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(LibraryApp.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(int resourse) {
        if (resourse == 0) {
            return;
        }
        Toast.makeText(LibraryApp.getInstance(), resourse, Toast.LENGTH_SHORT).show();
    }
}
