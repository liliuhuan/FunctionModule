package com.liliuhuan.com.mylibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liliuhuan.com.mylibrary.event.InitEvent;
import com.liliuhuan.com.mylibrary.utils.glide.GlideUtils;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Guojunhong on 2017/2/20.
 */

public abstract class BaseFragment extends Fragment {
  public Activity activity;
  public GlideUtils glideUtils;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity = getActivity();
    //ToastUtils.showToast("onCreate");
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = initView(inflater, container);
    ButterKnife.bind(this, view);
    EventBus.getDefault().register(this);
    //ToastUtils.showToast("onCreateView");
    return view;
  }

  public abstract View initView(LayoutInflater inflater, ViewGroup container);

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
   // ToastUtils.showToast("onActivityCreated");
    glideUtils = new GlideUtils(this.getContext());
    initView();
    initData();
    prepareData();
  }

  public void initView() {
  }

  public void initData() {
  }

  /**
   * 描述：获取数据源（网络请求或者数据库读取）
   */
  protected void prepareData() {
  }
  public void onEventMainThread(InitEvent event) {
  }
  @Override
  public void onResume() {
    super.onResume();
   // MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面
  }
  @Override
  public void onPause() {
    super.onPause();
  //  MobclickAgent.onPageEnd(this.getClass().getSimpleName());
  }
  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    EventBus.getDefault().unregister(this);
  }

}
