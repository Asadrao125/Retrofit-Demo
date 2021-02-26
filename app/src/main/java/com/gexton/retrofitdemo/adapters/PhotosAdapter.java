package com.gexton.retrofitdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gexton.retrofitdemo.R;
import com.gexton.retrofitdemo.model.Photos;
import com.gexton.retrofitdemo.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {
    Context context;
    List<Photos> photosList;

    public PhotosAdapter(Context context, List<Photos> photosList) {
        this.context = context;
        this.photosList = photosList;
    }

    @NonNull
    @Override
    public PhotosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_photos, parent, false);
        return new PhotosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.MyViewHolder holder, int position) {
        Photos photos = photosList.get(position);
        holder.tvTitle.setText(photos.title);
        Picasso.get().load(photos.imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView imageView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
