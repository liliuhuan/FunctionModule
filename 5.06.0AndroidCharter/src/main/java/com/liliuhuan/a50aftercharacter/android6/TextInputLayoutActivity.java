package com.liliuhuan.a50aftercharacter.android6;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.liliuhuan.a50aftercharacter.BaseActivity;
import com.liliuhuan.a50aftercharacter.R;

public class TextInputLayoutActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_input_layout;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("title");
        //toolbarHelper.getToolbar().setNavigationIcon(null);
    }

    @Override
    protected void initView() {
        if(Build.VERSION.SDK_INT> 21){
            //使用新动画
        }
        final TextInputLayout textLayout = (TextInputLayout) findViewById(R.id.text_layout);
        EditText editText = textLayout.getEditText();
        //editText.setHint("你好，");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!= null){
                    String s1 = s.toString();
                    if (s1.length() > 5){
                        textLayout.setError("最多输入五个字符");
                        textLayout.setErrorEnabled(true);
                    }
                }
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.float_bar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,getString(R.string.show_snack_message),Snackbar.LENGTH_LONG).show();
            }
        });



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        //给ViewPager填充数据
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //关联TabLayout和ViewPager
        tabLayout.setupWithViewPager(viewpager);


    }

    private class MyAdapter extends FragmentPagerAdapter {
        String[] title = {"fragment1","fragment2","fragment3"};
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}