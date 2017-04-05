package com.example.martian.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.martian.R;
import com.example.martian.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements HomeAdapter.OnItemClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private static final String TOOLBAR = "ToolBar";
    private static final String ANIMATION = "View state change Animation";
    private static final String NOTIFICATION = "Notifition";
    private static final String SVG = "SVG";
    private static final String SURFACEVIEW = "SurfaceView";
    private static final String VIEWDRAGHELPER = "ViewDragHelper";
    private static final String COORDINATORLAYOUT = "CoordinatorLayout";
    private static final String RETROFIT = "Retrofit";
    private static final String GLIDE = "Glide";
    private static final String RXJAVA = "RxJava";
    private static final String BAR = "Bar";
    private static final String ARITHMETIC = "Arithmetic";
    private static final String AIDL = "Aidl";
    private static final String NDK = "Ndk";
    private static final String EVENTBUS = "EventBus";
    private static final String MVP = "Mvp";
    private static final String DAGGER2 = "Dagger2";
    private static final String VOLLEY = "Volley";
    private static final String OKHTTP = "OkHttp";
    private static final String LEAKCANARY = "LeakCanary";
    private static final String NESTRECYCLEVIEW = "NestRecycleView";
    private static final String MYVIEWGROUP = "MyViewGroup";


    private List<String> mDatas;
    private String[] data = {TOOLBAR,ANIMATION,NOTIFICATION,SVG,SURFACEVIEW,VIEWDRAGHELPER,COORDINATORLAYOUT,
            RETROFIT,GLIDE,RXJAVA,BAR,ARITHMETIC,AIDL,NDK,EVENTBUS,MVP,DAGGER2,VOLLEY,OKHTTP,LEAKCANARY,NESTRECYCLEVIEW,MYVIEWGROUP};
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniView();
        initData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
    private void iniView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        mDatas = new ArrayList<String>();
        mAdapter = new HomeAdapter(mDatas);
        mAdapter.setOnItemClickListener(this);
        recyclerview.setAdapter(mAdapter);
//        recyclerview.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));




    }
    protected void initData()
    {
                Collections.addAll(mDatas,data);
//        System.arraycopy();
//        mDatas
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }
        mAdapter.notifyDataSetChanged();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, mDatas.get(position), Toast.LENGTH_SHORT).show();
        String data =  mDatas.get(position);
        Intent intent = new Intent();
        switch (data){
            case TOOLBAR:
                intent.setClass(this,ToolBarActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title",TOOLBAR);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case ANIMATION:
                intent.setClass(this,AnimationActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title",ANIMATION);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case NOTIFICATION:
                intent.setClass(this,NotifitionActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", NOTIFICATION);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case SVG:
                intent.setClass(this,SVGActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", SVG);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case SURFACEVIEW:
                intent.setClass(this,SurfaceViewActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", SURFACEVIEW);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case VIEWDRAGHELPER:
                intent.setClass(this,ViewDragHelperActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title", VIEWDRAGHELPER);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;

            case COORDINATORLAYOUT:
                intent.setClass(this,CoordinatorLayoutActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", COORDINATORLAYOUT);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case RETROFIT:
                intent.setClass(this,RetrofitActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", RETROFIT);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case GLIDE:
                intent.setClass(this,GlideActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title", GLIDE);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case RXJAVA:
                intent.setClass(this,RxJavaActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", RXJAVA);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case BAR:
                intent.setClass(this,BarActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", BAR);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case ARITHMETIC:
                intent.setClass(this,ArithmeticActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title", ARITHMETIC);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case AIDL:
                intent.setClass(this,AidlActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", AIDL);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case NDK:
                intent.setClass(this,NdkActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", NDK);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case EVENTBUS:
                intent.setClass(this,EventBusActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", EVENTBUS);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case MVP:
                intent.setClass(this,MvpActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", MVP);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case DAGGER2:
                intent.setClass(this,Dagger2Activity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", DAGGER2);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case VOLLEY:
                intent.setClass(this,VolleyActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title", VOLLEY);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case OKHTTP:
                intent.setClass(this,OkHttpActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", OKHTTP);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;

            case LEAKCANARY:
                intent.setClass(this,LeakCanaryActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("title", LEAKCANARY);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case NESTRECYCLEVIEW:
                intent.setClass(this,NestRecycleViewActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("title", NESTRECYCLEVIEW);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case MYVIEWGROUP:
                intent.setClass(this,MyViewGroupActivity.class);
                intent.putExtra("flag",2);
                intent.putExtra("title", MYVIEWGROUP);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Android按返回键，程序进入后台运行，不关闭程序，finishAcrivity
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            moveTaskToBack(false);
        }
        return super.onKeyDown(keyCode, event);
    }
}
