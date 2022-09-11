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
        ListView listView = findViewById(R.id.list);

    }
    SimpleAdapter ad;
    public void GetList(View v){
        ListView listView = (ListView)findViewById(R.id.list);

        List<Map<String,String>> myDataList = null;
        ListItem MyData = new ListItem();
        myDataList = MyData.getList();

        String[] FromTable = {"airline_id","airline_name","airline_website"};
        int[] Tow = {R.id.TextID,R.id.TextName,R.id.TextWebsite};

        ad = new SimpleAdapter(MainActivity.this, myDataList,R.layout.table_list,FromTable,Tow);
        listView.setAdapter(ad);
    }
    /*@SuppressLint("NewApi")
    public void OnClick(View v)
    {
        TextView ID = findViewById(R.id.textID);
        TextView Name = findViewById(R.id.textName);
        TextView Website = findViewById(R.id.textWebsite);


        ConnectionHelper helper = new ConnectionHelper();
        ResultSet rs = null;
        Connection connection;

        try {
            connection = helper.Connection();
            if(connection != null) {
                String query = "Select * From airlines";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    ID.setText(resultSet.getString("airline_id"));
                    Name.setText(resultSet.getString("airline_name"));
                    Website.setText(resultSet.getString("airline_website"));
                }
                ConnectionResult = "Success";
                connection.close();
            }
            else{
                ConnectionResult = "Failed";
                Log.e(ConnectionResult,"");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }*/

}