package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddPost extends AppCompatActivity {
    Button addPostBtn;
    EditText subjectField, contentField;
    String loginUserId;
    String loginUsername;
    String loginPassword;

    private InterestChatApi interestChatApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        addPostBtn = findViewById(R.id.addPostButton);
        subjectField = findViewById(R.id.editTextSubject);
        contentField = findViewById(R.id.editTextContent);

        loginUserId = getIntent().getStringExtra("loginUserId");
        loginUsername = getIntent().getStringExtra("loginUsername");
        loginPassword = getIntent().getStringExtra("loginPassword");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://group14-chat.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interestChatApi = retrofit.create(InterestChatApi.class);

        boolean test = false;
        subjectField.addTextChangedListener(new TextValidator(subjectField) {
            @Override public void validate(TextView textView, String text) {
                /* Validation code here */
                if (text.isEmpty()) {
                    textView.setError("Subject is required!");
                }
            }
        });
        // Validation for text on empty fields for posts
        contentField.addTextChangedListener(new TextValidator(contentField) {
            @Override public void validate(TextView textView, String text) {
                /* Validation code here */
                if (text.isEmpty()) {
                    textView.setError("Content is required!");
                }
            }
        });

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subjectField.getText().toString();
                String content = contentField.getText().toString();
                // Check for good post to upload to DB
                if (!subject.isEmpty() && !content.isEmpty()) {
                    //TODO: retrieve userId from props
                    createPost(loginUserId, subject, content);
                    Toast.makeText(AddPost.this, "Good post!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddPost.this, MainActivity.class);
                    intent.putExtra("loginUserId",loginUserId);
                    intent.putExtra("loginUsername", loginUsername);
                    intent.putExtra("loginPassword", loginPassword);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddPost.this, "Bad post!", Toast.LENGTH_LONG).show();                }
            }
        });
    }

    private void createPost(String userId, String subject, String content) {
        final Post post = new Post(userId, subject, content);

        Call<Post> call = interestChatApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Post postResponse = response.body();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                String failMessage = t.getMessage();
            }
        });
    }
}