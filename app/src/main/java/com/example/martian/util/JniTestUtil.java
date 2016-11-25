package com.example.martian.util;

/**
 * Created by yangpei on 2016/11/22.
 */

public class JniTestUtil {

    static {
        System.loadLibrary("jnitestlib");
    }

    public static native String getNativeData();
}
