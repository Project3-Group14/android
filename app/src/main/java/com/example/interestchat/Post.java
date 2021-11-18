package com.example.interestchat;

import java.util.ArrayList;

public class Post {
    private String postUsername;
    private String postSubject;
    private String postContent;

    public Post(String name, String subject, String content) {
        postUsername = name;
        postSubject = subject;
        postContent = content;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public String getPostSubject() {
        return postSubject;
    }

    public String getPostContent() {
        return postContent;
    }

    private static int lastPostId = 0;

    public static ArrayList<Post> createPostList(int numPosts) {
        ArrayList<Post> posts = new ArrayList<Post>();

        for (int i = 1; i <= numPosts; i++) {
            posts.add(new Post("Person " + ++lastPostId, "Subject ", "Content "));
        }

        return posts;
    }
}