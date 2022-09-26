package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class EditClass extends AppCompatActivity {

    TextInputLayout editName;
    TextInputLayout editWebsite;
    TextInputLayout notEditId;
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);

        Bundle arguments = getIntent().getExtras();
        String _notEditId = arguments.get("notEditId").toString();
        String _editName = arguments.get("editName").toString();
        String _editWebsite = arguments.get("editWebsite").toString();
        notEditId = findViewById(R.id.notEditID);
        editName = findViewById(R.id.editName);
        editWebsite = findViewById(R.id.editWebsite);

        notEditId.getEditText().setText(_notEditId);
        editName.getEditText().setText(_editName);
        editWebsite.getEditText().setText(_editWebsite);
    }
    public void UpdateData(View v){
        try{
            ConnectionHelper connection = new ConnectionHelper();
            connect = connection.Connection();
            String qu = "update airlines set airline_name = \'" + Objects.requireNonNull(editName.getEditText().getText())
                    + "\', airline_website = \'" + Objects.requireNonNull(editWebsite.getEditText()).getText()
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
}
