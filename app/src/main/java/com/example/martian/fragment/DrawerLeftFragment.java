package com.example.martian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.martian.adapter.HomeAdapter;
import com.example.martian.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class DrawerLeftFragment extends Fragment implements HomeAdapter.OnItemClickListener {


    private View view;

    private List<String> mDatas;
    private HomeAdapter mAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drawerleft, null);
        initView();
        return view;
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_drawerleft_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDatas = Arrays.asList(getResources().getStringArray(R.array.drawer_left));
        mAdapter = new HomeAdapter(mDatas);
        mAdapter.setOnItemClickListener((HomeAdapter.OnItemClickListener) this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), mDatas.get(position), Toast.LENGTH_SHORT).show();
        if (mMenuItemSelectedListener != null) {
            mMenuItemSelectedListener.menuItemSelected(mDatas.get(position));
        }
    }

    //选择回调的接口
    public interface OnMenuItemSelectedListener {
        void menuItemSelected(String title);
    }

    private OnMenuItemSelectedListener mMenuItemSelectedListener;

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener menuItemSelectedListener) {
        this.mMenuItemSelectedListener = menuItemSelectedListener;
    }
}
