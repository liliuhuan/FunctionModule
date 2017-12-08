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

import static android.R.attr.max;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-09-07
 * DES : ${}
 * =======================================
 */

public class MultipleChoiceAdapter extends BaseAdapter {
    private  Context context;
    private  LayoutInflater layoutInflater;
    private  List<DataBean> datas;
    IOnSelectChangeDataLisenter onSelectChangeDatas;
    private int maxSelectNumber;

    public void setOnSelectChangeData(IOnSelectChangeDataLisenter onSelectChangeData) {
        this.onSelectChangeDatas = onSelectChangeData;
    }

    public MultipleChoiceAdapter(Context context,int maxNumber) {
        this.maxSelectNumber = maxNumber ;
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
        if(datas == null){
            return null;
        }
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.item_choce, viewGroup, false);
        TextView textView = inflate.findViewById(R.id.textview);
        TextView tvSelectNumber = inflate.findViewById(R.id.tv_select_number);
        if (datas != null) textView.setText(datas.get(i).name);
        final  int finalI= i ;
        DataBean dataBean = datas.get(i);
        final boolean isSelect = dataBean.isSelect;
        int selectNumver = dataBean.selectNumver;
        if (isSelect){
            textView.setTextColor(context.getColor(R.color.colorAccent));
            tvSelectNumber.setVisibility(View.VISIBLE);
            tvSelectNumber.setText(String.valueOf(selectNumver));
        }else {
            tvSelectNumber.setVisibility(View.GONE);
            textView.setTextColor(context.getColor(R.color.colorPrimary));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,datas.get(finalI).name,Toast.LENGTH_LONG).show();
                putFinalSelect(finalI,isSelect,maxSelectNumber);
                String selectData = getAllSelectData();
                if (onSelectChangeDatas!= null){
                    onSelectChangeDatas.OnSelectChangeData(selectData);
                }
            }
        });

        return inflate;
    }

    private String getAllSelectData() {
        if (datas == null) return "";
        StringBuilder sb =new StringBuilder();
        sb.append("选中的课程：");
        for (int i = 0; i <datas.size() ; i++) {
              if (datas.get(i).isSelect){
                  sb.append(datas.get(i).name+"\t");
              }
        }
        if (sb== null)return "";
        return sb.toString();
    }
    private int getAllSelectDataNumber() {
        if (datas == null) return 0;
        int number = 0;
        for (int i = 0; i <datas.size() ; i++) {
            if (datas.get(i).isSelect){
               number ++;
            }
        }
        return number;
    }
    private void putFinalSelect(int finalI, boolean isSelect,int maxSelect) {
        if (datas == null) return;
        int allSelectDataNumber = getAllSelectDataNumber();
        if (allSelectDataNumber > maxSelect&& !isSelect){
            Toast.makeText(context,"最多只能"+(maxSelect+1)+"个",Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i <datas.size() ; i++) {
            if (i == finalI){
                datas.get(i).isSelect = isSelect?false:true ;
                datas.get(i).selectNumver = isSelect ? allSelectDataNumber-1:allSelectDataNumber+1;
            }
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

    public void setData(List<DataBean> mData) {
        this.datas = mData;
        notifyDataSetChanged();
    }
    interface IOnSelectChangeDataLisenter{
       void OnSelectChangeData(String str);
    }
}
