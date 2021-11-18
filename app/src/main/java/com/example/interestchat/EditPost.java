package com.example.interestchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditPost extends AppCompatActivity {
    Button editPostBtn;
    EditText subjectField, contentField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        editPostBtn = findViewById(R.id.editPostButton);
        subjectField = findViewById(R.id.editTextEditSubject);
        contentField = findViewById(R.id.editTextEditContent);

        editPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subjectField.getText().toString().trim();
                String content = contentField.getText().toString().trim();
            }
        });
    }
}