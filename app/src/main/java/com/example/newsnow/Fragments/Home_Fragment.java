package com.example.newsnow.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.newsnow.Adapters.HorizontalRecycerAdapter;
import com.example.newsnow.Adapters.MyPagerAdapter;
import com.example.newsnow.Adapters.VerticalRecyclerAdapter;
import com.example.newsnow.HomeViewModel;
import com.example.newsnow.ImageList;
import com.example.newsnow.NewsItem;
import com.example.newsnow.R;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment implements HorizontalRecycerAdapter.OnItemClickListner {
    RecyclerView recyclerViewHorizontal,recyclerViewvertical;
    List<ImageList> imageList;
    LiveData<List<NewsItem>> lists,mylist;
    ViewPager viewPager;
    HomeViewModel viewModel;
    String category="general";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        imageList=new ArrayList<>();
        imageList.add(new ImageList(R.drawable.business,"Business"));
        imageList.add(new ImageList(R.drawable.entertainment,"Entertainment"));
        imageList.add(new ImageList(R.drawable.technology,"Technology"));
        imageList.add(new ImageList(R.drawable.sports,"Sports"));
        imageList.add(new ImageList(R.drawable.science,"Science"));
        imageList.add(new ImageList(R.drawable.health,"Health"));


        recyclerViewHorizontal= view.findViewById(R.id.horizontal_recycler);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(Home_Fragment.this.getContext(),LinearLayoutManager.HORIZONTAL
        ,false));
        HorizontalRecycerAdapter adapter = new HorizontalRecycerAdapter(imageList,Home_Fragment.this.getContext());
        adapter.setOnItemClickListner(Home_Fragment.this);
        recyclerViewHorizontal.setAdapter(adapter);
        recyclerViewHorizontal.setHasFixedSize(true);
        ////////////////////////////////////////
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        lists = viewModel.getList("in");
        viewPager = view.findViewById(R.id.viewPager);
        lists.observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                MyPagerAdapter adapter1 = new MyPagerAdapter(Home_Fragment.this.getContext(),newsItems);
                viewPager.setAdapter(adapter1);
            }
        });
        //////////////////////////////
        mylist = viewModel.getCatagorsiedList("in",category);
        recyclerViewvertical = view.findViewById(R.id.vertical_recycler);
        recyclerViewvertical.setLayoutManager(new LinearLayoutManager(Home_Fragment.this.getContext()));
        recyclerViewvertical.setHasFixedSize(true);
        mylist.observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                VerticalRecyclerAdapter adapter2 = new VerticalRecyclerAdapter(newsItems,Home_Fragment.this.getContext());
                recyclerViewvertical.setAdapter(adapter2);
            }
        });


        return view;
    }

    @Override
    public void OnItemClick(int position) {
        ImageList clickedItem = imageList.get(position);
        switch (clickedItem.getTitle()){
            case "Business" :
                category="business";
                mylist = viewModel.getCatagorsiedList("in",category);
            case "Entertainment" :
                category="entertainment";
                mylist = viewModel.getCatagorsiedList("in",category);
            case "Sports" :
                category="sports";
                mylist = viewModel.getCatagorsiedList("in",category);
            case "Technology" :
                category="technology";
                mylist = viewModel.getCatagorsiedList("in",category);
            case "Science" :
                category="science";
                mylist = viewModel.getCatagorsiedList("in",category);
            case "Health" :
                category="health";
                mylist = viewModel.getCatagorsiedList("in",category);
        }
    }
}
