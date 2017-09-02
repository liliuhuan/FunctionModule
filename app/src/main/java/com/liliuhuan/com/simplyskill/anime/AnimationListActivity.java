package com.liliuhuan.com.simplyskill.anime;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationListActivity extends BaseActivity {


    @BindView(R.id.action_image)
    ImageView actionImage;
    private AnimationDrawable animationDrawable,anim;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animation_list;
    }

    @OnClick({R.id.xml_action, R.id.java_action, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xml_action:
                actionImage.setImageResource(R.drawable.animation_list);
                animationDrawable = (AnimationDrawable) actionImage.getDrawable();
                animationDrawable.start();
                break;
            case R.id.java_action:
                anim = new AnimationDrawable();
                for (int i = 1; i <= 7; i++) {
                    int id = getResources().getIdentifier("goal_0" + i, "mipmap", getPackageName());
                    Drawable drawable = getResources().getDrawable(id);
                    anim.addFrame(drawable, 500);
                }
                anim.setOneShot(false);
                actionImage.setImageDrawable(anim);
                anim.start();
                break;
            case R.id.stop:
                if (animationDrawable != null && animationDrawable.isRunning()) animationDrawable.stop();
                if (anim != null && anim.isRunning()) anim.stop();
                break;
        }
    }
}
