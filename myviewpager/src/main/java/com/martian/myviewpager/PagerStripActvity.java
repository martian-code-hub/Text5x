package com.martian.myviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangpei on 2017/2/8.
 */

public class PagerStripActvity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] datas = {R.color.one,R.color.two,R.color.three,R.color.four};
    private String[] titles = {"第一页","第二页","第三页","第四页"};
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagerstrip);
        initView();
    }

    private void initView() {
        for (int i = 0;i<datas.length;i++){
            View view = new View(this);
            view.setBackgroundColor(this.getResources().getColor(datas[i]));
            views.add(view);
        }

        ViewPager vp_title = (ViewPager) findViewById(R.id.vp_title);
        vp_title.setAdapter(new MyPagerAdapter());
        ViewPager vp_tab = (ViewPager) findViewById(R.id.vp_tab);
        vp_tab.setAdapter(new MyPagerAdapter());
        vp_tab.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
