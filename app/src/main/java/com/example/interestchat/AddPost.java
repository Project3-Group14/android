package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPost extends AppCompatActivity {
    Button addPostBtn;
    EditText subjectField, contentField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        addPostBtn = findViewById(R.id.addPostButton);
        subjectField = findViewById(R.id.editTextSubject);
        contentField = findViewById(R.id.editTextContent);
        boolean test = false;
        subjectField.addTextChangedListener(new TextValidator(subjectField) {
            @Override public void validate(TextView textView, String text) {
                /* Validation code here */
                if (text.isEmpty()) {
                    textView.setError("Subject is required!");
                }
            }
        });
        // Validation for text on empty fields for posts
        contentField.addTextChangedListener(new TextValidator(contentField) {
            @Override public void validate(TextView textView, String text) {
                /* Validation code here */
                if (text.isEmpty()) {
                    textView.setError("Content is required!");
                }
            }
        });

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subjectField.getText().toString().trim();
                String content = contentField.getText().toString().trim();
                // Check for good post to upload to DB
                if (!subject.isEmpty() && !content.isEmpty()) {
                    Toast.makeText(AddPost.this, "Good post!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddPost.this, "Bad post!", Toast.LENGTH_LONG).show();                }
            }
        });
    }
}