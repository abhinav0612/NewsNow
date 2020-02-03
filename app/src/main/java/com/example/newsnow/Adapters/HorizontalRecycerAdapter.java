package com.example.newsnow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsnow.Fragments.Home_Fragment;
import com.example.newsnow.ImageList;
import com.example.newsnow.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalRecycerAdapter extends RecyclerView.Adapter<HorizontalRecycerAdapter.MyHorizontalViewHolder> {
   private List<ImageList> imageLists;
   private Context mContext;
   OnItemClickListner mListner;

    public HorizontalRecycerAdapter(List<ImageList> imageLists,Context mContext) {
        this.imageLists = imageLists;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_horizontal,parent
                    ,false);
        return new MyHorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHorizontalViewHolder holder, int position) {
        ImageList item = imageLists.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    class MyHorizontalViewHolder extends RecyclerView.ViewHolder
    {   TextView textView;
        CircleImageView imageView;
        public MyHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_horizontal);
            imageView = itemView.findViewById(R.id.image_horizontal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner!=null)
                    {
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListner.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public  interface OnItemClickListner
    {
        void OnItemClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner)
    {
        mListner=listner;
    }
}
