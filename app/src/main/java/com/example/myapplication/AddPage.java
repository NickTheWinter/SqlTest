package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;



public class AddPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_page);
    }

    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    public void AddItems(View v){
        EditText editName = findViewById(R.id.addName);
        EditText editWebsite = findViewById(R.id.addWebsite);
        if(!editName.getText().toString().equals("") && !editWebsite.getText().toString().equals("")){
            try{
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.Connection();
                if(connection != null){
                    String query = "Insert Into airlines (airline_name,airline_website) Values ('" + editName.getText().toString() + "', '" + editWebsite.getText().toString() + "')";
                    Statement statement = connection.createStatement();
                    statement.executeQuery(query);

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
        this.finish();
    }
}
