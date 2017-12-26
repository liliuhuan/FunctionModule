package com.liliuhuan.a50aftercharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Author :李刘欢
 * DATA : 2017-12-18
 * DES :
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ToolbarHelper mToolbarHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        if (toolBar != null){
            setSupportActionBar(toolBar);
            // 默认不显示原生标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbarHelper = new ToolbarHelper(toolBar);

            handleToolbar(mToolbarHelper);
        }
        initView();
    }

    protected void initView() {
    }

    protected abstract int getLayoutId() ;
    protected void handleToolbar(ToolbarHelper toolbarHelper) {}

    public static class ToolbarHelper {

        private Toolbar mToolbar;

        public ToolbarHelper(Toolbar toolbar) {
            this.mToolbar = toolbar;
        }

        public Toolbar getToolbar() {
            return mToolbar;
        }

        public void setTitle(String title) {
            TextView titleTV = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            titleTV.setText(title);
        }
    }
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null && fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
