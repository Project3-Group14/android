package com.example.interestchat;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private  String password;


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
