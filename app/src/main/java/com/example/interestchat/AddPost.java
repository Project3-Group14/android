package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subjectField.getText().toString().trim();
                String content = contentField.getText().toString().trim();
            }
        });
    }
}