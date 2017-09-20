package com.liliuhuan.com.mylibrary.utils;

/**
 * 防止点击按钮多次执行
 *
 *
 * @version 3.7.0
 */
public class OnClickUtils {
  private static long lastClickTime;
  public static boolean isFastDoubleClick() {
    long time = System.currentTimeMillis();
    long timeD = time - lastClickTime;
    if ( 0 < timeD && timeD < 500) {
      return true;
    }
    lastClickTime = time;
    return false;
  }
}
