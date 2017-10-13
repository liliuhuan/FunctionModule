package com.liliuhuan.com.simplyskill.fragmentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-09-30
 * DES : ${}
 * =======================================
 */

public class CyclyFragment extends SupportFragment{
    private int pos;

    public static CyclyFragment newInstance(int pos) {
        CyclyFragment cyclyFragment = new CyclyFragment();
        Bundle args = new Bundle();
        args.putInt("pos",pos);
        cyclyFragment.setArguments(args);
        return cyclyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            pos = bundle.getInt("pos");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clcly, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        TextView tvContent = (TextView) view.findViewById(R.id.textView2);
        tvContent.setText("第"+pos+"-----fragment");

        view.findViewById(R.id.create_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(CyclyFragment.newInstance(pos+1));
            }
        });
        view.findViewById(R.id.create_new_close_old).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWithPop(CyclyFragment.newInstance(pos+1));
            }
        });
    }
}
