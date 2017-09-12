package com.liliuhuan.com.simplyskill.anime.qqanime;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.WindowManager;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;

public class BalloolActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    @BindView(R.id.videoView)
    CustomVideoView mVideoView;
    @BindView(R.id.balloonRelativeLayout)
    BalloonRelativeLayout balloonRelativeLayout;
    private int TIME = 100;//这里默认每隔100毫秒添加一个气泡
    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                mHandler.postDelayed(this, TIME);
                balloonRelativeLayout.addBalloon();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_ballool;
    }

    @Override
    public void initView() {
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mqr));
        //设置相关的监听
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //开始播放
        mVideoView.start();
        mHandler.postDelayed(runnable, TIME);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //开始播放
        mVideoView.start();
    }
}
