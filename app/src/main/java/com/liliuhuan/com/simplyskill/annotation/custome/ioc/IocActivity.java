package com.liliuhuan.com.simplyskill.annotation.custome.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.R;

@CheckNetClass
public class IocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc);
        ViewUtils.inject(this);
        findViewById(R.id.check_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetIoc();
            }
        });
    }
    @CheckNet
    private void checkNetIoc() {
        Toast.makeText(this,"网络正常",Toast.LENGTH_LONG).show();
    }
}
