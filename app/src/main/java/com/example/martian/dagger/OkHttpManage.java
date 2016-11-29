package com.example.martian.dagger;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by yangpei on 2016/11/29.
 */
@Module
public class OkHttpManage {
    private int time;

//   public OkHttpManage(int time){
//       this.time = time;
//   }
    @Provides @Named("defult")
    @Singleton
    public OkHttpClient createDefultClient(){
        Logger.d("createOkHttpClient--defule");
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).build();
    }
    @Provides @Named("time")
    @Singleton
    public OkHttpClient createTimeClient(){
        Logger.d("createOkHttpClient--time");
        return new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
    }
}
