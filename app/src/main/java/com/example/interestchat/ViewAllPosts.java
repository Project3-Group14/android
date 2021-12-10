package com.example.interestchat;

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

import java.util.List;

public class ViewAllPosts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_posts);
        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.viewAllPostsRecyclerView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://group14-chat.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterestChatApi interestChatApi = retrofit.create(InterestChatApi.class);

        Call<List<Post>> call = interestChatApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Post> posts = response.body();
                // Initialize posts
                // Create adapter passing in the sample user data
                PostAdapter adapter = new PostAdapter(posts);
                // Attach the adapter to the recyclerview to populate items
                rvPosts.setAdapter(adapter);
                // Set layout manager to position the items
                rvPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                // That's all!
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}