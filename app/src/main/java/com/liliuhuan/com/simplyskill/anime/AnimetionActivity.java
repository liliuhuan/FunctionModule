package com.liliuhuan.com.simplyskill.anime;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimetionActivity extends BaseActivity {


    @BindView(R.id.action_image)
    ImageView actionImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animetion;
    }


    /**
     * AccelerateInterpolator 加速，开始时慢中间加速

     DecelerateInterpolator 减速，开始时快然后减速

     AccelerateDecelerateInterolator 先加速后减速，开始结束时慢，中间加速

     AnticipateInterpolator 反向，先向相反方向改变一段再加速播放

     AnticipateOvershootInterpolator 反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值

     BounceInterpolator 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100

     CycleIinterpolator 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)

     LinearInterpolator 线性，线性均匀改变

     OvershootInterpolator超越，最后超出目的值然后缓慢改变到目的值
     * @param view
     */
    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                alphaAnime();
                break;
            case R.id.text2:
                scaleAnime();
                break;
            case R.id.text3:
                translateAnime();
                break;
            case R.id.text4:
                rotaelAnime();
                break;
            case R.id.text5:
                setAnime();
                break;
        }
    }


    private void alphaAnime() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0F, 0.0F);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(3);
        actionImage.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void scaleAnime() {
        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
        scaleAnimation.setFillAfter(true);//如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
        scaleAnimation.setFillBefore(false);//如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
        scaleAnimation.setRepeatCount(3);//设置动画循环次数
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setStartOffset(0);
        scaleAnimation.setInterpolator(this, android.R.anim.decelerate_interpolator);//设置动画插入器
        actionImage.startAnimation(scaleAnimation);
    }
    private void translateAnime() {
        Animation translateAnimation = new TranslateAnimation(0, 100, 0, 0);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);//设置动画插入器
        translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
        actionImage.startAnimation(translateAnimation);
    }
    private void rotaelAnime() {
        Animation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);//设置动画插入器
        actionImage.startAnimation(rotateAnimation);
    }

    private void setAnime() {
        AnimationSet animationSet = new AnimationSet(true);

        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(500);//设置动画持续时间为500毫秒

        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);//设置动画持续时间为500毫秒
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setStartOffset(0);
        scaleAnimation.setInterpolator(this, android.R.anim.decelerate_interpolator);//设置动画插入器

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);

        actionImage.startAnimation(animationSet);
    }

}
