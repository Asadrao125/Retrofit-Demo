package com.gexton.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String imageUrl;
    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;

    public Photos(String title, String imageUrl, String thumbnailUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}
