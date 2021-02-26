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
import com.gexton.retrofitdemo.api_manager.ApiService;
import com.gexton.retrofitdemo.app_constants.Const;
import com.gexton.retrofitdemo.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    RecyclerView recyclerViewComments;
    ApiService apiService;
    List<Comment> list = new ArrayList<>();
    int postId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setTitle("Comments");
        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        recyclerViewComments.setHasFixedSize(true);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        postId = getIntent().getIntExtra("postdId", 1000);
        getComments(postId);
    }

    private void getComments(int id) {
        Call<List<Comment>> call = apiService.getComments(id);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    List<Comment> comments = response.body();
                    if (comments != null) {
                        for (Comment comment : comments) {
                            Comment post_p = new Comment(comment.id, comment.postID, comment.name, comment.email, comment.text);
                            list.add(post_p);
                        }
                        Log.d("response", "onResponse: " + list);
                        recyclerViewComments.setAdapter(new CommentAdapter(CommentActivity.this, list));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }
}