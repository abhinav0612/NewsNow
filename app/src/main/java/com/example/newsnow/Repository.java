package com.example.newsnow;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsnow.Fragments.Login_Fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

   private Map map,map1;
    private MutableLiveData<List<NewsItem>> newsList = new MutableLiveData<>();


    public Repository() {
    }

    void RetrofitCall(Map map1)
    {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        Call<News> call = newsAPI.getNews(map1);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()){
                    Log.d("---------","Failed : " + response.code());
                    return;
                }
                newsList.setValue(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("-----","erro : " + t.toString());
            }
        });

    }
    LiveData<List<NewsItem>> getList(String country)
    {
        map = new HashMap();
        map.put("country",country);
        RetrofitCall(map);
        return newsList;
    }
    LiveData<List<NewsItem>> getCategorsiedList(String country,String category)
    {
        map1 = new HashMap();
        map1.put("country",country);
        map1.put("category",category);
        RetrofitCall(map1);
        return newsList;
    }
}
