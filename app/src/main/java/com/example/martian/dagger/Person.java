package com.example.martian.dagger;

import javax.inject.Inject;

/**
 * Created by yangpei on 2016/11/29.
 */

public class Person {

    private Boy boy;

    @Inject
    public Person(Boy boy) {
        this.boy = boy;
    }

    public String say() {
        return boy.sex;
    }
}
