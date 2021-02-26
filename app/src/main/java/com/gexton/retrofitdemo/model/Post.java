package com.gexton.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    //@SerializedName("userId")
    public int userId;

    //@SerializedName("id")
    public int id;

    //@SerializedName("title")
    public String title;

    //@SerializedName("body")
    public String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}