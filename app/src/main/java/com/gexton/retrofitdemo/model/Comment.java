package com.gexton.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    public int id;
    public int postID;
    public String name;
    public String email;

    @SerializedName("body")
    public String text;

    public Comment(int postID, int id, String name, String email, String text) {
        this.postID = postID;
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }
}
