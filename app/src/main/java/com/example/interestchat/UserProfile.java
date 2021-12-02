package com.example.interestchat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    Button myPost, savedPost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Button btn = findViewById(R.id.myPostBtn);
        myPost = findViewById(R.id.viewMyPostButton);
        savedPost = findViewById(R.id.viewSavedButton);
    }
}
