package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.martian.R;
import com.example.martian.adapter.NestRecycleViewAdapter;
import com.example.martian.adapter.RecycleViewRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author martian on 2017/3/8.
 */



public class NestRecycleViewActivity extends AppCompatActivity {


    private String mTitle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
        mTitle = getIntent().getExtras().getString("title");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (flag) {
                case 0:
                    getWindow().setEnterTransition(new Explode());
                    break;
                case 1:
                    getWindow().setEnterTransition(new Slide());
                    break;
                case 2:
                    getWindow().setEnterTransition(new Fade());
                    break;
            }
        }
        setContentView(R.layout.activity_nestrecycleview);

        iniView();
    }

    private void iniView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle(mTitle);
//        toolbar.setSubtitle("toolbar");
//        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
        RecyclerView rv = (RecyclerView) findViewById(R.id.activity_nrv_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecycleViewRecycleViewAdapter(getData()));
    }

    private List<String> getData(){
        List<String> list = new ArrayList<>();
        for (int i= 0;i<10;i++){
            list.add("item:"+i);
        }
        return list;
    }
}
