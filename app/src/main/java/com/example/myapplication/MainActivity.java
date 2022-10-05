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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        imageView = findViewById(R.id.ItemImage);
        edit_page = new Intent(MainActivity.this, EditClass.class);
        GetList(v);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;

                HashMap item =  (HashMap)listView.getItemAtPosition(i);

                edit_page.putExtra("notEditId",item.get("TextID").toString());
                edit_page.putExtra("editName",item.get("TextName").toString());
                edit_page.putExtra("editWebsite",item.get("TextWebsite").toString());
                edit_page.putExtra("airPhoto",item.get("ItemImage").toString());
                startActivity(edit_page);
            }
        });
    }
    SimpleAdapter ad;
    public void GetList(View v){
        listView = (ListView)findViewById(R.id.list);

        List<Map<Bitmap,Map<String,String>>> myDataList = null;
        ListItem MyData = new ListItem();
        myDataList = MyData.getList();

        String[] FromTable = {"TextID","TextName","TextWebsite","ItemImage"};
        int[] ToView = {R.id.TextID,R.id.TextName,R.id.TextWebsite, R.id.ItemImage};

        ad = new SimpleAdapter(MainActivity.this, myDataList,R.layout.table_list,FromTable,ToView);
        listView.setAdapter(ad);
    }
    public void goAddPage(View v){
        startActivity(new Intent(MainActivity.this,AddPage.class));
    }

}