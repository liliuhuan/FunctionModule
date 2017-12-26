package com.liliuhuan.a50aftercharacter.android5;

import com.liliuhuan.a50aftercharacter.BaseActivity;
import com.liliuhuan.a50aftercharacter.R;

public class RippleActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ripple;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("ripple");
        //toolbarHelper.getToolbar().setNavigationIcon(null);
    }
}