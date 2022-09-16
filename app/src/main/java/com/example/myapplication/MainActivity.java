package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telecom.ConnectionRequest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    SimpleAdapter ad;
    public void GetList(View v){
        ListView listView = (ListView)findViewById(R.id.list);

        List<Map<String,String>> myDataList = null;
        ListItem MyData = new ListItem();
        myDataList = MyData.getList();

        String[] FromTable = {"TextID","TextName","TextWebsite"};
        int[] ToView = {R.id.TextID,R.id.TextName,R.id.TextWebsite};

        ad = new SimpleAdapter(MainActivity.this, myDataList,R.layout.table_list,FromTable,ToView);
        listView.setAdapter(ad);
    }
    public void goAddPage(View v){
        setContentView(R.layout.adding_page);
    }

}