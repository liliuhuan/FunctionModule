package com.liliuhuan.com.mylibrary.net.callback;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/19/16
 * 请求的回调
 */
public interface IRequestCallBack {

    /**
     * 请求前的回调
     */
    void onStartLoading();

    /**
     * 成功的回调
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败的回调
     * @param error
     */
    void onError(String error);

}
