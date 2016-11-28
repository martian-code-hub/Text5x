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
import android.widget.Toast;

import com.example.martian.MyView;
import com.example.martian.R;
import com.example.martian.util.MySurfaceView;

/**
 * Created by Administrator on 2016/7/6.
 */
public class SurfaceViewActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private String mTitle;

    private Toolbar toolbar;

    private MySurfaceView msv;

    private MyView mv ;



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
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                return false;
//            }
//        });
//        imageView = (ImageView) findViewById(R.id.activity_animation_iv);

        msv = (MySurfaceView) findViewById(R.id.activity_animation_iv);
        mv = (MyView) findViewById(R.id.myview);
        mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SurfaceViewActivity.this,mv.getId()+":"+msv.getId() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void click(View view)
    {
        switch (view.getId()){
//            case R.id.activity_animation_iv_two:
//                Drawable drawable = iv.getDrawable();
//                if(drawable instanceof Animatable){
//                    ((Animatable) drawable).start();
//                }
//                break;
//            case R.id.activity_animation_iv_three:
//                Drawable drawable_search = iv_search.getDrawable();
//                if(drawable_search instanceof Animatable){
//                    ((Animatable) drawable_search).start();
//                }
//                break;

        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
