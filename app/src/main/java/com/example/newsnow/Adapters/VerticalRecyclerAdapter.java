package com.example.newsnow.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsnow.NewsItem;
import com.example.newsnow.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<VerticalRecyclerAdapter.MyVerticalViehHolder> {
    List<NewsItem> mlist;
    Context context;


    public VerticalRecyclerAdapter(List<NewsItem> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVerticalViehHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.card_recycler,parent,false);
        return new MyVerticalViehHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVerticalViehHolder holder, int position) {
        holder.title.setText(mlist.get(position).getTitle());
        holder.date.setText(mlist.get(position).getPublishedAt());
       // holder.type.setText(mlist.get(position).getSource().getName());
        Picasso.get().load(mlist.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyVerticalViehHolder extends RecyclerView.ViewHolder
    {   TextView title,date,type;
        ImageView imageView;
        public MyVerticalViehHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recycle_card_title);
            date = itemView.findViewById(R.id.recycle_card_time);
           // type = itemView.findViewById(R.id.recycle_card_type);
            imageView = itemView.findViewById(R.id.recycle_card_image);

        }
    }
}
