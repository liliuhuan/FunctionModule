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

public class SecondFragment extends SupportFragment {

    public static final String KEY_RESULT_TITLE = "change_content";
    private static final int REQ_MODIFY_FRAGMENT = 0;
    private TextView tv;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
        view.findViewById(R.id.start_for_result_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult(ForResultFragment.newInstance(),REQ_MODIFY_FRAGMENT);
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            String mTitle = data.getString(KEY_RESULT_TITLE);
            tv.setText(mTitle);
        }
    }
}
