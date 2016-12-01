package com.example.martian.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by yangpei on 2016/12/2.
 */

public class MyImageCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mCache;

    private static final int size = 10*1024*1024;

    public MyImageCache() {
        mCache = new LruCache<String,Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }
}
