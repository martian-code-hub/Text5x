package com.example.martian.dagger;

import com.example.martian.view.Dagger2Activity;

import dagger.Component;

/**
 * Created by yangpei on 2016/11/29.
 */
@Component
public interface PersonComponent {
    void inject(Dagger2Activity dagger2Activity);
}
