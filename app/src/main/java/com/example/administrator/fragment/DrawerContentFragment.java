package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.text5x.R;

/**
 * Created by Administrator on 2016/7/7.
 */
public class DrawerContentFragment extends Fragment {


    private View view;

    public static DrawerContentFragment newInstance(String title) {
        DrawerContentFragment fragment = new DrawerContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drawercontent, null);
        initView();
        return view;
    }

    private void initView() {
        String title = (String) getArguments().get("title");
        TextView textview = (TextView) view.findViewById(R.id.fragment_drawercontent_tv);
        textview.setText(title);
    }
}
