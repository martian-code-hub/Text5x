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
import com.example.martian.R;

/**
 * @author martian on 2017/8/7.
 */

public class VideoActivity extends AppCompatActivity {

  private String mTitle;

  private Toolbar toolbar;

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
    setContentView(R.layout.activity_surfaceview);
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

    //msv = (MySurfaceView) findViewById(R.id.activity_animation_iv);
    //mv = (MyView) findViewById(R.id.myview);
    //mv.setOnClickListener(new View.OnClickListener() {
    //  @Override
    //  public void onClick(View view) {
    //    Toast.makeText(SurfaceViewActivity.this,mv.getId()+":"+msv.getId() , Toast.LENGTH_SHORT).show();
    //  }
    //});

  }


}
