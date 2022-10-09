package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListItem extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    List<Profile> data;

    public List<Profile> getList(){
        data = null;
        data = new ArrayList<Profile>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.Connection();
            if(connection != null){
                String query = "Select * From airlines";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    Bitmap bitmapImage = getImgBitmap(resultSet.getString("image"));
                    //Bitmap bitmapImage = null;
                    data.add(new Profile(resultSet.getString("airline_id"),resultSet.getString("airline_name"),resultSet.getString("airline_website"),bitmapImage));
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
    //Из строки в изображение
    public Bitmap getImgBitmap(String encodedImg) {
        if (encodedImg != null) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return BitmapFactory.decodeResource(this.getResources(), R.drawable.darlinglogo);
    }
}
