package com.liliuhuan.com.simplyskill.menupop.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.menupop.entity.DataEntity;

import java.util.List;

/**
 *
 * 综合排序
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class CityHolder extends BaseWidgetHolder<List<String>>{

    private ImageView mRecordImageView;

    private ListView mListView;
    private List<String> mDataList;
    private int mRightSelectedIndex;
    private RightAdapter mRightAdapter;
    /** 记录右侧条目对勾位置 */
    private ImageView mRightRecordImageView = null;
    private OnCityListViewItemSelectedListener mOnCityListViewItemSelectedListener;

    public CityHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.layout_holder_city, null);
        mListView = (ListView) view.findViewById(R.id.listView1);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRightSelectedIndex = position;
                ImageView imageView = (ImageView) view.findViewById(R.id.list2_right);

                if(mRightRecordImageView != null) {
                    mRightRecordImageView.setVisibility(View.INVISIBLE);
                }

                imageView.setVisibility(View.VISIBLE);

                mRightRecordImageView = imageView;

                if(mOnCityListViewItemSelectedListener != null){
                    String cityName = mDataList.get(mRightSelectedIndex);
                    mOnCityListViewItemSelectedListener.OnCityListViewItemSelected(mRightSelectedIndex,cityName);
                }
            }
        });
        return view;
    }

    @Override
    public void refreshView(List<String> data) {
    }
    public void refreshData(List<String> data, int rightSelectedIndex){

        this.mDataList = data;

        mRightSelectedIndex = rightSelectedIndex;


        //mRightSelectedIndexRecord = mRightSelectedIndex;

        mRightAdapter = new RightAdapter(data,mRightSelectedIndex);

        mListView.setAdapter(mRightAdapter);
    }

    private class RightAdapter extends BaseAdapter {

        private List<String> mRightDataList;

        public RightAdapter(List<String> list, int rightSelectedIndex){
            this.mRightDataList = list;
            mRightSelectedIndex = rightSelectedIndex;
        }

        public void setDataList(List<String> list, int rightSelectedIndex){
            this.mRightDataList = list;
            mRightSelectedIndex = rightSelectedIndex;
        }

        @Override
        public int getCount() {
            return mRightDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mRightDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            RightViewHolder holder;
            if(convertView == null){
                holder = new RightViewHolder();
                convertView = View.inflate(mContext, R.layout.layout_child_menu_item, null);
                holder.rightText = (TextView) convertView.findViewById(R.id.child_textView);
                holder.selectedImage = (ImageView)convertView.findViewById(R.id.list2_right);
                convertView.setTag(holder);
            }else{
                holder = (RightViewHolder) convertView.getTag();
            }

            holder.rightText.setText(mRightDataList.get(position));
            if(mRightSelectedIndex == position){
                holder.selectedImage.setVisibility(View.VISIBLE);
                mRightRecordImageView = holder.selectedImage;
            }else{
                holder.selectedImage.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }
    }
    private static class RightViewHolder{
        TextView rightText;
        ImageView selectedImage;
    }
    public void setOnCityListViewItemSelectedListener(OnCityListViewItemSelectedListener onRightListViewItemSelectedListener){
        this.mOnCityListViewItemSelectedListener = onRightListViewItemSelectedListener;
    }

    public interface OnCityListViewItemSelectedListener{
        void OnCityListViewItemSelected(int Index, String text);
        void OnCityListViewSelectedData(DataEntity dataEntity);
    }
}
