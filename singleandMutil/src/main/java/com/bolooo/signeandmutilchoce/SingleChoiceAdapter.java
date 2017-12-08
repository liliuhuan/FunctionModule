package com.bolooo.signeandmutilchoce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-09-07
 * DES : ${}
 * =======================================
 */

public class SingleChoiceAdapter extends BaseAdapter {
    private  Context context;
    private  LayoutInflater layoutInflater;
    private  List<DataBean> datas;
    public SingleChoiceAdapter(Context context) {
        this.context = context ;
        datas = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas == null ?0 : datas.size();
    }

    @Override
    public DataBean getItem(int i) {
        return datas == null?null:datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.item_choce, viewGroup, false);
        TextView textView = inflate.findViewById(R.id.textview);
        if (datas != null) textView.setText(datas.get(i).name);
        final  int finalI= i ;
        DataBean dataBean = datas.get(i);
        final boolean isSelect = dataBean.isSelect;
        if (isSelect){
            textView.setTextColor(context.getColor(R.color.colorAccent));
        }else {
            textView.setTextColor(context.getColor(R.color.colorPrimary));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,datas.get(finalI).name,Toast.LENGTH_LONG).show();
                if (!isSelect) {
                    clearAllSelect();
                    putFinalSelect(finalI);
                }
                context.startActivity(new Intent(context,SecondActivity.class));
            }
        });

        return inflate;
    }

    private void putFinalSelect(int finalI) {
        if (datas == null) return;
        for (int i = 0; i <datas.size() ; i++) {
            if (i == finalI)datas.get(i).isSelect = true ;
        }
        notifyDataSetChanged();
    }

    private void clearAllSelect() {
        if (datas == null) return;
        for (int i = 0; i <datas.size() ; i++) {
            datas.get(i).isSelect = false;
        }
        notifyDataSetChanged();
    }

    public void setData(List<DataBean> mDatas) {
        this.datas = mDatas;
        notifyDataSetChanged();
    }
}
