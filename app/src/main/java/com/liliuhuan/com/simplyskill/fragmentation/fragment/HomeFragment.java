package com.liliuhuan.com.simplyskill.fragmentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.liliuhuan.com.mylibrary.utils.ToastUtils;
import com.liliuhuan.com.simplyskill.R;

import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-09-30
 * DES : ${}
 * =======================================
 */

public class HomeFragment extends SupportFragment {
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ISupportActivity) _mActivity).setFragmentAnimator(new DefaultHorizontalAnimator());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Button tv = (Button) view.findViewById(R.id.btn_second);
        tv.setOnClickListener(view1 -> start(SecondFragment.newInstance()));

        view.findViewById(R.id.btn_databing).setOnClickListener(v->{
            //_mActivity.startActivity(new Intent(_mActivity, DataBindingActivity.class));
            ToastUtils.showToast("敬请期待");
        });
    }
}
