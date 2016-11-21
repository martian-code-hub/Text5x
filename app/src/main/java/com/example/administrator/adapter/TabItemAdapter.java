package com.example.administrator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.fragment.TabItemFragment;

import java.util.ArrayList;

/**
 * Created by yangpei on 2016/11/16.
 */

public class TabItemAdapter extends FragmentPagerAdapter {

    private ArrayList<TabItemFragment> list;
    private ArrayList<String> titles;

    public TabItemAdapter(FragmentManager fm,ArrayList<TabItemFragment> list,ArrayList<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
