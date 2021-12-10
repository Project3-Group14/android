package com.example.interestchat;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import retrofit2.http.Path;

public interface InterestChatApi {

    @GET("users/allUsers")
    Call<List<User>> getUsers();

    @GET("/posts/userId/{userId}")
    Call<List<Post>> getPostsByUserId(@Path("userId") String uid);

    @GET("users/userId/{userId}")
    Call<User> getUserByUserId(@Path("userId") String uid);

    @POST("users/save")
    Call<User> createUser(@Body User user);

    //@FormUrlEncoded
    @PUT("users/update/userId={userId}")
    Call<User> updateUser(@Path("userId") String userId, @Body User user);

    @POST("posts/save")
    Call<Post> createPost(@Body Post post);

    @POST("posts/save")
    @FormUrlEncoded
    Call<Post> createPost(
            @Field("userId") String userId,
            @Field("postTitle") String postTitle,
            @Field("postDesc") String postDesc
    );



}
