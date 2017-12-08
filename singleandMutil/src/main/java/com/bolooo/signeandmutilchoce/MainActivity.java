package com.bolooo.signeandmutilchoce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MultipleChoiceAdapter.IOnSelectChangeDataLisenter {

    private ListView listView;
    private TextView textView;
    SingleChoiceAdapter singleChoiceAdapter ;
    MultipleChoiceAdapter multipleChoiceAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview);
        multipleChoiceAdapter = new MultipleChoiceAdapter(this,4);
       // singleChoiceAdapter = new SingleChoiceAdapter(this);
        listView.setAdapter(multipleChoiceAdapter);
        loadData();
        multipleChoiceAdapter.setOnSelectChangeData(this);
    }

    private void loadData() {
        List<DataBean> mData= new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            DataBean dataBean = new DataBean();
            dataBean.name = "第"+i+"课";
            dataBean.isSelect = false ;
            mData.add(dataBean);
        }
        multipleChoiceAdapter.setData(mData);
    }

    @Override
    public void OnSelectChangeData(String str) {
        textView.setText(str);
    }

}
