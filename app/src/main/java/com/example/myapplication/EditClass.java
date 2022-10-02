package com.example.myapplication;


import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Objects;

public class EditClass extends AppCompatActivity {

    TextInputLayout editName;
    TextInputLayout editWebsite;
    TextInputLayout notEditId;
    Connection connect;
    ImageView editPhoto;
    String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        Bundle arguments = getIntent().getExtras();
        String _notEditId = arguments.get("notEditId").toString();
        String _editName = arguments.get("editName").toString();
        String _editWebsite = arguments.get("editWebsite").toString();
        String _editPhoto = arguments.get("editPhoto").toString();
        notEditId = findViewById(R.id.notEditID);
        editName = findViewById(R.id.editName);
        editWebsite = findViewById(R.id.editWebsite);
        editPhoto = findViewById(R.id.editPhoto);

        notEditId.getEditText().setText(_notEditId);
        editName.getEditText().setText(_editName);
        editWebsite.getEditText().setText(_editWebsite);
        editPhoto.setImageBitmap(getImgBitmap(_editPhoto));



        getImgBitmap(encodedImage);
    }
    //открытие
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
                    editPhoto.setImageBitmap(bitmap);
                    encodedImage = EncodeImage(bitmap);
                } catch (Exception e) {

                }
            }
        }
    });

    //Из строки в изображение
    private Bitmap getImgBitmap(String encodedImg) {
        if (encodedImg != null) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return BitmapFactory.decodeResource(this.getResources(), R.drawable.darlinglogo);
    }
    //Изображение в строку
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
    public void UpdateData(View v){
        try{
            ConnectionHelper connection = new ConnectionHelper();
            connect = connection.Connection();
            String qu = "update airlines set airline_name = \'" + Objects.requireNonNull(editName.getEditText().getText())
                    + "\', airline_website = \'" + Objects.requireNonNull(editWebsite.getEditText()).getText()
                    + "\', image = \'" + EncodeImage(((BitmapDrawable)editPhoto.getDrawable()).getBitmap())
                    + "\' where airline_id = " + Integer.parseInt(notEditId.getEditText().getText().toString());

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(qu);
            connect.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.e("Error - ",throwables.getMessage());
        }
    }
    public void DeleteRow(View v){
        try{
            ConnectionHelper connection = new ConnectionHelper();
            connect = connection.Connection();
            String qu = "Delete from airlines where airline_id = " + Integer.parseInt(notEditId.getEditText().getText().toString());
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(qu);
            connect.close();
            this.finish();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.e("Error - ",throwables.getMessage());
        }
    }
}
