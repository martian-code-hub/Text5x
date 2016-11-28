package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martian.R;
import com.example.martian.bean.News;
import com.example.martian.bean.NewsList;
import com.example.martian.data.RetrofitService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/6.
 */
public class RetrofitActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;
    ;

    private TextView jsonTv, contentTv;

    private static final String BASEURL = "http://news-at.zhihu.com/api/4/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

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
        setContentView(R.layout.activity_retrofit);
        iniView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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

        jsonTv = (TextView) findViewById(R.id.activity_retrofit_json);
        contentTv = (TextView) findViewById(R.id.activity_retrofit_content);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createNotifition();
//            }
//        });
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_retrofit_get_bt:
                getData();
                break;
            case R.id.activity_retrofit_post_bt:
                postData();
                break;
        }
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<NewsList> repos = service.getLatestNews();
//         try {
//            Response<NewsList> data = repos.execute();
//            jsonTv.setText(data.toString());
//            contentTv.setText(data.body() + data.message());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        repos.enqueue(new Callback<NewsList>() {
            public void onResponse(Call<NewsList> call, final Response<NewsList> response) {
                Toast.makeText(RetrofitActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jsonTv.setText(response.toString());
                        contentTv.setText(data(response.body()));
                    }
                });

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "failure" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.d("---", t.getMessage().toString());
            }
        });
    }

    private void postData() {
//       StringBuilder sb = new StringBuilder();
//        sb.
    }

    private String data(NewsList data) {
        if (data != null) {
            if (data.getStories() != null) {
                List<News> stories = data.getStories();
                StringBuffer sb = new StringBuffer();
                for (News news : stories) {
                    sb.append(news.getId()).append("  ").append(news.getType()).append("   ").append(news.getTitle()).append("\n");
                }
                return sb.toString();
            }
        }
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
