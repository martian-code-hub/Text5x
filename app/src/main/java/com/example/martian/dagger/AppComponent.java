package com.example.martian.dagger;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yangpei on 2016/11/28.
 */
@Singleton
@Component(modules = {DaggerManage.class})
public interface AppComponent {

    DaggerPresenter createDaggerPresenter();

//    void inject(Dagger2Activity dagger2Activity);
}
