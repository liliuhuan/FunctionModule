package com.liliuhuan.com.simplyskill.loading;

import android.view.View;

import com.liliuhuan.com.mylibrary.view.LoadingLayout;
import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.OnClick;

public class TestLoadingLayoutActivity extends BaseActivity {
   // @Bind(R.id.loading)
    LoadingLayout loading;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_loading_layout;
    }

    @Override
    public void initView() {
            loading = (LoadingLayout) findViewById(R.id.loading);
        loading.setStatus(LoadingLayout.Loading);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                loading.setStatus(LoadingLayout.Loading);
                break;
            case R.id.btn2:
                loading.setStatus(LoadingLayout.Error);
                break;
            case R.id.btn3:
                loading.setStatus(LoadingLayout.No_Network);
                break;
            case R.id.btn4:
                loading.setStatus(LoadingLayout.Success);
                break;
            case R.id.btn5:
                loading.setStatus(LoadingLayout.Empty);
                break;

        }
    }
}
