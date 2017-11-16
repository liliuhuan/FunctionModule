package com.liliuhuan.com.simplyskill.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.widget.activity.LQRRefreshActivity;
import com.liliuhuan.com.simplyskill.widget.activity.ProgressViewActivity;

public class ViewAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        findViewById(R.id.btn).setOnClickListener(v -> {
           intentAc(LQRRefreshActivity.class);
        });
        findViewById(R.id.btn2).setOnClickListener(v -> {
            intentAc(ProgressViewActivity.class);
        });
    }

    private void intentAc(Class targetClass) {
        startActivity(new Intent(this,targetClass));
    }
}
