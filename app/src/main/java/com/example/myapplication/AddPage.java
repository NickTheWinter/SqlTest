package com.example.myapplication;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


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
        TextInputLayout editName = findViewById(R.id.addName);
        TextInputLayout editWebsite = findViewById(R.id.addWebsite);
        ImageView addPhoto = findViewById(R.id.addPhoto);

        String encodedImage = EncodeImage(((BitmapDrawable)addPhoto.getDrawable()).getBitmap());
        if(!editName.getEditText().getText().toString().equals("") && !editWebsite.getEditText().getText().toString().equals("")){
            try{
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.Connection();
                if(connection != null){
                    String query = "Insert Into airlines (airline_name,airline_website,image) Values ('" + editName.getEditText().getText().toString() +
                            "', '" + editWebsite.getEditText().getText().toString() +
                            "', '" + encodedImage +"')";
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
                Log.e("Error:",throwables.getMessage());
            }
        }
    }

    public String EncodeImage(Bitmap bitmap) {
        int prevW = 150;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(bytes);
        }
        return "";
    }

    public void Back (View v){
        this.finish();
    }

}
