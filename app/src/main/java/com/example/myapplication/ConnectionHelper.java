package com.example.myapplication;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionHelper
{
    String ip = "ngknn.ru", port = "1433",
            database = "33П-КР-Глодин", userName = "33П", userPassword = "12357";

    public void Connection()
    {
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + userName + ";password=" + userPassword + ";";
            Connection connection = DriverManager.getConnection(ConnectionURL);
            Statement statement = connection.createStatement();


        }
        catch (Exception ex)
        {
            Log.e("Error", ex.getMessage());            
        }
    }
}
