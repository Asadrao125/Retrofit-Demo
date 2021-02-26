package com.gexton.retrofitdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gexton.retrofitdemo.R;
import com.gexton.retrofitdemo.model.Comment;
import com.gexton.retrofitdemo.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.tvName.setText("Name: " + comment.name);
        holder.tvEmail.setText("Email: " + comment.email);
        holder.tvComment.setText("Comment: " + comment.text);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail, tvComment;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvComment = view.findViewById(R.id.tvComment);
        }
    }
}
