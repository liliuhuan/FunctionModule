package com.liliuhuan.com.simplyskill.dialog;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.liliuhuan.com.simplyskill.R;

/**
 * Created by shaohui on 16/10/11.
 */

public abstract class BaseDialog extends DialogFragment {

    private static final String TAG = "bottom";

    private static final float DEFAULT_DIM = 0.2f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogUtil);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());

        View v = inflater.inflate(getLayoutRes(), container, false);
        bindView(v);
        return v;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void bindView(View v);

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.dimAmount = getDimAmount();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        if (getHeight() > 0) {
            params.height = getHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if (!getFragmentTag().equals("bottom"))
        params.gravity = Gravity.CENTER;
        else  params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }

    public int getHeight() {
        return -1;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public boolean getCancelOutside() {
        return true;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }
}
