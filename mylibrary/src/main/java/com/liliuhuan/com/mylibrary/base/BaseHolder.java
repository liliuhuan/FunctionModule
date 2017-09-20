package com.liliuhuan.com.mylibrary.base;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**

 * Date: 7/5/16
 * 基类holder
 */
public abstract class BaseHolder<T> {

    private View mView;
    private T data;
    private int pos;
    public Context context;

    public BaseHolder() {
        mView = initView();
        ButterKnife.bind(this,mView);
        mView.setTag(this);
    }

    public void setData(T data) {
        this.data = data;
        refreshView();
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public T getData() {
        return this.data;
    }

    public View getRootView() {
        return this.mView;
    }

    /**
     * 向view中填充数据
     */
    public abstract void refreshView();

    /**
     * 初始化view
     *
     * @return
     */
    public abstract View initView();

}
