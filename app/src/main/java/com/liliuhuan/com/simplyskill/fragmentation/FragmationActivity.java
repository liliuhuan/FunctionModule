package com.liliuhuan.com.simplyskill.fragmentation;

import android.os.Bundle;

import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.fragmentation.fragment.HomeFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class FragmationActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmation);
        if (findFragment(HomeFragment.class) == null){
            loadRootFragment(R.id.frame_layout,HomeFragment.newInstance());
        }
    }
}
