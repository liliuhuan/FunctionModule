package com.liliuhuan.com.appdevelop;

import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void requestNet() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.more).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);
        findViewById(R.id.check).setOnClickListener(this);
        findViewById(R.id.multi).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:startAc(MoreIntentActivity.class);break;
            case R.id.share:startAc(ShareActivity.class);break;
            case R.id.check:startAc(PermissionActivity.class);break;
            case R.id.multi:startAc(MultiMediaActivity.class);break;
        }
    }


}
