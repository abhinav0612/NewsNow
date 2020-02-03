package com.example.newsnow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.newsnow.NewsItem;
import com.example.newsnow.R;
import com.example.newsnow.ui.main.ViewPagerList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    private Context context;
    private List<NewsItem> lists;

    public MyPagerAdapter(Context context, List<NewsItem> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_viewpager,container,false);
        ImageView imageView;
        TextView title,description,date;
        imageView = view.findViewById(R.id.previewNewsImage);
        title = view.findViewById(R.id.tv_headline);
        description = view.findViewById(R.id.tv_discription);
        date = view.findViewById(R.id.tv_date);

        title.setText(lists.get(position).getTitle());
        description.setText(lists.get(position).getDescription());
        date.setText(lists.get(position).getPublishedAt());
        Picasso.get().load(lists.get(position).getUrlToImage()).into(imageView);
        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
    }
}
