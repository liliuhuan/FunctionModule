package com.liliuhuan.a50aftercharacter.android6;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liliuhuan.a50aftercharacter.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private int pos;

    public static BlankFragment newInstance(int pos) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle= new Bundle();
        bundle.putInt("pos",pos);
        fragment.setArguments(bundle);
        return  fragment ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments!= null) {
            pos = arguments.getInt("pos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView textView = view.findViewById(R.id.tv_pos);
        textView.setText("Fragment"+pos);
        return view;
    }

}
