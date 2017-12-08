package com.bolooo.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;

@Router("args_module/:id/:name")
public class ArgsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1_activity_args);
        TextView tvShow = (TextView) findViewById(R.id.tv_show);
        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        tvShow.setText("id=="+id+"\nname=="+name);
    }
}
