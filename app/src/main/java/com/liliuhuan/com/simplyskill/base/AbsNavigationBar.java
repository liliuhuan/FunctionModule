package com.liliuhuan.com.simplyskill.base;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Author :李刘欢
 * DATA : 2017-12-21
 * DES :
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.NavigationParams> implements INavigation {

    private P params;

    private View view;

    public AbsNavigationBar(P params) {
        this.params = params;
        createAndBind();
    }

    protected String getString(int id) {
        return this.params.context.getResources().getString(id);
    }

    protected int getColor(int id) {
        return ContextCompat.getColor(this.params.context, id);
    }

    protected P getParams() {
        return params;
    }


    /**
     * 设置文本
     * @param viewId
     * @param text
     */
    protected void setText(int viewId, CharSequence text) {
        TextView tv = findViewById(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }
    /**
     * 设置背景颜色
     * @param viewId
     * @param
     */
    protected void setBackgroundColor(int viewId, int color) {
        RelativeLayout tv = findViewById(viewId);
        if (tv != null) {
            tv.setBackgroundColor(this.params.context.getResources().getColor(color));
        }
    }
    /**
     * 设置点击事件
     * @param viewId
     * @param listener
     */
    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }


    /**
     * 设置背景资源
     * @param viewId
     * @param resourceId
     */
    protected void setImageResource(int viewId, int resourceId) {
        ImageView imageView = findViewById(viewId);
        if (imageView != null) {
            imageView.setImageResource(resourceId);
        }
    }

    protected <T extends View> T findViewById(int id) {
        return (T) view.findViewById(id);
    }


    /**
     * 创建和绑定布局
     */
    public void createAndBind() {
        if (params == null) {
            return;
        }
        view = LayoutInflater.from(params.context).inflate(bindLayoutId(), params.parent, false);
        params.parent.addView(view, 0);
        applyView();
    }


    // 构建导航条类 这个类只是定义默认的配置 具体功能的实现一定由具体的实现类决定
    public abstract static class Builder {

        // 构建导航条方法
        public abstract AbsNavigationBar create();

        // 默认的配置参数
        public static class NavigationParams {
            public Context context;
            public ViewGroup parent;

            public NavigationParams(Context context, ViewGroup parent) {
                this.context = context;
                this.parent = parent;
            }
        }
    }
}