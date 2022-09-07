package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("NewApi")
    public void OnClick(View v)
    {
        TextView ID = findViewById(R.id.textID);
        TextView Name = findViewById(R.id.textName);
        TextView Website = findViewById(R.id.textWebsite);

        ConnectionHelper helper = new ConnectionHelper();
        helper.Connection();
        Connection connection;
        if(connection != null)
        {
            while()
        }
    }

}