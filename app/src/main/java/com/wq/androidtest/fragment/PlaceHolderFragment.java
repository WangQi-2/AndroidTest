package com.wq.androidtest.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.util.Constant;

/**
 * Created by wangqi on 15/11/13.
 */
public class PlaceHolderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int pos = getArguments().getInt(Constant.POSITION);
        String desc = getArguments().getString(Constant.DESCRIPTION);
        TextView textView = new TextView(getActivity());
        textView.setText(desc + " : " + pos);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(getActivity().getResources().getDimensionPixelSize(R.dimen.text_small));
        textView.setBackgroundColor(Constant.COLORS[pos % Constant.COLORS.length]);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}
