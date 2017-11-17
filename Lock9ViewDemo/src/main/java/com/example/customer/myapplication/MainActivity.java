package com.example.customer.myapplication;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.customer.myapplication.databinding.ActivityMainBinding;
import com.example.customer.myapplication.weight.Lock9View;

public class MainActivity extends AppCompatActivity implements Lock9View.OnLockFinishListener {
    ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("text", MODE_PRIVATE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.lock.setOnLockFinishListener(this);
    }

    @Override
    public void onSuccess(boolean isSetLock, String password) {
        sharedPreferences.edit().putBoolean("isSetLock",isSetLock).commit();
        sharedPreferences.edit().putString("password",password).commit();
        finish();
    }

    @Override
    public void onShort(int remainder) {

    }

    @Override
    public void onFailue(boolean isSetLock, String password) {

    }
}
