package com.example.martian.retrofit;

import com.example.martian.okhttp.MyInterceptors;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangpei on 2016/12/5.
 */

public class RetrofitManager {

    private static Retrofit mRetrofit;

    private static RetrofitService mService;

    private static OkHttpClient mOkHttpClient;

    private static final String BASEURL = "http://news-at.zhihu.com/api/4/";

    public static OkHttpClient getOkHttpClient(){
        if(mOkHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            //MyInterceptors logging = new MyInterceptors();
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(logging)
                    .addInterceptor(logging)
                    .build();
        }
        return mOkHttpClient;
    }

    public static Retrofit getRetrofit(){
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();

        }
        return mRetrofit;
    }

    public static RetrofitService getService(){
        if(mService == null) {
            mService = getRetrofit().create(RetrofitService.class);
        }
        return mService;
    }
}
