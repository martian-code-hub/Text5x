package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.martian.R;
import com.example.martian.dex.IShowToast;
import com.example.martian.util.FileUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;

/**
 * @author martian on 2017/11/27.
 */

public class DexActivity extends AppCompatActivity {

  private String mTitle;
  private Toolbar toolbar;
  @Bind(R.id.toolbar_toolbar) Toolbar toolbarToolbar;
  @Bind(R.id.tv_button) Button tvButton;
  @Bind(R.id.tv_toast) Button tvToast;

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
    setContentView(R.layout.activity_dex);
    ButterKnife.bind(this);
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
  }

  @OnClick({R.id.tv_toast, R.id.tv_button})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_toast:
        //IShowToast iShowToast = new ShowToastImpl();
        //iShowToast.showToast(this);
        break;
      case R.id.tv_button:
        loadDex();
        break;
    }
  }
  /**
   * 加载动态的dex
   */
  private void loadDex() {
    File cacheFile = FileUtils.getCacheDir(getApplicationContext());
    String internalPath = cacheFile.getAbsolutePath() + File.separator + "dynamic_dex.jar";
    File desFile = new File(internalPath);
    try {
      if (!desFile.exists()) {
        desFile.createNewFile();
        FileUtils.copyFiles(this, "dynamic_dex.jar", desFile);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    getDex(internalPath,cacheFile);
  }
  /**
   * 获取动态的dex
   */
  private void getDex(String internalPath,File cacheFile) {
    //下面开始加载dex class
    DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
    try {
      Class libClazz = dexClassLoader.loadClass("com.example.martian.dex.impl.ShowToastImpl");
      IShowToast dynamic = (IShowToast) libClazz.newInstance();
      if (dynamic != null)
        dynamic.showToast(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}