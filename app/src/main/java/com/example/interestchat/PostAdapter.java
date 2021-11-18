package com.example.interestchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    private List<Post> mPosts;

    // Pass in the contact array into the constructor
    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_post, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Post post = mPosts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.usernameTextView;
        textView.setText(post.getPostUsername());

        TextView textView2 = holder.subjectTextView;
        textView2.setText(post.getPostSubject());

        TextView textView3 = holder.contentTextView;
        textView3.setText(post.getPostContent());

    }
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView usernameTextView;
        public TextView subjectTextView;
        public TextView contentTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            usernameTextView = (TextView) itemView.findViewById(R.id.postUsernameItem);
            subjectTextView = (TextView) itemView.findViewById(R.id.postSubjectItem);
            contentTextView = (TextView) itemView.findViewById(R.id.postContentItem);
        }
    }
}

