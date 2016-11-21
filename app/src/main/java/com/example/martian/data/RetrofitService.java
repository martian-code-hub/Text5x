package com.example.martian.data;

import com.example.martian.bean.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yangpei on 2016/9/2.
 */
public interface RetrofitService {

    @GET("stories/latest")
    Call<NewsList> getLatestNews();

//        @GET("stories/latest")
//    String getLatestNews();
}
