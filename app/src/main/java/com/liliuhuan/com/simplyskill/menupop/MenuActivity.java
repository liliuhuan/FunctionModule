package com.liliuhuan.com.simplyskill.menupop;

import android.view.View;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.menupop.entity.DataEntity;

public class MenuActivity extends BaseActivity implements SelectMenuView.OnMenuSelectDataChangedListener {
  SelectMenuView llSelect;
    TextView tvShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    public void initView() {
        tvShow = (TextView) findViewById(R.id.tv_show);
        llSelect = (SelectMenuView) findViewById(R.id.ll_select);
        llSelect.setOnMenuSelectDataChangedListener(this);
    }

    @Override
    public void onSubjectChanged(String grade, String subjects) {
        tvShow.setText("left-pos----"+grade+"----right-pos"+subjects+"\n");
    }

  @Override
  public void onSubjectChangedData(DataEntity dataEntity) {
    tvShow.append("right---id=="+dataEntity.id+"name=="+dataEntity.name);
  }

  @Override
    public void onSortChanged(String sortType) {

    }

    @Override
    public void onSelectedChanged(String gender, String classType) {

    }

    @Override
    public void onViewClicked(View view) {

    }

    @Override
    public void onSelectedDismissed(String gender, String classType) {

    }
}
