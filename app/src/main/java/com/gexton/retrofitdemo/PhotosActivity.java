package com.gexton.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;

import com.gexton.retrofitdemo.adapters.CommentAdapter;
import com.gexton.retrofitdemo.adapters.PhotosAdapter;
import com.gexton.retrofitdemo.api_manager.ApiService;
import com.gexton.retrofitdemo.app_constants.Const;
import com.gexton.retrofitdemo.model.Comment;
import com.gexton.retrofitdemo.model.Photos;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {
    RecyclerView recyclerViewPhotos;
    ApiService apiService;
    List<Photos> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        setTitle("Photos");
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos);
        recyclerViewPhotos.setHasFixedSize(true);
        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        getPhotos();

    }

    private void getPhotos() {
        Call<List<Photos>> call = apiService.getPhotos();
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()) {
                    List<Photos> photos = response.body();
                    if (photos != null) {
                        for (Photos photos1 : photos) {
                            Photos photos2 = new Photos(photos1.title, photos1.imageUrl, photos1.thumbnailUrl);
                            list.add(photos2);
                        }
                        Log.d("response", "onResponse: " + list);
                        recyclerViewPhotos.setAdapter(new PhotosAdapter(PhotosActivity.this, list));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }

}