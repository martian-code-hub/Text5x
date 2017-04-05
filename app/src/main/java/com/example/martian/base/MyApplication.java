package com.example.martian.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.martian.BuildConfig;
import com.example.martian.util.CrashHandler;
import com.orhanobut.logger.LogLevel;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by yangpei on 2016/11/10.
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks{

    private static final String TAG = "---";

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        //注册Logger
        com.orhanobut.logger.Logger.init(TAG).setMethodCount(5).setLogLevel(LogLevel.FULL);
        //注册去掉强制退出时的系统弹窗
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        //注册监听activity的生命周期
        registerActivityLifecycleCallbacks(this);

//       iniRefWatcher();

    }

    /**
     * 初始化leakcanary
     */
    private void iniRefWatcher() {
       refWatcher = (BuildConfig.DEBUG)? LeakCanary.install(this):RefWatcher.DISABLED;
    }


    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
