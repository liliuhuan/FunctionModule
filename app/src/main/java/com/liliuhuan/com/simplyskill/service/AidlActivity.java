package com.liliuhuan.com.simplyskill.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liliuhuan.com.simplyskill.R;

public class AidlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        findViewById(R.id.btn1).setOnClickListener(v ->intent(AidlSimpleActivity.class));
        findViewById(R.id.btn2).setOnClickListener(v -> intent(AidlMutilActivity.class));
        findViewById(R.id.btn3).setOnClickListener(v -> intent(IPCActivity.class));
    }
    private void intent(Class targetClass) {
        startActivity(new Intent(this,targetClass));
    }
}
