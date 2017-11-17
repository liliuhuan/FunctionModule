package com.example.customer.myapplication;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.customer.myapplication.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLogInBinding binding;
    private SharedPreferences sharedPreferences;
    private boolean isSetLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in);
        binding.setLock.setOnClickListener(this);
        binding.verityLock.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("text", MODE_PRIVATE);
        isSetLock = sharedPreferences.getBoolean("isSetLock", false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_lock :
                IntentUtil.start(this,MainActivity.class);
                break;
            case R.id.verity_lock :
                if (!isSetLock)
                IntentUtil.start(this,SecondActivity.class);
                else Toast.makeText(this,"请先设置密码",Toast.LENGTH_LONG).show();
                break;
        }

    }
}
