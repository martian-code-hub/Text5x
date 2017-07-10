package com.example.martian.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.martian.R;
import com.example.martian.base.IntentKey;
import com.example.martian.bean.NewsList;
import com.example.martian.retrofit.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Route(path = IntentKey.AROUTER_RXCAHE)
public class RxCacheActivity extends AppCompatActivity {

  private String str;
  private String mTitle;

  private TextView textView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rxcache);
    iniData();
    iniView();
  }

  private void iniData() {
    int flag = getIntent().getExtras().getInt("flag");
    mTitle = getIntent().getExtras().getString("title");
  }

  private void iniView() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    textView = (TextView) findViewById(R.id.textView);
    textView.setText(mTitle);
  }


  /**
   * 异步请求
   */
  private void enqueueData() {
    Call<NewsList> repos = RetrofitManager.getService().getLatestNews();
    repos.enqueue(new Callback<NewsList>() {
      @Override
      public void onResponse(Call<NewsList> call, final Response<NewsList> response) {
        Toast.makeText(RxCacheActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            textView.setText(response.toString());
          }
        });
      }

      @Override
      public void onFailure(Call<NewsList> call, Throwable t) {
        Log.d("---", t.getMessage().toString());
      }
    });
  }
}
