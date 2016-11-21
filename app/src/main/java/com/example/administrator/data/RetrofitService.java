package com.example.administrator.data;

import com.example.administrator.bean.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yangpei on 2016/9/2.
 */
public interface RetrofitService {

    @GET("stories/latest")
    Call<NewsList> getLatestNews();

//        @GET("stories/latest")
//    String getLatestNews();
}
