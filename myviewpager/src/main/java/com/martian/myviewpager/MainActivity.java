package com.martian.myviewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<String> datas = Arrays.asList("Base", "PagerStrip",  "自定义Tab");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(llm);
        MyAdapter adapter = new MyAdapter(datas);
//        Log.d("---","--datas.size--"+datas.size());

        adapter.setListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String content) {
                switch (content) {
                    case "Base":
                        startActivity(new Intent(MainActivity.this,BaseActivity.class));
                        break;
                    case "PagerStrip":
                        startActivity(new Intent(MainActivity.this,PagerStripActvity.class));
                        break;
                    case "自定义Tab":
                        startActivity(new Intent(MainActivity.this,MyPagerActvity.class));
                        break;
                }
            }
        });
        recyclerview.setAdapter(adapter);

    }
}
