package com.liliuhuan.com.mylibrary.loading;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.liliuhuan.com.mylibrary.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/26.
 */
public abstract class BaseLoadingActivity extends FragmentActivity{

    //标题控件
    public FrameLayout fl_title_layout;
    private ImageView leftButton;
    private TextView titleButton;
    private ImageView rightButton;
    private TextView tv_right_text_button;

    private boolean unTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentValue();
        if(unTitle){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        showContentView();
    }

    public abstract void showContentView() ;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if(!unTitle)customTitle();
        ButterKnife.bind(this);
    }
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        if(!unTitle)customTitle();
//        ButterKnife.bind(this);
    }
    private void customTitle() {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        leftButton = (ImageView)findViewById(R.id.iv_back);
        titleButton = (TextView)findViewById(R.id.tv_title);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected abstract void initIntentValue();

    public FrameLayout getTitleLayout(){
        return fl_title_layout;
    }

    public ImageView getLeftButton() {
        return leftButton;
    }

    public TextView getTitleButton() {
        return titleButton;
    }

    public ImageView getRightButton() {
        return rightButton;
    }
    public TextView getRightTextButton(){
        return tv_right_text_button;
    }

    @Override
    public void setTitle(CharSequence title) {
        if(null!=getTitleButton())getTitleButton().setText(title);
    }


    public boolean isUnTitle() {
        return unTitle;
    }

    public void setUnTitle(boolean unTitle) {
        this.unTitle = unTitle;
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    /**
     * 检查list数据
     * @param data
     * @return
     */
    protected LoadingPage.LoadResult checkResult(Object data) {
        if(null == data)return LoadingPage.LoadResult.ERROR;
        if(data instanceof List){
            List mData = (List)data;
            if(mData.size()==0)return LoadingPage.LoadResult.EMPTY;
        }
        return LoadingPage.LoadResult.SUCCESS;
    }

}
