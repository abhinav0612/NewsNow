package com.example.newsnow;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface NewsAPI {
    @GET("v2/top-headlines")
    @Headers({"X-Api-Key:c0130f3cec084c8cb2c0ce484e695614"})
    Call<News> getNews(@QueryMap Map<String,String> parameters);
}
