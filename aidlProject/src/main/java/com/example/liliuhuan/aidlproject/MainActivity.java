package com.example.liliuhuan.aidlproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void IntentAidl(View v){
        startActivity(new Intent(this,AidlActivity.class));
    }

    public void IntentAidlAdvance(View v){
        startActivity(new Intent(this,AidlAdvanceActivity.class));
    }
}
