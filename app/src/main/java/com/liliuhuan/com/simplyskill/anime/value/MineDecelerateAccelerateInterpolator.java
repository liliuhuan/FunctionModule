package com.liliuhuan.com.simplyskill.anime.value;

import android.animation.TimeInterpolator;

/**
 *z自定义 插值器，控制动画的速率
 */
//先减速后加速插值器
public class MineDecelerateAccelerateInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5f) {
            result = (float) (Math.sin(Math.PI * input)) / 2.0f;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2.0f;
        }
        return result;
    }
}
