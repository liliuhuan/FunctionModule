package com.liliuhuan.com.simplyskill.anime;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.anime.value.ValueAnimatorActivity;
import com.liliuhuan.com.simplyskill.utils.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PropertyAnimationActivity extends BaseActivity {

    @BindView(R.id.action_image)
    ImageView actionImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_property_animation;
    }

    @Override
    public void initView() {
        /** 单个view执行的动画
         ViewPropertyAnimator viewPropertyAnimator=imageView.animate();
         viewPropertyAnimator.scaleXBy(1.0f).scaleX(1.5f).scaleYBy(1.0f).scaleY(1.5f).setDuration(500).start();
         */
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(actionImage, "alpha", 0f, 1f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setRepeatCount(0);
                alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
                alphaAnimation.setStartDelay(200);
                alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                alphaAnimation.start();
                alphaAnimation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                break;
            case R.id.text2:
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(actionImage, "scaleX", 1f, 1.5f);
                scaleXAnimator.setDuration(500);
                scaleXAnimator.setRepeatCount(1);
                scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
                scaleXAnimator.start();
                break;
            case R.id.text3:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(actionImage, "translationX", 0f, 100f);
                objectAnimator.setDuration(500);
                objectAnimator.setRepeatCount(1);
                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                objectAnimator.start();
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        //可以根据自己的需要来获取动画更新值。
                        Log.e("AnimatorUpdateListener", "the animation value is " + value);
                    }
                });
                break;
            case R.id.text4:
                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(actionImage, "rotation", 0f, 360f);
                rotationAnimator.setDuration(500);
                rotationAnimator.setRepeatCount(1);
                rotationAnimator.setRepeatMode(ValueAnimator.REVERSE);
                rotationAnimator.start();
                break;
            case R.id.text5:
                AnimatorSet animatorSet = new AnimatorSet();

                ObjectAnimator scaleXAAnimator = ObjectAnimator.ofFloat(actionImage, "scaleX", 1f, 1.5f);
                scaleXAAnimator.setDuration(500);
                scaleXAAnimator.setRepeatCount(1);
                scaleXAAnimator.setRepeatMode(ValueAnimator.REVERSE);
                scaleXAAnimator.start();

                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(actionImage, "scaleY", 1f, 1.5f);
                scaleYAnimator.setDuration(500);
                scaleYAnimator.setRepeatCount(1);
                scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);
                //animatorSet.play(scaleXAAnimator).after(scaleYAnimator);
                animatorSet.playTogether(scaleXAAnimator, scaleYAnimator);
                animatorSet.start();
                /**
                 * 组合动画
                 PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
                 PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.5f);
                 ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, scaleXValuesHolder, scaleYValuesHolder);
                 objectAnimator.setDuration(500);
                 objectAnimator.setRepeatCount(1);
                 objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                 objectAnimator.start();
                 */
                break;

            case R.id.text6:
                IntentUtil.startActivity(this, ValueAnimatorActivity.class);
                break;
        }
    }
}
