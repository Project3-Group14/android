package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Login extends AppCompatActivity {
    public static final String ACTIVITY_LABEL = "LOGIN";

    EditText username;
    EditText password;
    String usernameInput;
    String passwordInput;
    Button login;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameInput = username.getText().toString();
                passwordInput = password.getText().toString();

                if (usernameInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT).show();
                }
                else if (passwordInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                }
                else {
                    //startActivity(new Intent(Login.this, Homepage.class));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkUser(usernameInput, passwordInput);
                            boolean valid = getCheck();
                            System.out.println(valid);
                            if(valid) {
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                //intent.putExtra("userId",userEntity.getId());
                                setCheckFalse();
                                startActivity(intent);
                            }
                            else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private boolean getCheck(){
        return this.check;
    }

    private void setCheck(){
        this.check = true;
    }

    private void setCheckFalse(){
        this.check = false;
    }

    private void checkUser(String username, String password){
        System.out.println("USER: " + username);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://group14-chat.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterestChatApi interestChatApi = retrofit.create(InterestChatApi.class);
        Call<List<User>> call = interestChatApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        setCheck();
                        return;
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(ACTIVITY_LABEL, "failed");
            }
        });
    }
}