package com.liliuhuan.com.simplyskill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.liliuhuan.com.simplyskill.widget.LQRRefreshButton;

public class CustomViewActivity extends AppCompatActivity {

    private LQRRefreshButton viewById;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        viewById = (LQRRefreshButton) findViewById(R.id.btn_refresh);
        btnStop = (Button) findViewById(R.id.stop);
        viewById.setOnClickListener(v -> viewById.start());
        btnStop.setOnClickListener(v -> {if (viewById!= null ) viewById.stop();});
    }
}
