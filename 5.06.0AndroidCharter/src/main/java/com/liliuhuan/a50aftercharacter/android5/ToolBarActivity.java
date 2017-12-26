package com.liliuhuan.a50aftercharacter.android5;

import com.liliuhuan.a50aftercharacter.BaseActivity;
import com.liliuhuan.a50aftercharacter.R;

public class ToolBarActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tool_bar;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("tool_bar");
        //toolbarHelper.getToolbar().setNavigationIcon(null);
    }
}
