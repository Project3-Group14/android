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

import java.util.ArrayList;
import java.util.List;

public class  ViewPostComments extends AppCompatActivity {
    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_comments);
        // Lookup the recyclerview in activity layout
        RecyclerView rvComments = (RecyclerView) findViewById(R.id.viewMyCommentsRecyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://group14-chat.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterestChatApi interestChatApi = retrofit.create(InterestChatApi.class);

        Intent intent = getIntent();
        String postId = intent.getStringExtra("postId");
        Call<List<Comment>> call = interestChatApi.getCommentsByPostId(postId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Comment> comments = response.body();
                // Initialize comments
                // Create adapter passing in the sample user data
                CommentAdapter adapter = new CommentAdapter(comments);
                // Attach the adapter to the recyclerview to populate items
                rvComments.setAdapter(adapter);
                // Set layout manager to position the items
                rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                // That's all!
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }
}