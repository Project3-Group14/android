package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    public static final String ACTIVITY_LABEL = "REGISTER_ACTIVITY";

    EditText username;
    EditText password;
    String usernameInput;
    String passwordInput;
    Button register;
    Button login;
    private InterestChatApi interestChatApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://group14-chat.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interestChatApi = retrofit.create(InterestChatApi.class);

        String loginUserId = getIntent().getStringExtra("loginUserId");
        String loginUsername = getIntent().getStringExtra("loginUsername");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameInput = username.getText().toString();
                passwordInput = password.getText().toString();

                if (usernameInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
                }
                else if (passwordInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            createUser(usernameInput, passwordInput);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register.this, Login.class));
                                }
                            });
                        }
                    }).start();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, Register.class);
        return intent;
    }

    public void createUser(String username, String password){
        final User user = new User(username, password);
        Call<User> userCall = interestChatApi.createUser(user);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(ACTIVITY_LABEL, "failed");
            }
        });
    }
}