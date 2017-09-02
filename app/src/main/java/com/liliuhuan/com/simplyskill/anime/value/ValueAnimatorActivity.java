package com.liliuhuan.com.simplyskill.anime.value;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;
import butterknife.OnClick;

//插值动画
public class ValueAnimatorActivity extends BaseActivity {


    @BindView(R.id.number)
    TextView mTextView;
    @BindView(R.id.number1)
    MyTextView myTextView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_value_animator;
    }

    @OnClick(R.id.button8)
    public void onViewClicked1() {
        // TypeEvaluator//估值器
        /**
         IntEvaluator 针对整型属性

         IntArrayEvaluator 针对整型属性集合

         FloatEvaluator 针对浮点型属性

         FloatArrayEvaluator 针对浮点型属性集合

         ArgbEvaluator  针对Color属性

         RectEvaluator 针对Rect属性

         PointFEvaluator 针对PointF属性
         */

        AnimatorSet animationSet = new AnimatorSet();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F, 123420.34F);
        //valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new MineDecelerateAccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float money = (float) animation.getAnimatedValue();
                mTextView.setText(String.format("%.2f", money));
            }
        });

        int startColor = Color.parseColor("#3F51B5");
        int endColor = Color.parseColor("#FB0435");

        ValueAnimator textColorAnimator = ValueAnimator.ofObject(new TextArgbEvaluator(), startColor, endColor);
        textColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                mTextView.setTextColor(animatedValue);
            }
        });

        animationSet.playTogether(valueAnimator, textColorAnimator);
        animationSet.setDuration(5000);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.start();
    }


    @OnClick(R.id.button9)
    public void onViewClicked() {
        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(myTextView,"text",0f,5000f);
        objectAnimator.setDuration(5000);
        objectAnimator.setInterpolator(new MineDecelerateAccelerateInterpolator());
        objectAnimator.start();
    }
}
