package com.liliuhuan.com.simplyskill.savedata.sqlite;

import android.view.View;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SqliteActivity extends BaseActivity {

    @BindView(R.id.tvshow)
    TextView tvshow;
    private DBManager dbManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sqlite;
    }

    @Override
    public void initView() {
        dbManager = DBManager.getInstance(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                dbManager.insertData("hello world");
                break;
            case R.id.button2:
                dbManager.deleteData("hello world");
                break;
            case R.id.button3:
                dbManager.updateData("hello world");
                break;
            case R.id.button4:
                String queryDatas = dbManager.queryDatas();
                tvshow.setText(queryDatas);
                break;
        }
    }
}
