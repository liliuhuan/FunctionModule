package com.liliuhuan.com.mylibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liliuhuan.com.mylibrary.R;
import com.liliuhuan.com.mylibrary.utils.LogUtils;
import com.liliuhuan.com.mylibrary.utils.UIUtil;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

/**
 * 自定义加载进度条
 * 
 * @author nanfeifei
 * @since 2016年4月1日上午11:44:07
 * @version 2.7
 */
public class WaitProgressBar extends RelativeLayout{

	/**
	 * 等待界面的背景色
	 */
	private int backgroundColor; 
	private LayoutInflater mInflater;
	private Context mContext;
	private ObjectAnimator animator;  //淡入动画
	private ObjectAnimator animator2;  //淡出动画
	private TextView mTextView;

	public WaitProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public WaitProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WaitProgressBar(Context context) {
		super(context);
		init(context);
	}
	private void init(Context context){
		mContext = context;
		backgroundColor = R.color.transparency;
		this.setBackgroundColor(context.getResources().getColor(backgroundColor));
		this.setVisibility(View.VISIBLE);
		ViewHelper.setAlpha(WaitProgressBar.this, 1.0f);
//		initAnimator();
		mInflater = LayoutInflater.from(context);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		addProgressBar(context);
		
	}
	/**
	 * 
	 * 将ProgressBar添加到当前View
	 * void
	 */
	 private void addProgressBar(Context context){
				ProgressBar progressBar = new ProgressBar(context,null
						, android.R.attr.progressBarStyle);
//		ProgressBar progressBar = new ProgressBar(context);
//		progressBar.setIndeterminateDrawable(getResources()
//				.getDrawable(R.drawable.base_loading_large_anim));
//		progressBar.setIndeterminate(false);
//		Interpolator _Interpolator = new LinearInterpolator();
//		progressBar.setInterpolator(_Interpolator);
		 progressBar.setIndeterminate(false);
		Drawable drawable = context.getResources().getDrawable(R.drawable.circle_progressbar_style);
		progressBar.setIndeterminateDrawable(drawable);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
				(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		int margin = UIUtil.dip2px( 18);
		params.setMargins(margin, UIUtil.dip2px( 5), margin, UIUtil.dip2px( 5));
		 mTextView = new TextView(context);
		LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams
				(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		tvParams.bottomMargin = UIUtil.dip2px( 5);
		tvParams.gravity = Gravity.CENTER_HORIZONTAL;
		 mTextView.setLayoutParams(tvParams);
		 mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
		 mTextView.setText("加载中...");
		 mTextView.setTextSize(14);
		 mTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
		LinearLayout linearLayout = new LinearLayout(context);
		LayoutParams rlParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		tvParams.gravity = Gravity.CENTER_HORIZONTAL;
		rlParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		linearLayout.setLayoutParams(rlParams);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setBackgroundResource(R.drawable.rectangle_round_corner6_progress_bg);
		linearLayout.addView(progressBar, params);
		linearLayout.addView(mTextView, tvParams);
		this.addView(linearLayout, rlParams);
	}
	/**
	 * 初始化淡入淡出动画
	 * 
	 * void
	 */
	public void initAnimator(){
		animator = ObjectAnimator.ofFloat(this, "alpha", 0.0f,1.0f);
		animator.setDuration(100);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				// TODO Auto-generated method stub
			}
		});
		animator2 = ObjectAnimator.ofFloat(this, "alpha", 1.0f,0.0f);
//		animator2.setStartDelay(1000);
		animator2.setDuration(300);
		animator2.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				// TODO Auto-generated method stub
				float value = (Float) animator.getAnimatedValue();
				
				if(value<0.000001f){
					LogUtils.i("alpha", ":"+value);
					WaitProgressBar.this.setVisibility(View.GONE);
				}
			}
		});
	}
	/**
	 * 显示当前View
	 * void
	 */
	public void show(){
		WaitProgressBar.this.setVisibility(View.VISIBLE);
//		ViewHelper.setAlpha(WaitProgressBar.this, 0.0f);
//
//		animator.start();
	}
	/**
	 * 隐藏当前View
	 * void
	 */
	public void hide(){
		WaitProgressBar.this.setVisibility(View.GONE);
//		if(WaitProgressBar.this.getVisibility() == View.VISIBLE){
//			animator2.start();
//		}
		
	}
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		if(animator!=null&&animator.isStarted()){
			animator.end();
		}
		if(animator2!=null&&animator.isStarted()){
			animator2.end();
		}
		super.onVisibilityChanged(changedView, visibility);
	}
	/**
	 * 更改等待页面的背景，默认为透明
	 */
	public void setBgColor(int backgroundColor) {
		this.setBackgroundColor(backgroundColor);
	};
	public void setContentText(String content) {
		if (mTextView!= null)
		mTextView.setText(content);
	};

}
