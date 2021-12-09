package com.example.interestchat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterestChatApi {

    @GET("users/allUsers")
    Call<List<User>> getUsers();
}
