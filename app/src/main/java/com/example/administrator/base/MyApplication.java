package com.example.administrator.base;

import android.app.Application;

import com.example.administrator.util.CrashHandler;
import com.orhanobut.logger.LogLevel;


/**
 * Created by yangpei on 2016/11/10.
 */

public class MyApplication extends Application {

    private static final String TAG = "---";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        com.orhanobut.logger.Logger.init(TAG).setMethodCount(5).setLogLevel(LogLevel.FULL);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }


}
