package com.liliuhuan.com.mylibrary.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.liliuhuan.com.mylibrary.R;
import com.liliuhuan.com.mylibrary.manager.ThreadManager;
import com.liliuhuan.com.mylibrary.utils.UIUtil;


/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 16-3-24.
 */
public abstract class LoadingPage extends FrameLayout implements View.OnClickListener {

    public static final int STATE_UNLOADING = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;

    View mErrorView;
    View mLoadingView;
    View mEmptyView;
    View mSuccessView;
    int mState;


    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mState = STATE_UNLOADING;
        mErrorView = createErrorView();
        if(null!=mErrorView){
            addView(mErrorView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            mErrorView.findViewById(R.id.rl_error_layout).setOnClickListener(this);
        }

        mEmptyView = createEmptyView();
        if(null != mEmptyView){
            addView(mEmptyView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mLoadingView = createLoadingView();
        if(null != mLoadingView){
            addView(mLoadingView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        showLoadingViewSafe();
    }

    private void showLoadingViewSafe() {
        UIUtil.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        if(null != mLoadingView){
            mLoadingView.setVisibility(mState==STATE_UNLOADING || mState==STATE_LOADING?VISIBLE:INVISIBLE);
        }

        if(null != mErrorView){
            mErrorView.setVisibility(mState==STATE_ERROR?VISIBLE:INVISIBLE);
        }

        if(null != mEmptyView){
            mEmptyView.setVisibility(mState == STATE_EMPTY ? VISIBLE : INVISIBLE);
        }

        if(null == mSuccessView && mState == STATE_SUCCESS){
            mSuccessView = createSuccessView();
            addView(mSuccessView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        if(null != mSuccessView){
            mSuccessView.setVisibility(mState == STATE_SUCCESS ? VISIBLE : INVISIBLE);
        }
    }

    private View createLoadingView() {
        return UIUtil.inflate(R.layout.loading_page_loading);
    }

    private View createEmptyView() {return UIUtil.inflate(R.layout.loading_page_empty);}

    private View createErrorView() {
        return UIUtil.inflate(R.layout.loading_page_error);
    }

    public void show(){
        if(mState == STATE_ERROR || mState == STATE_EMPTY){
            mState = STATE_UNLOADING;
        }
        if(mState == STATE_UNLOADING){
            mState = STATE_LOADING;
            TaskRunnable task = new TaskRunnable();
            ThreadManager.getLongPool().execute(task);
        }
        showPage();
    }

    public abstract View createSuccessView();

    @Override
    public void onClick(View v) {
        show();
    }

    public class TaskRunnable implements Runnable {

        @Override
        public void run() {
            final  LoadResult result = load();
            UIUtil.runInMainThread(new Runnable() {
                @Override
                public void run() {
                   mState = result.getValue();
                   showPage();
                }
            });
        }
    }

    protected abstract LoadResult load();

    public enum LoadResult{
        ERROR(2),EMPTY(3),SUCCESS(4);
        int value;
        LoadResult(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

}
