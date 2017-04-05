package com.martian.myviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yangpei on 2017/2/8.
 */

public class MyPagerActvity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] datas = {R.color.one, R.color.two, R.color.three, R.color.four};
    private String[] titles = {"第一页", "第二页", "第三页", "第四页"};
    private int[] ids = {R.id.tab_0,R.id.tab_1,R.id.tab_2,R.id.tab_3};
    private List<View> views = new ArrayList<>();
    private ImageView vp_indicator;
    private ViewPager vp_title;

    private int deflutLength = 0;
    private int width = 0;
    private int q_width = 0;

    private float rawStart = 0;
    private float rawEnd = 0;

    private float start = 0;
    private float end = 0;

    private int tempindex = 0;
    private int index = 0;

    private int scollState = 0;

//    private boolean isScollling = false;
    private boolean isFlipOver = false;
    private boolean isClick = false;

    private int seclectedColor = Color.BLACK;
    private int noSeclectedColor = Color.BLACK;
    private int oreitation = 0;
    private  static final int ZERO = 0;
    private  static final int LEFT = -1;
    private  static final int RIGHT = 1;


    private float temp_start = 0;
    private float temp_end = 0;

    private android.widget.LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypagerstrip);
        initData();
        initView();
    }

    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        q_width = width / datas.length;
        deflutLength = q_width * 2 / 3;

        rawStart = start = (q_width - deflutLength) / 2;
        rawEnd = end = q_width - start;

        seclectedColor = getResources().getColor(R.color.colorAccent);
//        dm.density
    }

    private void initView() {
        for (int i = 0; i < datas.length; i++) {
            View view = new View(this);
            view.setBackgroundColor(this.getResources().getColor(datas[i]));
            views.add(view);
        }

         vp_title = (ViewPager) findViewById(R.id.vp_my);
        vp_title.setAdapter(new MyPagerAdapter());
        vp_indicator = (ImageView) findViewById(R.id.vp_indicator);

        lp = (LinearLayout.LayoutParams) vp_indicator.getLayoutParams();
        lp.leftMargin = (int) start;
        lp.width = deflutLength;
        lp.height = 2;
        vp_indicator.setLayoutParams(lp);

        vp_title.addOnPageChangeListener(this);
//        vp_indicator.layout(start,0,end,20);
//        vp_indicator.invalidate();
    }

    public void goTo(View view){
        isClick = true;
        int index = 0;
        for (int i = 0;i<ids.length;i++){
            TextView v = (TextView) findViewById(ids[i]);
            if(view == v){
                v.setTextColor(seclectedColor);
                index = i;
            }else{
                v.setTextColor(noSeclectedColor);
            }
        }
        vp_title.setCurrentItem(index);
    }

    private int getRandom(){
        return new Random().nextInt(3)%(3-0+1)+0;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("---", "---position---:" + position + "--positionOffset-:" + positionOffset + "--positionOffsetPixels-:" + positionOffsetPixels );
        if (scollState != ViewPager.SCROLL_STATE_IDLE) {
            if (positionOffset == 0 && positionOffsetPixels == 0) {
                oreitation = ZERO;
            } else {

                if (positionOffsetPixels > tempindex) {
                    //向右滑动

                    Log.d("---", "--向右滑动--:");
                    if(scollState == ViewPager.SCROLL_STATE_SETTLING&&!isFlipOver){
                        temp_start = start + q_width * (positionOffset - 1);
                        temp_end = end;
                    }else{
                        temp_start = start;
                        if(isClick){
                            temp_end = end + q_width * positionOffset*(index-((end-(q_width-rawStart))/q_width));
                        }else{
                            temp_end = end + q_width * positionOffset;
                        }
                    }


                } else if (positionOffsetPixels < tempindex) {
                    //向左滑动
                    Log.d("---", "--向左滑动--:");
                    if(scollState == ViewPager.SCROLL_STATE_SETTLING&&!isFlipOver){

                        temp_start = start;
                        temp_end = end + q_width * positionOffset;
                    }else{
                        if(isClick){
                            temp_start = start + q_width * (positionOffset - 1)*(((start-rawStart)/q_width)-index);
                        }else{
                            temp_start = start + q_width * (positionOffset - 1);
                        }
                        temp_end = end;
                    }
                }
                lp.leftMargin = (int) temp_start;
                lp.width = (int) (temp_end - temp_start);
                vp_indicator.setLayoutParams(lp);
                tempindex = positionOffsetPixels;
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        isFlipOver = true;
        Log.d("---", "--onPageSelected--position:"+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        scollState = state;
        if (scollState == ViewPager.SCROLL_STATE_IDLE) {
            isFlipOver = false;
            isClick = false;
            tempindex = 0;
            start = rawStart + index * q_width;
            end = start + deflutLength;
            lp.leftMargin = (int) start;
            lp.width = (int) (end - start);
            vp_indicator.setLayoutParams(lp);

            for (int i = 0;i<ids.length;i++){
                TextView v = (TextView) findViewById(ids[i]);
                v.setTextColor((index == i)?seclectedColor:noSeclectedColor);
            }
        }
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
