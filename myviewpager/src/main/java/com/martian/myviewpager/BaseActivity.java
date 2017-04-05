package com.martian.myviewpager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangpei on 2017/2/7.
 */
public class BaseActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] datas = {R.color.one,R.color.two,R.color.three,R.color.four};
    private String[] titles = {"第一页","第二页","第三页","第四页"};
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView() {
        ViewPager vp_base = (ViewPager) findViewById(R.id.vp_base);
        for (int i = 0;i<datas.length;i++){
            View view = new View(this);
            view.setBackgroundColor(this.getResources().getColor(datas[i]));
            views.add(view);
        }
        vp_base.setAdapter(new MyPagerAdapter());
        vp_base.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this,titles[position] , Toast.LENGTH_SHORT).show();
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
    }
}
