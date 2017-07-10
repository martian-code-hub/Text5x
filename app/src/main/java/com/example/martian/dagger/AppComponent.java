package com.example.martian.dagger;


import com.example.martian.view.Dagger2Activity;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yangpei on 2016/11/28.
 */
@Singleton
@Component(modules = {DaggerManage.class})
public interface AppComponent {

    DaggerPresenter createDaggerPresenter();

    void inject(Dagger2Activity dagger2Activity);
}
