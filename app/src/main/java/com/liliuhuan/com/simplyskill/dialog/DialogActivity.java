package com.liliuhuan.com.simplyskill.dialog;

import android.view.View;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }


    @OnClick(R.id.button10)
    public void onClick() {
        DialogUtil.create(getSupportFragmentManager())
                .setViewListener(new DialogUtil.ViewListener() {
                    @Override
                    public void bindView(View v) {
                       // v.findViewById() 设置监听
                    }
                })
                .setLayoutRes(R.layout.dialog_layout)
                .setDimAmount(0.4f)//透明度
                .setTag("center")
                .show();
    }
}
