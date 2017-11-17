package com.example.customer.myapplication;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.customer.myapplication.databinding.ActivitySecondBinding;
import com.example.customer.myapplication.weight.Lock9View;

public class SecondActivity extends AppCompatActivity implements Lock9View.OnLockFinishListener {
    ActivitySecondBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_second);
        binding.lock.setOnLockFinishListener(this);
        sharedPreferences = getSharedPreferences("text", MODE_PRIVATE);
        String password = sharedPreferences.getString("password", "");
        binding.lock.setLock(password);
    }

    @Override
    public void onSuccess(boolean isSetLock, String password) {
        IntentUtil.start(this,Main2Activity.class);
    }

    @Override
    public void onShort(int remainder) {

    }

    @Override
    public void onFailue(boolean isSetLock, String password) {

    }
}
