package com.liliuhuan.com.mylibrary.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liliuhuan.com.mylibrary.utils.glide.GlideUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 封装adapter（注意：仅供参考，根据需要选择使用demo中提供的封装adapter）
 * @param <T>
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    protected Context mContext;
    private LayoutInflater mInflater;

    protected List<T> mDataList = new ArrayList<>();
    public GlideUtils glideUtils;

    public BaseRecycleViewAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        glideUtils = new GlideUtils(context);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(getConvertViewId(viewType), parent, false);
        BaseRecycleViewHolder<T> holder = getNewHolder(itemView);
        itemView.setTag(holder);
        return holder;
    }
    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        holder.loadData(mDataList.get(position), position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }

    }

    public abstract int getConvertViewId(int viewTypeviewType);
    public abstract BaseRecycleViewHolder<T> getNewHolder(View view);


    public void onBindItemHolder(BaseRecycleViewHolder<T> holder, int position, List<Object> payloads){

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void setList(Collection<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(Collection<T> list) {
//        int lastIndex = this.mDataList.size();
//        if (this.mDataList.addAll(list)) {
//            notifyItemRangeInserted(lastIndex, list.size());
//        }

        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.mDataList.remove(position);
        notifyItemRemoved(position);

        if(position != (getDataList().size())){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position,this.mDataList.size()-position);
        }
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}
