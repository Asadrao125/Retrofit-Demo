package com.gexton.retrofitdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gexton.retrofitdemo.R;
import com.gexton.retrofitdemo.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context context;
    List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvId.setText("ID: " + post.id);
        holder.tvUserId.setText("UserID: " + post.userId);
        holder.tvTitle.setText("Title: " + post.title);
        holder.tvBody.setText("Body: " + post.body);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvUserId, tvTitle, tvBody;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvId = view.findViewById(R.id.tvId);
            tvUserId = view.findViewById(R.id.tvUserId);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvBody = view.findViewById(R.id.tvBody);
        }
    }
}