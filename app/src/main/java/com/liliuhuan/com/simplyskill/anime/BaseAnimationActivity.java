package com.liliuhuan.com.simplyskill.anime;

import android.view.View;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.anime.qqanime.BalloolActivity;
import com.liliuhuan.com.simplyskill.utils.IntentUtil;

import butterknife.OnClick;

public class BaseAnimationActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_base_animation;
    }


    @OnClick({R.id.button, R.id.button4, R.id.button5, R.id.button6, R.id.button7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                IntentUtil.startActivity(this, AnimetionActivity.class);
                break;
            case R.id.button4:
                IntentUtil.startActivity(this, AnimationListActivity.class);
                break;
            case R.id.button5:
                IntentUtil.startActivity(this, PropertyAnimationActivity.class);
                break;
            case R.id.button6:
                IntentUtil.startActivity(this, BalloolActivity.class);
                break;
            case R.id.button7:
                break;
        }
    }
}
