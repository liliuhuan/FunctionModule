package com.liliuhuan.com.simplyskill.savedata;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SharedPreferencesActivity extends BaseActivity {

    private static final String USER_ID =SharedPreferencesActivity.class.getSimpleName() ;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.show)
    TextView show;
    private SharedPreferences sharedPreferences;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shared_preferences;
    }

    @Override
    public void initView() {
        sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        
        switch (view.getId()) {
            case R.id.button1:
                String toString = et.getText().toString();
                if (TextUtils.isEmpty(toString))return;
                //实例化SharedPreferences.Editor对象
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //用putString的方法保存数据
                editor.putString(USER_ID, toString);
                //提交当前数据
                editor.apply();
                et.setText("");
                break;
            case R.id.button2:
                String userId=sharedPreferences.getString(USER_ID,"");
                show.setText(userId);
                break;
        }
    }
}
