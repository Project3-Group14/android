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

public interface InterestChatApi {

    @GET("users/allUsers")
    Call<List<User>> getUsers();

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
