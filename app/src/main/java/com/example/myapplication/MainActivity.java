package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.telecom.ConnectionRequest;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    View v;
    ListView listView;
    String ConnectionResult = "";
    Intent edit_page;
    ImageView imageView;
    ProfileAdapter profileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        imageView = findViewById(R.id.ItemImage);
        edit_page = new Intent(MainActivity.this, EditClass.class);
        GetList(v);
    }
    public void GetList(View v){
        listView = (ListView)findViewById(R.id.list);

        List<Profile> myDataList = null;
        ListItem MyData = new ListItem();
        myDataList = MyData.getList();

        profileAdapter = new ProfileAdapter(MainActivity.this,myDataList);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //listView.setOnItemClickListener((parent, view, position, id) ->{

            String nameText, webSiteText, idText;

            TextView idTv = view.findViewById(R.id.TextID);
            idText = idTv.getText().toString();

            TextView nameTv = view.findViewById(R.id.TextName);
            nameText = nameTv.getText().toString();

            TextView webSiteTv = view.findViewById(R.id.TextWebsite);
            webSiteText = webSiteTv.getText().toString();

            setContentView(R.layout.edit_page);

            ImageView image = view.findViewById(R.id.ItemImage);
            Bitmap imageBm = ((BitmapDrawable)image.getDrawable()).getBitmap();

            TextInputLayout notEditID = findViewById(R.id.notEditID);
            //notEditID.getEditText().setText(idText);
            edit_page.putExtra("notEditID",idText);

            TextInputLayout editName = findViewById(R.id.editName);
            //editName.getEditText().setText(nameText);
            edit_page.putExtra("editName",nameText);

            TextInputLayout editWebSite = findViewById(R.id.editWebsite);
            //editWebSite.getEditText().setText(webSiteText);
            edit_page.putExtra("editWebSite",webSiteText);

            ImageView ivImage = findViewById(R.id.editPhoto);
            //ivImage.setImageBitmap(imageBm);
            edit_page.putExtra("ivImage",imageBm);

            startActivity(edit_page);
        };
        });
    }
    public void goAddPage(View v){
        startActivity(new Intent(MainActivity.this,AddPage.class));
    }

}