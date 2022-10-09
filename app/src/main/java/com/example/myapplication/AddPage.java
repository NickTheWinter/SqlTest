package com.example.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


public class AddPage extends AppCompatActivity {

    ImageView addPhoto;
    String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_page);
        addPhoto = findViewById(R.id.addPhoto);
    }

    Connection connection;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    public void AddItems(View v){
        TextInputLayout editName = findViewById(R.id.addName);
        TextInputLayout editWebsite = findViewById(R.id.addWebsite);


        encodedImage = EncodeImage(((BitmapDrawable)addPhoto.getDrawable()).getBitmap());
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
    public void ImageChoose(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImg.launch(intent);
    }
    //отдельный метод для открытия
    private final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            if (result.getData() != null) {
                Uri uri = result.getData().getData();
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    addPhoto.setImageBitmap(bitmap);
                    encodedImage = EncodeImage(bitmap);
                } catch (Exception e) {

                }
            }
        }
    });
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
