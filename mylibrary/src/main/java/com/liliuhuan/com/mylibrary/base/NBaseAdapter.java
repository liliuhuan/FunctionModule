/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liliuhuan.com.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.liliuhuan.com.mylibrary.utils.glide.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param <T>
 * @author nanfeifei
 * @since 2016年7月28日上午11:49:06
 * @version 1.0
 */
public abstract class NBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> itemList = new ArrayList<T>();
    public GlideUtils imageLoaderUtils;
    protected DisplayMetrics dm;
    public ViewGroup parent;
    protected View.OnClickListener listener;
    public NBaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        imageLoaderUtils = new GlideUtils(context);
        dm = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay()
		.getMetrics(dm);
    }

  /**
   * 设置点击监听
   * @param listener
   */
    public void setOnClickListener(View.OnClickListener listener){
      this.listener = listener;
    }
    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     *
     * @param itemList
     */
    public void setItems(List<T> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
    public List<T> getItems(){
      return itemList;
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        itemList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
    	if(itemList == null){
    		return 0;
    	}
        return itemList.size();
    }

    @Override
    public T getItem(int position) {
    	if(itemList == null){
    		return null;
    	}
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    abstract public View getView(int i, View view, ViewGroup viewGroup);
    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
    	BaseViewHolder<T> holder = null;
        parent = viewGroup;
    	if(view == null){
    		view = inflater.inflate(getConvertViewId(position), null);
            holder = getNewHolder(view);
    		view.setTag(holder);
    	}else{
    		holder = (BaseViewHolder<T>) view.getTag();
    	}
    	holder.loadData(getItem(position), position);
		return view;
    	
    };
    public abstract int getConvertViewId(int position);
    
    public abstract BaseViewHolder<T> getNewHolder(View view);
    
}
