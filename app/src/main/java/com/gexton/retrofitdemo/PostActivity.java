package com.gexton.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gexton.retrofitdemo.adapters.PostAdapter;
import com.gexton.retrofitdemo.api_manager.ApiService;
import com.gexton.retrofitdemo.app_constants.Const;
import com.gexton.retrofitdemo.model.Comment;
import com.gexton.retrofitdemo.model.Post;
import com.gexton.retrofitdemo.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    ApiService apiService;
    RecyclerView recyclerViewPost;
    List<Post> list = new ArrayList<>();
    Button btnPhotos, btnPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Posts");
        recyclerViewPost = findViewById(R.id.recyclerViewPost);
        recyclerViewPost.setHasFixedSize(true);
        btnPhotos = findViewById(R.id.btnPhotos);
        btnPdf = findViewById(R.id.btnPdf);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        getPosts();

        recyclerViewPost.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerViewPost, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                intent.putExtra("postdId", list.get(position).id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                System.out.println("-- item long press pos: " + position);
            }
        }));

        btnPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PhotosActivity.class));
            }
        });

        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GeneratePdfActivity.class));
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    if (posts != null) {
                        for (Post post : posts) {
                            Post post_p = new Post(post.userId, post.id, post.title, post.body);
                            list.add(post_p);
                        }
                        Log.d("response", "onResponse: " + list);
                        recyclerViewPost.setAdapter(new PostAdapter(PostActivity.this, list));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}