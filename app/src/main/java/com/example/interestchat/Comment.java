package com.example.interestchat;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
public class Comment {
    @SerializedName("commentId")
    private String commentId;
    @SerializedName("userId")
    private String userId;
    @SerializedName("comment")
    private String comment;
    @SerializedName("postId")
    private String postId;

    public Comment(String userId, String postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.comment = content;
    }
    public String getCommentId() {
        return commentId;
    }

    public String getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public String getPostId() {
        return postId;
    }

    private static int lastCommentId = 0;

    public static ArrayList<Comment> createCommentList(int numComments) {
        ArrayList<Comment> comments = new ArrayList<Comment>();

        for (int i = 1; i <= numComments; i++) {
            comments.add(new Comment("Username: ", "Post Title ", "Content "));
        }

        return comments;
    }
}