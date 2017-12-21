package com.liliuhuan.com.simplyskill;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.liliuhuan.com.simplyskill.base.DefaultNavigation;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liliuhuan on 2017/9/2.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected String className = this.getClass().getSimpleName();
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        new DefaultNavigation.Builder(this,
                (ViewGroup) findViewById(android.R.id.content))
                .setLeftIcon(R.drawable.ic_arrow_back)
                .setLeft("left")
                .setLeftOnClickListener(this)
                .setTitle(className)
                .setRightText("NEXT")
//                .setRightIcon(R.drawable.ic_arrow_back)
                .create();
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

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
