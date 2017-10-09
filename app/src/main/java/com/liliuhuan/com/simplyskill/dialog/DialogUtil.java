package com.liliuhuan.com.simplyskill.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.view.View;


public class DialogUtil extends BaseDialog {

    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private static final String KEY_HEIGHT = "bottom_height";
    private static final String KEY_DIM = "bottom_dim";
    private static final String KEY_CANCEL_OUTSIDE = "bottom_cancel_outside";

    private FragmentManager mFragmentManager;

    private boolean mIsCancelOutside = super.getCancelOutside();

    private String mTag = super.getFragmentTag();

    private float mDimAmount = super.getDimAmount();

    private int mHeight = super.getHeight();

    @LayoutRes
    private int mLayoutRes;

    private ViewListener mViewListener;

    public static DialogUtil create(FragmentManager manager) {
        DialogUtil dialog = new DialogUtil();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public DialogUtil setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public DialogUtil setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public DialogUtil setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public DialogUtil setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public DialogUtil setTag(String tag) {
        mTag = tag;
        return this;
    }

    public DialogUtil setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public DialogUtil setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public boolean getCancelOutside() {
        return mIsCancelOutside;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }

    public interface ViewListener {
        void bindView(View v);
    }

    public BaseDialog show() {
        show(mFragmentManager);
        return this;
    }
}
