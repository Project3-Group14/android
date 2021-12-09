package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    public static final String ACTIVITY_LABEL = "EDIT_PROFILE";

    TextView username;
    EditText password;
    String passwordInput;
    Button save;
    List<User> usersGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        String loginUserId = getIntent().getStringExtra("loginUserId");
        String loginUsername = getIntent().getStringExtra("loginUsername");
        String loginPassword = getIntent().getStringExtra("loginPassword");
        checkUser();

        username = (TextView)findViewById(R.id.username);
        username.setText(loginUsername);
        password = (EditText)findViewById(R.id.password);
        password.setHint(loginPassword);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordInput = password.getText().toString();

                if (passwordInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                }
                else {
                    //startActivity(new Intent(Login.this, Homepage.class));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            checkUser(usernameInput, passwordInput);
                            boolean valid = false;
                            User corrUser = null;
                            //System.out.println(valid);
                            List<User> users = getUsers();
//                            for (User user: users){
//                                if (user.getUsername().equals(usernameInput) && user.getPassword().equals(passwordInput)){
//                                    valid = true;
//                                    corrUser = user;
//                                    break;
//                                }
//                            }
//                            if(valid) {
//                                Intent intent = new Intent(Login.this, MainActivity.class);
//                                intent.putExtra("loginUserId", corrUser.getUserId().toString());
//                                intent.putExtra("loginUsername", corrUser.getUsername());
//                                intent.putExtra("loginPassword", corrUser.getPassword());
//                                startActivity(intent);
//                            }
//                            else {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
                        }
                    }).start();
                }
            }
        });
    }


    private void setUsers(Response<List<User>> response){
        this.usersGlobal = response.body();
    }

    private List<User> getUsers(){
        return this.usersGlobal;
    }

    private void checkUser(){
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
                setUsers(response);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(ACTIVITY_LABEL, "failed");
            }
        });
    }
}