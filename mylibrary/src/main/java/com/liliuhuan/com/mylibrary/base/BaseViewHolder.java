package com.liliuhuan.com.mylibrary.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Adapter的ViewHolder
 * @param <T>
 * @author nanfeifei
 * @since 2016年8月15日下午6:42:05
 * @version 1.0
 */
public abstract class BaseViewHolder<T> {
	public BaseViewHolder(View view){
		ButterKnife.bind(this, view);
	}

	/**
	 * 装载数据
	 * <功能详细描述>
	 * @param data
	 * @see [类、类#方法、类#成员]
	 */
	public abstract void loadData(T data,int position);
}
