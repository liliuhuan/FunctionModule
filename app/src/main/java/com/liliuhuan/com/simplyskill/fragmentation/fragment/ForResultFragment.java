package com.liliuhuan.com.simplyskill.fragmentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.liliuhuan.com.simplyskill.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-09-30
 * DES : ${}
 * =======================================
 */

public class ForResultFragment extends SupportFragment {
    public static ForResultFragment newInstance() {
        return new ForResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_for_result_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final EditText et = (EditText) view.findViewById(R.id.textView);
        view.findViewById(R.id.notify_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(SecondFragment.KEY_RESULT_TITLE, et.getText().toString());
                setFragmentResult(RESULT_OK, bundle);
            }
        });
        view.findViewById(R.id.create_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               start(CyclyFragment.newInstance(0));
            }
        });
//        view.findViewById(R.id.create_new_close_old).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startWithPop(CyclyFragment.newInstance(1));
//            }
//        });
    }
}
