package com.liliuhuan.a50aftercharacter.android5;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.liliuhuan.a50aftercharacter.BaseActivity;
import com.liliuhuan.a50aftercharacter.R;

public class EvelvationActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evelvation;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("evelvation");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        TextView tvClip = (TextView) findViewById(R.id.tv_clip);
        //剪裁，可以使用View.setClipToOutline方法去剪切一个视图的outline区域。只支持对矩形，圆形，圆角矩形的裁剪:
        tvClip.setClipToOutline(true);
        tvClip.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                int leftMargin = 10;
                int topMargin = 10;
                Rect selfRect = new Rect(leftMargin, topMargin,
                        rect.right - rect.left - leftMargin, rect.bottom - rect.top - topMargin);
                outline.setRoundRect(selfRect, 50);
            }
        });
    }
}