package com.liliuhuan.com.simplyskill.menupop;

import android.view.View;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.menupop.entity.DataEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity implements SelectMenuView.OnMenuSelectDataChangedListener {
    SelectMenuView llSelect;
    TextView tvShow;
    private List<DataEntity> mGroupList;
    private List<DataEntity> mPrimaryList;
    private List<DataEntity> mJuniorList;
    private List<DataEntity> mHighList;
    private List<List<DataEntity>> mSubjectDataList;
    private List<String> cityDatas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    public void initView() {
        tvShow = (TextView) findViewById(R.id.tv_show);
        llSelect = (SelectMenuView) findViewById(R.id.ll_select);
        llSelect.setOnMenuSelectDataChangedListener(this);

        mGroupList = new ArrayList<>();
        mGroupList.add(new DataEntity(1, "A"));
        mGroupList.add(new DataEntity(2, "B"));
        mGroupList.add(new DataEntity(3, "C"));
        mPrimaryList = new ArrayList<>();
        mPrimaryList.add(new DataEntity(1, "A1"));
        mPrimaryList.add(new DataEntity(1, "A2"));
        mPrimaryList.add(new DataEntity(1, "A3"));
        mJuniorList = new ArrayList<>();
        mJuniorList.add(new DataEntity(1, "B1"));
        mJuniorList.add(new DataEntity(1, "B2"));
        mJuniorList.add(new DataEntity(1, "B3"));
        mJuniorList.add(new DataEntity(1, "B4"));
        mJuniorList.add(new DataEntity(1, "B5"));
        mJuniorList.add(new DataEntity(1, "B6"));
        mJuniorList.add(new DataEntity(1, "B7"));

        mHighList = new ArrayList<>();
        mHighList.add(new DataEntity(1, "C1"));
        mHighList.add(new DataEntity(1, "C2"));
        mHighList.add(new DataEntity(1, "C3"));
        mHighList.add(new DataEntity(1, "C4"));
        mHighList.add(new DataEntity(1, "C5"));
        mHighList.add(new DataEntity(1, "C6"));
        mHighList.add(new DataEntity(1, "C7"));
        mHighList.add(new DataEntity(1, "C8"));
        mHighList.add(new DataEntity(1, "C9"));


        mSubjectDataList = new ArrayList<List<DataEntity>>();
        mSubjectDataList.add(mGroupList);
        mSubjectDataList.add(mPrimaryList);
        mSubjectDataList.add(mJuniorList);
        mSubjectDataList.add(mHighList);
        cityDatas = new ArrayList<String>();
        cityDatas.add("a1");
        cityDatas.add("a2");
        cityDatas.add("a3");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        cityDatas.add("a4");
        llSelect.setData(mSubjectDataList);
        llSelect.setCityData(cityDatas);
        List<String> yearDatas = new ArrayList<>();
        yearDatas.add("全部年龄");
        yearDatas.add("1-3岁");
        yearDatas.add("3-6岁");
        yearDatas.add("6-12岁");
        yearDatas.add("12岁以上");
        llSelect.setYearData(yearDatas);
    }

    @Override
    public void onSubjectChanged(String grade, String subjects) {
        tvShow.setText("left-pos----" + grade + "----right-pos" + subjects + "\n");
    }

    @Override
    public void onSubjectChangedData(DataEntity dataEntity) {
        tvShow.append("right---id==" + dataEntity.id + "name==" + dataEntity.name);
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
