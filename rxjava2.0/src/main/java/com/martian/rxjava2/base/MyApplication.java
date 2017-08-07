package com.martian.rxjava2.base;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author martian on 2017/4/19.
 */

public class MyApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    ARouter.init(this);
  }
}
