package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telecom.ConnectionRequest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list);

    }

    @SuppressLint("NewApi")
    public void OnClick(View v)
    {
        TextView ID = findViewById(R.id.textID);
        TextView Name = findViewById(R.id.textName);
        TextView Website = findViewById(R.id.textWebsite);


        ConnectionHelper helper = new ConnectionHelper();
        ResultSet rs = null;
        Connection connection;

        try {
            connection = helper.Connection();
            if(connection != null) {
                String query = "Select * From airlines";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    ID.setText(resultSet.getString("airline_id"));
                    Name.setText(resultSet.getString("airline_name"));
                    Website.setText(resultSet.getString("airline_website"));
                }
                ConnectionResult = "Success";
                connection.close();
            }
            else{
                ConnectionResult = "Failed";
                Log.e(ConnectionResult,"");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}