package com.example.martian.dagger;

import com.example.martian.view.Dagger2Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yangpei on 2016/11/29.
 */
@Singleton
@Component (modules = OkHttpManage.class)
public interface OkHttpClientComponent {

//    OkHttpClient createOkHttpClient();

    void inject(Dagger2Activity dagger2Activity);
}
