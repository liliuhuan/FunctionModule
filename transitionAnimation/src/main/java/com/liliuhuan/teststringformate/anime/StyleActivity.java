package com.liliuhuan.teststringformate.anime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liliuhuan.teststringformate.R;

public class StyleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        findViewById(R.id.finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
