package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditClass extends AppCompatActivity {

    EditText editName;
    EditText editWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        editName = findViewById(R.id.editName);
        editWebsite = findViewById(R.id.editWebsite);
    }
}
