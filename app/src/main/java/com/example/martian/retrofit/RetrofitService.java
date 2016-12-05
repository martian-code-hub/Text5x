package com.example.martian.retrofit;

import com.example.martian.bean.NewsList;
import com.example.martian.bean.Person;
import com.example.martian.mvp.User;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by yangpei on 2016/9/2.
 */
public interface RetrofitService {

    @GET("stories/latest")
    Call<NewsList> getLatestNews();

    //------------------例子---------------------------
    //@GET  @Path  @Query   @QueryMap
    @GET("user/{id}")
    Call<Person>  getPerson(@Path("id") String id);

    @GET("user/{id}/{name}")
    Call<Person>  getPerson(@Path("id") String id, @Path("name") String name, @Query("age") String age);

    @GET("user/{id}/{name}")
    Call<Person>  getPerson(@Path("id") String id, @Path("name") String name, @QueryMap Map<String,String> options);

    //-----------------------------------------------
    //@GET  @Body
    @POST("user/update")
    Call<Person>  updatPerson(@Body Person person);
    //-----------------------------------------------
    //@FormUrlEncoded  @Field  @FieldMap
    @FormUrlEncoded
    @POST("user/update")
    Call<Person>  updatPerson(@Field("name") String name,@Field("age") String age);

    @FormUrlEncoded
    @POST("user/update")
    Call<Person>  updatPerson(@FieldMap Map<String,String> data);

    //-----------------------------------------------
    //@Multipart  @Part  @FieldMap
    @Multipart
    @POST("user/update")
    Call<Person>  upLoadHeader(@Part("photo")RequestBody photo, @Part("description") RequestBody description);

    @Multipart
    @POST("user/update")
    Call<Person>  upLoadHeader(@PartMap Map<String ,RequestBody> requestBodys);

    @Multipart
    @POST("https://api.weibo.com/2/statuses/upload_pic.json")
    Call<String> uploadPicture(@Part("access_token") RequestBody token, @Part MultipartBody.Part file);

    //-----------------------------------------------
    //@Header  @HeaderMap    @Headers

    @GET("users/yuhengye")
    Call<String> getUserInfo(@Header("Accept-Language") List<String> langs);

//    List<String> langs = new ArrayList<>();
//    langs.add("en-US");
//    langs.add("zh-CN");


    @GET("users/yuhengye")
    Call<String> getUserInfo(@HeaderMap Map<String, String> headers);

//    Map<String,String> headers = new HashMap<>();
//    headers.put("Accept", "text/plain");
//    headers.put("Accept-Charset", "utf-8");
//
//    //Request Header
//      Accept:text/plain
//      Accept-Charset:utf-8
//    getUserInfo(headers);

    @Headers({
            "Cache-Control: max-age=640000",
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("user/{id}/photo/")
    Call<Person>  getHeader( @Path("id") String id);

    @POST("user/{id}/photo/")
    Call<Person>  getHeader( @Header("Cache-Control") String head,@Path("id") String id);


    //-----------------------------------------------
    //@Streaming
    @Streaming
    @GET("user/{id}/vido")
    Response getVido();

//    Response response = getVideo();
//    InputStream videoDataStream = response.getBody().in();
//    使用@Streaming时返回值必须是Response，包含了HTTP请求最初的body内容，未经任何转换；
//    一般是获取比较大的数据流时使用，或者下载文件时使用;

    //-----------------------------------------------
    //@Url
    @GET
    Call<Person> getPersonFromUrl(@Url String url);


}
