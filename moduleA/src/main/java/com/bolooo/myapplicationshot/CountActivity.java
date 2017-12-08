package com.bolooo.myapplicationshot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        final TextView tvShow = (TextView) findViewById(R.id.tv_show);
        findViewById(R.id.tv_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = count(1000);
                tvShow.setText("number=="+count);
            }
        });
    }

    private int count(int n) {
        if (n<1) return 0;
        int countNum = 0;
        String str = String.valueOf(n);
        int length = str.length();//位数
        for (int i = 1; i <= n ; i++) {
            if (length > 1){
                for (int j = length; j >1 ; j--) {
                    if (i%(Math.pow(10, j-1))==0){
                        countNum+=(j-1);
                        break;
                    }
                }
            }
        }
        return  countNum;
    }
}
