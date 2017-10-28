package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.example.martian.R;
import com.example.martian.adapter.HomeAdapter;
import com.example.martian.widget.MyBottomBehavior;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author martian on 2017/8/7.
 */

public class BottomSheetActivity extends AppCompatActivity {

  private String mTitle;
  private Toolbar toolbar;
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
  private static final String AROUTER = "ARouter";
  private static final String VIEW = "View";
  private static final String RXCACHE = "RxCache";
  private static final String VIDEO = "Video";
  private static final String BOTTOMSHEET = "BottomSheet";

  private HomeAdapter mAdapter;
  private List<String> mDatas  = new ArrayList<String>();
  private String[] data = {TOOLBAR,ANIMATION,NOTIFICATION,SVG,SURFACEVIEW,VIEWDRAGHELPER,COORDINATORLAYOUT,
      RETROFIT,GLIDE,RXJAVA,BAR,ARITHMETIC,AIDL,NDK,EVENTBUS,MVP,DAGGER2,VOLLEY,OKHTTP,LEAKCANARY,
      NESTRECYCLEVIEW,MYVIEWGROUP,AROUTER,VIEW,RXCACHE,VIDEO,BOTTOMSHEET};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
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
    setContentView(R.layout.activity_bottomsheet);
    iniView();
  }



  private void iniView() {
    toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
    toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
    toolbar.setTitle(mTitle);
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    mDatas = new ArrayList<String>();
    Collections.addAll(mDatas,data);
    mAdapter = new HomeAdapter(mDatas);
    RecyclerView  recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(mAdapter);

    final MyBottomBehavior bottomSheetBehavior = MyBottomBehavior.from(recyclerView);
    bottomSheetBehavior.setPeekHeight(40);
    //bottomSheetBehavior.setState(MyBottomBehavior.STATE_COLLAPSED);
    bottomSheetBehavior.setHideable(false);
    bottomSheetBehavior.setSlideAble(true);
    bottomSheetBehavior.setBottomSheetCallback(new MyBottomBehavior.BottomSheetCallback() {

      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState) {
        if (newState == MyBottomBehavior.STATE_EXPANDED) {
        }
      }

      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) {
      }
    });
    CheckBox checkBox = ((CheckBox) findViewById(R.id.cb));
    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bottomSheetBehavior.setState(isChecked ? MyBottomBehavior.STATE_EXPANDED : MyBottomBehavior.STATE_COLLAPSED);
      }
    });
    CheckBox checkBoxOne = ((CheckBox) findViewById(R.id.cb_one));
    checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bottomSheetBehavior.setSlideAble(isChecked);
      }
    });
  }


}
