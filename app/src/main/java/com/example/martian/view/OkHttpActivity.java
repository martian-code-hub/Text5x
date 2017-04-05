package com.example.martian.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import com.example.martian.R;
import com.example.martian.bean.NewsList;
import com.example.martian.bean.Person;
import com.example.martian.okhttp.MyInterceptors;
import com.example.martian.util.Util;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.BufferedSink;


/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class OkHttpActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextView tx_1;

    private ImageView iv, iv_1;
    private NetworkImageView niv;

    private static final String BASEURL = "http://news-at.zhihu.com/api/4/stories/latest";

    private static final String BASEURL_IMAGE = "http://c.hiphotos.baidu.com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg";


    private static final String BASEURL_XML = "http://flash.weather.com.cn/wmaps/xml/china.xml";

    private static final String DEFAULT_CACHE_DIR = "okhttp";


    private OkHttpClient okHttpClient;

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
        setContentView(R.layout.activity_okhttp);
        iniView();
        initData();
    }

    private void initData() {
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
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createNotifition();
//            }
//        });
        tx_1 = (TextView) findViewById(R.id.activity_okhttp_tx_1);

        iv = (ImageView) findViewById(R.id.activity_okhttp_iv);
        File cacheDir = new File(this.getCacheDir(), DEFAULT_CACHE_DIR);
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5 * 1000, TimeUnit.SECONDS)
                .readTimeout(30 * 1000, TimeUnit.SECONDS)
                .writeTimeout(10 * 1000, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new MyInterceptors())
                .build();


    }

    public void click(View view) {
        switch (view.getId()) {
            //get
            case R.id.activity_okhttp_bt_1:
                getRequest();
                break;
            //postString
            case R.id.activity_okhttp_bt_2:
                postString();
                break;
            //postStream
            case R.id.activity_okhttp_bt_3:
                postJson();
                break;
            //postStream
            case R.id.activity_okhttp_bt_4:
                postStream();
                break;
            //postfile
            case R.id.activity_okhttp_bt_5:
                postFile();
                break;
            //postform
            case R.id.activity_okhttp_bt_6:
                postForm();
                break;
            //postmultipart
            case R.id.activity_okhttp_bt_7:
                postMultipart();
                break;
            //gson
            case R.id.activity_okhttp_bt_8:
                gson();
                break;

            //cancel
            case R.id.activity_okhttp_bt_9:
                cancel();
                break;


        }
    }
    /**
     * getRequest
     */
    private void getRequest() {
        Request request = new Request.Builder()
                .url(BASEURL)
                .build();
        getResponse(request);
    }

    /**
     * postString
     */
    private void postString() {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

        Request request = new Request.Builder()
                .url(BASEURL)
                .post(RequestBody.create(mediaType, postBody))
                .build();

        getResponse(request);
    }

    /**
     * postJson
     */
    private void postJson() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody_json = RequestBody.create(JSON, jsonToString());
        Request request = new Request.Builder()
                .url(BASEURL)
                .post(requestBody_json)
                .build();

        getResponse(request);

    }

    /**
     * postStream
     */
    private void postStream() {
        final MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

        RequestBody requestBoby = new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }
        };
        Request request = new Request.Builder()
                .url(BASEURL)
                .post(requestBoby)
                .build();
        getResponse(request);

    }

    /**
     * postFile
     */
    private void postFile() {
        File file = new File("README.md");
        MediaType JSON = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, file);
        Request request = new Request.Builder()
                .url(BASEURL)
                .post(requestBody)
                .build();

        getResponse(request);
    }

    /**
     * postForm
     */
    private void postForm() {
        //post Form的键值对
        RequestBody requestBody = new FormBody.Builder()
                .add("okhttp", "martain")
                .build();

        Request request = new Request.Builder()
                .url(BASEURL)
                .post(requestBody)
                .build();

        getResponse(request);
    }

    /**
     * postMultipart
     */
    private void postMultipart() {
        MediaType TEXT = MediaType.parse("text/x-markdown; charset=utf-8");
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition","fomr-data;name=\"title\""),
                        RequestBody.create(TEXT, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        RequestBody.create(MEDIA_TYPE_PNG,new File("website/static/logo-square.png")))
                .addFormDataPart("name","okhttp")
                .build();

        Request request = new Request.Builder()
                .url(BASEURL)
                .post(requestBody)
                .build();

        getResponse(request);
    }

    /**
     * gson
     */
    private void gson() {
        Request request = new Request.Builder()
                .url(BASEURL)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(OkHttpActivity.this, "onFailure+e:" + e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            NewsList newsList =   new Gson().fromJson(response.body().charStream(), NewsList.class);
                            tx_1.setText(Util.data(newsList));
                            Toast.makeText(OkHttpActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OkHttpActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    /**
     * cancel
     */
    private void cancel() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        final long startNanos = System.nanoTime();
        final Call call = okHttpClient.newCall(new Request.Builder()
                    .url(BASEURL).build());

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                call.cancel();
                System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
            }
        },1,TimeUnit.SECONDS);

        try {
            System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
            Response response = call.execute();
            System.out.printf("%.2f Call was expected to fail, but completed: %s%n",(System.nanoTime() - startNanos) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f Call failed as expected: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, e);
        }
    }

    /**
     * OkHttp会自动重试未验证的请求。当响应是401 Not Authorized时，Authenticator会被要求提供证书。
     * Authenticator的实现中需要建立一个新的包含证书的请求。如果没有证书可用，返回null来跳过尝试
     */
    private void authenticating()throws Exception{

        okHttpClient.newBuilder().authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                System.out.println("Authenticating for response: " + response);
                System.out.println("Challenges: " + response.challenges());
                String credential = Credentials.basic("jesse", "password1");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }
        }).build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }

    /**
     * 获取的结果
     * @param request
     */
    private void getResponse(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(OkHttpActivity.this, "onFailure+e:" + e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tx_1.setText(response.toString());
                        if (response.isSuccessful()) {
                            Toast.makeText(OkHttpActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OkHttpActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }




    private String jsonToString() {
        Person person = new Person();
        person.setName("okhttp");
        person.setAge("20");
        person.setSex("boy");
        return new Gson().toJson(person);
    }


    private String factor(int n) {
        for (int i = 2; i < n; i++) {
            int x = n / i;
            if (x * i == n) return factor(x) + " × " + i;
        }
        return Integer.toString(n);
    }


    @Override
    protected void onStop() {
        super.onStop();
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
