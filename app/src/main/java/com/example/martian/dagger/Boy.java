package com.example.martian.dagger;

import javax.inject.Inject;

/**
 * Created by yangpei on 2016/11/29.
 */

public class Boy {

    String sex;

    @Inject
    public Boy(){
        sex = "I am a boy";
    }
}
