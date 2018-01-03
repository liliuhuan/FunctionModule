package com.liliuhuan.teststringformate.anime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.liliuhuan.teststringformate.R;

public class ActivityOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        // 设置允许使用转场动画 此属性同样可以在style设置
//        // <item name="android:windowContentTransitions">true</item>
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
