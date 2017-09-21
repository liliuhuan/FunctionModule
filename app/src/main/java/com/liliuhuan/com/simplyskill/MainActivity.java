package com.liliuhuan.com.simplyskill;

import android.view.View;

import com.liliuhuan.com.simplyskill.Permission.PermissionsActivity;
import com.liliuhuan.com.simplyskill.anime.BaseAnimationActivity;
import com.liliuhuan.com.simplyskill.annotation.custome.CustomAnnotationActivity;
import com.liliuhuan.com.simplyskill.immersive.ImmersiveActivity;
import com.liliuhuan.com.simplyskill.loading.TestLoadingLayoutActivity;
import com.liliuhuan.com.simplyskill.savedata.SaveDataActivity;
import com.liliuhuan.com.simplyskill.utils.IntentUtil;
import com.liliuhuan.com.simplyskill.webview.WebViewActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4,R.id.button5,R.id.button6,R.id.button7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                IntentUtil.startActivity(this, ImmersiveActivity.class);
                break;
            case R.id.button2:
                IntentUtil.startActivity(this, BaseAnimationActivity.class);
                break;
            case R.id.button3:
                IntentUtil.startActivity(this, WebViewActivity.class);
                break;
            case R.id.button4:
                IntentUtil.startActivity(this, PermissionsActivity.class);
                break;
            case R.id.button5:
                IntentUtil.startActivity(this, CustomAnnotationActivity.class);
                break;
            case R.id.button6:
                IntentUtil.startActivity(this, SaveDataActivity.class);
                break;
            case R.id.button7:
                IntentUtil.startActivity(this, TestLoadingLayoutActivity.class);
                break;
        }
    }
}
