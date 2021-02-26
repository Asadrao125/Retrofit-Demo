package com.gexton.retrofitdemo.api_manager;

import com.gexton.retrofitdemo.model.Comment;
import com.gexton.retrofitdemo.model.Photos;
import com.gexton.retrofitdemo.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET("photos")
    Call<List<Photos>> getPhotos();

}
