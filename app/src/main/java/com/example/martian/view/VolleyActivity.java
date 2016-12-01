package com.example.martian.view;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.martian.R;
import com.example.martian.bean.NewsList;
import com.example.martian.dagger.Util;
import com.example.martian.volley.GsonRequest;
import com.example.martian.volley.MyImageCache;
import com.example.martian.volley.XMLRequest;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;


/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressWarnings("unused")
public class VolleyActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextView tx_1, tx_2;

    private ImageView iv, iv_1;
    private NetworkImageView niv;

    private static final String BASEURL = "http://news-at.zhihu.com/api/4/stories/latest";

    private static final String BASEURL_IMAGE = "http://c.hiphotos.baidu.com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg";


    private static final String BASEURL_XML = "http://flash.weather.com.cn/wmaps/xml/china.xml";


    private RequestQueue requestQueue;
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
        setContentView(R.layout.activity_volley);
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
        tx_1 = (TextView) findViewById(R.id.activity_volley_tx_1);
        tx_2 = (TextView) findViewById(R.id.activity_volley_tx_2);

        iv = (ImageView) findViewById(R.id.activity_volley_iv);
        iv_1 = (ImageView) findViewById(R.id.activity_volley_iv_1);

        niv = (NetworkImageView) findViewById(R.id.activity_volley_niv);


        requestQueue = Volley.newRequestQueue(this);
        niv.setImageUrl(BASEURL_IMAGE, new ImageLoader(requestQueue, new MyImageCache()));
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_volley_bt_1:
                stringRequest();
                break;
            case R.id.activity_volley_bt_2:
                jsonObjectRequest();
                break;
            case R.id.activity_volley_bt_3:
                jsonArrayRequest();
                break;
            case R.id.activity_volley_bt_4:
                imageRequest();
                break;
            case R.id.activity_volley_bt_5:
                imageLoader();
                break;
            case R.id.activity_volley_bt_6:
                gsonRequest();
                break;
            case R.id.activity_volley_bt_7:
                xmlRequest();
                break;


        }
    }

    /**
     * StringRequest
     */
    private void stringRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASEURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.d("--stringRequest-：" + response);
                tx_1.setText(response);
                Toast.makeText(VolleyActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * jsonObjectRequest
     */
    private void jsonObjectRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASEURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tx_1.setText(response.toString());
                Toast.makeText(VolleyActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * jsonArrayRequest
     */
    private void jsonArrayRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASEURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                tx_1.setText(response.toString());
                Toast.makeText(VolleyActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * imageRequest
     */
    private void imageRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(BASEURL_IMAGE, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(imageRequest);
    }

    /**
     * imageLoader
     */
    private void imageLoader() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageloader = new ImageLoader(requestQueue, new MyImageCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(iv_1, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageloader.get(BASEURL_IMAGE, imageListener);
    }

    /**
     * gsonRequest
     */
    private void gsonRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<NewsList> gsonRequest = new GsonRequest<NewsList>(Request.Method.GET, BASEURL, NewsList.class, new Response.Listener<NewsList>() {
            @Override
            public void onResponse(NewsList response) {
                tx_2.setText(Util.data(response));
                Toast.makeText(VolleyActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(gsonRequest);
    }


    /**
     * xmlRequest
     */
    private void xmlRequest() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET, BASEURL_XML, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                //需要实现XML解析
                try {
                    int eventType = response.getEventType();
                    StringBuffer sb = new StringBuffer();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                if ("city".equals(nodeName)) {
                                    sb.append(response.getAttributeValue(0)).append("\n");
                                }
                                break;
                        }
                        eventType = response.next();
                    }
                    Logger.d("-xmlRequest-:" + sb.toString());
                    tx_2.setText(sb.toString());
                    sb = null;
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(VolleyActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "获取失败error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(xmlRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.cancelAll(this);
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
