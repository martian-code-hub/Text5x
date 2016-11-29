package com.example.martian.dagger;

import android.content.SharedPreferences;

import com.example.martian.data.RetrofitService;

/**
 * Created by yangpei on 2016/11/28.
 */

public class UserManager {

    private final SharedPreferences mPref;
    private final RetrofitService retrofitService;

    public UserManager(SharedPreferences mPref, RetrofitService retrofitService) {
        this.mPref = mPref;
        this.retrofitService = retrofitService;
    }

    public SharedPreferences getmPref() {
        return mPref;
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
