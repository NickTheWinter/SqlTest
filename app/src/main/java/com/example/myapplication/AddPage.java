package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class AddPage extends AppCompatActivity {


    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    public void AddItems(View v){
        EditText editAdd = findViewById(R.id.addName);
        EditText editWebsite = findViewById(R.id.addWebsite);
        if(editAdd.getText().toString() != "" && editWebsite.getText().toString() != ""){
            try{
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.Connection();
                if(connection != null){
                    String query = "Insert Into 33П-КР-Глодин (airline_name,airline_website) Values ('" + editAdd + "', '" + editWebsite + "')";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    ConnectionResult = "Success";
                    isSuccess = true;
                    Log.e(ConnectionResult,"");
                    connection.close();
                }
                else
                {
                    ConnectionResult = "Failed";
                    Log.e(ConnectionResult,"");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void Back (View v){
        setContentView(R.layout.activity_main);
    }
}
