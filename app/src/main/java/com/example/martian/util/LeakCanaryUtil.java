package com.example.martian.util;

import android.content.Context;
import android.widget.ImageView;

import com.example.martian.R;

/**
 * Created by yangpei on 2016/12/7.
 */

public class LeakCanaryUtil {

    private static LeakCanaryUtil instance = null;
    private Context context = null;

    public LeakCanaryUtil(Context context) {
        this.context = context;
    }
    public static LeakCanaryUtil getInstance(Context context) {
        if (instance == null) {
            instance = new LeakCanaryUtil(context);
        }
        return instance;
    }

}
