package com.liliuhuan.com.simplyskill.loading;

import android.os.Bundle;
import android.view.View;

import com.liliuhuan.com.mylibrary.loading.BaseLoadingActivity;
import com.liliuhuan.com.mylibrary.loading.LoadingPage;
import com.liliuhuan.com.mylibrary.utils.UIUtil;
import com.liliuhuan.com.simplyskill.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class TestLoadingActivity extends BaseLoadingActivity {
    List<String> mList;
//    @Bind(R.id.tv_test)
//    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_loading);
//        ButterKnife.bind(this);
    }

    @Override
    public void showContentView() {
        LoadingPage result = new LoadingPage(UIUtil.getContext()) {
            @Override
            public View createSuccessView() {
                return createView();
            }

            @Override
            protected LoadResult load() {
                return loadData();
            }
        };
        setContentView(result);
        result.show();
    }

    @Override
    protected void initIntentValue() {
        setUnTitle(true);
    }

    private LoadingPage.LoadResult loadData() {
        // mList = CommonApiUtil.getInstance().getSCList();
        mList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mList.add("a+" + i);
//        }
        return checkResult(mList);
    }

    private View createView() {
        View v = UIUtil.inflate(R.layout.activity_test_loading);
        ButterKnife.bind(this, v);
        return v;
    }
}
