package com.example.martian.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangpei on 2016/12/2.
 */

public class MyInterceptors implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Logger.d(String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

         Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        Logger.d(String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2 - t1) / 1e6d,response.headers()));

        return response;
    }
}
