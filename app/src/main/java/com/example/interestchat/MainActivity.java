package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.myPostBtn);
        Button goToPostbtn = findViewById(R.id.goToAddPostButton);
        Button goToUsersTestbtn = findViewById(R.id.goToUsersTest);
        Button editBtn = findViewById(R.id.editProfileButton);
        String loginUserId = getIntent().getStringExtra("loginUserId");
        String loginUsername = getIntent().getStringExtra("loginUsername");
        String loginPassword = getIntent().getStringExtra("loginPassword");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewMyPosts.class);
                intent.putExtra("loginUserId",loginUserId);
                intent.putExtra("loginUsername", loginUsername);
                intent.putExtra("loginPassword", loginPassword);
                startActivity(intent);
            }
        });
        goToPostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPost.class);
                intent.putExtra("loginUserId",loginUserId);
                intent.putExtra("loginUsername", loginUsername);
                intent.putExtra("loginPassword", loginPassword);
                startActivity(intent);
            }
        });
        goToUsersTestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserRetrofitTest.class));
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                intent.putExtra("loginUserId", loginUserId);
                intent.putExtra("loginUsername", loginUsername);
                intent.putExtra("loginPassword", loginPassword);
                startActivity(intent);
            }
        });
    }
}