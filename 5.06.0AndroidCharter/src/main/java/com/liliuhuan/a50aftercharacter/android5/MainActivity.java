package com.liliuhuan.a50aftercharacter.android5;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liliuhuan.a50aftercharacter.R;

/**
 * @author : 李刘欢
 * @date   :
 * @des    : http://blog.csdn.net/mrnohere/article/details/51864820
 */
public class MainActivity extends AppCompatActivity {
    private static boolean isChange = true ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(isChange? R.style.CustomTheme2:R.style.CustomTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_change_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme();
            }
        });
    }

    private void changeTheme() {
        Intent intent = getIntent();
        finish();//结束当前的Activity
        overridePendingTransition(0,0);//不要动画
        isChange = !isChange;
        startActivity(intent);
    }
}
