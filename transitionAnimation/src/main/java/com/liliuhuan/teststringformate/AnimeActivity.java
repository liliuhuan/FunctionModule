package com.liliuhuan.teststringformate;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.liliuhuan.teststringformate.anime.ActivityOptionsActivity;
import com.liliuhuan.teststringformate.anime.OverridePendingActivity;
import com.liliuhuan.teststringformate.anime.StyleActivity;

public class AnimeActivity extends AppCompatActivity implements View.OnClickListener {
    private AnimeActivity selfActivity = AnimeActivity.this;
    Button btn;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置允许使用转场动画 此属性同样可以在style设置
        // <item name="android:windowContentTransitions">true</item>
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selfActivity, OverridePendingActivity.class));
                overridePendingTransition(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selfActivity, StyleActivity.class));
            }
        });
        btn = findViewById(R.id.btn3);
        iv = findViewById(R.id.iv);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn3:
                optionsCompat();
                break;
            case R.id.btn4:
                slideCompat();
                break;
            case R.id.btn5:
                explodeCompat();
                break;
            case R.id.btn6:
                fadeCompat();
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void fadeCompat() {
        Fade explode = new Fade();
        explode.setDuration(700);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(selfActivity);
        Intent intent = new Intent(selfActivity, ActivityOptionsActivity.class);
        startActivity(intent, optionsCompat.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void explodeCompat() {
        Explode explode = new Explode();
        explode.setDuration(700);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(selfActivity);
        Intent intent = new Intent(selfActivity, ActivityOptionsActivity.class);
        startActivity(intent, optionsCompat.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void slideCompat() {
        Slide slide = new Slide();
        slide.setDuration(700);
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(selfActivity);
        Intent intent = new Intent(selfActivity, ActivityOptionsActivity.class);
        startActivity(intent, optionsCompat.toBundle());
    }

    private void optionsCompat() {
        //多个共享
//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        this,
//                        Pair.create((View) btn, "btn3"),
//                        new Pair<View, String>(iv, "iv"));

        //单view 共享
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        iv,
                        "activityOptions");
        Intent intent = new Intent(selfActivity, ActivityOptionsActivity.class);
        startActivity(intent, optionsCompat.toBundle());
    }
}
