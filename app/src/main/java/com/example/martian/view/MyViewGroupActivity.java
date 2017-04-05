package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.martian.R;

public class MyViewGroupActivity extends AppCompatActivity {

    private String mTitle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
        mTitle = getIntent().getExtras().getString("title");
        setContentView(R.layout.activity_my_view_group);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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


        TextView textview_1 = (TextView) findViewById(R.id.textview_1);
        TextView textview_2 = (TextView) findViewById(R.id.textview_2);
        textview_2.setText("4分");
        textview_1.setText("这是用settext方法添加的内容:看看是否把textview推出屏幕！！！！！！！！！！");

    }

}
