package com.example.myapplication;

import android.graphics.Bitmap;

public class Profile {
    String airline_name,airline_website, id;
    Bitmap image;

    public Profile(String _id,String _airline_name,String _airline_website, Bitmap _image){
        this.id = _id;
        this.airline_name = _airline_name;
        this.airline_website = _airline_website;
        this.image = _image;
    }
}
