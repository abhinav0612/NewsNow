package com.example.newsnow;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    LiveData<List<NewsItem>> mList,myList;
    Repository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public LiveData<List<NewsItem>> getList(String country)
    {
        mList = repository.getList(country);
        return mList;
    }
    public LiveData<List<NewsItem>> getCatagorsiedList(String country,String category)
    {
        myList = repository.getCategorsiedList(country,category);
        return myList;
    }

}
