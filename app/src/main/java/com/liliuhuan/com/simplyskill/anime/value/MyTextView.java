package com.liliuhuan.com.simplyskill.anime.value;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by liliuhuan on 2017/9/2.
 */

public class MyTextView extends TextView{

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }
    public void setText(float money) {

        setText(String.format("%.2f", money));
    }

}
