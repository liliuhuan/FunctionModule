package com.liliuhuan.com.simplyskill.savedata;

import android.view.View;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.savedata.sqlite.SqliteActivity;
import com.liliuhuan.com.simplyskill.utils.IntentUtil;

import butterknife.OnClick;

public class SaveDataActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_save_data;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                IntentUtil.startActivity(this,SharedPreferencesActivity.class);
                break;
            case R.id.button2:
                IntentUtil.startActivity(this,SqliteActivity.class);
                break;
            case R.id.button3:
                IntentUtil.startActivity(this,SharedPreferencesActivity.class);
                break;
            case R.id.button4:
                IntentUtil.startActivity(this,SharedPreferencesActivity.class);
                break;
            case R.id.button5:
                IntentUtil.startActivity(this,SharedPreferencesActivity.class);
                break;
        }
    }
}
