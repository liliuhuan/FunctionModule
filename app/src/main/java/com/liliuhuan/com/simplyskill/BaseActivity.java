package com.liliuhuan.com.simplyskill;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liliuhuan on 2017/9/2.
 */

public abstract class BaseActivity extends FragmentActivity {
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initView();
    }

    public void initView() {
    }

    public abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
