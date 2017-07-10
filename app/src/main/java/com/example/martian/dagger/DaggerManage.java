package com.example.martian.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.martian.data.RetrofitService;
import com.example.martian.view.Dagger2Activity;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangpei on 2016/11/28.
 */
@Module
public class DaggerManage {

    private static final String BASEURL = "http://news-at.zhihu.com/api/4/";

    private Context context;
    private IDaggerActivity ida;
    private int time;

    public DaggerManage(Context context) {
        this.context = context;
    }

    public DaggerManage(Context context, int time) {
        this.context = context;
        this.time = time;
    }

    @Provides
    @Singleton
    public IDaggerActivity createIDaggerActivity() {
        if (context instanceof IDaggerActivity) {
            ida = (IDaggerActivity) context;
        }
        return ida;
    }

    @Provides
    @Singleton
    public OkHttpClient createTimeClient() {
        return new OkHttpClient.Builder().connectTimeout(time, TimeUnit.SECONDS).build();
    }

    @Provides
    @Singleton
    public RetrofitService createRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BASEURL).build().create(RetrofitService.class);
    }

    @Provides
    @Singleton
    public SharedPreferences createSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public UserManager createUserManager(SharedPreferences sp, RetrofitService rs) {
        return new UserManager(sp, rs);
    }

    @Provides
    @Singleton
    public PasswordValidator createPasswordValidator() {
        return new PasswordValidator();
    }

    @Provides
    @Singleton
    public DaggerPresenter createDaggerPresenter(UserManager um, PasswordValidator pv, IDaggerActivity ida) {
        return new DaggerPresenter(um, pv, ida);
    }
}

