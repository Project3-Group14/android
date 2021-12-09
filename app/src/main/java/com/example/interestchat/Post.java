package com.example.interestchat;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
public class Post {
    @SerializedName("postId")
    private String postId;
    @SerializedName("userId")
    private String userId;
    @SerializedName("postTitle")
    private String postTitle;
    @SerializedName("postDesc")
    private String postDesc;

    public Post(String userId, String subject, String content) {
        this.userId = userId;
        this.postTitle = subject;
        this.postDesc = content;
    }
    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    private static int lastPostId = 0;

    public static ArrayList<Post> createPostList(int numPosts) {
        ArrayList<Post> posts = new ArrayList<Post>();

        for (int i = 1; i <= numPosts; i++) {
            posts.add(new Post("Username: ", "Subject ", "Content "));
        }

        return posts;
    }
}