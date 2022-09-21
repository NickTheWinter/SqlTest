package com.example.myapplication;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {
    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    List<Map<String,String>> data;

    public List<Map<String,String>> getList(){
        data = null;
        data = new ArrayList<Map <String,String>>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.Connection();
            if(connection != null){
                String query = "Select * From airlines";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    Map<String,String> dtName =  new HashMap<String, String>();
                    dtName.put("TextID",resultSet.getString("airline_id"));
                    dtName.put("TextName",resultSet.getString("airline_name"));
                    dtName.put("TextWebsite",resultSet.getString("airline_website"));
                    data.add(dtName);
                }
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
        return data;
    }

}
