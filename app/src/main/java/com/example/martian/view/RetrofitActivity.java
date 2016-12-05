package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.martian.retrofit.RetrofitManager;
import com.example.martian.util.NetUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    private TextView jsonTv, contentTv, httpTx;



    private static final String URL = "http://news-at.zhihu.com/api/4/stories/latest";


//    private static final int GET = 0;
//    private static final int POST = 1;

    private MyHandler myHandler = new MyHandler();

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
        httpTx = (TextView) findViewById(R.id.activity_retrofit_http_tx);

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_retrofit_get_bt:
                executeData();
                break;
            case R.id.activity_retrofit_post_bt:
                enqueueData();
                break;

            case R.id.activity_retrofit_upload_bt:
                upload();
                break;



            case R.id.activity_retrofit_http_get_bt:
                getHttpConnection();
                break;
            case R.id.activity_retrofit_http_post_bt:
                postHttpConnection();
                break;
        }
    }

    /**
     * 同步请求
     */
    private void executeData() {
        Call<NewsList> repos = RetrofitManager.getService().getLatestNews();
         try {
            Response<NewsList> data = repos.execute();
            jsonTv.setText(data.toString());
            contentTv.setText(data.body() + data.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 异步请求
     */
    private void enqueueData() {
        Call<NewsList> repos = RetrofitManager.getService().getLatestNews();
        repos.enqueue(new Callback<NewsList>() {
            @Override
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

    /**
     * 上传文件(例子)
     @Part和@PartMap是配合@Multipart使用的，使用@PartMap参数类型必须是Map，key就相当于partName,
     value所接受的参数类型跟@Part一样，但建议不要是MultipartBody.Part(因为MultipartBody.Part本身已经包含partName)，
     以下是@Part接受不同参数时的情况：
    1.当方法里传参类型是MultipartBody.Part时，不要这样(@Part("partName") MultipartBody.Part part申明，partName会被忽略，
     因为在构建MultipartBody.Part时，已经包含了part的name在里面；

    2.当方法里传参类型是RequestBody时，会把RequestBody的ContentType的值加到body里面去，
     比如像上面例子这样申明Call<String> uploadPicture(@Part("access_token") RequestBody token);
     那么调用该方法uploadPicture(RequestBody.create(MediaType.parse("multipart/form-data"), "tokenValue"))
     时插入body里面的内容是这样的:

    --d1547544-7ffb-4ebd-913e-8cb21d2ea8f9
    Content-Disposition: form-data; name="access_token"
    Content-Transfer-Encoding: binary
    Content-Type: multipart/form-data; charset=utf-8
    Content-Length: 32
    2.00tJ6X3GiGSdcC5f7433b5a40iAPJA
    --d1547544-7ffb-4ebd-913e-8cb21d2ea8f9--
    3.当方法里传参类型是其他对象类型时，将会通过实例化Retrofit时指定的conveter(下文会解释这是什么)来转换成RequestBody，
     然后跟上面第二点所述处理;
     */
    private void upload() {
        String s = "picture.png";
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), new File(s));

        MultipartBody.Part body = MultipartBody.Part.createFormData("pic", "share.png", requestFile);

        RetrofitManager.getService().uploadPicture(RequestBody.create(MediaType.parse("multipart/form-data"), "tokenValue"), body);

    }

    /**
     * HttpURLConnection  get
     */
    private void getHttpConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = NetUtil.get(URL);
                Logger.d("getHttpConnection---data:" + data);
                Message msg = myHandler.obtainMessage();
                msg.obj = data;
                myHandler.sendMessage(msg);
            }
        }).start();

    }

    /**
     * HttpURLConnection  post
     */
    private void postHttpConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = NetUtil.post(URL, "");
                Message msg = myHandler.obtainMessage();
                msg.obj = data;
                myHandler.sendMessage(msg);
            }
        }).start();
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


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.d("handleMessage---msg:" + msg.obj);
            httpTx.setText(msg.obj.toString());
        }
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
